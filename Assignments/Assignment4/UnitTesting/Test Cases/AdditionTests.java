import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for addition
 */
public class AdditionTests {

    private Calculator calc;

    @Before public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void BasicAddition() {
        assertEquals(calc.add(1.0, 1.0), Double.valueOf(2));
    }

    @Test public void AdditionNegativeNumber() {
        assertEquals(calc.add(1, -1), Double.valueOf(0));	
    }

    @Test public void RandomAddition() {
        double a = (Math.random() - 0.5) * 200000000;
        double b = (Math.random() - 0.5) * 200000000;
        assertEquals(calc.add(a, b), Double.valueOf(a + b));
    }

    @Test public void ReturnDouble() {	
        assertTrue(calc.add(1, 1).equals(Double.valueOf(2)));
    }

}
