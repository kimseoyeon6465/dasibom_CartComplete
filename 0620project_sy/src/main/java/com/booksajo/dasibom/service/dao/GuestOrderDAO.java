package com.booksajo.dasibom.service.dao;

import java.util.List;
import java.util.Map;

import com.booksajo.dasibom.vo.GuestOrderDetailVO;
import com.booksajo.dasibom.vo.GuestOrderVO;

public interface GuestOrderDAO {

	// 🔹 이메일로 비회원 주문 전체 조회
	List<GuestOrderVO> getGuestOrdersByEmail(String email);

	// 🔹 특정 비회원 주문의 상세 항목 조회
	List<GuestOrderDetailVO> getGuestOrderDetailsByOrderId(int orderId);

	int insertGuestOrder(GuestOrderVO guestOrderVO);

	int insertGuestOrderDetail(GuestOrderDetailVO guestOrderDetailVO);

	GuestOrderVO getGuestOrderById(int orderId);

	int getOrderSeqNextVal(); // ← 만약 selectOne 쓰는 방식 쓴다면

	void increaseSales(Map<String, Object> salesMap);

}
