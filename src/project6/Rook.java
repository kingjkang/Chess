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
 * The queen will use the rooks move code as well */
public class Rook extends ChessPiece{
	
	Image blackRook;
	Image whiteRook;
	
	//For castling
	public boolean firstStepDone;
	
	/* Initializes Rook to the place on board with unchangeable color
	 * Value of Rook is 5
	 * Use GUI flag to determine if need to associate toString or Image */
	public Rook(int r, int c, boolean color){
		if(GUI){
			blackRook = new Image("file:blackRook.png");
			whiteRook = new Image("file:whiteRook.png");
		}
		row = r;
		col = c;
		this.color = color;
		value = 5;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "R";
			if(GUI){
				piece = blackRook;
			}
		}
		else{
			label = "r";
			if(GUI){
				piece = whiteRook;
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
		
		
	}
	

}
