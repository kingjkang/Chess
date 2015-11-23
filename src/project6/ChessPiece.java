package project6;

public abstract class ChessPiece {
	
	protected int value;
	
	/*False = white, True = black*/
	protected boolean color;
	
	public static boolean black = true;
	public static boolean white = false;
	
	protected int x;
	protected int y;
	
	public String toString(){
		return "C";
	}
	
	public abstract void move(int nx, int ny);
	
	public abstract boolean isValidMove(int nx, int ny);
	
	public abstract void showMoves();
	
	public static ChessPiece[][] ChessBoard = new ChessPiece[8][8];
	
	public static void initializeBoard(){
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				ChessBoard[i][j] = null;
			}
		}
		
		for(int i = 0; i < 8; i++){
			ChessBoard[i][1] = new Pawn(i, 0, black);
			ChessBoard[i][7] = new Pawn(i, 7, white);
		}
		
		/* Black Team
		ChessBoard[0][0] = new Rook(0, 0, black);
		ChessBoard[1][0] = new Knight(1, 0, black);
		ChessBoard[2][0] = new Bishop(2, 0, black);
		ChessBoard[3][0] = new King(3, 0, black);
		ChessBoard[4][0] = new Queen(4, 0, black);
		ChessBoard[5][0] = new Bishop(5, 0, black);
		ChessBoard[6][0] = new Knight(6, 0, black);
		ChessBoard[7][0] = new Rook(7, 0, black);
		*/
		
		/* White Team
		ChessBoard[0][6] = new Rook(0, 6, black);
		ChessBoard[1][6] = new Knight(1, 6, black);
		ChessBoard[2][6] = new Bishop(2, 6, black);
		ChessBoard[3][6] = new King(3, 6, black);
		ChessBoard[4][6] = new Queen(4, 6, black);
		ChessBoard[5][6] = new Bishop(5, 6, black);
		ChessBoard[6][6] = new Knight(6, 6, black);
		ChessBoard[7][6] = new Rook(7, 6, black);
		
		*/
		
	}
	
	public static boolean inCheck(int nx, int ny){
		//check if the king is put in check
		
		return false;
	}
}
