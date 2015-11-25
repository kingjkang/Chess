package project6;

public class Rook extends ChessPiece{

	private boolean firstStepDone;
	private boolean canCastle;
	
	public Rook(int r, int c, boolean color){
		row = r;
		col = c;
		this.color = color;
		value = 5;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "R";
		}
		else{
			label = "r";
		}
	}
	
	public boolean isValidMove(int r, int c) {
		
		if(row == r && col == c){
			return false;
		}
		
		if(row != r && col != c){
			return false;
		}
		
		if(r < 0 || r > 7 || c < 0 || r > 0){
			return false;
		}
		
		
		if(row == r){
			if(col > c){
				for(int yc = col-1; yc >= c; yc--){
					if(yc == c){
						if(ChessBoard[row][yc].color != color && !inCheck(r, c)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[row][yc] != null){
						return false;
					}
				}
			}
			else{
				for(int yc = col+1; yc <= c; yc++){
					if(yc == c){
						if(ChessBoard[row][yc].color != color && !inCheck(r, c)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[row][yc] != null){
						return false;
					}
				}
			}
		}

		else{
			if(row > r){
				for(int xc = row-1; xc >= c; xc--){
					if(xc == r){
						if(ChessBoard[xc][col].color != color && !inCheck(r, c)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[xc][col] != null){
						return false;
					}
				}
			}
			else{
				for(int xc = row+1; xc <= c; xc++){
					if(xc == r){
						if(ChessBoard[xc][col].color != color && !inCheck(r, c)){
							return true;
						}
						else{
							return false;
						}
					}
					else if(ChessBoard[xc][col] != null){
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
