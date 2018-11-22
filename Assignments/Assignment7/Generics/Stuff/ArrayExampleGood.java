/**
 * Assignment 7: Type Compatibility and Generics <br />
 * An generic array
 */
public class ArrayExampleGood {

    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        // TODO: Assignment 7 Part 1-2
        // You should add statements to this code as needed, so that
        // the code compiles correctly, and runs without error.
        GeometricShape[] geoshapes;

        geoshapes[0] = new Circle(1.0);         // Make this line to compile and run correctly
        geoshapes[1] = new Cone(2.0, 3.0);      // Make this line to compile and run correctly
    }

}
