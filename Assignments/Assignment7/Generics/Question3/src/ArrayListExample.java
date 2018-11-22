import java.util.ArrayList;

/**
 * Assignment 7: Type Compatibility and Generics <br />
 * An generic array
 * 
 * @param <T extends TwoDShape>
 */
public class ArrayListExample {

// TODO: Assignment 7 Part 2 -- define the following four methods. No casts allowed!

	public static <T extends TwoDShape> double total_area(ArrayList<T> a) {
		double r = 0;
		for (T t : a)
			r += t.area();
		return r;
	}

	/**
	 * total_perimeter -- takes a list of rectangles and calculates the total
	 * perimeter
	 */
	public static double total_perimeter(ArrayList<Rectangle> a) {
		double r = 0;
		for (Rectangle rect : a)
			r += rect.perimeter();
		return r;
	}

	/**
	 * describe_all -- takes a list of geometric shapes and invokes the
	 * "{@code describe}" method on each of them, then prints out the total number
	 * of shapes
	 */
	public static <T extends GeometricShape> void describe_all(ArrayList<T> a) {
		for (T t : a) {
			t.describe();
			System.out.println();
		}
		System.out.println(String.format("Total number of shapes: %d", a.size()));
	}

	/**
	 * add_empties -- takes a list of geometric shapes and adds the following
	 * objects to it: <br/>
	 * {@code new Circle(0.0);          } <br/>
	 * {@code new Cone(0.0, 0.0);       } <br/>
	 * {@code new Rectangle(0.0, 0.0);  } <br/>
	 * {@code new Sphere(0.0);          }
	 */
	public static void add_empties(ArrayList<GeometricShape> a) {
		a.add(new Circle(0.0));
		a.add(new Cone(0.0, 0.0));
		a.add(new Rectangle(0.0, 0.0));
		a.add(new Sphere(0.0));

	}
	
	public static <T extends GeometricShape<T>> ArrayList<T> supersize_list(ArrayList<T> a){
		ArrayList<T> r = new ArrayList<T>();
		
		for (T t: a)
			r.add(t.supersize());
		
		return r;
		
	}

	/**
	 * Difficult Question: <br/>
	 * supersize_list -- takes an array list of some kind of geometric shapes and
	 * returns an array list of the same type, with the shapes super sized
	 */
// TODO: Assignment 7 Part 3 -- implement the "supersize_list" method

// leave this main method as is:
	/**
	 * Main entry
	 * 
	 * @param args {@code String[]} Command line arguments
	 */
	public static void main(String[] args) {

		/* ================ Codes for Part 3 the difficult question ================ */

		// Make a list of rectangles and add some rectangles.
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		rects.add(new Rectangle(2.0, 3.0));

		rects.add(new Rectangle(5.0, 5.0)); // Make a list of spheres
		ArrayList<Sphere> spheres = new ArrayList<Sphere>();
		spheres.add(new Sphere(10.0));
		spheres.add(new Sphere(50.0));
		spheres.add(new Sphere(0.0));
		// Super-size them System.out.println();
		System.out.println("super-sizing a list of rectangles");
		ArrayList<Rectangle> double_rects = supersize_list(rects);
		describe_all(double_rects);
		System.out.println();
		System.out.println("super-sizing a list of spheres");
		ArrayList<Sphere> double_spheres = supersize_list(spheres);
		describe_all(double_spheres);

	}

}
