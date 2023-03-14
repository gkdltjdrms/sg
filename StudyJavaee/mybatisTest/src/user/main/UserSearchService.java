package user.main;

import java.util.List;
import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.service.UserService;

public class UserSearchService implements UserService {

	@Override
	public void execute() {

	    Scanner scan = new Scanner(System.in);

	    System.out.println("1. 이름 검색");
	    System.out.println("2. 아이디 검색");
	    System.out.print("번호 입력: ");
	    int choice = scan.nextInt();

	    UserDAO userDAO = UserDAO.getInstance();

	    if (choice == 1) {
	        System.out.print("검색할 이름 입력: ");
	        String name = scan.next();

	        List<UserDTO> list = userDAO.searchByName(name);

	        if (list != null) {
	            System.out.println("검색 결과");
	            for (UserDTO userDTO : list) {
	                System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
	            }
	        } else {
	            System.out.println("검색 결과가 없습니다.");
	        }
	    } else if (choice == 2) {
	        System.out.print("검색할 아이디 입력: ");
	        String id = scan.next();

	        UserDTO userDTO = userDAO.searchById(id);

	        if (userDTO != null) {
	            System.out.println("검색 결과");
	            System.out.println(userDTO.getName() + "\t" + userDTO.getId() + "\t" + userDTO.getPwd());
	        } else {
	            System.out.println("검색 결과가 없습니다.");
	        }
	    }
	}

}
