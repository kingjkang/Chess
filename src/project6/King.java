package project6;

import java.util.ArrayList;

public class King extends ChessPiece{

	public King(int r, int c, boolean color){
		row = r;
		col = c;
		this.color = color;
		value = 10;
		ChessBoard[row][col] = this;
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
			System.out.println("Cannot move this because King is in Check!");
			return false;
		}
		
		return true;
		
		//return !inCheck(this.row, this.col, r, c);
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
