package com.booksajo.dasibom.vo;

import java.util.Date;

public class ReviewCommentVO {
	
	private int review_comment_Id;
    private int review_Id;
    private String user_Id;
    private String review_comment_text;
    private Date review_comment_date;
    
   
	public int getReview_comment_Id() {
		return review_comment_Id;
	}
	public void setReview_comment_Id(int review_comment_Id) {
		this.review_comment_Id = review_comment_Id;
	}
	public int getReview_Id() {
		return review_Id;
	}
	public void setReview_Id(int review_Id) {
		this.review_Id = review_Id;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getReview_comment_text() {
		return review_comment_text;
	}
	public void setReview_comment_text(String review_comment_text) {
		this.review_comment_text = review_comment_text;
	}
	public Date getReview_comment_date() {
		return review_comment_date;
	}
	public void setReview_comment_date(Date review_comment_date) {
		this.review_comment_date = review_comment_date;
	}

    
    
    
    
}
