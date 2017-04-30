import java.awt.Graphics;

public abstract class State {
	
	public Connect4Pannel pannel;
	
	public State(Connect4Pannel pannel) {
		this.pannel = pannel;
	}
	
	public abstract void draw(Graphics g);
	public abstract void update();
}
