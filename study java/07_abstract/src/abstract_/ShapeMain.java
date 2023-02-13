package abstract_;

import java.util.Scanner;

abstract class Shape{
	protected double area;
	protected Scanner scan = new Scanner(System.in);
	
	public Shape() {
		System.out.println("Shape 기본생성자");
	}
	
	public abstract void calcArea(); //추상 메소드
	public abstract void disArea();	 //추상 메소드
	


//삼각형class
class Sam extends Shape{
	protected int base, height;
	
	public Sam() {
		System.out.println("Sam 기본 생성자");
		System.out.print("밑변 : ");
		base = scan.nextInt();
		System.out.print("높이 : ");
		height = scan.nextInt();
	}
	//어노테이션(@)
	@Override
	public void calcArea() {
		area = base * height / 2.0;
	}
	
	@Override
	public void disArea() {
		System.out.println("삼각형 넓이 = "+area);
	}
}


//사각형 class
class Sa extends Shape{
	protected int base, height;
	
	public Sa() {
		System.out.println("Sa 기본 생성자");
		System.out.print("가로 : ");
		base = scan.nextInt();
		System.out.print("세로 : ");
		height = scan.nextInt();
	}
	//어노테이션(@)
	@Override
	public void calcArea() {
		area = base * height; 
	}
	
	@Override
	public void disArea() {
		System.out.println("사각형 넓이 = "+area);
	}
}


//사다리꼴 class
class Sadari extends Shape{
	protected int top, height, bottom;
	
	public Sadari() {
		System.out.println("Sadari 기본 생성자");
		System.out.print("윗변 : ");
		top = scan.nextInt();
		System.out.print("밑변 : ");
		bottom = scan.nextInt();
		System.out.print("높이 : ");
		height = scan.nextInt();
	}
	//어노테이션(@)
	@Override
	public void calcArea() {
		area = (top+bottom)*height; 
	}
	
	@Override
	public void disArea() {
		System.out.println("사다리꼴 넓이 = "+area);
	}
}


public class ShapeMain {

	public static void main(String[] args) {
//		Sam sam = new Sam();
//		sam.calcArea();
//		sam.disArea();
//		System.out.println();
//		Sa sa = new Sa();
//		sa.calcArea();
//		sa.disArea();
//		System.out.println();
//		Sadari sadari = new Sadari();
//		sadari.calcArea();
//		sadari.disArea();
		
		
		//다형성
		//부모클래스가 모든 자식클래스를 참조할수 있다
		Shape shape;
		shape = new Sam();
		shape.calcArea();
		shape.disArea();
		System.out.println();
		
		shape = new Sa();
		shape.calcArea();
		shape.disArea();
		System.out.println();
		
		shape = new Sadari();
		shape.calcArea();
		shape.disArea();
		System.out.println();
		
		

	}

}
}