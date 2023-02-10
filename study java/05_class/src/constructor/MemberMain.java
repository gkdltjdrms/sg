package constructor;

import java.util.Scanner;

public class MemberMain {

	public static void main(String[] args) {
		MemberService ms = new MemberService();
		Scanner sc = new Scanner(System.in);
		ms.menu();
		System.out.println("프로그램을 종료합니다");
			
	
		

	}

}

/*
클럽 회원관리 프로그램 작성
여기는 페쇄적인 


menu()
*************
   1. 가입
   2. 출력
   3. 수정
   4. 탈퇴
   5. 끝내기
*************
  번호 : 

[1번 경우]
insert()
5명의 정원이 꽉 찼습니다...//5명이 꽉 차면 
----------------------
이름 입력 :
나이 입력 :
핸드폰 입력 :
주소 입력 :

1 row created(한명이 회원가입을 하였습니다)

[2번 경우]
list()
이름      나이      핸드폰      주소

[3번 경우]
update()
핸드폰 번호 입력 : 010-123-1234
홍길동   25   xxx   xxx

수정 할 이름 입력 : 
수정 할 핸드폰 입력 : 
수정 할 주소 입력 : 

1 row(s) updated
--------------------
핸드폰 번호 입력 : 010-123-1235
찾는 회원이 없습니다

[4번 경우]
delete()
핸드폰 번호 입력 : 
1 row deleted
--------------------
핸드폰 번호 입력 : 
찾는 회원이 없습니다
*/