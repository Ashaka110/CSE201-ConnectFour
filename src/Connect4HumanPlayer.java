
public class Connect4HumanPlayer extends Connect4Player {

	Connect4Pannel pannel;
	MouseHandler mouse;
	
	public Connect4HumanPlayer(Connect4Logic g, boolean isRed, MouseHandler m, Connect4Pannel p) {
		super(g, isRed);
		mouse = m;
		pannel = p;
	}

	@Override
	public int selectMove() {
		if(mouse.isMousePressed()){
			game.makeMove(pannel.getCollomMouseOver(), isRedPlayer);
		}
		return 0;
	}

}
