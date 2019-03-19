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
	private double dt = 1;
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
		mercury = new CelestialBody(Masses.getmMercury(), -5.843237462283994E10, -2.143781663349622E+07, 6.693497964118796E+03, -4.362708337948559E+04, semiMajorAxis.getaMercury(), 50);
		venus = new CelestialBody(Masses.getmVenus(), -2580494883.0, -108702671108.0, 3004800124.0, -83049933.0, semiMajorAxis.getaVenus(),50);
		earth = new CelestialBody(Masses.getmEarth(), -149012983094.0, -2126426567.0, -5418387.0, -2582093185.0, semiMajorAxis.getaEarth(),50);
		mars = new CelestialBody(Masses.getmMars(), 23243203038.0, 231502807159.0, -2003886166.0, 387018894.0, semiMajorAxis.getaMars(), 50);
		jupiter = new CelestialBody(Masses.getmJupiter(), -235676200292.0, -761012101163.0, 1065639299.0, -281044438.0, semiMajorAxis.getaJupiter(), 50);
		saturn = new CelestialBody(Masses.getmSaturn(), 354764402704.0, -1461969639484.0, 766191189.0, 194147400.0, semiMajorAxis.getaSaturn(), 50);
		uranus = new CelestialBody(Masses.getmUranus(), 2520757503949.0, 1570287681289.0, -314379999.0, 471704779.0, semiMajorAxis.getaUranus(), 50);
		neptune = new CelestialBody(Masses.getmNeptune(), 4.3447875513657446E12, -1.0836647188150178E12, 1.1168348149336927E8, 4.583540857382406E8, semiMajorAxis.getaNeptune(), 50);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void update() {

		mercury.update(dt);
		/*venus.update(dt);
		earth.update(dt);
		mars.update(dt);
		jupiter.update(dt);
		saturn.update(dt);
		uranus.update(dt);
		neptune.update(dt);
		*/
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

	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		sun.drawPlanet(g);
		g.setColor(Color.GRAY);
		mercury.drawPlanet(g);
		/*g.setColor(Color.ORANGE);
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
		*/
	}
	
}
