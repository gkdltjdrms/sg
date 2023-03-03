package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.been.MemberDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "C##JAVA";
	private String password = "1234";
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static MemberDAO memberDAO = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return memberDAO;
	}
	
	public MemberDAO() { // driver loading

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loading 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Class타입으로 생성한다는 의미임. 풀 쿼리 네임으로 생성해야 함.(패키지명.클래스명)
		// 뒤에 .class 안적어도 됨. 생성자 안에서는 절대 접속하면 안 됨.
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JAVA", "1234");
			System.out.println("connection 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int memberWrite(MemberDTO memberDTO) {
		int su = 0;
		
		this.getConnection(); // 접속
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(sql); // 생성

		
		//?에 데이터 주입
		
		pstmt.setString(1, memberDTO.getName());
		pstmt.setString(2, memberDTO.getId());
		pstmt.setString(3, memberDTO.getPwd());
		pstmt.setString(4, memberDTO.getGender());
		pstmt.setString(5, memberDTO.getEmail1());
		pstmt.setString(6, memberDTO.getEmail2());
		pstmt.setString(7, memberDTO.getTel1());
		pstmt.setString(8, memberDTO.getTel2());
		pstmt.setString(9, memberDTO.getTel3());
		pstmt.setString(10, memberDTO.getZipcode());
		pstmt.setString(11, memberDTO.getAddr1());
		pstmt.setString(12, memberDTO.getAddr2());
		
		su = pstmt.executeUpdate(); //실행 - 개수 리턴
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
		}
		
		return su;
		
	}

	public String memberLogin(String id, String pwd){
		String name = null;
		String sql = "select * from member where id=? and pwd=?";
		getConnection(); //접속
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery(); //ResultSet 리턴
			
			if (rs.next()) {
				name = rs.getString("name");
				id = rs.getString("id");
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}	
		return name;		
	}
	
	
	
	
	
}