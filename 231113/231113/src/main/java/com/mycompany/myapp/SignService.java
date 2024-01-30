package com.mycompany.myapp;

import java.util.List;

import javax.naming.AuthenticationException;

public interface SignService {
    MemberDto signLogin(String userid, String password) throws AuthenticationException;

    int getMaxSeq();

    int signWrite(BoardDto board);

    int signWriteUpd(BoardDto board);
    
    List<BoardDto> signList(String userId, String userName, String userRank);

    BoardDto signDetail(int seq);

	List<BoardDto> signSearch(String userId, String userRank, String searchOption, String searchKeyword, String searchStatus, String startDate,
			String endDate);

	int signHistory(BoardDto board);

	List<HistoryDto> historyList(int Boardseq);

}
