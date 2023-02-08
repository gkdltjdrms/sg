package class_;

import java.util.Scanner;

public class ComputeMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print(" 횟수 입력 : ");
        int chance = sc.nextInt();
        Compute[] cp = new Compute[chance];//객체 배열
        for(int i=0; i<chance; i++) {
        	System.out.println((i+1)+"번째");
        	System.out.print(" x 입력 : ");
        	int x = sc.nextInt();
        	System.out.print(" y 입력 : ");
        	int y = sc.nextInt();
        	cp[i] = new Compute();
            cp[i].setData(x, y);
            cp[i].calc();
        }//for
            	  System.out.println(" x\ty\tsum\tsub\tmul\tdiv");
                  for(int i=0; i<chance; i++) {
                      System.out.println(cp[i].getX() +
                    		  "\t" + cp[i].getY() + 
                    		  "\t" + cp[i].getSum() +
                    		  "\t" + cp[i].getSub() + 
                    		  "\t" + cp[i].getMul() + 
                    		  "\t" + cp[i].getDiv());
                  
                  
          	}//for

          
	}//
}


/*
 [문제] 사칙연산
 x, y를 입력하여 합, 차, 곱, 몫을 구하시오
 
 클래스명 : compute (1인분)
 필드 : x, y, sum, sub, mul, div
 메소드 : setData(x의 값, y의 값)
 		calc()
 		getx()
 		gety()
 		getsum()
 		getsub()
 		getmul()
 		getdiv()
 [실행결과]
 횟수 입력 : 2
 
 [1번째]
 x 입력 : 25
 y 입력 : 36
 
 [2번째]
 x 입력 : 35
 y 입력 : 12
 
 x	y	sum		sub		mul		div
 25	36
 35	12
 */
