package com.mycompany.myapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignController {
	Logger logger = LoggerFactory.getLogger(SignController.class);
	
	@Autowired
	SignService signService;
	
    // 濡쒓렇�씤 �럹�씠吏� 濡쒕뵫
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLoginPage() {
        return "signLogin";
    }
    
    // 寃곗옱 湲��벐湲� �럹�씠吏� 濡쒕뵫
    @RequestMapping(value = "/signWrite", method = RequestMethod.POST)
    public String signWritePage(HttpSession session, Model model) {
        // �옉�꽦�옄 �씠由� 媛��졇���꽌 �솕硫� 異붽�
    	String userId = (String) session.getAttribute("ID");
    	String userRank = (String) session.getAttribute("RANK");
        String userName = (String) session.getAttribute("NAME");
        model.addAttribute("userId", userId);
        model.addAttribute("userRank", userRank);
        model.addAttribute("userName", userName);
        // 理쒕� SEQ 媛믪쓣 媛��졇���꽌 紐⑤뜽�뿉 異붽�
        int maxSeq = signService.getMaxSeq();
        model.addAttribute("maxSeq", maxSeq);

        return "signWrite";
    }
    
    // 濡쒓렇�씤 �쑀�슚�꽦
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signLogin(
            @RequestParam("userid") String userid,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        logger.info("SignController: signLogin");
        try {
            // 濡쒓렇�씤 �꽦怨� �떆 �궗�슜�옄 �젙蹂� 議고쉶
            MemberDto member = signService.signLogin(userid, password);

            // �꽭�뀡�뿉 �궗�슜�옄 �젙蹂� ���옣
            session.setAttribute("ID", member.getId());
            session.setAttribute("NAME", member.getName());
            session.setAttribute("RANK", member.getRank());

            // �꽦怨� �럹�씠吏�濡� 由щ뵒�젆�뀡
            return "redirect:/main";
        } catch (AuthenticationException e) {
            // �삤瑜� 硫붿떆吏�瑜� 紐⑤뜽�뿉 異붽�
            model.addAttribute("error", e.getMessage());
            // 濡쒓렇�씤 �럹�씠吏�濡� �씠�룞
            return "signLogin";
        }
    }
    
    // 濡쒓렇�븘�썐
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String signLogout(HttpSession session) {
        session.invalidate();
        return "signLogin";
    }
    
    // 硫붿씤 �럹�씠吏� (�쟾泥� 由ъ뒪�듃 異쒕젰)
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String signMain(HttpSession session, Model model) {
        logger.info("SignController: signMain");

        // �꽭�뀡�뿉�꽌 �궗�슜�옄 �씠由� 媛��졇�삤湲�
        String userId = (String) session.getAttribute("ID");
        String userName = (String) session.getAttribute("NAME");
        String userRank = (String) session.getAttribute("RANK");
        // �궗�슜�옄�쓽 湲� 紐⑸줉�쓣 媛��졇�� 紐⑤뜽�뿉 異붽�
        List<BoardDto> sList = signService.signList(userId, userName, userRank);
        model.addAttribute("sList", sList);

        // �씠 硫붿냼�뱶�뿉�꽌 異붽��쟻�씤 硫붿씤 �럹�씠吏� 濡쒖쭅�쓣 泥섎━�븷 �닔 �엳�쓬
        return "signMain";
    }
    
    // 寃��깋 �샃�뀡�뿉 �뵲瑜� 寃��깋 由ъ뒪�듃 異쒕젰 (�룞湲곗떇)
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String signSearch (
    		@RequestParam(value = "search-options", required = false) String searchOption,
    		@RequestParam(value = "search-keyword", required = false) String searchKeyword,
    		@RequestParam(value = "search-status-options", required = false) String searchStatus,
    		@RequestParam(value = "start-date", required = false) String startDate,
    		@RequestParam(value = "end-date", required = false) String endDate,
    		HttpSession session,
    		Model model) {
    	//logger.info("SignController: signSearch");
        //logger.info("searchOption: {}", searchOption);
        //logger.info("searchKeyword: {}", searchKeyword);
        //logger.info("searchStatus: {}", searchStatus);
        //logger.info("startDate: {}", startDate);
        //logger.info("endDate: {}", endDate);
        
        String userId = (String) session.getAttribute("ID");
        String userRank = (String) session.getAttribute("RANK");
        
    	List<BoardDto> searchResults = signService.signSearch(userId, userRank, searchOption, searchKeyword, searchStatus, startDate, endDate);
    	model.addAttribute("searchResults", searchResults);
    	
    	return "signMain";
    }
    
    // 寃곗옱 �샃�뀡�뿉 �뵲瑜� 由ъ뒪�듃 異쒕젰(鍮꾨룞湲곗떇)
    @ResponseBody
    @RequestMapping(value = "/statuslist", method = RequestMethod.GET, produces = "application/json")  
    public Map<String, Object> signStatusList(@RequestParam("signStatus") String signStatus, HttpSession session) {
        logger.info("SignController: signStatusList");
        
        String userId = (String) session.getAttribute("ID");
        String userName = (String) session.getAttribute("NAME");
        String userRank = (String) session.getAttribute("RANK");

        List<BoardDto> sList = signService.signList(userId, userName, userRank);

        // �꽑�깮�맂 �긽�깭�뿉 �뵲�씪 �븘�꽣留곷맂 寃곌낵
        List<BoardDto> statusList = new ArrayList<>();
        for (BoardDto post : sList) {
            if (signStatus.equals(post.getSignStatus())) {
                statusList.add(post);
            }
        }

        // 寃곌낵瑜� Map�뿉 �떞�븘 諛섑솚
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("signStatus", signStatus);
        resultMap.put("statusData", statusList);
        logger.info(signStatus);
        
        return resultMap;
    }

    // 湲��벐湲�
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String signWrite(@RequestParam("subject") String subject,
                            @RequestParam("content") String content,
                            @RequestParam("action") String action,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        logger.info("SignController: signWrite");

        // �궗�슜�옄 �젙蹂� 媛��졇�삤湲�
        String userId = (String) session.getAttribute("ID");
        String userName = (String) session.getAttribute("NAME");
        String userRank = (String) session.getAttribute("RANK");

        BoardDto board = new BoardDto();
        board.setId(userId);
        board.setSubject(subject);
        board.setContent(content);
        board.setRegDate(new Date());

        // "action" 媛믪뿉 �뵲�씪 board�쓽 SignStatus �꽕�젙
        if ("save".equals(action)) {
            board.setSignStatus("SAVE");
            board.setSigner("");
        } else if ("approve".equals(action)) {
            board.setSignStatus("APPROVE");
            board.setSigner(userId);
        } else if ("reject".equals(action)) {
            board.setSignStatus("REJECT");
        } else {
        }

        // board瑜� �꽌鍮꾩뒪濡� �쟾�떖�븯�뿬 泥섎━
        signService.signWrite(board);
        
        // 湲��벑濡� 泥섎━ �썑 �엳�뒪�넗由� �벑濡� 泥섎━
        if ("approve".equals(action)) {
        	int boardSeq = board.getSeq();
        	System.out.println("boardSeq :" + boardSeq);
        	
            BoardDto approveBoard = new BoardDto();
            approveBoard.setSeq(boardSeq);
            approveBoard.setId(board.getId());
            approveBoard.setRegDate(new Date());
            approveBoard.setSignStatus(board.getSignStatus());
            
            signService.signHistory(approveBoard);
        }
        
        // �궗�슜�옄�쓽 湲� 紐⑸줉�쓣 媛��졇���꽌 紐⑤뜽�뿉 異붽�
        List<BoardDto> sList = signService.signList(userId, userName, userRank);
        redirectAttributes.addFlashAttribute("sList", sList);

        // 湲��벐湲곌� �셿猷뚮릺硫� signMain �럹�씠吏�濡� redirect
        return "redirect:/main";
    }
    
    // 게시글 수정
    @RequestMapping(value = "/writeupd", method = RequestMethod.POST)
    public String signWriteUpd(@RequestParam("seq") int seq,
                         @RequestParam("hiddenId") String hiddenId,
                                @RequestParam("subject") String subject,
                                @RequestParam("content") String content,
                                @RequestParam("action") String action,
                                HttpSession session,
                                Model model) {
        logger.info("SignController: signWriteUpd");
        System.out.println("게시글 작성자 ID: " + hiddenId);

        // 사용자 정보 가져오기
        String userId = (String) session.getAttribute("ID");
        String userName = (String) session.getAttribute("NAME");
        String userRank = (String) session.getAttribute("RANK");

        BoardDto board = new BoardDto();
        board.setSeq(seq);
        board.setId(hiddenId);
        board.setLoginId(userId);
        board.setSubject(subject);
        board.setContent(content);
        board.setSignDate(new Date());
        
        System.out.println("현재 사용자 : " + userId);
        System.out.println("게시글 작성자 ID: " + board.getId());
        System.out.println("현재 사용자 RANK :" + userRank);

      // "action" 값에 따라 board의 SignStatus 설정
      // 임시저장 실행
      if ("save".equals(action)) {
         board.setSignStatus("SAVE");
      }

      // 결재 실행
      else if ("approve".equals(action)) {
         // 접속한 id와 게시글 작성자 id 일치 확인
         if (userId.equals(board.getId())) {
            // userRank가 '사원', '대리'인 경우 임시저장 -> 결재요청
            if ("사원".equals(userRank) || "대리".equals(userRank)) {
               board.setSignStatus("APPROVE");
            }
         } else {
            // userRank가 '과장'인 경우 결재요청 -> 결재중
            if ("과장".equals(userRank)) {
               board.setSignStatus("APPROVEWAIT");
            }
            // userRank가 '부장'인 경우 결재중 -> 결재완료
            else if ("부장".equals(userRank)) {
               board.setSignStatus("ACCEPT");
            }
         }
      }

      // 반려 실행
      else if ("reject".equals(action)) {
         if ("과장".equals(userRank) || "부장".equals(userRank)) {
            board.setSignStatus("REJECT");
         } else {
            board.setSignStatus("REJECT");
         }
      } else {
      }
      	
      
        // board를 서비스로 전달하여 처리
        signService.signWriteUpd(board);
        System.out.println("업뎃완료 !");
        signService.signHistory(board);
        // 사용자의 글 목록을 가져와서 모델에 추가
        List<BoardDto> sList = signService.signList(userId, userName, userRank);
        model.addAttribute("sList", sList);

        // 글쓰기가 완료되면 signMain 페이지로 redirect
        return "redirect:/main";
    }
    
    // 寃뚯떆湲� �긽�꽭 議고쉶
    @RequestMapping(value = "/signdatail", method = RequestMethod.GET)
    public String signDetail (@RequestParam("seq") int seq,
    		HttpSession session,
			Model model) {
    	logger.info("SignController: signDetail");
    	
        String userName = (String) session.getAttribute("NAME");
        String userRank = (String) session.getAttribute("RANK");
    	
    	BoardDto board = signService.signDetail(seq);
    	List<HistoryDto> historyList = signService.historyList(seq);
    	
    	model.addAttribute("board", board);
        model.addAttribute("userRank", userRank);
        model.addAttribute("userName", userName);
        model.addAttribute("historyList", historyList);
    	return "signDetail";
    }
    
}
