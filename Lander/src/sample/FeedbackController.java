package sample;

public class FeedbackController implements ControllerInterface {

    /*
     * totalVelocityX is a combination of your velocityX + windVelocityX
     * totalVelocityY is a combination of your velocityY + windVelocityY
     *
     * Feedback Controller: 
     * 1) receptor 
     * 2) Controller Center -- Determines the next action 
     * 3) The effector -- Changes the coordinates as well
     *
     * We need to return the side thrust as well as the updated X and Y velocities,
     * that will become the current ones.
     *
     */

    private boolean makeMainThrusterStronger = false;

    private double sideThruster = 0;

    // PID Controller variables

    private double kp_x = 8;
    private double kd_x = 0.00012;
    private double kp_y = 0.000000842;
    private double ki_y = 0.000000006;
    private double kd_y = 0.0;
    private double kp_xe = 8;
    private double kd_xe = 0.000123;
    private double kp_ye = 0.0000141;
    private double ki_ye = 0.0000001626;
    private double kd_ye = 0.0;
    private double last_error_x;
    private double last_error_y;
    private double last_error_xe;
    private double last_error_ye;
    private double errorSum;
    private double errorSumEarth;
    private final double set_point = 0;
    private final double ENGINE_POWER = 7257000; //Newton
    private double sum_thrust_Titan = 0;
    private double sum_thrust_Earth = 0;

    @Override
    public void controllerCenter(double windVelocityX, double windVelocityY, double posX, double posY, double dt) {

        double addedVelocity;

        // Calculate resulting position
        double resultingposX = posX + windVelocityX * dt;
        if (Math.abs(resultingposX) < 1) {
            sideThruster = 0;
            return;
        }
        double resultingposY = posY + windVelocityY * dt;
        if (resultingposX >= 0) {

            double updatedTheta = Math.atan(resultingposY / resultingposX) * Math.PI / 180;
            sideThruster = -updatedTheta + 0.0006;
        } else {
            double updatedTheta = Math.atan(resultingposY / -resultingposX) * Math.PI / 180;
            sideThruster = updatedTheta - 0.0006;
        }

    }

    @Override
    public double velocity(double mass, double height, double elapsedTime) {

        if (height <= 10000) {
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5.5765;
        } else if (height <= 72997653.14 / 8) {
            // Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5.2;
        } else if (height <= 72997653.14 / 4) {
            // Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 5;
        } else if (height <= 72997653.14 / 2) {
            // Formula of kinetic energy rewritten
            return Math.sqrt((2 * 46.2e9 * 0.12 / mass)) * 2.4;
        }
        return 0;
    }

    @Override
    public double PD_ControllerXTitan(double dt, double x_coord, double mass) {

        // Find all the working error variables
        double error = x_coord - set_point;
        double change_in_error = error - last_error_x;

        // This is measured in terms of acceleration
        double output = kp_x * error + kd_x * change_in_error;

        last_error_x = error;

        return Math.abs(output);
    }

    @Override
    public double PD_ControllerYTitan(double dt, double y_coord, double mass) {

        // Find all the working error variables
        double error = y_coord - set_point;
        errorSum += y_coord;
        double change_in_error = error - last_error_y;

        // This is measured in terms of acceleration
        double output = kp_y * error + kd_y * change_in_error + ki_y * errorSum;

        last_error_y = error;

        //Sum the thrust
        sum_thrust_Titan += output * mass;

        return output;
    }
    @Override
    public double PD_ControllerXEarth(double dt, double x_coord, double mass) {


        // Find all the working error variables
        double error = x_coord - set_point;
        double change_in_error = error - last_error_xe;

        // This is measured in terms of acceleration
        double output = kp_xe * error + kd_xe * change_in_error;

        last_error_xe = error;

        return Math.abs(output);
    }

    @Override
    public double PD_ControllerYEarth(double dt, double y_coord, double mass, double kerosene) {
        if(kerosene==0) return 0;
        // Find all the working error variables
        double error = y_coord - set_point;
        errorSum += y_coord;
        double change_in_error = error - last_error_ye;

        // This is measured in terms of acceleration
        double output = kp_ye * error + kd_ye * change_in_error + ki_ye * errorSum;

        last_error_ye = error;

        //Sum the thrust
        sum_thrust_Earth += output * mass;

        double a = dt * ENGINE_POWER * kerosene / (mass + kerosene);
        double v = a*dt;
        if(output > v){
            return v;
        } else {
            return output;
        }
    }

    public boolean getmakeMainThrusterStronger() {
        return makeMainThrusterStronger;
    }

    @Override
    public double getSideThruster() {
        return sideThruster;
    }

    public void setSideThruster(double sideThruster) {
        this.sideThruster = sideThruster;
    }

    public double getSum_thrust_Titan(){
        return sum_thrust_Titan;
    }

    public double getSum_thrust_Earth(){
        return sum_thrust_Earth;
    }

}
