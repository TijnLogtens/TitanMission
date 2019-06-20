
//package solarSystemModel;

import java.util.*;

public class CelestialBody {

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
	private int index = 0;
	private Star parent;

	public double[] trail_x = new double[250];
	public double[] trail_y = new double[250];
	public double[] trail_z = new double[250];

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

	private double rSOIcalc() {
		return this.semiMajorAxis * (this.mass / Masses.getmSun());
	}

	public double[] update(double dt) {
		double dx = calculateX(dt);
		double dy = calculateY(dt);
		double dz = calculateZ(dt);
		this.x += dx;
		this.y += dy;
		this.z += dz;
		return new double[] { this.x, this.y, this.z };
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

	private double calculateAy() {
		// return bigG * ((Masses.getmSun())/Math.pow(this.y, 2));
		// System.out.println(Math.pow((this.x*this.x + this.y*this.y),1.5));
		// return (bigG * this.parent.getMass()*(this.parent.getY()-this.y)) /
		// Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) +
		// ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun() * (-this.y))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);
	}

	private double calculateAz() {
		return (bigG * Masses.getmSun() * (-this.z))
				/ Math.pow((this.x * this.x + this.y * this.y + this.z * this.z), 1.5);
	}

	public double[] update(ArrayList<CelestialBody> bodies, double dt) {
		double[] first = new double[3];
		double[] second = new double[3];
		double[] third = new double[3];
		double[] fourth = new double[3];
		double[] fifth = new double[3];
		double[] sixth = new double[3];
		double[] acceleration = new double[3];


		//http://maths.cnam.fr/IMG/pdf/RungeKuttaFehlbergProof.pdf
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

	protected double[] thirdOrder(double[] a1, double[] a2, double[] a3){
		double[] augmented = new double[3];
		augmented[0] = a1[0] - a2[0] + a3[0];
		augmented[1] = a1[1] - a2[1] + a3[1];
		augmented[2] = a1[2] - a2[2] + a3[2];

		return augmented;
	}
	protected double[] step(double p1, double p2, double p3, double[] k, double dt) {
		double[] updated = new double[3];

		updated[0] = p1 + k[0] * dt;
		updated[1] = p2 + k[1] * dt;
		updated[2] = p3 + k[2] * dt;

		return updated;
	}

	protected double[] step(double p1, double p2, double p3, double[] k1, double[] k2, double dt1, double dt2){
		double[] updated = new double[3];
		
		updated[0] = p1 - k1[0] * dt1 + k2[0] * dt2;
		updated[1] = p2 - k1[1] * dt1 + k2[1] * dt2;
		updated[2] = p3 - k1[2] * dt1 + k2[2] * dt2;

		return updated;
	}

	public void updatePosition(double[] position) {
		this.x = position[0];
		this.y = position[1];
		this.z = position[2];
	}

	public double getMass() {
		return mass;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getrSOI() {
		return rSOI;
	}

	public void setrSOI(double rSOI) {
		this.rSOI = rSOI;
	}

	public double getSemiMajorAxis() {
		return this.semiMajorAxis;
	}

	public double getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getSize() {
		return size;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getVz() {
		return vz;
	}

	public void setVz(double vz) {
		this.vz = vz;
	}

	public double getBigG() {
		return bigG;
	}

	public Star getParent() {
		return parent;
	}
}
