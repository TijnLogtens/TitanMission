package sample;

public class Controller {

    private double verticalWind;
    private double horizontalWind;
    private ControllerInterface controller;
    private final static double ERROR_MARGIN = 0.1;
    private final static double MAXIMUM_WIND = 120;


    public Controller(boolean loop){
        verticalWind = Math.random()*100-50;
        horizontalWind = Math.random()*20-10;
        if(loop){
            controller = new FeedbackController();
        } else {
            controller = new OpenController();
        }
    }

    private double windInX(double dt){
        updateWind(dt);
        return -1;
    }

    private double windInY(double dt){
        return -1;
    }


    public void updateWind(double dt){
        double resultingWind;
        double something;
        double somethingElse;
        do {
            something = Math.random() * 3 - 1.5;
            somethingElse = Math.random() * 5 - 2.5;
            resultingWind = Math.sqrt((verticalWind + something * dt) * (verticalWind + something * dt) + (horizontalWind + somethingElse * dt) * (horizontalWind + somethingElse * dt));
        } while (resultingWind > MAXIMUM_WIND);
        verticalWind += something * dt;
        horizontalWind += somethingElse * dt;
    }


    public void setVerticalWind(double verticalWind) {
        this.verticalWind = verticalWind;
    }

    public void setHorizontalWind(double horizontalWind) {
        this.horizontalWind = horizontalWind;
    }

    public double getVerticalWind() {
        return verticalWind;
    }

    public double getHorizontalWind() {
        return horizontalWind;
    }
}
