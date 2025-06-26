package com.booksajo.dasibom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.service.GuestOrderService;
import com.booksajo.dasibom.service.dao.GuestOrderDAO;
import com.booksajo.dasibom.vo.GuestOrderDetailVO;
import com.booksajo.dasibom.vo.GuestOrderVO;

@Service("guestOrderService")
public class GuestOrderServiceImpl implements GuestOrderService {

	@Resource(name = "guestOrderDAO")
	private GuestOrderDAO guestOrderDAO;

	@Autowired
	private SqlSessionTemplate sqlSession;

	// �윍� 二쇰Ц ID濡� 鍮꾪쉶�썝 二쇰Ц �젙蹂� 議고쉶
	@Override
	public GuestOrderVO getGuestOrderById(int orderId) {
		return guestOrderDAO.getGuestOrderById(orderId);
	}

	// �윍� �씠硫붿씪濡� 鍮꾪쉶�썝 二쇰Ц 紐⑸줉 議고쉶
	@Override
	public List<GuestOrderVO> getGuestOrdersByEmail(String email) {
		return guestOrderDAO.getGuestOrdersByEmail(email);
	}

	// �윍� �듅�젙 二쇰Ц�쓽 �긽�꽭 �빆紐� 議고쉶
	@Override
	public List<GuestOrderDetailVO> getGuestOrderDetailsByOrderId(int orderId) {
		return guestOrderDAO.getGuestOrderDetailsByOrderId(orderId);
	}

	// �윍� 鍮꾪쉶�썝 二쇰Ц �깮�꽦留� 泥섎━ (�떆���뒪濡� order_id �솗蹂�)
	@Override
	public int createGuestOrder(GuestOrderVO order) {
		int orderId = sqlSession.selectOne("com.booksajo.dasibom.service.dao.GuestOrderDAO.getOrderSeqNextVal");
		order.setOrderId(orderId);
		guestOrderDAO.insertGuestOrder(order);
		return orderId;
	}

	// �윍� 鍮꾪쉶�썝 二쇰Ц �긽�꽭 由ъ뒪�듃 �벑濡�
	@Override
	public void insertGuestOrderDetails(int orderId, List<GuestOrderDetailVO> detailList) {
		for (GuestOrderDetailVO detail : detailList) {
			detail.setOrderId(orderId);
			guestOrderDAO.insertGuestOrderDetail(detail);

			Map<String, Object> salesMap = new HashMap<String, Object>();
			salesMap.put("isbn", detail.getIsbn());
			salesMap.put("count", detail.getCount());

			guestOrderDAO.increaseSales(salesMap);
		}
	}

	@Override
	public int insertGuestOrder(GuestOrderVO guestOrderVO, List<GuestOrderDetailVO> detailList) {
		guestOrderDAO.insertGuestOrder(guestOrderVO);
		int orderId = guestOrderVO.getOrderId(); // selectKey濡� �꽕�젙�맂 ID

		for (GuestOrderDetailVO detail : detailList) {
			detail.setOrderId(orderId);
			guestOrderDAO.insertGuestOrderDetail(detail);
		}

		return orderId;
	}

	@Override
	public int insertGuestOrderWithDetails(GuestOrderVO guestOrderVO, List<GuestOrderDetailVO> detailList) {
		int orderId = sqlSession.selectOne("getGuestOrderSeqNextVal");
		guestOrderVO.setOrderId(orderId);

		// �윊� 癒쇱� 遺�紐� �뀒�씠釉� insert
		guestOrderDAO.insertGuestOrder(guestOrderVO);
		System.out.println("�윍� [GuestOrderServiceImpl] insertGuestOrderWithDetails �떆�옉");
		System.out.println("�웺� �깮�꽦�맂 order_id = " + orderId);
		for (GuestOrderDetailVO detail : detailList) {
			detail.setOrderId(orderId);
			guestOrderDAO.insertGuestOrderDetail(detail); // �씠 �떆�젏�뿉 遺�紐⑦궎媛� �엳�뼱�빞 �븿
			System.out.println("�윋� ISBN: " + detail.getIsbn() + ", Count: " + detail.getCount());

		}
		System.out.println("🟢 [DEBUG] sumPrice before insert: " + guestOrderVO.getSumPrice());

		return orderId;
	}

}
