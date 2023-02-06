package for_;

import java.util.Scanner;

public class For06 {

	public static void main(String[] args) {
		int x, y=1; 
		Scanner scan = new Scanner(System.in); //키보드로부터 입력받는 Scanner 클래스를 생성
		System.out.print("숫자 입력 : ");
		x = scan.nextInt();
		
		
		
		for(int i=1; i<=x; i++) {		
			y*= i;
			
		
		}System.out.print(x + "! = " + y + "(");
		for (int i = 1; i <= x; i++) {
		    y *= i;
		    System.out.print(i);
		    if (i < x) {
		        System.out.print(" * ");
		    }
		}
		System.out.println(")");
		
		

	}

}



/*
[문제] 팩토리얼을 구하시오 (for)
- 입력되는 숫자는 1 ~ 10 사이만 입력한다.

[실행결과]
숫자 입력 : 3
3! = 6(1*2*3)
----------------

숫자 입력 : 5
5! = 120(1*2*3*4*5)


*/