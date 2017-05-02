import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Button {
	
	int x, y, l, w;
	String text;
	Color fillColor, outlineColor, textColor, hoverFillColor, hoverOutlineColor, hoverTextColor;
	
	public Button(int x, int y, int l, int w, String text) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		this.text = text;
		fillColor = Color.lightGray;
		outlineColor = Color.gray;
		textColor = Color.black;
		hoverFillColor = Color.lightGray;
		hoverOutlineColor = Color.yellow;
		hoverTextColor = Color.black;
	}
	
	public Button(int x, int y, int l, int w, String text, Color c) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		this.text = text;
		fillColor = c;
		outlineColor = Color.gray;
		textColor = Color.black;
		hoverFillColor = Color.lightGray;
		hoverOutlineColor = Color.yellow;
		hoverTextColor = Color.black;
	}
	
	public Button(int x, int y, int l, int w, String text, Color c, Color hoverColor) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		this.text = text;
		fillColor = c;
		outlineColor = Color.gray;
		textColor = Color.black;
		hoverFillColor = hoverColor;
		hoverOutlineColor = Color.yellow;
		hoverTextColor = Color.black;
	}
	public Button(int x, int y, int l, int w, String text, Color c1, Color c2, Color c3, Color c4, Color c5, Color c6) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		this.text = text;
		fillColor = c1;
		outlineColor = c2;
		textColor = c3;
		hoverFillColor = c4;
		hoverOutlineColor = c5;
		hoverTextColor = c6;
	}
	
	public void draw(Graphics g, boolean hovering)
	{
		
		
		if(hovering){
			g.setColor(hoverFillColor);
			g.fillRect(x, y, l, w);
			
			g.setColor(hoverOutlineColor);
			g.drawRect(x, y, l, w);
		}else{
			g.setColor(fillColor);
			g.fillRect(x, y, l, w);
			
			g.setColor(outlineColor);
			g.drawRect(x, y, l, w);
		}
		g.setColor(textColor);
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
