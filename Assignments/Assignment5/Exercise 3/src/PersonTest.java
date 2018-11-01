import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {

	@Test
	public void runTest() {

		Set<Person> persons = new TreeSet<Person>(new PersonComparator());
		persons.add(new Person(32));
		persons.add(new Person(17));
		persons.add(new Person(13));
		persons.add(new Person(35));
		persons.add(new Person(27));

		Iterator<Person> iter = persons.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		//testing personcomparator
		Person p1 = new Person(10);
		Person p2 = new Person(30);
		PersonComparator c = new PersonComparator();
		
		assertEquals(c.compare(p1, p2), -1);
		assertEquals(c.compare(p2, p1), 1);
		assertEquals(c.compare(p1, new Person(10)), 0);
		
		
//		assertTrue(c.equals(p1, new Person(10)));
//		assertFalse(c.equals(p1, p2));
		
	}
	

}

//
///**
// * Assignment 5: Interfaces <br />
// * Part 3: The {@code PersonTest} class
// */
//public class PersonTest {
//    public static void runTest() {
//        // TODO: Assignment 5 Part 3 -- rewrite this using JUnit
//        Set<Person> persons = new TreeSet<Person>(new PersonComparator());
//        persons.add(new Person(32));
//        persons.add(new Person(17));
//        persons.add(new Person(13));
//        persons.add(new Person(35));
//        persons.add(new Person(27));
//
//        Iterator<Person> iter = persons.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
//    }
//}

