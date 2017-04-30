import java.awt.Color;
import java.awt.Graphics;

public class OptionsState extends State{

	Button menuButton;
	
	public OptionsState(Connect4Pannel pannel) {
		super(pannel);
		// TODO Auto-generated constructor stub
		menuButton = new Button(400, 200, 40, 80, "Menu");
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

		g.setColor(Color.blue);
		g.fillRect(0, 0, pannel.getWidth(), pannel.getHeight()); 
		
		menuButton.draw(g, menuButton.isMouseOver(pannel.getMousePosition()));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(pannel.mouse.isMousePressed()){
			
			if(menuButton.isMouseOver(pannel.getMousePosition()))
			{
				pannel.changeState(Connect4Pannel.MENU_INDEX);
			}
		
		}
	}
	
}
