package sample;

public class OpenController implements ControllerInterface {

    private double sideThruster = 0;

    @Override
    public double velocity(double mass, double height, double elapsedTime){
        return 0.29082135*elapsedTime;
    }

    @Override
    public double getSideThruster() {
        return sideThruster;
    }

    @Override
    public void controllerCenter(double horizontalWind, double verticalWind, double posX, double posY, double dt){
        return;
    }

    @Override
    public double PD_ControllerXTitan(double dt, double x_coord, double mass){
        return -1;
    }

    @Override
    public double PD_ControllerYTitan(double dt, double x_coord, double mass){
        return -1;
    }

    @Override
    public double PD_ControllerXEarth(double dt, double x_coord, double mass){
        return -1;
    }
    
    @Override
    public double PD_ControllerYEarth(double dt, double x_coord, double mass, double kerosene){
        return -1;
    }

}
