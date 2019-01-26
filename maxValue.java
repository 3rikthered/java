import java.util.Scanner;

public class nelsonTask1
{
	// Main method
	public static void main(String[] args)
	{
		// initialize scanner
		Scanner input = new Scanner(System.in);
		
		// initialize rows and columns
		int arrayRows;
		int arrayColumns;
		
		// prompt for total rows
		System.out.print("How many rows are in this array? ");
		arrayRows = input.nextInt();
		
		// prompt for total columns
		System.out.print("How many columns are in this array? ");
		arrayColumns = input.nextInt();
		
		// generate array
		double[][] a = new double[arrayRows][arrayColumns];
		
		// prompt for array values and place them in the array
		for (int rows = 0; rows < a.length; rows++)
		{
			System.out.println("Please enter " + arrayColumns +
							   " values for row " + (rows + 1) +
							   ": ");
			for (int columns = 0; columns < a[0].length; columns++)
			{
				a[rows][columns] = input.nextDouble();
			}
		}
		
		// Close the stinkin' scanner!!
		input.close();

		// Use the locateLargest method on the array
		Location maxValueOfA = locateLargest(a);
		
		// Print the results
		System.out.print("The largest element is " + maxValueOfA.maxValue +
				         ". It is located at (" + maxValueOfA.row +
				         ", " + maxValueOfA.column + ")");
		
	}

	
	// locateLargest method
	public static Location locateLargest(double[][] a)
	{
		// Create an instance of the Location class
		// initial values must all be zero
		Location location = new Location(0, 0, 0);
		
		for (int rows = 0; rows < a.length; rows++)
		{
			for (int columns = 0; columns < a[0].length; columns++)
			{
				if (a[rows][columns] > location.maxValue)
				{
					location.row = rows;
					location.column = columns;
					location.maxValue = a[rows][columns];
				}
			}
		}
		return location;
	}
}

public class Location
{
	public int row;
	public int column;
	public double maxValue;
	
	// Constructor
	Location(int row, int column, double maxValue)
	{
		this.row = row;
		this.column = column;
		this.maxValue = maxValue;
	}
}
