import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

// Modified code
/**
 * Assignment 4 Part 1: Static Code Analysis <br />
 * The buggy {@code CodingHorror} source code
 */
public class CodingHorror {

    public static void main(String args[]) {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = null;
        try {
            input = br.readLine(); // e.g., peel
            if (input == null) {
            	throw new IOException("Input value was null, try running the program again.");
            }
        } catch (IOException ioex) {
        	if (ioex.getMessage()==null) {
        		System.err.println("Something broke, IOException, no message given");
        	}
        	else {
        		System.err.println(ioex.getMessage());
        	}
            return;
        } 
        input=input.replace('e', 'o');
        if (input.equals("pool")) {
            System.out.println("User entered peel.");
        } else {
            System.out.println("User entered something else.");
        }
    }
}


//Bug 1:
//Dereference of the result of readLine() without nullcheck in CodingHorror.main(String[])
//The result of invoking readLine() is dereferenced without checking to see if the result is null. If there are no more lines of text to read, readLine() will return null and dereferencing that will generate a null pointer exception.  
//Affected line: input.replace('e', 'o');
//
//Cause:
//BufferedReader.readLine() can return null
//if input is null and we attempt to execute input.replace(), the program will error out,
//because you can't call a method on a thing that's null
//
//fix:
//end the program if the input is null, and warn the user, you can't process the input anyways if it's null



//Bug 2:
//Possible null pointer dereference of input in CodingHorror.main(String[]) on exception path
//Affected line: input.replace('e', 'o');
//
//Cause:
//If an IO exception occurs, input is null, and we'll have the same problem as in
//bug 1 if we attempt to execute input.replace() since the error is just printed
//and the program continues
//
//fix:
//end the program after printing the error, you can't process the input anyways if it's null


//Bug 3:
//wait there's more!
//Possible null pointer dereference of input in CodingHorror.main(String[]) on exception path
//Affected line: System.err.println(ioex.getMessage());
//
//Cause:
//IOExeption.getMessage() can return null, and attempting to print a null will result in an error
//
//fix:
//check if the error message is null, if not, print the message given

//Bug 4:
//Return value of String.replace(char, char) ignored in CodingHorror.main(String[])
//Affected line: input.replace('e', 'o');
//
//Cause:
//String.replace() returns a new string, it doesn't modify the original, so input.replace()
//by itself won't modify our input variable
//
//fix:
//reassign input to the new string returned from input.replace()
//input=input.replace(...)

//Bug 5:
//Comparison of String objects using == or != in CodingHorror.main(String[]) 
//Affected line: if (input == "pool") {
//
//Cause:
//In java, == on strings checks if the Strings are the same object, which is not what we 
//actually want to test. We want to see if the values of the Strings are the same
//fix: use String.equals()
//if (input.equals("pool")){...}


