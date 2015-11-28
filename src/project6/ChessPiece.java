package project6;
import java.util.ArrayList;

public abstract class ChessPiece {
	
	protected boolean isMoving;
	
	protected int value;
	
	protected boolean color;
	
	public static boolean black = true;
	public static boolean white = false;
	
	public static boolean turn = white;
	
	protected int row;
	protected int col;
	
	protected String label = "C";
	
	public String toString(){
		return label;
	}
	
	public ArrayList<Move> Moves = new ArrayList<Move>();
	
	protected void move(int r, int c){
		isMoving = true;
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
		}
		else{
			System.out.println("invalid move, try again");
		}
		isMoving = false;
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
		    blacks.add(ChessBoard[i][j]);
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
		    whites.add(ChessBoard[i][j]);
		  }
		}
		
	}
	
	public static boolean inCheck(int r, int c){
		
		/*King king = null;
		
		if(turn == black){
			for(ChessPiece cp: blacks){
				if(cp instanceof King){
					king = (King)cp;
					break;
				}
			}
		}
		else{
			for(ChessPiece cp: whites){
				if(cp instanceof King){
					king = (King)cp;
					break;
				}
			}
		}*/
		
		
		
				
		return false;
	}
}
