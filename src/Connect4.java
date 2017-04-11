import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Connect4 extends JFrame{
	
	public static void main(String[] args) {
		JFrame frame = new Connect4();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public Connect4() {
		Connect4Pannel pannel = new Connect4Pannel();
		this.add(pannel);
		setSize(pannel.getPixelSizeX(), pannel.getPixelSizeY() );
	}
}
