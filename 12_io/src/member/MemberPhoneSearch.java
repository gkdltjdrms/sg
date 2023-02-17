package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberPhoneSearch implements Member {
    Scanner sc = new Scanner(System.in);

    @Override
    public void execute(ArrayList<MemberDTO> arrayList) {
        System.out.println("핸드폰 검색을 선택하셨습니다.");
        System.out.print("검색할 핸드폰 번호를 입력하세요: ");
        String phone = sc.nextLine();

        for (MemberDTO memberDTO : arrayList) {
            if (memberDTO.getPhone().equals(phone)) {
                System.out.println("이름: " + memberDTO.getName());
                System.out.println("나이: " + memberDTO.getAge());
                System.out.println("핸드폰: " + memberDTO.getPhone());
                System.out.println("주소: " + memberDTO.getAddress());
                System.out.println();
            }else System.out.println("찾는 번호가 없습니다");
        }
    }
}
