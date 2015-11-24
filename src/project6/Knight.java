package project6;

public class Knight extends ChessPiece{

	public Knight(int nx, int ny, boolean color){
		x = nx;
		y = ny;
		this.color = color;
		value = 3;
		ChessBoard[x][y] = this;
		if(color == black){
			label = "H";
		}
		else{
			label = "h";
		}
	}
	
	
	public boolean isValidMove(int nx, int ny) {
		//this checks to see if it is wininn the range/scope of the chessboard
		if(nx > 7 || nx < 0 || ny > 7 || ny < 0){
			return false;
		}
		
		//checking the 8 valid moves 
		if (nx == (x+1) && ny == (y-2)){
			//top right move
			//and check if the move will put the king in check
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x+2) && ny == (y-1)){
			//right right move
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x+2) && ny == (y+1)){
			//right right move
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x+1) && ny == (y+2)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x-1) && ny == (y+2)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x-2) && ny == (y+1)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x-2) && ny == (y-1)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (x-1) && ny == (y-2)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		
		return false;
	}

	public void showMoves() {

	}

}
