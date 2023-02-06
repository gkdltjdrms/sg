package operator;

import java.util.Scanner;

public class operator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("점수 입력 : ");
		int score = scan.nextInt();
		String result = score>=80 && score<=100 ? "합격" : "불합격";
		System.out.println(result);
		
		System.out.println((score>=80 && score<=100 ?  "합격" : "불합격"));

		
	}

}


//if(score >= 80) {
//System.out.println("합격");
//} else if(score >= 80) {
//System.out.println("불합격");
//} else if(score >= 70) {
//System.out.println("C");
//} else if(score >= 60) {
//System.out.println("D");
//} else {
//System.out.println("F");