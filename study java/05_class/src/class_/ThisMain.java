package class_;

class This {
	private int b;
	private static int c; //static은 식당에 시음식과 같다.
	
	
	public void setB(int b) { //구현, 인수(argument), 매개변수(parameter)
		System.out.println("this =" + this);
		this.b = b;
	}
	public void setC(int c) { //
		this.c = c;
	}
	
	public int getB(){
		return this.b;
	}	
	public int getC(){
		return c;
	}
	
}

public class ThisMain {
	private int a; // 필드
	
	public static void main(String[] args) {
		ThisMain tm = new ThisMain();
		tm.a = 10;
		System.out.println("a = "+tm.a);
		
		This t = new This();
		System.out.println("객체 t =" + t);
		t.setB(20);		//호출 -  호출한 메소드는 반드시 돌아온다.
		t.setC(30);  //의문점 set b랑 다를게 없는데 static의 차이첨을 잘 모르겟음
		System.out.println("t.b = "+ t.getB());
		System.out.println("t.c = " +t.getC());
		
		This w = new This();
		System.out.println("객체 w =" + w);
		w.setB(40);;
		w.setC(50);
		System.out.println("w.b = "+ w.getB());
		System.out.println("w.c = " +w.getC());
	}

}

//모든 클래스는 반드시 생성해야 한다.
