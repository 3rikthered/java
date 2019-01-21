/*
 * I declare that all material in this assessment task is my own work
 * except where there is clear acknowledgement or reference to the work
 * of others. I further declare that I have complied with, and agree to
 * abide by, the UIS Academic Integrity Policy at the University website.
 * http://www.uis.edu/academicintegrity
 * 
 * Author’s Name: Erik Nelson UID: ******** Date: 01/20/2019
 */


/* 
 * I had no previous experience filling empty arrays with a default value
 * before this assignment. Because of this, I had to look up how to do so,
 * and found a solution on stackoverflow, utilizing Arrays.fill. I had to
 * modify the code very slightly, in that my array is of chars rather than
 * doubles.
 * 
 * Listed below is the referenced site, using the solution provided by
 * aioobe on 08/19/2011
 * 
 * https://stackoverflow.com/questions/7118178/arrays-fill-with-multidimensional-array-in-java
 */


package nelsonTask1;

import java.util.Arrays;

public class nelsonTask1
{
	public static void main(String[] args)
	{
	    /* 
	     * Create a 2-dimensional array representing the 8x8 table.
	     */
	    char[][] queenArray = new char[8][8];
	    
	    /*
	     *  Fill in the empty spaces in the array, just so the table doesn't
	     *  look awful when printed out; Must be done BEFORE placing the
	     *  queens, so they don't get overwritten.
	     */
	    for (char[] row: queenArray)
	    {
	    		Arrays.fill(row, ' ');
	    }
	    
	    /*
	     * Place the queens into their positions in the array.
	     */
	    queenArray[0][3] = 'Q';
	    queenArray[1][6] = 'Q';
	    queenArray[2][2] = 'Q';
	    queenArray[3][7] = 'Q';
	    queenArray[4][1] = 'Q';
	    queenArray[5][4] = 'Q';
	    queenArray[6][0] = 'Q';
	    queenArray[7][5] = 'Q';

	    /* 
	     * Simple array printer: look at each row, print whatever is in each
	     * column, add some space, then iterate down to the next row.
	     */
	    for (int row = 0; row < 8; row++) // iterate through the rows
	    {
	    		System.out.print("| "); // print the first spacer for each row
	    		for (int col = 0; col < 8; col++)// iterate through the columns
	    		{
	    			System.out.print(queenArray[row][col] + " | "); // print the contents + spacer
	    		}
	    		System.out.println(); // make sure each row is on a new line
	    }
	}
}
