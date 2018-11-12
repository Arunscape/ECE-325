import java.math.BigInteger;
import java.util.Date;

public class Main {
	
	public BigInteger fnv(byte[] data, int length) {
		BigInteger h = new BigInteger("14695981039346656037");
		
		for (int i=0; i< length; i++) {
			h = (new BigInteger("1099511628211")).multiply(h).xor(data[i]);
		}
		return h;
	}

	public static void main(String[] args) {
		HwEngineer h = new HwEngineer("Bob", 3000);
		ProjManager p = new ProjManager("Rob", 6000, "Shitty Project", new Date());
		
		System.out.println(String.format("hwEng raised salary: %f\nprojMan raised salary: %f", 
				h.getBaseSalary()+h.RaiseSalary(), p.getBaseSalary()+p.RaiseSalary()));
		
		
		System.out.println(); System.out.println();
		Customer c = new Customer("Cus", 9001);
		System.out.println(c.PrintInfo());
		System.out.println(p.PrintInfo());
		
		
		System.out.println(); System.out.println();
		System.out.println();
	}

}
