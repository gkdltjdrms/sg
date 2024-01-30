package com.mycompany.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




import service.PostService; // 올바른 패키지 경로를 사용
import model.post; // 올바른 패키지 경로를 사용

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



	
	
	@RequestMapping(value = "/goToList", method = RequestMethod.GET)
	public String goToList(Model model) {
	
		List<post> postList = postService.getPostList();
		model.addAttribute("listOfPosts", postList);
		return "list";
	}
}
