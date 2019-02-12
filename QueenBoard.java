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
    //checks that square is clear
    if (board[r][c] != 0){
      return false;
    }
    //sets to queen
    board[r][c] = -1;
    //blocks squares in the same row
    for (int y = 0; y < board.length; y++){
      if (y != c){
        board[r][y]++;
      }
    }
    // blocks squares in the same column
    for (int x = 0; x < board[0].length; x++){
      if (x != r){
        board[x][c] ++;
      }
    }
    //blocks diagonals
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
    //checks that square is a queen
    if (board[r][c] != -1){
      return false;
    }
    //sets to clear
    board[r][c] = 0;
    // clears row
    for (int y = 0; y < board.length; y++){
      if (y != c){
        board[r][y]--;
      }
    }
    //clears column
    for (int x = 0; x < board[0].length; x++){
      if (x != r){
        board[x][c] --;
      }
    }
    //clears diagonals
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
        //checks that board starts out empty
        if (board[x][y] != 0){
          throw new IllegalStateException();
        }
      }
    }
    //calls helper
    return solveRow(0);
  }

  public boolean solveRow(int row){
    // if it reaches last row, then return true because all queens have been placed
    if (row == board[0].length){
      return true;
    }
    // checks each square in the row
    for (int col = 0; col < board[0].length; col++){
      //tries to add queen
      if (addQueen(row, col)){
        //calls the next row to check if it can place queen
        if (solveRow(row + 1)){
          return true;
        }
        // if next queen can't be placed, remove prior queen
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
        //makes sure that board starts off with all zeroes
        if (board[x][y] != 0){
          throw new IllegalStateException();
        }
      }
    }
    //calls helper
    return countSolutions(0);
  }

    public int countSolutions(int row){
      //counter
      int count = 0;
      //if it reaches the last row, a solution was found, so add 1 to count by returning 1
      if (row == board.length){
        return 1;
      }
      //checks each square in the row
      for (int col = 0; col < board.length; col++){
        // if it can add a queen, continue with next row
        if (addQueen(row, col)){
          count += countSolutions(row + 1);
        }
        // if it can't add queen, remove the last queen as well
        removeQueen(row, col);
      }
      return count;
    }

}
