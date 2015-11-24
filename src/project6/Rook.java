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
		if(color == black){
			label = "R";
		}
		else{
			label = "r";
		}
	}
	
	public boolean isValidMove(int nx, int ny) {
		
		if(x == nx && y == ny){
			return false;
		}
		
		if(x != nx && y != ny){
			return false;
		}
		
		if(nx < 0 || nx > 7 || ny < 0 || nx > 0){
			return false;
		}
		
		
		if(x == nx){
			if(y > ny){
				for(int yc = y-1; yc >= ny; yc--){
					if(yc == ny){
						if(ChessBoard[x][yc].color != color && !inCheck(nx, ny)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[x][yc] != null){
						return false;
					}
				}
			}
			else{
				for(int yc = y+1; yc <= ny; yc++){
					if(yc == ny){
						if(ChessBoard[x][yc].color != color && !inCheck(nx, ny)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[x][yc] != null){
						return false;
					}
				}
			}
		}

		else{
			if(x > nx){
				for(int xc = x-1; xc >= ny; xc--){
					if(xc == nx){
						if(ChessBoard[nx][y].color != color && !inCheck(nx, ny)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[xc][y] != null){
						return false;
					}
				}
			}
			else{
				for(int xc = x+1; xc <= ny; xc++){
					if(xc == nx){
						if(ChessBoard[nx][y].color != color && !inCheck(nx, ny)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[xc][y] != null){
						return false;
					}
				}
			}
		}
		
		return true;
		
	}

	public void showMoves() {
		
	}
	
	public void castle(){
		
	}

}
