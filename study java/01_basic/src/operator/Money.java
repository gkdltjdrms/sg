package operator;

import java.text.DecimalFormat;
import java.util.Scanner;
public class Money {

	public static void main(String[] args) {
		int money = 5378;
		
		Scanner scan = new Scanner(System.in); //키보드로부터 입력받는 Scanner 클래스를 생성
		System.out.print("돈 입력 : ");
		money = scan.nextInt();
		
		int thousands = money/1000;
		int hundreds = (money%1000)/100;
		int tens = (money%100)/10;
		int ones = (money%10)%10;
		
		DecimalFormat df = new DecimalFormat();
		
		System.out.println("현금\t:" +df.format(money)+"원"); //3자리마다 ,찍기
		System.out.println("천원\t:" + thousands+"장");
		System.out.println("백원\t:" + hundreds+"개");
		System.out.println("십원\t:" + tens+"개");
		System.out.println("1원\t:" + ones+"개");
		

	}

}
	
/*
		int money = 5378;
		System.out.println("현금: " + money+"원");
		int thousands = money / 1000;
		money %= 1000;
		
		int hundreds = money / 100;
		money %= 100;
		
		int tens = money / 10;
		int ones = money % 10;
	
		System.out.println("천원: " + thousands+"장");
		System.out.println("백원: " + hundreds+"개");
		System.out.println("십원: " + tens+"개");
		System.out.println("1원: " + ones+"개");
*/
/*
[문제] 동전교환기 - 현금 5378원이 있습니다. 

[실행결과]
현금	: 5378원
천원	: 5장
백원	: 3개
십원	: 7개
1원	: 8개
*/