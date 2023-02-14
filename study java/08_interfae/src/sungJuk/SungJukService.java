package sungJuk;

import java.util.Scanner;
import java.util.ArrayList;


public class SungJukService {
	private ArrayList<SungJukDTO> arrayList = new ArrayList<SungJukDTO>();
	

	public void menu() {
		Scanner sc = new Scanner(System.in);
		SungJuk sungjuk = null;
	
		int num;
		
		while(true) {
				System.out.println();
				System.out.println("  *********************");
				System.out.println("	1.	입력");
				System.out.println("	2.	출력");
				System.out.println("	3.	수정");
				System.out.println("	4.	삭제");
				System.out.println("	5.	정렬");
				System.out.println("	6.	끝내기");
				System.out.println("  *********************");
				System.out.println("   번호 : ");
				num = sc.nextInt();		
				
				if(num == 6) break;
				
				if(num == 1) sungjuk = new SungJukInsert() ;	
				else if(num == 2)sungjuk = new SungJukList() ;
				else if(num == 3);
				else if(num == 4);
				else if(num == 5);
				else {
					System.out.println("1 ~ 6번까지만 입력하세요");
					continue;
				}
				
				sungjuk.execute(arrayList); //호출
			}//while

		
		}
}


