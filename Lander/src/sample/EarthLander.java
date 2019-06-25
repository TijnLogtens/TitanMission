package sample;

import java.util.*;

public class EarthLander extends Lander {

    private double dryMass;
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
    private FeedbackController thrust;
    private Controller controller;
    private double kerosene;

    private final static double g = 9.80665; //gravitational constant of Earth
    private final static double b = 0.75; //coefficient of drag of the rocket

    public EarthLander(double dryMass, double posX, double posY, double speedX, double speedY){
        this.dryMass = dryMass;
        this.posX  = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.elapsedTime = 0;
        this.kerosene = this.dryMass * 0.98;
        //this.thrust = new FeedbackController();
        //loop == true -> feedback loop
        //loop == false -> open loop
        this.controller = new Controller(true);
    }

    public double[] update(double dt){
        this.elapsedTime += dt;
        controller.updateEarthWind(dt);
        controller.controllerCenter(posX, posY, dt);
        double newPosY = posY;
        double firstvy = CalculateVy(elapsedTime);
        double thrustPower = (controller.PD_ControllerYEarth(dt, posY, dryMass, kerosene) * Math.cos(controller.getSideThruster()) * dt);
        if(posY>0) {
            if(controller.getVerticalWind() >= 0) {
                newPosY += dt * firstvy + Math.sqrt(controller.getVerticalWind())*b + thrustPower;
            } else {
                newPosY += dt * firstvy - Math.sqrt(Math.abs(controller.getVerticalWind()))*b + thrustPower;
            }
        }

        /*
        double dKerosene = ((dryMass * g * dt)/22.749);
        if((kerosene - dKerosene) > 0) {
            kerosene = kerosene - dKerosene;
            dryMass -= dKerosene;
        }
        */
        double newPosX;
        if(controller.getHorizontalWind() >= 0) {
            newPosX = posX + (Math.sqrt(controller.getHorizontalWind()) + controller.PD_ControllerXEarth(dt, posX, dryMass) * Math.sin(controller.getSideThruster()) * dt);
        } else {
            newPosX = posX - (Math.sqrt(Math.abs(controller.getHorizontalWind()))) + controller.PD_ControllerXEarth(dt, posX, dryMass) * Math.sin(controller.getSideThruster()) * dt;
        }

        speedX = (newPosX-posX)/dt;
        speedY = (newPosY-posY)/dt;
        return new double[]{newPosX, newPosY};
    }

    public double CalculateVy(double dt){
        return -1*(( dryMass * kerosene * g) / b - ( dryMass * kerosene * g) / b * Math.exp((-b)/ (dryMass*kerosene) * dt));
    }


    public double getDryMass() {
        return dryMass;
    }

    public void setDryMass(double dryMass) {
        this.dryMass = dryMass;
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
    public double getSideThruster(){
        return thrust.getSideThruster();
    }

    public double successRate(){
        return controller.ReadFromFile();
    }
    public void addLanding(int result){
        controller.writeToFile(result);
    }
}
