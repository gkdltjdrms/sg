package basic;

public class Sungjuk {

	public static void main(String[] args) {
		int kor,eng,math,tot;
		char name;
		float avg;
		kor=85; eng=78; math=100; tot=kor+eng+math; avg=(float)tot/3;name='L';
		System.out.println("\t***"+name+" "+"성적표***");
		System.out.println("국어\t영어\t수학\t총점\t평균");
		System.out.println(kor+"\t"+eng+"\t"+math+"\t"+tot+"\t"+String.format("%.3f",avg));
	}

}
// result
//***L 성적표***
//국어	영어	수학	총점	평균
//85	78	100	263	87.667



/*
[문제] 성적계산
이름이 L 이고 국어점수(kor) 85점, 영어점수(eng) 78점, 수학점수(math) 100일때 총점(tot)과 평균(avg)을 구하시오

 총점 = 국어점수 + 영어점수 + 수학점수
 평균 = 총점 / 과목수
 
 [실행결과]
 	*** L 성적표 ***
 국어		영어		수학		총점		평균
 85		78		100		xxx		xx.xxx
 \t 탭키 
*/