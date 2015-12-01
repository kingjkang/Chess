package project6;

import java.util.ArrayList;

public class Rook extends ChessPiece{

	private boolean firstStepDone;
	
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
	
	public boolean canCastle(){
		if(firstStepDone){
			//if king can do it
			return true;
		}
		return false;
	}
	
	public boolean isValidMove(int r, int c) {
		
		
		if(r > 7 || r < 0 || c > 7 || c < 0){
			return false;
		}
		
		if(row == r && col == c){
			return false;
		}
		
		if(ChessBoard[r][c] != null && ChessBoard[r][c].color == color){
			return false;
		}
		
//		if(inCheck(this.row, this.col, r, c)){
//			return false;
//		}
		
		if(inCheck(r, c)){
			return false;
		}
		
		if(row == r){
			if(col > c){
				for(int yc = col-1; yc >= c; yc--){
					if(yc == c){
						if(ChessBoard[row][yc] != null){
							if(ChessBoard[row][yc].color != color){
								return true;
							}
							else{
								return false;
							}
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
						if(ChessBoard[row][yc] != null){
							if(ChessBoard[row][yc].color != color){
								return true;
							}
							else{
								return false;
							}
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
				for(int xc = row-1; xc >= r; xc--){
					if(xc == r){
						if(ChessBoard[xc][col] != null){
							if(ChessBoard[xc][col].color != color){	
								return true;
							}
							else{
								return false;
							}
						}
					}
					else if(ChessBoard[xc][col] != null){
						return false;
					}
				}
			}
			else{
				for(int xc = row+1; xc <= r; xc++){
					if(xc == r){
						if(ChessBoard[xc][col] != null){
							if(ChessBoard[xc][col].color != color){
								return true;
							}
							else{
								return false;
							}
						}
					}
					else if(ChessBoard[xc][col] != null){
						return false;
					}
				}
			}
		}
		return true;
		
		/*
		if(row == r){
			if(col > c){
				for(int yc = col-1; yc >= c; yc--){
					if(yc == c){
						if(ChessBoard[row][yc] != null){
							//if(ChessBoard[row][yc].color != color && !inCheck(this.row, this.col, r, c)){
							if(ChessBoard[row][yc].color != color && !inCheck( r, c)){
								return true;
							}
							else{
								return false;
							}
						}
						else{
							return !inCheck(this.row, this.col, r, c);
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
						if(ChessBoard[row][yc] != null){
							//if(ChessBoard[row][yc].color != color && !inCheck(this.row, this.col, r, c)){
							if(ChessBoard[row][yc].color != color && !inCheck( r, c)){
								return true;
							}
							else{
								return false;
							}
						}
						else{
							return !inCheck(this.row, this.col, r, c);
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
				for(int xc = row-1; xc >= r; xc--){
					if(xc == r){
						if(ChessBoard[xc][col] != null){
							//if(ChessBoard[xc][col].color != color && !inCheck(this.row, this.col, r, c)){
							if(ChessBoard[xc][col].color != color && !inCheck( r, c)){	
								return true;
							}
							else{
								return false;
							}
						}
						else{
							return !inCheck(this.row, this.col, r, c);
						}
					}
					else if(ChessBoard[xc][col] != null){
						return false;
					}
				}
			}
			else{
				for(int xc = row+1; xc <= r; xc++){
					if(xc == r){
						if(ChessBoard[xc][col] != null){
							if(ChessBoard[xc][col].color != color && !inCheck(this.row, this.col, r, c)){
								return true;
							}
							else{
								return false;
							}
						}
						else{
							return !inCheck(this.row, this.col, r, c);
						}
					}
					else if(ChessBoard[xc][col] != null){
						return false;
					}
				}
			}
		}
		return true;
		*/
		
	}

	public void showMoves() {
		
		Moves = new ArrayList<Move>(0);
		
		for(int r = row + 1; r <= 7; r++){
			
			if(ChessBoard[r][col] != null){
				if(isValidMove(r, col)){
					Moves.add(new Move(r, col));
				}
				break;
			}
			if(isValidMove(r, col)){
				Moves.add(new Move(r, col));
			}
		}
		
		for(int r = row - 1; r >= 0; row--){
			if(ChessBoard[r][col] != null){
				if(isValidMove(r, col)){
					Moves.add(new Move(r, col));
				}
				break;
			}
			if(isValidMove(r, col)){
				Moves.add(new Move(r, col));
			}
		}
		
		for(int c = col + 1; c <= 7; c++){
			if(ChessBoard[row][c] != null){
				if(isValidMove(row, c)){
					Moves.add(new Move(row, c));
				}
				break;
			}
			if(isValidMove(row, c)){
				Moves.add(new Move(row, c));
			}		}
		
		for(int c = col - 1; c >= 0; c--){
			if(ChessBoard[row][c] != null){
				if(isValidMove(row, c)){
					Moves.add(new Move(row, c));
				}
				break;
			}
			if(isValidMove(row, col)){
				Moves.add(new Move(row, c));
			}
		}
		
		
	}
	
	public void castle(){
		
	}

}
