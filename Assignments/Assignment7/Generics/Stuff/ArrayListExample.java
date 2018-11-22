import java.util.ArrayList;

/**
 * Assignment 7: Type Compatibility and Generics <br />
 * An generic array
 */
public class ArrayListExample {

    // TODO: Assignment 7 Part 2 -- define the following four methods. No casts allowed!

    /**
     * total_area -- takes a list of 2d shapes and calculates the total area of those shapes
     */


    /**
     * total_perimeter -- takes a list of rectangles and calculates the total perimeter
     */


    /**
     * describe_all -- takes a list of geometric shapes and invokes the "{@code describe}" method
     * on each of them, then prints out the total number of shapes
     */


    /**
     * add_empties -- takes a list of geometric shapes and adds the following objects to it: <br/>
     *      {@code new Circle(0.0);          } <br/>
     *      {@code new Cone(0.0, 0.0);       } <br/>
     *      {@code new Rectangle(0.0, 0.0);  } <br/>
     *      {@code new Sphere(0.0);          }
     */


    /**
     * Difficult Question: <br/>
     * supersize_list -- takes an array list of some kind of geometric shapes
     * and returns an array list of the same type, with the shapes super sized
     */
    // TODO: Assignment 7 Part 3 -- implement the "supersize_list" method


    // leave this main method as is:
    /**
     * Main entry
     * @param args          {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        /* ================ Codes for Part 2 ================ */
        // Make a list of shapes, add a circle, a cone and some empty shapes, and then describe all of the shapes
        System.out.println("Example with a list of shapes with a circle,  a cone, and some empty shapes");
        ArrayList<GeometricShape> shapes = new ArrayList<GeometricShape>();
        shapes.add(new Circle(1.0));
        shapes.add(new Cone(2.0, 3.0));
        add_empties(shapes);
        describe_all(shapes);
        // Make a list of rectangles, add some rectangles, describe them, and calculate the total area and perimeter
        System.out.println();
        System.out.println("Example with a list of rectangles");
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
        rects.add(new Rectangle(2.0, 3.0));
        rects.add(new Rectangle(5.0, 5.0));
        describe_all(rects);
        System.out.print("total area of rectangles: ");
        System.out.println(total_area(rects));
        System.out.print("total perimeter of rectangles: ");
        System.out.println(total_perimeter(rects));
        // Make a list of 2d shapes, add a rectangle and a circle, describe them and calculate the total area.
        System.out.println();
        System.out.print("Example with a list of 2d shapes with a circle ");
        System.out.println("and a rectangle");
        ArrayList<TwoDShape> flat_shapes = new ArrayList<TwoDShape>();
        flat_shapes.add(new Rectangle(10.0, 10.0));
        flat_shapes.add(new Circle(2.0));
        describe_all(flat_shapes);
        System.out.print("total area of flat shapes: ");
        System.out.println(total_area(flat_shapes));
        // Make a list of spheres and describe them
        ArrayList<Sphere> spheres = new ArrayList<Sphere>();
        spheres.add(new Sphere(10.0));
        spheres.add(new Sphere(50.0));
        spheres.add(new Sphere(0.0));
        System.out.println();
        System.out.println("Example list of spheres");
        describe_all(spheres);

        /* ================ Codes for Part 3 the difficult question ================ */
        /*
        // Make a list of rectangles and add some rectangles.
        ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
        rects.add(new Rectangle(2.0, 3.0));
        rects.add(new Rectangle(5.0, 5.0));
        // Make a list of spheres
        ArrayList<Sphere> spheres = new ArrayList<Sphere>();
        spheres.add(new Sphere(10.0));
        spheres.add(new Sphere(50.0));
        spheres.add(new Sphere(0.0));
        // Super-size them
        System.out.println();
        System.out.println("super-sizing a list of rectangles");
        ArrayList<Rectangle> double_rects = supersize_list(rects);
        describe_all(double_rects);
        System.out.println();
        System.out.println("super-sizing a list of spheres");
        ArrayList<Sphere> double_spheres = supersize_list(spheres);
        describe_all(double_spheres);
        */
    }

}
