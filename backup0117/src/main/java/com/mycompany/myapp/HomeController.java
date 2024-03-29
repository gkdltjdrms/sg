package com.mycompany.myapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.Variable;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;
import com.nexacro.java.xapi.tx.PlatformException;
import com.nexacro.java.xapi.tx.PlatformType;

import service.PostService; // 올바른 패키지 경로를 사용
import util.BoardStatusConverter;
import model.Board;
import model.History;
import model.User;


@Controller
public class HomeController {
	
	@Autowired
	 private SqlSession sql; // SqlSession을 주입합니다.
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private PostService postService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	
	@RequestMapping("nexaConn")
	public void nexaList(HttpServletRequest request, HttpServletResponse response) throws PlatformException {
	    System.out.println("================nexa start================");

	    HttpPlatformRequest req = new HttpPlatformRequest(request);
	    req.receiveData();
	    PlatformData inData = req.getData();
	    Variable keyword = inData.getVariable("keyword");
	    String keywordValue = keyword.getString(); // 키워드 값 가져오기
	    
	    // searchType 값 가져오기
	    Variable searchType = inData.getVariable("searchType");
	    String searchTypeValue = searchType.getString();

	    // 이후에 keywordValue를 사용하여 필요한 로직 수행
	    
	    Map<String, Object> paramMap = new HashMap<>();
      //  paramMap.put("searchType", searchType);
        paramMap.put("keyword", keywordValue);
        paramMap.put("searchtype", searchTypeValue);

	    System.out.println(keywordValue + "받아온 파라미터값 확인");
	    
	    if (sql != null) {
	        List<Map<String, Object>> list = sql.selectList("mapper.BoardMapper.boardList", paramMap);

	        if (list != null) {
	        	 DataSet ds = new DataSet("board");
	            ds.addColumn("seq", DataTypes.INT, 10);
	            ds.addColumn("mem_name", DataTypes.STRING, 255);
	            ds.addColumn("mem_id", DataTypes.STRING, 255);
	            ds.addColumn("board_subject", DataTypes.STRING, 255);
	            ds.addColumn("board_content", DataTypes.STRING, 4000);

	            System.out.println("ds데이터 가져온 값 확인" + ds);

	            for (int i = 0; i < list.size(); i++) {
	                int row = ds.newRow();
	                ds.set(row, "seq", list.get(i).get("seq"));
	                ds.set(row, "mem_name", list.get(i).get("mem_name"));
	                ds.set(row, "mem_id", list.get(i).get("mem_id"));
	                ds.set(row, "board_subject", list.get(i).get("board_subject"));
	                ds.set(row, "board_content", list.get(i).get("board_content"));
	            }

	            PlatformData outData = new PlatformData();
	            outData.addDataSet(ds);

	            HttpPlatformResponse res = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
	            res.setData(outData);
	            res.sendData(); //여기에서 넘김
	            System.out.println(keywordValue+"받아온 파라미터값 확인");
	            System.out.println(searchTypeValue + "받아온 searchType 파라미터값 확인");
	            System.out.println("================nexa end================");
	        } else {
	            System.out.println("List is null");
	        }
	    } else {
	        System.out.println("sql object is null");
	    }
	}


			//search
			 @RequestMapping(value = "/search", method = RequestMethod.GET)
			 public String search(@RequestParam("id") String id,
			                      @RequestParam("searchType") String searchType,
			                      @RequestParam("searchKeyword") String searchKeyword,
			                      @RequestParam("approveStatus") String payOption,
			                      @RequestParam("startDate") String startDate,
			                      @RequestParam("endDate") String endDate,
			                      HttpSession session,
			                      Model model) {
			     // 검색 관련 로직 수행
				 User user = postService.getUserInfo(id);
				   String userRank = user.getRank();
				   System.out.println(userRank+"이 직급으로 검색");
			        List<Board> boardList = new ArrayList<>(); // Initialize with an empty list
			            // 다른 경우의 게시판 목록을 가져옴 (예: 일반 직원)
			            boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
			            model.addAttribute("boardList", boardList);
			            System.out.println(boardList+"가져오는 항목 확인");
			     return "mainList";
			 }
			
			 //보드 화면 가져오기 
			 @RequestMapping(value = "/boardList", method = RequestMethod.POST)
			 public String boardList(@RequestParam("id") String id,
					 Model model, HttpSession session) {
				   // 로그인 성공 시 사용자의 이름과 직급을 가져옴
				    User user = postService.getUserInfo(id);
				  List<Board> boardList = new ArrayList<>(); // Initialize with an empty list
				   String payOption = null;
			        String searchType = null;
			        String searchKeyword = null;
			        String userRank = user.getRank();
			        String startDate = null;
			        String endDate = null;
				  // 다른 경우의 게시판 목록을 가져옴 (예: 일반 직원)
		            boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
	
		        model.addAttribute("boardList", boardList);
		        System.out.println(boardList + "가져오는지 체크 보드리스트");
				 
				 return "redirect:/mainList";
				
			 }
			 
