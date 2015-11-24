package project6;

public class Knight extends ChessPiece{

	public Knight(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 3;
		ChessBoard[x][y] = this;
	}
	
	public boolean isValidMove(int nx, int ny) {
		return false;
	}

	public void showMoves() {

	}

}
