.
package if_;

import java.util.Scanner;

public class if01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("숫자 입력 : ");
		int a = scan.nextInt();
		
		if(a >= 50) System.out.println(a + "는 50보다 크거나 같다");
		
		System.out.println(a+ "는 50보다 작다");
		System.out.println();
		//c
		if(false)
			if(true) System.out.println("A");
			else System.out.println("B");
		System.out.println("C");
		System.out.println();
		
		//a -> c
		if(true)
			if(true) System.out.println("A");
			else System.out.println("B");
		System.out.println("C");
		System.out.println();
		
		if(true)
			if(false) System.out.println("A");
			else System.out.println("B");
		System.out.println("C");
		System.out.println();
		
//		if(a가 대문자입니까  'A'65 'B' 'C' ..'Z'90)
		if(a>='A' && a<='Z') //65 ~ 90
			System.out.println((char)a + "는 대문자");
		else if(a>='a' && a<='z')//97 ~ 122
			System.out.println((char)a + "는 소문자");
		else
			System.out.println((char)a + "는 숫자이거나 특수문자");
		
	}

}
//
//
