package solarSystemModel;

public class solarSystemPanel implements Runnable{
	
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
	
	//Variables
	private float dt = 0.02f;
	
	public solarSystemPanel() {
		init();
	}
	
	public void init() {
		//TODO initialise planets and sun
		//(double mass, double x, double y, double vy, double vx, double a)
		sun = new Star(Masses.getmSun(), 0, 0);
		mercury = new CelestialBody(Masses.getmMercury(), -58433206319.0, -21438121768.0, 578326455.0, -3769433655.0, semiMajorAxis.getaMercury());
		venus = new CelestialBody(Masses.getmVenus(), -2580494883.0, -108702671108.0, 3004800124.0, -83049933.0, semiMajorAxis.getaVenus());
		earth = new CelestialBody(Masses.getmEarth(), -149012983094.0, -2126426567.0, -5418387.0, -2582093185.0, semiMajorAxis.getaEarth());
		mars = new CelestialBody(Masses.getmMars(), 23243203038.0, 231502807159.0, -2003886166.0, 387018894.0, semiMajorAxis.getaMars());
		jupiter = new CelestialBody(Masses.getmJupiter(), -235676200292.0, -761012101163.0, 1065639299.0, -281044438.0, semiMajorAxis.getaJupiter());
		saturn = new CelestialBody(Masses.getmSaturn(), 354764402704.0, -1461969639484.0, 766191189.0, 194147400.0, semiMajorAxis.getaSaturn());
		uranus = new CelestialBody(Masses.getmUranus(), 2520757503949.0, 1570287681289.0, -314379999.0, 471704779.0, semiMajorAxis.getaUranus());
		neptune = new CelestialBody(Masses.getmNeptune(), 434484939286.0, -1083680143147.0, 111685071.0, 458360609.0, semiMajorAxis.getaNeptune());
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

}
