package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LandingScreen extends Application {

    private final double SCALAR_SIZE = 5E4;
    private final double DISTANCE_SIZE = 9E7;
    private final double MOON_SCALAR = 7E1;
    private final double TITAN_SCALAR = MOON_SCALAR * 1.75;
    double anchorX;
    double anchorY;
    double anchorAngleX = 0;
    double anchorAngleY = 0;
    final DoubleProperty angleX = new SimpleDoubleProperty(0);
    final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private static final double GEOSTATIONARY_HEIGHT = 72997653.14;


    public static void main(String[] args) {
        launch(args);
    }

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
            group.translateZProperty().set(group.getTranslateZ() + delta * 5);
        });
    }

    @Override
    public void start(Stage stage) {

        int WIDTH=1000;
        int HEIGHT=1000;
        double SVIgetX=3.537424927743304E+11;
        double SVIgetY=-1.462539028125231E+12;
        double SVIgetZ=1.169787519537956E+10;
        double satgetX=3.547593532400821E+11;
        double satgetY=-1.461948830848272E+12;
        double satgetZ=1.129255310798091E+10;
        double satgetSize = 116464000;


        //create Titan Moon
        Sphere saturn = new Sphere();
        saturn.setRadius(satgetSize / (2 * SCALAR_SIZE));
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();

        Sphere tMoon = new Sphere();
        PhongMaterial m = new PhongMaterial();
        tMoon.setRadius(200);
        tMoon.setTranslateX(500);
        tMoon.setTranslateY(500);
        // tMoon.setTranslateZ((SVIgetZ - satgetZ) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateZ());
        m.setDiffuseColor(Color.GRAY);
        m.setSpecularColor(Color.GRAY);
        tMoon.setMaterial(m);

        // Create a Rocket
        //Box rocket = new Box(10, 5, 3);

        //rocket.setTranslateZ((SVIgetZ - satgetZ) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateZ());
        //   PhongMaterial m1 = new PhongMaterial();
        //  m1.setDiffuseColor(Color.GRAY);
        //  m1.setSpecularColor(Color.GRAY);
        // rocket.setMaterial(m1);

        final Image rocket = new Image(getClass().getResourceAsStream("rocket.jpg"));
        final ImageView imageView = new ImageView();
        imageView.setImage(rocket);
        rocket.setTranslateX(500);
        rocket.setTranslateY(0);

        Group group = new Group();
        group.getChildren().addAll(tMoon,rocket);
        Pane panel = new Pane();
        panel.getChildren().add(group);
        panel.getChildren().addAll(hbox2);

  /*  //set background
    Image image = new Image(getClass().getResourceAsStream("space.jpg"));
    BackgroundImage bgImg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
    Background b = new Background(bgImg);
    panel.setBackground(b);*/

        Scene main = new Scene(hbox1, WIDTH, HEIGHT);
        Scene landingScene = new Scene(panel,WIDTH, HEIGHT);

        Button goToLandingScene= new Button("View Landing on Titan ");
        goToLandingScene.setOnAction(e -> stage.setScene(landingScene));

        Button goToMain= new Button("View Solar System ");
        goToMain.setOnAction(e -> stage.setScene(main));

        //Button exit= new Button("Exit");

        hbox1.getChildren().addAll(goToLandingScene);
        hbox2.getChildren().addAll(goToMain);

    /*Camera c = new PerspectiveCamera();
    landingScene.setCamera(c);*/

        initialMouseControl(group, landingScene, stage);

        // MOVE PLANETS
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        Lander lander = new Lander(4771, 0,GEOSTATIONARY_HEIGHT, 0,0);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if( lander.getPosY() > 0) {
                    lander.setPosY(lander.update(1000));
                    rocket.setTranslateY(-(lander.getPosY()/1E5) + HEIGHT/2);
                    System.out.println((lander.getPosY()/1E6));
                }

            }
        };

        // create a keyFrame, the keyValue is reached at time 1s
        Duration duration = Duration.millis(1);
        // one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<>() {
            public void handle(ActionEvent t) {}};

        KeyFrame keyFrame = new KeyFrame(duration, onFinished); // keyValueX, keyValueY

        // add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();

        stage.setScene(landingScene);
        stage.setTitle("Solar System");
        stage.show();

    }
}
