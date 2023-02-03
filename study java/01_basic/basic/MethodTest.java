package basic;

public class MethodTest {

	public static void main(String[] args) {
		// 25, 36의 큰값을 구하시오
		int big = Math.max(25, 36);  //호출
		System.out.println("최대값 =" +" "+big); 
		
		//25.8, 78.6의 작은값을 구하시오
		double small = Math.min(25.8, 78.6);
		System.out.println("최소값 =" +" "+small);
		
		//250을 2진수 출력
		String binary = Integer.toBinaryString(250);
		String oct = Integer.toOctalString(250);
		String Hexa = Integer.toHexString(250);
		System.out.println("2진수 = " + binary);
		System.out.println("8진수 = " + oct);
		System.out.println("16진수 = " + Hexa);
	}

}
