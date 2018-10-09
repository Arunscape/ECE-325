import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

// ORIGINAL CODE
/**
 * Assignment 4 Part 1: Static Code Analysis <br />
 * The buggy {@code CodingHorror} source code
 */
//public class CodingHorror {
//
//    public static void main(String args[]) {
//        // TODO: Assignment 4 Part 1 -- run FindBugs on the code
//
//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(isr);
//        String input = null;
//        try {
//            input = br.readLine();                  // e.g., peel
//        } catch (IOException ioex) {
//            System.err.println(ioex.getMessage());
//        }
//        input.replace('e', 'o');
//        if (input == "pool") {
//            System.out.println("User entered peel.");
//        } else {
//            System.out.println("User entered something else.");
//        }
//    }
//}

// MODIFIED CODE
@SuppressWarnings("serial")
class BufferedReaderReturnedNullError extends Exception {
	public BufferedReaderReturnedNullError(String message) {
        super(message);
    }
}
public class CodingHorror {

    public static void main(String args[]) {
        // TODO: Assignment 4 Part 1 -- run FindBugs on the code

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = null;
        try {
            input = br.readLine(); // e.g., peel
            if(input == null) {
            	// honestly this might be overkill
            	throw new BufferedReaderReturnedNullError("Error, input is null");
            }
        } catch (IOException ioex) {
            System.err.println(ioex.getMessage());
        } catch (BufferedReaderReturnedNullError e) {
        	System.err.println(e.getMessage());
        	return;
		}
        if(input != null) {
        	if(input.equals("pool")) {
        		System.out.println("User entered pool.");
        	}
        	else {
        		input = input.replace('e', 'o');
                if (input.equals("pool")) {
                    System.out.println("User entered peel.");
                } else {
                    System.out.println("User entered something else.");
                }
        	}
            
        }

    }
}


// Bug 0:
// input.replace('e', 'o'); 
// String.replace() returns a new string, it does not modify the old one
// fix: assign the replacement string
// input = input.replace('e', 'o');

// Bug 1:
// input = br.readLine();
// BufferedReader.readLine() can return null
// if input is null and we attempt to execute input.replace(), the program will error out,
// because you can't call a method on a null object
// fix: check if input is null before attempting to replace characters
// input = (input == null) ? null : input.replace('e', 'o');

// Bug 2:
// if (input == "pool"){...}
// In java, == on strings checks if the Strings are the same object, which is not what we 
// actually want to test. We want to see if the values of the Strings are the same
// fix: use String.equals()
// if (input.equals("pool")){...}

// Bug 3:
// Ah wait, but if we change 
// if (input == "pool"){...} 
// to 
// if (input.equals("pool")){...}
// what if input is null? then input.equals() will error out because you can't call .equals() on null
// fix: check if input is null before checking if it equals to "pool"
//if (input != null){
//	if(input.equals("pool")){...}
//	}
//}

// Bug 4:
// if (input.equals("pool")) {
// System.out.println("User entered peel.");
// }
// But wait, there's more! If the user originally entered pool, the program will say "User entered peel"
// fix: check input before and after the replacement
//if (input != null) {
//  if (input.equals("pool")) {
//      System.out.println("User entered pool");
//	}
//	else {
//      input = input.replace('e','o');
//      if (input.equals("pool")) {
//          System.out.println("Result of substitution is peel");
//      }
//      else{
//          System.out.println("User entered something else");
//      }
//
// }
//}
//else {System.out.println("input was null"); }

