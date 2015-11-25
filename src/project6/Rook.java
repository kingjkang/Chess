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
		
		Moves = new ArrayList<Move>(0);
		
		int r = Math.floorMod(row + 1, 8);		
		while(r != row){
			if(ChessBoard[r][col] != null){
				if(isValidMove(r, col)){
					Moves.add(new Move(r, col));
				}
				break;
			}
			Moves.add(new Move(r, col));
			r = Math.floorMod(r + 1, 8);
		}
		
		r = Math.floorMod(row + 1, 8);
		while(r != row){
			if(ChessBoard[r][col] != null){
				if(isValidMove(r, col)){
					Moves.add(new Move(r, col));
				}
				break;
			}
			Moves.add(new Move(r, col));
			r = Math.floorMod(r - 1, 8);
		}
		
		int c = Math.floorMod(col + 1, 8);
		while(c != row){
			if(ChessBoard[row][c] != null){
				if(isValidMove(row, c)){
					Moves.add(new Move(row, c));
				}
				break;
			}
			Moves.add(new Move(row, c));
			c = Math.floorMod(c + 1, 8);
		}
		
		while(c != row){
			if(ChessBoard[row][c] != null){
				if(isValidMove(row, c)){
					Moves.add(new Move(row, c));
				}
				break;
			}
			Moves.add(new Move(row, c));
			c = Math.floorMod(c - 1, 8);
		}
		
	}
	
	public void castle(){
		
	}

}
