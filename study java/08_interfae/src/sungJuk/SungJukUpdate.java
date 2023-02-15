package sungJuk;

import java.util.ArrayList;
import java.util.Scanner;

public class SungJukUpdate implements SungJuk {

	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.println();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("찾는 번호 입력");
		int no = scan.nextInt();
		
		int i,sw=0;
		for(SungJukDTO sungjukdto : arrayList) {
			if(sungjukdto.getNo() == no) {
				sw = 1;
			
				
				System.out.println();System.out.println("수정할 이름 입력 : ");
				String name = scan.next();
				System.out.println();System.out.println("수정할 국어 입력 : ");
				int kor = scan.nextInt();
				System.out.println();System.out.println("수정할 영어 입력 : ");
				int eng = scan.nextInt();
				System.out.println();System.out.println("수정할 수학 입력 : ");
				int math = scan.nextInt();
				
				sungjukdto.setName(name);
				sungjukdto.setKor(kor);
				sungjukdto.setEng(eng);
				sungjukdto.setMath(math);
				
				sungjukdto.calc(); //재계산
				
				System.out.println("수정하였습니다.");
				
			}
		}//for
			
			if(sw == 0)
				System.out.println("없는 번호 입니다.");
			
			
			
	}
}

		
			
			
			
			
			
		
			
			



