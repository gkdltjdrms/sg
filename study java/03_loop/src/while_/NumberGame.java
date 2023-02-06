package while_;

import java.util.Scanner;

public class NumberGame {

	public static void main(String[] args) {
	    int com, user=0;
	    int count = 0;
	    Scanner scan = new Scanner(System.in);
	    while (true) {
	      com = (int)(Math.random() * 100 + 1);
	      System.out.println("1 ~ 100사이의 숫자를 맞추세요 : ");
	      user = 0;
	      count = 0;
	      while (true) {
	        System.out.print("숫자 입력 : ");
	        user = scan.nextInt();
	        count++;
	        if (user == com) {
	          break;
	        } else if (user < com) {
	          System.out.println(user + "보다 큰 숫자 입니다.");
	        } else if (user > com) {
	          System.out.println(user + "보다 작은 숫자 입니다.");
	        }
	      }
	      System.out.println("정답입니다! " + count + "번 만에 맞추셨습니다.");
	      System.out.println("한번 더 (y/n) :");
	      String yn = scan.next();
	      if (!yn.equals("y")) {
	        break;
	      }
	    }
	    System.out.println("게임 종료");
	  }
	}




/*
[문제] 숫자 맞추기 게임
- 컴퓨터가 1 ~ 100 사이의 난수를 발생하면, 발생한 난수를 맞추는 게임
- 몇 번만에 맞추었는지 출력한다.

[실행결과]
1~ 100 사이의 숫자를 맞추세요 (70)

숫자 입력 : 50
50보다 큰 숫자 입니다.

숫자 입력 : 85
85보다 작은 숫자 입니다.

~~~

*/