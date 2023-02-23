package student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	private String name;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";

	public Student() {
		try {
			Class.forName(driver);// Class타입으로 생성
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	Scanner scan = new Scanner(System.in);

	public void menu() {
		while (true) {
			System.out.println("****************");
			System.out.println("\t관리");
			System.out.println("****************");
			System.out.println(" 1. 입력");
			System.out.println(" 2. 검색");
			System.out.println(" 3. 삭제");
			System.out.println(" 4. 종료");
			System.out.println("****************");
			System.out.println();
			System.out.println("  번호 선택 : ");
			int num = scan.nextInt();

			if (num == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;

			}
			if (num == 1)
				insertArticle();

			else if (num == 2)
				selectArticle();

			else if (num == 3)
				deleteArticle();

			else
				System.out.println("1번부터 4번까지만 입력하시오");
		} // while
	}

	// 3
	private void deleteArticle() {
		System.out.println("삭제를 원하는 이름 입력 : ");
		String name = scan.next();

		getConnection();
		String sql = "DELETE FROM STUDENT2 WHERE NAME=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			int result = pstmt.executeUpdate();
			System.out.println(result + "행이 삭제되었습니다");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	// 2
	private void selectArticle() {

		while (true) {
			System.out.println("****************");
			System.out.println("1. 이름 검색(1개 글자가 포함된 이름은 모두 검색");
			System.out.println("2. 전체 검색");
			System.out.println("3. 이전메뉴");
			System.out.println("번호 선택 : ");
			int num = scan.nextInt();

			if (num == 3)
				break;

			// 데이터
			String name = null;
			if (num == 1) {
				System.out.println("검색할 이름 입력 : ");
				name = scan.next();
			}

			// DB - select
			getConnection();

			String sql = null;
			if (num == 1)
				sql = "SELECT * FROM STUDENT2 WHERE NAME LIKE ?";
			else if (num == 2)
				sql = "SELECT * FROM STUDENT2";

			try {
				pstmt = conn.prepareStatement(sql);

				if (num == 1)
					pstmt.setString(1, "%" + name + "%");
				System.out.println("검색 되었습니다.");

				rs = pstmt.executeQuery();

				while (rs.next()) {
					System.out.print("이름 = " + rs.getString("name") + "\t");

					if (rs.getInt("code") == 1)
						System.out.println("학번 = " + rs.getString("value"));
					else if (rs.getInt("code") == 2)
						System.out.println("과목 = " + rs.getString("value"));
					else if (rs.getInt("code") == 3)
						System.out.println("부서 = " + rs.getString("value"));

				} // while
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}

			}
		}
	}

	// 1
	private void insertArticle() {
		int num;
		while (true) {
			System.out.println("****************");
			System.out.println("1. 학생");
			System.out.println("2. 교수");
			System.out.println("3. 관리자");
			System.out.println("4. 이전메뉴");
			System.out.print("번호 선택 : ");
			num = scan.nextInt();

			if (num == 4)
				break;

			// 데이터
			System.out.println();
			String name, value = null;
			System.out.print("이름입력 : ");
			name = scan.next();
			if (num == 1) {
				System.out.print("학번입력 : ");
				value = scan.next();
			} else if (num == 2) {
				System.out.print("과목입력 : ");
				value = scan.next();
			} else if (num == 3) {
				System.out.print("부서 입력 : ");
				value = scan.next();
			} else {
				System.out.println("1번부터 4번까지만 입력하시오");
			}

			// DB - insert
			getConnection(); // 접속
			String sql = "INSERT INTO STUDENT2 VALUES(?,?,?)";
			try {
				pstmt = conn.prepareStatement(sql);// 생성

				pstmt.setString(1, name);
				pstmt.setString(2, value);
				pstmt.setInt(3, num);

				int result = pstmt.executeUpdate();// 실행
				System.out.println(result + "행이 추가되었습니다.");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // while
	}

	public static void main(String[] args) {
		Student st = new Student();
		st.menu();
	}

}
