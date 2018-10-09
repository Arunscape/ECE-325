import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for solving quadratic function
 */
public class SolveQuadraticTests {

    private Calculator calc;
    private double epsilon = 0.0000001;

    @Before public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void testBasicRoots() { 
        double a = 1.0, b = -3.0, c = 2.0;
        Double[] roots = calc.getRoots(a, b, c);
        assertEquals(roots.length, 2);
        double x = roots[0].doubleValue();
        assertTrue(Math.abs(a * x * x + b * x + c) < epsilon);
        x = roots[1].doubleValue();
        assertTrue(Math.abs(a * x * x + b * x + c) < epsilon);
    }

    @Test public void testSingleRoot() {
        double a = 1.0, b = 2.0, c = 1.0;
        Double[] roots = calc.getRoots(a, b, c);
        assertEquals(roots.length,1);
        double x = roots[0].doubleValue();
        assertTrue(Math.abs(a * x * x + b * x + c) < epsilon);
    }

    @Test public void testRandomSolvableQuadratic() {
        double a = (Math.random() - 0.5) * 200000000;
        double b = (Math.random() - 0.5) * 200000000;
        double c = (Math.random() - 0.5) * 200000000;
        double x;
        while (b * b - 4 * a * c < epsilon) {
            a = (Math.random() - 0.5) * 200000000;
            b = (Math.random() - 0.5) * 200000000;
            c = (Math.random() - 0.5) * 200000000;
        }
        Double[] roots = calc.getRoots(a, b, c);
        assertEquals(roots.length, 2);
        x = roots[0].doubleValue();
        assertTrue(Math.abs(a * x * x + b * x + c) < epsilon);
        x = roots[1].doubleValue();
        assertTrue(Math.abs(a * x * x + b * x + c) < epsilon);
    }

    @Test public void testRandomNonSolvableQuadratic() {
        double a = (Math.random() - 0.5)*200000000;
        double b = (Math.random() - 0.5)*200000000;
        double c = (Math.random() - 0.5)*200000000;
        while (b * b - 4 * a * c > -epsilon) {
            a = (Math.random() - 0.5)*200000000;
            b = (Math.random() - 0.5)*200000000;
            c = (Math.random() - 0.5)*200000000;
        }
        Double[] roots = calc.getRoots(a, b, c);
        assertEquals(roots.length, 2);
        assertTrue(roots[0].isNaN());
        assertTrue(roots[1].isNaN());
    }

}
