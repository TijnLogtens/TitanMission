package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double GEOSTATIONARY_HEIGHT = 72997653.14;
    private double y;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Lander lander = new Lander(4771, 0,GEOSTATIONARY_HEIGHT, 0,0);

        do {
            y =  lander.update(1);
            System.out.println(y);
        }
        while( y >= 0);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args);}
}
