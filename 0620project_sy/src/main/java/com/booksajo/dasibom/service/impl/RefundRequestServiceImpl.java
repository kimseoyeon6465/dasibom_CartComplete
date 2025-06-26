package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.service.RefundRequestService;
import com.booksajo.dasibom.service.dao.RefundRequestDAO;
import com.booksajo.dasibom.vo.OrderDetailVO;
import com.booksajo.dasibom.vo.RefundRequestVO;

@Service("refundService")
public class RefundRequestServiceImpl implements RefundRequestService {

	@Autowired
	private RefundRequestDAO refunddao;

	@Override
	public void processRefundItems(List<RefundRequestVO> refundList) {
		if (refundList == null || refundList.isEmpty())
			return;

		for (RefundRequestVO item : refundList) {
			// 二쇰Ц �긽�꽭 議고쉶
			OrderDetailVO detail = refunddao.selectOrderDetail(item.getOrderId(), item.getIsbn());

			if (detail == null)
				continue;

			int total = detail.getCount();
			int alreadyRefunded = detail.getRefundCount();
			int newTotalRefunded = alreadyRefunded + item.getRefundCount();

			// �긽�깭 寃곗젙
			String status = (newTotalRefunded >= total) ? "�솚遺덉셿猷�" : detail.getOrder_Status();
			item.setOrderStatus(status);

			// �솚遺� 泥섎━
			refunddao.updateRefund(item);
		}
	}

	@Override
	public void processRefund(int orderId, Map<String, Integer> refundMap) {
		List<RefundRequestVO> refundList = new ArrayList<RefundRequestVO>();

		for (Map.Entry<String, Integer> entry : refundMap.entrySet()) {
			String isbn = entry.getKey();
			int refundCount = entry.getValue();

			// 1截뤴깵 湲곗〈 二쇰Ц �긽�꽭 議고쉶
			OrderDetailVO existing = refunddao.selectOrderDetail(orderId, isbn);

			if (existing == null)
				continue; // �옒紐삳맂 ISBN�씪 寃쎌슦 臾댁떆

			int totalCount = existing.getCount(); // �쟾泥� 二쇰Ц �닔�웾
			int alreadyRefunded = existing.getRefundCount(); // 湲곗〈 �솚遺� �닔�웾

			int newRefundTotal = alreadyRefunded + refundCount;

			// 2截뤴깵 �긽�깭 寃곗젙
			String newStatus = (newRefundTotal >= totalCount) ? "�솚遺덉셿猷�" : existing.getOrder_Status();

			// 3截뤴깵 �솚遺� �슂泥� VO 援ъ꽦
			RefundRequestVO vo = new RefundRequestVO();
			vo.setOrderId(orderId);
			vo.setIsbn(isbn);
			vo.setRefundCount(refundCount);
			vo.setOrderStatus(newStatus);

			refundList.add(vo);
		}

		// 4截뤴깵 DB �뾽�뜲�씠�듃
		for (RefundRequestVO vo : refundList) {
			refunddao.updateRefund(vo);
		}
	}
}
