package abstract_;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Today {

	public static void main(String[] args) throws ParseException {
		Date date = new Date();//시스템의 시간과 날짜
		System.out.println("오늘 날짜 : " +date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("y년 MM월 dd일 E요일 hh:mm:ss");
		System.out.println("오늘 날짜 : " +sdf.format(date));
		
		//입력- 내 생일
		SimpleDateFormat input = new SimpleDateFormat("yyyyMMddHHmmss");
		Date birth = input.parse("19910716091415"); //String -> Date형 변환
		System.out.println("내 생일 = " +birth);
		System.out.println("내 생일 = " +sdf.format(birth));
		System.out.println();
		
		//Calendar cal = new Calendar(); - error
		
		Calendar cal = new GregorianCalendar();//Sub Class
		Calendar cal2 =Calendar.getInstance();//메소드
		
		int year = cal.get(1);//int year = cal.get(Calendar.YEAR);
		
		
		int month = cal.get(2)+1;//int month = cal.get(Calendar.MONTH)+1; //1월=0, 2월=1
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int week = cal.get(Calendar.DAY_OF_WEEK);//일요일 =1, 월요일=2
		
		String dayofweek = null;
		switch(week) {
		case 1 : dayofweek = "일";break;
		case 2 : dayofweek = "월";break;
		case 3 : dayofweek = "화";break;
		case 4 : dayofweek = "수";break;
		case 5 : dayofweek = "목";break;
		case 6 : dayofweek = "금";break;
		case 7 : dayofweek = "토";break;
		
		}
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		System.out.println(year+"년 "+month+"월 "+day+"일 "+dayofweek+"요일 ");
		System.out.println(hour+"시 "+minute+"분 "+second+"초 ");
		
		
		
	}
	

}
