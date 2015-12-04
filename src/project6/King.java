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
 * Extra moves are added from chessPiece */
public class King extends ChessPiece{

	Image blackKing;
	Image whiteKing;
	
	//For castling
	private boolean firstStepDone = false;

	/* Initializes King to the place on board with unchangeable color
	 * Value of King is 10
	 * Use GUI flag to determine if need to associate toString or Image */
	public King(int r, int c, boolean color){
		if(GUI){
			blackKing = new Image("file:blackKing.png");
			whiteKing = new Image("file:whiteKing.png");
		}
		row = r;
		col = c;
		this.color = color;
		value = 10;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "K";
			if(GUI){
				piece = blackKing;
			}
		}
		else{
			label = "k";
			if(GUI){
				piece = whiteKing;
			}
		}
	}
	
	/* Used in one the methods to check if the king is in check 
	 * Should not be visible to anyone else */
	private King(King k){
		row = k.row;
		col = k.col;
		color = k.color;
		if(color == black){
			label = "K";
		}
		else{
			label = "k";
		}
	}
	
	/* Pawn and the King are the only ChessPiece's that override move
	 * King overrides it because of castling and also because of special check conditions */
	protected void move(int r, int c){
//		super.move(r, c);
		if(isValidMove(r, c)){
			
			//Castling
			if(col + 2 == c){
				ChessBoard[row][col] = null;
				CCB[row][col] = null;
				row = r;
				col = c;
				ChessBoard[row][col] = this;
				CCB[row][col] = this;
				firstStepDone = true;
				
				Rook right = (Rook)ChessBoard[row][7];
				ChessBoard[row][7] = null;
				CCB[row][7] = null;
				right.row = r;
				right.col = c-1;
				ChessBoard[right.row][right.col] = right;
				CCB[right.row][right.col] = right;
				right.firstStepDone = true;
			}
			else if(col - 2  == c){
				ChessBoard[row][col] = null;
				CCB[row][col] = null;
				row = r;
				col = c;
				ChessBoard[row][col] = this;
				CCB[row][col] = this;
				firstStepDone = true;
				
				Rook left = (Rook)ChessBoard[row][0];
				ChessBoard[row][0] = null;
				CCB[row][0] = null;
				left.row = r;
				left.col = c+1;
				ChessBoard[left.row][left.col] = left;
				CCB[left.row][left.col] = left;
				left.firstStepDone = true;
			}
			else{
				ChessBoard[row][col] = null;
				CCB[row][col] = null;
				if(ChessBoard[r][c] != null){
					if(color == black){
						whites.remove(ChessBoard[r][c]);
					}
					else{
						blacks.remove(ChessBoard[r][c]);
					}
				}
				row = r;
				col = c;
				ChessBoard[row][col] = this;
				CCB[row][col] = this;
				firstStepDone = true;
			}
		}
		else{
			System.out.println("Invalid move, try again");
		}
		
		if(color == black){
			ChessPiece.blackKing = this;
		}
		else{
			ChessPiece.whiteKing = this;
		}
	}
	
	public boolean isValidMove(int r, int c) {
		
		if(r < 0 || r > 7 || c < 0 || c > 7){
			return false;
		}
		
		if(r == row && c == col){
			return false;
		}
		
		if(ChessBoard[r][c] != null && ChessBoard[r][c].color == color){
			return false;
		}
		
		if(inCheck(r, c)){
			return false;
		}
		
		if(!firstStepDone && !inCheck(row, col)){
			if(row == r && (col + 2 == c || col - 2 == c)){
				Rook castle;
				if(col + 2 == c){
					if(ChessBoard[row][7] instanceof Rook){
						castle = (Rook)ChessBoard[row][7];
						if(!castle.firstStepDone && castle.color == color){
							if(ChessBoard[row][5] == null && ChessBoard[row][6] == null){
								return true;
							}
						}
					}
					return false;
				}
				else{
					if(ChessBoard[row][0] instanceof Rook){
						castle  = (Rook)ChessBoard[row][0];
						if(!castle.firstStepDone && castle.color == color){
							if(ChessBoard[row][1] == null && ChessBoard[row][2] == null && ChessBoard[row][3] == null){
								return true;
							}
						}
					}
					return false;
				}
			}
		}
		
		int deltaR = Math.abs(row - r);
		int deltaC = Math.abs(col - c);
		
		if(deltaR > 1 || deltaC > 1){
			return false;
		}
		
		
		return true;
		
		//return !inCheck(this.row, this.col, r, c);
	}
	
	/* Only for the king, special case thats why normal inCheck in the ChessPiece can't work */
	public boolean inCheck(int r, int c){
		
		King king = new King(this);
		king.row = r;
		king.col = c;
		
		ChessPiece removed = CCB[r][c];
		
		CCB[r][c] = this;
		
		CCB[row][col] = null;
		
		int rr; int cc;
		
		//Going right
		if(king.col < 7){
			rr = king.row;
			cc = king.col+1;
			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				cc++;
			}
		
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col+1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going up
		if(king.row > 0){
			rr = king.row-1;
			cc = king.col;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
			}
			
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && rr == king.row-1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going left
		if(king.col > 0){
			rr = king.row;
			cc = king.col-1;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				cc--;
			}

			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col-1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going down
		if(king.row < 7){
			rr = king.row+1;
			cc = king.col;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
			}
			 
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && rr == king.row+1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going Up & Right
		if(king.row > 0 && king.col < 7){
			rr = king.row-1;
			cc = king.col+1;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
				cc++;
			}
			
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col+1 && rr == king.row-1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col+1 && rr == king.row-1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going Up & Left
		if(king.row > 0 && king.col > 0){
			rr = king.row-1;
			cc = king.col-1;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
				cc--;
			}
			
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col-1 && rr == king.row-1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col-1 && rr == king.row-1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going Down & Left
		if(king.row < 7 && king.col > 0){
			rr = king.row+1;
			cc = king.col-1;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
				cc--;
			}
			
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col-1 && rr == king.row+1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col-1 && rr == king.row+1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Going Down & Right
		if(king.row < 7 && king.col < 7){
			rr = king.row+1;
			cc = king.col+1;

			while(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
				cc++;
			}
			
			if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					if(CCB[rr][cc].color != turn){
						if(CCB[rr][cc] instanceof Queen){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col+1 && rr == king.row+1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col+1 && rr == king.row+1){
							CCB[r][c] = removed;
							CCB[row][col] = this;
							return true;
						}
					}
				}
			}
		}
		
		//Knight Cases
		rr = king.row + 1;
		cc = king.col + 2;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row + 2;
		cc = king.col + 1;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row + 2;
		cc = king.col - 1;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row + 1;
		cc = king.col - 2;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row - 1;
		cc = king.col + 2;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row - 2;
		cc = king.col + 1;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row - 2;
		cc = king.col - 1;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		rr = king.row - 1;
		cc = king.col - 2;
		if(rr >= 0 && rr <= 7 && cc >= 0 && cc <= 7){
			if(CCB[rr][cc] != null){
				if(CCB[rr][cc].color != turn){
					if(CCB[rr][cc] instanceof Knight){
						CCB[r][c] = removed;
						CCB[row][col] = this;
						return true;
					}
				}
			}
		}
		
		CCB[r][c] = removed;
		CCB[row][col] = this;
		return false;
		
		
	}
	
	public void showMoves() {
		
		Moves = new ArrayList<Move>(0);
		
		if(isValidMove(row, col + 1)){ Moves.add(new Move(row, col + 1)); }
		if(isValidMove(row - 1, col + 1)){ Moves.add(new Move(row - 1, col + 1)); }
		if(isValidMove(row - 1, col)){ Moves.add(new Move(row - 1, col)); }
		if(isValidMove(row - 1, col - 1)){ Moves.add(new Move(row - 1, col - 1)); }
		if(isValidMove(row, col - 1)){ Moves.add(new Move(row, col - 1)); }
		if(isValidMove(row + 1, col - 1)){ Moves.add(new Move(row + 1, col - 1)); }
		if(isValidMove(row + 1, col)){ Moves.add(new Move(row + 1, col)); }
		if(isValidMove(row + 1, col + 1)){ Moves.add(new Move(row + 1, col + 1)); }

	}
	

}
