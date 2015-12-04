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

/* Used only to determine if the king is in checkmate or stalemate
 * If pieces have no valid moves game should end
 * Just made a class because its easier to put in Moves intead of row and col */
public class Move {
	
	public int row;
	public int col;
	
	public Move(int row, int col){
		this.row = row; this.col = col;
	}
}
