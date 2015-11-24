package project6;

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
		System.out.println("lol");
		ChessPiece.initializeBoard();
		printBoard();
		
	}

}
