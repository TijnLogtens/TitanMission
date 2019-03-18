package solarSystemModel;

import java.awt.Graphics;

public class Star {
	private double mass;
	private double x;
	private double y;
	private double size;
	
	Star(double mass, double x, double y, double size){
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void drawPlanet(Graphics g){
		g.fillOval((int) (x /1E11), (int) (y/1E11), (int) (size/5E2), (int) (size/5E2)); 
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

