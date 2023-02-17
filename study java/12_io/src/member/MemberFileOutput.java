package member;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MemberFileOutput implements Member {

	@Override
	public void execute(ArrayList<MemberDTO> arrayList) {
		System.out.println();

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.txt"));
			
			for(MemberDTO memberDTO : arrayList) {
				oos.writeObject(memberDTO);
			}
			
			oos.close();
			System.out.println("저장 하였습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
