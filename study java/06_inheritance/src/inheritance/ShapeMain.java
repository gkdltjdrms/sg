package inheritance;

import java.util.Scanner;

class Shape{
	protected double area;
	protected Scanner scan = new Scanner(System.in);
	
	public Shape() {
		System.out.println("Shape 기본 생성자");
	}
	
	public void calcArea() {
		System.out.println("도형을 계산합니다");
	}
	public void dispArea() {
		System.out.println("도형을 출력합니다");
	}
	
}
//-----------------
class Sam extends Shape{
	protected int base, height;
	
	public Sam() {
		System.out.println("Sam 기본 생성자");
		System.out.println("밑변 : ");
		base = scan.nextInt();
		System.out.println("높이 : ");
		height = scan.nextInt();
	}
	@Override
	public void calcArea() {
		area = base* height / 2.0;
	}
	@Override
	public void dispArea() {
		System.out.println("삼각형 넓이 = "+ area);
		
	}
	//------------------------
//	class Sa extends Shape() {
//		protected int width, height;
//		System.out.println("Sa 기본 생성자");
//		System.out.println("가로 : ");
//		width = scan.nextInt();
//		System.out.println("세로 : ");
//		height = scan.nextInt();
//	}
//	//--------------------
//	class Sadari extends Shape() {
//		System.out.println("윗변 : ");
//		base = scan.nextInt();
//		System.out.println("밑변 : ");
//		height = scan.nextInt();
//		System.out.println("높이 : ");
//		height = scan.nextInt();
//		
//	}
	
	
}
//----------------
public class ShapeMain {

	public static void main(String[] args) {
		Sam sam = new Sam();
		sam.calcArea();
		sam.dispArea();
		

		
		
		
		
		
		
		
		//다형성 => 부모 = 자식
		Shape shape; 
		shape = new Sam();
		shape.calcArea();
		shape.dispArea();
		System.out.println();
		
//		Shape = new Sa(); 
//		shape.calcArea();
//		shape.dispArea();
//		System.out.println();
//		
//		Shape = new Sadari(); 
//		shape.calcArea();
//		shape.dispArea();
//		System.out.println();
	}

}
