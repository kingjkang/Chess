package project6;
import java.util.ArrayList;

public abstract class ChessPiece {
	
	protected int value;
	
	protected boolean color;
	
	public static boolean black = true;
	public static boolean white = false;
	
	protected int x;
	protected int y;
	
	protected String label = "C";
	
	public String toString(){
		return label;
	}
	
	public ArrayList<Move> Moves = new ArrayList<Move>();
	
	public void move(int nx, int ny){
		if(isValidMove(nx, ny)){
			ChessBoard[x][y] = null;
			if(ChessBoard[nx][ny] != null){
				if(color == black){
					whites.remove(ChessBoard[nx][ny]);
				}
				else{
					blacks.remove(ChessBoard[nx][ny]);
				}
			}
			x = nx;
			y = ny;
			ChessBoard[x][y] = this;
		}
	}
	
	public static void move1(int ox, int oy, int nx, int ny){
		ChessBoard[nx][ny] = ChessBoard[ox][oy];
		ChessBoard[ox][oy] = null;
	}
	
	public abstract boolean isValidMove(int nx, int ny);
	
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
	
	public static boolean inCheck(int nx, int ny){
		//check if the king is put in check
		
		return false;
	}
}
