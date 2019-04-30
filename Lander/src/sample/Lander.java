package sample;

import java.util.*;

public class Lander {

    private double Mass;
    private double x_pos;
    private double y_pos;
    private double x_speed;
    private double y_speed;
    private final static double g = 1.352;
    private final static double b = 1;
    public Lander(double Mass, double x_pos, double y_pos, double x_speed, double y_speed){
        this.Mass = Mass;
        this.x_pos  = x_pos;
        this.y_pos = y_pos;
        this.x_speed = x_speed;
    }

    public double update(double dt){
        //Euler's method
        double firstvy = CalculateVy(dt);
        y_pos += dt*firstvy;
        return y_pos;
    }

    public double CalculateVy(double dt){
        return -1*(( Mass * g) / b - ( Mass * g) / b * Math.exp((-b)/ Mass * dt));
    }


    public double getMass() {
        return Mass;
    }

    public void setMass(double mass) {
        Mass = mass;
    }

    public double getX_pos() {
        return x_pos;
    }

    public void setX_pos(double x_pos) {
        this.x_pos = x_pos;
    }

    public double getY_pos() {
        return y_pos;
    }

    public void setY_pos(double y_pos) {
        this.y_pos = y_pos;
    }

    public double getX_speed() {
        return x_speed;
    }

    public void setX_speed(double x_speed) {
        this.x_speed = x_speed;
    }

    public double getY_speed() {
        return y_speed;
    }

    public void setY_speed(double y_speed) {
        this.y_speed = y_speed;
    }
}
