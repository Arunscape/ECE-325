import java.util.*;
import java.util.regex.*;

/** ---- Augmented BNF definition of the expressions ----
 * EOL        = ";" / "\n" / "\r\n"
 * operator   = "+" / "-" / "*" / "/" / "^"
 * word       = 1*( ALPHA / "_" / "$" )
 * variable   = word *( 1*DIGIT *word )             ; Spaces are not accepted inside a single variable
 * expression = "(" expression ")"                  ; A parentheses wrapped expression
 *            | 1*DIGIT                             ; A simple number (integer only)
 *            | variable                            ; A predefined variable
 *            | "let" variable "=" expression       ; A "let-equal" definition
 *            | expression operator expression      ; An operator-separated expression
 */


/**
 * Assignment 3: Exception handling <br />
 * Calculator using BNF
 * @author  MarcoXZh
 * @version 1.0.0
 */
public class Calculator_Stack_Solution {

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     * @throws Exception    Any possible exception
     */
    public static void main(String[] args) throws Exception {
        Calculator_Stack_Solution calc = new Calculator_Stack_Solution();
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
        }; // String[] inputs = { ... };
        for (int i = 0; i < inputs.length; i++)
            System.out.println(String.format("%d -- %-90s %d", i+1, inputs[i], calc.execExpression(inputs[i])));

        // Part 2
        inputs = new String[] {
                "1 + (2 * 3;",                  // 1, syntax error: ')' expected
                "(let x 5) + x;",               // 2, syntax error: '=' expected
                "(let x = 5) (let y = 6);",     // 3, syntax error: operator expected
                "(let x = 5 let y = 6);",       // 4, syntax error: ')' expected
                "(ler x = 5) ^ (let y = 6);",   // 5, runtime error: 'ler' undefined
                "(let x = 5) + y;"              // 6, runtime error: 'y' undefined
        }; // inputs = new String[] { ... };
        for (int i = 0; i < inputs.length; i++)
            try {
                System.out.println(String.format("%d -- %-30s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
            } catch(Exception ex) {
                System.out.println(String.format("%d -- %-30s %s: %s",
                                                 i+1, inputs[i], ex.getClass().getName(), ex.getMessage()));
            } // for - try - catch
    } // public static void main(String[] args)

    /**
     * Execute the expression, and return the correct value
     * @param exp           {@code String} The expression string
     * @return              {@code int}    The value of the expression
     * @throws Exception    The corresponding exception (and the corresponding error message)
     */
    public int execExpression(String exp) throws Exception {
        exp = exp.replaceAll("\\s+", " ");
        if (exp.contains(";")) {                                        // Split multiple expressions by EOL, and
            String[] exps = exp.split(";");                             // run them separately
            int value = 0;
            for (String e: exps)
                if (e.trim().length() > 0)
                    value = execExpression(e);
            return value;
        } // if (exp.contains(";"))

        HashMap<String, Integer> vars = new HashMap<String, Integer>(); // Use hash map to save variables
        Stack<String> stack = new Stack<String>();                      // Use stack to deal with parentheses
        String subExp = "";
        for (char c: exp.toCharArray()) {
            if (c == '(') {                                             // '(' means to push a stack element
                stack.push(subExp);
                subExp = "";
            } else if (c == ')') {                                      // ')' means to pop a stack element
                if (stack.isEmpty())
                    throw new SyntaxError("unmatched ')'");
                int value = execSimpleExp(subExp, vars);
                if (stack.isEmpty())
                    return value;
                subExp = stack.pop() + " " + value;
            } else
                subExp += c;
        } // for (char c: exp)
        if (!stack.isEmpty())
            throw new SyntaxError("')' expected");
        return execSimpleExp(subExp, vars);
    } // public int execExpression(String exp) throws Exception

    /**
     * Get the return value of a simple expression.<br/>
     * A simple expression may be:<br/>
     *  - A number or a variable;<br/>
     *  - A flat expression containing only variables, numbers and operators, no parentheses.
     * @param subExp        {@code String}                   The simple expression string
     * @param vars          {@code HashMap<String, Integer>} The simple expression string
     * @return              {@code int}                      The value of the simple expression
     * @throws Exception    The corresponding exception (and the corresponding error message)
     */
    private int execSimpleExp(String subExp, HashMap<String, Integer> vars) throws Exception {
        subExp = subExp.replaceAll("\\s+", " ");
        if (subExp.matches("^\\d+$"))                                   // Numbers
            return Integer.parseInt(subExp);
        else if (!subExp.equals("let") && subExp.matches("^[_$a-zA-Z]+(\\d+[_$a-zA-Z]*)*$")) {
            Integer value = vars.get(subExp);                           // Predefined variables
            if (value == null)
                throw new RuntimeError("'" + subExp + "' undefined");
            else
                return value.intValue();
        } else if (subExp.startsWith("let") || subExp.contains("=")) {  // Definitions
            if (!subExp.startsWith("let"))
                throw new RuntimeError("'" + subExp.substring(0, 3) + "' undefined");
            subExp = subExp.substring(3);
            if (subExp.contains("let"))
                throw new SyntaxError("')' expected");
            String[] parts = subExp.split("=");
            if (parts.length != 2)                              // Assumption 1: only one "=" in each let-equal pair
                throw new SyntaxError("'=' expected");
            Integer value = execSimpleExp(parts[1].trim(), vars);
            vars.put(parts[0].trim(), value);
            return value.intValue();
        } else {                                                        // Expressions (containing variables),
            String[] parts = subExp.trim().split(" ");                  // replace all variables with their values
            String numericExp = "";
            for (int i = 0; i < parts.length; i++)
                if (parts[i].matches("\\+|\\-|\\*|\\/|\\^"))
                    numericExp += parts[i];
                else
                    numericExp += execSimpleExp(parts[i], vars);
            if (!numericExp.matches("\\d+((\\+|\\-|\\*|\\/|\\^)\\d+)+"))
                throw new SyntaxError("operator expected");
            return (int) evalMathExp(numericExp);                       // calculate the numeric expression
        } // else - if
    } // private int execSimpleExp(String subExp, HashMap<String, Integer> vars) throws Exception

    /**
     * Calculate the value of a numeric math expression
     * @param numericExp    {@code String} The numeric math expression
     * @return              {@code int}    The value of the expression
     */
    private int evalMathExp(String numericExp) {
        Pattern pattern = Pattern.compile("\\d+\\^\\d+");               // Power first
        Matcher matcher = pattern.matcher(numericExp);
        while (matcher.find()) {
            String exp = matcher.group();
            String[] nums = exp.split("\\^");
            String value = (int) Math.pow(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])) + "";
            numericExp = numericExp.replace(exp, value);
            matcher = pattern.matcher(numericExp);
        } // while (matcher.find())

