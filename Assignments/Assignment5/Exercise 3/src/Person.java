
public class Person{
	
	private int age;

	Person(int a){
		this.age=a;
	}
	
	public int getAge() {
		return this.age;
	}
	
	@Override
	public String toString(){
		return String.format("Age: %d", this.age);
	}

}
