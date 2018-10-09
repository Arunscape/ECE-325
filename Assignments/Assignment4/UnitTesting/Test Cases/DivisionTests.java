import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for division
 */
public class DivisionTests {

    private Calculator calc;
    private double epsilon = 0.0000001;

    @Before public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void testBasicDivision() {
        assertEquals(calc.divide(1.0, 2.0), Double.valueOf(0.5));	
    }

    @Test public void testDivisionNegativeNumber() {
        assertEquals(calc.divide(1.0, -2.0), Double.valueOf(-0.5));	
    }

    @Test public void testRandomDivision() {
        double a = (Math.random() - 0.5) * 200000000;
        double b = (Math.random() - 0.5) * 200000000;
        while (Math.abs(b) < epsilon)
            b = (Math.random() - 0.5) * 200000000;
        assertEquals(calc.divide(a, b), Double.valueOf(a / b));
    }

    @Test public void testDivisionByZero() {
        double a = (Math.random() - 0.5)*200000000;
        double b = 0.0;
        assertTrue(calc.divide(a, b).isInfinite());
    }

    @Test public void ReturnDouble() {	
        assertTrue(calc.divide(1, 1).equals(Double.valueOf(1)));
    }

}
