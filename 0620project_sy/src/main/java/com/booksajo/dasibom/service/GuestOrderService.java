package com.booksajo.dasibom.service;

import java.util.List;

import com.booksajo.dasibom.vo.GuestOrderDetailVO;
import com.booksajo.dasibom.vo.GuestOrderVO;

public interface GuestOrderService {

	// 🔹 이메일로 비회원 주문 조회
	List<GuestOrderVO> getGuestOrdersByEmail(String email);

	// 🔹 특정 주문 ID로 상세 조회
	List<GuestOrderDetailVO> getGuestOrderDetailsByOrderId(int orderId);

	int insertGuestOrder(GuestOrderVO guestOrderVO, List<GuestOrderDetailVO> detailList);

	int createGuestOrder(GuestOrderVO order); // 시퀀스 직접 조회 시

	GuestOrderVO getGuestOrderById(int orderId);

	void insertGuestOrderDetails(int orderId, List<GuestOrderDetailVO> detailList);

	int insertGuestOrderWithDetails(GuestOrderVO order, List<GuestOrderDetailVO> detailList);// 새로 추가

}
