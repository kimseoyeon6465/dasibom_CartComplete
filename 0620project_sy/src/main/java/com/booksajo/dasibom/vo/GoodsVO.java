package com.booksajo.dasibom.vo;

import java.util.Date;

public class GoodsVO {
	private String userId; // user_id

	private int goods_Id; // 굿즈 ID
	private String goods_name; // 굿즈 이름
	private String category; // 카테고리
	private int price; // 가격
	private String image_path; // 이미지 경로
	private String listType; // list_type ('wishlist' or 'cart')
	private int count; // 수량
	private Date addedDate; // added_date

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public int getGoods_Id() {
		return goods_Id;
	}

	public void setGoods_Id(int goods_Id) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	// --- JSP에서 사용하기 위한 커스텀 getter ---
	public int getGoodsId() {
		return getGoods_Id();
	}

	public void setGoodsId(int goods_Id) {
		this.goods_Id = goods_Id;
	}

	public String getGoodsName() {
		return getGoods_name();
	}

	public void setGoodsName(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getImagePath() {
		return getImage_path();
	}

	public void setImagePath(String image_path) {
		this.image_path = image_path;
	}
}
