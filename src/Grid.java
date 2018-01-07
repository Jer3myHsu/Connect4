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
   * @param player The number of the player; either 1 or 2
   * @param columnNum The number of the column inserted from 1-7.
   * @return An int from 1-6
   */
  public int insertCounter(int player, int columnNum) {
    return 0;
  }
  
  /**
   * Checks if the player won. Returns the positions for the counters which make up the win.
   * @param player The number of the player; either 1 or 2
   * @return An array of 4 coordinates
   */
  public int[][] checkForWin(int player) {
    return null;
  }
  
  //HELPER FUNCTIONS
  /**
   * Adds the inserted counter to the list of counter positions for the indicated player such
   * that it is sorted numerically based on x coordinate then y coordinate values.
   * @param player The number of the player; either 1 or 2
   */
  private void addToList(int player, int columnNum) {
    
  }
  
  /**
   * Checks vertically for a win for the indicated player.
   * @param player The number of the player; either 1 or 2
   * @return An array of 4 coordinates
   */
  private int[][] checkVertical(int player) {
    return null;
  }
  
  /**
   * Checks horizontally for a win for the indicated player.
   * @param player The number of the player; either 1 or 2
   * @return An array of 4 coordinates
   */
  private int[][] checkHorizontal(int player) {
    return null;
  }
  
  /**
   * Checks diagonally for a win for the indicated player.
   * @param player The number of the player; either 1 or 2
   * @return An array of 4 coordinates
   */
  private int[][] checkDiagonal(int player) {
    return null;
  }
  
  
}
