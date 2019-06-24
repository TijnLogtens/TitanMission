package sample;

public abstract class Lander {
    
    private static double g;
    private static double b;
    private Controller controller;


    abstract double[] update(double dt);
    abstract double getPosX();
    abstract double getPosY();
    abstract void setPosX(double posX);
    abstract void setPosY(double posY);
    abstract double getSideThruster();
}