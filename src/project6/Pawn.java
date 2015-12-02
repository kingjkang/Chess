package project6;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.image.Image;

public class Pawn extends ChessPiece{
	
	Image blackPawn;
	Image whitePawn;
	
	private boolean firstStepDone;

	public Pawn(int r, int c, boolean color){
		if(GUI){
			blackPawn = new Image("file:blackPawn.png");
			whitePawn = new Image("file:whitePawn.png");
		}
		row = r;
		col = c;
		this.color = color;
		value = 1;
		ChessBoard[row][col] = this;
		firstStepDone = false;
		if(color == black){
			label = "P";
			if(GUI){
				piece = blackPawn;
			}
		}
		else{
			label = "p";
			if(GUI){
				piece = whitePawn;
			}
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
	protected void move(int r, int c){
		super.move(r, c);
		firstStepDone = true;
		boolean validPiece = false;
		if(row == 7 || row == 0){
			Scanner kb = new Scanner(System.in);
			while(!validPiece){
				System.out.print("What Piece> ");
				String piece = kb.nextLine();
				validPiece = evolve(piece);
				if(!validPiece){
					System.out.println(piece + " is not a valid piece.");
				}
			}
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

		
		
//		if(inCheck(this.row, this.col, r, c)){
//			return false;
//		}
		
		if(inCheck(r, c)){
			return false;
		}
		
		
		int deltaR = Math.abs(r - row);
		int deltaC = Math.abs(c - col);
		
		
		if(!firstStepDone){
			if(deltaR == 2 && deltaC == 0){
				if(color == black){
					if(r > row){
						if(ChessBoard[row+1][col] != null || ChessBoard[row+2][col] != null){
							return false;
						}
						else{
							return true;
						}
					}
					else{
						return false;
					}
				}
				else{
					if(r < row){
						if(ChessBoard[row-1][col] != null || ChessBoard[row-2][col] != null){
							return false;
						}
						else{
							return true;
						}
					}
					else{
						return false;
					}
				}
			}
			else if(deltaR == 1 && deltaC <= 0){
				
			}
			else{
				return false;
			}
		}
		else{
			if(!(deltaR == 1 && deltaC <= 1)){
				return false;
			}
		}
		
		if(color == black){
			if(r > row){
				if(c == col){
					if(ChessBoard[r][c] == null){
						return true;
					}
					else{
						return false;
					}	
				}
				else{
					if(ChessBoard[r][c] != null && ChessBoard[r][c].color != color){
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
					if(ChessBoard[r][c] == null){
						return true;
					}
					else{
						return false;
					}	
				}
				else{
					if(ChessBoard[r][c] != null && ChessBoard[r][c].color != color){
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
		if(piece.equals("Queen")){
			upgrade = new Queen(row, col, color);
			validPiece = true;
		}
		else if(piece.equals("Rook")){
			upgrade = new Rook(row, col, color);
			validPiece = true;
		}
		else if(piece.equals("Bishop")){
			upgrade = new Bishop(row, col, color);
			validPiece = true;
		}
		else if(piece.equals("Knight")){
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
