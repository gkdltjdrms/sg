package array;

import java.util.Scanner;

public class BaseBall {

	public static void main(String[] args) {
		
		int strike=0,ball=0;
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < com.length; i++) {
	            int random = (int)(Math.random() * 9 + 1);
	            com[i] = random;

	            
	            for (int j = 0; j < i; j++) {  
	                if (com[j] == com[i]) {
	                    i--;
	                    break;
	                    
	                }//for if end
	            } // for j end
	        }//for i end
		 System.out.print("게임을 실행하시겠습니까(Y/N) : ");
	        String answer = scan.nextLine();
		 	if(answer.equals("Y") || answer.equals("y")) {        
		 		System.out.println("게임을 시작합니다");
		 		
		 		
		 	}else if(answer.equals("n") || answer.equals("N")) {
		 		System.out.println("프로그램을 종료합니다");
		 	}
		
	        
	        
//		 for (int data : com) {
//				System.out.print(String.format("%5d", data));			
//			}//for end	
		
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
