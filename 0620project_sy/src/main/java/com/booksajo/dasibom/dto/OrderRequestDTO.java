package com.booksajo.dasibom.dto;

import java.util.List;

import com.booksajo.dasibom.vo.OrderDetailVO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestDTO {
	@JsonProperty("user_Id")
	private String user_Id;
	private String receiver;
	private String tel;
	private String address;
	private String request;
	private int sumPrice;
	private int usedPoint; // �쐟 異붽��맂 �븘�뱶
	private String email;
	private int delivery;

	private List<OrderDetailVO> bookOrderList; // ✅ 도서 주문용
	private List<OrderDetailVO> goodsOrderList; // ✅ 굿즈 주문용

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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sum_Price) {
		this.sumPrice = sum_Price;
	}

	public int getUsedPoint() {
		return usedPoint;
	}

	public void setUsedPoint(int usedPoint) {
		this.usedPoint = usedPoint;
	}

	public List<OrderDetailVO> getBookOrderList() {
		return bookOrderList;
	}

	public void setBookOrderList(List<OrderDetailVO> bookOrderList) {
		this.bookOrderList = bookOrderList;
	}

	public List<OrderDetailVO> getGoodsOrderList() {
		return goodsOrderList;
	}

	public void setGoodsOrderList(List<OrderDetailVO> goodsOrderList) {
		this.goodsOrderList = goodsOrderList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDelivery() {
		return delivery;
	}

	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}
}