			 @RequestMapping(value = "/delegateList", method = RequestMethod.GET)
			 @ResponseBody
			 public List<Map<String, Object>> getDelegateList(@RequestParam("loggedInUserRank") String loggedInUserRank) {
			     // 세션에서 로그인한 사용자의 rank를 가져옵니다. (세션 정보를 얻는 방법은 프레임워크나 설정에 따라 다를 수 있습니다.)
			     System.out.println(loggedInUserRank + "랭크변수 확인");

			     // 대리자 목록을 가져오는 로직을 구현해야 합니다.
			     List<String> delegateList = postService.getDelegatesByRank(loggedInUserRank);
			     System.out.println(delegateList + "아이디 찾기 쿼리문 실행된 결고 ㅏ확인");

			     // 대리자 목록의 id들로 이름과 rank를 조회
			     List<Map<String, Object>> delegateInfoList = postService.getDelegateInfoByIds(delegateList);
			     System.out.println(delegateInfoList + "정보찾기 ");
			     
			     // delegateInfoList를 반환
			     return delegateInfoList;
			 }
			 
			 @RequestMapping(value = "/getDelegateRank", method = RequestMethod.GET)
			 @ResponseBody
			 public Map<String, Object> getDelegateRank(@RequestParam("delegateId") String delegateId) {
			     Map<String, Object> result = new HashMap<>();
			     System.out.println(delegateId + "받아온 아이디 확인");
			     String delegateRank = postService.getDelegateRankByIds(delegateId);
			     System.out.println(delegateRank + "가져온 직급 출력");
			     result.put("rank", delegateRank);
			     return result;
			 }
			 
			 @RequestMapping(value = "/approveDelegate", method = RequestMethod.GET)
			 @ResponseBody
			 public String approveDelegate(
			         @RequestParam("loggedInUserId") String loggedInUserId,
			         @RequestParam("delegateId") String delegateId) {
			     
			     // Perform your approval logic in the service layer
			     int approvalResult = postService.approveDelegate(loggedInUserId, delegateId);

			     if (approvalResult == 1) {
			         return "승인이 완료되었습니다."; // Adjust the response as needed
			     } else {
			         return "승인에 실패하였습니다."; // Adjust the response as needed
			     }
			 }



			 
		
				@RequestMapping(value = "/practice", method = RequestMethod.GET)
				public String practice() {
					return "practice"; // "payLogin.jsp"로 이동
				}
				
				@RequestMapping(value = "/instead", method = RequestMethod.GET)
				public String instead() {
					return "instead"; // 
				}

				//로그인 페이지 이동
				@RequestMapping(value = "/payLogin", method = RequestMethod.GET)
				public String payLogin() {
					System.out.println("여기서 되는건가? 홈컨틀롤러 호출");
					return "payLogin"; // "payLogin.jsp"로 이동
				}
			 
