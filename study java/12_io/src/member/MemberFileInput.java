package member;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MemberFileInput implements Member {

	@Override
	public void execute(ArrayList<MemberDTO> arrayList) {
		System.out.println();

		arrayList.clear();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("member.txt"))) {
			while (true) {
				try {
					MemberDTO memberDTO = (MemberDTO) ois.readObject();
					arrayList.add(memberDTO);
				} catch (EOFException e) {
					break;
				}
			}
			ois.close();
			System.out.println("파일에 읽기 완료");
		} catch (IOException e) {
			System.out.println("파일을 찾을 수 없습니다");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다");
			e.printStackTrace();
		}

	} // try

}
