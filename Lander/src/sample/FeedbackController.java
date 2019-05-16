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

    public void  ControllerCenter(double windVelocityX, double windVelocityY,  double posX, double posY, double dt){

        double addedVelocity;

        //Calculate resulting velocities
        double resultingposX = posX + windVelocityX * dt;
        if(Math.abs(resultingposX) < 1){
            sideThruster = 0;
            return;
        }
        double resultingposY = posY + windVelocityY * dt;
        if(resultingposX >= 0){
             addedVelocity = Math.sqrt(Math.pow(resultingposY,2) + Math.pow(resultingposX,2));

            double updatedTheta = Math.acos(resultingposY/addedVelocity) * Math.PI /180;
            sideThruster = updatedTheta;
        }
        else{
            //System.out.println("We here");
            addedVelocity = -(Math.sqrt(Math.pow(resultingposY,2) + Math.pow(resultingposX,2)));

            double updatedTheta = Math.acos(resultingposY/addedVelocity) * Math.PI /180;

            sideThruster = -updatedTheta;
        }

    }

    public double velocity(double mass, double height){

        if(height <= 10000){
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5.5555;
        }
        else if(height <= 72997653.14/8) {
            //Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5.2 ;
        }

        else if(height <= 72997653.14/4) {
            //Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5;
        }

        else if(height <= 72997653.14/2) {
            //Formula of kinetic energy rewritten
            if(sideThruster>0){
                System.out.println("WE THRUST RIGHT");
            } else {
                System.out.println("WE THRUST LEFT");
            }

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
