package com.booksajo.dasibom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksajo.dasibom.service.OrderService;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.MyOrderVO;

@Controller
public class MyOrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	/*
	 * 사용자의 전체 구매 내역 불러오기 (페이징 기법, 도서명 검색)
	 */
	@RequestMapping("/myOrderList.do")
	public String myOrderList(
			@RequestParam(defaultValue = "1") int page, 
			@RequestParam(required = false) String title, 	
			@RequestParam(required = false) String startDate,
		    @RequestParam(required = false) String endDate,
			HttpSession session, 
			Model model) {

		Integer userSeq = (Integer) session.getAttribute("user_seq");
		if (userSeq == null) {
			return "redirect:/login.do";
		}

		// session객체에서 user_id 가져오기. 
		// >> (후에session에 있는 user_id컬럼을 제거하고 user_seq만 남길 것이기 때문에 필요함!)
		String userId = userService.getUserBySeq(userSeq).getUser_Id();

		int pageSize = 10;
		int offset = (page - 1) * pageSize;

		// 파라미터 구성
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("offset", offset);
		params.put("limitValue", offset + pageSize);

		// 도서명으로 검색하면 도서명을 Map에 put.
		title = title != null ? title.trim() : null;
		if (title != null && !title.isEmpty()) {
		    params.put("title", title + "%"); // "데" 검색하면 sql에 "데%"로 select
		}
		
		// 구매기간으로 검색하면 시작일과 종료일을 Map에 put.
		// 및 유효성 검사
		if (startDate != null && !startDate.trim().isEmpty()) {
		    params.put("startDate", startDate.trim());
		}
		if (endDate != null && !endDate.trim().isEmpty()) {
		    params.put("endDate", endDate.trim());
		}
		
		System.out.println("검색 시작일: " + startDate);
		System.out.println("검색 종료일: " + endDate);
		System.out.println("params: " + params);


		// 구매내역 데이터 가져오기
		List<MyOrderVO> orderList = orderService.getOrdersByUserId(params);

		// 총 개수도 검색 조건 반영해서 가져오기
		int totalCount = orderService.countOrdersByUserId(params);

		// JSP로 데이터 전달
		model.addAttribute("orderList", orderList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", (int) Math.ceil((double) totalCount / pageSize));
		model.addAttribute("searchTitle", title); // 도서명 input 값 유지용
		model.addAttribute("startDate", startDate); // 시작일
		model.addAttribute("endDate", endDate); // 종료일

		return "myOrderList";
	}

	// (임시)주문취소 페이지로 이동
	@RequestMapping("/cancelOrder.do")
	public String cancelOrderPage(@RequestParam("orderId") int orderId, Model model) {
		MyOrderVO order = orderService.getOrderByOrderId(orderId); // 필요한 정보만 가져오는 쿼리 작성
		model.addAttribute("order", order);
		return "cancelOrder"; // cancelOrder.jsp
	}

	// (임시)환불 페이지로 이동
	@RequestMapping("/refundOrder.do")
	public String refundOrderPage(@RequestParam("orderId") int orderId, Model model) {
		MyOrderVO order = orderService.getOrderByOrderId(orderId); // 같은 쿼리 사용 가능
		model.addAttribute("order", order);
		return "refundOrder";
	}

}
