//package solarSystemModel;

import java.awt.Graphics;

public class Star extends CelestialBody {

	private static final int DISTANCE_SCALER = (int)1E9;
	private static final int SIZE_SCALER = (int)5E7;

	public Star(double mass, double x, double y, double z, double sma, double size){
		super(mass, x, y, z, 0, 0, 0, sma, size);
	}
}