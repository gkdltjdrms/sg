package com.mycompany.myapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class BoardListController {
	Logger logger = LoggerFactory.getLogger(BoardListController.class);

	@Autowired
	BoardListService bservice;
	
	@Autowired
    private SqlSession sqlSession;

	// 게시글 목록 (동기식)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String boardList(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "searchBar", required = false) String searchBar,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            Model model
    ) {
	    logger.info("boardList 호출");

	    // 검색 조건이 있는지 확인
	    boolean isSearch = search != null && searchBar != null;

	    // 서비스에 검색 정보 및 페이지 정보 전달
	    int pageSize = 10;
	    int totalSearchPages;
	    List<BoardListDto> bList;
	    
	    if (isSearch) {
	        bList = bservice.boardSearch(search, searchBar, startDate, endDate, currentPage, pageSize);
	    } else {
	        bList = bservice.boardList(currentPage, pageSize);
	    }
	    
	    // 검색어, 검색 날짜 조건 추가
	    model.addAttribute("search", search);
	    model.addAttribute("searchBar", searchBar);
	    model.addAttribute("startDate", startDate);
	    model.addAttribute("endDate", endDate);
	    
	    // 검색 여부, 결과, 및 페이지 정보 추가
	    model.addAttribute("isSearch", isSearch);
	    model.addAttribute("boardList", bList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("pageSize", pageSize);

	    // 전체 페이지 수 추가
	    model.addAttribute("totalPages", bservice.getTotalPages(isSearch, search, searchBar, startDate, endDate, pageSize));

	    return "boardList";
	}

	// 게시글 목록(비동기식)
	@RequestMapping(value = "/ajax/list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> handleAjaxListRequest(
	        @RequestParam(value = "search", required = false) String search,
	        @RequestParam(value = "searchBar", required = false) String searchBar,
	        @RequestParam(value = "startDate", required = false) String startDate,
	        @RequestParam(value = "endDate", required = false) String endDate,
	        @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
	        Model model
	) {
	    logger.info("boardList AJAX 호출");

	    boolean isSearch = search != null && searchBar != null;
	    int pageSize = 10;
	    int totalPages = bservice.getTotalPages(isSearch, search, searchBar, startDate, endDate, pageSize);

	    Map<String, Object> ajaxMap = new HashMap<>();
	    List<BoardListDto> bList;
	    if (isSearch) {
	        bList = bservice.boardSearch(search, searchBar, startDate, endDate, currentPage, pageSize);
	    } else {
	        bList = bservice.boardList(currentPage, pageSize);
	    }
	    ajaxMap.put("bList", bList);
	    ajaxMap.put("totalPages", totalPages);
	    ajaxMap.put("currentPage", currentPage);


	    return ajaxMap;
	}
	
	// 게시글 등록 페이지 이동
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String boardWrite(Model model) {
		logger.info("boardWrite 호출");
		return "writeForm";
	}
	
	// 게시글 등록
	private static final String SAVE_PATH = "C:/Users/dev/Desktop/image/"; // 파일 경로 설정
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public String boardInsert(@RequestParam("author") String author,
	                          @RequestParam("id") String id,
	                          @RequestParam("title") String title,
	                          @RequestParam("content") String content,
	                          @RequestParam(value = "uplfiles", required = false) MultipartFile[] uplfiles,
	                          Model model) {
	    logger.info("boardInsert 호출");

	    BoardListDto board = new BoardListDto();
	    board.setMem_name(author);
	    board.setMem_id(id);
	    board.setBoard_subject(title);
	    board.setBoard_content(content);
	    board.getReg_date();
	    
	    // 먼저 게시글을 등록
	    List<BoardListDto> bList = bservice.boardInsert(board);
	    model.addAttribute("boardInsert", bList);

	    // 등록된 게시글의 seq 값을 가져오기
	    int list_seq = sqlSession.selectOne("listSeq");  // 여기서 가져온 값이 null이면 예외처리 필요
	    System.out.println("list_seq : " + list_seq);

	    // 파일 업로드 로직
	    try {
	        if (uplfiles != null && uplfiles.length > 0)  {
	            for (MultipartFile uplfile : uplfiles) {
	                if (!uplfile.isEmpty()) {
	                    String real_name = uplfile.getOriginalFilename();
	                    String save_name = System.currentTimeMillis() + "_" + real_name;

	                    uplfile.transferTo(new File(SAVE_PATH + save_name));

	                    // 파일 업로드 시에는 이미 등록된 게시글의 seq 값을 사용하여 boardFileInsert 호출
	                    bservice.boardFileInsert(real_name, save_name, SAVE_PATH, list_seq); 
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); // 예외를 적절히 처리하세요
	    }

	    return "redirect:/list";
	}

	// 게시글 상세
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam("seq") int seq,
			Model model
			) {
		logger.info("boardDetail 호출");
		// seq에 해당하는 게시물 정보를 가져온다
		BoardListDto board = bservice.boardDetail(seq);
		// 파일 정보를 가져와 모델에 추가
		List<BoardListDto> file = bservice.boardFileDetail(seq);
		// 가져온 정보를 모델에 추가
		model.addAttribute("board", board);
		model.addAttribute("file", file);
		logger.info("file : " + file);

		return "boardDetail";
	}
	
	// 게시글 파일 다운로드
	private static final String DOWN_PATH = "C:/Users/dev/Desktop/image/"; // 파일 경로 설정
	@RequestMapping("/download")
	@ResponseBody
	public void boardDownload(@RequestParam("fileName") String fileName, HttpServletResponse response) {
		try {
			// 파일 경로 구성
			Path filePath = Paths.get(DOWN_PATH).resolve(fileName);

			// 파일이름 설정
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

			// 파일을 응답으로 전송
			Files.copy(filePath, response.getOutputStream());
			response.getOutputStream().flush();

		} catch (IOException e) {
			// 예외 처리 (예: 오류 기록 또는 오류 페이지 표시)
			e.printStackTrace();
		}
	}
	
	// 게시글 엑셀 다운로드
	@RequestMapping(value = "/axceldown", method = RequestMethod.POST)
	public String boardExcel(@RequestParam Map<String, Object> paramMap, Model model, HttpServletResponse response) throws Exception {
	    logger.info("boardExcel 호출");

	    // 엑셀 다운로드 서비스를 호출하여 전체 데이터를 가져옴
	    List<Map<String, Object>> excelList = sqlSession.selectList("selectBoardExcelList", paramMap);
	    // 모델에 데이터 추가
	    model.addAttribute("excelList", excelList);
	    return "excelList";
	}

	// 게시글 수정
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute("board") BoardListDto board, Model model) {
		logger.info("boardUpdate 호출");

		// 수정된 정보 업데이트
		BoardListDto updatedBoard = bservice.boardUpdate(board);
		model.addAttribute("boardUpdate", updatedBoard);
		return "redirect:/list";
	}

	// 게시글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String boardDelete(@RequestParam(value = "chk", required = false) List<Long> selectedBoards) {
		logger.info("boardDelete 호출");

		if (selectedBoards != null && !selectedBoards.isEmpty()) { // 선택된 항목이 있다면 삭제
			bservice.boardDelete(selectedBoards);
		}
		return "redirect:/list";
	}
}
