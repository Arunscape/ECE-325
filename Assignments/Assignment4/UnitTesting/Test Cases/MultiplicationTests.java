import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for multiplication
 */
public class MultiplicationTests {

    private Calculator calc;

    @Before public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void BasicMultiplication() {
        assertEquals(calc.multiply(1.0, 1.0), Double.valueOf(1.0));
    }

    @Test public void MultiplicationNegativeNumber() {
        assertEquals(calc.multiply(1.0, -1.0), Double.valueOf(-1.0));
    }

    @Test public void RandomMultiplication() {
        double a = (Math.random() - 0.5) * 200000000;
        double b = (Math.random() - 0.5) * 200000000;
        assertEquals(calc.multiply(a, b), Double.valueOf(a*b));
    }

    @Test public void ReturnDouble() {	
        assertTrue(calc.multiply(1, 1).equals(Double.valueOf(1)));
    }

}
