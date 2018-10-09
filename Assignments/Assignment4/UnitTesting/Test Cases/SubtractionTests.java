import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for subtraction
 */
public class SubtractionTests {

    private Calculator calc;

    @Before public void setUp() {
        calc = new Calculator();
    }

    @After public void tearDown() {
	}

    @Test public void testBasicSubtraction() {
        assertEquals(calc.subtract(1.0, 1.0), Double.valueOf(0));	
    }

    @Test public void testSubtractionNegativeNumber() {
        assertEquals(calc.subtract(1.0, -1.0), Double.valueOf(2));	
    }

    @Test public void testRandomSubtraction() {
        double a = (Math.random() - 0.5) * 200000000;
        double b = (Math.random() - 0.5) * 200000000;
        assertEquals(calc.subtract(a, b), Double.valueOf(a-b));
    }

}
