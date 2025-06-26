package com.booksajo.dasibom.vo;

public class GuestOrderDetailVO {

	private int orderId;
	private String isbn;
	private int count;
	private int price;
	private String orderStatus;
	private String title;
	private String imagePath;

	// Getter & Setter
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	// toString
	@Override
	public String toString() {
		return "GuestOrderDetailVO [order_id=" + orderId + ", isbn=" + isbn + ", count=" + count + ", price=" + price
				+ ", order_status=" + orderStatus + ", title=" + title + ", image_path" + imagePath + "]";
	}
}
