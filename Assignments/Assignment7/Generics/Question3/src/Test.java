/**
 * Assignment 7: Type Compatibility and Generics <br />
 * A test class to make sure that the various classes of geometric objects compile and execute OK
 */
public class Test {

    public static void main(String[] args) {
        GeometricShape g1 = new Circle(1.0);
        GeometricShape g2 = new Cone(2.0, 3.0);
        GeometricShape g3 = new Rectangle(2.0, 3.0);
        GeometricShape g4 = new Sphere(2.0);
        g1.describe();
        g2.describe();
        g3.describe();
        g4.describe();
    }

}