				// 로그인 처리
				@RequestMapping(value = "/checkId", method = RequestMethod.POST)
				public String checkId(@RequestParam("id") String id,
				                      @RequestParam("pwd") String pwd,
				                      Model model,
				                      HttpSession session) {

				    // Service를 통한 ID와 비밀번호 확인
				    int userIdCount = postService.findId(id);
				    int passwordCount = postService.checkPassword(id, pwd);

				    // 등록되지 않은 사용자인 경우
				    if (userIdCount == 0) {
				        model.addAttribute("error", "등록되지 않은 사용자입니다.");
				        return "redirect:/payLogin"; // payLogin 페이지로 리다이렉트
				    } else if (passwordCount == 0) {
				        model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
				        return "redirect:/payLogin"; // payLogin 페이지로 리다이렉트
				    }

				    // 로그인 성공 시 사용자의 이름과 직급을 가져옴
				    User user = postService.getUserInfo(id);

				    if (user != null) {
				        // 사용자 정보가 있다면 세션에 저장
				        session.setAttribute("loggedInUserId", id);
				        session.setAttribute("loggedInUserName", user.getMemName());
				        session.setAttribute("loggedInUserRank", user.getRank());
				    }

				    // 여기에 로그인 성공 시 처리하는 코드를 추가하세요.
				    System.out.println("메인리스트옴22");
				    return "redirect:/mainList"; 
				}
			 
				
				// 메인 페이지 이동
				@RequestMapping(value = "/mainList", method = RequestMethod.GET)
				public String mainList(HttpServletRequest request, Model model) {
				    System.out.println("여기서 되는건가? 홈컨트롤러 호출");

				    // 세션에서 id 값을 가져옴
				    String id = (String) request.getSession().getAttribute("loggedInUserId");

				    // 세션에 id가 없으면 로그인 페이지로 리다이렉트
				    if (id == null) {
				        // 여기에서 alert 메시지를 전달하는 방법은 없습니다.
				        // JavaScript로 처리하거나, 모달 창을 띄워 사용자에게 알림을 줄 수 있습니다.
				        return "redirect:/payLogin";
				    }
				    User user = postService.getUserInfo(id);

				    String payOption = null;
				    String searchType = null;
				    String searchKeyword = null;
				    String userRank = user.getRank();
				    String startDate = null;
				    String endDate = null;
				    List<Board> boardList = new ArrayList<>(); // Initialize with an empty list

				    // 다른 경우의 게시판 목록을 가져옴 (예: 일반 직원)
				    boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
				    // 게시글의 각 checkName에 해당하는 이름을 가져와서 모델에 추가
				    
				    
				    System.out.println(id + userRank + "rank변수 들어가는지 확인");
				    model.addAttribute("boardList", boardList);
				    System.out.println(boardList + "가져오는지 체크 보드리스트");

				    return "mainList";
				}

			 
			 
			 
			 
			
			//update AJAX
			@RequestMapping(value = "/searchoption", method = RequestMethod.GET)
			@ResponseBody
			public List<Board> searchoption(
			    @RequestParam("id") String id,
			    @RequestParam("approveStatus") String payOption,
			    @RequestParam(value = "searchType", required = false) String searchType, // Added searchType parameter
			    @RequestParam(value = "searchKeyword", required = false) String searchKeyword, // Added searchKeyword parameter
			    @RequestParam("startDate") String startDate,
                @RequestParam("endDate") String endDate,
			    HttpSession session,
			    Model model) {
			    System.out.println("검색하러 컨트롤러 옴");
			    User user = postService.getUserInfo(id);
			    String userRank = user.getRank();

			    // You can use searchType and searchKeyword as needed

			    List<Board> boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
			    
			    // 결재상태를 한글로 변환하여 데이터에 추가
			    for (Board board : boardList) {
			        board.setPayOption(BoardStatusConverter.getKoreanStatus(board.getPayOption()));
			    }

	
			    System.out.println(boardList);
			    return boardList;
			}

			@RequestMapping(value = "/detail", method = RequestMethod.GET)
			public String detail(@RequestParam("seq") int seq,
					@RequestParam("userId") String userId,
					@RequestParam("userRank") String userRank,
					Model model) {
			    // 게시글 정보 조회
			    Board board = postService.getBoardBySeq(seq);
			    
			    // 히스토리 정보 조회 (예시, 실제 데이터 조회 코드에 맞게 수정 필요)
			    List<History> historyList = postService.getHistoryListByBoardSeq(seq);
			    
			    String id = userId;
			    User user = postService.getUserInfo(id);

			    
			    // 모델에 게시글 및 히스토리 정보 추가
			    model.addAttribute("board", board);
			    model.addAttribute("historyList", historyList);
			    model.addAttribute("user", user);

			    // 뷰 이름 반환
			    return "detail";
			}
			

			//logOut
			@RequestMapping(value = "/logout", method = RequestMethod.POST)
			public String logout(HttpSession session) {
				System.out.println("logout@@@@@@@@@@@@@@@@@@@@");
			    // Invalidate the session to log the user out
			    session.invalidate();
			    // Redirect to the login page or any other desired page
			    return "redirect:/payLogin";
			}
			
			
			@RequestMapping(value = "/payWriteForm", method = RequestMethod.GET)
			public String payWriteForm(Model model,
					HttpSession session,
			        @RequestParam("id") String loggedInUserId,
			        @RequestParam("userName") String loggedInUserName,
			        @RequestParam("userRank") String loggedInUserRank) {

			    if (loggedInUserId != null) {
			    	   int seqNum = postService.findSeq();
			    	    model.addAttribute("seqNum", seqNum);
			    		System.out.println(seqNum);
						model.addAttribute("seqNum", seqNum);
						System.out.println("Model: " + model.asMap());

			    }else {
			    	  return "redirect:/payLogin";
			    }

			    // 글쓰기 페이지로 이동
			    return "payWriteForm";
			}
			
