package dao;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import model.BoardWrite;
import model.SearchCriteria;
import model.post;
import oracle.jdbc.proxy.annotation.Post;

@Repository
public class PostDao {
    
    @Autowired
    private SqlSession sqlSession; // SqlSession을 주입합니다.

    @Autowired
    private JdbcTemplate jdbcTemplate; // 데이터베이스와 상호 작용하는 JdbcTemplate을 주입합니다.




	/*
	 * public void savePost(BoardWrite newPost) { // SQL 쿼리를 작성 (서브쿼리로 직전 seq 값 가져와서
	 * 1 증가) String sql =
	 * "INSERT INTO board_study (seq, mem_name, mem_id, board_subject, board_content, reg_date) "
	 * + "VALUES ((SELECT MAX(seq) FROM board_study) + 1, ?, ?, ?, ?, ?)";
	 * 
	 * String memName = newPost.getMem_name(); String memId = newPost.getMem_id();
	 * String boardSubject = newPost.getBoard_subject(); String boardContent =
	 * newPost.getBoard_content(); Date regDate = newPost.getReg_date();
	 * 
	 * // 게시글을 데이터베이스에 저장 jdbcTemplate.update(sql, memName, memId, boardSubject,
	 * boardContent, new java.sql.Date(regDate.getTime())); }
	 */
    
    public void savePost(BoardWrite newPost) {
        sqlSession.insert("mapper.BoardMapper.savePost", newPost);
    }


	public void updatePost(post existingPost) {
		// TODO Auto-generated method stub
		sqlSession.update("updatePost", existingPost);
	}


	public post getPostBySeq(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.getPostBySeq", seq);
	}


	public void deletePost(Long seq) {
		 sqlSession.delete("mapper.BoardMapper.deletePost", seq);
	        // TODO: 예외 처리 등을 고려하세요.
	}


	public void deleteSelectPost(Long postId) {
		// TODO Auto-generated method stub
		 sqlSession.delete("mapper.BoardMapper.deletePost", postId);
		
	}


	public List<Post> searchPosts(String keyword) {
		
	    return sqlSession.selectList("mapper.BoardMapper.searchPosts", keyword);
	}

   
	
	public List<post> searchPostsByTitle(String keyword) {
	    System.out.println(keyword + " 키워드 변수값 확인");
	    // TODO Auto-generated method stub
	    Map<String, Object> parameter = new HashMap<>();
	    parameter.put("keyword", keyword);
	    return sqlSession.selectList("mapper.BoardMapper.searchPostsByTitle", parameter);
	}


	public List<post> searchPostByTitleAndAuthor(String keyword) {
		  System.out.println(keyword + " 키워드 변수값 확인 이곳은 제목++");
		// TODO Auto-generated method stub
		 Map<String, Object> parameter = new HashMap<>();
		    parameter.put("keyword", keyword);
		    return sqlSession.selectList("mapper.BoardMapper.searchPostsByTitleAndAuthor", parameter);
	}


	public List<post> searchPostById(String keyword) {
		  System.out.println(keyword + " 키워드 변수값 확인 이곳은 작성자만");
			// TODO Auto-generated method stub
			 Map<String, Object> parameter = new HashMap<>();
			    parameter.put("keyword", keyword);
			    return sqlSession.selectList("mapper.BoardMapper.searchPostsById", parameter);
	}
	
/*
	public List<post> getPostList(int offset, int pageSize) {
		System.out.println(offset + "||" + pageSize + "변수 체킹중 위치확인");

		  Map<String, Object> params = new HashMap<>();
	        params.put("startRow", offset);
	        params.put("endRow", offset + pageSize);
	        return sqlSession.selectList("mapper.BoardMapper.getPostList", params);
	        
	}


	public int getTotalPosts() {
		// TODO Auto-generated method stub
		  return sqlSession.selectOne("mapper.BoardMapper.getTotalPosts");
	}
*/
	
	public List<Post> getPostList(int page, int pageSize, String keyword, String searchOption, String startDate, String endDate) {
	    int startRow = (page - 1) * pageSize + 1;
	    int endRow = page * pageSize;

	    Map<String, Object> params = new HashMap<>();
	    params.put("startRow", startRow);
	    params.put("endRow", endRow);
	    params.put("keyword", keyword);
	    params.put("searchOption", searchOption);
	    params.put("startDate", startDate);
	    params.put("endDate", endDate);

	    System.out.println(startRow + " || " + endRow + " 변수 체킹 map");

	    return sqlSession.selectList("mapper.BoardMapper.getPostList", params);
	}






	
	


	/*
	public List<post> searchPost(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		 return sqlSession.selectList("mapper.BoardMapper.searchPosts", searchMap);
	}
	*/
	public int getTotalPosts(String keyword, String searchOption) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", keyword);
	    params.put("searchOption", searchOption);
	    return sqlSession.selectOne("mapper.BoardMapper.getTotalPosts", params);
	}

	// postDao의 메서드 예시
	public List<post> searchPost(SearchCriteria searchCriteria) {
		System.out.println(searchCriteria.getKeyword()+"변수 찍어볼텨 키워드");
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", searchCriteria.getKeyword());
	    // 나머지 검색 조건에 대한 처리
	    return sqlSession.selectList("mapper.BoardMapper.searchPost", params);
	}

	public int getTotalPosts(SearchCriteria searchCriteria) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("keyword", searchCriteria.getKeyword());
	    // 나머지 검색 조건에 대한 처리
	    return sqlSession.selectOne("mapper.BoardMapper.getTotalPosts", params);
	}

	 public List<post> getAllPosts() {
	        System.out.println("dao 이제부터 여기에서  search 도 만들예정 넘어옴");
	        return sqlSession.selectList("mapper.BoardMapper.getPost");
	    }
	

}
