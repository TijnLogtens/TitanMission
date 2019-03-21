import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
import javafx.scene.layout.Pane;

public class Test extends Application {
  int i=0;
  private final double SCALAR_SIZE = 1E5;
  private final double DISTANCE_SIZE = 2E8;
  private final double dt = 24*60*60;
     public void start(Stage stage) {

      

       //Drawing SUN
       Sphere sun = new Sphere();
       Star sun_ = new Star(Masses.getmSun(), 500, 500, Diameters.getdSun());
       //Setting the radius of the Sphere
       sun.setRadius((sun_.getSize()/2)/1E7);
       //Setting the position of the sphere
       sun.setTranslateX(sun_.getX()-sun.getRadius());
       sun.setTranslateY(sun_.getY()-  sun.getRadius());
       //setting the cull face of the sphere to front
       sun.setCullFace(CullFace.BACK);
       //Setting color to the sphere
       PhongMaterial m1 = new PhongMaterial();
       m1.setDiffuseColor(Color.YELLOW);
       m1.setSpecularColor(Color.YELLOW);
       sun.setMaterial(m1);




       //Drawing MERCURY
       Sphere mercury = new Sphere();
       CelestialBody merc = new CelestialBody(Masses.getmMercury(), -5.843237462283994E10, -2.143781663349622E10, 6.693497964118796E+03, -4.362708337948559E+04, 0, Diameters.getdMercury(), sun_);
       PhongMaterial m2 = new PhongMaterial();
       //System.out.println(merc.getSize()/(2*1E5));
       mercury.setRadius(merc.getSize()/(2*SCALAR_SIZE));
       //System.out.println((merc.getX()/2E8));
       mercury.setTranslateX((merc.getX()/DISTANCE_SIZE) +500  - mercury.getRadius());
        //System.out.println(merc.getY()/2E8);
       mercury.setTranslateY(merc.getY()/DISTANCE_SIZE + 500 - mercury.getRadius());
       mercury.setCullFace(CullFace.BACK);
       m2.setDiffuseColor(Color.GRAY);
       m2.setSpecularColor(Color.GRAY);
       mercury.setMaterial(m2);


       //Drawing VENUS
       Sphere venus = new Sphere();
       CelestialBody ven = new CelestialBody(Masses.getmVenus(), -2.580458154996926E+09, -1.087011239119300E+11, 3.477728421647656E+04, -9.612123998925466E+02, 0,Diameters.getdVenus(), sun_);
       PhongMaterial m3 = new PhongMaterial();
       venus.setRadius(ven.getSize()/(2*SCALAR_SIZE));
       venus.setTranslateX(ven.getX()/DISTANCE_SIZE + 500 - venus.getRadius());
       venus.setTranslateY(ven.getY() /DISTANCE_SIZE + 500 - venus.getRadius());
       venus.setCullFace(CullFace.BACK);
       m3.setDiffuseColor(Color.PERU);
       m3.setSpecularColor(Color.PERU);
       venus.setMaterial(m3);



      
       //Drawing EARTH
       Sphere earth = new Sphere();
       CelestialBody ear = new CelestialBody(Masses.getmEarth(), -1.490108621500159E+11, -2.126396301163715E+09, -6.271192280390987E+01, -2.988491242814953E+04, 0,Diameters.getdEarth(), sun_);
       PhongMaterial m4 = new PhongMaterial();
       earth.setRadius(ear.getSize()/(2*SCALAR_SIZE));
       earth.setTranslateX(ear.getX()/DISTANCE_SIZE + 500 - earth.getRadius());
       earth.setTranslateY(ear.getY()/DISTANCE_SIZE + 500 - earth.getRadius());
       earth.setCullFace(CullFace.BACK);
       m4.setDiffuseColor(Color.BLUE);
       m4.setSpecularColor(Color.GREEN);
       earth.setMaterial(m4);


       //Drawing MARS
       Sphere mars = new Sphere();
       CelestialBody mar = new CelestialBody(Masses.getmMars(), 2.324287221167859E+10, 2.314995121135774E+11, -2.319279681535404E+04, 4.479321597588995E+03, 0, Diameters.getdMars(), sun_);
       PhongMaterial m5 = new PhongMaterial();
       mars.setRadius(mar.getSize()/(2*SCALAR_SIZE));
       mars.setTranslateX(mar.getX()/ DISTANCE_SIZE + 500 - mars.getRadius());
       mars.setTranslateY(mar.getY()/ DISTANCE_SIZE + 500 - mars.getRadius());
       mars.setCullFace(CullFace.BACK);
       m5.setDiffuseColor(Color.BROWN);
       m5.setSpecularColor(Color.BROWN);
       mars.setMaterial(m5);

       //Drawing JUPITER
       Sphere jupiter = new Sphere();
       CelestialBody jup = new CelestialBody(Masses.getmJupiter(), -2.356728458452976E+11, -7.610012694580332E+11, 1.233361263555140E+04, -3.252782848348839E+03, 0, Diameters.getdJupiter(), sun_);
       PhongMaterial m6 = new PhongMaterial();
       jupiter.setRadius(jup.getSize()/(2*SCALAR_SIZE));
       jupiter.setTranslateX(jup.getX()/ DISTANCE_SIZE + 500 - jupiter.getRadius());
       jupiter.setTranslateY(jup.getY()/ DISTANCE_SIZE + 500 - jupiter.getRadius());
       jupiter.setCullFace(CullFace.BACK);
       m6.setDiffuseColor(Color.ORANGE);
       m6.setSpecularColor(Color.ORANGE);
       jupiter.setMaterial(m6);

       //Drawing SATURN
       Sphere saturn = new Sphere();
       CelestialBody sat = new CelestialBody(Masses.getmSaturn(), 3.547593532400821E+11, -1.461948830848272E+12, 8.867827359240396E+03, 2.247044412940183E+03, 0, Diameters.getdSaturn(), sun_);
       PhongMaterial m7 = new PhongMaterial();
       saturn.setRadius(sat.getSize()/(2*SCALAR_SIZE));
       saturn.setTranslateX(sat.getX() / DISTANCE_SIZE + 500 - saturn.getRadius());
       saturn.setTranslateY(sat.getY() / DISTANCE_SIZE + 500 - saturn.getRadius());
       saturn.setCullFace(CullFace.BACK);
       m7.setDiffuseColor(Color.GOLD);
       m7.setSpecularColor(Color.GOLD);
       saturn.setMaterial(m7);

       //Drawing NEPTUNE
       Sphere neptune = new Sphere();
       CelestialBody nep = new CelestialBody(Masses.getmNeptune(), 4.344787551365745E+12, -1.083664718815018E+12, 1.292632887654737E+03, 5.305024140488896E+03, 0, Diameters.getdNeptune(), sun_);
       PhongMaterial m9 = new PhongMaterial();
       PhongMaterial m8 = new PhongMaterial();
       neptune.setRadius(nep.getSize()/(2*SCALAR_SIZE));
       neptune.setTranslateX(nep.getX()/DISTANCE_SIZE + 500 - neptune.getRadius() );
       neptune.setTranslateY(nep.getY()/ DISTANCE_SIZE + 500 - neptune.getRadius());
       neptune.setCullFace(CullFace.BACK);
       m8.setDiffuseColor(Color.DARKBLUE);
       m8.setSpecularColor(Color.DARKBLUE);
       neptune.setMaterial(m8);

       //Drawing URANUS
       Sphere uranus = new Sphere();
       CelestialBody ur = new CelestialBody(Masses.getmUranus(), 2.520721625280142E+12, 1.570265330931762E+12, -3.638605615637463E+03, 5.459468350572506E+03,0, Diameters.getdUranus(), sun_);
       uranus.setRadius(ur.getSize()/(2*SCALAR_SIZE));
       uranus.setTranslateX(ur.getX()/ DISTANCE_SIZE+ 500 - uranus.getRadius() );
       uranus.setTranslateY(ur.getY()/ DISTANCE_SIZE+ 500 - uranus.getRadius());
       uranus.setCullFace(CullFace.BACK);
       m9.setDiffuseColor(Color.DODGERBLUE);
       m9.setSpecularColor(Color.DODGERBLUE);
       uranus.setMaterial(m9);

       

       int[] mercIndex = new int[]{0};
       int[] venIndex = new int[] {0};
       int[] earthIndex = new int[] {0};
       int[] marsIndex = new int[] {0};
       int[] jupIndex = new int[] {0};
       int[] saturnIndex = new int[] {0};
       int[] neptIndex = new int[] {0};
       int[] uranusIndex = new int[] {0};

       

       Sphere[] mercTrail = new Sphere[250];
       Sphere[] venTrail = new Sphere[250];
       Sphere[] earthTrail = new Sphere[250];
       Sphere[] marsTrail = new Sphere[250];
       Sphere[] jupTrail = new Sphere[250];
       Sphere[] saturnTrail = new Sphere[250];
       Sphere[] neptTrail = new Sphere[250];
       Sphere[] uranusTrail = new Sphere[250];

        



       for(int i = 0; i < 250; i++){

          mercTrail[i] = new Sphere(3);

          mercTrail[i].setTranslateX(mercury.getTranslateX());
          mercTrail[i].setTranslateY(mercury.getTranslateY());
          mercTrail[i].setMaterial(m2);

          venTrail[i] = new Sphere(3);

          venTrail[i].setTranslateX(venus.getTranslateX());
          venTrail[i].setTranslateY(venus.getTranslateY());
          venTrail[i].setMaterial(m3);

          earthTrail[i] = new Sphere(3);

          earthTrail[i].setTranslateX(earth.getTranslateX());
          earthTrail[i].setTranslateY(earth.getTranslateY());
          earthTrail[i].setMaterial(m4);

          marsTrail[i] = new Sphere(3);

          marsTrail[i].setTranslateX(mars.getTranslateX());
          marsTrail[i].setTranslateY(mars.getTranslateY());
          marsTrail[i].setMaterial(m5);

          jupTrail[i] = new Sphere(3);

          jupTrail[i].setTranslateX(jupiter.getTranslateX());
          jupTrail[i].setTranslateY(jupiter.getTranslateY());
          jupTrail[i].setMaterial(m6);

          saturnTrail[i] = new Sphere(3);

          saturnTrail[i].setTranslateX(saturn.getTranslateX());
          saturnTrail[i].setTranslateY(saturn.getTranslateY());
          saturnTrail[i].setMaterial(m7);

          neptTrail[i] = new Sphere(3);

          neptTrail[i].setTranslateX(neptune.getTranslateX());
          neptTrail[i].setTranslateY(neptune.getTranslateY());
          neptTrail[i].setMaterial(m8);

          uranusTrail[i] = new Sphere(3);

          uranusTrail[i].setTranslateX(uranus.getTranslateX());
          uranusTrail[i].setTranslateY(uranus.getTranslateY());
          uranusTrail[i].setMaterial(m9);
          

       }
       

       
  
        
       //Creating a Group object
       Group root = new Group(sun,mercury,venus,earth,mars,jupiter,saturn,neptune,uranus);
       Group trail = new Group(mercTrail);
       Group ventrail = new Group(venTrail);
       Group earthtrail = new Group(earthTrail);
       Group marstrail = new Group(marsTrail);
       Group juptrail = new Group(jupTrail);
       Group saturntrail = new Group(saturnTrail);
       Group neptrail = new Group(neptTrail);
       Group uranustrail = new Group(uranusTrail);
       //Creating a scene object
       
       
       Pane panel = new Pane();
       panel.getChildren().add(trail);
       panel.getChildren().add(ventrail);
       panel.getChildren().add(marstrail);
       panel.getChildren().add(juptrail);
       panel.getChildren().add(saturntrail);
       panel.getChildren().add(neptrail);
       panel.getChildren().add(uranustrail);
       panel.getChildren().add(earthtrail);
       panel.getChildren().add(root);
       panel.setStyle("-fx-background-color: black");
       
       Scene scene = new Scene(panel,1000,1000);

       
       /* //Set Background
       Image image=new Image("File:space.png");
       ImageView mv= new ImageView(image);

       root.getChildren().addAll(mv); */

       //final StackPane stack = new StackPane();
       //final StackPane stack1 = new StackPane();

       //stack.setLayoutX(30);
       //stack.setLayoutY(30);

       //stack1.getChildren().addAll(venus,earth,mars,jupiter,uranus,neptune,saturn,sun);

       //root.getChildren().add(stack);
       //stack.getChildren().addAll(mercury);


       //Setting title to the Stage
       stage.setTitle("Universe");

       //Adding scene to the stage
       stage.setScene(scene);
       //stage.setResizable(false);

        //Displaying the contents of the stage
        stage.show();


        //MOVE MERCURY
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);


