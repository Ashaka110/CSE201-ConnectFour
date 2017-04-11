import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Connect4Pannel extends JPanel{

	static int cellSize = 40;
	static int cellSpacing = 5;
	static int marginSize = cellSize * 3;
	
	boolean isMousePressed, isMouseReleased;
	
	Connect4Player playerRed, playerYellow;
	
	MouseHandler mouse;
	Connect4Logic logic;
	
	public Connect4Pannel() {
		mouse = new MouseHandler();
		this.addMouseListener(mouse);
		
		logic = new Connect4Logic(7, 6);
		
		startup();
		
		playerRed = new Connect4HumanPlayer(logic, true, mouse, this);
		playerYellow = new Connect4AI(logic, false);//new Connect4HumanPlayer(logic, false, mouse, this);//
		
	}
	
	public void startup(){
		logic.resetBoard();
		Timer t  = new Timer(100, new ActionListener(){  
            public void actionPerformed(ActionEvent e) {
                update();
            	repaint();
            }
        });
		
		t.start();
		
			
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, this.getWidth(), getHeight());  
		drawBoard(g);
		
		
		String s = "";
		if(logic.isRedTurn){
			g.setColor(Color.red);
			s = "  Red's Turn";
		}else{
			g.setColor(Color.YELLOW);			
			s = "Yellow's Turn";
		}
		g.fillOval(40 *7 + 35, 40, 40, 40);
		g.drawString(s, 40 *7 + 20, 100);
		
		
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
	
	public void drawBoard(Graphics g){
		for (int x = 0; x < logic.getSizeX(); x++) {
			for (int y = 0; y < logic.getSizeY(); y++) {
				if(logic.getSpace(x, y) == Connect4Logic.RED_CHIP_INDEX){
					g.setColor(Color.red);
					g.fillOval( 5+ x * (cellSize + cellSpacing), y * (cellSize + cellSpacing), cellSize, cellSize);
				}
				if(logic.getSpace(x, y) == Connect4Logic.YELLOW_CHIP_INDEX){
					g.setColor(Color.YELLOW);
					g.fillOval(5+ x * (cellSize + cellSpacing), y * (cellSize + cellSpacing), cellSize, cellSize);
				}
				
				g.setColor(Color.black);
				
				if(getCollomMouseOver() == x){
					if(logic.getSpace(x, 0) == 0)
						g.setColor(Color.yellow);
				}
				g.drawOval(5+x * (cellSize + cellSpacing), y * (cellSize + cellSpacing), cellSize, cellSize);
				
				
			}
		}
	}
	
	
	public void update(){

		//if(!logic.isRedTurn && !logic.hasPlayerWon()){
		//	logic.makeMove(Connect4AI.selectMove(logic.board), logic.isRedTurn);
		//}
		if(logic.hasPlayerWon()){
			if(mouse.isMousePressed()){
				logic.resetBoard();
			}
		}else if(logic.isRedTurn){
			playerRed.selectMove();
			//logic.makeMove(getCollomMouseOver(), logic.isRedTurn);
		}else if(!logic.isRedTurn){
			playerYellow.selectMove();
		}
		//}
		
		
	}
	
	
	
	
	
	public int getCollomMouseOver(){
		Point mousepos = getMousePosition();
		if(mousepos != null){
			int mouseX = mousepos.x;// MouseInfo.getPointerInfo().getLocation().x;
			return mouseX / (cellSize+cellSpacing);
		}
		return -1;
	}
	
	public int getPixelSizeX(){
		return (Connect4Pannel.cellSpacing + Connect4Pannel.cellSize) * (logic.getSizeY()+1)  + Connect4Pannel.marginSize;
	}
	public int getPixelSizeY(){
		return (Connect4Pannel.cellSpacing + Connect4Pannel.cellSize)* (logic.getSizeY()+1);
	}
}
