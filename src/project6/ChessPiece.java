package project6;

public abstract class ChessPiece {
	
	protected int value;
	
	/*False = white, True = black*/
	protected boolean color;
	
	protected int x;
	protected int y;
	
	public abstract void move(int row, int col);
	
	public abstract boolean isValidMove(int row, int col);
	
	public abstract void showMoves();
	
	public static ChessBoard[][] GameBoard = new ChessBoard[8][8];
	
	public static boolean checkCheck(){
		//check if the king is put in check
		
		return false;
	}
}
