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

    public void controllerCenter(double windVelocityX, double windVelocityY, double posX, double posY, double dt){

        double addedVelocity;

        //Calculate resulting position
        double resultingposX = posX + windVelocityX * dt;
        if(Math.abs(resultingposX) < 1){
            sideThruster = 0;
            return;
        }
        double resultingposY = posY + windVelocityY * dt;
        if(resultingposX >= 0){

            double updatedTheta = Math.atan(resultingposY/resultingposX) * Math.PI /180;
            sideThruster = -updatedTheta + 0.0006;
        } else{
            //System.out.println("We here");

            double updatedTheta = Math.atan(resultingposY/-resultingposX) * Math.PI /180;

            sideThruster = updatedTheta - 0.0006;
        }

    }

    public double velocity(double mass, double height, double elapsedTime){

        if(height <= 10000){
        return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5.5765;
        } else if(height <= 72997653.14/8) {
            //Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5.2 ;
        } else if(height <= 72997653.14/4) {
            //Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5;
        } else if(height <= 72997653.14/2) {
            //Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 2.4;
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
