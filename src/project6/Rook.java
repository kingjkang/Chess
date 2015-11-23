package project6;

public class Rook extends ChessPiece{

	private boolean firstStepDone;
	private boolean canCastle;
	
	public Rook(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 5;
		ChessBoard[x][y] = this;
	}
	
	@Override
	public void move(int nx, int ny) {
		// TODO Auto-generated method stub
		
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
	
	public void castle(){
		
	}
	
	public String toString(){
		return "R";
	}
}
