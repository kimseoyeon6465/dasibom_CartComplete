package com.booksajo.dasibom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksajo.dasibom.service.BookService;
import com.booksajo.dasibom.service.ReviewCommentService;
import com.booksajo.dasibom.service.ReviewService;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.BookSearchResult;
import com.booksajo.dasibom.vo.BookVO;
import com.booksajo.dasibom.vo.ReviewCommentVO;
import com.booksajo.dasibom.vo.ReviewVO;
import com.booksajo.dasibom.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/MainPage.do")
	public String MainPage(HttpSession session, Model model) throws Exception {
		String userId = (String) session.getAttribute("userId");

		if (userId != null) {
			model.addAttribute("userId", userId);
		}
		ArrayList<BookVO> bestlist = bookService.getBest();
		model.addAttribute("bestlist", bestlist);
		return "MainPage";
	}

	@Resource(name = "bookService")
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Resource(name = "reviewService")
	private ReviewService reviewService;

	@Resource(name = "reviewCommentService")
	private ReviewCommentService reviewCommentService;

	@RequestMapping("/bookview.do")
	public String bookView(BookVO bookVO, Model model, HttpSession session) {

		BookVO telinfo = bookService.getTelinfo(bookVO);
		if (telinfo == null) {
			return "redirect:/MainPage.do";
		}
		model.addAttribute("book", telinfo);

		// 책 평균 평점
		double averageRating = bookService.getAverageRating(bookVO.getIsbn());
		model.addAttribute("averageRating", averageRating);

		Integer user_seq = (Integer) session.getAttribute("user_seq");
		if (user_seq != null) {

			UserVO user = userService.getUserBySeq(user_seq);
			model.addAttribute("user", user);
		}

		/// 리뷰 목록 가져오기
		List<ReviewVO> reviewList = reviewService.getAllReviewByIsbn(bookVO.getIsbn());
		model.addAttribute("reviewList", reviewList);

		// 댓글 Map 조회
		Map<Integer, List<ReviewCommentVO>> commentMap = new HashMap<Integer, List<ReviewCommentVO>>();

		// 각 리뷰별 댓글 가져오기
		for (ReviewVO review : reviewList) {
			List<ReviewCommentVO> comments = reviewCommentService.getCommentsByReviewId(review.getReview_Id());
			commentMap.put(review.getReview_Id(), comments);
		}

		model.addAttribute("commentMap", commentMap);

		return "bookview";
	}

	@RequestMapping(value = "/books.do", method = RequestMethod.GET)
	public String getBooks(@RequestParam String query,
			@RequestParam(required = false, defaultValue = "1") Integer currentPage, Model model) throws Exception {

		BookSearchResult result = bookService.searchBooks(query, (currentPage - 1) * 10);
		List<BookVO> books = result.getBooks();
		int total = result.getTotal();

		model.addAttribute("books", books);
		model.addAttribute("query", query);
		model.addAttribute("total", (int) Math.ceil((double) total / 10));
		model.addAttribute("pageSize", 10);
		model.addAttribute("currentPage", currentPage);

		int startPage = ((currentPage - 1) / 10) * 10 + 1;
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", Math.min(startPage + 9, (int) Math.ceil((double) total / 10)));

		return "bookList";
	}

	@RequestMapping(value = "/genre.do", method = RequestMethod.GET)
	public String getBooks(@RequestParam String category, 
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			Model model
			) throws Exception {
		
		int pageSize = 10;
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		
		
		ArrayList<BookVO> books = bookService.searchBooksByGenre(category, start, end);
		int total = bookService.getBookCountByGenre(category);

		model.addAttribute("books", books);
		model.addAttribute("total", (int) Math.ceil((double) total / 10));
		model.addAttribute("pageSize", 10);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("category", category);

		int startPage = ((currentPage - 1) / 10) * 10 + 1;
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", Math.min(startPage + 9, (int) Math.ceil((double) total / 10)));

		return "genreBookList";

	}

	@RequestMapping(value = "/bestbook.do", method = RequestMethod.GET)
	public String best(Model model) throws Exception {
		ArrayList<BookVO> books = bookService.getBest();
		model.addAttribute("books", books);
		return "BestBook";

	}

	@RequestMapping(value = "/newbook.do", method = RequestMethod.GET)
	public String newbook(Model model) throws Exception {
		ArrayList<BookVO> books = bookService.getNew();
		model.addAttribute("books", books);
		return "NewBook";

	}

	// 회원가입 페이지로 이동
	@RequestMapping(value = "/signup.do")
	public String goSignup() {
		return "signupForm";
	}

	// 로그인 페이지로 이동
	@RequestMapping(value = "/login.do")
	public String goLogin() {
		return "loginPage";
	}

	@RequestMapping(value = "/sakura.do")
	public String sakura() {
		return "saku";
	}

}
