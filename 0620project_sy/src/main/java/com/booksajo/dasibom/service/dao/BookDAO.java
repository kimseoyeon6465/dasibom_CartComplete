package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.BookVO;
import com.booksajo.dasibom.vo.ReviewVO;

public interface BookDAO {
	BookVO getOnebook(BookVO bookVO);

	ArrayList<BookVO> searchBooksByGenre(@Param("category") String category,
			  @Param("start") int start,
			  @Param("end") int end);

	ArrayList<BookVO> getBest();

	ArrayList<BookVO> getNew();

	ArrayList<BookVO> getAllBook();

	BookVO getTelinfo(BookVO bookVO);

	ArrayList<ReviewVO> getAllReview();

	ArrayList<ReviewVO> getAllReviewByIsbn(String isbn);

	void insertReview(ReviewVO reviewVO);

	void updateReview(ReviewVO reviewVO);

	void deleteReview(ReviewVO reviewVO);

	// 평점
	public double getAverageRating(String isbn);

	// 좋아요
	void incrementLikes(int cid);

	BookVO getBookByIsbn(String isbn); 

	int getBookCountByGenre(@Param("category") String category);

}
