package nelsonTask6;

import java.util.Scanner;

public class nelsonTask6 {
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {			
			
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
	
	public static int setChoice()
	{
		int choice = 0;
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
	
	public static int setBoundary()
	{
		System.out.println("Recursive programs can quickly get out of hand complexity wise. " +
				   		   "Set an upper bound on input size.\n" +
				   		   "WARNING: inputs of greater than 6 digits/chars may result in slower processing.");
		return input.nextInt();
	}
	
	public static void menu(int choice, int boundary)
	{
		if (choice == 1)
		{
			System.out.println("Enter a string: ");
			String stringValue = input.next();
			
			while (stringValue.length() > boundary)
			{
				System.out.println("Enter a string: ");
				stringValue = input.next();	
			}
			
			System.out.println(choice + " " + boundary + " " + stringValue);
			
//			displayPermutation(stringValue);
		}
		else if (choice == 2)
		{
			System.out.println("Enter a string: ");
			String stringValue = input.next();
			boolean hexTest = stringValue.matches("-?[0-9a-fA-F]+");
			
			while(stringValue.length() > boundary || !hexTest)
			{
				System.out.println("Enter a string: ");
				stringValue = input.next();
				hexTest = stringValue.matches("-?[0-9a-fA-F]+");
			}
			
			System.out.println(choice + " " + boundary + " " + stringValue);

			
//			hex2Dec(stringValue);
		}
		else if (choice == 3)
		{
			System.out.println("Enter a string: ");
			String stringValue = input.next();
			boolean binTest = stringValue.matches("-?[0-1]+");
			
			while(stringValue.length() > boundary || !binTest)
			{
				System.out.println("Enter a string: ");
				stringValue = input.next();
				binTest = stringValue.matches("-?[0-1]+");
			}
						
			System.out.println("The decimal equivalent is " + bin2Dec(stringValue) + "\n");
		}
		else if (choice == 4)
		{
			System.out.println("Enter an integer: ");
			int intValue = input.nextInt();
			
			while (String.valueOf(intValue).length() > boundary)
			{
				System.out.println("Enter an integer: ");
				intValue = input.nextInt();
			}
						
			System.out.println("The hex equivalent is " + dec2Hex(intValue) + "\n");
		}
	}

	/*
	
	/////////////////////////
	// PERMUTATION DISPLAY //
	/////////////////////////
	public static void displayPermutation(String s)
	{
		
	}
	
	///////////////////////////////
	// HEX TO DECIMAL CONVERSION //
	///////////////////////////////
	public static int hex2Dec(String hexString)
	{
		
	}
	
	*/
	
	//////////////////////////////////
	// BINARY TO DECIMAL CONVERSION //
	//////////////////////////////////
	public static int bin2Dec(String binaryString)
	{
		return bin2Dec(binaryString, 0);
	}
	
	public static int bin2Dec(String binaryString, int integer)
	{
		if (binaryString.isEmpty() || binaryString.length() <= integer)
		{
			return 0;
		}
		
		else
		{
			int output;
			
			if (binaryString.charAt(integer) == '1')
			{
				output = 1;
			}
			else
			{
				output = 0;
			}
			
			return (output << binaryString.length() - integer - 1) + bin2Dec(binaryString, integer + 1);
			}
	}
	
	
	///////////////////////////////
	// DECIMAL TO HEX CONVERSION //
	///////////////////////////////
	public static String dec2Hex(int value)
	{
		String output = "";
		
		int remainder = value % 16;
		
		if (value != 0)
		{		
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
				default: output = remainder + output;
						 break;
			}
			
			return dec2Hex(value / 16) + output;
		}
		else
		{
			return "";
		}
	}
}
