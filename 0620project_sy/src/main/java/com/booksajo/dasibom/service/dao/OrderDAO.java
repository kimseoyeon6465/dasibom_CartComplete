package com.booksajo.dasibom.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.GuestOrderDetailVO;
import com.booksajo.dasibom.vo.GuestOrderVO;
import com.booksajo.dasibom.vo.OrderDetailVO;
import com.booksajo.dasibom.vo.UserOrderVO;

public interface OrderDAO {

	int insertUserOrder(UserOrderVO orderVO); // user_order_table에 삽입

	int getNextOrderId(); // order_id 생성용

	void insertOrderDetail(OrderDetailVO detail);

	UserOrderVO getUserOrder(int orderId);

	List<OrderDetailVO> getUserOrderDetails(int orderId);

	void insertGuestOrder(GuestOrderVO order);

	void insertGuestOrderDetail(GuestOrderDetailVO detail);

	List<UserOrderVO> selectOrdersByUserId(String userId);

	List<OrderDetailVO> selectOrderDetailsByOrderId(int orderId);

	void updateOrderStatus(@Param("orderId") int orderId, @Param("isbn") String isbn,
			@Param("orderStatus") String orderStatus);

	void increaseSales(Map<String, Object> salesMap);

	void insertGoodsOrderDetail(OrderDetailVO detail); // user_goods_order_detail_table insert

	List<OrderDetailVO> selectGoodsOrderDetailsByOrderId(int orderId); // 굿즈 주문 상세 리스트

	List<OrderDetailVO> getUserGoodsOrderDetails(int orderId);

}
