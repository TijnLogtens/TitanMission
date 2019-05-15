package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

    @Override
    public void start(Stage stage) throws Exception{

        int WIDTH=1000;
        int HEIGHT=1000;

        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();

        Sphere tMoon = new Sphere();
        PhongMaterial m = new PhongMaterial();
        tMoon.setRadius(50);
        tMoon.setTranslateX(500);
        tMoon.setTranslateY(500);
        //tMoon.setTranslateZ();
        m.setDiffuseColor(Color.GRAY);
        m.setSpecularColor(Color.GRAY);
        tMoon.setMaterial(m);

        // Create a Rocket
        Box rocket = new Box(100, 50, 30);
        rocket.setTranslateX(500);
        rocket.setTranslateY(0);
        //rocket.setTranslateZ(400);

        Group group = new Group();
        group.getChildren().addAll(tMoon,rocket);
        Pane panel = new Pane();
        panel.getChildren().add(group);
        panel.getChildren().addAll(hbox2);

        Scene main = new Scene(hbox1, WIDTH, HEIGHT);
        Scene landingScene = new Scene(panel,WIDTH, HEIGHT);

        Button goToLandingScene= new Button("View Landing on Titan ");
        goToLandingScene.setOnAction(e -> stage.setScene(landingScene));

        Button goToMain= new Button("View Solar System ");
        goToMain.setOnAction(e -> stage.setScene(main));

        Button exit= new Button("Exit");

        hbox1.getChildren().addAll(goToLandingScene);
        hbox2.getChildren().addAll(goToMain);

        Camera c = new PerspectiveCamera();
        landingScene.setCamera(c);

        // MOVE PLANETS
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        Lander lander = new Lander(4771, 0,GEOSTATIONARY_HEIGHT, 0,0);
        int[] i = new int[]{0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if( lander.getPosY() > 0) {
                    double newPosition[] = new double[2];
                    for (int i = 0; i < 10; i++) {
                        newPosition = lander.update(1);
                        lander.setPosX(newPosition[0]);
                        lander.setPosY(newPosition[1]);
                    }
                    //System.out.println(i[0]+" Y: "+newPosition[1]);
                    System.out.println(i[0]+" X: "+newPosition[0]);
                    //System.out.println(lander.getSideThruster());
                    rocket.setTranslateX(-(lander.getPosX()/1E5) + WIDTH/2);
                    rocket.setTranslateY(-(lander.getPosY()/1E5) + HEIGHT/2);
                    i[0]++;
                }

            }
        };

        // create a keyFrame, the keyValue is reached at time 1s
        Duration duration = Duration.millis(1);
        // one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler() {
            @Override
            public void handle(Event event) {

            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished); // keyValueX, keyValueY

        // add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(landingScene);
        stage.show();
    }


    public static void main(String[] args) { launch(args);}
}
