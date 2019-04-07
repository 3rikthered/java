import java.util.Scanner;

public class Conversions {
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {			
			
		// If the user selects 5, the program exits
		int choice = 0;		
		while (choice != 5)
		{
			choice = setChoice();
			if (choice == 5)
			{
				break;
			}
			int boundary = setBoundary();
			menu(choice, boundary);
		}			
	}
	
	// Offers user the ability to choose a routine
	public static int setChoice()
	{
		int choice = 0;
		
		// Choice must be between 1 and 5, inclusive
		while (choice < 1 || choice > 5)
		{
			System.out.println("Choose a method:\n\n" +
							   "1 - String permutation\n" +
							   "2 - hex to decimal\n" +
							   "3 - binary to decimal\n" +
							   "4 - decimal to hex\n" +
							   "5 - exit\n");
			
			choice = input.nextInt();
		}
		
		return choice;
	}
	
	// Warns user of recursive program complexity and sets a user-defined character limit
	public static int setBoundary()
	{
		System.out.println("Recursive programs can quickly get out of hand complexity wise. " +
				   		   "Set an upper bound on input size.\n" +
				   		   "WARNING: inputs of greater than 6 digits/chars may result in slower processing.");
		return input.nextInt();
	}
	
	// Based on the routine the user chose, prompts the user for the routine's requirements.
	// User's input cannot be greater than the character limit they set.
	public static void menu(int choice, int boundary)
	{
		// Choice 1: display all permutations of a string
		if (choice == 1)
		{
			System.out.println("Enter a string: ");
			String stringValue = input.next();
			
			// tests character limit
			while (stringValue.length() > boundary)
			{
				System.out.println("Enter a string: ");
				stringValue = input.next();	
			}
						
			displayPermutation(stringValue);
		}
		
		// Choice 2: converts a hex number to a decimal
		else if (choice == 2)
		{
			System.out.println("Enter a hex number: ");
			String stringValue = input.next();
			
			// returns true if input only contains hex digits
			boolean hexTest = stringValue.matches("-?[0-9a-fA-F]+");
			
			// tests character limit and string content
			while(stringValue.length() > boundary || !hexTest)
			{
				System.out.println("Enter a hex number: ");
				stringValue = input.next();
				hexTest = stringValue.matches("-?[0-9a-fA-F]+");
			}
			
			// performs hex2Dec on string and ensures there are no case issues
			System.out.println("The decimal equivalent is " + hex2Dec(stringValue.toUpperCase()));
		}
		
		// Choice 3: converts a binary number to a decimal
		else if (choice == 3)
		{
			System.out.println("Enter a binary number: ");
			String stringValue = input.next();
			
			// returns true if input only contains binary digits
			boolean binTest = stringValue.matches("-?[0-1]+");
			
			// tests character limit and string content
			while(stringValue.length() > boundary || !binTest)
			{
				System.out.println("Enter a binary number: ");
				stringValue = input.next();
				binTest = stringValue.matches("-?[0-1]+");
			}
						
			System.out.println("The decimal equivalent is " + bin2Dec(stringValue) + "\n");
		}
		
		// Choice 4: converts a decimal to a hex number
		else if (choice == 4)
		{
			System.out.println("Enter an integer: ");
			String stringValue = input.next();
			
			// returns true if input only contains decimal values
			boolean intTest = stringValue.matches("-?[0-9]+");
						
			// tests character limit and string content
			while (stringValue.length() > boundary || !intTest)
			{
				System.out.println("Enter an integer: ");
				stringValue = input.next();
				intTest = stringValue.matches("-?[0-9]+");
			}
			
			// converts the number to an integer
			int intValue = Integer.valueOf(stringValue);
						
			System.out.println("The hex equivalent is " + dec2Hex(intValue) + "\n");
		}
	}
	
