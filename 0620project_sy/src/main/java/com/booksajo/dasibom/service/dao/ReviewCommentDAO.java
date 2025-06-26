package com.booksajo.dasibom.service.dao;

import java.util.List;

import com.booksajo.dasibom.vo.ReviewCommentVO;

public interface ReviewCommentDAO {
    void insertComment(ReviewCommentVO commentVO);
    //��� ��ȸ
    List<ReviewCommentVO> getCommentsByReviewId(int review_id);
    void updateComment(ReviewCommentVO commentVO);
    void deleteComment(ReviewCommentVO commentVO);
    
    //���� ���� �� ��� �Բ� ����
    public void deleteByReviewId(int review_Id);
    
}
