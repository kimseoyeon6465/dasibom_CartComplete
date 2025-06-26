package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;

import com.booksajo.dasibom.vo.CommentVO;



public interface CommentDAO {

	void writeComments(CommentVO commentVO);

	ArrayList<CommentVO> getAllCommentsForPost(Integer post_id);

	boolean deleteComment(int comment_id);

	void writeReplyComment(CommentVO commentVO);

	int getCommentCountForPost(int post_id);

	void updateComment(CommentVO commentVO);

	public CommentVO getCommentById(int commentId);
}
