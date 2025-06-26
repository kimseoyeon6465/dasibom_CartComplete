package com.booksajo.dasibom.vo;

public class OrderDetailVO {

	private int order_Id; // 주문 번호 (FK)
	private String isbn; // 도서 ISBN
	private int count; // 수량
	private int price; // 가격
	private int goods_Id; // ✅ 굿즈 ID (FK) — goodsVO와 통일
	private String order_Status; // 주문 상태 (예: 결제완료, 배송중 등)
	private int refundCount;// 환불 수량
	private String title;// 책 이름
	private String imagePath;// 책 사진
	private int originalPrice; // 책 정가 추가
	private String goods_name; // ✅ 굿즈 이름 — 통일
	private boolean isBook = true; // 책/굿즈 구분

	public int getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(int order_Id) {
		this.order_Id = order_Id;
	}

	public String getOrder_Status() {
		return order_Status;
	}

	public void setOrder_Status(String order_Status) {
		this.order_Status = order_Status;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRefundCount() {
		return refundCount;
	}

	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getGoodsName() {
		return goods_name;
	}

	public void setGoodsName(String goods_name) {
		this.goods_name = goods_name;
	}

	public boolean isBook() {
		return isBook;
	}

	public void setBook(boolean isBook) {
		this.isBook = isBook;
	}

	public void setOrderStatus(String order_Status) {
		this.order_Status = order_Status;
	}

	public String getOrderStatus() {
		return order_Status;
	}

	// jsp 용 커스텀 메서드

	public int getOrderId() {
		return order_Id;
	}

	public void setOrderId(int order_Id) {
		this.order_Id = order_Id;
	}

	// ✅ GoodsVO와 통일된 커스텀 getter
	public int getGoodsId() {
		return goods_Id;
	}

	public void setGoodsId(int goods_Id) {
		this.goods_Id = goods_Id;
	}

}
