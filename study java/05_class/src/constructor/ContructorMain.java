package constructor;

public class ContructorMain { //this 자기자신 클래스의 정보 
		//this() overload된 다른 생성자를 호출, 반드시 첫번째 줄에서만 적용
	private String name;
	private int age;
	
	public  ContructorMain() {
		System.out.println("default 생성자");
	}
	public ContructorMain(String name) {
		this();
		System.out.println("name 처리 생성자");
		this.name = name;
	}
	public ContructorMain(int age) {
		this("코난"); //Overload된 다른 생성자를 호출할 수 있다.
		System.out.println("age 처리 생성자");
		this.age = age;
		
	}
	
	public static void main(String[] args) {
		ContructorMain aa = new ContructorMain();
		System.out.println(aa.name + "\t" + aa.age);
		System.out.println();
		
		ContructorMain bb = new ContructorMain("홍길동");
		System.out.println(bb.name + "\t" +bb.age);
		System.out.println();
		
		ContructorMain cc = new ContructorMain(25);
		System.out.println(cc.name + "\t" +cc.age);
		System.out.println();
	}

}
