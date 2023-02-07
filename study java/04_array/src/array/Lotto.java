package array;

import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {
		int[] lotto = new int[6];
		int random,temp1;
		Scanner sc = new Scanner(System.in);
	      System.out.print("현금 입력 : ");
	      int cash= sc.nextInt();
	      int lines = cash / 1000;
	      
		for(int a=1; a <=lines; a++) {
		for(int i=0; i<lotto.length; i++) {
		random = (int)(Math.random() * 45 + 1); //		
//		System.out.println(random);
		lotto[i] = random; // 중복체크
			for(int j=0; j<i; j++) {
				 if(lotto[i] == lotto[j]) {i--; break;} //i를 빼주지 않으면 다음위치부터 난수 생성					  					  				 
			}// for j
			
		}	// for i		
		
		//난수 발생, 중복x
		
				//오름차순
		int temp;
		for (int i = 0; i < lotto.length-1; i++) {
			  for (int j = i+1; j < lotto.length; j++) {
				  if(lotto[j] < lotto[i]) { 
					  temp = lotto[i];
					  lotto[i] = lotto[j];
					  lotto[j] = temp;
					  
				  }//if end
			   
				  
			  }//for j end
			}//for i end
		for (int data : lotto) {
			System.out.print(String.format("%5d", data));			
		}	
		System.out.println();
		if (a % 5 == 0)
			System.out.println();
		}
	}

}

/*
[문제] 로또 -자동
- 크기가 6개인 배열 생성
- 1 ~ 45 사이의 난수 발생
- 숫자는 오름차순하여 출력 (Selection sort)
- 출력시 자리수는 5자리로 지정
- 중복 숫자가 나오면 안된다
- 1000원당 1줄이 나온다
- 5줄마다 줄바꿈
[실행결과]
현금 입력 : 7000
	2	4	19	39	43	44
	2	4	19	39	43	44
	2	4	19	39	43	44
	2	4	19	39	43	44
	2	4	19	39	43	44
	
	2	4	19	39	43	44
	2	4	19	39	43	44
*/