        AnimationTimer timer = new AnimationTimer() {
          //int x=300; int y=300;
            @Override
            public void handle(long l) {
                double[] points = merc.update(dt);
                mercury.setTranslateX((points[0]/DISTANCE_SIZE)+ 500 - mercury.getRadius());
                mercury.setTranslateY((points[1]/DISTANCE_SIZE) + 500 -  mercury.getRadius());
                if(mercIndex[0] == 250) { mercIndex[0] = 0;}
                mercTrail[mercIndex[0]].setTranslateX(mercury.getTranslateX());
                mercTrail[mercIndex[0]].setTranslateY(mercury.getTranslateY());
                mercIndex[0]++;

                double[] points1 = ven.update(dt);
                venus.setTranslateX((points1[0]/DISTANCE_SIZE) + 500 - venus.getRadius());
                venus.setTranslateY((points1[1]/DISTANCE_SIZE) + 500 -  venus.getRadius());
                 if(venIndex[0] == 250) {venIndex[0] = 0;}
                venTrail[venIndex[0]].setTranslateX(venus.getTranslateX());
                venTrail[venIndex[0]].setTranslateY(venus.getTranslateY());
                venIndex[0]++;

                double[] points7 = ear.update(dt);
                earth.setTranslateX((points7[0]/DISTANCE_SIZE) + 500 - earth.getRadius());
                earth.setTranslateY((points7[1]/DISTANCE_SIZE) + 500 -  earth.getRadius());
                 if(earthIndex[0] == 250) {earthIndex[0] = 0;}
                earthTrail[earthIndex[0]].setTranslateX(earth.getTranslateX());
                earthTrail[earthIndex[0]].setTranslateY(earth.getTranslateY());
                earthIndex[0]++;


                double[] points2 = mar.update(dt);
                 mars.setTranslateX((points2[0]/DISTANCE_SIZE) + 500 - mars.getRadius());
                mars.setTranslateY((points2[1]/DISTANCE_SIZE) + 500 -  mars.getRadius());
                 if(marsIndex[0] == 250) {marsIndex[0] = 0;}
                marsTrail[marsIndex[0]].setTranslateX(mars.getTranslateX());
                marsTrail[marsIndex[0]].setTranslateY(mars.getTranslateY());
                marsIndex[0]++;

                double[] points3 = jup.update(dt);
                jupiter.setTranslateX((points3[0]/DISTANCE_SIZE) + 500 - jupiter.getRadius());
                jupiter.setTranslateY((points3[1]/DISTANCE_SIZE) + 500 -  jupiter.getRadius());
                 if(jupIndex[0] == 250) {jupIndex[0] = 0;}
                jupTrail[jupIndex[0]].setTranslateX(jupiter.getTranslateX());
                jupTrail[jupIndex[0]].setTranslateY(jupiter.getTranslateY());
                jupIndex[0]++;

                double[] points4 = sat.update(dt);
                saturn.setTranslateX((points4[0]/DISTANCE_SIZE) + 500 - saturn.getRadius());
                saturn.setTranslateY((points4[1]/DISTANCE_SIZE) + 500 -  saturn.getRadius());
                 if(saturnIndex[0] == 250) {saturnIndex[0] = 0;}
                saturnTrail[saturnIndex[0]].setTranslateX(saturn.getTranslateX());
                saturnTrail[saturnIndex[0]].setTranslateY(saturn.getTranslateY());
                saturnIndex[0]++;

                double[] points5 = nep.update(dt);
                neptune.setTranslateX((points5[0]/DISTANCE_SIZE) + 500 - neptune.getRadius());
                neptune.setTranslateY((points5[1]/DISTANCE_SIZE) + 500 -  neptune.getRadius());
                 if(neptIndex[0] == 250) {neptIndex[0] = 0;}
                neptTrail[neptIndex[0]].setTranslateX(neptune.getTranslateX());
                neptTrail[neptIndex[0]].setTranslateY(neptune.getTranslateY());
                neptIndex[0]++;

                double[] points6 = ur.update(dt);
                uranus.setTranslateX((points6[0]/DISTANCE_SIZE) + 500 - uranus.getRadius());
                uranus.setTranslateY((points6[1]/DISTANCE_SIZE) + 500 -  uranus.getRadius());
                 if(uranusIndex[0] == 250) {uranusIndex[0] = 0;}
                uranusTrail[uranusIndex[0]].setTranslateX(uranus.getTranslateX());
                uranusTrail[uranusIndex[0]].setTranslateY(uranus.getTranslateY());
                uranusIndex[0]++;             

            }
          };



          //create a keyFrame, the keyValue is reached at time 2s
          Duration duration = Duration.millis(1);
          //one can add a specific action when the keyframe is reached
          EventHandler onFinished = new EventHandler<ActionEvent>() {
              public void handle(ActionEvent t) {
                   //stack.setTranslateX(100); //java.lang.Math.random()*200-100
                   //reset counter
                   //i = 0;
              }
          };

          KeyFrame keyFrame = new KeyFrame(duration, onFinished); // keyValueX, keyValueY

                //add the keyframe to the timeline
                timeline.getKeyFrames().add(keyFrame);

                timeline.play();
                timer.start();



      /*  PathTransition trans=new PathTransition();
        Ellipse e=new Ellipse(400,400,250,150);
        trans.setNode(mercury);
        trans.setDuration(Duration.seconds(3));
        trans.setPath(e);
        trans.setCycleCount(PathTransition.INDEFINITE);
        trans.play();*/

     }

     public static void main(String[] args){
        Application.launch(args);
     }
  }