import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/*
 * 
 * This is the painting class of the connect 4 game
 * 
 * 
 */

public class Connect4Pannel extends JPanel {

	public static final int MENU_INDEX = 0;
	public static final int OPTIONS_INDEX = 1;
	public static final int PLAY_INDEX = 2;

	public static final int DELTATIMEMS = 50;

	public static int redWinCount = 0, yellowWinCount = 0;

	MouseHandler mouse;
	State[] states;
	int state = MENU_INDEX;

	public Connect4Pannel() {

		mouse = new MouseHandler();
		this.addMouseListener(mouse);

		states = new State[3];
		states[MENU_INDEX] = new MenuState(this);
		states[OPTIONS_INDEX] = new OptionsState(this);
		states[PLAY_INDEX] = new PlayState(this, mouse);

		Timer t = new Timer(DELTATIMEMS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});

		t.start();

	}

	public void paintComponent(Graphics g) {
		states[state].draw(g);
	}

	public void update() {
		states[state].update();
	}

	public void changeState(int stateID) {
		state = stateID;
	}

	public int getPixelSizeX() {
		return PlayState.getPixelSizeX();
	}

	public int getPixelSizeY() {
		return PlayState.getPixelSizeY();
	}

	public void quit() {
		Connect4.close();
	}
}
