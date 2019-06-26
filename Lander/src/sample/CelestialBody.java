package sample;

import java.util.*;

public class CelestialBody {

	//variables
	protected final int DISTANCE_SCALER = (int) 5E9;
	protected final int SIZE_SCALER = (int) 5E5;

	protected double mass;
	protected double x;
	protected double y;
	protected double z;
	protected double vx;
	protected double vy;
	protected double vz;
	protected double rSOI;
	protected double semiMajorAxis;
	protected double size;
	protected double bigG = 6.674E-11;
	private Star parent;

	public double[] trail_x = new double[250];
	public double[] trail_y = new double[250];
	public double[] trail_z = new double[250];

	//constructors
	CelestialBody(double mass, double x, double y, double z, double vx, double vy, double vz, double semiMajorAxis,
			double size) {
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.semiMajorAxis = semiMajorAxis;
		this.rSOI = rSOIcalc();
		this.size = size;
		for (int i = 0; i < trail_x.length; i++) {
			trail_x[i] = -1;
			trail_y[i] = -1;
			trail_z[i] = -1;
		}
	}

	CelestialBody(double mass, double x, double y, double z, double vx, double vy, double vz, double semiMajorAxis,
			double size, Star parent) {
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.semiMajorAxis = semiMajorAxis;
		this.rSOI = rSOIcalc();
		this.size = size;
		this.parent = parent;
		for (int i = 0; i < trail_x.length; i++) {
			trail_x[i] = -1;
			trail_y[i] = -1;
			trail_z[i] = -1;
		}
	}

	CelestialBody(double mass, double x, double y, double vx, double vy) {
		this.mass = mass;
		this.x = x;
		this.y = y;
		//this.z = z;
		this.vx = vx;
		this.vy = vy;
		//this.vz = vz;

	}


	//methods
	/**
	 * Calculate the sphere of influence
	 * @return the radius of the sphere of influence
	 */
	private double rSOIcalc() {
		return this.semiMajorAxis * (this.mass / Masses.getmSun());
	}

	/**
	 * this method gives the values to update the position of the CelestialBody
	 * @param dt the timestep of the update
	 * @return the new coordinates for the CelestialBody
	 */
	public double[] update(double dt) {
		double dx = calculateX(dt);
		double dy = calculateY(dt);
		double dz = calculateZ(dt);
		this.x += dx;
		this.y += dy;
		this.z += dz;
		return new double[] { this.x, this.y, this.z };
	}

	/** 
	 * Calculate the new X position
	 * @param dt the timestep for the update
	 * @return the new X position
	 */
	private double calculateX(double dt) {
		calculateVx(dt);
		return (this.vx * dt);
	}

	/** 
	 * Calculate the new Y position
	 * @param dt the timestep for the update
	 * @return the new Y position
	 */
	private double calculateY(double dt) {
		calculateVy(dt);
		return (this.vy * dt);
	}

	/** 
	 * Calculate the new Z position
	 * @param dt the timestep for the update
	 * @return the new Z position
	 */
	private double calculateZ(double dt) {
		calculateVz(dt);
		return (this.vz * dt);
	}

	/** 
	 * Calculate the new X velocity
	 * @param dt the timestep for the update
	 * @return the new X velocity
	 */
	private void calculateVx(double dt) {
		double accX = calculateAx();
		// System.out.println(/*"X acc = " + */accX);
		this.vx += (accX * dt);
	}

	/** 
	 * Calculate the new Y velocity
	 * @param dt the timestep for the update
	 * @return the new Y velocity
	 */
	private void calculateVy(double dt) {
		double accY = calculateAy();
		// System.out.print(" Y acc = " + accY + "\n");
		this.vy += (accY * dt);
	}

	/** 
	 * Calculate the new Z velocity
	 * @param dt the timestep for the update
	 * @return the new Z velocity
	 */
	private void calculateVz(double dt) {
		double accZ = calculateAz();
		this.vz += (accZ * dt);
	}

	/** 
	 * Calculate the X acceleration using Newton's law of universal gravitation
	 * @return the X acceleration
	 */
	private double calculateAx() {
		// return bigG * ((Masses.getmSun())/Math.pow(this.x, 2));
		// System.out.println((bigG *
		// Masses.getmSun()*(-this.x))/(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y,
		// 2),(3/2))));
		// return (bigG * this.parent.getMass()*(this.parent.getX()-this.x)) /
		// Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) +
		// ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun() * (-this.x))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);
	}

