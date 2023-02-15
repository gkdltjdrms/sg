package sungJuk;

import java.util.ArrayList;
import java.util.Scanner;

public class SungJukInsert implements SungJuk  {
	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("번호 입력");
		int no = sc.nextInt();
		System.out.println("이름 입력");
		String name = sc.next();
		System.out.println("국어 입력");
		int kor = sc.nextInt();
		System.out.println("영어 입력");
		int eng = sc.nextInt();
		System.out.println("수학 입력");
		int math = sc.nextInt();
		
		SungJukDTO sungjukdto = new SungJukDTO(no, name, kor, eng, math);//생성자를 통해 dto로 보낸다
		sungjukdto.calc();
		
		arrayList.add(sungjukdto);
		
		System.out.println("입력하였습니다");
	}

	
	

}
