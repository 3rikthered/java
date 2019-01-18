import java.lang.Math;

class Main {
  public static void main(String[] args){

    // Creates a single-row array of all possible positions on an 8x8 grid
    int[] possibleLocations = new int[64];
    for (int i = 0; i < 64; i++){
      possibleLocations[i] = i;
    }

    // Creates the 2-dimensional array representing the 8x8 grid
    char[][] queenArray = new char[8][8];

    for (int i = 0; i < 8; i++){
      int random = (int)(Math.random() * (64 - i - 1));
      int queenLocation = possibleLocations[random];
      possibleLocations[random] = possibleLocations[64 - i - 1];
      int row = queenLocation / 8;
      int col = queenLocation % 8;
      queenArray[row][col] = 'Q';
    }

    // Displays the grid
    for (int row = 0; row < 8; row++){
      for (int col = 0; col < 8; col++){
        System.out.print(queenArray[row][col] + " ");
      }
      System.out.println();
    }
  }
}
