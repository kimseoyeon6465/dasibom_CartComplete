package com.booksajo.dasibom.vo;

import java.util.Date;

public class CommentVO {
	private int post_id;
	private int comment_id;
	private int parent_comment_id;
	private String comment_contents;
	private String user_id;
	private Date comment_date;

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getParent_comment_id() {
		return parent_comment_id;
	}

	public void setParent_comment_id(int parent_comment_id) {
		this.parent_comment_id = parent_comment_id;
	}

	public String getComment_contents() {
		return comment_contents;
	}

	public void setComment_contents(String comment_contents) {
		this.comment_contents = comment_contents;
	}

	public String getComment_contentsWithBr() {
		if (comment_contents == null)
			return "";
		return comment_contents.replaceAll("(\r\n|\r|\n)", "<br>");
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
}