import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class DrawPlanet extends JPanel{

	List<Planet> planets; 

	public DrawPlanet(Planet venus, Planet earth, Planet mars, Planet jupyter, Planet saturn, Planet urinus, Planet neptun){
		planets = new ArrayList<>();
		planets.add(venus);
		planets.add(earth);
		planets.add(mars);
		planets.add(jupyter);
		planets.add(saturn);
		planets.add(urinus);
		planets.add(neptun);

		Timer timer = new Timer(40, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(Planet pl: planets){
					pl.update();
					repaint();
				}
			}

		});
		timer.start();
	}

	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Planet pl: planets){
			pl.drawPlanet(g);
		}
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new DrawPlanet(new Planet(100,100,20,100,100,45),new Planet(130,130,20,130,130,90),new Planet(160,160,20,160,160,120),new Planet(200,200,20,200,200,130),new Planet(240,240,20,240,240,60),new Planet(280,280,20,280,280,70),new Planet(320,320,20,320,320,90)));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });
	}

	 static class Planet{
	double x,y; 
	int size; 
	int a;
	int b;
	double theta;
	int rad = 5;
	public Planet(int x, int y, int size, int a, int b, double theta){
		this.x = x; 
		this.y = y;
		this.size = size; 
		this.a = a; 
		this.b = b;
		this.theta = theta;
	}

	public void drawPlanet(Graphics g){
		g.fillOval((int) x, (int) y, size, size); 
	}

	public void update(){
		//theta is the angle, x and y the coordinates, a and b the center of the circle
		theta = theta + Math.toRadians(45);
        x = a + rad * Math.cos(theta);
        y = b + rad * Math.sin(theta); 
	}
}


}
