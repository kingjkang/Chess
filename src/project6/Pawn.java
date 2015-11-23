package project6;

public class Pawn extends ChessPiece{
	
	private boolean firstStepDone;

	public Pawn(int nx, int ny, boolean color){
		ChessBoard[nx][ny] = this;
		x = nx;
		y = ny;
		value = 1;
		this.color = color;
		firstStepDone = false;
	}
	
	@Override
	public void move(int nx, int ny) {
		if(isValidMove(nx, ny)){
			ChessBoard[x][y] = null;
			x = nx;
			y = ny;
			ChessBoard[x][y] = this;
		}
	}

	@Override
	public void showMoves(){
	
	}
	
	public String toString(){
		return "P";
	}

	@Override
	public boolean isValidMove(int nx, int ny) {
		
		if(nx > 8 || nx < 0 || ny > 8 || ny < 0){
			return false;
		}
		
		int deltaX = Math.abs(nx - x);
		int deltaY = Math.abs(ny - y);
		if(firstStepDone){
			if(deltaX > 1 || deltaY > 1){
				return false;
			}
		}
		else{
			if(deltaX > 0 || deltaY > 2){
				return false;
			}
		}
		
		/* Black --> goes down" */
		if(color == black){
			if(ny > y){
				/* Check if going down is in bounds */
				if(ny <= 7 && ny >= 2){
					/* Check if moving laterally 
					 * Can't move forward and stay in same x if anyone is in front */
					if(nx == x){
						if(ChessBoard[nx][ny] == null && !inCheck(nx, ny)){
							return true;
						}
						else{
							return false;
						}	
					}
					else{
						if(ChessBoard[nx][ny].color == white){
							
						}
					}
			}
		}
		
		/* White --> goes up" */
		else{
			
		}
		return false;
	}

	public void evolve(String piece){
		
	}
	
}
