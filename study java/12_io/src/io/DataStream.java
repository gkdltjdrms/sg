package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataStream {

	public static void main(String[] args) {
		try {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("result.txt"));
		
//		FileOutputStream fos = new FileOutputStream("result.txt");
//		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동");
		dos.writeInt(25);
		dos.writeDouble(185.3);
		
		dos.close();
		
		//load file
		DataInputStream dis = new DataInputStream(new FileInputStream("result.txt"));
		String name = dis.readUTF();
		int age = dis.readInt();
		double height = dis.readDouble();
		
		System.out.println("이름 = "+name+" 나이 = "+age+" 키 = "+height);
		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		
		}catch(IOException e) {//하나로도 가능
			e.printStackTrace();
		}
		
	}

}
