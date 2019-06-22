package sample;

public class OpenController implements ControllerInterface {

    private double sideThruster = 0;

    public double velocity(double mass, double height, double elapsedTime){
        return 0.29082135*elapsedTime;
    }

    public double getSideThruster() {
        return sideThruster;
    }

    public void controllerCenter(double horizontalWind, double verticalWind, double posX, double posY, double dt){
        return;
    }
    public double PD_ControllerX(double dt, double x_coord, double mass){
        return -1;
    }
    public double PD_ControllerY(double dt, double x_coord, double mass){
        return -1;
    }

}
