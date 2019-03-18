package solarSystemModel;

public class Main {

	public static void main(String[] args) {
		solarSystemPanel ree = new solarSystemPanel();
		for(int i = 0; i < 50; i++) {
			ree.update();
			System.out.println("Pos Sun: " + ree.sun.getX());
			System.out.println("Pos Neptune: " + ree.neptune.getX());
		}
		
	}

}
