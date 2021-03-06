/**
 * Assignment 3: Exception handling <br />
 * Calculator using BNF
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.*;

@SuppressWarnings("serial")
class SyntaxError extends Exception {

	public SyntaxError(String message) {
        super(message);
    }

}
@SuppressWarnings("serial")
class RuntimeError extends Exception {

	public RuntimeError(String message) {
        super(message);
    }

}

public class Calculator {

/**
 * Execute the expression, and return the correct value
 * @param exp           {@code String} The expression string
 * @return              {@code int}    The value of the expression
 */
private Pattern findBrackets = Pattern.compile("\\([^\\(\\)]*\\)");
private Pattern findExponents = Pattern.compile("([0-9]+|[a-z]) \\^ ([0-9]+|[a-z])");
private Pattern findMulDiv = Pattern.compile("([0-9]+|[a-z]) (\\*|/) ([0-9]+|[a-z])");
private Pattern findAddSub = Pattern.compile("([0-9]+|[a-z]) (\\+|-) ([0-9]+|[a-z])");
//private Pattern findAssignments = Pattern.compile("let [a-z] = ([a-z]|[0-9]+)");
private Pattern findAssignments = Pattern.compile("let [a-z] = [^;)]*");
private int answer;

private int findOperation(String in, HashMap<Character,Integer> vars) throws RuntimeError, SyntaxError{
		Matcher m = null;
		
		HashMap<Character,Integer> dict;
		if (vars==null) {
			dict=new HashMap<>();
		}
		else {
			dict=vars;
		}
		String input = in.replace(";","");
		
        if         (findBrackets.matcher(input).find())        { m=findBrackets.matcher(input);        }
        else if (findExponents.matcher(input).find())     { m=findExponents.matcher(input);     }
        else if (findMulDiv.matcher(input).find())           { m=findMulDiv.matcher(input);           }
        else if (findAddSub.matcher(input).find())          { m=findAddSub.matcher(input);         }
        else if (findAssignments.matcher(input).find()) { m=findAssignments.matcher(input); }
        else {        	
        	
        		try {
        			Pattern p = Pattern.compile("[a-z0-9] [a-z0-9]");
        			Matcher moo = p.matcher(input);
        				
        			if(moo.find()) {throw new SyntaxError("operator expected");}
        			return Integer.parseInt(input);
        		}
        		catch(NumberFormatException e) {
        			//get the value of a variable
        			return vars.get(input.charAt(0));
        		}
        }
        // R E C U R S I O N
        
        
       if(m.find()) {    	   
    		   calculate(input, m.start(), m.end(),dict);         
        }
       return answer; 
       
}

private void calculate(String input, int a, int b, HashMap<Character,Integer> vars) throws RuntimeError, SyntaxError {
	int sol=-1;
	
	String exp = input.substring(a,b);
	exp = exp.replace("(","");
	exp = exp.replace(")","");

	Matcher m = findAssignments.matcher(exp);
	if (m.find()) {
		Character varName = m.group().charAt(4);
		int whateverthefuckisontherightside = findOperation(m.group().substring(8), vars);
		vars.put(varName, whateverthefuckisontherightside);
		sol= whateverthefuckisontherightside; 
		// if you're reading this I am very sorry for the
		// poor choice in variable name, but at this point I'm scared to change it
		
	}
	else {
		exp = exp.replace("(","");
		exp = exp.replace(")","");
		String[] stuff = exp.split(" ");
		// 0 - left operand
		// 1- operator
		// 2 - right operand
		if (Arrays.asList(stuff).contains("=")) {
			int tmpidx = Arrays.asList(stuff).indexOf("=");
			throw new RuntimeError(String.format("%s undefined", stuff[tmpidx-2]));
		}
		int left; int right;
		try {
			left = Integer.parseInt(stuff[0]);
		}
		catch(NumberFormatException e) {
			// it's not a number, it's a variable
			left = vars.get(stuff[0].charAt(0));
		}
		try {
			right = Integer.parseInt(stuff[2]);
		}
		catch(NumberFormatException e) {
			try {
				right = vars.get(stuff[2].charAt(0));
			}
			catch(NullPointerException er){
				throw new RuntimeError(String.format("%s is undefined", stuff[2]));
			}
			
		}
		
		switch(stuff[1]) {
		case "*": sol = left*right; break;
		case "/": sol = left/right; break;
		case "+": sol = left+right; break;
		case "-": sol = left-right; break;
		case "^": sol = (int) Math.pow(left, right); break;
		default: throw new RuntimeError("Operator expected");
		}
	
	}
	answer=sol;

	findOperation(
			input.substring(0, a)+
			Integer.toString(sol)+
			input.substring(b)
			,vars
			);
}

