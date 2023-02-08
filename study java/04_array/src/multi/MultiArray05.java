package multi;

import java.util.Scanner;
import java.text.DecimalFormat;

public class MultiArray05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.00");
        
        System.out.print("인원수 : ");
        int cnt = sc.nextInt();
        
        String[][] names = new String[cnt][2];
        int[][][] subjects = new int[cnt][][];
        
        for (int i = 0; i < cnt; i++) {
            System.out.print("이름입력 : ");
            names[i][0] = sc.next();
            System.out.print("과목수 입력 : ");
            int subjectCnt = sc.nextInt();
            subjects[i] = new int[subjectCnt][2];
            int total = 0;
            
            for (int j = 0; j < subjectCnt; j++) {
       
                System.out.print("과목명 입력 : ");
                String subject = sc.next();
                for (int k = 0; k < subjectCnt; k++) {
                    if (j == k) {
                        System.out.print(subject + " 점수 입력 : ");
                        int jumsu = sc.nextInt();
                        total += jumsu;
                        subjects[i][j][0] = jumsu;
                        subjects[i][j][1] = j;
                    }
                }
            }




            
            names[i][1] = df.format(total / (double) subjectCnt);
            System.out.println("---------------------");
        }
        
        System.out.println("이름\t국어\t영어\t총점\t평균");
        for (int i = 0; i < cnt; i++) {
            System.out.print(names[i][0] + "\t");
            for (int j = 0; j < subjects[i].length; j++) {
                System.out.print(subjects[i][j][0] + "\t");
            }
            System.out.println(names[i][1]);
        }
    }
}
/*
[문제]
인원수를 입력하여 인원수만큼 데이터를 입력받고 총점과 평균을 구하시오
평균은 소수이하 2째자리까지 출력

[실행결과]
인원수 : 2 (cnt)

이름입력 : 홍길동 (name)
과목수 입력 : 2   (subjectCnt)
과목명 입력 : 국어 (subject)
과목명 입력 : 영어
국어 점수 입력 : 95 (jumsu)
영어 점수 입력 : 100
---------------------
이름입력 : 이기자
과목수 입력 : 3
과목명 입력 : 국어
과목명 입력 : 영어
과목명 입력 : 과학
국어 점수 입력 : 95
영어 점수 입력 : 100
과학 점수 입력 : 90
---------------------

이름     국어     영어   총점     평균
홍길동    95     100   xxx     xx.xx

이름      국어  영어   과학    총점      평균
이기자   95   100   90    xxx      xx.xx
*/