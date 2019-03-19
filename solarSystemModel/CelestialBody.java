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
	public double bigG = 0.000000000066740831;
	
	
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
		this.x += (this.vx*dt*1000);
	}

	private void calculateY(double dt) {
		calculateVy(dt);
		this.y += (this.vy*dt*1000);
	}

	private void calculateVx(double dt) {
		double accX = calculateAx();
		this.vx += (accX*dt*1000);
	}

	private void calculateVy(double dt) {
		double accY = calculateAy();
		this.vy += (accY*dt*1000);
	}

	private double calculateAx() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.x, 2));
		return bigG * ((Masses.getmSun()*(-this.x))/(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2),(3/2))));
	}

	private double calculateAy() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.y, 2));
		return bigG * ((Masses.getmSun()*(-this.y))/(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2),(3/2))));
	}

	public void drawPlanet(Graphics g){

		System.out.println("X pos = " + x + "	Y pos = " + y);

		g.fillOval((int) (x /3E10) + 500-(int)((size/1E6)/2), (int) (y/3E10) + 500-(int)((size/1E6)/2), (int) (size/1E6), (int) (size/1E6));
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

