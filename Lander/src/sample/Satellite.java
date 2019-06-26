package sample;

import java.util.ArrayList;

public class Satellite extends CelestialBody {

	CelestialBody parent;

	Satellite(double mass, double x, double y, double z, double vx, double vy, double vz, double semiMajorAxis,
			double size, CelestialBody parent) {
		super(mass, x, y, z, vx, vy, vz, semiMajorAxis, size);
		this.parent = parent;
	}

	public double[] update(double dt) {
		double dx = calculateX(dt);
		double dy = calculateY(dt);
		double dz = calculateZ(dt);
		this.x += dx;
		this.y += dy;
		this.z += dz;
		return new double[] { this.x, this.y, this.z };
		// System.out.println("X pos = " + x + " Y pos = " + y);
	}

	private double calculateX(double dt) {
		calculateVx(dt);
		return (this.vx * dt);
	}

	private double calculateY(double dt) {
		calculateVy(dt);
		return (this.vy * dt);
	}

	private double calculateZ(double dt) {
		calculateVz(dt);
		return (this.vz * dt);
	}

	private void calculateVx(double dt) {
		double accX = calculateAx();
		// System.out.println(/*"X acc = " + */accX);
		this.vx += (accX * dt);
	}

	private void calculateVy(double dt) {
		double accY = calculateAy();
		// System.out.print(" Y acc = " + accY + "\n");
		this.vy += (accY * dt);
	}

	private void calculateVz(double dt) {
		double accZ = calculateAz();
		this.vz += (accZ * dt);
	}

	private double calculateAx() {
		return (bigG * parent.getMass() * (-this.x))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);

		// return (bigG * this.parent.getMass()*(this.parent.getX()-this.x)) /
		// Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) +
		// ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);
	}

	private double calculateAy() {
		return (bigG * parent.getMass() * (-this.y))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);

		// return (bigG * this.parent.getMass()*(this.parent.getY()-this.y)) /
		// Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) +
		// ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);
	}

	private double calculateAz() {
		return (bigG * parent.getMass() * (-this.z))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);
	}

	public double[] update(ArrayList<CelestialBody> planets, double dt) {
		double[] first = new double[3];
		double[] second = new double[3];
		double[] third = new double[3];
		double[] fourth = new double[3];
		double[] acceleration = new double[3];

		for (Object object : planets) {
			if (object instanceof CelestialBody) {
				CelestialBody planet = (CelestialBody) object;
				if (planet != this) {
					double distance = Math.pow((this.x - planet.getX()), 2) + Math.pow((this.y - planet.getY()), 2)
							+ Math.pow((this.z - planet.getZ()), 2);
					double dist = Math.sqrt(distance);
					double temp = bigG * planet.getMass() / Math.pow(dist, 3);

					// Euler acceleration -- ~First Order
					first[0] = temp * (planet.getX() - this.x);
					first[1] = temp * (planet.getY() - this.y);
					first[2] = temp * (planet.getZ() - this.z);

					// ~Second Order after a half timestep
					double[] temp_vel = step(this.vx, this.vy, this.vz, first, 0.5 * dt);
					double[] temp_position = step(this.x, this.y, this.z, temp_vel, 0.5 * dt);
					second[0] = (planet.getX() - temp_position[0] - this.parent.getX()) * temp;
					second[1] = (planet.getY() - temp_position[1] - this.parent.getY()) * temp;
					second[2] = (planet.getZ() - temp_position[2] - this.parent.getZ()) * temp;

					// ~Third Order after a half timestep
					temp_vel = step(this.vx, this.vy, this.vz, second, 0.5 * dt);
					temp_position = step(this.x, this.y, this.z, temp_vel, 0.5 * dt);
					third[0] = (planet.getX() - temp_position[0] - this.parent.getX()) * temp;
					third[1] = (planet.getY() - temp_position[1] - this.parent.getY()) * temp;
					third[2] = (planet.getZ() - temp_position[2] + this.parent.getZ()) * temp;

					// ~Fourth Order after 1 timestep in the future using
					temp_vel = step(this.vx, this.vy, this.vz, third, dt);
					temp_position = step(this.x, this.y, this.z, temp_vel, dt);
					fourth[0] = (planet.getX() - temp_position[0] - this.parent.getX()) * temp;
					fourth[1] = (planet.getY() - temp_position[1] - this.parent.getY()) * temp;
					fourth[2] = (planet.getZ() - temp_position[2] - this.parent.getZ()) * temp;

					acceleration[0] += (first[0]); // + 2 * second[0])/3; //+ 2 * third[0] + fourth[0])/6;
					acceleration[1] += (first[1]); // + 2 * second[1])/3; // + 2 * third[1] + fourth[1])/6;
					acceleration[2] += (first[2]); // + 2 * second[2])/3; // + 2 * third[2] + fourth[2])/6;
				}
			} else if (object instanceof Star) {
				Star planet = (Star) object;
				double distance = Math.pow((this.x), 2) + Math.pow((this.y), 2) + Math.pow((this.z), 2);
				double dist = Math.sqrt(distance);
				double temp = bigG * planet.getMass() / Math.pow(dist, 3);

				// Euler acceleration -- ~First Order
				first[0] = (-this.x) * temp;
				first[1] = (-this.y) * temp;
				first[2] = (-this.z) * temp;

				// ~Second Order after a half timestep
				double[] temp_vel = step(this.vx, this.vy, this.vz, first, 0.5 * dt);
				double[] temp_position = step(this.x, this.y, this.z, temp_vel, 0.5 * dt);
				second[0] = (-temp_position[0] - this.parent.getX()) * temp;
				second[1] = (-temp_position[1] - this.parent.getY()) * temp;
				second[2] = (-temp_position[2] - this.parent.getZ()) * temp;

				// ~Third Order after a half timestep
				temp_vel = step(this.vx, this.vy, this.vz, second, dt * 0.5);
				temp_position = step(this.x, this.y, this.z, temp_vel, dt * 0.5);
				third[0] = (-temp_position[0] - this.parent.getX()) * temp;
				third[1] = (-temp_position[1] - this.parent.getY()) * temp;
				third[2] = (-temp_position[2] - this.parent.getZ()) * temp;

				// ~Fourth Order after 1 timestep in the future using
				temp_vel = step(this.vx, this.vy, this.vz, third, dt);
				temp_position = step(this.x, this.y, this.z, temp_vel, dt);
				fourth[0] = (-temp_position[0] - this.parent.getX()) * temp;
				fourth[1] = (-temp_position[1] - this.parent.getY()) * temp;
				fourth[2] = (-temp_position[2] - this.parent.getZ()) * temp;

				acceleration[0] += (first[0]);// + (2 * second[0]))/3; //+ (2 * third[0]) + fourth[0])/6;
				acceleration[1] += (first[1]);// + (2 * second[1]))/3; //+ (2 * third[1]) + fourth[1])/6;
				acceleration[2] += (first[2]);// + (2 * second[2]))/3; // + (2 * third[2]) + fourth[2])/6;
			}
		}

		double[] velocity = step(this.vx, this.vy, this.vz, acceleration, dt);
		double[] position = step(this.x, this.y, this.z, velocity, dt);

		this.vx = velocity[0];
		this.vy = velocity[1];
		this.vz = velocity[2];
		// System.out.println("X="+acceleration[0]+" Y= "+ acceleration[1] +" Z="+
		// acceleration[2]);
		this.x = position[0];
		this.y = position[1];
		this.z = position[2];

		return position;
	}

	public CelestialBody getParentPlanet() {
		return parent;
	}

	public void setParent(CelestialBody parent) {
		this.parent = parent;
	}
}