package solarSystemModel;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

public class CelestialBody {

	protected final int DISTANCE_SCALER = (int)5E9;
	protected final int SIZE_SCALER = (int)5E5;

	protected double mass;
	protected double x;
	protected double y;
	protected double vx;
	protected double vy;
	protected double rSOI;
	protected double semiMajorAxis;
	protected double size;
	protected double bigG = 6.674E-11;
	private int index = 0;
	private Star parent;

	public double[] trail_x = new double[250];
	public double[] trail_y = new double[250];

	CelestialBody(double mass, double x, double y, double vx, double vy, double semiMajorAxis, double size){
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.semiMajorAxis = semiMajorAxis;
		this.rSOI = rSOIcalc();
		this.size = size;
		for(int i = 0; i < trail_x.length; i++){
			trail_x[i] = -1;
			trail_y[i] = -1;
		}
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
		for(int i = 0; i < trail_x.length; i++){
			trail_x[i] = -1;
			trail_y[i] = -1;
		}
	}

	private double rSOIcalc() {
		return this.semiMajorAxis * (this.mass/Masses.getmSun());
	}

	public double[] update(double dt) {
		double dx = calculateX(dt);
		double dy = calculateY(dt);
		this.x += dx;
		this.y += dy;
		return new double[]{this.x,this.y};
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

		int norm_x = (int) (x/DISTANCE_SCALER) + 500;
		int norm_y = (int) (y/DISTANCE_SCALER) + 500;

		g.fillOval(norm_x-(int)((size/SIZE_SCALER)/2), norm_y-(int)((size/SIZE_SCALER)/2), (int) (size/SIZE_SCALER), (int) (size/SIZE_SCALER));

		trail_x[index] = norm_x;
		trail_y[index] = norm_y;
		index++;

		for(int i=0;i<trail_x.length;i++){
			g.fillOval((int) trail_x[i], (int) trail_y[i], 2, 2);
		}
		if(index==250){
			index = 0;
		}
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
	public double getIndex(){
		return index;
}
	public void setIndex(int index){
		this.index = index;
	}
}

