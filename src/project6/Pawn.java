package project6;

public class Pawn extends ChessPiece{

	public Pawn(int row, int col, boolean color){
		GameBoard[row][col].add(this);
		value = 1;
		this.color = color;
	}
	
	@Override
	public void move(int row, int col) {
		if(isValidMove(row, col)){
			GameBoard[x][y].remove(this);
			x = row;
			y = col;
			GameBoard[x][y].add(this);
		}
	}

	@Override
	public void showMoves() {
		
	}

	@Override
	public boolean isValidMove(int row, int col) {
		/* Black --> goes down" */
		if(color){
			if(col > y && col != 8){
				
			}
		}
		/* White --> goes up" */
		else{
			
		}
		return false;
	}

}
