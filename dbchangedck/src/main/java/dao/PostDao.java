package dao;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.apache.ibatis.session.SqlSession;

import model.BoardWrite;
import model.post;

@Repository
public class PostDao {
    
    @Autowired
    private SqlSession sqlSession; // SqlSession을 주입합니다.

    @Autowired
    private JdbcTemplate jdbcTemplate; // 데이터베이스와 상호 작용하는 JdbcTemplate을 주입합니다.

    public List<post> getAllPosts() {
        System.out.println("dao 넘어옴");
        return sqlSession.selectList("mapper.BoardMapper.getPost");
    }


    public void savePost(BoardWrite newPost) {
        // SQL 쿼리를 작성 (서브쿼리로 직전 seq 값 가져와서 1 증가)
        String sql = "INSERT INTO board_study (seq, mem_name, mem_id, board_subject, board_content, reg_date) " +
                     "VALUES ((SELECT MAX(seq) FROM board_study) + 1, ?, ?, ?, ?, ?)";

        String memName = newPost.getMem_name();
        String memId = newPost.getMem_id();
        String boardSubject = newPost.getBoard_subject();
        String boardContent = newPost.getBoard_content();
        Date regDate = newPost.getReg_date();

        // 게시글을 데이터베이스에 저장
        jdbcTemplate.update(sql, memName, memId, boardSubject, boardContent, new java.sql.Date(regDate.getTime()));
    }


	public void updatePost(post existingPost) {
		// TODO Auto-generated method stub
		sqlSession.update("updatePost", existingPost);
	}


	public post getPostBySeq(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.BoardMapper.getPostBySeq", seq);
	}

}
