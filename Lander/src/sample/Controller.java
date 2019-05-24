package sample;

import java.util.*;
import java.io.*;

public class Controller {

    private double verticalWind;
    private double horizontalWind;
    private ControllerInterface controller;
    private final static double ERROR_MARGIN = 0.1;
    private final static double MAXIMUM_WIND = 120;
    private final static double EPSILON = 1E-14;

    public Controller(boolean loop){
        verticalWind = Math.random()*20-10;
        horizontalWind = Math.random()*100-50;
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


    /*

            Format: verticalWind:horizontalWind:1 or 0
            1 refers to successful landing
            0 refers to unsuccessful landing
    */
    public double ReadFromFile(){

        int success = 0;
        int failures = 0;
        double result = 0;

        // pass the path to the file as a parameter
        try{
            //set up data file for reading
            FileReader the_file = new FileReader("landings.txt");
            //Passing the FileReader object to the BufferedReader constructor
            BufferedReader file = new BufferedReader(the_file);
            //Scanning the file
            Scanner sc = new Scanner(file);
            String line = file.readLine();

            if(line == null){
                System.out.println("There are no data in our database to predict the likelihood of landing");
                return -1;
            }

            do {
                //Stores each time the user name and the password in an array
                String[] details = line.split(":");
                if((Math.abs(verticalWind - Double.parseDouble(details[0])) < EPSILON) && (Math.abs(horizontalWind - Double.parseDouble(details[1])) < EPSILON)) {
                    if (Double.parseDouble(details[2]) == 1) {
                        success++;
                    } else {
                        failures++;
                    }
                }
            }while(((line = file.readLine()) != null) && line != "" );

            if(success == 0 && failures == 0){
                return -1;
            }
            result = success/ (success + failures);
        }
        catch (IOException io) {
            System.out.println("Error trying to open file " + io.getMessage());
        }

        return result;
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
