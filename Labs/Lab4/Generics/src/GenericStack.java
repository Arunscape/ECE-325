import java.util.ArrayList;
import java.util.Arrays;

/**
 * Lab 4: Generics <br />
 * The {@code GenericStack} class
 */
@SuppressWarnings("serial")
public class GenericStack<T> extends ArrayList<T>{
	

    /**
     * Query the top element
     * @return          {@code T} the top element
     */
    public T peek() {
        // TODO: Lab 4 Part 1-1 -- GenericStack, finish the peek method
        
        return this.get(this.size()-1);
    }

    /**
     * Add a new element as top element
     * @param value     {@code T} the new element
     */
    public void push(T value) {
        // TODO: Lab 4 Part 1-2 -- GenericStack, finish the push method
        this.add(value);
    }

    /**
     * Remove the top element
     * @return          {@code T} the removed element
     */
    public T pop() {
        // TODO: Lab 4 Part 1-3 -- GenericStack, finish the pop method
        
        return this.remove(this.size()-1);
    }

    /**
     * Query the size of the stack
     * @return          {@code int} size of the element
     */
//    public int size() {
//        // TODO: Lab 4 Part 1-4 -- GenericStack, finish the size method
//        
//        return -1;
//    }

    /**
     * Check if the stack is empty of not
     * @return          {@code boolean} {@code true} for empty; {@code false} for not
     */
    public boolean isEmpty() {
        // TODO: Lab 4 Part 1-5 -- GenericStack, finish the isEmpty method
        
        return this.size() == 0;
    }

    /**
     * Calculate a postfix expression
     * @param exp       {@code String} the postfix expression
     * @return          {@code Double} the value of the expression
     */
    public static Double calcPostfixExpression(String exp) {
        // TODO: Lab 4 Part 1-6 -- GenericStack, calculate postfix expression
        String[] stuff = exp.split(" ");
        String[] op =  {"+", "-", "*", "/", "^"};
        GenericStack<Double> stack = new GenericStack<Double>();
        for (String s: stuff) {
        	if(!Arrays.asList(op).contains(s)) {
        		stack.push(Double.parseDouble(s));
        	}
        	else {
        		double op2 = stack.pop();
        		double op1 = stack.pop();
        		if (s.equals("+")){
        			stack.push(op1+op2);
        		} else if (s.equals("-")) {
        			stack.push(op1-op2);
        		} else if (s.equals("*")) {
        			stack.push(op1*op2);
        		}
        		else if (s.equals("/")) {
        			stack.push(op1/op2);
        		}
        		else if (s.equals("^")) {
        			stack.push((double) Math.pow(op1, op2));
        		}
        		else {
        			System.out.println("REEEEEEEE");
        		}
        	}
        }
        	
        return Double.valueOf(stack.peek());
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[] expressions = {
                "4 1 +",                    // 1: = 5
                "2 6 -",                    // 2: = -4
                "3 3 *",                    // 3: = 9
                "1 4 /",                    // 4: = 0.25
                "2 3 ^",                    // 5: = 8
                "2 1 + 4 * 8 - 3 ^ 6 -",    // 6: 58
        }; // String[] expressions = { ... };
        for (String s: expressions)
            System.out.println(s + " = " + calcPostfixExpression(s));
    }

}
