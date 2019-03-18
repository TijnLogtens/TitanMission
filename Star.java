package solarSystemModel;

public class Star {
	public double mass;
	public double x;
	public double y;
	
	Star(double mass, double x, double y){
		this.mass = mass;
		this.x = x;
		this.y = y;
	}

	public double getMass() {
		return mass;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
}