	////////////////////////////
	// 1. PERMUTATION DISPLAY //
	////////////////////////////
	public static void displayPermutation(String s)
	{
		System.out.println("\nThe permutations of this string are:");
		
		// calls helper method, output string is initially empty
		displayPermutation(s, "");
		System.out.println("\n");
	}
	
	// helper method
	public static void displayPermutation(String s1, String s2)
	{
		// base case
		if (s1.length() == 0)
		{
			// when every permutation is reached, this will output the results
			System.out.println(s2);
		}
		else
		{
			for(int i = 0; i < s1.length(); i++)
			{
				// the new output string is the previous output string, plus the character at i
				// of the input string
				String newS2 = s2 + s1.charAt(i);
				
				// the new input string is the first character in the previous input string
				// through i, plus the character immediately after i through the end of the string
				String newS1 = s1.substring(0, i) + s1.substring(i + 1);
				
				// recursive call
				displayPermutation(newS1, newS2);
			}
		}
	}
	
	//////////////////////////////////
	// 2. HEX TO DECIMAL CONVERSION //
	//////////////////////////////////
	public static int hex2Dec(String hexString)
	{
		int digit;
		int length = hexString.length();
		
		// creating a string to grab the position values from
		String hexCharacters = "0123456789ABCDEF";
		
		// base case
		if (length == 0)
		{
			return 0;
		}
		else
		{
			// checks for whatever position the character is found in the hexCharacters String,
			// then assigns that position as the digit's value.
			digit = hexCharacters.indexOf(hexString.charAt(0));
		}
		
		// Iteration
		String nextDigit = hexString.substring(1, hexString.length());
		
		// returns the digit, multiplied by 16 raised to the power of the length of the
		// string (or substring, as it iterates through), adding to the total recursively.
		int decimal = digit * (int)Math.pow(16, length - 1) + hex2Dec(nextDigit);
		
		return decimal;
	}
	
	/////////////////////////////////////
	// 3. BINARY TO DECIMAL CONVERSION //
	/////////////////////////////////////
	public static int bin2Dec(String binaryString)
	{
		return bin2Dec(binaryString, 0);
	}
	
	// I chose to use a helper method in this case, because it's not how I normally would
	// do recursion. I think I like this solution for iteration better, it's cleaner to read!
	public static int bin2Dec(String binaryString, int iterator)
	{
		// base case
		if (binaryString.isEmpty() || binaryString.length() <= iterator)
		{
			return 0;
		}
		else
		{
			int output;
			char c = binaryString.charAt(iterator);
			
			// if the character at this iteration is 1, output is 1. Otherwise, output is 0.
			switch(c)
			{
				case '1': output = 1;
						  break;
				default:  output = 0;
				  		  break;
			}
			
			// returns the output, bitshifted left by the total length of the string,
			// minus one greater than the iterator, then recursively runs the method, iterating by 1.			
			int decimal = (output << binaryString.length() - (iterator + 1)) + bin2Dec(binaryString, iterator + 1);
	
			return decimal;
			}
	}
	
	//////////////////////////////////
	// 4. DECIMAL TO HEX CONVERSION //
	//////////////////////////////////
	public static String dec2Hex(int value)
	{
		String output = "";
		
		// get the remainder of the value divided by 16
		int remainder = value % 16;
		
		if (value != 0)
		{		
			// simple switch statement for cases greater than 9, to create a base 16 number system
			switch(remainder)
			{
				case 10: output = "A";
						 break;
				case 11: output = "B";
						 break;
				case 12: output = "C";
				 		 break;
				case 13: output = "D";
				 		 break;
				case 14: output = "E";
				 		 break;
				case 15: output = "F";
				 		 break;
				 		 
				// If the remainder is 0 - 9, puts that number in the next space in the output
				default: output = remainder + output;
						 break;
			}
			
			// recursively runs the method, dividing the initial value by 16 each pass
			return dec2Hex(value / 16) + output;
		}
		// base case
		else
		{
			return "";
		}
	}
}
