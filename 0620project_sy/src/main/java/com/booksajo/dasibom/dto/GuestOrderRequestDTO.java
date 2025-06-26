package com.booksajo.dasibom.dto;

import java.util.List;

import com.booksajo.dasibom.vo.GuestOrderDetailVO;

public class GuestOrderRequestDTO {
	private String receiver;
	private String tel;
	private String address;
	private String email;
	private String request;
	private int sumPrice;
	private List<GuestOrderDetailVO> orderList;

	// Getter/Setter
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	public List<GuestOrderDetailVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<GuestOrderDetailVO> orderList) {
		this.orderList = orderList;
	}
}
