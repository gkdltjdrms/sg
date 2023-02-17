package member;

import java.util.ArrayList;

public class MemberPrint implements Member {

	@Override
	public void execute(ArrayList<MemberDTO> arrayList) {
		System.out.println();
		System.out.println("이름\t나이\t헨드폰\t주소");

		for (MemberDTO memberdto : arrayList) {
			System.out.println(memberdto.toString());
		} // for

	}
}
