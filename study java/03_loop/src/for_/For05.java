package for_;

public class For05 {

	public static void main(String[] args) {
		int eng,count=0;
		for(int i=1; i<=100; i++) {
			eng = (int)(Math.random()*26)+65;
			{System.out.print((char)eng+" ");
			if (i % 10 == 0) { // i(count)가 10으로 나누어 떨어질때 밑에프린트ln으로 줄나눔을 한다
				System.out.println();
			}
			if (eng == 65) {
				count++;  // 1번 for문이 돌아갈때마다 count +1
			}
			}
	}System.out.println("A 의 개수 : "+count);//for문이 끝나는구간

}
}

/*
[문제] 대문자(A~Z)를 100개 발생하여 출력하시오  65~90
- 1줄에 10개씩 출력
- 100개중에서 A가 몇개 나왔는지 개수를 출력

[실행결과]
H  D  D  R  A  Y  A  K  T  H
C  X  F  Z  B  S  L  Y  Q  D
H  K  O  H  O  B  Z  N  J  T
U  P  A  P  K  Q  G  W  F  A
S  U  D  Z  I  V  J  U  O  G
L  M  Z  L  H  U  Y  D  Q  R
F  T  I  Z  A  W  E  O  F  Z
A  Y  C  I  U  Z  O  B  C  G
H  G  Y  Z  V  P  I  R  L  G
Y  H  R  R  M  H  Y  S  B  P

A의 개수 = 6
*/