			@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
			public String updateBoard(
			        @RequestParam("seq") int seq,
			        @RequestParam("id") String id,
			        @RequestParam("payOption") String payOption,
			        @RequestParam("subject") String subject,
			        @RequestParam("content") String content,
			        @RequestParam("action") String action,
			        @RequestParam("loginId") String loginId,
			        HttpSession session,
			        Model model) {
			    System.out.println("넘어오는지 확인2");
			    System.out.println(action+loginId+seq+content+payOption+"로그인랭크확인 결재할때");
			    
			    User user = postService.getUserInfo(loginId);
			    String userRank = user.getRank();
			    switch (action) {
			        case "save":
			            // '임시저장' 버튼이 클릭된 경우의 처리
			            // 예를 들어, postService.saveBoard() 메서드 호출 등
			            postService.saveUpdateBoard(seq, id, subject, content);
			            int PostSeq = seq;
			            postService.submitSaveHistory(id, PostSeq);
			            break;
			        case "approval":
			        	// '결재' 버튼이 클릭된 경우의 처리
			        	System.out.println("Login Rank222: " + userRank); // 디버깅을 위해 이 줄 추가
			        	if ("부장".equals(userRank)) {
			        	    System.out.println("Inside 부장 condition"); // 디버깅을 위해 이 줄 추가
			        	    // 부장의 처리 추가
			        	    if ("save".equals(payOption)) {
		        		        payOption = "checking"; // 'save'인 경우 'wait'로 변경
		        		        postService.checkBoard(seq, id, subject, content, payOption, loginId);
		        		        int postSeq = seq;
		        		        postService.submitCheckHistory(loginId, postSeq, payOption);
		        		    } else {
			        	    postService.checkBoard(seq, id, subject, content, payOption, loginId);
			        	    int postSeq = seq;
			        	    postService.submitCheckHistory(loginId, postSeq, payOption);
		        		    }
			        	} else if ("과장".equals(userRank)) {
			        		 if ("save".equals(payOption)) {
			        		        payOption = "wait"; // 'save'인 경우 'wait'로 변경
			        		        postService.checkBoard(seq, id, subject, content, payOption, loginId);
			        		        int postSeq = seq;
			        		        postService.submitCheckHistory(loginId, postSeq, payOption);
			        		    } else {
			        			    System.out.println("Inside 과장 condition"); // 디버깅을 위해 이 줄 추가
					        	    postService.checkBoard(seq, id, subject, content, payOption, loginId);
					        	    int postSeq = seq;
					        	    postService.submitCheckHistory(loginId, postSeq, payOption);
			        		    }
			        
			        	} else {
			        	    System.out.println("Inside default condition"); // 디버깅을 위해 이 줄 추가
			        	    postService.updateBoard(seq, id, subject, content);
			        	    int postSeq = seq;
			        	    postService.submitHistory(id, postSeq);
			        	}
			        	break;

			        case "reject":
			            // '반려' 버튼이 클릭된 경우의 처리
			            // 예를 들어, postService.rejectBoard() 메서드 호출 등
			        	System.out.println("반려로옴@@@@@@");
			        		postService.rejectBoardseq(seq, loginId);
			        		 int postSeq = seq;
			        		 payOption="reject";
			        		 postService.submitCheckHistory(loginId, postSeq, payOption);
			            break;
			        default:
			            // 알 수 없는 액션인 경우의 처리
			        	
			        	System.out.println("디폴트값");
			            break;
			    }

			    return "redirect:/mainList"; // 적절한 URL로 변경
			}

					
			//submit
			@RequestMapping(value = "/submitWriteForm", method = RequestMethod.POST)
			public String submitWriteForm(
			        @RequestParam("id") String id,
			        @RequestParam("subject") String subject,
			        @RequestParam("content") String content,
			        @RequestParam("payOption") String payOption, // Add this line for payOption
			        HttpSession session,
			        Model model) {
			    System.out.println("넘어오는지 확인22");
			    if ("save".equals(payOption)) {
			        // Your logic for handling 'save' option goes here
			    	int PostSeq = postService.savesubmitWriteForm(id, subject, content);
			    	 postService.submitSaveHistory(id, PostSeq);
			    }
			    else {
			    	   User user = postService.getUserInfo(id);
					    String userRank = user.getRank();
			    	 // postService.submitWriteForm 메서드에 필요한 인자들을 전달하여 호출
			    	int PostSeq = postService.submitWriteForm(id, subject, content, userRank);
				    postService.submitHistory(id, PostSeq);

			    }

			
			    // Do something with the payOption parameter (e.g., save it to the database)

			    // 로그인 성공 시 사용자의 이름과 직급을 가져옴
			    User user = postService.getUserInfo(id);
			    String userRank = user.getRank();
			    if (user != null) {
			        // 사용자 정보가 있다면 세션에 저장
			        session.setAttribute("loggedInUserId", id);
			        session.setAttribute("loggedInUserName", user.getMemName());
			        session.setAttribute("loggedInUserRank", user.getRank());
			        
			        String searchType = null;
			        String searchKeyword = null;
			        		payOption = null;
			        String startDate = null;
			        String endDate = null;
			        List<Board> boardList = postService.getBoardList(id, payOption, userRank, searchType, searchKeyword, startDate, endDate);
			        model.addAttribute("boardList", boardList);
			        System.out.println(boardList + "가져오는지 체크 보드리스트");
			    }

			    return "mainList"; // 적절한 URL로 변경
			}


			
			

}
