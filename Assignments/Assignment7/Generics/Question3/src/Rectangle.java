/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code Rectangle} class
 */
public class Rectangle implements TwoDShape<Rectangle> {

	private double width, height;

	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public double area() {
		return width * height;
	}

	public double perimeter() {
		return 2.0 * (width + height);
	}

	public void describe() {
		System.out.println("Rectangle[width=" + width + ", height=" + height + "]");
	}

	public Rectangle supersize() {
		return new Rectangle(this.width * 2, this.height * 2);
	}

}
