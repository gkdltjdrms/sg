package class_;

public class SungJukMain2 {

	public static void main(String[] args) {	
		SungJuk[] ar = new SungJuk[3];//객체 배열
		ar[0] = new SungJuk(); 
		ar[0].setData("홍길동", 91, 95, 100);
		ar[1] = new SungJuk(); 
		ar[1].setData("프로도", 100, 89, 75);
		ar[2] = new SungJuk();
		ar[2].setData("죠르디", 75, 80, 48); // 호출
		
		for(int i=0; i<ar.length; i++) {
			ar[i].calc();
			System.out.println(ar[i].getName()+"\t"
					+ar[i].getKor()+"\t"
					+ar[i].getEng()+"\t"
					+ar[i].getMath()+"\t"
					+ar[i].getTot()+"\t"
					+String.format("%.2f",ar[i].getAvg() )+"\t"
					+ar[i].getGrade());
		
			
		
		
		
		
		ar[1].calc();
		System.out.println(ar[i].getName()+"\t"
				+ar[i].getKor()+"\t"
				+ar[i].getEng()+"\t"
				+ar[i].getMath()+"\t"
				+ar[i].getTot()+"\t"
				+String.format("%.2f",ar[i].getAvg() )+"\t"
				+ar[i].getGrade());
	
		
		ar[i].calc();//호출
		System.out.println(ar[i].getName()+"\t"
		+ar[i].getKor()+"\t"
		+ar[i].getEng()+"\t"
		+ar[i].getMath()+"\t"
		+ar[i].getTot()+"\t"
		+String.format("%.2f",ar[i].getAvg() )+"\t"
		+ar[i].getGrade());
		}
//		int a=10; // 정수형 변수
//		int b=20;
//		int c=30;
//		
//		int[] ar = new int[3];//정수형 배열
//				ar[0]=10;
//				ar[1]=20;
//				ar[2]=30;
//	}

}
}