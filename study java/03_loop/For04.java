package for_;

import java.util.Scanner;

public class For04 {

	public static void main(String[] args) {
		int x, y, mul=1; 
		Scanner scan = new Scanner(System.in); //키보드로부터 입력받는 Scanner 클래스를 생성
		System.out.print("x값 입력 : ");
		x = scan.nextInt();
		System.out.print("y값 입력 : ");
		y = scan.nextInt();
		
		
		for(int i=1; i<=y; i++) {		
			mul *= x;
			
		
		}System.out.println(x+ "의" +y + "승은" + mul);
		
		
		

	}

}

/*
[문제] 제곱 계산 -x의 y승
x값 입력 : 2
y값 입력 : 5

2의 5승은 32
*/

