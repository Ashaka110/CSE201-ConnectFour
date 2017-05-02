import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Main class, start to start the game
 */
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
		
		
		addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
				  close();
			  }
		});
	}
	
	public static void close()
	{
		System.out.println("ending");
		JOptionPane.showMessageDialog(null, "Yellow has won : "  + Connect4Pannel.yellowWinCount + " times\n Red has won : " + Connect4Pannel.redWinCount + " times" , "Connect4", JOptionPane.PLAIN_MESSAGE);
	    System.exit(0);
	}
}
