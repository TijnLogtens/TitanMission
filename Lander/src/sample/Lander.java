package sample;

import java.util.*;

public class Lander {

    private double mass;
    private double posX;
    private double posY;
    private double speedX;
    private double speedY;
    private double verticalWind;
    private double horizontalWind;
    private double horizontalThruster;
    private double verticalThruster;
    private double currentFuel;
    private double elapsedTime;
    private OpenController thrust;
    private Controller controller;
    private double kerosene;

    private final static double g = 1.352; //gravitational constant of Titan
    private final static double b = 0.75; //coefficient of drag of the rocket
    public Lander(double mass, double posX, double posY, double speedX, double speedY){
        this.mass = mass;
        this.posX  = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.elapsedTime = 0;
        this.kerosene = this.mass * 0.98;
        this.thrust = new OpenController();
        this.controller = new Controller(true);
    }

    public double[] update(double dt){
        this.elapsedTime += dt;
        controller.updateWind(dt);
        //Euler's method
        double newPosY = posY;
        double firstvy = CalculateVy(elapsedTime);
        if(posY>0) {
            newPosY += dt * firstvy + Math.sqrt(controller.getVerticalWind())/* + (thrust.velocity(kerosene) * dt)*/;
        } else {
            newPosY -= dt * firstvy + Math.sqrt(controller.getVerticalWind())/* + (thrust.velocity(kerosene)* dt)*/;
        }
/*        double dKerosene = kerosene - ((mass * g * dt)/2.749);
        mass -= dKerosene;
        kerosene -= dKerosene;
*/
        double newPosX = posX + Math.sqrt(controller.getHorizontalWind());
        return new double[]{newPosX, newPosY};
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

    public void setVerticalWind(double verticalWind) {
        this.verticalWind = verticalWind;
    }

    public void setHorizontalWind(double horizontalWind) {
        this.horizontalWind = horizontalWind;
    }

    public double getHorizontalThruster() {
        return horizontalThruster;
    }

    public void setHorizontalThruster(double horizontalThruster) {
        this.horizontalThruster = horizontalThruster;
    }

    public double getVerticalThruster() {
        return verticalThruster;
    }

    public void setVerticalThruster(double verticalThruster) {
        this.verticalThruster = verticalThruster;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
