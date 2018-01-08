import java.util.ArrayList;

public class Grid {
	// grid represents the playing grid.
	// p#Counters represents a list of the appropriate player's counter
	// positions.
	private int[][] grid = new int[7][6];
	private ArrayList<int[]> p1Counters = new ArrayList<int[]>();
	private ArrayList<int[]> p2Counters = new ArrayList<int[]>();

	// FUNCTIONS TO BE USED IN Main.java
	/**
	 * Return y coordinate of the position of the counter inserted.
	 * @param player The number of the player; either 1 or 2.
	 * @param columnNum The number of the column inserted into from 1-7.
	 * @return An int from 1-6 representing the row number.
	 */
	public int insertCounter(int player, int columnNum) {
		int rowNum;
		if(player == 1) {
			rowNum = addToList(p1Counters, columnNum);
		} else {
			rowNum = addToList(p2Counters, columnNum);
		}
		grid[columnNum-1][rowNum-1] = player;
		return rowNum;
	}

	/**
	 * Checks if the player won. Returns the positions for the counters which make up the win.
	 * @param player The number of the player; either 1 or 2.
	 * @return An array of 4 coordinates.
	 */
	public int[][] checkForWin(int player, int col, int row) {
		int list[][] = checkVertical(player, col, row);
		if (list[0][0] == 0) {
			list = checkHorizontal(player, col, row);
			if (list[0][0] == 0) {
				list = checkIncreaseDiagonal(player, col, row);
				if (list[0][0] == 0) {
					list = checkDecreaseDiagonal(player, col, row);
				}
			}
		}
		return list;
	}

	//HELPER FUNCTIONS
	/**
	 * Adds the inserted counter to the list of counter positions for the indicated player such
	 * that it is sorted numerically based on the column number then y row number. Returns
	 * the row number of the counter added.
	 * @param pCounters A list of the player's counter positions.
	 * @param int columnNum The number of the column inserted into from 1-7.
	 * @return An int from 1-6 representing the row number.
	 */
	private int addToList(ArrayList<int[]> pCounters, int columnNum) {
		// Find the first empty row in that column.
		int[] rows = grid[columnNum-1];
		int rowNum = 0;
		while (rows[rowNum] != 0) {
			rowNum++;
		}
		// Add the counter to pCounters.
		int[] addedPosition = new int[2];
		addedPosition[0] = columnNum;
		addedPosition[1] = rowNum+1;
		
		int size = pCounters.size();
	    int i = 0;
	    while (i != size && columnNum > pCounters.get(i)[0]) {
	      i++;
	    }
	    
		pCounters.add(addedPosition);
		System.out.println("Items in pCounters:"); // REMOVE from here to
		for (int[] j: pCounters){
			System.out.println(" {" + j[0] + "," + j[1] + "}");
		} // HERE

		return rowNum+1;
	}

	/**
	 * Checks vertically for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @return An array of 4 coordinates.
	 */
	private int[][] checkVertical(int player, int col, int row) {
		int counterNum = 0;
		int list[][] = new int[4][2];
		list[counterNum][0] = col;
		list[counterNum][1] = row;
		int i = 1;
		counterNum = 1;
		try {
			while (true) {
				if (grid[col - 1][row - 1 + i] == player) {
					list[counterNum][0] = col;
					list[counterNum][1] = row + i;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			while (true) {
				if (grid[col - 1][row - 1 - i] == player) {
					list[counterNum][0] = col;
					list[counterNum][1] = row - i;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
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
	 * @return An array of 4 coordinates.
	 */
	private int[][] checkHorizontal(int player, int col, int row) {
		int counterNum = 0;
		int list[][] = new int[4][2];
		list[counterNum][0] = col;
		list[counterNum][1] = row;
		int i = 1;
		counterNum = 1;
		try {
			while (true) {
				if (grid[col - 1 + i][row - 1] == player) {
					list[counterNum][0] = col + i;
					list[counterNum][1] = row;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			while (true) {
				if (grid[col - 1 - i][row - 1] == player) {
					list[counterNum][0] = col - i;
					list[counterNum][1] = row;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}

	/**
	 * Checks diagonally for a win for the indicated player.
	 * @param player The number of the player; either 1 or 2.
	 * @return An array of 4 coordinates.
	 */
	private int[][] checkIncreaseDiagonal(int player, int col, int row) {
		int counterNum = 0;
		int list[][] = new int[4][2];
		list[counterNum][0] = col;
		list[counterNum][1] = row;
		int i = 1;
		counterNum = 1;
		try {
			while (true) {
				if (grid[col - 1 + i][row - 1 + i] == player) {
					list[counterNum][0] = col + i;
					list[counterNum][1] = row + i;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			while (true) {
				if (grid[col - 1 - i][row - 1 - i] == player) {
					list[counterNum][0] = col - i;
					list[counterNum][1] = row - i;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}
	private int[][] checkDecreaseDiagonal(int player, int col, int row) {
		int counterNum = 0;
		int list[][] = new int[4][2];
		list[counterNum][0] = col;
		list[counterNum][1] = row;
		int i = 1;
		counterNum = 1;
		try {
			while (true) {
				if (grid[col - 1 + i][row - 1 - i] == player) {
					list[counterNum][0] = col + i;
					list[counterNum][1] = row - i;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		try {
			while (true) {
				if (grid[col - 1 - i][row - 1 + i] == player) {
					list[counterNum][0] = col - i;
					list[counterNum][1] = row + i;
					counterNum++;
					i++;
				} else {
					break;
				}
				if (counterNum >= 4) {
					return list;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		for (int j = 0; j < list.length; j++) {
			for (int k = 0; k < list[j].length; k++) {
				list[j][k] = 0;
			}
		}
		return list;
	}
	public void resetGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = 0;
			}
		}
	}
}
