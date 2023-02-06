package array;

import java.util.Scanner;

public class Array03 {

	public static void main(String[] args) {
		int size;
		int[] ar;
		int sum = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("배열의 크기 입력 : ");
		size = scan.nextInt();
		ar = new int[size];
		for (int i = 0; i < size; i++) {
		System.out.print("ar[" + i + "] 입력 : ");
		ar[i] = scan.nextInt();
		sum += ar[i];
		}
		System.out.println();
		//최대값 구하기
		int max = ar[0];
		int min = ar[0];
		for (int i = 1; i < ar.length; i++) {
		  if (ar[i] > max) max = ar[i];
		  if (ar[i] < min) min = ar[i];
		  
		}
		System.out.println("합 = " + sum);
		System.out.println("최대값 = " + max);
		System.out.println("최소값 = " + min);
		
		}
		}
/*
[문제] 배열의 크기를 입력 받아서 배열을 생성한다.

[실행결과]
배열 크기 입력 : 3

ar[0] 입력 : 25
ar[1] 입력 : 13
ar[2] 입력 : 57

25+ 13+ 57
합 = 95
*/