package com.booksajo.dasibom.vo;

import java.sql.Date;
import java.util.List;

public class UserOrderVO {

	private int order_Id;
	private Date order_Date;
	private String user_Id;
	private int sum_Price;

	private String receiver;
	private String tel;
	private String address;
	private String request;
	private int usedPoint; // ✅ 추가: 사용 포인트
	private String order_Status; // 주문 상태
	private List<OrderDetailVO> detail_List;

	public int getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(int order_Id) {
		this.order_Id = order_Id;
	}

	public Date getOrder_Date() {
		return order_Date;
	}

	public void setOrder_Date(Date order_Date) {
		this.order_Date = order_Date;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getSum_Price() {
		return sum_Price;
	}

	public void setSum_Price(int sum_Price) {
		this.sum_Price = sum_Price;
	}

	public String getOrder_Status() {
		return order_Status;
	}

	public void setOrder_Status(String order_Status) {
		this.order_Status = order_Status;
	}

	public List<OrderDetailVO> getDetail_List() {
		return detail_List;
	}

	public void setDetail_List(List<OrderDetailVO> detail_List) {
		this.detail_List = detail_List;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}

	// jsp에서 _인식하도록 커스텀 함수 생성
	public int getOrderId() {
		return order_Id;
	}

	public void setOrderId(int orderId) {
		this.order_Id = orderId;
	}

	public java.sql.Date getOrderDate() {
		return order_Date;
	}

	public void setOrderDate(Date order_Date) {
		this.order_Date = order_Date;
	}

	public String getUserId() {
		return user_Id;
	}

	public void setUserId(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getSumPrice() {
		return sum_Price;
	}

	public void setSumPrice(int sum_Price) {
		this.sum_Price = sum_Price;
	}

	public String getOrderStatus() {
		return order_Status;
	}

	public void setOrderStatus(String order_Status) {
		this.order_Status = order_Status;
	}

	public java.util.List<OrderDetailVO> getDetailList() {
		return detail_List;
	}

	public void setDetailList(List<OrderDetailVO> detail_List) {
		this.detail_List = detail_List;
	}

}
