package solarSystemModel;

import java.awt.Graphics;

public class Star {
	private double mass;
	private double x;
	private double y;
	private double size;
	private final int DISTANCE_SCALER = (int)1E9;
	private final int SIZE_SCALER = (int)5E7;

	Star(double mass, double x, double y, double size){
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void drawPlanet(Graphics g){

		g.fillOval((int) (x /DISTANCE_SCALER) + 500-(int)((size/SIZE_SCALER)/2), (int) (y/DISTANCE_SCALER) + 500-(int)((size/SIZE_SCALER)/2), (int) (size/SIZE_SCALER), (int) (size/SIZE_SCALER));
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

