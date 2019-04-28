import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;

public class FibAndPrime
{

	// runs menu routine, exits if choice is 3
	// counts the total number of performances of each menu item and displays that upon exiting
	public static void main(String[] args)
	{
		// start the clock
		long startTime = System.currentTimeMillis();

		// default choice
		int choice = 0;
				
		// fibonacci metadata
		int fibonacciCounter = 0;
		long fibonacciStartTime = 0;
		long fibonacciEndTime = 0;
		
		// prime metadata
		int primeCounter = 0;
		long primeStartTime = 0;
		long primeEndTime = 0;
		
		MenuSelection menuSelection =  new MenuSelection(choice);

		while (choice != 3)
		{
			choice = menuSelection.setChoice();
			
			
			// increment the appropriate counter and set start time
			if (choice == 1)
			{
				fibonacciCounter++;
				fibonacciStartTime += System.currentTimeMillis();
			}
			if (choice == 2)
			{
				primeCounter++;
				primeStartTime += System.currentTimeMillis();
			}
			
			// perform the menu action associated with the choice
			menuSelection.runOperation();
			
			// stop the timers
			if (choice == 1)
			{
				fibonacciEndTime += System.currentTimeMillis();
			}
			if (choice == 2)
			{
				primeEndTime += System.currentTimeMillis();
			}
		}
		
		// set totals for timers, find end time, and create time format
		double fibonacciTotalTime = ((fibonacciEndTime - fibonacciStartTime) / 1000);
		double primeTotalTime = ((primeEndTime - primeStartTime) / 1000);
		long endTime = System.currentTimeMillis();
		DateFormat time = new SimpleDateFormat("HH':'mm':'ss");

		// Print fibonacci metadata
		System.out.print(fibonacciCounter + " Fibonacci command(s) yielding " +
						  menuSelection.fibonacciOutputs + " individual outputs requiring " +
						 fibonacciTotalTime + " seconds\n");
		
		// Print prime metadata
		System.out.print(primeCounter + " Prime command(s) yielding " +
				 		  menuSelection.primeOutputs + " Prime outputs requiring " +
						 primeTotalTime + " seconds\n");
		
		// print total elapsed time
		System.out.print("Program started at " + time.format(new Date(startTime)) +
						 " and terminated at " + time.format(new Date(endTime)));
	}
	
	// Tests whether user input is an integer
	private static boolean intCheck(String choiceAsString)
	{
		try
		{
			Integer.parseInt(choiceAsString);
			return true;
		}
		catch (Exception E)
		{
			return false;
		}
	}
	
	// Tests whether user input is a double
	private static boolean doubleCheck(String choiceAsString)
	{
		try
		{
			Double.parseDouble(choiceAsString);
			return true;
		}
		catch (Exception E)
		{
			return false;
		}
	}
	
	///////////////////
	// MENUSELECTION //
	///////////////////
	public static class MenuSelection
	{
		static Scanner input = new Scanner(System.in);

		int choice;
		int fibonacciOutputs = 0;
		int primeOutputs = 0;

		// constructor
		public MenuSelection(int choice)
		{
			this.choice = choice;
		}
		
		int setChoice()
		{
			// resets choice to 0 to prevent infinite loop
			choice = 0;
			
			// Prompts user to make a selection
			while (choice < 1 || choice > 3)
			{
				System.out.println("Choose a method:\n\n" +
								   "1 - Fibonnaci Number Iterator\n" +
								   "2 - Prime Number Iterator\n" +
								   "3 - Exit");
				

				String choiceAsString = input.next();
				
				// testing to ensure input is an integer
				if (intCheck(choiceAsString))
				{
					choice = Integer.parseInt(choiceAsString);
				}
			}
			
			return choice;
		}
		
