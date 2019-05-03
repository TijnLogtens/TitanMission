package sample;

import java.util.*;

public class Lander {

    private double mass;
    private double posX;
    private double posY;
    private double speedX;
    private double speedY;
    private final static double g = 1.352; //gravitational constant of Titan
    private final static double b = 0.75; //coefficient of drag of the rocket
    public Lander(double mass, double posX, double posY, double speedX, double speedY){
        this.mass = mass;
        this.posX  = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public double update(double dt){
        //Euler's method
        double newPosY = posY;
        double firstvy = CalculateVy(dt);
        if(posY>0) {
            newPosY += dt * firstvy;
        } else {
            newPosY -= dt * firstvy;
        }
        return newPosY;
    }

    public double CalculateVy(double dt){
        return -1*(( mass * g) / b - ( mass * g) / b * Math.exp((-b)/ mass * dt));
    }


    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
