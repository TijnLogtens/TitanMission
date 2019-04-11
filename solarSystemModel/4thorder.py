def calculate_single_body_acceleration(self, body_index):
        G_const = 6.67408e-11 #m3 kg-1 s-2
        acceleration = point(0,0,0)
        target_body = self.bodies[body_index]

        k1 = point (0,0,0)
        k2 = point (0,0,0)
        k3 = point (0,0,0)
        k4 = point (0,0,0)
        tmp_loc = point (0,0,0)
        tmp_vel = point (0,0,0)

        for index, external_body in enumerate(self.bodies):
            if index != body_index:
                r = (target_body.location.x - external_body.location.x)**2 + (target_body.location.y - external_body.location.y)**2 + (target_body.location.z - external_body.location.z)**2
                r = math.sqrt(r)
                tmp = G_const * external_body.mass / r**3

                #k1 - regular Euler acceleration
                k1.x = tmp * (external_body.location.x - target_body.location.x)
                k1.y = tmp * (external_body.location.y - target_body.location.y)
                k1.z = tmp * (external_body.location.z - target_body.location.z)

                #k2 - acceleration 0.5 timesteps in the future based on k1 acceleration value
                tmp_vel = partial_step(target_body.velocity, k1, 0.5)
                tmp_loc = partial_step(target_body.location, tmp_vel, 0.5 * self.time_step)
                k2.x = (external_body.location.x - tmp_loc.x) * tmp
                k2.y = (external_body.location.y - tmp_loc.y) * tmp
                k2.z = (external_body.location.z - tmp_loc.z) * tmp

                #k3 acceleration 0.5 timesteps in the future using k2 acceleration
                tmp_vel = partial_step(target_body.velocity, k2, 0.5)
                tmp_loc = partial_step(target_body.location, tmp_vel, 0.5 * self.time_step)
                k3.x = (external_body.location.x - tmp_loc.x) * tmp
                k3.y = (external_body.location.y - tmp_loc.y) * tmp
                k3.z = (external_body.location.z - tmp_loc.z) * tmp

                #k4 - location 1 timestep in the future using k3 acceleration
                tmp_vel = partial_step(target_body.velocity, k3, 1)
                tmp_loc = partial_step(target_body.location, tmp_vel, self.time_step)
                k4.x = (external_body.location.x - tmp_loc.x) * tmp;
                k4.y = (external_body.location.y - tmp_loc.y) * tmp;
                k4.z = (external_body.location.z - tmp_loc.z) * tmp;

                acceleration.x += (k1.x + k2.x * 2 + k3.x * 2 + k4.x) / 6;
                acceleration.y += (k1.y + k2.y * 2 + k3.y * 2 + k4.y) / 6;
                acceleration.z += (k1.z + k2.z * 2 + k3.z * 2 + k4.z) / 6;

        return acceleration