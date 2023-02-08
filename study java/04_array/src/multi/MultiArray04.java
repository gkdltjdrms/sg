package multi;

public class MultiArray04 {

	public static void main(String[] args) {
		int[][] ar = new int[3][];//가변길이
		ar[0] = new int[2];
		ar[1] = new int[3];
		ar[2] = new int[4];
		
		ar[0][0] = 10;
		ar[0][1] = 20;
		
		ar[1][0] = 30;
		ar[1][1] = 40;
		ar[1][2] = 50;
		
		ar[2][0] = 60;
		ar[2][1] = 70;
		ar[2][2] = 80;
		ar[2][3] = 90;
		
		System.out.println("배열명 ar = "+ar);
		for(int i=0; i<ar.length; i++) {
			
			System.out.println("행 ar["+i+"] = "+ar[i]);
			for(int j = 0; j<ar[i].length; j++) { //
				System.out.println("ar["+i+"]["+j+"] = "+ ar[i][j]);
				
			}// for j
			System.out.println();
		}// for i
		

	}

}

//
