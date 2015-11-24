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
	
	public boolean isValidMove(int nx, int ny) {
		
		if(x == nx && y == ny){
			return false;
		}
		
		if(nx < 0 || nx > 7 || ny < 0 || nx > 0){
			return false;
		}
		
		if(color == black){
			if(x == nx){
				
			}
		}
		
		else{
			
		}
		return false;
	}

	public void showMoves() {
		// TODO Auto-generated method stub
		
	}
	
	public void castle(){
		
	}
	
	public String toString(){
		return "R";
	}
}
