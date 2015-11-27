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
		System.out.print("+" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+\n");
		
		for(int i = 0; i < 8; i++){
			System.out.print("|");
			for(int j = 0; j < 8; j++){
				if (ChessPiece.ChessBoard[i][j] == null){
					System.out.print("   ");
				}
				else {
					System.out.print(" " + ChessPiece.ChessBoard[i][j].toString() + " ");
				}
			}
			System.out.println("|");
		}
		
		System.out.print("+" + " - " + " - " + " - " + " - " + " - " + " - " + " - " + " - " + "+\n");
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		int ocol, orow, ncol, nrow;
		
		System.out.println("lol");
		ChessPiece.initializeBoard();
		printBoard();
		
		while (true){
			orow = kb.nextInt();
			ocol = kb.nextInt();
			
			nrow = kb.nextInt();
			ncol = kb.nextInt();
			
			// column then row convention
			
			//ChessPiece.move1(ox, oy, xc, yc);
			ChessPiece.move1(orow, ocol, nrow, ncol);
			
			printBoard();
		}
		
	}

}
