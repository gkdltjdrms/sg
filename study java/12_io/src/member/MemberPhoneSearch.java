package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberPhoneSearch implements Member {
	Scanner sc = new Scanner(System.in);

	@Override
	public void execute(ArrayList<MemberDTO> arrayList) {
		System.out.print("검색할 핸드폰 번호를 입력하세요: ");
		String phone = sc.nextLine();
		int sw = 0;
		for (MemberDTO memberDTO : arrayList) {
			if (memberDTO.getPhone().equals(phone)) {
				System.out.println("이름\t나이\t핸드폰\t주소");
				System.out.println(memberDTO);
				System.out.println();
				sw = 1;

				break;
			}
			if (sw == 0)
				System.out.println("찾는 번호가 없습니다");
			break;
		} // for
	}
}
