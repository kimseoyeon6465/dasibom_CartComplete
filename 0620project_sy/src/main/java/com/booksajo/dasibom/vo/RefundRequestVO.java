package com.booksajo.dasibom.vo;

public class RefundRequestVO {
	private int orderId;
	private String isbn;
	private int refundCount;
	private String orderStatus; // 주문 상태 (예: 결제완료, 배송중 등)

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		// TODO Auto-generated method stub
		this.orderStatus = orderStatus;

	}

}
