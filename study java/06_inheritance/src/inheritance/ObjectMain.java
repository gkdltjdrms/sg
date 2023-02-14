package inheritance;

class Test extends Object {
	
}

//------------
class Sample{
	@Override
	public String toString() {
		return getClass() + "@개바부";
	}
}
//------------
class Exam {
	private String name = "홍길동";
	private int age = 25;
	@Override
	public String toString() {
		return name+"\t"+age;
	}
}

public class ObjectMain {

	public static void main(String[] args) {
		Test t = new Test();
		System.out.println("객체 t = " + t);//클래스명@16진수 inheritance.Test@7e774085
		System.out.println("객체 t = " + t.toString());// ;;
		System.out.println("객체 t = " + t.hashCode());
		System.out.println();
		
		Sample s = new Sample();
		System.out.println("객체 s = " + s.toString());//inheritance.Sample@726f3b58
		System.out.println();
		
		Exam e = new Exam();
		System.out.println("객체 e = " + e.toString());//inheritance.Sample@726f3b58
		System.out.println();
		
		String aa = "apple";
		System.out.println("객체 aa = " + aa.toString());//문자열
		System.out.println("객체 aa = " + aa.toString());
		System.out.println("객체 aa = " + aa.hashCode());
		System.out.println();
		
		//문자열의 hashcode 는 10진수로 표기가 되는데 문자열은 무한대로 표기가 되므로 10진수 표기 x
		
		String bb = new String("apple");
		String cc = new String("apple");
		System.out.println("bb==cc : "+ (bb==cc));//주소 false
		System.out.println("bb.equals(cc) : "+ bb.equals(cc));//문자열 true
		System.out.println();
		
		Object dd = new Object();
		Object ee = new Object();
		System.out.println("dd==ee : "+ (dd==ee)); //주소 false
		System.out.println("bb.equals(cc) : "+ dd.equals(ee)); //false (object의 equals는 참조(주소)값을 비교를 한다
		System.out.println();
		
		Object ff = new String("apple");
		Object gg = new String("apple");
		System.out.println("dd==ee : "+ (ff==gg)); //주소 false
		System.out.println("bb.equals(cc) : "+ ff.equals(gg)); //문자열 true
		//object로 지정해도 override 가 되었을때 자식클래스를 먼저 처리한다.
		System.out.println();
		
	}

}

/*
class Object {
   public String toString() {}         클래스명@16진수
   public int hashCode() {}         10진수
   public boolean equals(Object ob){}   참조값 비교
}

class String
   public String toString() {}         문자열
   public int hashCode() {}         10진수 (믿으면 안된다)
  -> 표기할 수 있는 문자열은 무한대이기 때문에 10진수로는 다 표기할 수 없다.
   public boolean equals(Object ob){}   문자열 비교
}
 */ 

 
