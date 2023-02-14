package sungJuk;

import java.util.ArrayList;

public class SungJukList implements SungJuk {

	@Override
	public void execute(ArrayList<SungJukDTO> arrayList) {
		System.out.println();
		
//		for(int i=0; i<arrayList.size(); i++) {
//			System.out.println(arrayList.get(i).getNo()+"\t"
//					+arrayList.get(i).getName()+"\t"
//					+arrayList.get(i).getKor()+"\t"
//					+arrayList.get(i).getEng()+"\t"
//					+arrayList.get(i).getMath()+"\t"
//					+arrayList.get(i).getTot()+"\t"
//					+arrayList.get(i).getAvg()+"\t");
//		}//for
		
//		for(SungJukDTO sungjukdto : arrayList) {
//			System.out.println(sungjukdto.getNo()+"\t"
//					+sungjukdto.getName()+"\t"
//					+sungjukdto.getKor()+"\t"
//					+sungjukdto.getEng()+"\t"
//					+sungjukdto.getMath()+"\t"
//					+sungjukdto.getTot()+"\t"
//					+sungjukdto.getAvg()+"\t");
//		}//dto for
		
		for(SungJukDTO sungjukdto : arrayList) {
			System.out.println(sungjukdto); 
		}
	
	}

	

}
