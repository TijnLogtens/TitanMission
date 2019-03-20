package solarSystemModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.LinkedList;
import java.util.Queue;

public class solarSystemPanel extends JPanel implements Runnable{

	//CelestialBodies
	public Star sun;
	public CelestialBody mercury;
	public CelestialBody venus;
	public CelestialBody earth;
	public CelestialBody mars;
	public CelestialBody jupiter;
	public CelestialBody saturn;
	public CelestialBody uranus;
	public CelestialBody neptune;
	public double[][] points; 


	//Moons
	public Satellite moon;
	public Satellite phobos, deimos;
	public Satellite io, europa, ganymede, callisto;
	public Satellite mimas, enceladus, tethys, dione, rhea, titan, iapetus;
	public Satellite[] marsMoons = new Satellite[]{phobos, deimos};
	public Satellite[] jupiterMoons = new Satellite[]{io, europa, ganymede, callisto};
	public Satellite[] saturnMoons = new Satellite[]{mimas, enceladus, tethys, dione, rhea, titan, iapetus};

	//Variables
	private double dt = 24*60*60;
	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;

	public solarSystemPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		init();
		render();
	}

	public void init() {
		//TODO initialise planets and sun
		sun = new Star(Masses.getmSun(), 0, 0, Diameters.getdSun());
		mercury = new CelestialBody(Masses.getmMercury(), -5.843237462283994E10, -2.143781663349622E10, 6.693497964118796E+03, -4.362708337948559E+04, semiMajorAxis.getaMercury(), Diameters.getdMercury(), sun);
		venus = new CelestialBody(Masses.getmVenus(), -2.580458154996926E+09, -1.087011239119300E+11, 3.477728421647656E+04, -9.612123998925466E+02, semiMajorAxis.getaVenus(),Diameters.getdVenus(), sun);
		earth = new CelestialBody(Masses.getmEarth(), -1.490108621500159E+11, -2.126396301163715E+09, -6.271192280390987E+01, -2.988491242814953E+04, semiMajorAxis.getaEarth(),Diameters.getdEarth(), sun);
		mars = new CelestialBody(Masses.getmMars(), 2.324287221167859E+10, 2.314995121135774E+11, -2.319279681535404E+04, 4.479321597588995E+03, semiMajorAxis.getaMars(), Diameters.getdMars(), sun);
		jupiter = new CelestialBody(Masses.getmJupiter(), -2.356728458452976E+11, -7.610012694580332E+11, 1.233361263555140E+04, -3.252782848348839E+03, semiMajorAxis.getaJupiter(), Diameters.getdJupiter(), sun);
		saturn = new CelestialBody(Masses.getmSaturn(), 3.547593532400821E+11, -1.461948830848272E+12, 8.867827359240396E+03, 2.247044412940183E+03, semiMajorAxis.getaSaturn(), Diameters.getdSaturn(), sun);
		uranus = new CelestialBody(Masses.getmUranus(), 2.520721625280142E+12, 1.570265330931762E+12, -3.638605615637463E+03, 5.459468350572506E+03, semiMajorAxis.getaUranus(), Diameters.getdUranus(), sun);
		neptune = new CelestialBody(Masses.getmNeptune(), 4.344787551365745E+12, -1.083664718815018E+12, 1.292632887654737E+03, 5.305024140488896E+03, semiMajorAxis.getaNeptune(), Diameters.getdNeptune(), sun);
		moon = new Satellite(Masses.getmMoon(), -3.518238400980993E+08, -8.598213408503399E+07, 2.167615778151889E+01, -1.061706350429193E+03, semiMajorAxis.getaMoon(), Diameters.getdMoon(), earth);
		/*phobos = new Satellite(Masses.getmPhobos(), -6.158431385405609E+06, -6.363353887023877E+06, 1.288711455996767E+03, -1.557153702986508E+03, semiMajorAxis.getaPhobos(), Diameters.getdPhobos(), mars);
		deimos = new Satellite(Masses.getmDeimos(), 1.783618538019075E+07, 1.352491840245665E+07, -6.725498522894642E+02, 1.098537553338818E+03, semiMajorAxis.getaDeimos(), Diameters.getdDeimos(), mars);
		io = new Satellite(Masses.getmIo(), 1.380920023293307E+08, -3.967491880441244E+08, 1.644325961621238E+04, 5.668373803162439E+03, semiMajorAxis.getaIo(), Diameters.getdIo(), jupiter);
		europa = new Satellite(Masses.getmEuropa(), -3.514385830422270E+07, 6.635734967793569E+08, -1.384460356721946E+04, -6.797082818354832E+02, semiMajorAxis.getaEuropa(), Diameters.getdEuropa(), jupiter);
		ganymede = new Satellite(Masses.getmGanymede(), -1.064633035469716E+08, -1.062583793009553E+09, 1.084493571087621E+04, -1.070950584454436E+03, semiMajorAxis.getaGanymede(), Diameters.getdGanymede(), jupiter);
		callisto = new Satellite(Masses.getmCallisto(), 8.412953282863846E+08, -1.678519668237818E+09, 7.329296441560971E+03, 3.728818013089040E+03, semiMajorAxis.getaCallisto(), Diameters.getdCallisto(), jupiter);
		/*mimas = new Satellite(Masses.getmMimas(), x, y, vx, vy, sma, d, saturn);
		enceladus = new Satellite(Masses.getmEnceladus(), x, y, vx, vy, sma, d, saturn);
		tethys = new Satellite(Masses.getmTethys(), x, y, vx, vy, sma, d, saturn);
		dione = new Satellite(Masses.getmDione(), x, y, vx, vy, sma, d, saturn);
		rhea = new Satellite(Masses.getmRhea(), x, y, vx, vy, sma, d, saturn);*/
		titan = new Satellite(Masses.getmTitan(), -1.016860465751689E+09, -5.901972769593473E+08, 3.214103533464870E+03, -4.060883992202967E+03, semiMajorAxis.getaTitan(), Diameters.getdTitan(), saturn);
		//iapetus = new Satellite(Masses.getmIapetus(), x, y, vx, vy, sma, d, saturn);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void render() {
		Timer timer = new Timer(10, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				update();
				repaint();
			}
		});
		timer.start();
	}

	public void update() {
		double[] merc_points = mercury.update(dt);
		double[] venus_points= venus.update(dt);
		double[] earth_points=earth.update(dt);
		double[] mars_points=mars.update(dt);
		double[] jupiter_points=jupiter.update(dt);
		double[] saturn_points=saturn.update(dt);
		double[] uranus_points=uranus.update(dt);
		double[] neptune_points=neptune.update(dt);
		double[] moon_points=moon.update(dt);
		/*phobos.update(dt);
		deimos.update(dt);
		io.update(dt);
		//System.out.println("X pos = " + io.getX() + "	Y pos = " + io.getY());
		europa.update(dt);
		ganymede.update(dt);
		callisto.update(dt);*/
		points = new double[][]{merc_points, venus_points, earth_points,mars_points,jupiter_points,saturn_points,uranus_points,neptune_points,moon_points};
		titan.update(dt);

	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		sun.drawPlanet(g);
		g.setColor(Color.GRAY);
		mercury.drawPlanet(g);
		g.setColor(Color.ORANGE);
		venus.drawPlanet(g);
		g.setColor(Color.GREEN);
		earth.drawPlanet(g);
		g.setColor(Color.RED);
		mars.drawPlanet(g);
		g.setColor(Color.WHITE);
		jupiter.drawPlanet(g);
		g.setColor(Color.YELLOW);
		saturn.drawPlanet(g);
		g.setColor(Color.PINK);
		uranus.drawPlanet(g);
		g.setColor(Color.BLUE);
		neptune.drawPlanet(g);
		g.setColor(Color.GRAY);
		moon.drawPlanet(g);
		/*phobos.drawPlanet(g);
		deimos.drawPlanet(g);
		io.drawPlanet(g);
		europa.drawPlanet(g);
		ganymede.drawPlanet(g);
		callisto.drawPlanet(g);*/
		titan.drawPlanet(g);

	}

}
