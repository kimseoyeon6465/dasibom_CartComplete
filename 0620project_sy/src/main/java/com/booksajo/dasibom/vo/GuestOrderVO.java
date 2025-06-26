package com.booksajo.dasibom.vo;

import java.sql.Date;

public class GuestOrderVO {

	private int orderId;
	private Date orderDate;
	private String address;
	private String request;
	private String tel;
	private int sumPrice;
	private String email;
	private String receiver;

	// Getter & Setter

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {

		this.receiver = receiver;
	}

	// toString
	@Override
	public String toString() {
		return "GuestOrderVO [order_id=" + orderId + ", order_date=" + orderDate + ", receiver=" + receiver
				+ ", address=" + address + ", request=" + request + ", tel=" + tel + ", sum_price=" + sumPrice
				+ ", email=" + email + "]";
	}
}
