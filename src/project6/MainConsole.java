package project6;

import java.util.Scanner;

public class MainConsole {
	
	public static void initBoard(){
		System.out.print("+" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+\n");
		for (int i = 0; i < 8; i++){
			System.out.println("|                        |");
		}
		System.out.print("+" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+\n");
	}
	
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
	
	public static void main(String[] args) {

		Scanner kb = new Scanner(System.in);
		int ocol, orow, ncol, nrow;
		
		System.out.println("Good Game");
		ChessPiece.initializeBoard();
		printBoard();
		
		boolean checkMate = false;
		while (!checkMate){
			
			String color = null;
			if(ChessPiece.turn){
				color = "Black ";
			}
			else{
				color = "White ";
			}
			
			
			if(ChessPiece.checkForKing()){
				if(ChessPiece.checkMate()){
					checkMate = true;
					System.out.println("CheckMate! "+ color + " lost.");
					break;
				}
				else{
					System.out.println(color + "King is in Check!");
				}
			}
			
			System.out.println(color + "turn");
			
			System.out.print("Enter coordinates of the piece you want to move and press Enter> ");
			
			orow = kb.nextInt();
			ocol = kb.nextInt();
			
			System.out.print("Enter coordinates to desired location and press Enter>           ");
			
			nrow = kb.nextInt();
			ncol = kb.nextInt();
			
			ChessPiece.movePiece(orow, ocol, nrow, ncol);
			
			printBoard();
		}
		
	}

}
