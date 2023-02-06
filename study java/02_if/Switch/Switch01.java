package Switch_;

import java.util.Scanner;

public class Switch01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int days;
		System.out.print("월 입력: ");
		int month = scan.nextInt();
		
		switch(month) {
		case 1 : 
		case 3 : 
		case 5 : 
		case 7 : 
		case 8 : 
		case 10 : 
		case 12 : days=31;break; // switch 를 벗어나ㅏ
		
		
		case 2 : days=28; break ;
			
		case 4 : 
		case 6 : 
		case 9 : 
		case 11 : days=30;break;
		
		
		
		
		
		default : days=0;
		}
		
		System.out.println(month + "월은" + days + "일 입니다");
		
		
		
		
	}

}
