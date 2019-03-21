//package solarSystemModel;

import java.awt.Graphics;

public class Satellite extends CelestialBody {

    CelestialBody parent;

    Satellite(double mass, double x, double y, double vx, double vy, double semiMajorAxis, double size, CelestialBody parent){
        super(mass, x, y, vx, vy, semiMajorAxis, size);
        this.parent = parent;
    }

    public double[] update(double dt){
        double dx = calculateX(dt);
        double dy = calculateY(dt);
        this.x += dx;
        this.y += dy;
        return new double[]{this.x,this.y};
        //System.out.println("X pos = " + x + "   Y pos = " + y);
    }

    private double calculateX(double dt){
        calculateVx(dt);
        return (this.vx*dt);
    }

    private double calculateY(double dt){
        calculateVy(dt);
        return (this.vy*dt);
    }

    private void calculateVx(double dt){
        double accX = calculateAx();
        //System.out.println(/*"X acc = " + */accX);
        this.vx += (accX*dt);
    }

    private void calculateVy(double dt){
        double accY = calculateAy();
        //System.out.print("	Y acc = " + accY + "\n");
        this.vy += (accY*dt);
    }

    private double calculateAx(){
        return (bigG * parent.getMass()*(-this.x)) / Math.pow((this.x*this.x + this.y*this.y),1.5);

        //return (bigG * this.parent.getMass()*(this.parent.getX()-this.x)) / Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) + ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);
    }

    private double calculateAy(){
        return (bigG * parent.getMass()*(-this.y)) / Math.pow((this.x*this.x + this.y*this.y),1.5);

        //return (bigG * this.parent.getMass()*(this.parent.getY()-this.y)) / Math.pow(((this.x-this.parent.getX())*(this.x-this.parent.getX())) + ((this.y-this.parent.getY())*(this.y-this.parent.getY())),1.5);
    }

    public void drawPlanet(Graphics g){

        //System.out.println("X pos = " + x + "	Y pos = " + y);

        g.fillOval((int) ((x+parent.getX()) /DISTANCE_SCALER) + 500-(int)((size/SIZE_SCALER)/2), (int) ((y+parent.getY())/DISTANCE_SCALER) + 500-(int)((size/SIZE_SCALER)/2), (int) (size/SIZE_SCALER), (int) (size/SIZE_SCALER));
    }

    public CelestialBody getParent() {
        return parent;
    }
    public void setParent(CelestialBody parent) {
        this.parent = parent;
    }
}