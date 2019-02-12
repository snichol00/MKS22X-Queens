public class QueenBoard{
  private int[][]board;

  public QueenBoard(int size){
    board = new int[size][size];
    for (int y = 0; y < size; y++){
      for (int x = 0; x < size; x++){
        board[y][x] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if (board[r][c] != 0){
      return false;
    }
    board[r][c] = -1;
    for (int y = 0; y < board.length; y++){
      if (y != c){
        board[r][y]++;
      }
    }
    for (int x = 0; x < board[0].length; x++){
      if (x != r){
        board[x][c] ++;
      }
    }
    int x1 = r + 1;
    for (int y = c + 1; y < board.length && x1 < board.length; y++){
      board[x1][y] ++;
      x1++;
    }
    int x2 = r - 1;
    for (int y = c + 1; y < board.length && x2 >= 0; y++){
      board[x2][y] ++;
      x2--;
    }
    int x3 = r + 1;
    for (int y = c - 1; y >= 0 && x3 < board.length; y--){
      board[x3][y] ++;
      x3++;
    }
    int x4 = r - 1;
    for (int y = c - 1; y >= 0 && x4 >= 0; y--){
      board[x4][y] ++;
      x4--;
    }
    return true;
  }

  private boolean removeQueen(int r, int c){
    if (board[r][c] != -1){
      return false;
    }
    board[r][c] = 0;
    for (int y = 0; y < board.length; y++){
      if (y != c){
        board[r][y]--;
      }
    }
    for (int x = 0; x < board[0].length; x++){
      if (x != r){
        board[x][c] --;
      }
    }
    int x1 = r + 1;
    for (int y = c + 1; y < board.length && x1 < board.length; y++){
      board[x1][y] --;
      x1++;
    }
    int x2 = r - 1;
    for (int y = c + 1; y < board.length && x2 >= 0; y++){
      board[x2][y] --;
      x2--;
    }
    int x3 = r + 1;
    for (int y = c - 1; y >= 0 && x3 < board.length; y--){
      board[x3][y] --;
      x3++;
    }
    int x4 = r - 1;
    for (int y = c - 1; y >= 0 && x4 >= 0; y--){
      board[x4][y] --;
      x4--;
    }
    return true;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String output = "";
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        if (board[y][x] == -1){
          output += "Q ";
        }
        else{
          output += "_ ";
        }
      }
      output += "\n";
    }
    return output;
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        if (board[x][y] != 0){
          throw new IllegalStateException();
        }
      }
    }
    return solveRow(0);
  }

  public boolean solveRow(int row){
    if (row == board[0].length){
      return true;
    }
    for (int col = 0; col < board[0].length; col++){
      if (addQueen(row, col)){
        if (solveRow(row + 1)){
          return true;
        }
        removeQueen(row, col);
      }
    }
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        if (board[x][y] != 0){
          throw new IllegalStateException();
        }
      }
    }
    return countSolutions(0);
  }

    public int countSolutions(int row){
      int count = 0;
      if (row == board.length){
        return 1;
      }
      for (int col = 0; col < board.length; col++){
        if (addQueen(row, col)){
          count += countSolutions(row + 1);
        }
        removeQueen(row, col);
      }
      return count;
    }

}
