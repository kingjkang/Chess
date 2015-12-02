package project6;

import java.util.ArrayList;

public class Bishop extends ChessPiece{
	
	private static int upRight = 1;
	private static int upLeft = 2;
	private static int downLeft = 3;
	private static int downRight = 4;

	public Bishop(int r, int c, boolean color){
		row = r;
		col = c;
		this.color = color;
		value = 3;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "B";
		}
		else{
			label = "b";
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
		
//		if(inCheck(this.row, this.col, r, c)){
//			return false;
//		}
		
		if(inCheck(r, c)){
			return false;
		}
		
		int deltaR = Math.abs(row - r);
		int deltaC = Math.abs(col - c);
		int direction = 0;
		
		if(deltaR != deltaC){
			return false;
		}
		
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

	public void showMoves() {
		
		Moves = new ArrayList<Move>(0);
		
		int rr;
		int cc;
		
		rr = row - 1;
		cc = col + 1;
		while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
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
		
		rr = row - 1;
		cc = col - 1;
		while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
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
		
		rr = row + 1;
		cc = col - 1;
		while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
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
		
		rr = row + 1;
		cc = col + 1;
		while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
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
