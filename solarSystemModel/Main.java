package solarSystemModel;

import javax.swing.JFrame;
import java.awt.Color;

public class Main {

	public static void main(String[] args) {


		JFrame frame = new JFrame();
		frame.setContentPane(new solarSystemPanel());
		frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
	}

}
