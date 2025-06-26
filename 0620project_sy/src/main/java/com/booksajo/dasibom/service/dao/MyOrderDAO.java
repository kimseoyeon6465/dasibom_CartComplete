package com.booksajo.dasibom.service.dao;

import java.util.List;
import java.util.Map;

import com.booksajo.dasibom.vo.MyOrderVO;

public interface MyOrderDAO {

	public List<MyOrderVO> getOrdersByUserId(Map<String, Object> params);

	public int countOrdersByUserId(Map<String, Object> params);
	
	// (юс╫ц)
	public MyOrderVO getOrderByOrderId(int orderId);
}
