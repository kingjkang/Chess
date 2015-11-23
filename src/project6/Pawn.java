package project6;

public class Pawn extends ChessPiece{

	public Pawn(int row, int col){
		GameBoard[row][col].add(this);
		value = 1;
	}
	
	@Override
	public void move(int row, int col) {

	}

	@Override
	public void showMoves() {
		
	}

	@Override
	public void isValidMove(int row, int col) {
		/* Black --> goes down" */
		if(color){
			
		}
		/* White --> goes up" */
		else{
			
		}
	}

}
