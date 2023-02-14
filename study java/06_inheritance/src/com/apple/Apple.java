package com.apple;

import com.zoo.Zoo;

import inheritance.SubMain;

public class Apple {

	public static void main(String[] args) {
		System.out.println("빨간 사과");
		
		
		Zoo z = new Zoo();//Zoo클래스의 tiger() 호출
		z.tiger();
		//z.giraffe();
		//z.elephant();
		//z.lion();
	}

}
