import java.util.*;

public class ColumnCheck implements Runnable {
  String[][] boardRef;
  List<Tile> inc;
  int currCol;
  int currRow;

  public ColumnCheck(String[][] br, List<Tile> incList) {
    boardRef = br;
    currCol = 0;
    currRow = 0;
    inc = incList;
  }

  public void run() {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        String currNum = boardRef[j][i];
        currCol = i + 1;
        currRow = j + 1;

        for (int k = 0; k < 9; k++) { //k = j + 1?
          if (j == k)
            continue;
          if (currNum.equals(boardRef[k][i])) {
            // System.out.println("Column duplicate " + currNum + " found.");
            // System.out.println("Row: " + currRow + " Column: " + currCol +
            //   " and " + "Row: " + (k + 1) + " Column: " + (i + 1));

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
