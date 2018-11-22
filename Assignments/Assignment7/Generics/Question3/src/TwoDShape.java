/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code TwoDShape} interface
 */
public interface TwoDShape<T extends TwoDShape<T>> extends GeometricShape<T>{
    public double area();
}
