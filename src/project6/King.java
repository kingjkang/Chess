package project6;

public class King extends ChessPiece{

	public King(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 10;
		ChessBoard[x][y] = this;
		if(color == black){
			label = "K";
		}
		else{
			label = "k";
		}
	}
	

	@Override
	public boolean isValidMove(int nx, int ny) {
		
		return false;
	}

	@Override
	public void showMoves() {
		// TODO Auto-generated method stub
		
	}
	

}
