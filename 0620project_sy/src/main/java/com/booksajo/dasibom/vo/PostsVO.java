package com.booksajo.dasibom.vo;

import java.util.Date;

public class PostsVO {
	private int post_id;
	private String title;
	private String post_contents;
	private String user_id;
	private String category;
	private Date post_date;
	private int commentCount;
	private String image_path;
	private boolean hasImageInContent;

	public PostsVO() {
	}

	public PostsVO(int post_id, String title, String post_contents, String user_id, String category, Date post_date,
			int commentCount, boolean hasImageInContent) {
		this.post_id = post_id;
		this.title = title;
		this.post_contents = post_contents;
		this.user_id = user_id;
		this.category = category;
		this.post_date = post_date;
		this.commentCount = commentCount;
		this.hasImageInContent = hasImageInContent;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPost_contents() {
		return post_contents;
	}

	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}

	public String getPostContentsWithBr() {
		if (post_contents == null)
			return "";
		return post_contents.replaceAll("(\r\n|\r|\n)", "<br>");
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public boolean isHasImageInContent() {
		return hasImageInContent;
	}

	public void setHasImageInContent(boolean hasImageInContent) {
		this.hasImageInContent = hasImageInContent;
	}
}