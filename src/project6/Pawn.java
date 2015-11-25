package project6;

import java.util.ArrayList;
import java.util.Scanner;

public class Pawn extends ChessPiece{
	
	private boolean firstStepDone;

	public Pawn(int r, int c, boolean color){
		row = r;
		col = c;
		this.color = color;
		value = 1;
		ChessBoard[row][col] = this;
		firstStepDone = false;
		if(color == black){
			label = "P";
		}
		else{
			label = "p";
		}
	}
	
	public void showMoves(){
		
		Moves = new ArrayList<Move>(0);
		
		if(color == black){
			if(isValidMove(row+1, col)){
				Moves.add(new Move(row + 1, col));
			}
			if(isValidMove(row+2, col)){
				Moves.add(new Move(row + 2, col));
			}
			if(isValidMove(row+1, col+1)){
				Moves.add(new Move(row + 1, col + 1));
			}
			if(isValidMove(row+1, col-1)){
				Moves.add(new Move(row + 1, col - 1));
			}
		}
		else{
			if(isValidMove(row-1, col)){
				Moves.add(new Move(row - 1, col));
			}
			if(isValidMove(row-2, col)){
				Moves.add(new Move(row - 2, col));
			}
			if(isValidMove(row-1, col+1)){
				Moves.add(new Move(row - 1, col + 1));
			}
			if(isValidMove(row-1, col-1)){
				Moves.add(new Move(row - 1, col - 1));
			}
		}
		
	}
	
	@Override
	public void move(int r, int c){
		super.move(r, c);
		firstStepDone = true;
		boolean validPiece = false;
		if(row == 7 || row == 0){
			Scanner kb = new Scanner(System.in);
			while(!validPiece){
				System.out.print("What Piece> ");
				String piece = kb.nextLine();
				validPiece = evolve(piece);
			}
		}
	}
	
	public boolean isValidMove(int r, int c) {
		
		if(row == r && col == c){
			return false;
		}
		
		if(r > 7 || r < 0 || c > 7 || c < 0){
			return false;
		}
		
		int deltaX = Math.abs(r - row);
		int deltaY = Math.abs(c - col);
		
		if(!firstStepDone){
			if(deltaX > 0 || deltaY > 2){
				return false;
			}
		}
		else{
			if(deltaX > 1 || deltaY > 1){
				return false;
			}
		}
		
		if(color == black){
			if(r > row){
				if(c == col){
					if(ChessBoard[r][c] == null && !inCheck(r, c)){
						return true;
					}
					else{
						return false;
					}	
				}
				else{
					if(ChessBoard[r][c].color != color  && !inCheck(r, c)){
						return true;
					}
					else{
						return false;
					}
				}
			}
			else{
				return false;
			}
		}
		else{
			if(r < row){
				if(c == col){
					if(ChessBoard[r][c] == null && !inCheck(r, c)){
						return true;
					}
					else{
						return false;
					}	
				}
				else{
					if(ChessBoard[r][c].color == white && !inCheck(r, c)){
						return true;
					}
					else{
						return false;
					}
				}
			}
			else{
				return false;
			}
		}
	}

	public boolean evolve(String piece){
		
		boolean validPiece = false;
		
		ChessPiece upgrade = null;
		if(piece == "Queen"){
			upgrade = new Queen(row, col, color);
			validPiece = true;
		}
		else if(piece == "Rook"){
			upgrade = new Rook(row, col, color);
			validPiece = true;
		}
		else if(piece == "Bishop"){
			upgrade = new Bishop(row, col, color);
			validPiece = true;
		}
		else if(piece == "Knight"){
			upgrade = new Knight(row, col, color);
			validPiece = true;
		}
		
		if(validPiece){
			ChessBoard[row][col] = upgrade;
			if(color == black){
				blacks.remove(this);
				blacks.add(upgrade);
			}
			else{
				whites.remove(this);
				whites.add(upgrade);
			}
		}
		
		return validPiece;

	}
	
}
