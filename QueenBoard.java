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
    board[r][c] = -1;
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        if (board[x][y] == -1){
          return false;
        }
        if (x == r){
          if (board[x][y] == -1 && c != y){
            return false;
          }
          else{
            board[x][y] ++;
          }
        }
        if (y == c){
          if (board[x][y] == -1 && r != x){
            return false;
          }
          else{
            board[x][y] ++;
          }
        }
        if (r-c == x-y){
          if (board[x][y] == -1 && r != x && c != y){
            return false
          }
          else{
            board[x][y] ++;
          }
        }
      }
    }
    return true;
  }

  private boolean removeQueen(int r, int c){
    board[r][c] = 0;
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        if (x == r&& board[x][y] != -1){
            board[x][y] --;
        }
        if (y == c && board[x][y] != -1){
            board[x][y] --;
        }
        if (r-c == x-y && board[x][y] != -1){
            board[x][y] --;
        }
      }
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
        if (board[y][x] = -1){
          output += "Q ";
        }
        else{
          output += "_ ";
        }
      }
      output += "\n";
    }
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    for (int y = 0; y < board.length;){
      for (int x = 0; x < board[0].length){
        if (board[x][y] == 0){
          if (addQueen(x, y)){
            y += 1;
            x = 0;
          }
          else{
            removeQueen(x, y);
            x += 1;
          }
        }
      }
    }
    else{
      for (int y = 0; y < board.length; y++){
        for (int x = 0; x < board[0].length; x++){
          board[y][x] = 0;
      }
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int y = 0; y < board.length; y++){
      for (int x = 0; x < board[0].length; x++){
        board[y][x] = 0;
      }
    }
  }

}
