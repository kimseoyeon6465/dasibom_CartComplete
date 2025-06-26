package com.booksajo.dasibom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booksajo.dasibom.dto.GuestOrderRequestDTO;
import com.booksajo.dasibom.service.GuestOrderService;
import com.booksajo.dasibom.vo.GuestOrderDetailVO;
import com.booksajo.dasibom.vo.GuestOrderVO;

@Controller
public class GuestOrderController {
	@Resource(name = "guestOrderService")
	private GuestOrderService guestOrderService;

	@RequestMapping(value = "/guestOrderList.do", method = RequestMethod.GET)
	public String getGuestOrdersByEmail(@RequestParam("email") String email, Model model) {
		List<GuestOrderVO> guestOrders = guestOrderService.getGuestOrdersByEmail(email);
		model.addAttribute("guestOrders", guestOrders);
		return "guestOrderList"; // → guestOrderList.jsp에서 출력
	}

	@RequestMapping(value = "/guestOrderDetail.do", method = RequestMethod.GET)
	public String getGuestOrderDetails(@RequestParam("orderId") int orderId, Model model) {
		GuestOrderVO guestOrder = guestOrderService.getGuestOrderById(orderId);
		List<GuestOrderDetailVO> details = guestOrderService.getGuestOrderDetailsByOrderId(orderId);

		model.addAttribute("guestOrder", guestOrder); // 주문 정보
		model.addAttribute("orderDetails", details); // 상세 리스트

		return "guestOrderDetail"; // → guestOrderDetail.jsp에서 출력
	}

	@RequestMapping(value = "/guestOrder.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> processGuestOrder(@RequestBody GuestOrderRequestDTO dto, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			GuestOrderVO order = new GuestOrderVO();
			order.setReceiver(dto.getReceiver());
			order.setAddress(dto.getAddress());
			order.setRequest(dto.getRequest());
			order.setTel(dto.getTel());
			order.setEmail(dto.getEmail());
			order.setSumPrice(dto.getSumPrice());

			// ✅ 하나의 메서드에서 일괄 insert 처리
			int orderId = guestOrderService.insertGuestOrderWithDetails(order, dto.getOrderList());

			session.setAttribute("lastGuestOrderId", orderId);
			result.put("result", "success");
			System.out.println("📥 [GuestOrderController] 비회원 주문 접수 시작");
			System.out.println("📦 주문자 이메일: " + dto.getEmail());
			System.out.println("📦 주문 수: " + dto.getOrderList().size());
			System.out.println("🟡 sum_Price from DTO: " + dto.getSumPrice());
			System.out.println("🟡 sumPrice in VO before insert: " + order.getSumPrice());

		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "error");
		}

		return result;
	}

	@RequestMapping(value = "/guestOrderForm.do", method = RequestMethod.GET)
	public String guestOrderForm(Model model) {
		List<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>();

		Map<String, Object> book1 = new HashMap<String, Object>();
		book1.put("isbn", "9788901249485");
		book1.put("title", "미드나잇 라이브러리");
		book1.put("price", 13800);
		book1.put("count", 2);
		book1.put("imagePath", "https://shopping-phinf.pstatic.net/main_3243818/32438188779.20230214162555.jpg");

		Map<String, Object> book2 = new HashMap<String, Object>();
		book2.put("isbn", "9788956055461");
		book2.put("title", "어린왕자");
		book2.put("price", 10000);
		book2.put("count", 1);
		book2.put("imagePath", "https://shopping-phinf.pstatic.net/main_5474784/54747847962.20250523091959.jpg");

		bookList.add(book1);
		bookList.add(book2);

		model.addAttribute("bookList", bookList);
		return "guestOrderForm";
	}

	@RequestMapping("/guestPaymentComplete.do")
	public String showGuestPaymentCompletePage(HttpSession session, Model model) {
		Integer orderId = (Integer) session.getAttribute("lastGuestOrderId");

		if (orderId != null) {
			GuestOrderVO guestOrder = guestOrderService.getGuestOrderById(orderId);
			List<GuestOrderDetailVO> orderList = guestOrderService.getGuestOrderDetailsByOrderId(orderId);

			model.addAttribute("guestOrder", guestOrder);
			model.addAttribute("orderList", orderList);
		}

		return "guestPaymentComplete";
	}
}
