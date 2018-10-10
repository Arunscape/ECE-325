/**
 * Assignment 4 Part 2: Unit Testing <br />
 * The calculator to run the test cases
 */
// TODO: Assignment 4 Part 2 -- Create the Calculator here

class Calculator{
	
	public Double add(double x, double y) {
		return x+y;
	}
	
	public Double subtract(double x, double y) {
		return x-y;
	}
	
	public Double multiply(double x, double y) {
		return x*y;
	}
	
	public Double divide(double x, double y) {
		return x/y;
	}
	
	public Double[] getRoots(double a, double b, double c) {
		// ax²+bx+c
		// x=(-b +/- √b²-4ac)/(2a)
		Double x1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
		Double x2 = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
		
		return x1.equals(x2) && (!x1.isNaN() && !x2.isNaN()) ? new Double[] {x1} : new Double[]{x1,x2};
	}
	
	public Double sqrt(double x) {
		return Math.sqrt(x);
	}
	
}


