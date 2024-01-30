package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dao.PostDao;
import model.Board;
import model.History;
import model.User;

import java.util.List;

@Service("PostService")
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public int findId(String id) {
        return postDao.findId(id);
    }

    @Override
    public int checkPassword(String id, String pwd) {
        return postDao.checkPassword(id, pwd);
    }

    @Override
    public User getUserInfo(String id) {
        return postDao.getUserInfo(id);
    }

    @Override
    public int findSeq() {
        return postDao.findSeq();
    }

    @Override
    public int submitWriteForm(String id, String subject, String content) {
        return postDao.submitWriteForm(id, subject, content);
    }

    @Override
    public void savesubmitWriteForm(String id, String subject, String content) {
        postDao.savesubmitWriteForm(id, subject, content);
    }
    
    @Override
	public void updateBoard(int seq, String id, String subject, String content) {
		// TODO Auto-generated method stub
    	 postDao.updateBoard(seq, id, subject, content);
	}
    
    @Override
	public void rejectBoardseq(int seq, String loginId) {
		// TODO Auto-generated method stub
    	 postDao.rejectBoardseq(seq, loginId);
	}
    
    @Override
	public void checkBoard(int seq, String id, String subject, String content, String payOption, String loginId) {
		// TODO Auto-generated method stub
    	 postDao.checkBoard(seq, id, subject, content, payOption, loginId);
	}
    
	@Override
	public void saveUpdateBoard(int seq, String id, String subject, String content) {
		// TODO Auto-generated method stub
		 postDao.saveUpdateBoard(seq, id, subject, content);
	}

    @Override
    public List<Board> getBoardList(String id, String payOption, String userRank, String searchType,
                                    String searchKeyword, String startDate, String endDate) {
        return postDao.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
    }

	@Override
	public Board getBoardBySeq(int seq) {
		// TODO Auto-generated method stub
		return postDao.getBoardBySeq(seq);
	}

	@Override
	public String getUserNameById(String checkName) {
		// TODO Auto-generated method stub
		return postDao.getUserNameById(checkName);
	}

	@Override
	public void submitHistory(String id, int PostSeq) {
		// TODO Auto-generated method stub
		 postDao.submitHistory(id, PostSeq);
	}

	@Override
	public List<History> getHistoryListByBoardSeq(int seq) {
		// TODO Auto-generated method stub
		return postDao.getHistoryListByBoardSeq(seq);
	}

	@Override
	public void submitCheckHistory(String loginId, int postSeq, String payOption) {
		// TODO Auto-generated method stub
		postDao.submitCheckHistory(loginId, postSeq, payOption);
	}

	

	



	
}
