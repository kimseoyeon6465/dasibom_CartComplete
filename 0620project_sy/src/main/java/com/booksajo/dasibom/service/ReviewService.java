package com.booksajo.dasibom.service;

import java.util.ArrayList;

import com.booksajo.dasibom.vo.ReviewVO;

public interface ReviewService {
	
	ArrayList<ReviewVO> getAllReview();
	ArrayList<ReviewVO> getAllReviewByIsbn(String isbn);
	
	void insertReview(ReviewVO reviewVO) throws Exception;
	void updateReview(ReviewVO reviewVO) throws Exception;
	public void deleteReview(int review_Id, String user_Id);

	void incrementLikes(int review_Id);
	
}
