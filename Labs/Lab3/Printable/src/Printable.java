import java.util.Date;
import java.util.Objects;

class Person {
	private String name;

	Person(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Person)) {
			return false;
		}
		Person p = (Person) o;
		return this.name.equals(p.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}
}

class Customer extends Person implements Printable {
	private double projPrice;

	Customer(String name, double projPrice) {
		super(name);
		this.projPrice = projPrice;
	}

	public double getProjPrice() {
		return this.projPrice;
	}

	@Override
	public String PrintInfo() {
		return String.format("Name: %s\nProject Price: %f", this.getName(), this.projPrice);
	}
}

class Employee extends Person {
	private double baseSalary;

	Employee(String name, double baseSalary) {
		super(name);
		this.baseSalary = baseSalary;
	}

	public double getBaseSalary() {
		return this.baseSalary;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Employee)) {
			return false;
		}
		Employee e = (Employee) o;
		return this.getName().equals(e.getName()) && 
				this.getBaseSalary() == e.getBaseSalary();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getBaseSalary());
	}
}

class HwEngineer extends Employee implements SalaryRaisable {
	HwEngineer(String name, double baseSalary) {
		super(name, baseSalary);
	}

	@Override
	public double RaiseSalary() {
		return this.getBaseSalary() * 0.18;
	}
}

class SwEngineer extends Employee {
	private String projName;

	SwEngineer(String name, double baseSalary, String projName) {
		super(name, baseSalary);
		this.projName = projName;
	}

	public String getProjName() {
		return this.projName;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof SwEngineer)) {
			return false;
		}
		SwEngineer s = (SwEngineer) o;
		return this.getName().equals(s.getName()) && 
				this.getBaseSalary() == s.getBaseSalary() &&
				this.projName.equals(s.projName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getBaseSalary(), this.projName);
	}

}

class ProjManager extends SwEngineer implements Printable, SalaryRaisable {
	private Date projDeadline;

	ProjManager(String name, double baseSalary, String projName, Date projDeadline) {
		super(name, baseSalary, projName);
		this.projDeadline = projDeadline;
	}

	public Date getProjDeadLine() {
		return this.projDeadline;
	}

	@Override
	public double RaiseSalary() {
		return this.getBaseSalary() * 0.24;
	}

	@Override
	public String PrintInfo() {
		return String.format("Name: %s\nProject Name: %s\nSalary: %f\nProject Deadline: %s", this.getName(),
				this.getProjName(), this.getBaseSalary() + this.RaiseSalary(), this.projDeadline.toString());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof ProjManager)) {
			return false;
		}
		ProjManager p = (ProjManager) o;
		return this.getName().equals(p.getName()) && 
				this.getBaseSalary() == p.getBaseSalary() &&
				this.getProjName().equals(p.getProjName()) && 
				this.projDeadline.equals(p.projDeadline);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getBaseSalary(), this.projName);
	}
}

interface SalaryRaisable {
	double RaiseSalary();
}

/**
 * Lab 3: Inheritance, Interfaces, Hash, Design Pattern and Big Number <br />
 * The {@code Printable} interface
 */
public interface Printable {
	/**
	 * @return {@code String} The printable information in the string type.
	 */
	String PrintInfo();
	// TODO: Lab 3 Part 1 -- compose classes according to the UML diagram
}
