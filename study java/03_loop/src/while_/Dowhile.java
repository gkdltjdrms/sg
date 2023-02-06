package while_;

public class Dowhile {

	public static void main(String[] args) {
		// 1 2 3 4 5 6 7 8 9 10
		int a = 0;
		do {a++;
		System.out.print(a+" ");
		}while(a<10);
		System.out.println();
		
		//A B C D E..Z
		// 1줄에 7개씩 출력하시오
		char ch = 'A';
		int count=0;
		do {System.out.print(ch+" ");
		ch++;
		count++; // 개수 - 1 2 3 4 5 ..
		if (count % 7 == 0)  System.out.println();
			
		}while(ch<='Z');
		

	}

}
