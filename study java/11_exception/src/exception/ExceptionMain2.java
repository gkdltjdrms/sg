package exception;

import java.util.ArrayList;
import java.util.Scanner;



public class ExceptionMain2 {
    private int x;
    private int y;
    private int mul;

    public void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("x 입력: ");
        x = sc.nextInt();

        System.out.print("y 입력: ");
        y = sc.nextInt();

        mul = 1;
        for (int i = 0; i < y; i++) {
            mul *= x;
        }
    }

    public void output() {
       if(y>=0) {
    	   int mul = 1;
    	   for(int i=1; i<=y; i++) {
    		   mul *= x;
    	   }
    	System.out.println("----------------------------------");
        System.out.println(x+"의 "+y+" 승은 "+mul);
    }else {
//    	System.out.println("y 는 0보다 크거나 같아야 한다.");
    	
    	//개발자가 강제로 Exception 발생 
    	try {
    		throw new Exception("y는 0보다 크거나 같아야 한다.");
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
  }
    
    
    public static void main(String[] args) {
        ExceptionMain2 exceptionmain2 = new ExceptionMain2();
        exceptionmain2.input();
        exceptionmain2.output();
    }
}


/*
[문제] 제곱 연산
- x의 y승을 처리 한다.

[실행결과]
x 입력 : 2
y 입력 : 10         input()
----------------------------------
2의 10승은 xxx        output()
*/