package class__;

import java.util.Scanner;

public class ExamMain {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("인원수 입력 : ");
		int cnt = sc.nextInt();
		
		//객체 배열
		Exam[] ar = new Exam[cnt];
		
		for(int k=0; k<cnt; k++) {
			ar[k] = new Exam();
			ar[k].compare();
			System.out.println();
		}//for k
		
		//출력
		System.out.println("이름\t 1 2 3 4 5 \t점수 ");
		for(Exam e : ar ) {
			
			System.out.print(e.getName()+"\t");
			for(int i=0; i<e.getOx().length; i++) {
				System.out.print(e.getOx()[i]+" ");
				}//for i
			System.out.println("\t"+ e.getScore());
		}
	}

}


/*
[문제] 사지선다형
- 총 5문제의 답을 입력받는다
- 정답은 "11111" 이다
- 맞으면 'O', 틀리면 'X'
- 1문제당 점수는 20점씩 처리

클래스명 : Exam
* 필드
private String name = null;
private String dap = null;
private char[] ox = null;
private int score = 0;
private final String JUNG = "11111"; //상수화

* 메소드
생성자 - Scanner 를 이용하여 이름과 답을 입력받는다. 
compare() - 비교
getName()
getOx()
getScore()

클래스명 : ExamMain

[실행결과]
인원수 입력 : 2

이름 입력 : 홍길동
답 입력 : 12311  

이름 입력 : 코난
답 입력 : 24331

이름      1 2 3 4 5   점수
홍길동     O X X O O   60
코난     X X X X O   20
*/