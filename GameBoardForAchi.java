/* This program is for the game Achi.
 * CSCI 204, Fall 2023
 * @author Dhriti Nair
 */

package achi;

public class GameBoardForAchi {

	static private GameCellForAchi[][] gameCells;

	static int fromRow; // row of cell from which the piece will move
	static int fromCol; // column of cell from which the piece will move

	/* constructor */
	public GameBoardForAchi() {
		gameCells = new GameCellForAchi[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				gameCells[row][col] = new GameCellForAchi();
			}
		}
	}

	/** method to enter piece into the cell for the drop phase */
	public void locationEnteredDropPhase(int row, int col) {
		int player = Achi.getWhoseTurnIsIt();
		gameCells[row][col].setWhoOwnsMe(player); // changes the
		Achi.changePlayer();
	}

	/** method to enter piece into the cell for the move phase */
	public static void locationEnteredMovePhase(int row, int col) {
		int player = Achi.getWhoseTurnIsIt();
		gameCells[findEmptyCell()[0]][findEmptyCell()[1]].setWhoOwnsMe(player);
		gameCells[Achi.fromRow][Achi.fromCol].setWhoOwnsMe(0); // Empty the original cell
		Achi.changePlayer();
	}

	/** method to convert data structure into a readable string */
	public String toString() {
		String stringSoFar = "";
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				stringSoFar += gameCells[row][col];
				if (col < 2) {
					stringSoFar += " ";
				}
			}
			stringSoFar += "\n";
		}
		return stringSoFar;
	}

	/** checks if the cell is empty */
	public static Boolean isEmpty(int row, int col) {
		return gameCells[row][col].getWhoOwnsMe() == 0;
	}

	/** checks if the cell is empty, and therefore valid for the drop phase */
	public void checkValidityInDropPhase(int row, int col) {
		if (isEmpty(row, col) == true) {
			Achi.dropPhaseMove(row, col);
		} else {
			invalidMoveInDropPhase(row, col);
		}
	}

	/**
	 * will print error statement and give player another turn if illegal move is
	 * made in drop phase
	 */
	private static void invalidMoveInDropPhase(int row, int col) {
		System.out.println("\nThis is an illegal move, try again \n");
		Achi.dropPhase();
	}

	/** finds empty cell for piece to move into */
	public static int[] findEmptyCell() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (isEmpty(row, col)) {
					return new int[] { row, col };
				}
			}
		}
		return null;
	}

	/** checks if a horizontal or vertical move is valid */
	public static Boolean checkRowColValidityInMovePhase(int row, int col) {
		int[] emptyCell = findEmptyCell();
		return (emptyCell[0] == row && Math.abs(emptyCell[1] - col) == 1)
				|| (emptyCell[1] == col && Math.abs(emptyCell[0] - row) == 1);
	}

	/** checks if a diagonal move is valid */
	public static Boolean checkDiagonalValidityInMovePhase(int row, int col) {
		int[] emptyCell = findEmptyCell();
		return (emptyCell[0] == 1 && emptyCell[1] == 1) && (row != 1 && col != 1)
				|| (row == 1 && col == 1) && (emptyCell[0] != 1 && emptyCell[1] != 1);
	}

	/**
	 * will print error statement and give player another turn if illegal move is
	 * made in move phase
	 */
	public static void invalidMoveInMovePhase(int row, int col) {
		System.out.println("\nThis is an illegal move, try again \n");
		Achi.movePhase();
	}

	/** checks if the player is clicking their own piece */
	public static Boolean checkPlayerValidity(int row, int col) {
		return (gameCells[row][col].getWhoOwnsMe() == Achi.getWhoseTurnIsIt());
	}
}
	
