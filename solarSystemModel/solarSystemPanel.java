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




	//Moons
	public CelestialBody moon;
	public CelestialBody phobos, deimos;
	public CelestialBody io, europa, ganymede, callisto;
	public CelestialBody mimas, enceladus, thetys, dione, rhea, titan, iapetus;
//	public CelestialBody[] marsMoons = new Satellite[2]{phobos, deimos};
//	public CelestialBody[] jupiterMoons = new Satellite[4]{io, europa, ganymede, callisto};
//	public CelestialBody[] saturnMoons = new Satellite[7]{mimas, enceladus, thethys, dione, rhea, titan, iapetus};

	//Variables
	private double dt = 0.001;
	public static final int HEIGHT = 800;
	public static final int WIDTH = 800;
	
	public solarSystemPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		init();
		//render();
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
		/*phobos = new Satellite(mass, x, y, vx, vy, sma, d, mars);
		deimos = new Satellite(mass, x, y, vx, vy, sma, d, mars);
		io = new Satellite(mass, x, y, vx, vy, sma, d, jupiter);
		europa = new Satellite(mass, x, y, vx, vy, sma, d, jupiter);
		ganymede = new Satellite(mass, x, y, vx, vy, sma, d, jupiter);
		callisto = new Satellite(mass, x, y, vx, vy, sma, d, jupiter);
		mimas = new Satellite(mass, x, y, vx, vy, sma, d, saturn);
		enceladus = new Satellite(mass, x, y, vx, vy, sma, d, saturn);
		thetys = new Satellite(mass, x, y, vx, vy, sma, d, saturn);
		dione = new Satellite(mass, x, y, vx, vy, sma, d, saturn);
		rhea = new Satellite(mass, x, y, vx, vy, sma, d, saturn);*/
		titan = new Satellite(Masses.getmTitan(), -1.016860465751689E+09, -5.901972769593473E+08, 3.214103533464870E+03, -4.060883992202967E+03, semiMajorAxis.getaTitan(), Diameters.getdTitan(), saturn);
		//iapetus = new Satellite(mass, x, y, vx, vy, sma, d, saturn);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void render() {
		Timer timer = new Timer(20, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				update();
				repaint();
			}
		});
		timer.start();
	}

	public void update() {
		mercury.update(dt);
		venus.update(dt);
		earth.update(dt);
		mars.update(dt);
		jupiter.update(dt);
		saturn.update(dt);
		uranus.update(dt);
		neptune.update(dt);
		moon.update(dt);

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
		g.setColor(Color.BLACK);
		jupiter.drawPlanet(g);
		g.setColor(Color.YELLOW);
		saturn.drawPlanet(g);
		g.setColor(Color.PINK);
		uranus.drawPlanet(g);
		g.setColor(Color.BLUE);
		neptune.drawPlanet(g);
		g.setColor(Color.GRAY);
		moon.drawPlanet(g);
		titan.drawPlanet(g);

	}
	
}
