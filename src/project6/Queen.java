package project6;

import java.util.ArrayList;

public class Queen extends ChessPiece{
	
	private static int upRight = 1;
	private static int upLeft = 2;
	private static int downLeft = 3;
	private static int downRight = 4;

	public Queen(int r, int c, boolean color){
		row = r;
		col = c;
		this.color = color;
		value = 9;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "Q";
		}
		else{
			label = "q";
		}
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
		
		
		if(inCheck(r, c)){
			return false;
		}
		
		int deltaR = Math.abs(row - r);
		int deltaC = Math.abs(col - c);
		int direction = 0;
		
		if(deltaR != deltaC && (deltaR == 0 || deltaC == 0)){
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

		}
		else if(deltaR == deltaC){
			
			deltaR = r - row;
			deltaC = c - col;
			
			int rr = row;
			int cc = col;
			if(deltaR < 0 && deltaC > 0){
				direction = upRight;
				rr--;
				cc++;
			}
			else if(deltaR < 0 && deltaC < 0){
				direction = upLeft;
				rr--;
				cc--;
			}
			else if(deltaR > 0 && deltaC < 0){
				direction = downLeft;
				rr++;
				cc--;
			}
			else{
				direction = downRight;
				rr++;
				cc++;
			}
			
			if(direction == upRight){
				while(!(rr == r && cc == c)){
					if(ChessBoard[rr][cc] != null){
						return false;
					}
					rr--;
					cc++;
				}
				
			}
			else if(direction == upLeft){
				while(!(rr == r && cc == c)){
					if(ChessBoard[rr][cc] != null){
						return false;
					}
					rr--;
					cc--;
				}
			}
			else if(direction == downLeft){
				while(!(rr == r && cc == c)){
					if(ChessBoard[rr][cc] != null){
						return false;
					}
					rr++;
					cc--;
				}
			}
			else{
				while(!(rr == r && cc == c)){
					if(ChessBoard[rr][cc] != null){
						return false;
					}
					rr++;
					cc++;
				}
			}
			
			return true;
			
		}
		
		return false;
		
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
		
		for(int r = row - 1; r >= 0; r--){
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
			}		
		}
		
		for(int c = col - 1; c >= 0; c--){
			if(ChessBoard[row][c] != null){
				if(isValidMove(row, c)){
					Moves.add(new Move(row, c));
				}
				break;
			}
			if(isValidMove(row, c)){
				Moves.add(new Move(row, c));
			}
		}
			
		
		int rr;
		int cc;
		
		rr = row;
		cc = col;
		while(rr >= 0 && cc <= 7){
			if(ChessBoard[rr][cc] != null){
				if(isValidMove(rr, cc)){
					Moves.add(new Move(rr, cc));
				}
				break;
			}
			if(isValidMove(rr, cc)){
				Moves.add(new Move(rr, cc));
			}
			rr--;
			cc++;
		}
		
		rr = row;
		cc = col;
		while(rr >= 0 && cc <= 7){
			if(ChessBoard[rr][cc] != null){
				if(isValidMove(rr, cc)){
					Moves.add(new Move(rr, cc));
				}
				break;
			}
			if(isValidMove(rr, cc)){
				Moves.add(new Move(rr, cc));
			}
			rr--;
			cc--;
		}
		
		rr = row;
		cc = col;
		while(rr >= 0 && cc <= 7){
			if(ChessBoard[rr][cc] != null){
				if(isValidMove(rr, cc)){
					Moves.add(new Move(rr, cc));
				}
				break;
			}
			if(isValidMove(rr, cc)){
				Moves.add(new Move(rr, cc));
			}
			rr++;
			cc--;
		}
		
		rr = row;
		cc = col;
		while(rr >= 0 && cc <= 7){
			if(ChessBoard[rr][cc] != null){
				if(isValidMove(rr, cc)){
					Moves.add(new Move(rr, cc));
				}
				break;
			}
			if(isValidMove(rr, cc)){
				Moves.add(new Move(rr, cc));
			}
			rr++;
			cc++;
		}
		
	}
	

}
