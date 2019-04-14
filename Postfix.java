package nelsonTask7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class nelsonTask7
{
	public static void main(String[] args) throws Exception
	{	
		Scanner input = new Scanner(System.in);
		
		int choice = 0;
		while (choice != 4)
		{
			choice = setChoice(input);
			if (choice == 4)
				break;
			menu(choice, input);
		}
		
		input.close();
	}

	private static void menu(int choice, Scanner input) throws Exception
	{		
		if (choice == 1)
		{
			System.out.print("--Evaluate postfix expression--\n\n" +
							 "Enter a postfix expression: ");
			
			String postfixExpression = reader(input);
			postfix(postfixExpression);
		}
		else if (choice == 2)
		{
			System.out.print("Convert infix to postfix and evaluate\n\n" +
							 "Enter an infix expression: ");

			String infixExpression = reader(input);
			infix2postfix(infixExpression);
		}
		else if (choice == 3)
		{
			System.out.print("Alphebetize and dedupe words from text file\n\n");
			
			fileReader(input);
		}
	}

	private static int setChoice(Scanner input)
	{		
		int choice = 0;
		
		while (choice < 1 || choice > 4)
		{
			System.out.println("Choose a method:\n\n" +
							   "1 - Evaluate postfix expression\n" +
							   "2 - Convert infix to postfix and evaluate\n" +
							   "3 - Alphebetize and dedupe words from text file\n" +
							   "4 - Exit");
			
			choice = input.nextInt();
		}
		
		return choice;
	}
	
	// clears 'Enter' from the buffer, then reads the input 
	public static String reader(Scanner input)
	{
		input.nextLine();
		return input.nextLine();
	}

	
	/////////////////////////////////
	// Evaluate postfix expression //
	/////////////////////////////////
	private static void postfix(String postfixExpression)
	{				
		Stack<Integer> stack = new Stack<>();
		List<String> parsed = parser(postfixExpression);
			
		for (int i = 0; i < parsed.size(); i++)
		{
			String value = parsed.get(i);
			
			if (operatorsList.contains(value))
			{
				switch(value)
				{
					case "+": stack = addition(stack);
							  break;
					case "-": stack = subtraction(stack);
							  break;
					case "*": stack = multiplication(stack);
						  	  break;
					case "/": stack = division(stack);
						  	  break;
					case "^": stack = exponent(stack);
				}
			}
			
			else
			{
				stack.push(Integer.valueOf(value));
			}
		}
		
		System.out.print("\n" + postfixExpression + " = " + stack.pop() + "\n\n");
	}

	// MATHEMATIC OPERATORS: pop the last 2 elements in the stack, perform the appropriate
	// mathematic operation against them, returns the stack
	private static Stack<Integer> exponent(Stack<Integer> stack) {
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push((int) Math.pow(secondLastInt, lastInt));
		
		return stack;
	}

	private static Stack<Integer> multiplication(Stack<Integer> stack)
	{
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt * lastInt);
		
		return stack;
	}

	private static Stack<Integer> division(Stack<Integer> stack)
	{
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt / lastInt);
		
		return stack;
	}

	private static Stack<Integer> subtraction(Stack<Integer> stack)
	{
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt - lastInt);
		
		return stack;
	}

	private static Stack<Integer> addition(Stack<Integer> stack)
	{
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt + lastInt);
		
		return stack;
	}

	///////////////////////////////////////////
	// Convert infix to postfix and evaluate //
	///////////////////////////////////////////
	private static void infix2postfix(String infixExpression)
	{		
		Stack<String> stack = new Stack<>();
		List<String> parsed = parser(infixExpression);

		String postfixExpression = "";
		
		// checks each element in the parsed user input
		for (int i = 0; i < parsed.size(); i++)
		{
			String value = parsed.get(i);
						
			switch(value)
			{
				// Opening parentheses are pushed onto the stack
				case "(": stack.push(value);
						  break;
				// Ending parentheses ensure that parenthetical expressions are evaluated
				// as a group, up until it reaches the opening parenthesis
				case ")": while(!stack.isEmpty() && stack.peek() != "(")
						  {
								postfixExpression += stack.pop();
						  }
						  break;
				// Ugly code having an if-else statement inside a case switch, I haven't
				// figured out how to implement it differently though. If the element is
				// an operator, it will determine precedence and order the operations
				// accordingly. If it is a number, it will place the value in the postfix
				// expression.
				default:  if (operatorsList.contains(value))
						  {
						  		while (!stack.isEmpty() && precedence(value) <= precedence(stack.peek()))
						  		{
						  			postfixExpression += stack.pop();
						  		}
						  		stack.push(value);
						  }
						  else
						  {
							  	postfixExpression += (value + " ");
						  }
						  break;
			}
		}
		
		while (!stack.isEmpty())
		{
			postfixExpression += stack.pop();
		}
		
		// Removes beginning parentheses from the postfixExpression
		postfixExpression = postfixExpression.replaceAll("\\(", " ");
		
		System.out.print("\n" + infixExpression + " = " + postfixExpression);
		postfix(postfixExpression);
	}

	// order of precedence for operators
	private static int precedence(String value)
	{
		switch (value)
		{
			case "+": return 1;
			case "-": return 1;
			case "*": return 2;
			case "/": return 2;
			case "^": return 3;
			default:  return 0;
		}
	}
	
	// parses the input into an arraylist
	private static List<String> parser(String input)
	{
		// because people don't always put spaces between operators and numbers, this
		// pads each operator with spaces for parsing
		input = input.replace("+", " + ");
		input = input.replace("-", " - ");
		input = input.replace("*", " * ");
		input = input.replace("/", " / ");
		input = input.replace("^", " ^ ");
		input = input.replace("(", " ( ");
		input = input.replace(")", " ) ");

		// splits by spaces
		List<String> list = new ArrayList<String>(Arrays.asList(input.split(" ")));
		
		// removes empty elements created as a result of double spaces
		list.removeAll(Arrays.asList("", null));
		return list;
	}
	
	// A list of mathematic operators to compare against
	static List<String> operatorsList = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "^"));

	
	/////////////////////////////////////////////////
	// Alphebetize and dedupe words from text file //
	/////////////////////////////////////////////////
	public static void fileReader(Scanner input) throws Exception
	{
		System.out.print("Enter the name of the text file: ");
		String fileName = reader(input);
		
		LinkedList<String> list = importer(fileName, input);
		
		// dedupes and alphebetizes the list
		list = dedupeAndAlphebetize(list);
				
		if (list.size() % 4 != 0)
		{
			for (int i = 0; i < list.size() % 4; i++)
			{
				list.offerFirst("");
			}
		}

		
		// Prints the list out in descending alphabetic order
		//!!!!!!!!!!!!!!!!!!! FIND A WAY TO PRINT FOUR WORDS PER LINE WITHOUT THROWING EXCEPTION
		ListIterator<String> iterator = list.listIterator(list.size());
		while (iterator.hasPrevious())
		{
			System.out.print(iterator.previous() + "	" +
							 iterator.previous() + "	" +
							 iterator.previous() + "	" +
							 iterator.previous() + "\n");
		}
		
		System.out.print("\n");
	}

	// Imports the file and reads it into the LinkedList
	public static LinkedList<String> importer(String fileName, Scanner input) throws Exception
	{
		File file = new File(fileName);
		
		LinkedList<String> list = new LinkedList<String>();
		
		try
		{
			// check if the file exists
			if (file.isFile())
			{
				// reads the file
				BufferedReader readFile = new BufferedReader(new FileReader(fileName));
				
				String nextWord = "";
				
				// while there are still words in the file, adds those words to the LinkedList
				while ((nextWord = readFile.readLine()) != null)
				{
					list.add(nextWord);
				}
				
				readFile.close();
			}
			else
			{
				throw(new FileNotFoundException());
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	// EXCEPTION HANDLER
	public static class FileNotFoundException extends Exception
	{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMessage()
		{
			return "EXCEPTION: File does not exist";
		}
	}
	
	// To dedupe and alphebetize the linkedlist, converts it to a
	// case-insensitive treeset, then converts back.
	private static LinkedList<String> dedupeAndAlphebetize(LinkedList<String> list)
	{
		TreeSet<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		
		for (String string : list)
		{
			set.add(string);
		}
		
		LinkedList<String> returnList = new LinkedList<String>(set);
		return returnList;
	}
}
