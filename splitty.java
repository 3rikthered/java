import java.util.Arrays;
import java.util.Scanner;

class Main 
{
  public static void main(String[] args)
  {
    // Initialize Scanner and string
    Scanner input = new Scanner(System.in);
    String s;
    String regex;

    System.out.println("Input?");
    s = input.nextLine();

    System.out.println("Delimiters?");
    regex = input.nextLine();

    //
    String[] holder = split(s, regex);

    // Get the printout
    System.out.print(Arrays.toString(holder));
    //for (int i = 0; i < holder.length; i++)
    //{
      //System.out.print(holder[i] + "\n");
    //}
  
  }


  // Splitter method, accepts a string and delimiters
  public static String[] split(String s, String regex)
  {
    // Separate the regex into an array
    String[] delimiters = regex.split("");

    // Fix certain characters in the array that don't play nicely otherwise
    for (int i = 0; i < delimiters.length; i++)
    {
      switch(delimiters[i])
      {
        case "$": delimiters[i] = "\\$"; break;
        case "^": delimiters[i] = "\\^"; break;

      }
    }

    // Searches for all regex delimiters in the string,
    // Adds a new delimiter on each side of the regex delimiter
    for (int i = 0; i < delimiters.length; i++)
    {
          s = s.replaceAll(delimiters[i], "!!SPLITHERE!!" + delimiters[i] + "!!SPLITHERE!!");
    }

    // Creates an array that splits the string based on the new delimiter
    String[] splitty = s.split("!!SPLITHERE!!");

    return splitty;
  }
}
