package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberInsert implements Member {
	Scanner sc = new Scanner(System.in);

	@Override
	public void execute(ArrayList<MemberDTO> arrayList) {
		System.out.println("회원 등록을 선택하셨습니다.");
		System.out.println();
		System.out.print("이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("나이를 입력하세요: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("핸드폰 번호를 입력하세요: ");
		String phone = sc.nextLine();
		System.out.print("주소를 입력하세요: ");
		String address = sc.nextLine();

		MemberDTO member = new MemberDTO(name, age, phone, address);
		arrayList.add(member);
		System.out.println("회원 정보가 등록되었습니다.");
	}
}
