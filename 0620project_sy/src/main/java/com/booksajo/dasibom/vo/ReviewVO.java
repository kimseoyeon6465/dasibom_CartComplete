package com.booksajo.dasibom.vo;

import java.util.Date;

public class ReviewVO {

	private int review_Id;
    private String isbn;           // ���� ISBN
    private String user_Id;         // �ۼ���
    private String review_text;     // ���� ����
    private double review_score;    // ����
    private Date review_date;       // �ۼ���
    private String image_path;      // ÷�� �̹���
    private int likes;             // ���ƿ� ��
    

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}
	public double getReview_score() {
		return review_score;
	}
	public void setReview_score(double review_score) {
		this.review_score = review_score;
	}
	public Date getReview_date() {
		return review_date;
	}
	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getReview_Id() {
		return review_Id;
	}
	public void setReview_Id(int review_Id) {
		this.review_Id = review_Id;
	}
    
}
