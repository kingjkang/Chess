package project6;
/* GAME PROJECT <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * <Anurag Andoji>
 * <aka888>
 * <16340>
 * <Student2 Justin Kang>
 * <jk36542>
 * <16340>
 * Slip days used: <0>
 * Fall 2015
 */

import java.util.ArrayList;

import javafx.scene.image.Image;

/* Extension of ChessPiece
 * All the moves descriptions are same
 * but some are overridden
 * The queen will use the bishops move code as well */
public class Bishop extends ChessPiece{
	
	Image blackBishop;
	Image whiteBishop;
	
	//Directions to determine which diagonal bishop is moving
	private static int upRight = 1;
	private static int upLeft = 2;
	private static int downLeft = 3;
	private static int downRight = 4;

	/* Initializes Bishop to the place on board with unchangeable color
	 * Value of Bishop is 3
	 * Use GUI flag to determine if need to associate toString or Image */	
	public Bishop(int r, int c, boolean color){
		if(GUI){
			blackBishop = new Image("file:blackBishop.png");
			whiteBishop = new Image("file:whiteBishop.png");
		}
		row = r;
		col = c;
		this.color = color;
		value = 3;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "B";
			if(GUI){
				piece = blackBishop;
			}
		}
		else{
			label = "b";
			if(GUI){
				piece = whiteBishop;
			}
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
