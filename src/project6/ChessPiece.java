package project6;

public abstract class ChessPiece {
	
	private int value;
	
	/*True = white, False = black*/
	private boolean color;
	
	private int x;
	private int y;
	
	public abstract void move(int row, int col);
	
	public abstract void isValidMove(int row, int col);
	
	public abstract void showMoves();
	
}
