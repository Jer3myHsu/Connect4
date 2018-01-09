import java.util.ArrayList;

public class Grid {
	// grid represents the playing grid.
	// p#Counters represents a list of the appropriate player's counter
	// positions.
	private int[][] grid = new int[7][6];

	// FUNCTIONS TO BE USED IN Main.java
	/**
	 * Return y coordinate of the position of the counter inserted.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column inserted into from 1-7.
	 * @return An int from 1-6 representing the row number.
	 */
	public int insertCounter(int player, int columnNum) {
		// Find the first empty row in that column.
		int[] rows = grid[columnNum-1];
		int rowNum = 0;
		while (rows[rowNum] != 0) {
			rowNum++;
		}
		// Add the counter in that position.
		grid[columnNum-1][rowNum] = player;
		return rowNum+1;
	}

	/**
	 * Checks if the player won. Returns the positions for the counters which make up the win.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column from 1-7.
	 * @param rowNum The number of the row from 1-6.
	 * @return An array of 4 coordinates.
	 */
	public int[][] checkForWin(int player, int columnNum, int rowNum) {
		int list[][] = checkVertical(player, columnNum, rowNum);
		if (list[0][0] == 0) {
			list = checkHorizontal(player, columnNum, rowNum);
			if (list[0][0] == 0) {
				list = checkIncreaseDiagonal(player, columnNum, rowNum);
				if (list[0][0] == 0) {
					list = checkDecreaseDiagonal(player, columnNum, rowNum);
				}
			}
		}
		return list;
	}

	/**
	 * Reset the grid for a new game.
	 */
	public void resetGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 0;
			}
		}
	}

	//HELPER FUNCTIONS
	/**
	 * Checks vertically for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column from 1-7.
	 * @param rowNum The number of the row from 1-6.
	 * @return An array of 4 coordinates.
	 */
	private int getMinNum(int numOne, int numTwo) {
		if (numOne <= numTwo) {
			return numOne;
		} else {
			return numTwo;
		}
	}
	private int[][] checkVertical(int player, int columnNum, int rowNum) {
		int list[][] = new int[4][2];
		list[0][0] = columnNum;
		list[0][1] = rowNum;
		int counterNum = 1;
		for (int i = 1; i < (grid[columnNum - 1].length - rowNum); i++) {
			if (grid[columnNum - 1][rowNum - 1 + i] == player) {
				list[counterNum][0] = columnNum;
				list[counterNum][1] = rowNum + i;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < rowNum; i++) {
			if (grid[columnNum - 1][rowNum - 1 - i] == player) {
				list[counterNum][0] = columnNum;
				list[counterNum][1] = rowNum - i;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}

	/**
	 * Checks horizontally for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column from 1-7.
	 * @param rowNum The number of the row from 1-6.
	 * @return An array of 4 coordinates.
	 */
	private int[][] checkHorizontal(int player, int columnNum, int rowNum) {
		int list[][] = new int[4][2];
		list[0][0] = columnNum;
		list[0][1] = rowNum;
		int counterNum = 1;
		for (int i = 1; i < (grid.length - columnNum); i++) {
			if (grid[columnNum - 1 + i][rowNum - 1] == player) {
				list[counterNum][0] = columnNum + i;
				list[counterNum][1] = rowNum;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < columnNum; i++) {
			if (grid[columnNum - 1 - i][rowNum - 1] == player) {
				list[counterNum][0] = columnNum - i;
				list[counterNum][1] = rowNum;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}

	/**
	 * Checks increase diagonally for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column from 1-7.
	 * @param rowNum The number of the row from 1-6.
	 * @return An array of 4 coordinates.
	 */
	private int[][] checkIncreaseDiagonal(int player, int columnNum, int rowNum) {
		int list[][] = new int[4][2];
		list[0][0] = columnNum;
		list[0][1] = rowNum;
		int counterNum = 1;
		int topSpace = getMinNum(grid[columnNum - 1].length - rowNum, grid.length - columnNum);
		int bottomSpace = getMinNum(rowNum, columnNum);
		for (int i = 1; i < topSpace; i++) {
			if (grid[columnNum - 1 + i][rowNum - 1 + i] == player) {
				list[counterNum][0] = columnNum + i;
				list[counterNum][1] = rowNum + i;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < bottomSpace; i++) {
			if (grid[columnNum - 1 - i][rowNum - 1 - i] == player) {
				list[counterNum][0] = columnNum - i;
				list[counterNum][1] = rowNum - i;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}

	/**
	 * Checks decrease diagonally for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column from 1-7.
	 * @param rowNum The number of the row from 1-6.
	 * @return An array of 4 coordinates.
	 */
	private int[][] checkDecreaseDiagonal(int player, int columnNum, int rowNum) {
		int list[][] = new int[4][2];
		list[0][0] = columnNum;
		list[0][1] = rowNum;
		int counterNum = 1;
		int topSpace = getMinNum(grid[columnNum - 1].length - rowNum, columnNum);
		int bottomSpace = getMinNum(rowNum,  grid.length - columnNum);
		for (int i = 1; i < bottomSpace; i++) {
			if (grid[columnNum - 1 + i][rowNum - 1 - i] == player) {
				list[counterNum][0] = columnNum + i;
				list[counterNum][1] = rowNum - i;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < topSpace; i++) {
			if (grid[columnNum - 1 - i][rowNum - 1 + i] == player) {
				list[counterNum][0] = columnNum - i;
				list[counterNum][1] = rowNum + i;
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}
}
