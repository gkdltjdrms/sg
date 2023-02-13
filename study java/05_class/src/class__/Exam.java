package class__;

import java.util.Scanner;

public class Exam {
	   private String name;
	   private String dap;
	   private char[] ox;
	   private int score;
	   private final String JUNG = "11111"; //상수화
	   
	   public Exam(){
	      Scanner sc = new Scanner(System.in);
	      System.out.println("이름 입력 : ");
	      this.name = sc.next();
	      System.out.println("답 입력 : ");
	      this.dap = sc.next();
	      
	      ox = new char[5]; //생성 
	   }
	   
	   public void compare() {
	      for(int i= 0; i<ox.length; i++) {
	      //for(int i= 0; i<JUNG.length(); i++) {
	         if(dap.charAt(i) == JUNG.charAt(i)) {
	               ox[i] = 'O';
	               score += 20;
	         }else
	            ox[i] = 'X';
	      }
	   }
	   
	   public String getName(){
	      return name;
	   }
	   public char[] getOx() {
	      return ox;
	   }
	   public int getScore() {
	      return score;
	   }
	}
