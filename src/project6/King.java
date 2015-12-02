package project6;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class King extends ChessPiece{

	Image blackKing;
	Image whiteKing;

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
	
	protected void move(int r, int c){
		super.move(r, c);
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
		
		int deltaR = Math.abs(row - r);
		int deltaC = Math.abs(col - c);
		
		if(deltaR > 1 || deltaC > 1){
			return false;
		}
		
		if(inCheck(r, c)){
			return false;
		}
		
		return true;
		
		//return !inCheck(this.row, this.col, r, c);
	}
	
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
