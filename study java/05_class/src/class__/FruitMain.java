package class__;

import java.util.Scanner;

class Fruit {
	private String pum;
    private int jan, feb, mar, tot;
    private static int sumJan;
    private static int sumFeb;
    private static int sumMar;
	
	
	
	public Fruit(String pum, int jan, int feb, int mar) {
		this.pum=pum;
		this.jan=jan;
		this.feb=feb;
		this.mar=mar;
		this.calc();
	}
	
	
	
	
	public void calc() {
		this.tot = this.jan + this.feb + this.mar;
		  Fruit.sumJan += this.jan;
	      Fruit.sumFeb += this.feb;
	      Fruit.sumMar += this.mar;
		
	}
	   public void display() {
	        System.out.printf("%-8s%5d%5d%5d%7d\n", this.pum, this.jan, this.feb, this.mar, this.tot);
	    }
	
	   public static void output(Fruit[] fruits) {
		   int sumjan =0;
		   int sumfeb =0;
		   int summar =0;
	        System.out.println("---------------------------------");
	        System.out.println("PUM      JAN   FEB   MAR      TOT");
	        System.out.println("---------------------------------");
	        for (Fruit fruit : fruits) {
	            fruit.display();
	            sumjan += fruit.jan;
	            sumfeb += fruit.feb;
	            summar += fruit.mar;
	        }
	        System.out.println("---------------------------------");
	        System.out.printf("        %3d   %3d   %3d\n", Fruit.sumJan, Fruit.sumFeb, Fruit.sumMar);
	    }
		
		
	}
	
	
	

//------------------
public class FruitMain {

	public static void main(String[] args) {
		 Fruit[] fruits = {
	                new Fruit("사과", 100, 80, 75),
	                new Fruit("포도", 30, 25, 10),
	                new Fruit("딸기", 25, 30, 90)
	        };
	        Fruit.output(fruits);
	    }
	}


/*
과일 판매량 구하기
월별 매출합계도 구하시오

클래스 : Fruit
필드 : pum, jan, feb, mar, tot
	  sumJan, sumFeb, sumMar

메소드 : 생성자(품명, 1월, 2월, 3월)
		calcTot()
		display()
		public static void output()

클래스 : FruitMain

[실행결과]
---------------------------------
PUM      JAN   FEB   MAR      TOT
---------------------------------
사과    100    80    75     255
포도     30    25    10     65          //객체배열
딸기     25    30    90     145
---------------------------------
        155   135   175            output()로 처리
*/