package sample;

public interface ControllerInterface {

    double velocity(double mass, double posY, double elapsedTime);
    void controllerCenter(double horizontalWind, double verticalWind, double posX, double posY, double dt);
    double getSideThruster();

}
