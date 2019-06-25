package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainEarth extends Application {
    private final double SCALAR_SIZE = 5E4;
    int WIDTH=1000;
    int HEIGHT=625;
    double anchorX;
    double anchorY;
    double anchorAngleX = 0;
    double anchorAngleY = 0;
    final DoubleProperty angleX = new SimpleDoubleProperty(0);
    final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private static final double GEOSTATIONARY_HEIGHT = 42164000;


    public void initialMouseControl(Group group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(xRotate = new Rotate(0, Rotate.X_AXIS), yRotate = new Rotate(0, Rotate.Y_AXIS));
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleX + anchorY - event.getSceneY());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = -event.getDeltaY(); // +ve then fwd if -ve then bckwd
            group.translateZProperty().set(group.getTranslateZ() + delta * 2);
        });
    }

    @Override
    public void start(Stage stage) throws Exception{


        double satgetSize = 116464000;


        //create Titan Moon
        Sphere saturn = new Sphere();
        saturn.setRadius(satgetSize / (2 * SCALAR_SIZE));
        Sphere tMoon = new Sphere();
        PhongMaterial m = new PhongMaterial();
        tMoon.setRadius(200);
        tMoon.setTranslateX(500);
        tMoon.setTranslateY(750);
        m.setDiffuseMap(new Image(getClass().getResourceAsStream("earth.jpg")));
        m.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("earth.jpg")));
        m.setSpecularMap(new Image(getClass().getResourceAsStream("earth.jpg")));
        tMoon.setMaterial(m);

        Box rocket = new Box(75, 100, 0);
        PhongMaterial m1 = new PhongMaterial();
        m1.setDiffuseMap(new Image(getClass().getResourceAsStream("rocket.png")));
        m1.setSelfIlluminationMap(new Image(getClass().getResourceAsStream("rocket.png")));
        m1.setSpecularMap(new Image(getClass().getResourceAsStream("rocket.png")));
        rocket.setMaterial(m1);

        rocket.setTranslateX(500);
        rocket.setTranslateY(0);

        Group group = new Group();
        group.getChildren().addAll(tMoon,rocket);
        Pane panel = new Pane();
        panel.getChildren().add(group);

        //set background
        Image image = new Image(getClass().getResourceAsStream("space.jpg"));
        BackgroundImage bgImg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background b = new Background(bgImg);
        panel.setBackground(b);

        Scene landingScene = new Scene(panel,WIDTH, HEIGHT);

        initialMouseControl(group, landingScene, stage);

        // MOVE PLANETS
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        Lander lander = new EarthLander(110000, 0, GEOSTATIONARY_HEIGHT, 0, 0);
        int[] i = new int[]{0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if( lander.getPosY() > 0) {
                    double newPosition[] = new double[2];
                    for (int i = 0; i < 10; i++) {
                        if(lander.getPosY()>0){
                            newPosition = lander.update(1);
                            lander.setPosX(newPosition[0]);
                            lander.setPosY(newPosition[1]);
                        }
                    }
                    //System.out.println(i[0]+" Y: "+newPosition[1]);

                    System.out.println(i[0] + " X= " + newPosition[0] + "    Y= " + newPosition[1]);

                    //System.out.println(lander.getSideThruster());
                    rocket.setTranslateX(-(lander.getPosX()/1E2) + WIDTH/2);
                    rocket.setTranslateY(-(lander.getPosY()/1E5) + 750 - tMoon.getRadius() - (rocket.getHeight()-30)/2);
                    //rocket.setRotate(-lander.getSideThruster()*180/Math.PI);
                    i[0]++;
                } else {
                    System.exit(0);
                }

            }
        };

        // create a keyFrame, the keyValue is reached at time 1s
        Duration duration = Duration.millis(1);
        // one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler() {
            @Override
            public void handle(Event event) {

            }};

        KeyFrame keyFrame = new KeyFrame(duration, onFinished); // keyValueX, keyValueY

        // add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Landing ");
        stage.setScene(landingScene);
        stage.show();
    }

    public static void main(String[] args) { launch(args);}
}
