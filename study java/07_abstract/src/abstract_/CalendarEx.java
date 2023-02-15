package abstract_;

import java.util.Calendar;
import java.util.Scanner;



public class CalendarEx {
	 Scanner sc = new Scanner(System.in);
	   private int year;
	   private int month;
	   private int end;
	   private int dayOfWeek;
	   
	   public CalendarEx(){
	      System.out.print("년도 입력 : ");
	      this.year = sc.nextInt();
	      System.out.print("월 입력 : ");
	      this.month = sc.nextInt();
	   }
	   public void calc() {
	      Calendar cal = Calendar.getInstance();
	      cal.set(Calendar.YEAR, this.year); //입력받은 년도로 세팅
	      cal.set(Calendar.MONTH, this.month); //입력받은 월로 세팅
	      cal.set(year,month-1,1); //입력받은 월의 1일로 세팅
	                           //month는 0이 1월이므로 -1을 해준다
	      this.end = cal.getActualMaximum(Calendar.DATE); //해당 월 마지막 날짜
	      this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //해당 날짜의 요일(1:일요일 … 7:토요일)
	   }
	   
	   public void display() {
		   System.out.println("                  " + year + "년 " + month + "월");  // 년도와 월 출력
	        System.out.println(" 일\t월\t화\t수\t목\t금\t토");
	        System.out.println("======================================================");
	      
	      for(int i=1; i<=end; i++) {
	         if(i==1) {
	            for(int j=1; j<dayOfWeek; j++) {
	               System.out.print("\t");
	            }
	         }
	         if(i<10) { //한자릿수일 경우 공백을 추가해서 줄맞추기
	            System.out.print(" ");
	         }
	         System.out.printf(i+"\t");
	         if(dayOfWeek%7==0) { //한줄에 7일씩 출력
	            System.out.println();
	         }
	         dayOfWeek++;
	      }
	   }
	   
	}