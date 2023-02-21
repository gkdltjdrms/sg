package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMain {

	public static void main(String[] args) {
		// Connection, PreparedStatement, ResultSet 객체를 선언한다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);

		System.out.print("검색할 이름 입력: ");
		String name = sc.nextLine();

		try {
			// Oracle JDBC 드라이버를 로딩한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver load 성공");
			// Connection 객체를 생성한다.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##java", "1234");
			System.out.println("connection 성공");
			// SQL문을 작성한다.
			String sql = "UPDATE DBTEST SET age = age + 1, height = height + 1 WHERE name LIKE ?";
			// PreparedStatement 객체를 생성한다.
			pstmt = conn.prepareStatement(sql);
			// PreparedStatement 객체의 물음표에 값을 설정한다.
			pstmt.setString(1, "%" + name + "%");
			// SQL문을 실행하고 수정된 레코드의 수를 반환한다.
			int result = pstmt.executeUpdate();
			// 결과를 출력한다.
			if(result == 0 )System.out.println("검색된 이름이 없습니다."); //결과가 0 일시 출력
			System.out.println("수정된 레코드 수: " + result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체들을 닫는다.
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
/*
 * 검색 할 이름 입력 : 홍
 * 
 * 홍이 들어간 레코드를 나이를 1증가, 키도 1증가 하시오
 * 
 */