package com.booksajo.dasibom.service;

import java.util.ArrayList;

import com.booksajo.dasibom.vo.CommentVO;

public interface CommentService {

	// 댓글 작성
	void writeComment(CommentVO commentVO);

	// 특정 게시글의 모든 댓글 조회
	ArrayList<CommentVO> getAllCommentsForPost(Integer post_id);

	// 댓글 삭제
	boolean deleteComment(int comment_id);

	// 대댓글 작성
	void writeReplyComment(CommentVO commentVO);

	// 댓글 수 조회
	int getCommentCountForPost(int post_id);
	
	// 댓글 수정
	void updateComment(CommentVO commentVO);
	
	CommentVO getCommentById(int commentId);
}