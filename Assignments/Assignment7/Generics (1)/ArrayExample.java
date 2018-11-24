/**
 * Assignment 7: Type Compatibility and Generics <br />
 * A generic array
 */
public class ArrayExample {

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        // TODO: Assignment 7 Part 1-1
        // You should add statements to this code as needed, so that
        // the code compiles correctly; and when it is running, it raises
        // a java.lang.ArrayStoreException for adding the cone to geoshapes.
        // (Adding the circle should be OK.)
        GeometricShape[] geoshapes;

        geoshapes[0] = new Circle(1.0);         // Make this line to compile correctly
        geoshapes[1] = new Cone(2.0, 3.0);      // Make this line to compile correctly however raise a runtime exception
    }

}
