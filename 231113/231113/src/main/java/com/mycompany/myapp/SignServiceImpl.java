package com.mycompany.myapp;

import java.util.List;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
    private static final Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    @Autowired
    private SignDao signDao;

    // 濡쒓렇�씤 �쑀�슚�꽦
    @Override
    public MemberDto signLogin(String userid, String password) throws AuthenticationException {
        logger.info("SignServiceImpl: signLogin");

        // �궗�슜�옄 議고쉶
        MemberDto member = signDao.signLogin(userid);

        if (member == null) {
            throw new AuthenticationException("�벑濡앸릺吏� �븡�� �궗�슜�옄");
        }

        // 鍮꾨�踰덊샇 寃�利�
        if (!password.equals(member.getPw())) {
            throw new AuthenticationException("鍮꾨�踰덊샇 遺덉씪移�");
        }

        return member;
    }
    
    // 寃뚯떆湲� 理쒕� 踰덊샇
    @Override
    public int getMaxSeq() {
        return signDao.getMaxSeq();
    }

    // 湲��벐湲�
    @Override
    public int signWrite(BoardDto board) {
        logger.info("SignServiceImpl: signWrite");
        return signDao.signWrite(board);
    }

    // 湲��닔�젙
    @Override
    public int signWriteUpd(BoardDto board) {
    	logger.info("SignServiceImpl: signWriteUpd");
    	return signDao.signWriteUpd(board);
    }
    
    // 寃뚯떆湲� �쟾泥� 由ъ뒪�듃
    @Override
    public List<BoardDto> signList(String userId, String userName, String userRank) {
        logger.info("SignServiceImpl: signList");
        return signDao.signList(userId, userName, userRank);
    }

    // 寃뚯떆湲� �긽�꽭 議고쉶
    @Override
    public BoardDto signDetail(int seq) {
        logger.info("SignServiceImpl: signDetail");
        return signDao.signDetail(seq);
    }

    // 寃뚯떆湲� 寃��깋
	@Override
	public List<BoardDto> signSearch(String userId, String userRank, String searchOption, String searchKeyword, String searchStatus, String startDate,
			String endDate) {
		logger.info("SignServiceImpl: signSearch");
		return signDao.signSearch(userId, userRank, searchOption, searchKeyword, searchStatus, startDate, endDate);
	}

	// 寃뚯떆湲� 寃곗옱 �긽�깭 �엳�뒪�넗由�
	@Override
	public int signHistory(BoardDto board) {
		logger.info("SignServiceImpl: signHistory");
		return signDao.signHistory(board);
	}

	@Override
	public List<HistoryDto> historyList(int boardSeq) {
		logger.info("SignServiceImpl: historyList");
		return signDao.historyList(boardSeq);
	}


}
