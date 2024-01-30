package com.mycompany.myapp;

import model.BoardPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String showWriteForm(Model model) {
        return "write"; // write.jsp를 뷰로 사용
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writeBoard(BoardPost boardPost) {
        // boardPost 객체에 입력된 데이터가 전달됨
        // 여기에서 데이터베이스에 저장하는 로직을 추가
        // 저장 후 홈페이지로 리다이렉트 또는 다른 작업 수행
        return "redirect:/"; // 홈페이지로 리다이렉트
    }
}