private void checkSyntax(String input) throws SyntaxError {
		
	// Check balanced brackets
	String onlyBrackets = input.replaceAll("[^()]", "");	
	
	while(onlyBrackets.contains("()")){
		onlyBrackets = onlyBrackets.replaceAll("\\(\\)", "");
	}
	if (onlyBrackets.length() > 0) {
		if (onlyBrackets.charAt(0) == '(') {
			throw new SyntaxError(") expected");
		}
		else if (onlyBrackets.charAt(1) == ')') {
			throw new SyntaxError("( expected");
		}
	}
	// let without =
	Pattern p = Pattern.compile("let [a-z] [a-z0-9]");
	Matcher m = p.matcher(input);
	if(m.find()) {
		throw new SyntaxError("= expected");
	}
	// missing operator
	p = Pattern.compile(" let [a-z]");
    m = p.matcher(input);
	if(m.find()) {
		throw new SyntaxError(") expected");
	}
	
}

public int execExpression(String exp) throws SyntaxError, RuntimeError{
    // TODO: Assignment 3 Part 1 -- parse, calculate the expression, and return the correct value

    // TODO: Assignment 3 Part 2-1 -- when come to illegal expressions, raise proper exceptions
    
    	checkSyntax(exp);
    	return findOperation(exp,null);

}

/**
 * Main entry
 * @param args          {@code String[]} Command line arguments
 */
public static void main(String[] args) {
	
        Calculator calc = new Calculator();
        // Part 1
        String[] inputs = {
                "let x = 1;",                                                                       // 1, returns 1
                "(let x = 1) + x;",                                                                 // 2, returns 2
                "(let a = 2) + 3 * a - 5;",                                                         // 3, returns 3
                "(let x = (let y = (let z = 1))) + x + y + z;",                                     // 4, returns 4
                "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;", // 5, returns 5
                "1 + (let a = (let b = 1) + b) + a + 1;",                                           // 6, returns 6
                "(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;",                       // 7, returns 7
                "(let x = 2) ^ (let y = 3);",                                                       // 8, returns 8
                "(let y = 3) ^ (let x = 2);"                                                        // 9, returns 9
        };    

        try {
            for (int i = 0; i < inputs.length; i++)
            	System.out.println(String.format("%d -- %-90s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
//            System.out.println(calc.execExpression(inputs[6]));
        }
     // no errors with these strings
        catch(SyntaxError e) {
        	; 
        }
        catch(RuntimeError e) {
        	;
        }


        // Part 2
        inputs = new String[] {
                "1 + (2 * 3;",                  // 1, syntax error: ')' expected
                "(let x 5) + x;",               // 2, syntax error: '=' expected
                "(let x = 5) (let y = 6);",     // 3, syntax error: operator expected
                "(let x = 5 let y = 6);",       // 4, syntax error: ')' expected
                "(ler x = 5) ^ (let y = 6);",   // 5, runtime error: 'ler' undefined
                "(let x = 5) + y;"              // 6, runtime error: 'y' undefined
        };
        //  Assignment 3 Part 2-2 -- catch and deal with your exceptions here
  
          for (int i = 0; i < inputs.length; i++)
              try {
                  System.out.println(String.format("%d -- %-30s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
//              	System.out.println(calc.execExpression(inputs[4]));
              }
          catch(SyntaxError e) {
          	System.out.println(e);
          }
          catch(RuntimeError e) {
          	System.out.println(e);
          }

}

}
