package solarSystemModel;

import java.awt.Graphics;

public class CelestialBody {
	public double mass;
	public double x;
	public double y;
	public double vx;
	public double vy;
	public double rSOI;
	public double a;
	public double size;
	public double bigG = 6.674E-11;
	
	
	CelestialBody(double mass, double x, double y, double vx, double vy, double a, double size){
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.a = a;
		this.rSOI = rSOIcalc();
		this.size = size;
	}

	public double rSOIcalc() {
		return this.a * (this.mass/Masses.getmSun());
	}
	
	public void update(double dt) {
		calculateX(dt);
		calculateY(dt);
	}

	private void calculateX(double dt) {
		calculateVx(dt);
		this.x += (this.vx*dt);
	}

	private void calculateY(double dt) {
		calculateVy(dt);
		this.y += (this.vy*dt);
	}

	private void calculateVx(double dt) {
		double accX = calculateAx();
		System.out.print("X acc = " + accX);
		this.vx += (accX*dt);
	}

	private void calculateVy(double dt) {
		double accY = calculateAy();
		System.out.print("	Y acc = " + accY + "\n");
		this.vy += (accY*dt);
	}

	private double calculateAx() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.x, 2));
		//System.out.println((bigG * Masses.getmSun()*(-this.x))/(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2),(3/2))));
		return (bigG * Masses.getmSun()*(-this.x))/Math.pow((this.x*this.x + this.y*this.y),1.5);
	}

	private double calculateAy() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.y, 2));

		return (bigG * Masses.getmSun()*(-this.y))/Math.pow((this.x*this.x + this.y*this.y),1.5);
	}

	public void drawPlanet(Graphics g){

		//System.out.println("X pos = " + x + "	Y pos = " + y);

		g.fillOval((int) (x /1E10) + 500-(int)((size)/2), (int) (y/1E10) + 500-(int)((size)/2), (int) (size), (int) (size));
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
	public double getA() {
		return this.a;
	}
}

