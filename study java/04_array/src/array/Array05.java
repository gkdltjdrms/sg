package array;

public class Array05 {

	public static void main(String[] args) { 
		char[] ar = new char[50];  // 50개의 문자를 저장할 배열 생성
		int[] count = new int[26];  // 26개의 알파벳의 개수를 저장할 배열 생성
		int eng;
		for(int i=0; i<ar.length; i++) {  // 50번 반복
			eng = (int)(Math.random()*26)+65; // 65에서 90사이의 난수 생성(y-x+1) * x
		    ar[i] = (char)eng; // 랜덤한 문자 저장
		    count[eng-'A']++; // 해당 알파벳의 개수 증가 아스키코드를 빼거나 'A'를 빼준다.
		    System.out.print(ar[i]+" "); // 배열에 저장된 문자 출력
		    if ((i+1) % 10 == 0) {  // 10개씩 출력하기 위한 조건
		        System.out.println(); //10개 출력 후 줄바꿈
		    }
		}
		System.out.println();  // 알파벳 개수 출력 전 줄바꿈
		for (int i=0; i<count.length; i++) { // 26개의 알파벳 개수 출력 count.length 로 대신할수 있음
		    System.out.print((char)(i+'A') + " : " + count[i]+"개"+" ");// 알파벳 개수 출력
		    if ((i+1) % 4 == 0) {  // 10개씩 출력하기 위한 조건
		        System.out.println(); //10개 출력 후 줄바꿈
		    }
		}
	}
}
/*
[문제] 크기가 50개인 문자배열을 잡아서 65~90사이의 난수를 발생하여 저장 후 출력하시오
- 1줄에 10개씩 출력
- A의 개수? B의 개수? C의 개수? ~~~ Z의 개수 ?

[실행결과]
D F A G H I J K L T
O P W E R F V A S B
P Y R O L E E Z L I
F E U Z T U P P P A
S P G B F F O W J C

A : 2
B : 3
...
X : 0
Y : 1
Z : 0
*/