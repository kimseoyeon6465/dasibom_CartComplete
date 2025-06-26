package com.booksajo.dasibom.service;

import java.util.ArrayList;

import com.booksajo.dasibom.vo.CommentVO;

public interface CommentService {

	// ��� �ۼ�
	void writeComment(CommentVO commentVO);

	// Ư�� �Խñ��� ��� ��� ��ȸ
	ArrayList<CommentVO> getAllCommentsForPost(Integer post_id);

	// ��� ����
	boolean deleteComment(int comment_id);

	// ���� �ۼ�
	void writeReplyComment(CommentVO commentVO);

	// ��� �� ��ȸ
	int getCommentCountForPost(int post_id);
	
	// ��� ����
	void updateComment(CommentVO commentVO);
	
	CommentVO getCommentById(int commentId);
}