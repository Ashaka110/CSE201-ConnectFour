import java.util.Random;

public class Connect4AI extends Connect4Player{
	
	public Connect4AI(Connect4Logic g,  boolean isRed) {
		super(g, isRed);
	}

	//returns collom numer
	public int selectMove(){
		
		int t = findWinningSpot();
		if(t != -1){
			game.makeMove(t, isRedPlayer);
		}else if((t = findOpponentWinLocation()) != -1){
			game.makeMove(t, isRedPlayer);
		}else if((t = anticipateTraps()) != -1){
			game.makeMove(t, isRedPlayer);
		}else{
			
			
			
			Random rand = new Random();
			game.makeMove(rand.nextInt(game.board.length), isRedPlayer);
		}
		return 0;
	}
	
	int anticipateTraps(){
		for (int x = 0; x < game.board.length; x++) {
			int y = game.getColomDropLocation(x);
			if(game.getSpace(x, y) == 0){
				int col = -1;
				
				game.board[x][y] = getOppontentChipIndex();
				
				if(detectNoWinScenario()){
					col = x;
				}
				
				game.board[x][y] = 0;
				if(col != -1)
				return x;
			}
		}
		for (int x = 0; x < game.board.length; x++) {
			int y = game.getColomDropLocation(x);
			if(game.getSpace(x, y) == 0){
				int col = -1;
				
				game.board[x][y] = getOppontentChipIndex();
				
				if(detecttwostory()){
					col = x;
				}
				
				game.board[x][y] = 0;
				if(col != -1)
				return x;
			}
		}
		return -1;
	}
	boolean detectNoWinScenario(){
		int opponentWinCount = 0;
		for (int x = 0; x < game.board.length; x++) {
			int y = game.getColomDropLocation(x);
			if(game.getSpace(x, y) == 0){
				game.board[x][y] = getOppontentChipIndex();
				boolean opponentwinningSpot = game.checkWin(); 
				game.board[x][y] = 0;
				
				if(opponentwinningSpot){
					opponentWinCount++;
				}
				
			}
		}
		//if(opponentWinCount > 0){
			System.out.println(opponentWinCount);
		//}
		return opponentWinCount > 1;
	}
	boolean detecttwostory(){
		for (int x = 0; x < game.board.length; x++) {
			int y = game.getColomDropLocation(x);
			if(game.getSpace(x, y) == 0 && game.getSpace(x, y -1) ==0){
				game.board[x][y] = isRedPlayer ? game.RED_CHIP_INDEX : game.YELLOW_CHIP_INDEX;
				game.board[x][y-1] =  getOppontentChipIndex();
				
				boolean opponentwinningSpot = game.checkWin();
				game.board[x][y-1] =  0;
				
				game.board[x][y] = 0;
				
				if(opponentwinningSpot){
					return true;
				}
				
			}
		}
		return false;
	}
	
	int findWinningSpot(){
		for (int x = 0; x < game.board.length; x++) {
			int y = game.getColomDropLocation(x);
				if(game.getSpace(x, y) == 0){
					game.board[x][y] = isRedPlayer ? game.RED_CHIP_INDEX : game.YELLOW_CHIP_INDEX;
					boolean playerwinningSpot = game.checkWin(); 
					game.board[x][y] = 0;
					
					if(playerwinningSpot){
						if(isValadMoveLocation(x, y)){
							return x;
						}
					}
					
				}
			}
		
		return -1;
	}
	
	int findOpponentWinLocation(){
		for (int x = 0; x < game.board.length; x++) {
			int y = game.getColomDropLocation(x);
			if(game.getSpace(x, y) == 0){
				game.board[x][y] = getOppontentChipIndex();
				boolean opponentwinningSpot = game.checkWin(); 
				game.board[x][y] = 0;
				
				if(opponentwinningSpot){
					if(isValadMoveLocation(x, y)){
						return x;
						//game.makeMove(x, isRedPlayer);
						//return true;
					}
				}
				
			}
		}
		
		return -1;
	}
	
	boolean isValadMoveLocation(int x, int y){
		//if space below is not empty and space is empty
		if(game.getSpace(x, y+1) !=0 && game.getSpace(x, y) == 0 ){
			return true;
		}
		return false;
	}
	
	int getOppontentChipIndex(){
		return isRedPlayer ? game.YELLOW_CHIP_INDEX : game.RED_CHIP_INDEX;
	}
	
}
