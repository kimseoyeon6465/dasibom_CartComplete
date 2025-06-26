package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksajo.dasibom.service.CommentService;
import com.booksajo.dasibom.service.dao.CommentDAO;
import com.booksajo.dasibom.vo.CommentVO;


@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Override
	@Transactional
	public void writeComment(CommentVO commentVO) {
		commentDAO.writeComments(commentVO);
	}

	@Override
	public ArrayList<CommentVO> getAllCommentsForPost(Integer post_id) {
		return commentDAO.getAllCommentsForPost(post_id);
	}

	@Override
	public boolean deleteComment(int comment_id) {
		return commentDAO.deleteComment(comment_id);
	}

	@Override
	public void writeReplyComment(CommentVO commentVO) {
		commentDAO.writeReplyComment(commentVO);
	}

	@Override
	public int getCommentCountForPost(int post_id) {
		return commentDAO.getCommentCountForPost(post_id);
	}

	@Override
	public void updateComment(CommentVO commentVO) {
		commentDAO.updateComment(commentVO);
	}

	@Override
	public CommentVO getCommentById(int commentId) {
		return commentDAO.getCommentById(commentId);
	}
}
