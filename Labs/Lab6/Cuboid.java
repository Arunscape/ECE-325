/**
 * Lab 6: Anonymous Inner Classes and Reflection <br />
 * The {@code Cuboid} class
 */
public class Cuboid {
    /**
     * Length, width and height of the cuboid
     */
    private double length, width, height;

    /**
     * Constructor: create a new cuboid
     * @param length    {@code double} Length of the cuboid
     * @param width     {@code double} Width of the cuboid
     * @param height    {@code double} Height of the cuboid
     */
    public Cuboid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Get length of the cuboid
     * @return          {@code double} Length of the cuboid
     */
    public double getLength() {
        return length;
    }

    /**
     * Get width of the cuboid
     * @return          {@code double} Width of the cuboid
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get height of the cuboid
     * @return          {@code double} Height of the cuboid
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get surface area of the cuboid
     * @return          {@code double} Surface area of the cuboid
     */
    public double getArea() {
        return 2 * (length * width + width * height + height * width);
    }

    /**
     * Get volume of the cuboid
     * @return          {@code double} Volume of the cuboid
     */
    public double getVolume() {
        return length * width * height;
    }

    /**
     * Print the information of the cuboid into a string
     * @return          {@code String} information string
     */
    @Override public String toString() {
        return String.format("Cuboid[%.2f, %.2f, %.2f]: area=%.2f, volume=%.2f",
                             length, width, height, getArea(), getVolume());
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        Cuboid[] cuboids = new Cuboid[] {new Cuboid(3.0, 1.0, 2.0),     // 1
                                         new Cuboid(2.0, 3.0, 3.0),     // 2
                                         new Cuboid(2.0, 2.0, 2.0),     // 3
                                         new Cuboid(3.0, 2.0, 2.0),     // 4
                                         new Cuboid(1.0, 2.0, 2.0)};    // 5

        System.out.println("Sort by length");
        // TODO: Lab 6 Part 1-1 -- sort cuboids by length
        
        for (Cuboid c: cuboids)
            System.out.println(c);
        System.out.println();

        System.out.println("Sort by area");
        // TODO: Lab 6 Part 1-2 -- sort cuboids by area
        
        for (Cuboid c: cuboids)
            System.out.println(c);
        System.out.println();

        System.out.println("Sort by volume");
        // TODO: Lab 6 Part 1-3 -- sort cuboids by volume
        
        for (Cuboid c: cuboids)
            System.out.println(c);
        System.out.println();

        System.out.println("Sort by length first and then area");
        // TODO: Lab 6 Part 1-4 -- sort cuboids by length first and then area
        
        for (Cuboid c: cuboids)
            System.out.println(c);
        System.out.println();
    }

}
