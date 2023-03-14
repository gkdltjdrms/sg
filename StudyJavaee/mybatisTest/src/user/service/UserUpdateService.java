package user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserUpdateService implements UserService {

	@Override
	public void execute() {
		System.out.println();
		
		//data
		Scanner scan = new Scanner(System.in);
		
		System.out.println("찾고자 하는 아이디를 입력 : ");
		String id = scan.next();
		
		UserDAO userDAO = UserDAO.getInstance();
		UserDTO userDTO = userDAO.getUser(id);
		
	
		
		if(userDTO == null) {
			System.out.println("찾고자 하는 아이디가 없습니다.");
			return;
		}
		
		System.out.println("수정할 이름을 입력 : ");
		String name = scan.next();
		
		System.out.println("수정할 비밀번호를 입력 : ");
		String pwd = scan.next();
		
		
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("id", id);
		map.put("pwd", pwd);
		
		userDAO.update(map);
		
		System.out.println(id + "의 정보가 수정되었습니다.");
		
		
//		userDTO.setName(name);
//		userDTO.setPwd(pwd);
//	
//		
//		userDAO.update(userDTO);
//		
//		System.out.println(id + "의 정보가 수정되었습니다.");
	}

}