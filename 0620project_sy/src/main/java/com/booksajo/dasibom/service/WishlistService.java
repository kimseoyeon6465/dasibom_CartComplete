package com.booksajo.dasibom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.WishlistVO;

public interface WishlistService {

	ArrayList<WishlistVO> getAllWishlist() throws Exception;

	ArrayList<WishlistVO> getWishlistByUserId(String userId) throws Exception;

	WishlistVO getWishlist(WishlistVO wishVO) throws Exception;

	void insertWishlist(WishlistVO wishVO) throws Exception;

	void updateWishlist(WishlistVO wishVO) throws Exception;

	void moveToCart(WishlistVO wishVO);

	void moveToWishlist(WishlistVO wishVO);

	List<WishlistVO> getWishlistByPage(Map<String, Object> param);

	int getWishlistCount(String userId);

	void deleteWishlist(String userId, List<String> isbnList);

	WishlistVO getWishlistBookByUserIdAndIsbn(String userId, String isbn, String listType);

	boolean checkGoodsWishlistExists(String userId, String goodsId);

	void insertGoodsToWishlistAsCart(CartVO vo);

	boolean checkGoodsExistsInCart(String userId, int goodsId);

	void incrementGoodsCountInCart(String userId, int goodsId);

	void insertGoodsToWishlist(CartVO vo);

	List<CartVO> getGoodsWishlistByUserId(String userId);

	void deleteGoodsFromWishlist(String userId, List<Integer> goodsIdList);

}
