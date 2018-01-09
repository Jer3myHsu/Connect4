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
		int list[][] = toDoubleArray(checkVertical(player, columnNum, rowNum));
		if (list[0][0] == 0) {
			list = toDoubleArray(checkHorizontal(player, columnNum, rowNum));
			if (list[0][0] == 0) {
				list = toDoubleArray(checkIncreaseDiagonal(player, columnNum, rowNum));
				if (list[0][0] == 0) {
					list = toDoubleArray(checkDecreaseDiagonal(player, columnNum, rowNum));
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
	 * Gets the smallest number
	 * @param Number A
	 * @param Number B
	 * @return Smallest number A and B.
	 */
	private int getMinNum(int numOne, int numTwo) {
		if (numOne <= numTwo) {
			return numOne;
		} else {
			return numTwo;
		}
	}
	private int[][] toDoubleArray(ArrayList<int[]> arrayList) {
		int array[][] = new int[arrayList.size()][2];
		for (int i = 0; i < arrayList.size(); i++) {
			for (int j = 0; j < arrayList.get(i).length; j++) {
				array[i][j] = arrayList.get(i)[j];
			}
		}
		return array;
	}
	/**
	 * Checks vertically for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column from 1-7.
	 * @param rowNum The number of the row from 1-6.
	 * @return An array of 4 coordinates.
	 */
	private ArrayList<int[]> checkVertical(int player, int columnNum, int rowNum) {
		int firstCoordinate[] = {columnNum, rowNum};
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(firstCoordinate);
		int counterNum = 1;
		for (int i = 1; i < (grid[columnNum - 1].length - rowNum); i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1][rowNum - 1 + i] == player) {
				coordinate[0] = columnNum;
				coordinate[1] = rowNum + i;
				list.add(coordinate);
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < rowNum; i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1][rowNum - 1 - i] == player) {
				coordinate[0] = columnNum;
				coordinate[1] = rowNum - i;
				list.add(coordinate);
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < list.size(); j++) {
			for (int k = 0; k < list.get(j).length; k++) {
				list.get(j)[k] = 0;
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
	private ArrayList<int[]> checkHorizontal(int player, int columnNum, int rowNum) {
		int firstCoordinate[] = {columnNum, rowNum};
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(firstCoordinate);
		int counterNum = 1;
		for (int i = 1; i <= (grid.length - columnNum); i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1 + i][rowNum - 1] == player) {
				coordinate[0] = columnNum + i;
				coordinate[1] = rowNum;
				list.add(coordinate);
				counterNum++;
			} else {
				break;
			}
		}
		for (int i = 1; i < columnNum; i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1 - i][rowNum - 1] == player) {
				coordinate[0] = columnNum - i;
				coordinate[1] = rowNum;
				list.add(coordinate);
				counterNum++;
			} else {
				break;
			}
		}
		if (counterNum >= 4) {
			return list;
		}
		for (int j = 0; j < list.size(); j++) {
			for (int k = 0; k < list.get(j).length; k++) {
				list.get(j)[k] = 0;
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
	private ArrayList<int[]> checkIncreaseDiagonal(int player, int columnNum, int rowNum) {
		int firstCoordinate[] = {columnNum, rowNum};
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(firstCoordinate);
		int counterNum = 1;
		int topSpace = getMinNum(grid[columnNum - 1].length - rowNum, grid.length - columnNum);
		int bottomSpace = getMinNum(rowNum, columnNum);
		for (int i = 1; i <= topSpace; i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1 + i][rowNum - 1 + i] == player) {
				coordinate[0] = columnNum + i;
				coordinate[1] = rowNum + i;
				list.add(coordinate);
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int i = 1; i < bottomSpace; i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1 - i][rowNum - 1 - i] == player) {
				coordinate[0] = columnNum - i;
				coordinate[1] = rowNum - i;
				list.add(coordinate);
				counterNum++;
				if (counterNum >= 4) {
					return list;
				}
			} else {
				break;
			}
		}
		for (int j = 0; j < list.size(); j++) {
			for (int k = 0; k < list.get(j).length; k++) {
				list.get(j)[k] = 0;
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
	private ArrayList<int[]> checkDecreaseDiagonal(int player, int columnNum, int rowNum) {
		int firstCoordinate[] = {columnNum, rowNum};
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(firstCoordinate);
		int counterNum = 1;
		int topSpace = getMinNum(grid[columnNum - 1].length - rowNum, columnNum);
		int bottomSpace = getMinNum(rowNum,  grid.length - columnNum);
		for (int i = 1; i < bottomSpace; i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1 + i][rowNum - 1 - i] == player) {
				coordinate[0] = columnNum + i;
				coordinate[1] = rowNum - i;
				list.add(coordinate);
				counterNum++;
			} else {
				break;
			}
		}
		for (int i = 1; i < topSpace; i++) {
			int coordinate[] = new int[2];
			if (grid[columnNum - 1 - i][rowNum - 1 + i] == player) {
				coordinate[0] = columnNum - i;
				coordinate[1] = rowNum + i;
				list.add(coordinate);
				counterNum++;
			} else {
				break;
			}
		}
		if (counterNum >= 4) {
			return list;
		}
		for (int j = 0; j < list.size(); j++) {
			for (int k = 0; k < list.get(j).length; k++) {
				list.get(j)[k] = 0;
			}
		}
		return list;
	}
}
