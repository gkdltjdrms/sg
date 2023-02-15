package collection;

import java.util.ArrayList;
import java.util.Collection;

public class PersonMain {
	
	public ArrayList<PersonDTO> init() {
		PersonDTO aa = new PersonDTO("홍길동", 25);
		PersonDTO bb = new PersonDTO("프로도", 30);
		PersonDTO cc = new PersonDTO("라이언", 40);
		
		ArrayList<PersonDTO> list = new ArrayList<>();
		list.add(aa);
		list.add(bb);
		list.add(cc);
		
		return list;
	}

	public static void main(String[] args) {
		PersonMain personMain = new PersonMain();
		
		ArrayList<PersonDTO> list = personMain.init();
		
		for(PersonDTO personDTO : list) {
			System.out.println(personDTO.getName()+"\t"+personDTO.getAge());
		}//for
			System.out.println();
			for(PersonDTO personDTO : list) {
				System.out.println(personDTO);//주소의 값만 나온다//to string 을 사용하여 출력
			}
		
		}
	}


