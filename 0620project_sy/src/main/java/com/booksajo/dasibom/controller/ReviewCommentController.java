package com.booksajo.dasibom.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksajo.dasibom.service.ReviewCommentService;
import com.booksajo.dasibom.vo.ReviewCommentVO;
import com.booksajo.dasibom.vo.UserVO;


@Controller
public class ReviewCommentController {

    @Resource(name = "reviewCommentService")
    private ReviewCommentService reviewCommentService;

    @RequestMapping(value = "/comment.do", params = "action=insert", method = RequestMethod.POST)
    public String insertComment(
            @ModelAttribute("commentVO") ReviewCommentVO commentVO,
            @RequestParam("isbn") String isbn,
            HttpSession session) throws Exception {
        
        UserVO user = (UserVO) session.getAttribute("user");
        if (user == null)
            return "redirect:/login.do";

        commentVO.setUser_Id(user.getUser_Id());
        reviewCommentService.insertComment(commentVO);

        return "redirect:/bookview.do?isbn=" + isbn;  // isbn 파라미터 사용
    }

    // 수정/삭제
}
