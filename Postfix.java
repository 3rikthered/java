
import java.util.Scanner;
import java.util.Stack;

public class Postfix
{
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		int choice = 0;
		while (choice != 4)
		{
			choice = setChoice();
			if (choice == 4)
				break;
			menu(choice);
		}
	}

	private static void menu(int choice)
	{
		if (choice == 1)
		{
			System.out.println("--Evaluate postfix expression--\n\n" +
							 "Enter a postfix expression: ");
			
			String postfixExpression = input.next();
			
			System.out.print("\n");
			postfix(postfixExpression);
		}
		else if (choice == 2)
		{
			System.out.print("Convert infix to postfix and evaluate\n\n" +
							 "Enter an infix expression: ");
			
			String infixExpression = input.nextLine();
			
			System.out.print("\n");
			infix2postfix(infixExpression);
		}
		else if (choice == 3)
		{
			System.out.print("Alphebetize and dedupe words from text file\n\n" +
							 "Enter the filepath: ");
			
			// IMPORT FILE
		}
	}

	private static int setChoice()
	{
		int choice = 0;
		
		while (choice < 1 || choice > 4)
		{
			System.out.println("Choose a method:\n\n" +
							   "1 - Evaluate postfix expression\n" +
							   "2 - Convert infix to postfix and evaluate\n" +
							   "3 - Alphebetize and dedupe words from text file" +
							   "4 - Exit");
			
			choice = input.nextInt();
		}
		
		return choice;
	}

	/////////////////////////////////
	// Evaluate postfix expression //
	/////////////////////////////////
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! this code works, the input is failing somewhere
	private static void postfix(String postfixExpression) {
		
		System.out.print(postfixExpression);
		
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
					case ' ': break;
				}
			}
		}
		
		System.out.print("\n\n" + postfixExpression + " = " + stack.pop() + "\n\n");
	}

	private static Stack<Integer> multiplication(Stack<Integer> stack) {
		
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt * lastInt);
		
		return stack;
	}

	private static Stack<Integer> division(Stack<Integer> stack) {
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt / lastInt);
		
		return stack;
	}

	private static Stack<Integer> subtraction(Stack<Integer> stack) {
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt - lastInt);
		
		return stack;
	}

	private static Stack<Integer> addition(Stack<Integer> stack) {
		int lastInt = stack.pop();
		int secondLastInt = stack.pop();
		
		stack.push(secondLastInt + lastInt);
		
		return stack;
	}

	///////////////////////////////////////////
	// Convert infix to postfix and evaluate //
	///////////////////////////////////////////
	private static void infix2postfix(String infixExpression) {
		
	}

}
