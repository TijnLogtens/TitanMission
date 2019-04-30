
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Box;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.layout.Pane;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

public class LandingScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

    int WIDTH=2880;
    int HEIGHT=1800;

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

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
        //  while(rocket.getTranslateY()!=tMoon.getTranslateY())
            rocket.setTranslateY(rocket.getTranslateY()+1);
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

    stage.setScene(main);
    stage.setTitle("Solar System");
    stage.show();

    }
}
