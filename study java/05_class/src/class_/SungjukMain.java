package class_;

public class SungjukMain {

	public static void main(String[] args) {
		SungJuk aa = new SungJuk();
		aa.setData("홍길동", 91, 95, 100); // 호출
		aa.calc();//호출
		System.out.println(aa.getName()+"\t"
		+aa.getKor()+"\t"
		+aa.getEng()+"\t"
		+aa.getMath()+"\t"
		+aa.getTot()+"\t"
		+String.format("%.2f",aa.getAvg() )+"\t"
		+aa.getGrade());
	
		SungJuk bb = new SungJuk();
		bb.setData("프로도", 100, 89, 75); // 호출
		bb.calc();//호출
		System.out.println(bb.getName()+"\t"
		+bb.getKor()+"\t"
		+bb.getEng()+"\t"
		+bb.getMath()+"\t"
		+bb.getTot()+"\t"
		+String.format("%.2f",bb.getAvg() )+"\t"
		+bb.getGrade());
		
		SungJuk cc = new SungJuk();
		cc.setData("죠르디", 75, 80, 48); // 호출
		cc.calc();//호출
		System.out.println(cc.getName()+"\t"
		+cc.getKor()+"\t"
		+cc.getEng()+"\t"
		+cc.getMath()+"\t"
		+cc.getTot()+"\t"
		+String.format("%.2f",cc.getAvg() )+"\t"
		+cc.getGrade());
		
	}//

}


/*
[문제] 성적 처리
- 총점, 평균, 학점을 구하시오
- 평균은 소수이하 2째자리까지 출력
총점 = 국어+영어+수학
평균 = 총점 / 과목수
학점은	평균이 90 이상이면 'A'
		평균이 80 이상이면 'B'
		평균이 70 이상이면 'C'
		평균이 60 이상이면 'D'
		그외는 'F'
		
클래스명 : SungJuk
필드 : name, kor, eng, math, tot, avg, grade
메소드 : setData(이름, 국어, 영어, 수학)
		calc()- 총점, 평균, 학점 계산
		getName()
		getKor()
		getEng()
		getMath()
		getTot()
		getAvg()
		getGrade()


[실행 결과]
----------------------------------------------------
이름      국어      영어      수학      총점      평균      학점
----------------------------------------------------
홍길동     90       95      100
----------------------------------------------------
*/