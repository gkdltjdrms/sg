package abstract_;

import java.util.Calendar;
import java.util.Scanner;



public class CalendarEx {
	Scanner sc = new Scanner(System.in);
	 	// 윤년을 제외한 각 달의 최대 일수
		private static final int[] MAX_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		// 윤년인 경우 각 달의 최대 일수
		private static final int[] LEAP_MAX_DAYS = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	    private int year;
	    private int month;
	    
	    // 생성자, 년도와 월을 입력받아 객체를 생성
		public CalendarEx(){
			System.out.print("년도 입력 : ");
			this.year = sc.nextInt();
			System.out.print("월 입력 : ");
			this.month = sc.nextInt();
		}
	    
	    
	    // 현재 월의 최대 일수를 구하는 메소드
	    public int calc() {
	    	// 윤년 판단
	    	if((year % 4 == 0 && year % 100 != 0) || (year % 400 ==0))
	            return LEAP_MAX_DAYS[month-1];
	        return MAX_DAYS[month-1];
	    }

	    public void display() {
	        System.out.println("    " + year + "년 " + month + "월");  // 년도와 월 출력
	        System.out.println(" 일   월   화   수   목   금   토");
	        System.out.println("===========================");
	        
	        // Calendar 객체 생성
	        Calendar cal = Calendar.getInstance();
	        cal.set(year, month-1, 1); // 현재 년도와 월, 일 설정
	        
	        // 1월 1일의 요일 구하기
	        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        int maxDay = calc(); // maxDay 변수에 calc 메소드의 결과 값을 대입

	        for (int i = 1; i < dayOfWeek; i++) { // dayOfWeek 변수부터 1씩 증가하며 반복
	            System.out.print("    "); // 공백 4칸 출력
	        }

	        for (int i = 1; i <= maxDay; i++) { // maxDay 까지 1씩 증가하며 반복
	            System.out.printf("%4d", i); // i 를 4칸의 크기로 출력
	            if ((i + dayOfWeek - 1) % 7 == 0) { // i와 dayOfWeek를 합산한 값이 7의 배수일 때
	                System.out.println(); 
	            }
	        }
	        System.out.println(); 
	    }
	}

	