package user.main;

import java.util.Scanner;

import user.service.UserInsertService;
import user.service.UserSelectService;
import user.service.UserService;
import user.service.UserUpdateService;

public class UserMain {
	
	public void menu() {
		Scanner scan = new Scanner(System.in);
		UserService userService = null;
		int num;
		
		while(true) {
			System.out.println();
			System.out.println("**********");
			System.out.println("	1. 입력");
			System.out.println("	2. 출력");
			System.out.println("	3. 수정");
			System.out.println("	4. 삭제");
			System.out.println("	5. 검색");
			System.out.println("	6. 종료");
			System.out.println("**********");
			System.out.println("번호 입력 :");
			num = scan.nextInt();
			if(num == 6)break;
			
			if(num == 1) {
				userService = new UserInsertService();	//부모 = 자식, 다형성
			}else if(num == 2) {
				userService = new UserSelectService();
			}
			else if(num == 3) {
				userService = new UserUpdateService();
			}
			else if(num == 4) {
				userService = new UserDeleteService();
			}
			else if(num == 5) {
				userService = new  UserSearchService();
			}
			userService.execute();//호출 
		}//while
		
	}

	public static void main(String[] args) {
		UserMain userMain = new UserMain();
		userMain.menu();
		System.out.println("프로그램을 종료합니다.");
	}

}
