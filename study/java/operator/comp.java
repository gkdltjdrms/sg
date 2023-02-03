package basic;

class Test {
	int a = 10;
	static int b = 20;
	static String str;
}
//---------------------
public class Variable02 {
	int a;  //필드, 초기화 a = 0
	double b; //필드
	static int c;
	
	
	public static void main(String[] args) {
		int a=5; // 지역변수 local variable(a) garbage(쓰레기값)
		System.out.println("지역변수 a = "+ a );
		
		Variable02 v = new Variable02();//메모리 생성
		System.out.println("객체 =" + v);
		System.out.println("필드 a =" + v.a);
		System.out.println("필드 b =" + v.b);
		System.out.println("필드 c =" + v.c);
		System.out.println("필드 c =" + Variable02.c);
		
		//test
		Test t = new Test();
		System.out.println("필드 a =" + t.a);
		System.out.println("필드 b =" + Test.b);
		System.out.println("필드 str =" + Test.str);
		
		

	}

}
