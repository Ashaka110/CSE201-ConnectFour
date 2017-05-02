/*
 * The is the super class for the AI and human player classes
 */
public abstract class Connect4Player {

	Connect4Logic game;
	boolean isRedPlayer;

	public Connect4Player(Connect4Logic g, boolean isRed) {
		game = g;
		isRedPlayer = isRed;
	}

	public abstract boolean selectMove();

}
