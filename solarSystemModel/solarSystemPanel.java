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
	
	List<CelestialBody> planets;
	
	//Moons
	
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
		mercury = new CelestialBody(Masses.getmMercury(), -5.843237462283994E10, -2.143781663349622E10, 6.693497964118796E+03, -4.362708337948559E+04, semiMajorAxis.getaMercury(), Diameters.getdMercury());
		venus = new CelestialBody(Masses.getmVenus(), -2.580458154996926E+09, -1.087011239119300E+11, 3.477728421647656E+04, -9.612123998925466E+02, semiMajorAxis.getaVenus(),Diameters.getdVenus());
		earth = new CelestialBody(Masses.getmEarth(), -1.490108621500159E+11, -2.126396301163715E+09, -6.271192280390987E+01, -2.988491242814953E+04, semiMajorAxis.getaEarth(),Diameters.getdEarth());
		mars = new CelestialBody(Masses.getmMars(), 2.324287221167859E+10, 2.314995121135774E+11, -2.319279681535404E+04, 4.479321597588995E+03, semiMajorAxis.getaMars(), Diameters.getdMars());
		jupiter = new CelestialBody(Masses.getmJupiter(), -2.356728458452976E+11, -7.610012694580332E+11, 1.233361263555140E+04, -3.252782848348839E+03, semiMajorAxis.getaJupiter(), Diameters.getdJupiter());
		saturn = new CelestialBody(Masses.getmSaturn(), 3.547593532400821E+11, -1.461948830848272E+12, 8.867827359240396E+03, 2.247044412940183E+03, semiMajorAxis.getaSaturn(), Diameters.getdSaturn());
		uranus = new CelestialBody(Masses.getmUranus(), 2.520721625280142E+12, 1.570265330931762E+12, -3.638605615637463E+03, 5.459468350572506E+03, semiMajorAxis.getaUranus(), Diameters.getdUranus());
		neptune = new CelestialBody(Masses.getmNeptune(), 4.344787551365745E+12, -1.083664718815018E+12, 1.292632887654737E+03, 5.305024140488896E+03, semiMajorAxis.getaNeptune(), Diameters.getdNeptune());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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

	}
	
	public void render() {
		Timer timer = new Timer(1, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				update();
				repaint();
			}
		});
		timer.start();	
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

	}
	
}
