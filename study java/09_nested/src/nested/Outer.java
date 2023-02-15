package nested;

public class Outer {
	private String name;
	
	public void output() {
		Inner in = new Inner();
		System.out.println("이름 = "+ name + "\t나이 = "+ in.age);//안쪽으로 객체를 생성해야 사용가능
	}
	
	class Inner {
		private int age;
		
		public void disp() {
			System.out.println("이름 = " + Outer.this.name + "\t" + this.age);
		}
		
		
	}

	public static void main(String[] args) {
		Outer ou = new Outer();
		ou.name = "홍길동";
		System.out.println("이름 = "+ ou.name);
		System.out.println();
		
		Outer.Inner in2 = ou.new Inner();
		in2.age = 25;
		in2.disp();
		System.out.println();
		
		Outer.Inner in3 = ou.new Inner();
		in3.age = 30;
		in3.disp();
		System.out.println();
		
		Outer.Inner in4 = new Outer().new Inner();
		in4.age = 32;
		//in4.name = "코난"; error
		in4.disp();
		System.out.println();
		
	}

}
