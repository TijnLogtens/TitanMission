package sample;

import java.util.*;

public class FeedbackController implements ControllerInterface {

    /**
     * totalVelocityX is a combination of your  velocityX + windVelocityX
     * totalVelocityY is a combination of your velocityY + windVelocityY
     *
     * Feedback Controller:
     * 1) receptor
     * 2) Controller Center -- Determines the next action
     * 3) The effector -- Changes the coordinates as well
     *
     * We need to return the side thrust as well as the updated X and Y velocities, that will become the current ones.
     *
     * TO DO: Take into consideration gravity.
     */

    private boolean makeMainThrusterStronger = false;

    private double sideThruster = 0;

    public double ControllerCenter(double windVelocityX, double windVelocityY,  double currentVelocityX, double currentVelocityY){

        //Calculate resulting velocities
        double resultingVelocityX = currentVelocityX - windVelocityX;
        double resultingVelocityY = currentVelocityY - windVelocityY;

        double updatedTheta = Math.atan(resultingVelocityY/resultingVelocityX);

        sideThruster = updatedTheta;
        
        if(resultingVelocityX > currentVelocityX){
            makeMainThrusterStronger = true;
        } else {
            makeMainThrusterStronger = false;
        }


            return 0;
    }

    public boolean getmakeMainThrusterStronger(){
        return makeMainThrusterStronger;
    }

    public double getSideThruster() {
        return sideThruster;
    }

    public void setSideThruster(double sideThruster) {
        this.sideThruster = sideThruster;
    }



}
