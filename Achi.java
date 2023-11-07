/* This program is for the game Achi.
 * CSCI 204, Fall 2023
 * @author Dhriti Nair
 */

package achi;

import java.util.Scanner;

public class Achi {

	private static Scanner getInput;
	private static GameBoardForAchi gameBoard;
	private static int whoseTurnIsIt;

	static int fromRow; // row of cell from which the piece will move
	static int fromCol; // column of cell from which the piece will move

	/** driver for the program */
	public static void main(String[] args) { // Plays the game
		initializeGameVariables();
		System.out.println("Drop phase of Achi");
		for (int move = 1; move <= 8; move++) {
			dropPhase();
		}
		System.out.println("Move phase of Achi");
		while (true) {
			movePhase();
		}
	}

	/** DROP PHASE FOR ACHI */

	/** keeps track of whose turn it is */
	public static int getWhoseTurnIsIt() {
		return whoseTurnIsIt;
	}

	/** changes the player */
	public static void changePlayer() {
		whoseTurnIsIt *= -1;
	}

	/** manages the drop phase of the game */
	public static void dropPhase() {
		int row, col;
		System.out.print("\nWhat move do you make? Use the format 'row column': ");
		row = getInput.nextInt(); // Get input from the user
		col = getInput.nextInt();
		gameBoard.checkValidityInDropPhase(row, col); // check validity of the move
	}

	/** Create initial values for the scanner, the game board, and whoseTurnIsIt */
	private static void initializeGameVariables() {
		getInput = new Scanner(System.in);
		gameBoard = new GameBoardForAchi();
		whoseTurnIsIt = 1;
	}

	/** makes one move in the drop phase */
	public static void dropPhaseMove(int row, int col) {
		gameBoard.locationEnteredDropPhase(row, col);
		System.out.print("\nThe current board is:\n" + gameBoard);
	}

	/** MOVE PHASE FOR ACHI */

	/** manages the move phase of the game */
	public static void movePhase() {
		// get input from the user
		System.out.print("Which piece do you want to move? Use the format 'row column': ");
		fromRow = getInput.nextInt();
		fromCol = getInput.nextInt();
		// check validity of the move
		checkMovePhaseValidity(fromRow, fromCol);
	}

	/** checks the validity of the move made by the user in the move phase */
	public static void checkMovePhaseValidity(int row, int col) {
		if ((GameBoardForAchi.checkRowColValidityInMovePhase(row, col) == true
				|| GameBoardForAchi.checkDiagonalValidityInMovePhase(row, col) == true)
				&& (GameBoardForAchi.checkPlayerValidity(row, col) == true)) { // will return true if the move is valid
			GameBoardForAchi.locationEnteredMovePhase(fromRow, fromCol);
			System.out.print("\nThe current board is:\n" + gameBoard);
		} else {
			GameBoardForAchi.invalidMoveInMovePhase(fromRow, fromCol);
		}
	}
}
