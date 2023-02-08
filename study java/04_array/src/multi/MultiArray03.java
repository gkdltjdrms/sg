package multi;

import java.text.DecimalFormat;


public class MultiArray03 {

	  public static void main(String[] args) {
	      String[] names = {"홍길동", "프로도", "죠르디"};
	      int[][] scores = {{90,95,100},{100,89,75},{75,80,48}};
	      
	      System.out.println("이름\t국어\t영어\t수학\t총점\t평균\t학점");
	      System.out.println("----------------------------------------------------");
	      
	      DecimalFormat df = new DecimalFormat("#.##");
	      
	      for (int i = 0; i < scores.length; i++) {
	         int total = 0;
	         for (int j = 0; j < scores[i].length; j++) {
	            total += scores[i][j];
	         }
	         double average = (double) total / scores[i].length;
	         String grade;
	         if (average >= 90) {
	            grade = "A";
	         } else if (average >= 80) {
	            grade = "B";
	         } else if (average >= 70) {
	            grade = "C";
	         } else if (average >= 60) {
	            grade = "D";
	         } else {
	            grade = "F";
	         }
	         System.out.println(names[i] + "\t" + scores[i][0] + "\t" + scores[i][1] + "\t" + scores[i][2] + "\t" + total + "\t" + df.format(average) + "\t" + grade);
	      }
	   }
	}


/*
[문제] 성적 처리
- 총점, 평균, 학점을 구하시오
- 평균은 소수이하 2째자리까지 출력
총점 = 국어+영어+수학
평균 = 총점 / 과목수
학점은	평균이 90 이상이면 'A'
		평균이 80 이상이면 'B'
		평균이 70 이상이면 'C'
		평균이 60 이상이면 'D'
		그외는 'F'
[실행 결과]
----------------------------------------------------
이름      국어      영어      수학      총점      평균      학점
----------------------------------------------------
홍길동     90       95      100
프로도     100      89      75
죠르디     75       80      48
----------------------------------------------------
*/