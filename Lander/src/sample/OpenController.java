package sample;

public class OpenController implements ControllerInterface {


    public double velocity(double mass){
        //Formula of kinetic energy rewritten
        return Math.sqrt((2*46.2e9 * 0.12/mass));
    }


}
