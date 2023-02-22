package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertMain {
	private Connection conn;
	private PreparedStatement pstmt;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String ur1 = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "C##JAVA";
	private String password = "1234";

	public InsertMain() {
		try {
			Class.forName(driver);// Class타입으로 생성
			System.out.println("driver loading 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(ur1, username, password);
			System.out.println("connection 성공 ");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void insertArticle() {
		Scanner scan = new Scanner(System.in);
		System.out.println("이름 입력 : ");
		String name = scan.next();
		System.out.println("나이 입력 : ");
		int age = scan.nextInt();
		System.out.println("키 입력 : ");
		double height = scan.nextDouble();
		// ------------------------

		this.getConnection();

		String sql = "INSERT INTO DBTEST VALUES(?, ?, ?, sysdate)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);

			int su = pstmt.executeUpdate();// 실행 - 개수가 리턴되서 나온다.
			System.out.println(su + "행 이 만들어 졌습니다.");

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

	public static void main(String[] args) {
		InsertMain im = new InsertMain();
		im.insertArticle();

	}

}
