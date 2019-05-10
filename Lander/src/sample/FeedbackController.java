package sample;

public class FeedbackController implements ControllerInterface {

    private double initialVxWind;
    private double initialVyWind;

    private final static double ERROR_MARGIN = 0.1;

    /*   controller
     *   actuator
     *   plant
     */

    /*
     *   we need thrust as a function of error
     *   we have 3 thrusters, 2 small (angle), one main (lift)
     */



    public double directionalThrust(){


        return -1;
    }

    public double liftingThrust(double error){
        if(error <= ERROR_MARGIN){
            return 0;
       /* } else if(vx > 0) {
            return -1 * error;*/
        } else {
            return error;
        }

    }
}
