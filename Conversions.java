package nelsonTask7;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;


// POSTFIX ONLY WORKING ON SINGLE DIGIT INPUT....FIX IT
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
				
		for (int i = 0; i < postfixExpression.length(); i++)
		{
			char value = postfixExpression.charAt(i);
			
			
			if (Character.isDigit(value))
			{
				stack.push(value - '0');
			}
			else
			{
				switch(value)
				{
					case '+': stack = addition(stack);
							  break;
					case '-': stack = subtraction(stack);
							  break;
					case '/': stack = division(stack);
						  	  break;
					case '*': stack = multiplication(stack);
						  	  break;
					case '^': stack = exponent(stack);
					case ' ': break;
				}
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
		Stack<Character> charStack = new Stack<Character>();
		
		String postfixExpression = "";
		
		for (int i = 0; i < infixExpression.length(); i++)
		{
			char c = infixExpression.charAt(i);
			
			switch(c)
			{
				case '(': charStack.push(c);
						  break;
				case ')': while(!charStack.isEmpty() && charStack.peek() != '(')
						  {
								postfixExpression += charStack.pop();
						  }
						  break;
				default:  if (isOperator(c))
						  {
						  		while (!charStack.isEmpty() && precedence(c) <= precedence(charStack.peek()))
						  		{
						  			postfixExpression += charStack.pop();
						  		}
						  		charStack.push(c);
						  }
						  else if (Character.isDigit(c))
						  {
							  	postfixExpression += c;
						  }
						  break;
			}
		}
		
		while (!charStack.isEmpty())
		{
			postfixExpression += charStack.pop();
		}
		
		System.out.print("\n" + infixExpression + " = " + postfixExpression);
		postfix(postfixExpression);
	}
	
	// reference hashset for operators
	private static final HashSet<Character> operators = new HashSet<Character>();
	
	static
	{
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');
		operators.add('^');
	}
	
	// check to see if the character is an operator
	public static boolean isOperator(Character c)
	{
		return operators.contains(c);
	}

	// order of precedence for operators
	private static int precedence(char c)
	{
		switch (c)
		{
			case '+': return 1;
			case '-': return 1;
			case '*': return 2;
			case '/': return 2;
			case '^': return 3;
			default:  return 0;
		}
	}
}
