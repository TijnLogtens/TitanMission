package sample;

import java.util.*;
import java.io.*;

public class Controller {

    private double verticalWind;
    private double horizontalWind;
    private double verWind;
    private double horWind;
    private ControllerInterface controller;
    private final static double ERROR_MARGIN = 0.1;
    private final static double MAXIMUM_WIND = 120;
    private final static double EPSILON = 1;
    private static double initialVerticalWind;
    private static double initialHorizontalWind;
    private static double initialHorizontalWindEarth;
    private static double initialVerticalWindEarth;


    public Controller(boolean loop){
        //Titan
        verticalWind = Math.random()*20-10;
        horizontalWind = Math.random()*100-50;
        //Earth
        verWind = 0;
        horWind  = 0;
        //Titan
        initialHorizontalWind = (int) horizontalWind;
        initialVerticalWind = (int) verticalWind;
        //Earth
        initialHorizontalWindEarth = (int) verWind;
        initialVerticalWindEarth = (int) horWind;
        if(loop){
            controller = new FeedbackController();
        } else {
            controller = new OpenController();
        }
    }

    private double windInX(double dt){
        updateTitanWind(dt);
        return -1;
    }

    private double windInY(double dt){
        return -1;
    }


    public void updateTitanWind(double dt){
        double resultingWind;
        double something;
        double somethingElse;
        do {
            something = Math.random() - 0.5;
            somethingElse = Math.random() * 5 - 2.5;
            resultingWind = Math.sqrt((verticalWind + something * dt) * (verticalWind + something * dt) + (horizontalWind + somethingElse * dt) * (horizontalWind + somethingElse * dt));
        } while (resultingWind > MAXIMUM_WIND);
        verticalWind += something * dt;
        horizontalWind += somethingElse * dt;
    }

    public void updateEarthWind(double dt){
        double resultingWind;
        double something;
        double somethingElse;
        do {
            something = Math.random() - 0.5;
            somethingElse = Math.random() * 5 - 2.5;
            resultingWind = Math.sqrt((verticalWind + something * dt) * (verticalWind + something * dt) + (horizontalWind + somethingElse * dt) * (horizontalWind + somethingElse * dt));
        } while (resultingWind > MAXIMUM_WIND);
        verticalWind += something * dt;
        horizontalWind += somethingElse * dt;
    }




    /*

            Format: verticalWind:horizontalWind:1 or 0
            1 refers to successful landing
            0 refers to unsuccessful landing
    */
    public double ReadFromFile() {

        double success = 0;
        double failures = 0;
        double result = 0;

        // pass the path to the file as a parameter
        try {
            //set up data file for reading
            FileReader the_file = new FileReader("landings2.txt");
            //Passing the FileReader object to the BufferedReader constructor
            BufferedReader file = new BufferedReader(the_file);
            //Scanning the file
            Scanner sc = new Scanner(file);
            String line = file.readLine();

            if (line == null || line == "") {
                System.out.println("There are no data in our database to predict the likelihood of landing");
                return -1;
            }

            do {
                //Stores each time the user name and the password in an array
                String[] details = line.split(":");
                if ((Math.abs(horizontalWind - Double.parseDouble(details[0])) < EPSILON) && (Math.abs(verticalWind - Double.parseDouble(details[1])) < EPSILON)) {
                    if (Double.parseDouble(details[2]) == 1) {
                        success++;
                    } else {
                        failures++;
                    }
                }
            } while (((line = file.readLine()) != null) && line != "");

            if (success == 0 && failures == 0) {
                return -1;
            }
            result = success / (success + failures);
        } catch (IOException io) {
            System.out.println("Error trying to open file " + io.getMessage());
        }

        return result;
    }

    public void writeToFile(int result){
        try {

            //set up data file for reading
            FileWriter the_file = new FileWriter("landings2.txt", true);
            //Passing the FileReader object to the BufferedReader constructor
            BufferedWriter file = new BufferedWriter(the_file);
            String str = initialHorizontalWind + ":" + initialVerticalWind + ":" + result;
            file.write(str);
            file.write("\r\n");
            file.close();
        }catch(IOException io) {
            System.out.println("Error trying to open file " + io.getMessage());
        }
    }

    public void controllerCenter(double posX, double posY, double dt){
            controller.controllerCenter(horizontalWind, verticalWind, posX, posY, dt);
    }


    public double thrust(double mass, double height, double elapsedTime){
        return controller.velocity(mass, height, elapsedTime);
    }

    public double PD_ControllerX(double dt, double x_coord, double mass){
        return controller.PD_ControllerX(dt, x_coord, mass);
    }

    public double PD_ControllerY(double dt, double x_coord, double mass){
        return controller.PD_ControllerY(dt, x_coord, mass);
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

    public double getSideThruster(){
        return controller.getSideThruster();
    }
}
