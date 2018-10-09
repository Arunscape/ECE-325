/**
 * Assignment 4 Part 2: Unit Testing <br />
 * The calculator to run the test cases
 */
// TODO: Assignment 4 Part 2 -- Create the Calculator here

class Calculator{
	
//	public Number add(Number x, Number y) {
//		System.out.println(x.getClass().getName());
//		if (x instanceof Integer || y instanceof Integer) {
//			System.out.println(x.intValue()+y.intValue());
//			return x.intValue()+y.intValue();
//		}
//		else if (x instanceof Double || y instanceof Double) {
//			System.out.println(x.doubleValue()+y.doubleValue());
//			return x.doubleValue()+y.doubleValue();
//		}
//		else {
//			return Float.NaN;
//		}
//	}
	
	public static Double add(Double x, Double y) {
		return x+y;
	}
	public static int add(int x, int y) {
		return x+y;
	}
	
	public static Double subtract( Double x, Double y) {
		return x-y;
	}
		
	
	public Number multiply(Number x, Number y) {
		System.out.println(x.getClass().getName());
		if (x instanceof Integer || y instanceof Integer) {
			System.out.println(x.intValue()+y.intValue());
			return x.intValue()*y.intValue();
		}
		else if (x instanceof Double || y instanceof Double) {
			System.out.println(x.doubleValue()+y.doubleValue());
			return x.doubleValue()*y.doubleValue();
		}
		else {
			return Float.NaN;
		}
	}

	public Number divide(Number x, Number y) {
		System.out.println(x.getClass().getName());
		if (x instanceof Integer || y instanceof Integer) {
			System.out.println(x.intValue()+y.intValue());
			return x.intValue()/y.intValue();
		}
		else if (x instanceof Double || y instanceof Double) {
			System.out.println(x.doubleValue()+y.doubleValue());
			return x.doubleValue()/y.doubleValue();
		}
		else {
			return Float.NaN;
		}
	}
	
//	
//	public static void main(String[] args) {
//		System.out.println(add(1,-1));
//		;
//	}
}


