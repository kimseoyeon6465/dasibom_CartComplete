package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;

import com.booksajo.dasibom.vo.ReviewVO;

public interface ReviewDAO {

	ArrayList<ReviewVO> getAllReview();
	ArrayList<ReviewVO> getAllReviewByIsbn(String isbn);
	
	void insertReview(ReviewVO reviewVO);
	void updateReview(ReviewVO reviewVO);
	void deleteReview(ReviewVO reviewVO);
	

	//���ƿ�
	void incrementLikes(int review_Id);
	
}
