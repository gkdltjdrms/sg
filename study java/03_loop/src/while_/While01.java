package while_;

public class While01 {

	public static void main(String[] args) {
		// 1 2 3 4 5 6 7 8 9 10 
		int a = 0;
		while (a<10) {
			a++;
			System.out.print(a +" ");
		}//while
		System.out.println();
		
		a = 0;
		while(true) {
			a++;
			System.out.print(a+ " ");
			if(a>=10) break; //while을 벗어나라
		}
		
		

	}

}