	/** 
	 * Calculate the Y acceleration using Newton's law of universal gravitation
	 * @return the Y acceleration
	 */
	private double calculateAy() {
		// return bigG * ((Masses.getmSun())/Math.pow(this.y, 2));
		// System.out.println(Math.pow((this.x*this.x + this.y*this.y),1.5));
		// return (bigG * this.parent.getMass()*(this.parent.getY()-this.y)) /
		// Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) +
		// ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun() * (-this.y))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);
	}

	/** 
	 * Calculate the Z acceleration using Newton's law of universal gravitation
	 * @return the Z acceleration
	 */
	private double calculateAz() {
		return (bigG * Masses.getmSun() * (-this.z))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);
	}

	/**
	 * Calculate the new postion of the CelestialBody using the 3/8th rule version of Runge-Kutta 4th 
	 * order method
	 * @param bodies a list of bodies of which the this CelestialBody should be compared to
	 * @param dt the timestep of the calculation
	 * @return the new postion of the CelestialBody
	 */
	public double[] update(ArrayList<CelestialBody> bodies, double dt) {
		double[] first = new double[3];
		double[] second = new double[3];
		double[] third = new double[3];
		double[] fourth = new double[3];
		double[] acceleration = new double[3];

		for (CelestialBody planet : bodies) {
			if (planet != this) {
				double xDist = (this.x - planet.getX());
				double yDist = (this.y - planet.getY());
				double zDist = (this.z - planet.getZ());
				double distance = xDist * xDist + yDist * yDist + zDist * zDist;
				double dist = Math.sqrt(distance);
				double temp = bigG * planet.getMass() / (dist * dist * dist);

				// Euler acceleration -- ~First Order
				first[0] = temp * (planet.getX() - this.x);
				first[1] = temp * (planet.getY() - this.y);
				first[2] = temp * (planet.getZ() - this.z);
				
				// ~Second Order after a half timestep
				double[] temp_vel = step(this.vx, this.vy, this.vz, first, 1/2 * dt);
				double[] temp_position = step(this.x, this.y, this.z, temp_vel, 1/2 * dt);
				second[0] = (planet.getX() - temp_position[0]) * temp;
				second[1] = (planet.getY() - temp_position[1]) * temp;
				second[2] = (planet.getZ() - temp_position[2]) * temp;

				// ~Third Order after a half timestep
				temp_vel = step(this.vx, this.vy, this.vz, first, second, 1/2 * dt, dt);
				temp_position = step(this.x, this.y, this.z, temp_vel, 1/2 * dt);
				third[0] = (planet.getX() - temp_position[0]) * temp;
				third[1] = (planet.getY() - temp_position[1]) * temp;
				third[2] = (planet.getZ() - temp_position[2]) * temp;

				// ~Fourth Order after 1 timestep in the future using
				temp_vel = step(this.vx, this.vy, this.vz, thirdOrder(first, second, third), dt);
				temp_position = step(this.x, this.y, this.z, temp_vel, dt);
				fourth[0] = (planet.getX() - temp_position[0]) * temp;
				fourth[1] = (planet.getY() - temp_position[1]) * temp;
				fourth[2] = (planet.getZ() - temp_position[2]) * temp;
				
				acceleration[0] += (first[0] + 2 * second[0] + 2 * third[0] + fourth[0]) / 6;
				acceleration[1] += (first[1] + 2 * second[1] + 2 * third[1] + fourth[1]) / 6;
				acceleration[2] += (first[2] + 2 * second[2] + 2 * third[2] + fourth[2]) / 6;
}
		}

		double[] velocity = step(this.vx, this.vy, this.vz, acceleration, dt);
		double[] position = step(this.x, this.y, this.z, velocity, dt);

		this.vx = velocity[0];
		this.vy = velocity[1];
		this.vz = velocity[2];
		/*
		 * this.x = position[0]; this.y = position[1]; this.z = position[2];
		 */
		return position;
	}

	/**
	 * A helper method to help calculating the 4th step in RK4 3/8th rule
	 * @param a1 results of the first calculation
	 * @param a2 results of the second calculation
	 * @param a3 results of the third calculation
	 * @return the necessary value for the formula f
	 */
	protected double[] thirdOrder(double[] a1, double[] a2, double[] a3){
		double[] augmented = new double[3];
		augmented[0] = a1[0] - a2[0] + a3[0];
		augmented[1] = a1[1] - a2[1] + a3[1];
		augmented[2] = a1[2] - a2[2] + a3[2];

		return augmented;
	}
	/**
	 * a helper method to update the values using a simple step
	 * @param p1 first value to update
	 * @param p2 second value to update
	 * @param p3 third value to update
	 * @param k the k value to update with
	 * @param dt the timestep to update @param p1, @param p2, and @param p3 over using @param k
	 * @return the updated values using the simple step
	 */
	protected double[] step(double p1, double p2, double p3, double[] k, double dt) {
		double[] updated = new double[3];

		updated[0] = p1 + k[0] * dt;
		updated[1] = p2 + k[1] * dt;
		updated[2] = p3 + k[2] * dt;

		return updated;
	}

	/**
	 * a helper method to update the values of the 2nd step using a simple step
	 * @param p1 first value to update
	 * @param p2 second value to update
	 * @param p3 third value to update
	 * @param k1 the k1 value to update with
	 * @param k2 the k2 value to update with
	 * @param dt1 the timestep to apply over @param k1
	 * @param dt2 the timestep to apply over @param k2
	 * @return the updated values using the simple step
	 */
	protected double[] step(double p1, double p2, double p3, double[] k1, double[] k2, double dt1, double dt2){
		double[] updated = new double[3];
		
		updated[0] = p1 - k1[0] * dt1 + k2[0] * dt2;
		updated[1] = p2 - k1[1] * dt1 + k2[1] * dt2;
		updated[2] = p3 - k1[2] * dt1 + k2[2] * dt2;

		return updated;
	}

	/**
	 * update the position of the CelestialBody
	 * @param position the new position vector of the CelestialBody
	 */
	public void updatePosition(double[] position) {
		this.x = position[0];
		this.y = position[1];
		this.z = position[2];
	}

	/**
	 * 
	 * @return the mass of the CelestialBody
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * 
	 * @return the X component of the CelestialBody
	 */
	public double getX() {
		return x;
	}

	/**
	 * set a new value to the X component of the CelestialBody
	 * @param x new X component of the CelestialBody
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * 
	 * @return the Y component of the CelestialBody
	 */
	public double getY() {
		return y;
	}

	/**
	 * set a new value to the Y component of the CelestialBody
	 * @param y new Y component of the CelestialBody
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * 
	 * @return the X velocity of the CelestialBody
	 */
	public double getVx() {
		return vx;
	}

	/**
	 * set a new value to the X velocity of the CelestialBody
	 * @param vx new X velocity of the CelestialBody
	 */
	public void setVx(double vx) {
		this.vx = vx;
	}

	/**
	 * 
	 * @return the Y velocity of the CelestialBody
	 */
	public double getVy() {
		return vy;
	}

	/**
	 * set a new value to the Y velocity of the CelestialBody
	 * @param vy new Y velocity of the CelestialBody
	 */
	public void setVy(double vy) {
		this.vy = vy;
	}

	/**
	 * 
	 * @return the radius of the sphere of influence of the CelestialBody
	 */
	public double getrSOI() {
		return rSOI;
	}

	/**
	 * 
	 * @return the semimajor-axis of the CelestialBody
	 */
	public double getSemiMajorAxis() {
		return this.semiMajorAxis;
	}

	/**
	 * 
	 * @return the diameter of the CelestialBody
	 */
	public double getSize() {
		return size;
	}
	/**
	 * 
	 * @return the Z component of the CelestialBody
	 */
	public double getZ() {
		return z;
	}

	/**
	 * set a new value to the Z component of the CelestialBody
	 * @param z new Z component of the CelestialBody
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * 
	 * @return the Z Velocity of the CelestialBody
	 */
	public double getVz() {
		return vz;
	}

	/**
	 * set a new value to the Z velocity of the CelestialBody
	 * @param vz new Z velocity of the CelestialBody
	 */
	public void setVz(double vz) {
		this.vz = vz;
	}
}
