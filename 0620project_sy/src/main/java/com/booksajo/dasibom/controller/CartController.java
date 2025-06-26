package com.booksajo.dasibom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booksajo.dasibom.service.CartService;
import com.booksajo.dasibom.service.OrderService;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.GoodsVO;

@Controller
public class CartController {

	@Resource(name = "cartService")
	private CartService cartService;

	@Resource(name = "orderService")
	private OrderService orderService;

	@Resource(name = "UserService")
	private UserService userService;

	@RequestMapping(value = "/cartAll.do")
	public String cartAll(HttpSession session, Model model) throws Exception {

		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/login.do";
		}

		ArrayList<CartVO> alist = cartService.getCartByUserId(userId);
		if (alist == null) {
			alist = new ArrayList<CartVO>();
		}

		List<GoodsVO> userGoodsList = cartService.getUserGoodsCartByUserId(userId);
		if (userGoodsList == null) {
			userGoodsList = new ArrayList<GoodsVO>();
		}

		// ✅ 로그인 유저 정보 추가 (포인트 표시용)
		model.addAttribute("loginUser", userService.getUserById(userId));

		model.addAttribute("alist", alist);
		model.addAttribute("userGoodsList", userGoodsList); // 유저 굿즈

		return "cartAll";
	}

	@RequestMapping(value = "/cartInsertForm.do")
	public String insertCartForm(@ModelAttribute("cartVO") CartVO cartVO, Model model) throws Exception {
		return "cartInsertForm";
	}

	/* -----------------update-start------------------- */
	@RequestMapping(value = "/insertCart.do", method = RequestMethod.POST)
	@ResponseBody
	public String insertCart(HttpSession session, @ModelAttribute CartVO cartVO) throws Exception {
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return "login-required";
		}

		cartVO.setUserId(userId);
		cartVO.setListType("cart");

		CartVO existing = cartService.getCartBookByUserIdAndIsbn(userId, cartVO.getIsbn());

		if (existing != null) {
			// 수량 누적
			existing.setCount(existing.getCount() + cartVO.getCount());
			cartService.updateCart(existing);
		} else {
			// 새로 담기
			cartService.insertCart(cartVO);
		}

		return "success";
	}

	/* -----------------update-end--------------------- */
	@RequestMapping(value = "/updateCart.do")
	public String updateCart(@ModelAttribute("cartVO") CartVO cartVO) throws Exception {
		cartService.updateCart(cartVO);
		return "redirect:cartAll.do";
	}

	@RequestMapping(value = "/deleteCart.do", method = RequestMethod.POST)
	public String deleteCartItems(@RequestParam("userId") String userId,
			@RequestParam(required = false) List<String> isbnList,
			@RequestParam(required = false) List<String> goodsIdList) {
		if (isbnList != null && !isbnList.isEmpty()) {
			cartService.deleteCart(userId, isbnList);
		}

		if (goodsIdList != null && !goodsIdList.isEmpty()) {
			cartService.deleteGoodsFromCart(userId, goodsIdList);
		}

		return "redirect:/cartAll.do";
	}

	@RequestMapping(value = "/modifyCartCount.do", method = RequestMethod.POST)
	@ResponseBody
	public String modifyCartCount(HttpSession session, @RequestParam("isbn") String isbn,
			@RequestParam("count") int count) {

		// 세션에서 userId 가져오기
		String userId = (String) session.getAttribute("userId");

		// 로그인 안 된 상태라면 처리
		if (userId == null) {
			return "login-required";
		}

		CartVO vo = new CartVO();
		vo.setUserId(userId);
		vo.setIsbn(isbn);
		vo.setCount(count);

		int result = cartService.modifyCount(vo);
		return (result > 0) ? "success" : "fail";
	}

}
