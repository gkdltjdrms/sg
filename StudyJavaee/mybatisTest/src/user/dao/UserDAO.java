package user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import user.bean.UserDTO;

public class UserDAO {
	private SqlSessionFactory sqlsessionfactory;
	private static UserDAO userDAO = new UserDAO();
	
	public static UserDAO getInstance() {
		return userDAO;
	}

	
	public UserDAO() {
//		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		InputStream inputstream;
		try {
			inputstream = Resources.getResourceAsStream("mybatis-config.xml");
			
			sqlsessionfactory = new SqlSessionFactoryBuilder().build(inputstream);
																										
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void write(UserDTO userDTO) {
		SqlSession sqlsession = sqlsessionfactory.openSession(); //생성
		sqlsession.insert("userSQL.write", userDTO);
		sqlsession.commit();
		sqlsession.close();
		}


	public List<UserDTO> getUserList() {
		SqlSession sqlsession = sqlsessionfactory.openSession(); //생성
		List<UserDTO> list = sqlsession.selectList("userSQL.getUserList");
		sqlsession.close();
		return list;
	}

	  public void update(UserDTO userDTO) {
		    SqlSession sqlSession = sqlsessionfactory.openSession();
		    sqlSession.update("userSQL.update", userDTO);
		    sqlSession.commit();
		    sqlSession.close();
		}


	public UserDTO getUser(String id) {
		 SqlSession sqlSession = sqlsessionfactory.openSession();
	        UserDTO userDTO = sqlSession.selectOne("userSQL.getUser", id);
	        sqlSession.close();
	        return userDTO;
	}


	public void update(Map<String, String> map) {
		 SqlSession sqlSession = sqlsessionfactory.openSession();
		    sqlSession.update("userSQL.update", map);
		    sqlSession.commit();
		    sqlSession.close();
		
	}


	public void delete(String id) {
		 SqlSession sqlSession = sqlsessionfactory.openSession();
	        UserDTO userDTO = sqlSession.selectOne("userSQL.delete", id);
	        sqlSession.close();
		
	}
	
	

	public List<UserDTO> searchByName(String name) {
	    SqlSession sqlSession = sqlsessionfactory.openSession();
	    List<UserDTO> list = sqlSession.selectList("userSQL.searchByName", name);
	    sqlSession.close();
	    return list;
	}

	public UserDTO searchById(String id) {
	    SqlSession sqlSession = sqlsessionfactory.openSession();
	    UserDTO userDTO = sqlSession.selectOne("userSQL.searchById", id);
	    sqlSession.close();
	    return userDTO;
	}
	
	

}



