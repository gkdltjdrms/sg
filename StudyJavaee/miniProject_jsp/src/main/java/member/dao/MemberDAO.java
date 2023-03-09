package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.been.MemberDTO;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DataSource ds;
	
	private static MemberDAO memberDAO = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return memberDAO;
	}

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

	public MemberDAO() {
		try {
			Context ctx = new InitialContext(); //생성
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");//Tomcat인 경우에는 java:comp/env/ 붙여야 한다.  
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int memberWrite(MemberDTO memberDTO) {
		int su = 0;
		
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);//생성
			
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
	
	public MemberDTO memberLogin(String id, String pwd){
		MemberDTO memberDTO = null;
		
		String sql = "SELECT * FROM MEMBER where id=? and pwd=?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();//ResultSet 리턴
			
			if(rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return memberDTO;		
	}
	
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = null;
		String sql = "select * from member where id=?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);//생성
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); //ResultSet 리턴
			
			if(rs.next()) {
				memberDTO = new MemberDTO(); //생성
				memberDTO.setName(rs.getString("name"));
				memberDTO.setId(rs.getString("id"));
		        memberDTO.setPwd(rs.getString("pwd"));
		        memberDTO.setGender(rs.getString("gender"));
		        memberDTO.setEmail1(rs.getString("email1"));
		        memberDTO.setEmail2(rs.getString("email2"));
		        memberDTO.setTel1(rs.getString("tel1"));
		        memberDTO.setTel2(rs.getString("tel2"));
		        memberDTO.setTel3(rs.getString("tel3"));
		        memberDTO.setZipcode(rs.getString("zipcode"));
		        memberDTO.setAddr1(rs.getString("addr1"));
		        memberDTO.setAddr2(rs.getString("addr2"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return memberDTO;
	}

	public void memberUpdate(MemberDTO memberDTO) {
		String sql = "update member set name=?"
								   + ", pwd=?"
								   + ", gender=?"
								   + ", email1=?"
								   + ", email2=?"
								   + ", tel1=?"
								   + ", tel2=?"
								   + ", tel3=?"
								   + ", zipcode=?"
								   + ", addr1=?"
								   + ", addr2=?"
								   + ", logtime=sysdate"
								   + " where id=?";
		
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getPwd());
		    pstmt.setString(3, memberDTO.getGender());
		    pstmt.setString(4, memberDTO.getEmail1());
		    pstmt.setString(5, memberDTO.getEmail2());
		    pstmt.setString(6, memberDTO.getTel1());
		    pstmt.setString(7, memberDTO.getTel2());
		    pstmt.setString(8, memberDTO.getTel3());
		    pstmt.setString(9, memberDTO.getZipcode());
		    pstmt.setString(10, memberDTO.getAddr1());
		    pstmt.setString(11, memberDTO.getAddr2());
		    pstmt.setString(12, memberDTO.getId());
		    
		    pstmt.executeUpdate();//개수 리턴
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MemberDAO.close(conn, pstmt);
		}
	}
	
	public boolean isExistPwd(String id, String pwd){
		boolean exist = false;
		String sql = "select * from member where id=? and pwd=?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) exist = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt, rs);
		}
		
		return exist;
	}
	
	public void memberDelete(String id) {
		String sql = "delete member where id=?";
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MemberDAO.close(conn, pstmt);
		}
	}
}



















