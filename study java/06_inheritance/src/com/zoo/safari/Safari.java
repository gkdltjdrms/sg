package com.zoo.safari;

import com.zoo.Zoo;

public class Safari extends Zoo {

	public static void main(String[] args) {
		Zoo z = new Zoo();//Zoo클래스의 tiger() 호출
		z.tiger();
//		z.giraffe();
//		z.elephant();
//		z.lion();
		System.out.println();
		
		Safari s = new Safari();
		s.tiger();
		s.giraffe();
	}

}
