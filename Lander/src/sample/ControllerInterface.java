package sample;

public interface ControllerInterface {

    double velocity(double mass, double posY, double elapsedTime);
    void controllerCenter(double horizontalWind, double verticalWind, double posX, double posY, double dt);
    double PD_ControllerXTitan(double dt, double x_coord, double mass);
    double PD_ControllerYTitan(double dt, double y_coord, double mass);    
    double PD_ControllerXEarth(double dt, double x_coord, double mass);
    double PD_ControllerYEarth(double dt, double y_coord, double mass, double kerosene);
    double getSideThruster();

}
