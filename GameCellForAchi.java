/* This program is for the game Achi.
 * CSCI 204, Fall 2023
 * @author Dhriti Nair
 */

package achi;

public class GameCellForAchi {

	public int whoOwnsMe; // 1=X, -1=O, 0 means no one.

	public GameCellForAchi() {
		whoOwnsMe = 0;
	}

	// Setters and Getters:
	public int getWhoOwnsMe() {
		return whoOwnsMe;
	}

	public void setWhoOwnsMe(int whoOwnsMe) {
		this.whoOwnsMe = whoOwnsMe;
	}

	/** method to convert data structure into a readable string */
	public String toString() {
		if (whoOwnsMe == 1)
			return "x";
		else if (whoOwnsMe == -1)
			return "o";
		else
			return "_";
	}
}