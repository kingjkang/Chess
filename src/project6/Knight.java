package project6;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Knight extends ChessPiece{
	
	//Image blackKnight = new Image(getClass().getResourceAsStream("file:blackKnight.png"));
	//Image whiteKnight = new Image(getClass().getResourceAsStream("file:whiteKnight.png"));
	Image blackKnight = new Image("file:blackKnight.png");
	Image whiteKnight = new Image("file:whiteKnight.png");

	public Knight(int r, int c, boolean color){
		row = r;
		col = c;
		this.color = color;
		value = 3;
		ChessBoard[row][col] = this;
		if(color == black){
			label = "H";
			piece = blackKnight;
		}
		else{
			label = "h";
			piece = whiteKnight;
		}
	}
	
	public boolean isValidMove(int r, int c) {

		if(r > 7 || r < 0 || c > 7 || c < 0){
			return false;
		}
		
		if(row == r && col == c){
			return false;
		}
		
		if(ChessBoard[r][c] != null && ChessBoard[r][c].color == color){
			return false;
		}
				
		if(inCheck(r, c)){
			return false;
		}
		
		if(ChessBoard[r][c] == null || ChessBoard[r][c].color != color){
			if(r == row - 1 && c == col - 2){
				return true;
			}
			if(r == row - 2 && c == col - 1){
				return true;
			}
			if(r == row - 1 && c == col + 2){
				return true;
			}
			if(r == row - 2 && c == col + 1){
				return true;
			}
			if(r == row + 1 && c == col - 2){
				return true;
			}
			if(r == row + 2 && c == col - 1){
				return true;
			}
			if(r == row + 1 && c == col + 2){
				return true;
			}
			if(r == row + 2 && c == col + 1){
				return true;
			}
		}
		return false;
	}

	public void showMoves() {
		
		Moves = new ArrayList<Move>(0);
		
		if(isValidMove(row - 1, col - 2)){
			Moves.add(new Move(row - 1, col - 2));
		}
		if(isValidMove(row - 2, col - 1)){
			Moves.add(new Move(row - 2, col - 1));
		}
		if(isValidMove(row - 1, col + 2)){
			Moves.add(new Move(row - 1, col - 2));
		}
		if(isValidMove(row - 2, col + 1)){
			Moves.add(new Move(row - 2, col - 1));
		}
		if(isValidMove(row + 1, col + 2)){
			Moves.add(new Move(row + 1, col + 2));
		}
		if(isValidMove(row + 2, col + 1)){
			Moves.add(new Move(row + 2, col + 1));
		}
		if(isValidMove(row + 1, col - 2)){
			Moves.add(new Move(row + 1, col - 2));
		}
		if(isValidMove(row + 2, col - 1)){
			Moves.add(new Move(row + 2, col - 1));
		}
		
	}

}
