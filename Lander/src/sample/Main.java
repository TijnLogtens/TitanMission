package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static final double GEOSTATIONARY_HEIGHT = 72997653.14;
    private double y;
    //private static final double THRUST =

    @Override
    public void start(Stage primaryStage) throws Exception {
        Lander lander = new Lander(4771, 0, GEOSTATIONARY_HEIGHT, 0, 0);
        int i = 0;
        do {

            if(lander.getPosY() > 0) {
                double newPosition = lander.getPosY();
                newPosition = lander.update(1);
                lander.setPosY(newPosition);
                System.out.println(i + " " + newPosition);
                i++;
            }
            else{
                System.exit(0);
            }
        }
        while (y >= 0);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
    }
}