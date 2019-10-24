public class Tile {
  public int row;
  public int column;
  public String currValue;
  public int suggestedValue;

  public Tile(int r, int c, String v) {
    row = r;
    column = c;
    currValue = v;
    suggestedValue = 0;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return column;
  }

  @Override
  public boolean equals(Object other) {
    return (row == ((Tile)other).row && column == ((Tile)other).column);
  }

  @Override
  public String toString() {
    return "Row: " + row + " Column: " + column + " Value: " + currValue;
  }
}