        pattern = Pattern.compile("\\d+[\\*|\\/]\\d+");                 // Multiplication/division second
        matcher = pattern.matcher(numericExp);
        while (matcher.find()) {
            String exp = matcher.group();
            String[] nums = exp.split("\\*|\\/");
            int value = exp.contains("*") ? Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]) :
                                            Integer.parseInt(nums[0]) / Integer.parseInt(nums[1]);
            numericExp = numericExp.replace(exp, value + "");
            matcher = pattern.matcher(numericExp);
        } // while (matcher.find())

        pattern = Pattern.compile("\\d+(\\+|-)\\d+");                   // Addition/Subtraction last
        matcher = pattern.matcher(numericExp);
        while (matcher.find()) {
            String exp = matcher.group();
            String[] nums = exp.split("\\+|-");
            int value = exp.contains("+") ? Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]) :
                                            Integer.parseInt(nums[0]) - Integer.parseInt(nums[1]);
            numericExp = numericExp.replace(exp, value + "");
            matcher = pattern.matcher(numericExp);
        } // while (matcher.find())

        return Integer.parseInt(numericExp);
    } // private int evalNumericExp(String numericExp)

} // public class Calculator_Solution

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
    } // public SyntaxError(String msg)
} // class SyntaxError extends Exception

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
    } // public RuntimeError(String msg)
} // class RuntimeError extends Exception

