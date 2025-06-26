package com.booksajo.dasibom.service;

import java.util.List;

import com.booksajo.dasibom.vo.ReviewCommentVO;

public interface ReviewCommentService {
	
    void insertComment(ReviewCommentVO commentVO) throws Exception;
    List<ReviewCommentVO> getCommentsByReviewId(int review_id);
    void updateComment(ReviewCommentVO commentVO) throws Exception;
    void deleteComment(ReviewCommentVO commentVO) throws Exception;
}
