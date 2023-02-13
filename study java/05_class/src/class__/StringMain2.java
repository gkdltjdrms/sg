package class__;

import java.util.Scanner;

public class StringMain2 {

	 public static void main(String[] args) {
	        Scanner scan = new Scanner(System.in);
	        while (true) {
	            System.out.print("문자열 입력: ");
	            String original = scan.next();

	            System.out.print("현재 문자열 입력: ");
	            String current = scan.next();
	            current = current.toLowerCase();

	            System.out.print("바꿀 문자열 입력 : ");
	            String after = scan.next();

	            original = original.toLowerCase();
	            int index = original.indexOf(current);
	            if (index == -1) {
	                System.out.println("입력한 문자열의 크기가 작습니다");
	                System.out.println("치환 할 수 없습니다");
	            } else {
	                int count = 0;
	                while (index != -1) {
	                    original = original.substring(0, index) + after + original.substring(index + current.length());
	                    count++;
	                    index = original.indexOf(current);
	                }
	                System.out.println(original);
	                System.out.println(count + "번 치환");
	            }
	        }
	    }
	}


/*
치환하는 프로그램을 작성하시오 - indexOf(?, ?), replace()
String 클래스의 메소드를 이용하시오
대소문자 상관없이 개수를 계산하시오
while 문 사용후 if문 
[실행결과]
문자열 입력 : aabba
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddbba
1번 치환

문자열 입력 : aAbbA
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddbba
1번 치환

문자열 입력 : aabbaa
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddbbdd
2번 치환

문자열 입력 : AAccaabbaaaaatt
현재 문자열 입력 : aa
바꿀 문자열 입력 : dd
ddccddbbddddatt
4개 치환

문자열 입력 : aabb
현재 문자열 입력 : aaaaa
바꿀 문자열 입력 : ddddd
입력한 문자열의 크기가 작습니다
치환 할 수 없습니다
*/