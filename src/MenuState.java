import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuState extends State{

	Button playButton, optionsButton, quitButton;
	BufferedImage titleImage;
	
	public MenuState(Connect4Pannel pannel) {
		super(pannel);
		// TODO Auto-generated constructor stub

		Point buttonOffset = new Point(	40, 200);
		playButton = new Button(	buttonOffset.x + 40, buttonOffset.y + 40, 80, 40, "Play");
		optionsButton = new Button( buttonOffset.x + 140,buttonOffset.y + 40, 80, 40, "Options");
		quitButton = new Button(    buttonOffset.x + 240,buttonOffset.y + 40, 80, 40, "Quit");
		
		try {
			titleImage = ImageIO.read(new File("Title.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, pannel.getPixelSizeX(), pannel.getPixelSizeY());
		
		playButton.draw(g, playButton.isMouseOver(pannel.getMousePosition()));
		optionsButton.draw(g, optionsButton.isMouseOver(pannel.getMousePosition()));
		quitButton.draw(g, quitButton.isMouseOver(pannel.getMousePosition()));
		
		g.drawImage(titleImage, 20, 20, 879/2, 196/2, null);
	}

	@Override
	public void update() {
		if(pannel.mouse.isMousePressed()){
			
			if(playButton.isMouseOver(pannel.getMousePosition()))
			{
				pannel.changeState(Connect4Pannel.PLAY_INDEX);
			}
			else if(optionsButton.isMouseOver(pannel.getMousePosition()))
			{
				pannel.changeState(Connect4Pannel.OPTIONS_INDEX);
			}
			else if(quitButton.isMouseOver(pannel.getMousePosition()))
			{
				pannel.quit();
			}
		}
	}
	
}
