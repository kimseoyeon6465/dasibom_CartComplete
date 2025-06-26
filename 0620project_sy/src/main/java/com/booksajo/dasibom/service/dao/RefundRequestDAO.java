package com.booksajo.dasibom.service.dao;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.OrderDetailVO;
import com.booksajo.dasibom.vo.RefundRequestVO;

public interface RefundRequestDAO {
	void updateRefund(RefundRequestVO vo); // ȯ�� ó�� (update)

	OrderDetailVO selectOrderDetail(@Param("orderId") int orderId, @Param("isbn") String isbn);

}
