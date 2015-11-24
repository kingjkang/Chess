package project6;

public class Bishop extends ChessPiece{

	public Bishop(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 3;
		ChessBoard[x][y] = this;
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
	
	public String toString(){
		return label;
	}

}
