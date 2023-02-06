package operator;

public class Operator05 {

	public static void main(String[] args) {
		boolean a = 25 > 36;
		System.out.println("a = "+a); //True
		System.out.println("!a = "+!a); // ! 부정 False 
		System.out.println();
		
		String b = "apple"; // literal 생성
		String c = new String("apple");
		
		String result = b == c ? "같다" : "다르다"; //주소를 비교하는것이다
		System.out.println("b == c: " + result); // 다르다
		System.out.println("b == c: " + b != c ? "참" : "거짓"); // 참
		System.out.println();
		
		String result2 = b.equals(c) ? "같다" : "다르다";
		System.out.println("b.equals(c): " + result2); //같다
		System.out.println("b.equals(c): " + ((!b.equals(c)) ? "같다" : "다르다")); //다르다

		
		
		
	}

}
