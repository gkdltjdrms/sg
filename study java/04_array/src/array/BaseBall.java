package array;

import java.util.Scanner;

public class BaseBall {

	public static void main(String[] args) {
	      int[] com = new int[3];
	      int[] user = new int[3];
	      int strike = 0; 
	      int ball = 0; 
	      String yn;
	         Scanner sc = new Scanner(System.in);
	         do {
	        	 System.out.print("게임을 실행하시겠습니까(Y/N) : ");
		         yn = sc.next();
	         }while(!yn.equals("y") && !yn.equals("Y") && !yn.equals("N") && !yn.endsWith("n"));
	         
	         if(yn.equals("Y") || yn.endsWith("y")) {
	        	 System.out.println("게임을 시작합니다");
	        	 
	        	 //컴퓨터가 난수 발생
	        	 for(int i=0; i<com.length; i++) {
	        		 com[i] = (int)(Math.random() * 9 + 1);
	        		 //중복제거
	     			for(int j=0; j<i; j++) {
	     				 if(com[i] == com[j]) {i--; break;} //	  				 
	     			}// for j
	     		}	// for i	
	        	System.out.println(com[0]+","+com[1]+","+com[2]);
	        	
	        	//사용자 숫자 입력
	        	 while(true) {
	        		 System.out.println();
	        		 System.out.println("숫자 입력 :");
	        		 String num = sc.next();
	        		 
	        		 user[0] = num.charAt(0)-'0';  //num/100;
	        		 user[1] = num.charAt(1)-'0';  //(num%100)/10;
	        		 user[2] = num.charAt(2)-48;  //(num%100)%10;
	        		 System.out.println(user[0]+","+user[1]+","+user[2]);
	        		 
	        		 //비교
	        		 strike = ball = 0;
	        		 for(int i=0; i<com.length; i++) {
	        			 for(int j=0; j<user.length; j++) {
	        				 
	        				 if(com[1] == user[j]) {
	        					 if(i == j) strike++;
	        					 else ball++;
	        					 
	        				 }// if 
	        			 }//for j
	        		 }//for i
	        		 
	        		 System.out.println(strike + " 스트라이크\t" + ball + "볼");
	        		 
	        		 if(strike == 3) {
	        			 System.out.println("정답!");
	        			 break;
	        			 
	        		 }
	        	 }//while
	        	 
	         }else
	        	 System.out.println("프로그램을 종료합니다");
	        	 //if end
	         
	         
	         
	        
	   }

}




/*
[문제] 야구게임
크기가 3개인 정수형 배열을 잡고 1~9사이의 난수를 발생한다
발생한 수를 맞추는 게임
중복은 제거한다

[실행결과]
게임을 실행하시겠습니까(Y/N) : w
게임을 실행하시겠습니까(Y/N) : u
게임을 실행하시겠습니까(Y/N) : y

게임을 시작합니다

숫자입력 : 123
0스트라이크 0볼

숫자입력 : 567
0스트라이크 2볼

숫자입력 : 758  int[] com = new int[3];
		int[] user = new int[3];
1스트라이크 2볼
...

숫자입력 : 785
3스트라이크 0볼

프로그램을 종료합니다.
*/
