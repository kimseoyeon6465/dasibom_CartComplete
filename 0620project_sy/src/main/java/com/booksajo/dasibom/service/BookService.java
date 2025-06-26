package com.booksajo.dasibom.service;

import java.util.ArrayList;

import com.booksajo.dasibom.vo.BookSearchResult;
import com.booksajo.dasibom.vo.BookVO;
import com.booksajo.dasibom.vo.ReviewVO;

public interface BookService {
	BookSearchResult searchBooks(String query, int startnum) throws Exception;

	ArrayList<BookVO> searchBooksByGenre(String category, int start, int end) throws Exception;

	ArrayList<BookVO> getBest() throws Exception;

	ArrayList<BookVO> getNew() throws Exception;

	ArrayList<BookVO> getAllBook();

	BookVO getTelinfo(BookVO bookVO);

	ArrayList<ReviewVO> getAllReview();

	ArrayList<ReviewVO> getAllReviewByIsbn(String isbn);

	void insertReview(ReviewVO reviewVO) throws Exception;

	void updateReview(ReviewVO reviewVO) throws Exception;

	void deleteReview(ReviewVO reviewVO) throws Exception;

	double getAverageRating(String isbn);

	void incrementLikes(int cid);

	BookVO getBookByIsbn(String isbn); // ✅ 정가 가져오기용

	int getBookCountByGenre(String category);

}