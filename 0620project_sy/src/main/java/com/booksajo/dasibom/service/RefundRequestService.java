package com.booksajo.dasibom.service;

import java.util.List;
import java.util.Map;

import com.booksajo.dasibom.vo.RefundRequestVO;

public interface RefundRequestService {
	public void processRefundItems(List<RefundRequestVO> refundList);

	public void processRefund(int orderId, Map<String, Integer> refundMap);

}
