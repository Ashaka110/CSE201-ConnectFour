import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{
	
	boolean mousePress;
	
	
	public MouseHandler(){
			
	}
	
	public boolean isMousePressed(){
		if(mousePress)
		{
			mousePress = false;
			return true;
		}
		
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mousePress = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
