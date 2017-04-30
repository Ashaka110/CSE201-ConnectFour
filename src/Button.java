import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Button {
	
	int x, y, l, w;
	String text;
	
	public Button(int x, int y, int l, int w, String text) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		this.text = text;
	}
	
	
	public void draw(Graphics g, boolean hovering)
	{
		g.setColor(Color.lightGray);
		g.fillRect(x, y, l, w);
		
		if(hovering){
			g.setColor(Color.yellow);
			g.drawRect(x, y, l, w);
		}else{

			g.setColor(Color.gray);
			g.drawRect(x, y, l, w);
		}
		g.setColor(Color.black);
		g.drawString(text, x+(l - g.getFontMetrics().stringWidth(text))/2, y+w/2);
	}
	
	public boolean isMouseOver(Point mouse)
	{
		if(mouse == null)
			return false;
		return mouse.x > x && mouse.y > y && 
				mouse.x < x + l && mouse.y < y + w; 
	}
	
}
