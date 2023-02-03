package basic;

public class Variable01 {

	public static void main(String[] args) {
		System.out.println(Integer.MIN_VALUE + "," + Integer.MAX_VALUE);
		System.out.println(Long.MIN_VALUE + "," + Long.MAX_VALUE);
		System.out.println();
		
		boolean a;
		a = 25 > 36;
		
		System.out.println("a = " + a);
		
		char b;
		b = 'A'; //65, 0100 0001
		System.out.println("b = " + b);
		
		char c;
		c = 65;
		System.out.println("c = " + c);
		
		byte d=0; //1byte, 8bit, -128 ~ +127
		//d = 128; - error
		System.out.println("d = " + d);
		
		int e;
		e = 65; //0100 0001
		System.out.println("e = " + e); //65
		
		int f;
		f = 'A';
		System.out.println("f = " + f); //65
		
		long g;
		g = 25L; //25L은 long형 상숭
		System.out.println("g = " + g);
		
		float h;
//		h = 43.8; //43.8은 double형 상수 , error type 이 double
//		h = (float)43.8; //강제형변환
		h = 43.8F; // 43.8F은 float형 상수

	}

}
