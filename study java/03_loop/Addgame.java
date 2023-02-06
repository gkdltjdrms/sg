package for_;

import java.util.Scanner;

public class AddGame {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a, b, count=0;
		for(int i=1; i<=5; i++) {
			a = (int)(Math.random() * 90 + 10); //!~90사이의 난수 
			b = (int)(Math.random() * 90 + 10);
		System.out.println("["+i+"] "+a+"+"+b+"=");
		System.out.print("정답 입력 : ");
		int ans = scan.nextInt();
		if(ans == (a+b)) {System.out.println("참 잘했어요");count++;
		}else System.out.println("틀렸습니다");
		}System.out.println("당신은 총"+count+"문제를 맞추어서"+" 점수"+count*20+"점입니다");
	}

}

/*
[문제] 덧셈 문제
- 2자리 숫자(10 ~99)만 제공한다

[실행결과]
87 + 56 = ?
*/
