package com.mycompany.myapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignDaoImpl implements SignDao {
    Logger logger = LoggerFactory.getLogger(SignDaoImpl.class);
    private final SqlSession sqlSession;

    @Autowired
    public SignDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    
    // 濡쒓렇�씤
    @Override
    public MemberDto signLogin(String userid) {
        logger.info("SignDaoImpl: signLogin");
        return sqlSession.selectOne("selectMemberByUserId", userid);
    }

    // 寃뚯떆湲� 理쒕� 踰덊샇
    @Override
    public int getMaxSeq() {
        return sqlSession.selectOne("getMaxSeq");
    }

    // 湲��벐湲�
    @Override
    public int signWrite(BoardDto board) {
        logger.info("SignDaoImpl: signWrite");
        return sqlSession.insert("selectInsert", board);
    }
    
    // 湲��닔�젙
    @Override
    public int signWriteUpd(BoardDto board) {
        logger.info("SignDaoImpl: signWriteUpd");

        try {
            int result = sqlSession.update("selectUpdate", board);
            logger.info("Update result: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("Error updating board", e);
            throw new RuntimeException("Error updating board", e);
        }
    }

    // 寃뚯떆湲� �쟾泥� 由ъ뒪�듃
    @Override
    public List<BoardDto> signList(String userId, String userName, String userRank) {
        logger.info("SignDaoImpl: signList");

        Map<String, String> parameters = new HashMap<>();
        
        parameters.put("userId", userId);
        parameters.put("userName", userName);
        parameters.put("userRank", userRank);

        //logger.info("userId: {}", userId);
        //logger.info("userName: {}", userName);
        //logger.info("userRank: {}", userRank);
        return sqlSession.selectList("selectList", parameters);
    }

    // 寃뚯떆湲� �긽�꽭 議고쉶
    @Override
    public BoardDto signDetail(int seq) {
        logger.info("SignDaoImpl: signDetail");
        return sqlSession.selectOne("selectDetail", seq);
    }

    // 寃뚯떆湲� 寃��깋
	@Override
	public List<BoardDto> signSearch(String userId, String userRank, String searchOption, String searchKeyword, String searchStatus, String startDate,
			String endDate) {
		logger.info("SignDaoImpl: signSearch");

		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		searchMap.put("userRank", userRank);
		searchMap.put("searchOption", searchOption);
		searchMap.put("searchKeyword", searchKeyword);
		searchMap.put("searchStatus", searchStatus);
		searchMap.put("startDate", startDate);
		searchMap.put("endDate", endDate);
		return sqlSession.selectList("selectSearch", searchMap);
	}

	// 寃뚯떆湲� 寃곗옱 �긽�깭 �엳�뒪�넗由�
	@Override
	public int signHistory(BoardDto board) {
		logger.info("SignDaoImpl: signHistory");
		return sqlSession.insert("selectHistoryInsert", board);
	}


	@Override
	public List<HistoryDto> historyList(int boardSeq) {
		logger.info("SignDaoImpl: historyList");
		return sqlSession.selectList("selectHistoryList", boardSeq);
	}


}
