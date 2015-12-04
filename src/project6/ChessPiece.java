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
import java.util.Iterator;

import javafx.scene.image.Image;

/* Main component of the Chess Game
 * The Pawn, Bishop, Knight, Rook, Queen, King all extend this abstract class
 * Contains checkmate, check, stalemate, move, validMove function
 * Contains the ChessBoard */
public abstract class ChessPiece {
	
	//Toggle for GUI/Console
	public static boolean GUI;
	
	//The importance of each piece
	//Ex: King has the most value, Pawn has the least value
	//Intended for the AI
	protected int value;
	
	//White or Black
	protected boolean color;
	
	public boolean check = false;
	
	public static boolean black = true;
	public static boolean white = false;
	
	protected static ChessPiece blackKing = null;
	protected static ChessPiece whiteKing = null; 
	
	public static boolean turn = white;
	
	static Boolean turnColor = true;
	static String printColor = "white";
	
	protected int row;
	protected int col;
	
	protected String label = "C";
	protected Image piece;
	
	public String toString(){
		return label;
	}
	
	public Image toImage(){
		return piece;
	}
	
	//Contains all the valid moves of the piece at each turn
	public ArrayList<Move> Moves = new ArrayList<Move>();
	
	/* Checks if the move is valid and then moves
	 * Pawn overrides because of firstStep and evolve
	 * King overrides because of castling */
	protected void move(int r, int c){
		if(isValidMove(r, c)){
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
		}
		else{
			System.out.println("Invalid move, try again");
		}
	}
	
	/* For the console
	 * Gets the coordinates of the piece you want to move
	 * Then moves it to the correct coordinates */
	public static void movePiece(int or, int oc, int nr, int nc){
		
		if(ChessBoard[or][oc] != null){
			if(ChessBoard[or][oc].color == turn){
				ChessBoard[or][oc].move(nr, nc);
				if(ChessBoard[or][oc] == null){
					turn = !turn;
				}
				if (turn == white){
					printColor = "White";
				} 
				else {
					printColor = "Black";
				}
			}
			else{
				System.out.println("Wrong Turn!");
				System.out.println(printColor + " Turn");
			}
		}
		else{
			System.out.println("No piece at specified coordinates, Try again!");
			System.out.println(printColor + " Turn");
		}

	}
	
	/* Checks if move is Valid
	 * I.E. the move checks if the movement is consistent with the piece's movement
	 * checks if the move doesn't put king in check or take king out of check */
	public abstract boolean isValidMove(int r, int c);
	
	/* Gets all the validMoves for the respective ChessPiece */
	public abstract void showMoves();
	
	/* Actual gameboard */
	public static ChessPiece[][] ChessBoard = new ChessPiece[8][8];
	
	/* Backend gameboard to check for King in check */
	public static ChessPiece[][] CCB = new ChessPiece[8][8];
		
	public static ArrayList<ChessPiece> whites = new ArrayList<ChessPiece>();
	
	public static ArrayList<ChessPiece> blacks = new ArrayList<ChessPiece>();
	
