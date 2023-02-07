package array;

public class SelectionSort {

	public static void main(String[] args) {
		int[] ar = {32, 40, 25, 78, 56};
		
		//출력
		for (int i=0; i<ar.length; i++) { 
			System.out.print(String.format("%4d", ar[i]));
			
		}//for i end
		System.out.println();
		
		//정렬
		//오름차순(ASCENDING)
		int temp;
		for (int i = 0; i < ar.length-1; i++) {
			  for (int j = i+1; j < ar.length; j++) { System.out.println(i+" "+j);
				  if(ar[j] < ar[i]) { 
					  temp = ar[i];
					  ar[i] = ar[j];
					  ar[j] = temp;
					  
				  }//if end
			   
				  
			  }//for j end
			}//for i end
		
		//after sort
		for (int i=0; i<ar.length; i++) {
			System.out.print(String.format("%4d", ar[i]));			
		}	System.out.println();				
		    
	}

}