		public void runOperation()
		{
			switch(choice)
			{
				// FIBONACCI OPERATION
				case 1: System.out.println("\n--Fibonacci Number Iterator--\n");
						System.out.print("Enter the limit value: ");
						String fibMaxAsString = input.next();
						
						// default max
						double fibMax = 0;
						
						// ensures that max is in double format, otherwise will
						// display 0.0 and return to menu
						if (doubleCheck(fibMaxAsString))
						{
							fibMax = Double.parseDouble(fibMaxAsString);
						}
						
						FibonacciIterator fibonacciIterator = new FibonacciIterator(fibMax);
						
						// converts results into an array and displays them
						ArrayList<Double> fibArray = fibonacciIterator.findNums();					
						displayResults(fibArray);
						
						// add the results to the fibonacci output counter
						for (int i = 0; i < fibArray.size(); i++)
						{
							fibonacciOutputs++;
						}
						break;
				
				// PRIME OPERATION
				case 2: System.out.println("\n--Prime Number Iterator--");
						System.out.print("Enter the limit value: ");
						String primeMaxAsString = input.next();
						
						// default max
						double primeMax = 0;
						
						// ensures that max is in double format, otherwise will
						// display 0.0 and return to menu
						if (doubleCheck(primeMaxAsString))
						{
							primeMax = Double.parseDouble(primeMaxAsString);
						}
						
						PrimeIterator primeIterator = new PrimeIterator(primeMax);
						
						// converts results into an array and displays them
						ArrayList<Double> primeArray = primeIterator.findNums();					
						displayResults(primeArray);
						
						// add the results to the prime output counter
						for (int i = 0; i < primeArray.size(); i++)
						{
							primeOutputs++;
						}
						break;
				
				// EXIT OPERATION
				case 3: System.out.println("\n--Exit--");
						break;
			}
		}
		
		// Outputs results of the arraylist in lines of five, tab-spaced
		private static void displayResults(ArrayList<Double> list)
		{
			ArrayList<String> displayList = new ArrayList<String>();
			
			for (int i = 0; i < list.size(); i++)
			{
				displayList.add(Double.toString(list.get(i)));
			}
			
			// prevents breaking when total outputs would not be a multiple of 5
			if (displayList.size() % 5 != 0)
			{
				for (int i = 0; i < displayList.size() % 5; i++)
				{
					displayList.add("");
				}
			}
			
			ListIterator<String> listIterator = displayList.listIterator(displayList.size());
			
			// print in descending order
			while (listIterator.hasPrevious())
			{
				System.out.print(listIterator.previous() + "	" +
								 listIterator.previous() + "	" +
								 listIterator.previous() + "	" +
								 listIterator.previous() + "	" +
								 listIterator.previous() + "\n");
			}
			
			System.out.print("\n");
		}
	}

	
	///////////////
	// FIBONACCI //
	///////////////
	public static class FibonacciIterator
	{
		double max;
		
		// constructor
		public FibonacciIterator(double max)
		{
			this.max = max;
		}
		
		public ArrayList<Double> findNums()
		{
			ArrayList<Double> array = new ArrayList<Double>();
	        
			// adds the first fibonacci value
	        array.add(0.0);
	        
	        // prevents queries for less than 1
	        if (max <= 0)
	        {
	            return array;
	        }
	        
	        // adds the second and third fibonacci values
	        array.add(1.0);
	        array.add(1.0);
	        
	        double a = 1;
	        double b = 1;
	        
	        // adds the previous two values in the fibonacci sequence together
	        for (double i = 1; i + a < max;)
	        {
	        	b = i + a;
	            i = a;
	            a = b;
	            
	            //adds sum to the array
	            array.add(b);
	        }
	        
	        return array;
	    }
	}
	
	
	///////////
	// PRIME //
	///////////
	public static class PrimeIterator
	{
		double max;
		
		// constructor
		public PrimeIterator(double max)
		{
			this.max = max;
		}
		
		public ArrayList<Double> findNums()
		{
			ArrayList<Double> array = new ArrayList<Double>();
		        
	        // prevents queries for less than 1
			if (max <= 1)
		    {
				array.add(0.0);
				return array;
		    }
		        
			// iterates through the every number up to the max
			for (double i = 2; i <= max; i++)
			{
				if (primeCheck(i))
				{
					// adds all prime numbers to the array
					array.add(i);
				}
			}
		         
			return array;
		}
		    
		public boolean primeCheck(double n)
		{
			// checks to see if the number can be evenly divided by any number other than
			// itself or one
			for (double i = 2; i <= Math.sqrt(n); i++)
			{
				// if number is divisible by anything, it does not pass the prime check
				if (n % i == 0)
				{
					return false;
				}
			}
		       
			return true;
		}
	}
}
