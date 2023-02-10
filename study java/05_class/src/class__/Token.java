package class__;

import java.util.StringTokenizer;

public class Token {

	public static void main(String[] args) {
		String str = "학원,집,,게임방";
		System.out.println(str);
		StringTokenizer st = new StringTokenizer(str,",");
		System.out.println("토큰 개수 = "+ st.countTokens()); //3
		
		while(st.hasMoreTokens()) {//토큰 있다?(true) 없다?(false)
			System.out.println(st.nextToken()); //토큰을 꺼내고 다음토큰으로 이동 학원 \n 집 \n 게임방
			
		}//while
		System.out.println("------------------");
		
		String[] ar = str.split(",");
		
		for(String data : ar) {
			System.out.println(data); //게임방
		}
		
	}

}
