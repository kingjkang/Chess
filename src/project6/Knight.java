package project6;

public class Knight extends ChessPiece{

	public Knight(int nx, int ny, boolean color){
		row = nx;
		col = ny;
		this.color = color;
		value = 3;
		ChessBoard[row][col] = this;
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
		if (nx == (row+1) && ny == (col-2)){
			//top right move
			//and check if the move will put the king in check
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row+2) && ny == (col-1)){
			//right right move
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row+2) && ny == (col+1)){
			//right right move
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row+1) && ny == (col+2)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row-1) && ny == (col+2)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row-2) && ny == (col+1)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row-2) && ny == (col-1)){
			if (inCheck(nx, ny)){
				return false;
			}
			return true;
		}
		else if (nx == (row-1) && ny == (col-2)){
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
