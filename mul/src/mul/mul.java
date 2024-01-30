package mul;

public class mul {

	 public static void main(String[] args) {
	        int n = 9; 

	        for (int i = 1; i <= n; i++) {
	            for (int j = 2; j <= n; j++) {
	                int result = i * j;
	                System.out.print(j + "x" + i + "=" + result + "\t");
	            }
	            System.out.println(); 
	        }
	    }
	}