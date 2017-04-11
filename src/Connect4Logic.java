import javax.swing.JOptionPane;

public class Connect4Logic {
	int[][] board;
	private int BoardSizeX = 7, BoardSizeY = 9;
	
	boolean hasRedWon, hasYellowWon, isRedTurn;
	
	
	static final int RED_CHIP_INDEX = 1, YELLOW_CHIP_INDEX = 2;
	
	
	public Connect4Logic(int sizeX, int sizeY)
	{
		BoardSizeX = sizeX;
		BoardSizeY = sizeY;
		
		board = new int[sizeX][sizeY];
		
	}
	
	
	public void resetBoard(){
		hasRedWon = false; 
		hasYellowWon = false;
		
		board = new int[BoardSizeX][BoardSizeY];
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = 0;
			}
		}	
	}
	
	public void makeMove(int collom, boolean isRed){
		if(isRedTurn == isRed){
			if(placeChip(collom, isRed)){
				toggleTurn();
			}
		}
		if(checkWin()){
			hasRedWon = !isRedTurn;
			hasYellowWon = isRedTurn;
		}else{
			if(!isMoveAvailable()){
				JOptionPane.showMessageDialog(null, "No moves available", "Connect4", JOptionPane.PLAIN_MESSAGE);
				resetBoard();
			}
		}
	}
	
	private void toggleTurn(){
		isRedTurn = !isRedTurn;
	}
	
	
	public boolean placeChip(int collom, boolean isRed){
//		if(collom >= BoardSizeX || collom < 0){
//			return false;
//		}
//		
//		if(board[collom][0] != 0){
//			//JOptionPane.showMessageDialog(null, "Cannot Place Chip There", "Connect4", JOptionPane.PLAIN_MESSAGE);
//			
//			return false;
//		}
//		for (int y = 1; y < board[collom].length; y++) {
//			if(board[collom][y] != 0 ){
//				board[collom][y-1] = isRed ? 1 : 2;
//				return true;
//			}
//		}
//		
//		if(board[collom][BoardSizeY-1] == 0){
//			board[collom][BoardSizeY-1] = isRed ? 1 : 2;
//		}
//		
//		return true;
		
		int dropLocation = getColomDropLocation(collom);
		if(dropLocation != -1){
			board[collom][dropLocation] =  isRed ? 1 : 2;
			return true;
		}
		return false;
		
	}
	
	public int getColomDropLocation(int collom){
		if(board[collom][0] != 0 || collom >= BoardSizeX || collom < 0){
			return -1;
		}
		for (int y = 1; y < board[collom].length; y++) {
			if(board[collom][y] != 0 ){
				return y-1;
			}
		}
		if(board[collom][BoardSizeY-1] == 0){
			return BoardSizeY - 1;
		}
		return -1;
	}

	public boolean checkWin(){
		//check horisontaly
		for (int x = 0; x < board.length - 3; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if(board[x][y] != 0){
					if(board[x][y] == board[x+1][y] &&
						board[x][y] == board[x+2][y] &&
						board[x][y] == board[x+3][y] ){
						return true;
					}
				}
			}
		}
		// check verticaly
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length- 3; y++) {
				if(board[x][y] != 0){
					if(board[x][y] == board[x][y+1] &&
						board[x][y] == board[x][y+2] &&
						board[x][y] == board[x][y+3] ){
						return true;
					}
				}
			}
		}
		//check diagonaly
		for (int x = 0; x < board.length - 3; x++) {
			for (int y = 0; y < board[x].length- 3; y++) {
				if(board[x][y] != 0){
					if(board[x][y] == board[x+1][y+1] &&
						board[x][y] == board[x+2][y+2] &&
						board[x][y] == board[x+3][y+3] ){
						System.out.println("diagonalfound 1");
						return true;
					}
				}
			}
		}
		for (int x = 3; x < board.length; x++) {
			for (int y = 0; y < board[x].length- 3; y++) {
				if(board[x][y] != 0){
					if(board[x][y] == board[x-1][y+1] &&
						board[x][y] == board[x-2][y+2] &&
						board[x][y] == board[x-3][y+3] ){
						System.out.println("diagonalfound 2");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean isMoveAvailable()
	{
		for (int i = 0; i < board.length; i++) {
			if(board[i][0] == 0 ){
				return true;
			}
		}
		return false;
	}
	
	
	public int getSizeX()
	{
		return board.length;
	}
	
	public int getSizeY(){
		return board[0].length;
	}
	
	public int getSpace(int x, int y){
		if(x < 0 || x >= board.length){
			return -1;
		}
		if(y < 0 || y >= board[0].length )
			return -1;
		
		return board[x][y];
	}
	
	public boolean hasPlayerWon(){
		return hasRedWon || hasYellowWon;
	}
}
