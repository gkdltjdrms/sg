package com.mycompany.myapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.PostService; // 올바른 패키지 경로를 사용
import model.SearchCriteria;
import model.post; // 올바른 패키지 경로를 사용
import oracle.jdbc.proxy.annotation.Post;


@Controller
public class HomeController {
	
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
	
	//글쓰기 페이지 이동
	@RequestMapping(value = "/goToWrite", method = RequestMethod.GET)
	public String goToWrite() {
		System.out.println("여기서 되는건가? 홈컨틀롤러 호출");
		return "write"; // "write.jsp"로 이동
	}
	
	
	
	@RequestMapping(value = "/listinfo", method = RequestMethod.GET)
	public String goToPost(@RequestParam("seq") int seq, Model model) {
	    // seq를 사용하여 해당 글을 가져오는 로직 추가
	    post post = postService.getPostBySeq(seq);
	    model.addAttribute("post", post);
	    return "listinfo"; // "listinfo.jsp"로 이동
	}
	
	
	//작성or수정
		@RequestMapping(value = "/writeOrUpdatePost", method = RequestMethod.POST)
		public String writeOrUpdatePost(@RequestParam("memName") String memName,
		                                @RequestParam("memId") String memId,
		                                @RequestParam("boardSubject") String boardSubject,
		                                @RequestParam("boardContent") String boardContent,
		                                @RequestParam(value = "seq", required = false) Integer seq,
		                                Model model) {
			System.out.println("writeorupdatecontroller 옴");
		    if (seq == null) {
		        // seq가 null이면 새로운 글 작성
		        postService.writePost(memName, memId, boardSubject, boardContent);
		    } else {
		        // seq가 존재하면 해당 seq의 글 업데이트
		        postService.updatePost(seq, memName, memId, boardSubject, boardContent);
		    }

		    // 작성 또는 업데이트 완료 후 리스트 페이지로 리다이렉트
		    return "redirect:/goToList";
		}

		
		// 글 삭제
		@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
		public String deletePost(@RequestParam("seq") Long seq) {
		    // Assuming you have a service to handle post deletion
		    postService.deletePost(seq);
		    // Redirect to the post list page after deletion
		    return "redirect:/goToList";
		}

	
	
	
	/*
	 * @RequestMapping(value = "/goToSearch", method = RequestMethod.GET) public
	 * String goToSearch(@RequestParam(value = "keyword") String keyword,
	 * 
	 * @RequestParam(value = "searchOption", defaultValue = "all") String
	 * searchOption, Model model) { List<post> searchResults = new ArrayList<>();
	 * 
	 * switch (searchOption) { case "title":
	 * System.out.println("일단 컨트롤러 search 시작 서비스로 이동시키는중"); searchResults =
	 * postService.searchPostsByTitle(keyword); break; case "titleAndAuthor":
	 * searchResults = postService.searchPostsByTitleAndAuthor(keyword); break; case
	 * "id": searchResults = postService.searchPostsById(keyword); break; default:
	 * // searchResults = postService.searchPosts(keyword); break; }
	 * 
	 * 
	 * model.addAttribute("listOfPosts", searchResults);
	 * 
	 * return "list"; }
	 */

		/*
	@RequestMapping(value = "/goToSearch", method = RequestMethod.GET)
	public String goToSearch(@RequestParam(value = "keyword") String keyword,
	                         @RequestParam(value = "searchOption", defaultValue = "all") String searchOption,
	                         @RequestParam(value = "startDate", required = false) String startDate,
	                         @RequestParam(value = "endDate", required = false) String endDate,
	                         Model model) {
	    Map<String, Object> searchMap = new HashMap<>();
	    searchMap.put("keyword", keyword);
	    searchMap.put("searchOption", searchOption);
	    searchMap.put("startDate", startDate);
	    searchMap.put("endDate", endDate);

	    List<post> searchResults = postService.searchPosts(searchMap);

	    model.addAttribute("listOfPosts", searchResults);

	    return "list";
	}
	*/


	
	

		
		
		
	
	  //리스트 추력
	  /*
	  @RequestMapping(value = "/goToList", method = RequestMethod.GET) public
	  String goToList(Model model) {
	  
	  List<post> postList = postService.getPostList();
	  model.addAttribute("listOfPosts", postList);
	  return "list"; 
	  }
	  */
	
