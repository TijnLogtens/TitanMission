package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double GEOSTATIONARY_HEIGHT = ;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Lander lander = new Lander(4771, 0,100000, 0,0);
        while(lander.update(1) >= 0){
            System.out.println(lander.update(1));
        }



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) { launch(args);}
}
