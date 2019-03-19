
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.CullFace;
import javafx.stage.Stage;
import javafx.scene.shape.Sphere;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.animation.PathTransition;
import javafx.util.*;
import javafx.scene.shape.Ellipse;

public class Test extends Application {
     public void start(Stage stage) {

        //Drawing SUN
        Sphere sun = new Sphere();
        //Setting the radius of the Sphere
        sun.setRadius(80.0);
        //Setting the position of the sphere
        sun.setTranslateX(400);
        sun.setTranslateY(400);
        //setting the cull face of the sphere to front
        sun.setCullFace(CullFace.BACK);
        //Setting color to the sphere
        PhongMaterial m1 = new PhongMaterial();
        m1.setDiffuseColor(Color.YELLOW);
        m1.setSpecularColor(Color.YELLOW);
        sun.setMaterial(m1);

        //Drawing MERCURY
        Sphere mercury = new Sphere();
        PhongMaterial m2 = new PhongMaterial();
        mercury.setRadius(20.0);
        mercury.setTranslateX(500);
        mercury.setTranslateY(400);
        mercury.setCullFace(CullFace.BACK);
        m2.setDiffuseColor(Color.GRAY);
        m2.setSpecularColor(Color.GRAY);
        mercury.setMaterial(m2);

        //MOVE MERCURY
        PathTransition trans=new PathTransition();
        Ellipse e=new Ellipse(400,400,250,150);
        trans.setNode(mercury);
        trans.setDuration(Duration.seconds(3));
        trans.setPath(e);
        trans.setCycleCount(PathTransition.INDEFINITE);
        trans.play();


        //Drawing VENUS
        Sphere venus = new Sphere();
        PhongMaterial m3 = new PhongMaterial();
        venus.setRadius(35.0);
        venus.setTranslateX(600);
        venus.setTranslateY(400);
        venus.setCullFace(CullFace.BACK);
        m3.setDiffuseColor(Color.PERU);
        m3.setSpecularColor(Color.PERU);
        venus.setMaterial(m3);

        //Drawing EARTH
        Sphere earth = new Sphere();
        PhongMaterial m4 = new PhongMaterial();
        earth.setRadius(45.0);
        earth.setTranslateX(700);
        earth.setTranslateY(400);
        earth.setCullFace(CullFace.BACK);
        m4.setDiffuseColor(Color.BLUE);
        m4.setSpecularColor(Color.GREEN);
        earth.setMaterial(m4);

        //Drawing MARS
        Sphere mars = new Sphere();
        PhongMaterial m5 = new PhongMaterial();
        mars.setRadius(30.0);
        mars.setTranslateX(800);
        mars.setTranslateY(400);
        mars.setCullFace(CullFace.BACK);
        m5.setDiffuseColor(Color.BROWN);
        m5.setSpecularColor(Color.BROWN);
        mars.setMaterial(m5);

        //Drawing JUPITER
        Sphere jupiter = new Sphere();
        PhongMaterial m6 = new PhongMaterial();
        jupiter.setRadius(65.0);
        jupiter.setTranslateX(900);
        jupiter.setTranslateY(400);
        jupiter.setCullFace(CullFace.BACK);
        m6.setDiffuseColor(Color.ORANGE);
        m6.setSpecularColor(Color.ORANGE);
        jupiter.setMaterial(m6);

        //Drawing SATURN
        Sphere saturn = new Sphere();
        PhongMaterial m7 = new PhongMaterial();
        saturn.setRadius(55.0);
        saturn.setTranslateX(1000);
        saturn.setTranslateY(400);
        saturn.setCullFace(CullFace.BACK);
        m7.setDiffuseColor(Color.GOLD);
        m7.setSpecularColor(Color.GOLD);
        saturn.setMaterial(m7);

        //Drawing NEPTUNE
        Sphere neptune = new Sphere();
        PhongMaterial m8 = new PhongMaterial();
        neptune.setRadius(45.0);
        neptune.setTranslateX(1100);
        neptune.setTranslateY(400);
        neptune.setCullFace(CullFace.BACK);
        m8.setDiffuseColor(Color.DARKBLUE);
        m8.setSpecularColor(Color.DARKBLUE);
        neptune.setMaterial(m8);

        //Drawing URANUS
        Sphere uranus = new Sphere();
        PhongMaterial m9 = new PhongMaterial();
        uranus.setRadius(35.0);
        uranus.setTranslateX(1200);
        uranus.setTranslateY(400);
        uranus.setCullFace(CullFace.BACK);
        m9.setDiffuseColor(Color.DODGERBLUE);
        m9.setSpecularColor(Color.DODGERBLUE);
        uranus.setMaterial(m9);

        //Creating a Group object
        Group root = new Group(sun,mercury,venus,earth,mars,jupiter,saturn,neptune,uranus);

        //Creating a scene object
        Scene scene = new Scene(root,1500,600);

        /*//Set Background
        Image image=new Image("File:space.png");
        ImageView mv= new ImageView(image);

        root.getChildren().addAll(mv);*/


        //Setting title to the Stage
        stage.setTitle("Universe");

        //Adding scene to the stage
        stage.setScene(scene);
      //  stage.setResizable(false);

        //Displaying the contents of the stage
        stage.show();
     }

     public static void main(String[] args){
        Application.launch(args);
     }
  }
