import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for creation
 */
public class CreationTests {

    private Calculator calc;

    @Before public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After public void tearDown() throws Exception {
    }

    @Test public void testCreation() {
        assertNotNull(calc);		
    }

}
