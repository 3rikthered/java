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

class Main 
{
  public static void main(String[] args)
  {
    // Input string here
    String str = "Hello world%Goodbye world";

    System.out.print(split(str));


    

  
  }

  public static String[] split(String s)
  {
     s = s.replaceAll(" ", "NOREPLACEME").replaceAll("%", " ");

    String[] splitty = s.split(" ");

    for (int i = 0; i < splitty.length; i++)
    {
      splitty[i] = (splitty[i].replaceAll("NOREPLACEME", " "));
    }
    return splitty;
  }
}
