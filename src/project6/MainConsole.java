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
		int xc, yc, ox, oy;
		
		System.out.println("lol");
		ChessPiece.initializeBoard();
		printBoard();
		
		while (true){
			ox = kb.nextInt();
			oy = kb.nextInt();
			
			xc = kb.nextInt();
			yc = kb.nextInt();
			
			ChessPiece.move1(ox, oy, xc, yc);
			
			printBoard();
		}
		
	}

}
