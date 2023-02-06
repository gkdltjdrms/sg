package array;

public class Array01 {

	public static void main(String[] args) {
		int[] ar = new int[5];
		ar[0] = 25;
		ar[1] = 37;
		ar[2] = 55;
		ar[3] = 42;
		ar[4] = 30;

		for (int i = 0; i < ar.length; i++) {
			System.out.println("ar[" + i + "] = " + ar[i]);
		}

		System.out.println();
		for (int i = ar.length - 1; i >= 0; i--) {
			System.out.println("ar[" + i + "] = " + ar[i]);
		}

		System.out.println();
		System.out.println("Odd data only:");
		for (int i = 0; i < ar.length; i++) {
			if (i % 2 == 1) {
				System.out.println("ar[" + i + "] = " + ar[i]);
			}
		}

		System.out.println();
		System.out.println("확장 for:");
		for (int data : ar) {
			System.out.println(data);
		}
	}

}



