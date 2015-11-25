package project6;

public class Bishop extends ChessPiece{

	public Bishop(int nx, int ny, boolean color){
		row = nx;
		col = ny;
		this.color = color;
		value = 3;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "B";
		}
		else{
			label = "b";
		}
	}
	

	@Override
	public boolean isValidMove(int nx, int ny) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showMoves() {
		// TODO Auto-generated method stub
		
	}
	
}
