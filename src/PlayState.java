import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayState extends State{

	static int cellSize = 57;
	static int cellSpacing = 6;
	static int marginSize = cellSize * 3, 
			xOffset = 22, yOffset = 17;
	
	
	boolean isMousePressed, isMouseReleased;
	
	Connect4Player playerRed, playerYellow;
	
	MouseHandler mouse;
	Connect4Logic logic;
	
	Button menuButton;
	
	BufferedImage yellowChip, redChip, boardImage;
	int fallingChipY;
	int fallSpeed = 400;
	boolean falling;
	
	
	public PlayState (Connect4Pannel pannel, MouseHandler m) {
		super(pannel);
		mouse = m;
		
		logic = new Connect4Logic(7, 6);
		logic.resetBoard();
		
		playerRed = new Connect4HumanPlayer(logic, true, mouse, this);
		playerYellow = new Connect4AI(logic, false);//new Connect4HumanPlayer(logic, false, mouse, this);//
		
		menuButton = new Button(500, 200, 40, 80, "Menu");
		
		try {
			yellowChip = ImageIO.read(new File("yellow_chip.png"));
			redChip = ImageIO.read(new File("red_chip.png"));
			boardImage = ImageIO.read(new File("Square.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, pannel.getWidth(), pannel.getHeight());  
		

		drawFallingChip(g);
		drawBoard(g);
		drawUI(g);
		
		
		menuButton.draw(g, menuButton.isMouseOver(pannel.getMousePosition()));
		
	}
	BufferedImage getChipImage(boolean isRed){
		if(isRed){
			return redChip;
		}
		return yellowChip;
	}
	public void updateFallingChip(float delta)
	{
		Point move = logic.GetLastMove();
		
		if(falling){
			float fallDistance = delta * fallSpeed;
			if(fallingChipY + fallDistance < move.y * (cellSize + cellSpacing) + yOffset){
				fallingChipY += fallDistance;
			}else{
				fallingChipY = move.y * (cellSize + cellSpacing) + yOffset;
				falling = false;
			}
			
		}
	}
	public void drawFallingChip(Graphics g)
	{
		Point move = logic.GetLastMove();
		
		if(move.x == -1 && move.y == -1){
			return;
		}
		
		g.drawImage(getChipImage(logic.getSpace(move.x, move.y)==1), 5+ move.x * (cellSize + cellSpacing) + xOffset, fallingChipY, null);//(x, y, width, height);
		//g.fillRect(5+ move.x * (cellSize + cellSpacing) + xOffset, fallingChipY, 60, 60);
		
	}
	
	public void drawBoard(Graphics g){
		
		Point lastMove = logic.GetLastMove();
		
		for (int x = 0; x < logic.getSizeX(); x++) {
			for (int y = 0; y < logic.getSizeY(); y++) {
				if(x != lastMove.x || y != lastMove.y){
					if(logic.getSpace(x, y) == Connect4Logic.RED_CHIP_INDEX){
						g.setColor(Color.red);
						g.drawImage(redChip, 5+ x * (cellSize + cellSpacing) + xOffset, y * (cellSize + cellSpacing) + yOffset, null);
						//g.fillOval( 5+ x * (cellSize + cellSpacing), y * (cellSize + cellSpacing), cellSize, cellSize);
					}
					if(logic.getSpace(x, y) == Connect4Logic.YELLOW_CHIP_INDEX){
						g.setColor(Color.YELLOW);
						g.drawImage(yellowChip, 5+ x * (cellSize + cellSpacing) + xOffset, y * (cellSize + cellSpacing) + yOffset, null);//(x, y, width, height);
						//g.fillOval(5+ x * (cellSize + cellSpacing), y * (cellSize + cellSpacing), cellSize, cellSize);
					}
				}
		//	}
		//}

		//g.drawImage(boardImage, 0, 0, null);
		
		//for (int x = 0; x < logic.getSizeX(); x++) {
		//	for (int y = 0; y < logic.getSizeY(); y++) {
				g.drawImage(boardImage, 5+x * (cellSize + cellSpacing) + xOffset - cellSpacing/2, y * (cellSize + cellSpacing) + yOffset- cellSpacing/2, cellSize + cellSpacing, cellSize + cellSpacing, null);
				
				g.setColor(Color.black);
				
				if(getCollomMouseOver(pannel.getMousePosition()) == x){
					if(logic.getSpace(x, 0) == 0)
						g.setColor(Color.yellow);
				}
				Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(1));
				
                g2.drawOval(5+x * (cellSize + cellSpacing) + xOffset, y * (cellSize + cellSpacing) + yOffset, cellSize, cellSize);
				
				
			}
		}
	}
	
	public void drawUI(Graphics g){
		String s = "";
		if(logic.isRedTurn){
			g.setColor(Color.red);
			s = "  Red's Turn";
		}else{
			g.setColor(Color.YELLOW);			
			s = "Yellow's Turn";
		}
		g.fillOval(cellSize *8 + 35, 40, 40, 40);
		g.drawString(s, cellSize *8 + 20, 100);
		
		
		if(logic.hasRedWon || logic.hasYellowWon) {

			int centerx = cellSize * logic.getSizeX()/2 + marginSize/2;
			int centery = cellSize * logic.getSizeY()/2;
			
			g.setColor(Color.blue);
			g.fillRect(centerx - 140/2, centery - 50/2, 140, 50);
			
			if(logic.hasYellowWon){
				s = "Yellow Wins!";
				g.setColor(Color.YELLOW);
				
			}if (logic.hasRedWon){
				s = " Red Wins!";
				g.setColor(Color.red);
			}
					
			
			g.drawRect(centerx - 120/2, centery - 30/2, 120, 30);
			g.drawString(s, centerx - 30, centery + 5);
		}
	
	}

	public void update(){
		
		if(pannel.mouse.mousePress){
			
			if(menuButton.isMouseOver(pannel.getMousePosition()))
			{
				pannel.changeState(Connect4Pannel.MENU_INDEX);
			}
		
		}
		
		updateFallingChip((float)(Connect4Pannel.DELTATIMEMS)/1000);
		//if(!logic.isRedTurn && !logic.hasPlayerWon()){
		//	logic.makeMove(Connect4AI.selectMove(logic.board), logic.isRedTurn);
		//}
		if(logic.hasPlayerWon()){
			if(mouse.isMousePressed()){
				logic.resetBoard();
				
			}
		}else if(logic.isRedTurn && !falling){
			if(playerRed.selectMove()){
				setUpChipFall();
			}
			if(logic.hasRedWon){
				Connect4Pannel.redWinCount++;
				System.out.println("red wins: " + Connect4Pannel.redWinCount);
			}
		}else if(!logic.isRedTurn && !falling){
			if(playerYellow.selectMove()){
				setUpChipFall();
			}
			if(logic.hasYellowWon){
				Connect4Pannel.yellowWinCount++;
				System.out.println("red wins: " + Connect4Pannel.redWinCount);
			}
		}else if(pannel.mouse.isMousePressed()){}
		//}
		
		
		
	}
	
	void setUpChipFall()
	{
		fallingChipY = 0;
		falling = true;
	}
	
	
	void reset(){
		
	}
	
	
	public int getCollomMouseOver(Point mousepos){
		//Point mousepos = getMousePosition();
		if(mousepos != null){
			int mouseX = mousepos.x;// MouseInfo.getPointerInfo().getLocation().x;
			return (mouseX- xOffset) / (cellSize+cellSpacing) ;
		}
		return -1;
	}
	
	public static int getPixelSizeX(){
		return (cellSpacing + cellSize) * (7+1)  + marginSize;
	}
	public static int getPixelSizeY(){
		return (cellSpacing + cellSize)* (7+1);
	}

}
