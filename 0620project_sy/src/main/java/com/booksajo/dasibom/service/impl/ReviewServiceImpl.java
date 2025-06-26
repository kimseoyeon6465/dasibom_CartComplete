package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksajo.dasibom.service.ReviewService;
import com.booksajo.dasibom.service.dao.ReviewCommentDAO;
import com.booksajo.dasibom.service.dao.ReviewDAO;
import com.booksajo.dasibom.vo.ReviewVO;





@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	@Resource(name = "reviewDAO")
    private ReviewDAO reviewDAO;

    @Resource(name = "reviewCommentDAO") 
    private ReviewCommentDAO reviewCommentDAO;
    
	
	@Override
	@Transactional
	public ArrayList<ReviewVO> getAllReview() {
		// TODO Auto-generated method stub
		return reviewDAO.getAllReview();
	}	
	
	@Override
	public ArrayList<ReviewVO> getAllReviewByIsbn(String isbn) {
	    return reviewDAO.getAllReviewByIsbn(isbn);
	}
	
	
	@Override
	public void insertReview(ReviewVO reviewVO) {
		reviewDAO.insertReview(reviewVO);
	}
	
	@Override
	public void updateReview(ReviewVO reviewVO) {
		reviewDAO.updateReview(reviewVO);
	}

	/*
	 * @Override public void deleteReview(ReviewVO reviewVO) {
	 * reviewDAO.deleteReview(reviewVO); }
	 */
	
	@Transactional
	@Override
	public void deleteReview(int review_Id, String user_Id) {
	    // 1. 댓글 먼저 삭제
	    reviewCommentDAO.deleteByReviewId(review_Id);

	    // 2. 리뷰 삭제
	    ReviewVO reviewVO = new ReviewVO();
	    reviewVO.setReview_Id(review_Id);
	    reviewVO.setUser_Id(user_Id);
	    reviewDAO.deleteReview(reviewVO);
	}

	
	
	//좋아요버튼
	@Override
	public void incrementLikes(int review_id) {
		reviewDAO.incrementLikes(review_id);
	}
	
	
	
}
