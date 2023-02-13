package inheritance;

import java.util.Scanner;

public class Exam {
	private String name;
	private String dap;
	private char[] ox;
	private int score;
	private final String JUNG = "11111";
	
		
    // 생성자 정의
    public Exam(String name, String dap) {
    	 this.name = name; // 이름 설정
    	    this.dap = dap; // 정답 설정
    	    ox = new char[JUNG.length()]; // OX 결과 배열 생성
    	    compare(); // 비교 메소드 호출
    	}

    	// 비교 메소드 정의
    	public void compare() {
    	    // 반복문을 사용하여 5개 문제의 정답 비교
    	    for(int i=0; i<JUNG.length(); i++) {
    	        // 정답과 입력한 답이 일치하면
    	        if(dap.charAt(i) == JUNG.charAt(i)) {
    	            ox[i] = 'O'; // O 기록
    	            score += 20; // 점수 20 추가
    	        } else {
    	            ox[i] = 'X'; // X 기록
    	        }
    	    }
    	}

    	// 이름 반환 메소드 정의
    	public String getName() {
    	    return name;
    	}

    	// OX 결과 반환 메소드 정의
    	public char[] getOx() {
    	    return ox;
    	}

    	// 점수 반환 메소드 정의
    	public int getScore() {
    	    return score;
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