/*

The split method in the String class returns an array of strings consisting of the substrings split by the
delimiters. However, the delimiters are not returned.
Implement the following new method that returns an array of strings consisting of the substrings split
by the matching delimiters, including the matching delimiters.
Assume a maximum limit of 2 delimiters, the space denotes end of string and beginning of delimiters.
- public static String[] split(String s, String regex)
For example, split(”ab#12#453”, ”#”) returns
- ab, #, 12, #, 453 in an array of String
and split(”a?b?gfe#”, ”[?#]”) returns
- a, ?, b, ?, gfe, #, in an array of String.

*/

// My solution:
// Take the string in
// for each character [regex]
//    insert a Splitter before and after each [regex]
// Split string by Splitter
// Return results



/*

split string into array of strings, separated by nothing
for each variable in the array:
  if variable == [regex]:
    variable = Splitter [regex] Splitter

Turn array of strings back into string
Split string by Splitter
Return results

LOOK INTO string.replaceAll()

*/

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

  // Regex method, converts characters

  // Splitter method, accepts a string and delimiters
  public static String[] split(String s, String regex)
  {
    // Separate the regex into an array
    String[] delimiters = regex.split("");

    // Searches for all regex delimiters in the string,
    // Adds a new delimiter on each side of the regex delimiter
    for (int i = 0; i < delimiters.length; i++){
          s = s.replaceAll(delimiters[i], "!!SPLITHERE!!" + delimiters[i] + "!!SPLITHERE!!");
    }

    // Creates an array that splits the string based on the new delimiter
    String[] splitty = s.split("!!SPLITHERE!!");

    return splitty;
  }
}
