package project6;
/* GAME PROJECT <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * <Anurag Andoji>
 * <aka888>
 * <16340>
 * <Student2 Justin Kang>
 * <jk36542>
 * <16340>
 * Slip days used: <0>
 * Fall 2015
 */

import java.util.Scanner;

/* Console part of the game 
 * Depends crucially on the GUI toggle in ChessPiece */
public class MainConsole {
	
	/* Initializes board */
	public static void initBoard(){
		System.out.print("+" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+\n");
		for (int i = 0; i < 8; i++){
			System.out.println("|                        |");
		}
		System.out.print("+" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+\n");
	}
	
	/* Outputs state of the board on console with the toStrings of each piece
	 * Blacks are Uppercase
	 * Whites are Lowercase
	 * Border around the game board
	 * Coordinates around the game board*/
	public static void printBoard(){

	    System.out.println("   " + " 0 " + " 1 " + " 2 " + " 3 " + " 4 " + " 5 " + " 6 " + " 7 ");
	    System.out.println("  +" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+");
		
		for(int i = 0; i < 8; i++){
			System.out.print(i + " |");
			for(int j = 0; j < 8; j++){
				if (ChessPiece.ChessBoard[i][j] == null){
					System.out.print(" - ");
				}
				else {
					System.out.print(" " + ChessPiece.ChessBoard[i][j].toString() + " ");
				}
			}
			System.out.println("|");
		}
		
		System.out.println("  +" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+");
		
	}
	
	/* Execution of the console portion */
	
	public static void main(String[] args) {
		
		ChessPiece.GUI = false;

		Scanner kb = new Scanner(System.in);
		int ocol = 0, orow = 0, ncol = 0, nrow = 0;
		
		ChessPiece.initializeBoard();
		printBoard();
		System.out.println("White Turn");
		
		boolean checkMate = false;
		while (!checkMate){
			
			String color = null;
			String other = null;
			if(ChessPiece.turn){
				color = "Black ";
				other = "White ";
			}
			else{
				other = "Black ";
				color = "White ";
			}
			
			checkMate = ChessPiece.checkMate();
			
			if(ChessPiece.checkForKing()){
				if(checkMate){
					System.out.println("CheckMate! "+ other + "won.");
					break;
				}
				else{
					System.out.println(color + "King is in Check!");
				}
			}
			
			if(checkMate){
				System.out.println("Stalemate! No valid move can be made. It is a draw.");
				break;
			}

			
			//System.out.print("Enter coordinates of the piece you want to move and press Enter> ");
			

			orow = kb.nextInt();
			ocol = kb.nextInt();

			
			//System.out.print("Enter coordinates to desired location and press Enter>           ");
			
			nrow = kb.nextInt();
			ncol = kb.nextInt();

			
			
			ChessPiece.movePiece(orow, ocol, nrow, ncol);
			
			printBoard();
			System.out.println(ChessPiece.printColor + " Turn");
		}
		
	}

}
