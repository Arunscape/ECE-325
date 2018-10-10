import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for solving square roots
 */
public class SquareRootTests {

    // TODO: Assignment 4 Part 2 -- Checkpoint4
	private Calculator calc;

    @Before public void setUp() throws Exception {
    	calc = new Calculator();
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void testRandomPositiveSquareRoot() {
        // You cannot use the Math.sqrt() function in the test!
    	double ans = (Math.random() - 0.5) * 200000000;
    	while (ans <= 0) {
    		ans = (Math.random() - 0.5) * 200000000;
    	}
    	double x = ans*ans;
    	assertEquals(calc.sqrt(x),Double.valueOf(ans));   	
        
    }

    @Test public void testRandomNegitiveSquareRoot() {
        // The result should be a complex number i.e. Double.isNaN()
    	double x = (Math.random() - 0.5) * 200000000;
    	while (x >= 0) {
    		x = (Math.random() - 0.5) * 200000000;
    	}
    	assertTrue(calc.sqrt(x).isNaN());
        
    }

    @Test public void testSquareRootofZero() {
        // You cannot use the Math.sqrt() function in the test!
        assertEquals(calc.sqrt(0),Double.valueOf(0));
    }

    @Test public void testSquareRootofOne() {
        // You cannot use the Math.sqrt() function in the test!
    	assertEquals(calc.sqrt(1),Double.valueOf(1));
    }

}
