package com.booksajo.dasibom.controller;

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

import com.booksajo.dasibom.service.CartService;
import com.booksajo.dasibom.service.WishlistService;
import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.WishlistVO;

@Controller
public class WishlistController {
	@Resource(name = "wishlistService")
	private WishlistService wishlistService;

	@Resource(name = "cartService")
	private CartService cartService;

	@RequestMapping("/wishlist.do")
	public String wishlist(HttpSession session, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/login.do";
		}

		int pageSize = 8;
		int start = (page - 1) * pageSize + 1;
		int end = page * pageSize;

		// ✅ Map 생성
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("start", start);
		param.put("end", end);

		// ✅ Map 기반으로 호출
		List<WishlistVO> wlist = wishlistService.getWishlistByPage(param);
		int totalCount = wishlistService.getWishlistCount(userId);
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
		// ✅ 굿즈 보관함 리스트 추가
		List<CartVO> goodsList = wishlistService.getGoodsWishlistByUserId(userId);

		model.addAttribute("wlist", wlist);
		model.addAttribute("goodsList", goodsList); // ✅ 이 부분이 있어야 JSP에서 굿즈 출력됨

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		return "wishlist";
	}

	@RequestMapping(value = "/moveToCart.do", method = RequestMethod.POST)
	@ResponseBody
	public String moveToCart(@RequestParam("userId") String userId, @RequestParam("isbn") String isbn,
			@RequestParam("imagePath") String imagePath) {
		System.out.println("=== moveToCart 호출됨 ===");
		System.out.println("user_id: " + userId + ", isbn: " + isbn);

		WishlistVO vo = new WishlistVO();
		vo.setUserId(userId);
		vo.setIsbn(isbn);
		vo.setImagePath(imagePath);

		wishlistService.moveToCart(vo);
		return "success";
	}

	@RequestMapping(value = "/moveToWishlistGoods.do", method = RequestMethod.POST)
	@ResponseBody
	public String moveToWishlistGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") int goodsId) {

		boolean exists = wishlistService.checkGoodsExistsInCart(userId, goodsId);
		if (exists) {
			return "duplicated";
		}

		CartVO vo = new CartVO();
		vo.setUserId(userId);
		vo.setGoods_Id(goodsId);
		vo.setListType("wishlist");
		vo.setCount(null); // 보관함은 수량 없음

		wishlistService.insertGoodsToWishlistAsCart(vo);
		return "success";
	}

	/*-------------------update-end------------------------------*/

// ✅ 장바구니 → 보관함 이동
	@RequestMapping(value = "/moveToWishlist.do", method = RequestMethod.POST)
	@ResponseBody
	public String moveToWishlist(@RequestParam("userId") String userId, @RequestParam("type") String type,
			@RequestParam("itemId") String itemId, @RequestParam("imagePath") String imagePath) {
		System.out.println("=== moveToWishlist 호출됨 ===");
		System.out.println("user_id: " + userId + ", type: " + type + ", itemId: " + itemId);

		try {
			if ("book".equals(type)) {
				WishlistVO vo = new WishlistVO();
				vo.setUserId(userId);
				vo.setIsbn(itemId);
				vo.setListType("wishlist");

				WishlistVO exists = wishlistService.getWishlistBookByUserIdAndIsbn(userId, itemId, "wishlist");
				if (exists != null) {
					return "duplicated";
				}
				wishlistService.insertWishlist(vo);

			} else if ("goods".equals(type)) {
				boolean exists = wishlistService.checkGoodsWishlistExists(userId, itemId);
				if (exists) {
					return "duplicated";
				}

				CartVO vo = new CartVO();
				vo.setUserId(userId);
				vo.setGoods_Id(Integer.parseInt(itemId));
				vo.setListType("wishlist");
				vo.setImagePath(imagePath);
				vo.setCount(null); // 보관함에서는 수량 없음

				wishlistService.insertGoodsToWishlist(vo);
			}

			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping(value = "/moveToCartGoods.do", method = RequestMethod.POST)
	@ResponseBody
	public String moveToCartGoods(@RequestParam("userId") String userId, @RequestParam("goodsId") int goodsId,
			@RequestParam("imagePath") String imagePath) {
		System.out.println("=== moveToCartGoods 호출됨 ==="); // ✅ 이게 콘솔에 출력되는지 확인

		boolean exists = wishlistService.checkGoodsExistsInCart(userId, goodsId);

		if (exists) {
			wishlistService.incrementGoodsCountInCart(userId, goodsId);
		} else {
			CartVO vo = new CartVO();
			vo.setUserId(userId);
			vo.setGoods_Id(goodsId);
			vo.setImagePath(imagePath);
			vo.setCount(1);
			vo.setListType("cart");

			wishlistService.insertGoodsToWishlistAsCart(vo);
		}

		return "success";
	}

	/* -----------------update-start------------------- */
	@RequestMapping(value = "/insertWishlist.do", method = RequestMethod.POST)
	@ResponseBody
	public String insertWishlist(HttpSession session, @ModelAttribute WishlistVO wishlistVO) throws Exception {
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return "login-required";
		}

		wishlistVO.setUserId(userId);
		wishlistVO.setListType("wishlist");

		WishlistVO existing = wishlistService.getWishlistBookByUserIdAndIsbn(userId, wishlistVO.getIsbn(), "wishlist");

		if (existing != null) {
			return "duplicated";
		} else {
			// 새로 담기
			wishlistService.insertWishlist(wishlistVO);
		}

		return "success";
	}

	/* -----------------update-end--------------------- */

	@RequestMapping(value = "/deleteWishlist.do", method = RequestMethod.POST)
	public String deleteWishlist(@RequestParam("userId") String userId,
			@RequestParam("checkedItems") List<String> checkedItems) {

		List<String> isbnList = new java.util.ArrayList<>();
		List<Integer> goodsIdList = new java.util.ArrayList<>();

		for (String item : checkedItems) {
			if (item.matches("\\d+")) {
				goodsIdList.add(Integer.parseInt(item)); // 숫자면 굿즈 ID
			} else {
				isbnList.add(item); // 그 외는 ISBN
			}
		}

		if (!isbnList.isEmpty()) {
			wishlistService.deleteWishlist(userId, isbnList); // 기존 책 삭제
		}

		if (!goodsIdList.isEmpty()) {
			wishlistService.deleteGoodsFromWishlist(userId, goodsIdList); // 굿즈 삭제 추가 필요
		}

		return "redirect:/wishlist.do";
	}

}
