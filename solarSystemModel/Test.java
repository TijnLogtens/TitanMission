import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class Test extends Application {
    // variables
    int i = 0;
    private final double SCALAR_SIZE = 5E4;
    private final double DISTANCE_SIZE = 9E7;
    private final double MOON_SCALAR = 7E1;
    private final double TITAN_SCALAR = MOON_SCALAR * 1.75;
    private final double dt = 10;
    private boolean canContinue = false;

    private int days = 0;

    double anchorX;
    double anchorY;
    double anchorAngleX = 0;
    double anchorAngleY = 0;
    double initialTitanX = 3.537424927743304E+11;
    double initialTitanY = -1.462539028125231E+12;
    double minDifference = Double.MAX_VALUE;
    final DoubleProperty angleX = new SimpleDoubleProperty(0);
    final DoubleProperty angleY = new SimpleDoubleProperty(0);

    // creates the rotation of the camera
    class BGroup extends Group {

        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }

    /**
     * initialise mouse control for rotating the panel
     * 
     * @param group the elements to rotate in the @param scene
     * @param scene the scene to rotate
     * @param stage the stage the @param scene is applied to
     */
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
            group.translateZProperty().set(group.getTranslateZ() + delta * 200);
        });
    }

    /**
     * Initialise every Sphere and CelestialBody Initialise every location and trail
     * (if applicable)
     * 
     */
    public void start(Stage stage) {
        ArrayList<CelestialBody> items = new ArrayList<>();
        // Drawing SUN
        Sphere sun = new Sphere();
        CelestialBody sun_ = new CelestialBody(Masses.getmSun(), 0, 0, 0, 0, 0, 0, Double.MAX_VALUE,
                Diameters.getdSun());
        PhongMaterial m1 = new PhongMaterial();
        sun.setRadius((sun_.getSize() / 2) / 3E6);
        initiatePlanetLocation(sun, sun_);
        initiatePlanetSprite(sun, m1, new Image(getClass().getResourceAsStream("sun.jpeg")));
        items.add(sun_);

        // Drawing MERCURY
        Sphere mercury = new Sphere();
        CelestialBody merc = new CelestialBody(Masses.getmMercury(), -5.843237462283994E10, -2.143781663349622E10,
                3.608679295141068E9, 6.693497964118796E+03, -4.362708337948559E+04, -4.178969254985038E+03, 0,
                Diameters.getdMercury());
        PhongMaterial m2 = new PhongMaterial();
        int[] mercuryIndex = new int[] { 0 };
        Sphere[] mercuryTrail = new Sphere[250];
        mercury.setRadius(merc.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(mercury, merc, m2, new Image(getClass().getResourceAsStream("merc.jpg")), mercuryTrail);
        items.add(merc);

        // Drawing VENUS
        Sphere venus = new Sphere();
        CelestialBody ven = new CelestialBody(Masses.getmVenus(), -2.580458154996926E+09, -1.087011239119300E+11,
                -1.342601858592726E+09, 3.477728421647656E+04, -9.612123998925466E+02, -2.020103291838695E+03, 0,
                Diameters.getdVenus());
        PhongMaterial m3 = new PhongMaterial();
        int[] venusIndex = new int[] { 0 };
        Sphere[] venusTrail = new Sphere[250];
        venus.setRadius(ven.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(venus, ven, m3, new Image(getClass().getResourceAsStream("venus.jpg")), venusTrail);
        items.add(ven);

        // Drawing EARTH
        Sphere earth = new Sphere();
        CelestialBody ear = new CelestialBody(Masses.getmEarth(), -1.490108621500159E+11, -2.126396301163715E+09,
                1.388910094132880E+05, -6.271192280390987E+01, -2.988491242814953E+04, 1.101633412416092E+00, 0,
                Diameters.getdEarth());
        PhongMaterial m4 = new PhongMaterial();
        int[] earthIndex = new int[] { 0 };
        Sphere[] earthTrail = new Sphere[250];
        earth.setRadius(ear.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(earth, ear, m4, new Image(getClass().getResourceAsStream("earth.jpg")), earthTrail);
        items.add(ear);

        // Drawing MARS
        Sphere mars = new Sphere();
        CelestialBody mar = new CelestialBody(Masses.getmMars(), 2.324287221167859E+10, 2.314995121135774E+11,
                4.280415288364515E+09, -2.319279681535404E+04, 4.479321597588995E+03, 6.629375352771729E+02, 0,
                Diameters.getdMars());
        PhongMaterial m5 = new PhongMaterial();
        int[] marsIndex = new int[] { 0 };
        Sphere[] marsTrail = new Sphere[250];
        mars.setRadius(mar.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(mars, mar, m5, new Image(getClass().getResourceAsStream("mars.jpg")), marsTrail);
        items.add(mar);

        // Drawing JUPITER
        Sphere jupiter = new Sphere();
        CelestialBody jup = new CelestialBody(Masses.getmJupiter(), -2.356728458452976E+11, -7.610012694580332E+11,
                8.434019057867110E+09, 1.233361263555140E+04, -3.252782848348839E+03, -2.625332120353037E+02, 0,
                Diameters.getdJupiter());
        PhongMaterial m6 = new PhongMaterial();
        int[] jupiterIndex = new int[] { 0 };
        Sphere[] jupiterTrail = new Sphere[250];
        jupiter.setRadius(jup.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(jupiter, jup, m6, new Image(getClass().getResourceAsStream("jupiter.jpg")), jupiterTrail);
        items.add(jup);

        // Drawing SATURN
        Sphere saturn = new Sphere();
        CelestialBody sat = new CelestialBody(Masses.getmSaturn(), 3.547593532400821E+11, -1.461948830848272E+12,
                1.129255310798091E+10, 8.867827359240396E+03, 2.247044412940183E+03, -3.923703407460523E+02, 0,
                Diameters.getdSaturn());
        PhongMaterial m7 = new PhongMaterial();
        int[] saturnIndex = new int[] { 0 };
        Sphere[] saturnTrail = new Sphere[250];
        saturn.setRadius(sat.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(saturn, sat, m7, new Image(getClass().getResourceAsStream("saturn.jpg")), saturnTrail);
        items.add(sat);

        // Drawing URANUS
        Sphere uranus = new Sphere();
        CelestialBody ur = new CelestialBody(Masses.getmUranus(), 2.520721625280142E+12, 1.570265330931762E+12,
                -2.681128773651946E+10, -3.638605615637463E+03, 5.459468350572506E+03, 6.727967066481910E+01, 0,
                Diameters.getdUranus());
        PhongMaterial m8 = new PhongMaterial();
        int[] uranusIndex = new int[] { 0 };
        Sphere[] uranusTrail = new Sphere[250];
        uranus.setRadius(ur.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(uranus, ur, m8, new Image(getClass().getResourceAsStream("uranus.jpg")), uranusTrail);
        items.add(ur);

        // Drawing NEPTUNE
        Sphere neptune = new Sphere();
        CelestialBody nep = new CelestialBody(Masses.getmNeptune(), 4.344787551365745E+12, -1.083664718815018E+12,
                -7.782629925658041E+10, 1.292632887654737E+03, 5.305024140488896E+03, -1.386814230827889E+02, 0,
                Diameters.getdNeptune());
        PhongMaterial m9 = new PhongMaterial();
        int[] neptuneIndex = new int[] { 0 };
        Sphere[] neptuneTrail = new Sphere[250];
        neptune.setRadius(nep.getSize() / (2 * SCALAR_SIZE));
        initiatePlanet(neptune, nep, m9, new Image(getClass().getResourceAsStream("neptune.jpg")), neptuneTrail);
        items.add(nep);

        // Drawing MOON
        Sphere moon = new Sphere();
        CelestialBody luna = new CelestialBody(Masses.getmMoon(), -1.493626859901140E+11, -2.212378435248749E+09,
                3.162933122716530E+07, 1.540496550112790E+02, -3.094661877857872E+04, 2.193857468353855E+01,
                semiMajorAxis.getaMoon(), Diameters.getdMoon());
        PhongMaterial m10 = new PhongMaterial();
        moon.setRadius(luna.getSize() / (4 * SCALAR_SIZE));
        moon.setTranslateX((luna.getX() - ear.getX()) * MOON_SCALAR / DISTANCE_SIZE + earth.getTranslateX());
        moon.setTranslateY((luna.getY() - ear.getY()) * MOON_SCALAR / DISTANCE_SIZE + earth.getTranslateY());
        moon.setTranslateZ((luna.getZ() - ear.getZ()) * MOON_SCALAR / DISTANCE_SIZE + earth.getTranslateZ());
        m10.setDiffuseColor(Color.GRAY);
        m10.setSpecularColor(Color.GRAY);
        moon.setMaterial(m10);
        items.add(luna);

        // Drawing TITAN
        Sphere titan = new Sphere();
        CelestialBody SVI = new CelestialBody(Masses.getmTitan(), 3.537424927743304E+11, -1.462539028125231E+12,
                1.169787519537956E+10, 1.208193089270527E+04, -1.813839579262785E+03, 1.381017323560965E+03,
                semiMajorAxis.getaTitan(), Diameters.getdTitan());
        titan.setRadius(SVI.getSize() * 2 / (SCALAR_SIZE));
        titan.setTranslateX((SVI.getX() - sat.getX()) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateX());
        titan.setTranslateY((SVI.getY() - sat.getY()) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateY());
        titan.setTranslateZ((SVI.getZ() - sat.getZ()) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateZ());
        m10.setDiffuseColor(Color.GRAY);
        m10.setSpecularColor(Color.GRAY);
        titan.setMaterial(m10);
        items.add(SVI);

        // Create Rocket
        Sphere rocket = new Sphere();
        CelestialBody Rocket = new CelestialBody(Masses.getmRocket(), -1.48994114668E+11, -4.708050346E+09,
            -0.794796007452525E+04, -1.595574212795757E+04);
        rocket.setRadius(300);
        rocket.setMaterial(m10);
        items.add(Rocket);
        /**
         * add all the spheres and trails into a group to add them to the scene
         */
        // Creating a Group object
        Group root = new Group(sun, mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, moon, titan, rocket);
        Group mercTrail = new Group(mercuryTrail);
        Group venTrail = new Group(venusTrail);
        Group earTrail = new Group(earthTrail);
        Group marTrail = new Group(marsTrail);
        Group jupTrail = new Group(jupiterTrail);
        Group satTrail = new Group(saturnTrail);
        Group urTrail = new Group(uranusTrail);
        Group nepTrail = new Group(neptuneTrail);
        BGroup group = new BGroup();
        group.getChildren().addAll(root, mercTrail, venTrail, earTrail, marTrail, jupTrail, satTrail, urTrail,
                nepTrail);
        group.translateXProperty().set(10);
        group.translateYProperty().set(10);
        group.translateZProperty().set(10);

        /**
         * add zoom options for the window
         */
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
            case S:
                group.translateZProperty().set(group.getTranslateZ() + 3000);
                break;
            case W:
                group.translateZProperty().set(group.getTranslateZ() - 3000);
                break;
            case ESCAPE:
                System.exit(0);
                break;
            default:
                break;
            }
        });

        Pane panel = new Pane();
        panel.getChildren().add(group);

        Scene scene = new Scene(panel, 2880, 1800);

        Image image = new Image(getClass().getResourceAsStream("space.jpg"));
        BackgroundImage bgImg = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background b = new Background(bgImg);
        panel.setBackground(b);

        Camera c = new PerspectiveCamera();
        scene.setCamera(c);

        initialMouseControl(group, scene, stage);

        // Setting title to the Stage
        stage.setTitle("Universe");

        // Adding scene to the stage
        stage.setScene(scene);
        // stage.setResizable(false);

        // Displaying the contents of the stage
        stage.show();

        // MOVE MERCURY
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        AnimationTimer timer = new AnimationTimer() {
            /**
             * Update the position of all CelestialBody's and update the position of all
             * spheres update trails, if applicable
             */
            @Override
            public void handle(long l) {
                double[][] points = new double[items.size()][3];
                points[11][0] = Rocket.getX();
                points[11][1] = Rocket.getY();
                points[11][2] = Rocket.getZ();
                for (int i = 0; i < 24 * 60 * 60 * 0.1; i++) {
                    for (int j = 0; j < items.size(); j++) {
                        if(j != 11){
                            points[j] = items.get(j).update(items, dt);
                        } else if (!canContinue){
                            canContinue = true;
                        } else {
                            points[j] = items.get(j).update(items, dt);
                        }
                    }

                    for (int j = 0; j < items.size(); j++) {
                        items.get(j).updatePosition(points[j]);
                    }
                }
                days++;
                
                updatePlanet(points[0], sun);
                updatePlanet(points[1], mercury, mercuryIndex, mercuryTrail);
                updatePlanet(points[2], venus, venusIndex, venusTrail);
                updatePlanet(points[3], earth, earthIndex, earthTrail);
                updatePlanet(points[4], mars, marsIndex, marsTrail);
                updatePlanet(points[5], jupiter, jupiterIndex, jupiterTrail);
                updatePlanet(points[6], saturn, saturnIndex, saturnTrail);
                updatePlanet(points[7], uranus, uranusIndex, uranusTrail);
                updatePlanet(points[8], neptune, neptuneIndex, neptuneTrail);

                moon.setTranslateX((points[9][0] - ear.getX()) * MOON_SCALAR / DISTANCE_SIZE + earth.getTranslateX());
                moon.setTranslateY((points[9][1] - ear.getY()) * MOON_SCALAR / DISTANCE_SIZE + earth.getTranslateY());
                moon.setTranslateZ((points[9][2] - ear.getZ()) * MOON_SCALAR / DISTANCE_SIZE + earth.getTranslateZ());

                titan.setTranslateX(
                        (points[10][0] - sat.getX()) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateX());
                titan.setTranslateY(
                        (points[10][1] - sat.getY()) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateY());
                titan.setTranslateZ(
                        (points[10][2] - sat.getZ()) * TITAN_SCALAR / DISTANCE_SIZE + saturn.getTranslateZ());

                updatePlanet(points[11], rocket);
                /*
                if(Math.sqrt((points[11][0]-points[10][0])*(points[11][0]-points[10][0]) + (points[11][1]-points[10][1])*(points[11][1]-points[10][1])) < minDifference){
                    minDifference = Math.sqrt((points[11][0]-points[10][0])*(points[11][0]-points[10][0]) + (points[11][1]-points[10][1])*(points[11][1]-points[10][1]));
                    System.out.println(days +" "+ minDifference);
                }
                */
                //System.out.println(Rocket.getVx());
                
                if(Math.abs(points[11][0]-initialTitanX) < Diameters.getdTitan() && Math.abs(points[11][1]-initialTitanY) < Diameters.getdTitan()){
                    System.out.println("WE MADE IT");
                    System.out.println(points[10][0] + " " + points[10][1]);
                }
                if (days <= (15 * 365) + 3) {
                //   System.out.println(points[10][1]);
                } else {
                    System.exit(0);
                }
            }
        };

        // create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(1);
        // one can add a specific action when the keyframe is reached
        EventHandler onFinished = t -> {
            // stack.setTranslateX(100); //java.lang.Math.random()*200-100
            // reset counter
            // i = 0;
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished); // keyValueX, keyValueY

        // add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        timeline.play();
        timer.start();
    }

    /**
     * initialises a planet
     * 
     * @param planet      Sphere that represents the planet
     * @param planetBody  CelestialBody that represents the planet
     * @param m           PhongMaterial that represents the texture of the planet
     * @param planetImage Image that represents the texture of the planet
     * @param planetTrail Trail that should be linked to the planet
     */
    private void initiatePlanet(Sphere planet, CelestialBody planetBody, PhongMaterial m, Image planetImage,
            Sphere[] planetTrail) {
        initiatePlanetLocation(planet, planetBody);
        initiatePlanetSprite(planet, m, planetImage);
        initiatePlanetTrail(planet, planetTrail, m);
    }

    /**
     * initialises the location of the Sphere in the scene
     * 
     * @param planet     Sphere that represents the planet
     * @param planetBody CelestialBody that represents the planet
     */
    private void initiatePlanetLocation(Sphere planet, CelestialBody planetBody) {
        planet.setTranslateX((planetBody.getX() / DISTANCE_SIZE));
        planet.setTranslateY((planetBody.getY() / DISTANCE_SIZE));
        planet.setTranslateZ((planetBody.getZ() / DISTANCE_SIZE));
    }

    /**
     * initialises the appearance of the planet
     * 
     * @param planet      Sphere that represents the planet
     * @param m           PhongMaterial that should be applied to @param planet
     * @param planetImage Image that should be applied to @param m
     */
    private void initiatePlanetSprite(Sphere planet, PhongMaterial m, Image planetImage) {
        m.setDiffuseMap(planetImage);
        m.setSelfIlluminationMap(planetImage);
        m.setSpecularMap(planetImage);
        planet.setMaterial(m);
    }

    /**
     * initialises the trail of the planet
     * 
     * @param planet      Sphere that represents the planet
     * @param planetTrail array of the Spheres that represent the trail
     * @param m           PhongMaterial that should be applied to the Spheres
     *                    in @param planetTrail
     */
    private void initiatePlanetTrail(Sphere planet, Sphere[] planetTrail, PhongMaterial m) {
        for (int i = 0; i < 250; i++) {
            planetTrail[i] = new Sphere(3);
            planetTrail[i].setTranslateX(planet.getTranslateX());
            planetTrail[i].setTranslateY(planet.getTranslateY());
            planetTrail[i].setTranslateZ(planet.getTranslateZ());
            planetTrail[i].setMaterial(m);
        }
    }

    /**
     * upates the planets
     * 
     * @param points      new location of the planet
     * @param planet      the Sphere that represents the planet
     * @param planetIndex the index of the element that is needed in @param
     *                    planetTrail
     * @param planetTrail the array of spheres with the trail
     */
    private void updatePlanet(double[] points, Sphere planet, int[] planetIndex, Sphere[] planetTrail) {
        updatePlanetLocation(points, planet);
        updatePlanetTrail(planet, planetIndex, planetTrail);
    }

    /**
     * updates the planet (if no trail exists)
     * 
     * @param points new position of the planet
     * @param planet the Sphere that represents the planet
     */
    private void updatePlanet(double[] points, Sphere planet) {
        updatePlanetLocation(points, planet);
    }

    /**
     * updates the trail of the planet
     * 
     * @param planet      Sphere that represents the planet
     * @param trailIndex  index of the element that should be accessed in @param planetTrail
     * @param planetTrail the trail of the planet
     */
    private void updatePlanetTrail(Sphere planet, int[] trailIndex, Sphere[] planetTrail) {
        if (trailIndex[0] == 250) {
            trailIndex[0] = 0;
        }
        planetTrail[trailIndex[0]].setTranslateX(planet.getTranslateX());
        planetTrail[trailIndex[0]].setTranslateY(planet.getTranslateY());
        planetTrail[trailIndex[0]].setTranslateZ(planet.getTranslateZ());
        trailIndex[0]++;
    }

    /**
     * updates the position of the planet
     * 
     * @param points array of new postion of the planet
     * @param planet Sphere that represents the planet
     */
    private void updatePlanetLocation(double[] points, Sphere planet) {
        planet.setTranslateX((points[0] / DISTANCE_SIZE));
        planet.setTranslateY((points[1] / DISTANCE_SIZE));
        planet.setTranslateZ((points[2] / DISTANCE_SIZE));
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
