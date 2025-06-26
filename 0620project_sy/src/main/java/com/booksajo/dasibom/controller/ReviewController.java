package com.booksajo.dasibom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booksajo.dasibom.service.BookService;
import com.booksajo.dasibom.service.ReviewService;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.BookVO;
import com.booksajo.dasibom.vo.ReviewVO;
import com.booksajo.dasibom.vo.UserVO;



@Controller
public class ReviewController {

	@Resource(name = "reviewService")
	private ReviewService reviewService;

	@Resource(name = "bookService")
	private BookService bookService;
	
	@Resource(name = "UserService")
	private UserService userService;

	 // 리뷰 출력용
    @RequestMapping("/review.do")
    public String getReviewPage(@RequestParam("isbn") String isbn, Model model, HttpSession session) {

        ArrayList<ReviewVO> reviewList = reviewService.getAllReviewByIsbn(isbn);

        BookVO bookVO = new BookVO();
        bookVO.setIsbn(isbn);

        BookVO book = bookService.getTelinfo(bookVO);

        model.addAttribute("book", book);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("isbn", isbn);
        model.addAttribute("user", session.getAttribute("user"));

        return "review";
    }


	// 리뷰 등록 → review.do?action=insert
	@RequestMapping(value = "/reviewinsert.do")
	public String insertReview(@ModelAttribute("reviewVO") ReviewVO reviewVO, HttpSession session) throws Exception {

		int user_seq = (Integer) session.getAttribute("user_seq");
	    UserVO user = userService.getUserBySeq(user_seq);
		
		
		if (user == null)
			return "redirect:/login.do";

		reviewVO.setUser_Id(user.getUser_Id());
		reviewService.insertReview(reviewVO);

		return "redirect:/bookview.do?isbn=" + reviewVO.getIsbn();
	}

	// 리뷰 수정
	@RequestMapping(value = "reviewupdate.do")
	public String updateReview(@ModelAttribute("reviewVO") ReviewVO reviewVO, HttpSession session) throws Exception {
		
		int user_seq = (Integer) session.getAttribute("user_seq");
	    UserVO user = userService.getUserBySeq(user_seq);
	    
		reviewVO.setUser_Id(user.getUser_Id()); // ⭐️ 반드시 user_id 세팅
		reviewService.updateReview(reviewVO);
		return "redirect:/bookview.do?isbn=" + reviewVO.getIsbn();
	}

	// 리뷰 삭제( 댓글 있을경우 함께 삭제)
	@RequestMapping(value = "/reviewdelete.do")
	public String deleteReview(@RequestParam("review_Id") int review_Id,
	                           @RequestParam("isbn") String isbn,
	                           HttpSession session) throws Exception {
		
		int user_seq = (Integer) session.getAttribute("user_seq");
	    UserVO user = userService.getUserBySeq(user_seq);

	    // 수정: Service 메서드가 바뀌었으므로!
	    reviewService.deleteReview(review_Id, user.getUser_Id());

	    return "redirect:/bookview.do?isbn=" + isbn;
	}
	
	// 좋아요 (like)
	@RequestMapping(value = "/reviewlike.do")
	public String likeReview(@RequestParam("review_Id") int review_Id,
	                         @RequestParam("isbn") String isbn,
	                         HttpSession session) throws Exception {

	    // 로그인 여부와 관계없이 좋아요 가능
	    reviewService.incrementLikes(review_Id);

	    return "redirect:/bookview.do?isbn=" + isbn;
	}


}