	  /*
	@RequestMapping(value = "/goToList", method = RequestMethod.GET)
	public String goToList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
	    List<post> postList = postService.getPostList(page, pageSize);
	    int totalPosts = postService.getTotalPosts();

	    int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

	    model.addAttribute("listOfPosts", postList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);

	    return "list";
	}
	*/
		
		
	@RequestMapping(value = "/goToSearch", method = RequestMethod.GET)
	public String goToSearch(@ModelAttribute SearchCriteria searchCriteria, Model model) {
		List<post> searchResults = postService.searchPosts(searchCriteria);
		int totalPosts = postService.getTotalPosts(searchCriteria);

		int totalPages = (int) Math.ceil((double) totalPosts / searchCriteria.getPageSize());

		model.addAttribute("listOfPosts", searchResults);
		model.addAttribute("currentPage", searchCriteria.getPage());
		model.addAttribute("totalPages", totalPages);

		return "list";
	}

	@RequestMapping(value = "/goToList", method = RequestMethod.GET)
	public String goToList(@ModelAttribute SearchCriteria searchCriteria, Model model) {
		System.out.println("그냥리스트");
	    List<Post> postList = postService.getPostList(
	            searchCriteria.getPage(),
	            searchCriteria.getPageSize(),
	            searchCriteria.getKeyword(),
	            searchCriteria.getSearchOption(),
	            searchCriteria.getStartDate(),
	            searchCriteria.getEndDate()
	    );

	    int totalPosts = postService.getTotalPosts(
	            searchCriteria.getKeyword(),
	            searchCriteria.getSearchOption()
	    );

	    System.out.println("22");
	    model.addAttribute("currentPage", searchCriteria.getPage());
	    int totalPages = (int) Math.ceil((double) totalPosts / searchCriteria.getPageSize());

	    model.addAttribute("listOfPosts", postList);
	    model.addAttribute("totalPages", totalPages);

	    return "list";
	}

	
	
	@RequestMapping(value = "/searchgo", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Object> searchgo(
	        @ModelAttribute SearchCriteria searchCriteria, Model model) {
	    System.out.println("일단 ajax 컨트롤러 옴");
	    
	   
	    Map<String, Object> searchgo = new HashMap<>();
	    List<Post> postList = postService.getPostList(
	            searchCriteria.getPage(),
	            searchCriteria.getPageSize(),
	            searchCriteria.getKeyword(),
	            searchCriteria.getSearchOption(),
	            searchCriteria.getStartDate(),
	            searchCriteria.getEndDate()
	    );
	    int totalPosts = postService.getTotalPosts(
	            searchCriteria.getKeyword(),
	            searchCriteria.getSearchOption()
	    );
	    int totalPages = (int) Math.ceil((double) totalPosts / searchCriteria.getPageSize());
	    System.out.println("데이터 입력중");
		/* response.put("currentPage", searchCriteria.getPage()); */
	    searchgo.put("listOfPosts", postList);
		/* response.put("totalPages", totalPages); */
	    System.out.println("데이터 입력완료");
	    System.out.println(searchgo);
	    return searchgo;
	}




	
	
	  
	

	 
	
	



	
	
	//삭제 페이지로 이동
			@RequestMapping(value = "/goDelete", method = RequestMethod.GET)
			public String goDelete(Model model) {
				System.out.println("일단 delete.jsp 로 이동하는 컨트롤러 ");
				List<post> postList = postService.getPostList();
				model.addAttribute("listOfPosts", postList);
				return "delete"; // "delete.jsp"로 이동
			}
			
			@RequestMapping(value = "/deleteSelectedPosts", method = RequestMethod.POST)
			public String deleteSelectedPosts(@RequestParam(value = "selectedPosts", required = false) List<Long> selectedPosts, Model model) {
			    if (selectedPosts == null || selectedPosts.isEmpty()) {
			        // 선택된 항목이 없는 경우, 메시지를 추가
			        model.addAttribute("message", "체크된 항목이 없습니다. 삭제하려는 항목을 선택하세요.");
			        return "goToList";
			    }

			    // 선택된 항목이 있는 경우, 삭제 수행
			    postService.deleteSelectedPosts(selectedPosts);

			    return "redirect:/goToList";
			}

			
	
	
}
