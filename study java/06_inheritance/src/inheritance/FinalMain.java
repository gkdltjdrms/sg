package inheritance;

enum Color {
	RED, GREEN, BLUE
}

class Final {
	public final String FRUIT = "사과";
	public final String FRUIT2;
	
	// static 인 경우에 생성자에서 초기화 안된다.
	public static final String ANIMAL = "기린";
	public static final String ANIMAL2;
	
//	public static final int RED = 0;
//	public static final int GREEN = 1;
//	public static final int BLUE = 2;
	
	
	static {
		System.out.println("static 초기화 영역");
		ANIMAL2= "코끼리";
	}
	
	
	
	public Final() {
		System.out.println("기본 생성자");
		FRUIT2 = "딸기";
	}
}

//----------------------

public class FinalMain {

	public static void main(String[] args) {
		final int A = 10;//상수화
		//A = 20; -error final은 값을 변경할수 없다.
		System.out.println("a = " + A);
		
		final int B;
		B = 30;
		//B = 40;
		System.out.println("b =" + B);
		
		Final f = new Final();
		System.out.println("FRUIT = " + f.FRUIT);
		System.out.println("FRUIT = " + f.FRUIT2);
		
		System.out.println("ANIMAL = " + Final.ANIMAL);
		System.out.println("ANIMAL = " + Final.ANIMAL2);
		System.out.println();
		
		System.out.println("빨강 = " + Color.RED);
		System.out.println("빨강 = " + Color.RED.ordinal());
		System.out.println();
		
		for(Color data : Color.values()) {
			System.out.println(data+"\t" + data.ordinal());
		}
		
	}

}
