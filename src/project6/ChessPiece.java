package project6;

public abstract class ChessPiece {
	
	protected int value;
	
	/*False = white, True = black*/
	protected boolean color;
	
	protected int x;
	protected int y;
	
	public abstract void move(int row, int col);
	
	public abstract void isValidMove(int row, int col);
	
	public abstract void showMoves();
	
<<<<<<< HEAD
=======
	public static ChessBoard[][] GameBoard = new ChessBoard[8][8];
	
>>>>>>> 56adeb67c5fbaa35d8c9b632289e9a5c1984c206
}
