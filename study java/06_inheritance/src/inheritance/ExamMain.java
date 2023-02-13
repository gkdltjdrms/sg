package inheritance;

import java.util.Scanner;

public class ExamMain {
	

		    public static void main(String[] args) {
		        Scanner sc = new Scanner(System.in);
		        System.out.print("인원수 입력 : ");
		        int num = sc.nextInt();

		        Exam[] exams = new Exam[num];

		        for(int i=0; i<num; i++) {
		        	
		            System.out.print("이름 입력 : ");
		            String name = sc.next();
		            System.out.print("답 입력 : ");
		            String dap = sc.next();

		            exams[i] = new Exam(name, dap);
		        }

		        System.out.println("이름      1 2 3 4 5   점수");
		        for(int i=0; i<num; i++) {
		            System.out.print(exams[i].getName() + "     ");
		            for(int j=0; j<5; j++) {
		                System.out.print(exams[i].getOx()[j] + " ");
		            }
		            System.out.println("  " + exams[i].getScore());
		        }
		    }

				
				

}
