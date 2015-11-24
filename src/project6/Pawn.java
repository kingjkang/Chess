package project6;

public class Pawn extends ChessPiece{
	
	private boolean firstStepDone;

	public Pawn(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 5;
		ChessBoard[x][y] = this;
		firstStepDone = false;
	}
	
	public void showMoves(){
		
	}
	
	public String toString(){
		return "P";
	}

	public boolean isValidMove(int nx, int ny) {
		
		if(nx > 7 || nx < 0 || ny > 7 || ny < 0){
			return false;
		}
		
		int deltaX = Math.abs(nx - x);
		int deltaY = Math.abs(ny - y);
		
		if(deltaX == 0 && deltaY == 0){
			return false;
		}
		
		if(firstStepDone){
			if(deltaX > 1 || deltaY > 1){
				return false;
			}
		}
		else{
			if(deltaX > 1 || deltaY > 2){
				return false;
			}
		}
		
		/* Black --> goes down" */
		if(color == black){
			if(ny > y){
				/* Can't move forward and stay in same x if anyone is in front */
				if(nx == x){
					if(ChessBoard[nx][ny] == null && !inCheck(nx, ny)){
						return true;
					}
					else{
						return false;
					}	
				}
				else{
					if(ChessBoard[nx][ny].color == white && !inCheck(nx, ny)){
						return true;
					}
					else{
						return false;
					}
				}
			}
			else{
				return false;
			}
		}
		
		/* White --> goes up" */
		else{
			if(ny < y){
				/* Can't move forward and stay in same x if anyone is in front */
				if(nx == x){
					if(ChessBoard[nx][ny] == null && !inCheck(nx, ny)){
						return true;
					}
					else{
						return false;
					}	
				}
				else{
					if(ChessBoard[nx][ny].color == white && !inCheck(nx, ny)){
						return true;
					}
					else{
						return false;
					}
				}
			}
			else{
				return false;
			}
		}
	}

	public void evolve(String piece){
		
	}
	
}
