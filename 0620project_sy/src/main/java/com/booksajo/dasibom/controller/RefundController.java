package com.booksajo.dasibom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booksajo.dasibom.service.OrderService;
import com.booksajo.dasibom.service.RefundRequestService;
import com.booksajo.dasibom.vo.OrderDetailVO;
import com.booksajo.dasibom.vo.UserOrderVO;

@Controller
public class RefundController {
	@Resource(name = "orderService")
	private OrderService orderService;

	@Resource(name = "refundService")
	private RefundRequestService refundService;

	@RequestMapping("/Refund.do")
	public String showRefundPage(@RequestParam("orderId") int orderId, Model model) {
		UserOrderVO order = orderService.getUserOrder(orderId);
		List<OrderDetailVO> detailList = orderService.getUserOrderDetails(orderId);

		model.addAttribute("order", order);
		model.addAttribute("orderDetailList", detailList);
		return "Refund"; // refund.jsp
	}

	@RequestMapping(value = "/processRefund.do", method = RequestMethod.POST)
	public String processRefund(HttpServletRequest request, RedirectAttributes redirectAttr) {

		// ✅ [1] order_id 받기 (form의 hidden input으로부터)
		int orderId = Integer.parseInt(request.getParameter("orderId"));

		// ✅ [2] 선택된 ISBN들 받기
		String[] selectedIsbns = request.getParameterValues("selectedItems");

		if (selectedIsbns == null || selectedIsbns.length == 0) {
			redirectAttr.addFlashAttribute("error", "환불할 상품을 선택해주세요.");
			return "redirect:/Refund.do?orderId=" + orderId;
		}

		// ✅ [3] 환불 Map<isbn, count> 구성
		Map<String, Integer> refundMap = new HashMap<String, Integer>();

		for (String isbn : selectedIsbns) {
			String countStr = request.getParameter("refundCount_" + isbn);
			try {
				int count = Integer.parseInt(countStr);
				if (count > 0) {
					refundMap.put(isbn, count);
				}
			} catch (NumberFormatException e) {
				// 무시하거나 기본값 설정 가능
				refundMap.put(isbn, 1);
			}
		}

		if (refundMap.isEmpty()) {
			redirectAttr.addFlashAttribute("error", "유효한 환불 수량을 선택해주세요.");
			return "redirect:/Refund.do?orderId=" + orderId;
		}

		// ✅ [4] 서비스에 전달
		refundService.processRefund(orderId, refundMap);

		// ✅ [5] 완료 메시지 및 리다이렉트
		redirectAttr.addFlashAttribute("msg", "환불 요청이 완료되었습니다.");

		return "redirect:/myOrders.do";
	}
}
