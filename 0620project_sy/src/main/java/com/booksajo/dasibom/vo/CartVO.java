package com.booksajo.dasibom.vo;

import java.sql.Timestamp;

public class CartVO {
	private String userId;
	private String isbn;
	private Timestamp addedDate;
	private String imagePath;
	private String listType;
	private Integer count;
	private String title; // 책 제목
	private int price; // 책 가격
	private Integer goods_Id;
	private String goods_name;
	private String category; // optional (굿즈 분류)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Timestamp getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Timestamp addedDate) {
		this.addedDate = addedDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Integer getGoods_Id() {
		return goods_Id;
	}

	public void setGoods_Id(Integer goods_Id) {
		this.goods_Id = goods_Id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// --- JSP에서 사용하기 위한 커스텀 getter ---
	public Integer getGoodsId() {
		return getGoods_Id();
	}

	public void setGoodsId(Integer goods_Id) {
		this.goods_Id = goods_Id;
	}

	// ✅ JSP에서 ${cart.user_id}처럼 쓰고 싶을 때 지원
	public String getUser_Id() {
		return userId;
	}

	public void setUser_Id(String userId) {
		this.userId = userId;
	}

	public String getImage_Path() {
		return imagePath;
	}

	public void setImage_Path(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getList_Type() {
		return listType;
	}

	public void setList_Type(String listType) {
		this.listType = listType;
	}

	public Integer getGoods_id() {
		return goods_Id;
	}

	public void setGoods_id(Integer goods_Id) {
		this.goods_Id = goods_Id;
	}

	public String getGoodsName() {
		return goods_name;
	}

	public void setGoodsName(String goods_name) {
		this.goods_name = goods_name;
	}

}
