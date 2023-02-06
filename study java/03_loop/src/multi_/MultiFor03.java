package multi_;

public class MultiFor03 {

	public static void main(String[] args) {
		int i,dan,count=0;
		for(dan=1; dan<=9; dan++) {
			for (i=2; i<=9; i++) {
				System.out.print(i+"*"+dan+"="+dan*i+"\t");
				count++;
				if (count % 8 == 0)  System.out.println();
			}//for i
			
		}// for dan

	}

}