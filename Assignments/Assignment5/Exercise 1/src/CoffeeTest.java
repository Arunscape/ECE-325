import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class CoffeeTest {

	@Test
	public void testCompareTo() {
		List<Coffee> coffees = new ArrayList<Coffee>();
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(2));
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(20));
        coffees.add(new Coffee(5));
        Collections.sort(coffees);

        System.out.println("Coffees in order of strength:");
        for (Coffee type : coffees) {
            System.out.println(type);
        }
        
        // Checking that the compareTo method works
        Coffee c = new Coffee(10);
        assertEquals(c.compareTo(new Coffee(0)), 1);
        assertEquals(c.compareTo(new Coffee(10)), 0);
        assertEquals(c.compareTo(new Coffee(20)), -1);
        
	}

}
//
///**
// * Assignment 5: Interfaces <br />
// * Part 1: The {@code CoffeeTest} class
// */
//public class CoffeeTest {
//    public static void testComparable() {
//        // TODO: Assignment 5 Part 1 -- rewrite this using JUnit
//        List<Coffee> coffees = new ArrayList<Coffee>();
//        coffees.add(new Coffee(10));
//        coffees.add(new Coffee(2));
//        coffees.add(new Coffee(10));
//        coffees.add(new Coffee(20));
//        coffees.add(new Coffee(5));
//        Collections.sort(coffees);
//
//        System.out.println("Coffees in order of strength:");
//        for (Coffee type : coffees) {
//            System.out.println(type);
//        }
//    }
//}
