package solarSystemModel;

import java.awt.Graphics;

public class CelestialBody {
	protected double mass;
	protected double x;
	protected double y;
	protected double vx;
	protected double vy;
	protected double rSOI;
	protected double semiMajorAxis;
	protected double size;
	protected double bigG = 6.674E-11;

	private Star parent;


	CelestialBody(double mass, double x, double y, double vx, double vy, double semiMajorAxis, double size){
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.semiMajorAxis = semiMajorAxis;
		this.rSOI = rSOIcalc();
		this.size = size;
	}

	CelestialBody(double mass, double x, double y, double vx, double vy, double semiMajorAxis, double size, Star parent){
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.semiMajorAxis = semiMajorAxis;
		this.rSOI = rSOIcalc();
		this.size = size;
		this.parent = parent;
	}

	private double rSOIcalc() {
		return this.semiMajorAxis * (this.mass/Masses.getmSun());
	}
	
	public void update(double dt) {
		double dx = calculateX(dt);
		double dy = calculateY(dt);
		this.x += dx;
		this.y += dy;
	}

	private double calculateX(double dt) {
		calculateVx(dt);
		return (this.vx*dt);
	}

	private double calculateY(double dt) {
		calculateVy(dt);
		return (this.vy*dt);
	}

	private void calculateVx(double dt) {
		double accX = calculateAx();
		//System.out.println(/*"X acc = " + */accX);
		this.vx += (accX*dt);
	}

	private void calculateVy(double dt) {
		double accY = calculateAy();
		//System.out.print("	Y acc = " + accY + "\n");
		this.vy += (accY*dt);
	}

	private double calculateAx() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.x, 2));
		//System.out.println((bigG * Masses.getmSun()*(-this.x))/(Math.pow(Math.pow(this.x, 2) + Math.pow(this.y, 2),(3/2))));
		//return (bigG * this.parent.getMass()*(this.parent.getX()-this.x)) / Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) + ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun()*(-this.x)) / Math.pow((this.x*this.x + this.y*this.y),1.5);
	}

	private double calculateAy() {
		//return bigG * ((Masses.getmSun())/Math.pow(this.y, 2));
		//System.out.println(Math.pow((this.x*this.x + this.y*this.y),1.5));
		//return (bigG * this.parent.getMass()*(this.parent.getY()-this.y)) / Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) + ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);

		return (bigG * Masses.getmSun()*(-this.y)) / Math.pow((this.x*this.x + this.y*this.y),1.5);
	}

	public void drawPlanet(Graphics g){

		//System.out.println("X pos = " + x + "	Y pos = " + y);

		g.fillOval((int) (x /1E9) + 500-(int)((size/1E6)/2), (int) (y/1E9) + 500-(int)((size/1E6)/2), (int) (size/1E6), (int) (size/1E6));
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
}

