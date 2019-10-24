import java.util.*;
import java.util.Arrays;

public class GridCheck implements Runnable {
  String[][] boardRef;
  List<Tile> inc;
  int currCol;
  int currRow;

  public GridCheck(String[][] br, List<Tile> incList) {
    boardRef = br;
    currCol = 0;
    currRow = 0;
    inc = incList;
  }

  public void run() {
    for (int i = 0; i <= 6; i = i + 3) {
      for(int j = 0; j <= 6; j = j + 3) {
        checkSubgrid(i,j);
      }
    }
  }

//checks a subgrid for duplicates when given a starting row / column offset
  private void checkSubgrid(int rs, int cs) {

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        String currNum = boardRef[rs + i][cs + j];
        currRow = rs + i + 1;
        currCol = cs + j + 1;
//        System.out.println("Checking: " + currNum);
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
//            System.out.println("comparing with: " + boardRef[rs + k][cs + l]);
            if ((i == k) && (j == l))
              continue;
            if (currNum.equals(boardRef[rs + k][cs + l])) {
              // System.out.println("Grid duplicate " + currNum + " found.");
              // System.out.println("Row: " + currRow + " Column: " + currCol +
              //   " and " + "Row: " + (rs + k + 1) + " Column: " + (cs + l + 1));

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
}
