
/*
 * The is the human player class
 */
public class Connect4HumanPlayer extends Connect4Player {

	PlayState pannel;
	MouseHandler mouse;

	public Connect4HumanPlayer(Connect4Logic g, boolean isRed, MouseHandler m, PlayState p) {
		super(g, isRed);
		mouse = m;
		pannel = p;
	}

	@Override
	public boolean selectMove() {
		if (mouse.isMousePressed()) {
			return game.makeMove(pannel.getCollomMouseOver(pannel.pannel.getMousePosition()), isRedPlayer);
		}
		return false;
	}

}
