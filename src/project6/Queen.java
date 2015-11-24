package project6;

public class Queen extends ChessPiece{

	public Queen(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 9;
		ChessBoard[x][y] = this;
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
	
	public String toString(){
		return "Q";
	}

}
