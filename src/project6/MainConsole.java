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
		
		while (true){
			
			orow = kb.nextInt();
			ocol = kb.nextInt();
			
			nrow = kb.nextInt();
			ncol = kb.nextInt();
			
			
			//ChessPiece.move1(ox, oy, xc, yc);
			ChessPiece.movePiece(orow, ocol, nrow, ncol);
			
			printBoard();
		}
		
	}

}
