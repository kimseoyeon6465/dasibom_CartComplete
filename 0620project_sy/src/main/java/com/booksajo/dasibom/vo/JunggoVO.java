package com.booksajo.dasibom.vo;

public class JunggoVO {
	private int post_id;
	private String content;
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public JunggoVO() { 	}
	
	public JunggoVO(int post_id, String content) {
		this.post_id = post_id;
		this.content = content;	
	}
	
}
