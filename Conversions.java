package nelsonTask7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class nelsonTask7
{
	public static void main(String[] args)
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

	private static void menu(int choice, Scanner input)
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
			System.out.print("Alphebetize and dedupe words from text file\n\n" +
							 "Enter the filepath: ");
			
			// IMPORT FILE
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
	
	// clears 'Enter' from the buffer, then reading the input 
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
		
		for (int i = 0; i < parsed.size(); i++)
		{
			String value = parsed.get(i);
						
			switch(value)
			{
				case "(": stack.push(value);
						  break;
				case ")": while(!stack.isEmpty() && stack.peek() != "(")
						  {
								postfixExpression += stack.pop();
						  }
						  break;
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
	
	private static List<String> parser(String input)
	{
		input = input.replace("+", " + ");
		input = input.replace("-", " - ");
		input = input.replace("*", " * ");
		input = input.replace("/", " / ");
		input = input.replace("^", " ^ ");
		input = input.replace("(", " ( ");
		input = input.replace(")", " ) ");

		List<String> list = new ArrayList<String>(Arrays.asList(input.split(" ")));
		list.removeAll(Arrays.asList("", null));
		return list;
	}
	
	static List<String> operatorsList = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "^"));
}
