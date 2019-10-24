import java.util.*;

public class RowCheck implements Runnable {
  String[][] boardRef;
  List<Tile> inc;
  int currCol;
  int currRow;

  public RowCheck(String[][] br, List<Tile> incList) {
    boardRef = br;
    currCol = 0;
    currRow = 0;
    inc = incList;
  }

  public void run() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        String currNum = boardRef[i][j];
        currRow = i + 1;
        currCol = j + 1;
        for (int k = 0; k < 9; k++) {
          if (j == k)
            continue;
          if (currNum.equals(boardRef[i][k])) {
            // System.out.println("Row duplicate " + currNum + " found.");
            // System.out.println("Row: " + currRow + " Column: " + currCol +
            //   " and " + "Row: " + (i + 1) + " Column: " + (k + 1));

            //add to the list of incorrect tiles
            Tile newTile = new Tile(currRow, currCol, currNum);
            if (!inc.contains(newTile)) {
              inc.add(newTile);
            }
          }
        }
      }
    }
  }
}
