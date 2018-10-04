/**
 * Assignment 3: Exception handling <br />
 * Calculator using BNF
 */
import java.util.HashMap;
import java.util.regex.*;
public class Calculator {

/**
 * Execute the expression, and return the correct value
 * @param exp           {@code String} The expression string
 * @return              {@code int}    The value of the expression
 */
private Pattern findBrackets = Pattern.compile("\\([^\\(\\)]*\\)");
private Pattern findExponents = Pattern.compile("[0-9]+ \\^ [0-9]+");
private Pattern findMulDiv = Pattern.compile("[0-9]+ (\\*|/) [0-9]+");
private Pattern findAddSub = Pattern.compile("[0-9]+ (\\+|-) [0-9]+");
private Pattern findAssignments = Pattern.compile("let [a-z] = ([a-z]|[0-9]+)");
private int answer;

private int findOperation(String input, HashMap<Character,Integer> vars){
		Matcher m = null;
		
		HashMap<Character,Integer> dict;
		if (vars==null) {
			dict=new HashMap<>();
		}
		else {
			dict=vars;
		}
		
		
        if         (findBrackets.matcher(input).find())        { m=findBrackets.matcher(input);        }
        else if (findExponents.matcher(input).find())     { m=findExponents.matcher(input);     }
        else if (findMulDiv.matcher(input).find())           { m=findMulDiv.matcher(input);           }
        else if (findAddSub.matcher(input).find())          { m=findAddSub.matcher(input);         }
        else if (findAssignments.matcher(input).find()) { m=findAssignments.matcher(input); }
        else {return answer;}
        // R E C U R S I O N
//        System.out.println(input);
        
        
       if(m.find()) {
//    	   System.out.println(input.substring(m.start(), m.end()));
//    	   System.out.print(m.start());
//    	   System.out.print(" ");
//    	   System.out.println(m.end());
//    	   System.out.println(input.indexOf(")"));
//    	   System.out.println(input);
         calculate(input, m.start(), m.end(),dict);
        }
       return answer;
       
}

//private int calculate(int a, String op, int b) {
//	if(op=="*") {return a*b;}
//	else if(op=="/") {return a/b;}
//	else if(op=="+"){return a+b;}
//	else if(op=="-"){return a-b;}
//	return -1;
//}

private void calculate(String input, int a, int b, HashMap<Character,Integer> vars) {
	int sol;
	String exp = input.substring(a,b);
	Matcher m = findAssignments.matcher(exp);
	if (m.find()) {
		// in an expression let x = #, the variable is; always the 4th index in the string
		// the value is the substring from index 8 onwards
//		System.out.println(m.group());
//		System.out.println(m.group().charAt(4));
//		System.out.println(m.group().substring(8));
		sol= Integer.parseInt(m.group().substring(8));
		vars.put(m.group().charAt(4), sol);
		
	}
	else {
		String[] stuff = exp.split(" ");
		System.out.println(stuff);
//		Matcher m1 = Pattern.compile("[*/+-]").matcher(input);
//		if(m1.find()) {
//			String op = m1.group();
//			System.out.println(op);
//		}
		sol = 10;
	}
	answer=sol;
	
//	System.out.println(input);
//	String op = Pattern.compile("[*/+-]").matcher(input).group();
//	System.out.println(op);
	
	findOperation(
			input.substring(0, a)+
			Integer.toString(sol)+
			input.substring(b)
			,vars
			);
	
//	return s
}

public int execExpression(String exp) {
        int returnValue = -1;
        // TODO: Assignment 3 Part 1 -- parse, calculate the expression, and return the correct value

        // TODO: Assignment 3 Part 2-1 -- when come to illegal expressions, raise proper exceptions
        
        return findOperation(exp,null);
        
//        return returnValue;
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
//        for (int i = 0; i < inputs.length; i++)
//                System.out.println(String.format("%d -- %-90s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
//        for (int i = 0; i < inputs.length; i++)
//        	calc.execExpression(inputs[i]);
        System.out.println(calc.execExpression(inputs[1]));

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
//        for (int i = 0; i < inputs.length; i++)
//                System.out.println(String.format("%d -- %-30s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
}

}
