package com.booksajo.dasibom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.service.ReviewCommentService;
import com.booksajo.dasibom.service.dao.ReviewCommentDAO;
import com.booksajo.dasibom.vo.ReviewCommentVO;


@Service("reviewCommentService")
public class ReviewCommentServiceImpl implements ReviewCommentService {

	    @Autowired
	    private ReviewCommentDAO reviewCommentDAO;
	
	    @Override
	    public void insertComment(ReviewCommentVO commentVO) throws Exception {
	        reviewCommentDAO.insertComment(commentVO);
	    }
	
	    @Override
	    public List<ReviewCommentVO> getCommentsByReviewId(int review_id) {
	        return reviewCommentDAO.getCommentsByReviewId(review_id);
	    }
	
	    @Override
	    public void updateComment(ReviewCommentVO commentVO) throws Exception {
	        reviewCommentDAO.updateComment(commentVO);
	    }
	
	    @Override
	    public void deleteComment(ReviewCommentVO commentVO) throws Exception {
	        reviewCommentDAO.deleteComment(commentVO);
	    }
	}
