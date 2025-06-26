package com.booksajo.dasibom.service;

import java.util.List;
import java.util.Map;

import com.booksajo.dasibom.vo.MyOrderVO;
import com.booksajo.dasibom.vo.OrderDetailVO;
import com.booksajo.dasibom.vo.UserOrderVO;

public interface OrderService {
	// 페이징 구매내역 데이터 가져오기 (조건 포함)
	public List<MyOrderVO> getOrdersByUserId(Map<String, Object> params);

	// 구매내역 페이지 개수 (조건 포함)
	public int countOrdersByUserId(Map<String, Object> params);

	// (임시) 주문취소, 환불 페이지에 취소/환불 원하는 구매내역 객체, order 전달
	public MyOrderVO getOrderByOrderId(int orderId);

	int insertUserOrder(UserOrderVO orderVO); // user_order_table 삽입

	int getNextOrderId(); // 시퀀스 또는 max + 1 방식

	void insertOrderDetails(int orderId, List<OrderDetailVO> detailList);

	void insertOrderDetail(OrderDetailVO detail);

	UserOrderVO getUserOrder(int orderId);

	List<UserOrderVO> getOrdersByUserId(String userId);

	List<OrderDetailVO> getOrderDetailsByOrderId(int orderId);

	List<OrderDetailVO> getUserOrderDetails(int orderId);

	void updateOrderStatusToCancelled(int orderId, String isbn);

	// ✅ 추가됨: 굿즈 주문 상세 등록
	void insertGoodsOrderDetails(int orderId, List<OrderDetailVO> goodsList);

	// ✅ 추가됨: 굿즈 주문 상세 조회
	List<OrderDetailVO> getUserGoodsOrderDetails(int orderId);
}
