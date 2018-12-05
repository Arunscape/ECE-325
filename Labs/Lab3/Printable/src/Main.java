import java.math.BigInteger;
import java.util.Date;

public class Main {
	
	public static long fnv(String data) {
		
		
		BigInteger h = new BigInteger("14695981039346656037");
		
		for (int i=0; i< data.length(); i++) {
			h = (new BigInteger("1099511628211")).multiply(h).xor(BigInteger.valueOf(Character.getNumericValue(data.charAt(i))));
		}
		return h.longValue();
	}

	public static void main(String[] args) {
		HwEngineer h = new HwEngineer("Bob", 3000);
		ProjManager p = new ProjManager("Rob", 6000, "Shitty Project", new Date());
		
		System.out.println(String.format("hwEng raised salary: %f\nprojMan raised salary: %f", 
				h.RaiseSalary(), p.RaiseSalary()));

		
		System.out.println(String.format("hwEng raised salary: %f\nprojMan raised salary: %f", 
				h.getBaseSalary()+h.RaiseSalary(), p.getBaseSalary()+p.RaiseSalary()));
		System.out.println(String.format("hwEng raised salary: %f\nprojMan raised salary: %f", 
				h.getBaseSalary()+h.RaiseSalary(), p.getBaseSalary()+p.RaiseSalary()));
		
		System.out.println(); System.out.println();
		Customer c = new Customer("Cus", 9001);
		System.out.println(c.PrintInfo());
		System.out.println(p.PrintInfo());
		
		
		System.out.println(); System.out.println();
		System.out.println();
		
		System.out.println(fnv("heeeeyyy"));
		System.out.println(fnv("oooooooooohhh"));
		System.out.println(fnv("aaaahhhhh"));
		System.out.println(fnv("weeeeee"));
	}

}
