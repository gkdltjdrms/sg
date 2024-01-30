package service;

import java.util.List;
import model.Board;
import model.History;
import model.User;

public interface PostService {
    int findId(String id);
    int checkPassword(String id, String pwd);
    User getUserInfo(String id);
    int findSeq();
    int submitWriteForm(String id, String subject, String content);
    void savesubmitWriteForm(String id, String subject, String content);
    List<Board> getBoardList(String id, String payOption, String userRank, String searchType,
            String searchKeyword, String startDate, String endDate);
	Board getBoardBySeq(int seq);
	void updateBoard(int seq, String id, String subject, String content);
	String getUserNameById(String checkName);
	void submitHistory(String id, int postSeq);
	List<History> getHistoryListByBoardSeq(int seq);
	void saveUpdateBoard(int seq, String id, String subject, String content);
	void checkBoard(int seq, String id, String subject, String content, String payOption, String loginId);
	void submitCheckHistory(String loginId, int postSeq, String payOption);
}
