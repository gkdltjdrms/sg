package multi;

public class MultiArray02 {

	public static void main(String[] args) {
		int[][] ar = new int[10][10];
		int num = 0;
		//input
//		for(int i = 0; i < ar.length; i++) {
//			for(int j=0; j<ar[i].length; j++) {
//				num++;
//				ar[i][j] = num;
//			}//for i
//		}//for j
		
		//2
		/*
		 * for(int i = 0; i < ar.length; i++) { for(int j=0; j<ar[i].length; j++) {
		 * num++; ar[j][i] = num; //순서를 바꾸면 배열 순서가 바뀐다 }//for i }//for j
		 * 
		 * for(int i = 0; i < ar.length; i++) { for(int j=0; j<ar[i].length; j++) {
		 * num++; ar[j][i] = num; //순서를 바꾸면 배열 순서가 바뀐다 }//for i }//for j
		 */		
		
		for(int i = ar.length-1; i >=0; i--) {
			for(int j=ar[i].length-1; j>=0; j--) {
				num++; //1 2 3 4 5
				ar[i][j] = num;    //순서를 바꾸면 배열 순서가 바뀐다
				
			}//for i
		}//for j
		
		//출력
		for(int i=0; i<ar.length; i++) {
			for(int j=0; j<ar[i].length; j++) {
				System.out.print(String.format("%4d", ar[i][j]));
				
			}//for j
			System.out.println();
		}//for i
	}

}