	/* Sets the initial pieces up */
	public static void initializeBoard(){
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				ChessBoard[i][j] = null;
			}
		}
		
		for(int i = 0; i < 8; i++){
			ChessBoard[1][i] = new Pawn(1, i, black);
			ChessBoard[6][i] = new Pawn(6, i, white);
		}
		
		//Black Team
		ChessBoard[0][0] = new Rook(0, 0, black);
		ChessBoard[0][1] = new Knight(0, 1, black);
		ChessBoard[0][2] = new Bishop(0, 2, black);
		ChessBoard[0][3] = new Queen(0, 3, black);
		ChessBoard[0][4] = new King(0, 4, black);
		ChessBoard[0][5] = new Bishop(0, 5, black);
		ChessBoard[0][6] = new Knight(0, 6, black);
		ChessBoard[0][7] = new Rook(0, 7, black);
		for(int r = 0; r <= 1; r++){
		  for(int c = 0; c <+ 7; c++){
		       blacks.add(ChessBoard[r][c]);
		  }
		}
		
		//White Team
		ChessBoard[7][0] = new Rook(7, 0, white);
		ChessBoard[7][1] = new Knight(7, 1, white);
		ChessBoard[7][2] = new Bishop(7, 2, white);
		ChessBoard[7][3] = new Queen(7, 3, white);
		ChessBoard[7][4] = new King(7, 4, white);
		ChessBoard[7][5] = new Bishop(7, 5, white);
		ChessBoard[7][6] = new Knight(7, 6, white);
		ChessBoard[7][7] = new Rook(7, 7, white);
		for(int r = 6; r <= 7; r++){
			for(int c = 0; c <= 7; c++){
				whites.add(ChessBoard[r][c]);
			}
		}
		
		blackKing = ChessBoard[0][4];
		whiteKing = ChessBoard[7][4];
		
		for(int r = 0; r <= 7; r++){
			for(int c = 0; c <= 7; c++){
				if(ChessBoard[r][c] != null){
					CCB[ChessBoard[r][c].row][ChessBoard[r][c].col] = ChessBoard[r][c];
				}
				else{
					CCB[r][c] = null;
				}
			}
		}
		
	}
	
	/* Checks if the king is in check if a move is to be made to (r, c)
	 * Temporarily moves the piece in the backend chessboard and checks if the move
	 * Does not yield its king in check
	 * Then moves the piece back in the backend chessboard */
	public boolean inCheck(int r, int c){
		
		ChessPiece removed = CCB[r][c];
		
		CCB[r][c] = this;
		
		CCB[row][col] = null;
		
		King king = null;
		
		
		if(turn == black){
			king = (King) blackKing;
		}
		else{
			king = (King) whiteKing;
		}
		
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

	/* Checks if the king is in check at the beginning of check
	 * Very similar to inCheck except nothing has to be updated in CCB */
	public static boolean checkForKing(){
				
		King king = null;
		
		if(turn == black){
			king = (King) blackKing;
		}
		else{
			king = (King) whiteKing;
		}
		
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
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							return true;

						}
						if(CCB[rr][cc] instanceof King && cc == king.col+1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							return true;
						}
						if(CCB[rr][cc] instanceof King && rr == king.row-1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col-1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Rook){
							return true;
						}
						if(CCB[rr][cc] instanceof King && rr == king.row+1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col+1 && rr == king.row-1){
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col+1 && rr == king.row-1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col-1 && rr == king.row-1){
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col-1 && rr == king.row-1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col-1 && rr == king.row+1){
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col-1 && rr == king.row+1){
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
							return true;
						}
						if(CCB[rr][cc] instanceof Bishop){
							return true;
						}
						if(CCB[rr][cc] instanceof King && cc == king.col+1 && rr == king.row+1){
							return true;
						}
						if(CCB[rr][cc] instanceof Pawn && cc == king.col+1 && rr == king.row+1){
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
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
	
	/* Checks if the king is in Stalemate or Checkmate
	 * Essentially goes through all the pieces on black team
	 * or white team, if no piece has a valid move then the king
	 * is either in checkmate or stalemate
	 * King is in Checkmate when the king is also in check
	 * King is in Stalemate when the king is not in check but none of the pieces
	 * can make a valid move */
	public static boolean checkMate(){
		
		if(turn == black){
			for(ChessPiece cp : blacks){
				cp.showMoves();
			}
			for(ChessPiece cp: blacks){
				if(!cp.Moves.isEmpty()){
					return false;
				}
			}
		}
		else{
			for(ChessPiece cp: whites){
				cp.showMoves();
			}
			for(ChessPiece cp: whites){
				if(!cp.Moves.isEmpty()){
					return false;
				}
			}
		}
		
		
		return true;
		
	}
	
}



