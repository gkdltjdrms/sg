package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectMain {
	private String name;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "c##java";
	private String password = "1234";

	public SelectMain() {
		try {
			Class.forName(driver);// Class타입으로 생성
			System.out.println("driver loading 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("connection 성공 ");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void selectArticle() {
		getConnection(); // 접속

		String sql = "SELECT * FROM DBTEST";

		try {
			pstmt = conn.prepareStatement(sql);// 생성
			rs = pstmt.executeQuery(); // 실행 - ResultSet 리턴

			while (rs.next()) {
				System.out.println(rs.getString("name") + "\t"
						+ rs.getInt("age") + "\t"
						+ rs.getDouble("height") + "\t"
						+ rs.getString("logtime"));
			} // while

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		SelectMain sm = new SelectMain();
		sm.selectArticle();
	}

}