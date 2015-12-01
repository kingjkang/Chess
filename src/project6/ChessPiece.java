package project6;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ChessPiece {
	
	
	protected int value;
	
	protected boolean color;
	
	public boolean check = false;
	
	public static boolean black = true;
	public static boolean white = false;
	
	protected static ChessPiece blackKing = null;
	protected static ChessPiece whiteKing = null; 
	
	public static boolean turn = white;
	
	protected int row;
	protected int col;
	
	protected String label = "C";
	
	public String toString(){
		return label;
	}
	
	public ArrayList<Move> Moves = new ArrayList<Move>();
	
	protected void move(int r, int c){
		if(isValidMove(r, c)){
			ChessBoard[row][col] = null;
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
			System.out.println("invalid move, try again");
		}
	}
	
	public static void checkMove(int orow, int ocol, int nrow, int ncol){
		CheckChessBoard[orow][ocol].row = nrow;
		CheckChessBoard[orow][ocol].col = ncol;
		CheckChessBoard[nrow][ncol] = CheckChessBoard[orow][ocol];
		CheckChessBoard[orow][ocol] = null;
	}
	
	public static void checkMovePiece(int or, int oc, int nr, int nc){

		if(CheckChessBoard[or][oc] != null){
			if(CheckChessBoard[or][oc].color == turn){
				//CheckChessBoard[or][oc].checkMove(nr, nc);
				if(CheckChessBoard[or][oc] == null){
					turn = !turn;
				}
			}
			else{
				System.out.println("wrong turn!");
			}
		}
		else{
			System.out.println("no piece at specified coordinates, try again!");
		}

	}
	
	public static void moveDirectly(int orow, int ocol, int nrow, int ncol){
		if (ChessBoard[orow][ocol] == null){
			System.out.println("invalid move, try again");
		}
		else if (ChessBoard[orow][ocol].isValidMove(nrow, ncol)){	
			ChessBoard[orow][ocol].row = nrow;
			ChessBoard[orow][ocol].col = ncol;
			ChessBoard[nrow][ncol] = ChessBoard[orow][ocol];
			ChessBoard[orow][ocol] = null;
		}
		else {
			System.out.println(ChessBoard[orow][ocol].toString());
			System.out.println("invalid move, try again");
		}
	}
	
	public static void movePiece(int or, int oc, int nr, int nc){

		if(ChessBoard[or][oc] != null){
			if(ChessBoard[or][oc].color == turn){
				ChessBoard[or][oc].move(nr, nc);
				if(ChessBoard[or][oc] == null){
					turn = !turn;
				}
			}
			else{
				System.out.println("wrong turn!");
			}
		}
		else{
			System.out.println("no piece at specified coordinates, try again!");
		}

	}
	
	public abstract boolean isValidMove(int r, int c);
	
	public abstract void showMoves();
	
	public static ChessPiece[][] ChessBoard = new ChessPiece[8][8];
	
	public static ChessPiece[][] CCB = new ChessPiece[8][8];
	
	public static ChessPiece[][] CheckChessBoard = new ChessPiece[8][8];
	
	public static ArrayList<ChessPiece> whites = new ArrayList<ChessPiece>();
	
	public static ArrayList<ChessPiece> blacks = new ArrayList<ChessPiece>();
	
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
		ChessBoard[0][3] = new King(0, 3, black);
		ChessBoard[0][4] = new Queen(0, 4, black);
		ChessBoard[0][5] = new Bishop(0, 5, black);
		ChessBoard[0][6] = new Knight(0, 6, black);
		ChessBoard[0][7] = new Rook(0, 7, black);
		for(int j = 0; j < 2; j++){
		  for(int i = 0; i < 8; i++){
		    blacks.add(ChessBoard[j][i]);
		  }
		}
		
		//White Team
		ChessBoard[7][0] = new Rook(7, 0, white);
		ChessBoard[7][1] = new Knight(7, 1, white);
		ChessBoard[7][2] = new Bishop(7, 2, white);
		ChessBoard[7][3] = new King(7, 3, white);
		ChessBoard[7][4] = new Queen(7, 4, white);
		ChessBoard[7][5] = new Bishop(7, 5, white);
		ChessBoard[7][6] = new Knight(7, 6, white);
		ChessBoard[7][7] = new Rook(7, 7, white);
		for(int j = 6; j < 8; j++){
		  for(int i = 0; i < 8; i++){
		    whites.add(ChessBoard[j][i]);
		  }
		}
		
		blackKing = ChessBoard[0][3];
		whiteKing = ChessBoard[7][3];
		
		for(int r = 0; r <= 7; r++){
			for(int c = 0; c <= 7; c++){
				if(ChessBoard[r][c] != null){
					CCB[ChessBoard[r][c].row][ChessBoard[r][c].col] = ChessBoard[r][c];
				}
			}
		}
		
	}
	
	public boolean inCheck(int r, int c){
		
		ChessPiece removed = CCB[r][c];
		
		CCB[r][c] = this;
		
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
			while(cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				cc++;
			}
			
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
		
		//Going up
		if(king.row > 0){
			rr = king.row-1;
			cc = king.col;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
			}
			
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
		
		//Going left
		if(king.col > 0){
			rr = king.row;
			cc = king.col-1;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				cc--;
			}

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
		
		//Going down
		if(king.row < 7){
			rr = king.row+1;
			cc = king.col;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
			}

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
		
		//Going Up & Right
		if(king.row > 0 && king.col < 7){
			rr = king.row-1;
			cc = king.col+1;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
				cc++;
			}
			
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
		
		//Going Up & Left
		if(king.row > 0 && king.col > 0){
			rr = king.row-1;
			cc = king.col-1;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
				cc--;
			}
			
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
		
		//Going Down & Left
		if(king.row < 7 && king.col > 0){
			rr = king.row+1;
			cc = king.col-1;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
				cc--;
			}
			
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
		
		//Going Down & Right
		if(king.row < 7 && king.col < 7){
			rr = king.row+1;
			cc = king.col+1;

			while(rr >= 0 && rr <= 7 && col >= 0 && col <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
				cc++;
			}
			
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
			while(cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				cc++;
			}
			
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
		
		//Going up
		if(king.row > 0){
			rr = king.row-1;
			cc = king.col;

			while(rr >= 0){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
			}
			
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
		
		//Going left
		if(king.col > 0){
			rr = king.row;
			cc = king.col-1;

			while(cc >= 0){
				if(CCB[rr][cc] != null){
					break;
				}
				cc--;
			}

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
		
		//Going down
		if(king.row < 7){
			rr = king.row+1;
			cc = king.col;

			while(rr <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
			}

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
		
		//Going Up & Right
		if(king.row > 0 && king.col < 7){
			rr = king.row-1;
			cc = king.col+1;

			while(rr >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
				cc++;
			}
			
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
		
		//Going Up & Left
		if(king.row > 0 && king.col > 0){
			rr = king.row-1;
			cc = king.col-1;

			while(rr >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr--;
				cc--;
			}
			
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
		
		//Going Down & Left
		if(king.row < 7 && king.col > 0){
			rr = king.row+1;
			cc = king.col-1;

			while(rr >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
				cc--;
			}
			
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
		
		//Going Down & Right
		if(king.row < 7 && king.col < 7){
			rr = king.row+1;
			cc = king.col+1;

			while(rr >= 0 && cc <= 7){
				if(CCB[rr][cc] != null){
					break;
				}
				rr++;
				cc++;
			}
			
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
	
	public static boolean inCheck(int or, int oc, int r, int c){
		
		
		for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++){
				CheckChessBoard[row][col] = ChessBoard[row][col];
			}
		}
		//System.out.println(CheckChessBoard[0][0].toString());
		//System.out.println(or + "," + oc + "," + r + "," + c);
		checkMove(or, oc, r, c);
		int opp = 0;
		
		//System.out.println("code getting here");
		
		King king = null;
		if(turn == black){
			king = (King) ChessPiece.blackKing;
			//if we are checking the black king then we look to see if the whites can put him in check 
			Iterator<ChessPiece> itr = whites.iterator();
			while (itr.hasNext()){
				ChessPiece checking = itr.next();
				System.out.println("toCheckB"+checking.toString());
				if (CheckChessBoard[checking.row][checking.col].isValidMove(king.row, king.col)){
					return true;
				}
				
			}
		}
		else{
			king = (King) ChessPiece.whiteKing;
			Iterator<ChessPiece> itr = blacks.iterator();
			while (itr.hasNext()){
				ChessPiece checking = itr.next();
				System.out.println("toCheckW"+checking.toString());
				if (CheckChessBoard[checking.row][checking.col].isValidMove(king.row, king.col)){
					System.out.println("shouldent print rn ");
					return true;
				}
			}
		}
		
		/*
		System.out.println("breaking here null pointer");
		System.out.println(r + "," + c + "," +  king.row + "," + king.col);
		//null pointer because the piece hasnt moved yet
		if (ChessBoard[r][c].isValidMove(king.row, king.col)){
			System.out.println("King in Check");
			return true;
		}
		*/
		
		return false;
	}
}



