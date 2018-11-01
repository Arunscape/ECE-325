
class U implements X,G{
	
}
interface G{
	
}
class B implements X{
	
}
class Z extends U{
	
}
interface X{
	
}
public class testing {

	public static void main(String[] args) {
		U u = new U();
		G g = new U();
		B b = new B();
		Z z = new Z();
		X x = new B();
		
		// \begin{legal}
		u=z;
		x=b;
		g=u;
		x=u;
		// \end{legal}
		
		// \begin{illegal}
		u=b;
		x=g;
		b=u;
		z=u;
		g=x;
		// \end{illegal}
		
		
		System.out.println("hi");
		

	}

}


//class U extends X implements G{
//	
//}
//interface G{
//	
//}
//class B extends X{
//	
//}
//class Z extends U{
//	
//}
//class X{
//	
//}
//public class testing {
//
//	public static void main(String[] args) {
//		U u = new U();
//		G g = new U();
//		B b = new B();
//		Z z = new Z();
//		X x = new B();
//		
//		// \begin{legal}
//		u=z;
//		x=b;
//		g=u;
//		x=u;
//		// \end{legal}
//		
//		// \begin{illegal}
//		u=b;
//		x=g;
//		b=u;
//		z=u;
//		g=x;
//		// \end{illegal}
//		
//		
//		System.out.println("hi");
//		
//
//	}
//
//}

