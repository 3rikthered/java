import java.util.Scanner;

class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    Location locationFinder = new Location();

    System.out.print("Array dimensions: How many rows? ");
    int arrayRows = input.nextInt();

    System.out.print("Array dimensions: How many columns? ");
    int arrayColumns = input.nextInt();

    // Generate the array
    double[][] a = new double[arrayRows][arrayColumns];

    // assign user input values for each spot in the array
    for (int row = 0; row < a.length; row++)
    {
      System.out.print("Please enter " + arrayColumns + " values for row " + (row + 1) + ": ");
      for (int column = 0; column < a[0].length; column++)
      {
        array[row][column] = input.nextDouble();
      }
    }

    System.out.print(Location(a));
  }
}

class Location
{
  int row;
  int column;
  double maxValue;
}
