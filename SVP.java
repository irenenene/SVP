/*Main thread*/
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.*;
import java.util.Collections;


public class SVP {
  public static void main(String[] args) {
    Scanner inFile;
    String[][] board = new String[9][];
    List<Tile> incorrect = Collections.synchronizedList(new ArrayList<Tile>());

    try {
      inFile = new Scanner(new File(args[0]));
      int lineCount = 0;

      while (lineCount < 9 && inFile.hasNextLine()) {
        String line = inFile.nextLine();
        board[lineCount] = line.split(",");
        lineCount++;
      }
    }
    catch (IOException e) {
      System.out.println("Error reading from file.");
    }

    Thread rowThread = new Thread(new RowCheck(board, incorrect));
    rowThread.start();

    Thread colThread = new Thread(new ColumnCheck(board, incorrect));
    colThread.start();

    Thread gridThread = new Thread(new GridCheck(board, incorrect));
    gridThread.start();

    try {

      rowThread.join();

      colThread.join();

      gridThread.join();

    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }

    System.out.println("Tiles with duplicates:");
    for (int i = 0; i < incorrect.size(); i++) {
      System.out.println(incorrect.get(i));
    }
    System.out.println("Solution: ");
    for (int i = 0; i < incorrect.size(); i++) {
      fixTile(incorrect.get(i).getRow(), incorrect.get(i).getCol(), board);
    }
  }

/*given a set of tile coords (1-based), will find the correct value of the tile*/
  public static void fixTile(int row, int col, String[][] bd) {
    isMissing("1", row, col, bd);
    isMissing("2", row, col, bd);
    isMissing("3", row, col, bd);
    isMissing("4", row, col, bd);
    isMissing("5", row, col, bd);
    isMissing("6", row, col, bd);
    isMissing("7", row, col, bd);
    isMissing("8", row, col, bd);
    isMissing("9", row, col, bd);
  }

//returns true if the number if not in column AND row;
  public static void isMissing(String num, int r, int c, String[][] b) {
    Boolean notInRow = true;
    Boolean notInCol = true;
    for (int i = 0; i < 9; i++) {
      if (num.equals(b[r-1][i]))
        notInRow = false;
    }
    for (int j = 0; j < 9; j++) {
      if (num.equals(b[j][c-1]))
        notInCol = false;
    }

    if (notInRow && notInCol)
      System.out.println("Value: " + num + " should be in row: " + r + " col: " + c);
  }
}
