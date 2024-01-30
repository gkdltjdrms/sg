package dao;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import model.Board;
import model.BoardWrite;
import model.SearchCriteria;
import model.User;
import model.post;
import oracle.jdbc.proxy.annotation.Post;

@Repository
public class PostDao {
    
    @Autowired
    private SqlSession sqlSession; // SqlSession을 주입합니다.



	public int findId(String id) {
		
		
		  return sqlSession.selectOne("mapper.BoardMapper.findId", id);
	}


	public int checkPassword(String id, String pwd) {
	    Map<String, String> parameters = new HashMap<>();
	    parameters.put("id", id);
	    parameters.put("pwd", pwd);
	    
	    return sqlSession.selectOne("mapper.BoardMapper.checkPassword", parameters);
	}


	public User getUserInfo(String id) {
		// TODO Auto-generated method stub
		 return sqlSession.selectOne("mapper.BoardMapper.getUserInfo", id);
	}




	public int getSeqValue() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.getSeqValue");
	}


	public int findSeq() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.findSeq");
	}


	public void submitWriteForm(String id, String subject, String content) {
	    Board board = new Board();
	    board.setId(id);
	    board.setSubject(subject);
	    board.setContent(content);
	    board.setRegDate(new Date()); // 작성일 설정
	    sqlSession.insert("mapper.BoardMapper.insertBoard", board);
	}


	public void savesubmitWriteForm(String id, String subject, String content) {
		// TODO Auto-generated method stub
		  Board board = new Board();
		    board.setId(id);
		    board.setSubject(subject);
		    board.setContent(content);
		    board.setRegDate(new Date()); // 작성일 설정
		    sqlSession.insert("mapper.BoardMapper.saveBoard", board);
	}
	


	public List<Board> getBoardList(
					@Param("id") String id,
					@Param("payOption") String payOption,
					@Param("userRank") String userRank,
					@Param("searchType") String searchType,
					@Param("searchKeyword") String searchKeyword,
					@Param("startDate") String startDate,
					@Param("endDate") String endDate
			) {
			
			    Map<String, Object> paramMap = new HashMap<>();
			    paramMap.put("id", id);
			    paramMap.put("userRank", userRank);
			    paramMap.put("payOption", payOption);
			    paramMap.put("searchType", searchType);
			    paramMap.put("searchKeyword", searchKeyword);
			    paramMap.put("startDate", startDate);
			    paramMap.put("endDate", endDate);
			    System.out.println(payOption+"검색옵션확인");
			    return sqlSession.selectList("mapper.BoardMapper.getBoardList", paramMap);
	}






	

}
