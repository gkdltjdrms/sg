package multi;

import java.util.Scanner;
import java.text.DecimalFormat;

public class MultiArray05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[][] subject;
		String[] name;
		int[][] jumsu;
		int[] subjectCnt;
		//기본 배열 지정
		System.out.println("인원 수 : ");
		int cnt = scan.nextInt();
		//초기값
		name = new String[cnt];
		subjectCnt = new int[cnt];
		double[] avg = new double[cnt];
		subject = new String[cnt][];
		jumsu = new int[cnt][];
		
		System.out.println();
		//정보 입력
		for(int i = 0; i<cnt; i++) {
		System.out.println("이름 입력 : ");
		name[i] = scan.next();
		
		System.out.println("과목 수 입력 : ");
		subjectCnt[i] = scan.nextInt();
		subject[i] = new String[subjectCnt[i]]; //멀티배열을 지정하기 위해 사용
		jumsu[i] = new int[subjectCnt[i] + 1];	//멀티배열을 지정하기 위해 사용
		
		for(int j = 0; j<subjectCnt[i]; j++) {
		System.out.println("과목 명 입력 : ");
		subject[i][j] = scan.next();
			}//for i 
		
		
		for(int j = 0; j<subject[i][j].length(); j++) {
			System.out.println(subject[i][j]+"점수  입력 : ");
			jumsu[i][j] = scan.nextInt();
			jumsu[i][subjectCnt[i]] += jumsu[i][j];	//총점  //여기가 너무 헷갈림
		}//for j 
		//평균 계산
		avg[i] = jumsu[i][subjectCnt[i]]/subjectCnt[i];
	
		}//for i 1
		for(int i=0; i<cnt; i++) {
			for(int j=0; j<subjectCnt[i]; j++) {
				
				
				
			}//for j
			
			
		}//for i
		
		
		
		
	}//
}
/*
[문제]
인원수를 입력하여 인원수만큼 데이터를 입력받고 총점과 평균을 구하시오
평균은 소수이하 2째자리까지 출력

[실행결과]
인원수 : 2 (cnt)

이름입력 : 홍길동 (name)
과목수 입력 : 2   (subjectCnt)
과목명 입력 : 국어 (subject)
과목명 입력 : 영어
국어 점수 입력 : 95 (jumsu)
영어 점수 입력 : 100
---------------------
이름입력 : 이기자
과목수 입력 : 3
과목명 입력 : 국어
과목명 입력 : 영어
과목명 입력 : 과학
국어 점수 입력 : 95
영어 점수 입력 : 100
과학 점수 입력 : 90
---------------------

이름     국어     영어   총점     			평균
홍길동    95     100   xxx     		xx.xx

이름     국어    영어    과학    총점      평균
이기자   95   100   90    xxx      		xx.xx
*/