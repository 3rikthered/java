package HangmanGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame
{
	public static void main (String[] args) throws Exception
	{
		Scanner input = new Scanner(System.in);
		List<String> wordList = new ArrayList<>();
		
		// Prompts user for file name
		System.out.print("Enter the name of the text file: ");
		String fileName = input.nextLine();
				
		try
		{
			// check if the filename is correct
			if (fileName.equals("hangman.txt"))
			{
				// reads the file
				BufferedReader readFile = new BufferedReader(new FileReader(fileName));
				
				String nextWord = "";
				
				// while there are still words in the file, adds those words to the word list
				while ((nextWord = readFile.readLine()) != null)
				{
					wordList.add(nextWord);
				}
				
				// grabs a random word
				String randomWord = randomWordGenerator(wordList);
				
				// plays the game
				hangman(randomWord);
				
				input.close();
				readFile.close();
			}		
			else
			{
				input.close();
				throw (new FileNotFoundException());
			}	
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	// EXCEPTION HANDLER
	public static class FileNotFoundException extends Exception
	{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getMessage()
		{
			return "EXCEPTION: File must be \"hangman.txt\"\n";
		}
	}
	
	// Selects a word at random
	private static String randomWordGenerator(List<String> wordList)
	{
		Random rand = new Random();
		int randomNumber = rand.nextInt(wordList.size());
		String word = wordList.get(randomNumber);
		
		return word;
	}
	
	// method to play hangman
	public static void hangman(String randomWord)
	{		
		Scanner input = new Scanner(System.in);
		
		boolean victorious = false;
		int count = 0;
		
		// replace all characters in the random word with the * character
		String hiddenWord = randomWord.replaceAll(".", "*");
		
		// create a copy of the * character that will be revealed based on correct guesses
		String revealedWord = hiddenWord;
		
		while (count < 13)
		{
			// checks for victory condition
			if (hiddenWord.equals(randomWord))
			{
				victorious = true;
				break;
			}
			else
			{
				System.out.println("Guess the letters represented by the asterisks " + hiddenWord);
				System.out.println(hangmanGraphic(count));
				
				// prevent case issues
				String guess = input.next().toLowerCase();
				
				// tells user if guessed letter has already been revealed
				if (revealedWord.contains(guess.substring(0, 1)))
				{
					System.out.println("You already tried that letter.");
				}
				else
				{
					for (int i = 0; i < randomWord.length(); i++)
					{
						// if the guess matches a character in the random word,
						// places that character in the correct position in the revealed word
						if (guess.charAt(0) == randomWord.charAt(i))
						{
							revealedWord = replaceStringCharacters(i, randomWord.charAt(i), revealedWord);
						}
					}
					
					// if the hidden word and the revealed word don't match, increments the counter
					if (hiddenWord.equals(revealedWord))
					{
						count++;
					}
					// otherwise, matches the hidden word with the revealed word
					else
					{
						hiddenWord = revealedWord;
					}
				}
			}
		}
		
		// victory statement
		if (victorious == true)
		{
			System.out.println("You win! The word was " + randomWord);
		}
		// loss statement
		else
		{
			System.out.println("Game Over! You lose! The word was " + randomWord);
			System.out.println(hangmanGraphic(count));
		}
		
		input.close();	
	}
	
	// replaces characters in a string
	public static String replaceStringCharacters(int pos, char letter, String hiddenWord)
	{
		char[] hiddenWordArray = hiddenWord.toCharArray();
		hiddenWordArray[pos] = letter;
		return new String(hiddenWordArray);
	}

	// Builds the hanged man graphic, based on guess counter	
	public static String hangmanGraphic(int count)
	{
		String hangedMan = "";
		
		switch(count)
		{
			case 1:	hangedMan = "     \n"
							  + "     \n"
							  + "     \n"
							  + "     \n"
							  + "    |\n";
					break;
			case 2:	hangedMan = "     \n"
							  + "     \n"
							  + "     \n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 3:	hangedMan = "     \n"
							  + "     \n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 4:	hangedMan = "     \n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 5:	hangedMan = "   - \n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 6:	hangedMan = "  -- \n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 7:	hangedMan = "  -- \n"
							  + " |  |\n"
							  + "    |\n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 8:	hangedMan = "  -- \n"
							  + " |  |\n"
							  + " O  |\n"
							  + "    |\n"
							  + "    |\n";
					break;
			case 9:	hangedMan = "  -- \n"
							  + " |  |\n"
							  + " O  |\n"
							  + " |  |\n"
							  + "    |\n";
					break;
			case 10:hangedMan = "  -- \n"
							  + " |  |\n"
							  + " O  |\n"
							  + "/|  |\n"
							  + "    |\n";
					break;
			case 11:hangedMan = "  -- \n"
							  + " |  |\n"
							  + " O  |\n"
							  + "/|\\  |\n"
							  + "    |\n";
					break;
			case 12:hangedMan = "  -- \n"
							  + " |  |\n"
							  + " O  |\n"
							  + "/|\\ |\n"
							  + "/   |\n";
					break;
			case 13:hangedMan = "  -- \n"
							  + " |  |\n"
							  + " O  |\n"
							  + "/|\\ |\n"
							  + "/ \\ |\n";
					break;
		}
		return hangedMan;
	}
}
