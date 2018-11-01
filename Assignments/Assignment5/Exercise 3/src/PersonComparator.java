
public class PersonComparator implements java.util.Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		return Integer.compare(p1.getAge(), p2.getAge());
	}
	
//	// probably not necessary
//	public boolean equals(Person p1, Person p2) {
//		return compare(p1,p2) == 0 ? true : false;
//	}
}
