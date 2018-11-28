import java.util.*;
import java.util.regex.*;

/**
 * Assignment 3: Exception handling <br />
 * Calculator using BNF
 * @author  Jason Yuan
 * @version 1.0.1
 */
public class Calculator_Regex_Solution {
	private HashMap<String, Integer> var = new HashMap<String, Integer>();
	
    /**
     * Execute the expression, and return the correct value
     * @param exp           {@code String} The expression string
     * @return              {@code int}    The value of the expression
     * @throws RuntimeError 
     * @throws SyntaxError 
     * @throws NumberFormatException 
     */
    public int execExpression(String exp) throws Exception {
    	
    	var = new HashMap<String, Integer>();
    	
        // TODO: Assignment 3 Part 1 -- parse, calculate the expression, and return the correct value
    	return Integer.parseInt(parse(exp));
    }
    
    /**
     * Execute the expression, and return the correct value
     * @param exp           {@code String} The expression string
     * @return              {@code String} The value of the expression
     * @throws RuntimeError 
     * @throws SyntaxError 
     */
	private String parse(String exp) throws SyntaxError, RuntimeError {
		Matcher P = Pattern.compile("\\([^\\(\\)]*(\\)|;)").matcher(exp);
		Matcher E = Pattern.compile("([a-zA-Z]+|[0-9]+) (\\^) ([a-zA-Z]+|[0-9]+)").matcher(exp);
		Matcher DM = Pattern.compile("([a-zA-Z]+|[0-9]+) (\\*|\\/) ([a-zA-Z]+|[0-9]+)").matcher(exp);
		Matcher AS = Pattern.compile("([a-zA-Z]+|[0-9]+) (\\+|\\-) ([a-zA-Z]+|[0-9]+)").matcher(exp);
		Matcher LET = Pattern.compile("let ([a-zA-Z]+)( = | *)([a-zA-Z]+|[0-9]+)").matcher(exp);
		Matcher SOL = Pattern.compile("([0-9]+)[;]?").matcher(exp);

		if (SOL.matches()) {
			return SOL.group(1);
		}
		if (P.find()) {
			if (P.group(1).equals(";")) throw new SyntaxError("unmatched ')'");
			return calc(exp, P.start(), P.end(), exp.substring(P.start() + 1, P.end() - 1), "(", "");
		}
		if (E.find()) {
			return calc(exp, E.start(), E.end(), E.group(1), E.group(2), E.group(3));
		}
		if (DM.find()) {
			return calc(exp, DM.start(), DM.end(), DM.group(1), DM.group(2), DM.group(3));
		}
		if (AS.find()) {
			return calc(exp, AS.start(), AS.end(), AS.group(1), AS.group(2), AS.group(3));
		}
		if (LET.find()) {
			if (LET.group(2).equals(" ")) throw new SyntaxError("'=' expected");
			return calc(exp, LET.start(), LET.end(), LET.group(1), LET.group(2).trim(), LET.group(3));
		}

		// TODO: Assignment 3 Part 2-1 -- when come to illegal expressions, raise proper exceptions
		for (String s : exp.split(" |;")) get_var(s);
		if (!exp.contains(";")) throw new SyntaxError("')' expected");
		if (Pattern.compile("[a-zA-Z0-9]+ [a-zA-Z0-9]+").matcher(exp).find()) throw new SyntaxError("operator expected");
		throw new RuntimeError("unknown error");
	}
	
    /**
     * Get the return value of a simple expression.<br/>
     * A simple expression is a flat expression containing only variables, numbers and operators, no parentheses.
     * @param exp           {@code String}                   The full expression string
     * @param l_bound       {@code int}                      The left bound of the simple expression to be evaluated
     * @param r_bound       {@code int}                      The right bound of the simple expression to be evaluated
     * @param first         {@code String}                   The first operand
     * @param op            {@code String}                   The operator
     * @param second        {@code String}                   The second operand
     * @return              {@code String}                   The value of the simple expression
     * @throws RuntimeError 
     * @throws SyntaxError 
     */
	private String calc(String exp, int l_bound, int r_bound, String first, String op, String second) throws SyntaxError, RuntimeError {
		String solution = "";
		switch (op) {
			case "(": solution = parse(first); break;
			case "^": solution += (int) Math.pow(get_var(first), get_var(second)); break;
			case "*": solution += get_var(first) * get_var(second); break;
			case "/": solution += get_var(first) / get_var(second); break;
			case "+": solution += get_var(first) + get_var(second); break;
			case "-": solution += get_var(first) - get_var(second); break;
			case "=": solution += get_var(second);
					  var.put(first, get_var(second));
		}
		return parse(exp.substring(0, l_bound) + solution + exp.substring(r_bound, exp.length()));
	}
	
    /**
     * Finds the value of a number or a variable
     * @param variable      {@code String} The number or variable
     * @return              {@code int}    The value of the number or variable
     * @throws RuntimeError 
     */
	private int get_var(String variable) throws RuntimeError {
		try {
			return Integer.parseInt(variable.trim());
		} catch(NumberFormatException e) {
			try {
				return var.get(variable);
			} catch(NullPointerException ex) {
				throw new RuntimeError("'" + variable + "' undefined");
			}
		}
	}
	
    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
    	Calculator_Regex_Solution calc = new Calculator_Regex_Solution();
        // Part 1
        String[] inputs = {
            "let x = 1;",                                                                           // 1, returns 1
            "(let x = 1) + x;",                                                                     // 2, returns 2
            "(let a = 2) + 3 * a - 5;",                                                             // 3, returns 3
            "(let x = (let y = (let z = 1))) + x + y + z;",                                         // 4, returns 4
            "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;",   // 5, returns 5
            "1 + (let a = (let b = 1) + b) + a + 1;",                                               // 6, returns 6
            "(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;",                           // 7, returns 7
            "(let x = 2) ^ (let y = 3);",                                                           // 8, returns 8
            "(let y = 3) ^ (let x = 2);"                                                            // 9, returns 9
        };
        for (int i = 0; i < inputs.length; i++)
			try {
				System.out.println(String.format("%d -- %-90s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
			} catch (Exception ex) {}

        // Part 2
        inputs = new String[] {
                "1 + (2 * 3;",                  // 1, syntax error: ')' expected
                "(let x 5) + x;",               // 2, syntax error: '=' expected
                "(let x = 5) (let y = 6);",     // 3, syntax error: operator expected
                "(let x = 5 let y = 6);",       // 4, syntax error: ')' expected
                "(ler x = 5) ^ (let y = 6);",   // 5, runtime error: 'ler' undefined
                "(let x = 5) + y;"              // 6, runtime error: 'y' undefined
        };
        // TODO: Assignment 3 Part 2-2 -- catch and deal with your exceptions here
        for (int i = 0; i < inputs.length; i++) 
            try {
                System.out.println(String.format("%d -- %-30s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
            } catch(Exception ex) {
                System.out.println(String.format("%d -- %-30s %s: %s",
                                                 i+1, inputs[i], ex.getClass().getName(), ex.getMessage()));
            }
    }
}

/**
 * The syntax error exception
 */
@SuppressWarnings("serial")
class SyntaxError extends Exception {
    /**
     * Constructor -- create a new syntax error exception
     * @param msg   {@code String}  The error message
     */
    public SyntaxError(String msg) {
        super(msg);
    }
}

/**
 * The runtime error exception
 */
@SuppressWarnings("serial")
class RuntimeError extends Exception {
    /**
     * Constructor -- create a new runtime error exception
     * @param msg   {@code String}  The error message
     */
    public RuntimeError(String msg) {
        super(msg);
    }
}