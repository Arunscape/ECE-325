/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code Cone} class
 */
public class Cone implements ThreeDShape<Cone> {

	private double radius, height;

	public Cone(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}

	public double volume() {
		return (1.0 / 3.0) * Math.PI * radius * radius * height;
	}

	public void describe() {
		System.out.println("Cone[radius=" + radius + ", height=" + height + "]");
	}

	public Cone supersize() {
		return new Cone(this.radius * 2, this.height * 2);
	}
}
