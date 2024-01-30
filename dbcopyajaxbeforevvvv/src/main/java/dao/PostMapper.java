/*
 * package dao;
 * 
 * import org.springframework.jdbc.core.RowMapper; import model.post; import
 * java.sql.ResultSet; import java.sql.SQLException;
 * 
 * public class PostMapper implements RowMapper<post> {
 * 
 * @Override public post mapRow(ResultSet rs, int rowNum) throws SQLException {
 * post post = new post(); post.setSeq(rs.getInt("seq"));
 * post.setMem_id(rs.getString("mem_id"));
 * post.setBoard_subject(rs.getString("board_subject"));
 * post.setBoard_content(rs.getString("board_content"));
 * post.setReg_date(rs.getDate("reg_date"));
 * post.setUpt_date(rs.getDate("upt_date"));
 * post.setView_cnt(rs.getInt("view_cnt")); return post; }
 * 
 * 
 */

 