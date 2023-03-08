package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import board.been.BoardDTO;


public class BoardDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "C##JAVA";
	private String password = "1234";
	
	private static BoardDAO boardDAO = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return boardDAO;
	}
	
	
	public static void close(Connection conn, PreparedStatement pstmt) {//구현
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
	
	public BoardDAO() {
		try {
			Class.forName(driver); //Class타입으로 생성
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
	
	public void boardWrite(Map<String, String> map) {
		String sql = "insert into board(seq,id,name,email,subject,content,ref)"
				+ " values(seq_board.nextval,?,?,?,?,?,seq_board.currval)";
		
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("id"));
			pstmt.setString(2, map.get("name"));
			pstmt.setString(3, map.get("email"));
			pstmt.setString(4, map.get("subject"));
			pstmt.setString(5, map.get("content"));
			
			pstmt.executeUpdate();//실행 - 개수 리턴
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardDAO.close(conn, pstmt);
		}
	}
	
	public List<BoardDTO> boardList(int startNum, int endNum) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		String sql = "  select * from (select rownum rn, aa.* from (select * from board order by ref desc, step asc) aa) where rn>=? and rn<=?";
		getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);//6,10
			pstmt.setInt(2, endNum);
			
			
			rs = pstmt.executeQuery();//실행-ResultSet 리턴
		
			while(rs.next()) {
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setSeq(rs.getInt("seq"));
				boardDTO.setId(rs.getString("id"));
				boardDTO.setName(rs.getString("name"));
				boardDTO.setEmail(rs.getString("email"));
				boardDTO.setSubject(rs.getString("subject"));
				boardDTO.setContent(rs.getString("content"));
				boardDTO.setRef(rs.getInt("ref"));
				boardDTO.setLev(rs.getInt("lev"));
				boardDTO.setStep(rs.getInt("step"));
				boardDTO.setPseq(rs.getInt("pseq"));
				boardDTO.setReply(rs.getInt("reply"));
				boardDTO.setHit(rs.getInt("hit"));
				boardDTO.setLogtime(rs.getDate("logtime"));
				
				list.add(boardDTO);
			}//while
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally {
			BoardDAO.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	
	public int getTotalA() {
	    int totalA = 0;
	    String sql = "SELECT COUNT(*) FROM BOARD";
	    getConnection();
	    try {
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        rs.next();
	        totalA = rs.getInt(1);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	BoardDAO.close(conn, pstmt, rs);
	    }
	    
	    return totalA;
	}
	
	
	
}



















