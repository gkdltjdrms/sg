package abstract_;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberMain {

	public static void main(String[] args) {
		//NumberFormat nf = new NumberFormat(); -error 추상클래스라 생성이 안된다
		
		//3자리 마다 ,를 찍고 소수이하 3째자리까지 출력
		NumberFormat nf = new DecimalFormat();//Sub Class 이용하여 생성
		System.out.println(nf.format(12345678.456789));
		System.out.println(nf.format(12345678));
		System.out.println();
		
		NumberFormat nf2 = new DecimalFormat("#,###.##원");
		//유효숫자가 아닌것은 표현하지 않는다
		System.out.println(nf2.format(12345678.456789));
		System.out.println(nf2.format(12345678));
		System.out.println();
		
		NumberFormat nf3 = new DecimalFormat("#,###.00원");
		//유효숫자가 아닌것은 표현하지 않는다
		System.out.println(nf3.format(12345678.456789));
		System.out.println(nf3.format(12345678));
		System.out.println();
		
		//메소드를 이용하여 생성
		//NumberFormat nf4 = NumberFormat.getInstance();//메소드 이용하여 생성
		NumberFormat nf4 = NumberFormat.getCurrencyInstance();//won
		nf4.setMaximumFractionDigits(2);//소수이하 2째자리까지 표시
		nf4.setMinimumFractionDigits(2);
		System.out.println(nf4.format(12345678.456789));
		System.out.println(nf4.format(12345678));
		System.out.println();
		
		NumberFormat nf5 = NumberFormat.getCurrencyInstance(Locale.US);//$
		nf5.setMaximumFractionDigits(2);//소수이하 2째자리까지 표시
		nf5.setMinimumFractionDigits(2);
		System.out.println(nf5.format(12345678.456789));
		System.out.println(nf5.format(12345678));
		System.out.println();
	}

}
