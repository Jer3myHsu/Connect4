import java.util.ArrayList;

public class Test {
  
  public static void main(String args[]){
    Grid grid = new Grid();
    int rowNum = grid.insertCounter(1, 1);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    
    rowNum = grid.insertCounter(1, 1);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    
    rowNum = grid.insertCounter(1, 7);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    
    rowNum = grid.insertCounter(1, 7);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    grid.checkForWin(1, 7, 2);
    
    rowNum = grid.insertCounter(1, 1);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    
    rowNum = grid.insertCounter(1, 2);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    
    rowNum = grid.insertCounter(1, 1);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");
    
    rowNum = grid.insertCounter(1, 2);
    System.out.println("Item added at row: " + rowNum);
    System.out.println("");

  }
}
