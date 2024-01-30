package com.mycompany.myapp;

import java.util.List;

public interface SignDao {
    MemberDto signLogin(String userid);

    int getMaxSeq();

    int signWrite(BoardDto board);
    
    int signWriteUpd(BoardDto board);

    List<BoardDto> signList(String userId, String userName, String userRank);

    BoardDto signDetail(int seq);

	List<BoardDto> signSearch(String userId, String userRank, String searchOption, String searchKeyword, String searchStatus, String startDate,
			String endDate);

	int signHistory(BoardDto board);

	List<HistoryDto> historyList(int boardSeq);

}
