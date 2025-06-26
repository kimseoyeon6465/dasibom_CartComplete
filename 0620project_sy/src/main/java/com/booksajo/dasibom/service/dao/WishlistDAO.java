package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.WishlistVO;

public interface WishlistDAO {

	ArrayList<WishlistVO> getAllWishlist();

	// �쐟 濡쒓렇�씤�븳 �궗�슜�옄�쓽 �옣諛붽뎄�땲 議고쉶
	ArrayList<WishlistVO> getWishlistByUserId(String userId);

	void insertWishlist(WishlistVO wishVO);

	void updateWishlist(WishlistVO wishVO);

	WishlistVO getWishlist(WishlistVO wishVO);

	void deleteWishlist(@Param("userId") String userId, @Param("isbnList") List<String> isbnList);

	// �빑�떖 硫붿꽌�뱶
	void moveToCart(WishlistVO vo);

	int existsInCart(WishlistVO wishVO);

	void updateCartCount(WishlistVO wishVO);

	void insertToCart(WishlistVO wishVO);

	List<WishlistVO> getWishlistByPage(@Param("userId") String userId, @Param("start") int start,
			@Param("end") int end);

	int getWishlistCount(String userId);

	WishlistVO getWishlistBookByUserIdAndIsbn(@Param("userId") String userId, @Param("isbn") String isbn,
			@Param("listType") String listType);

	int checkGoodsWishlistExists(@Param("userId") String userId, @Param("goodsId") String goodsId);

	void insertGoodsToWishlistAsCart(CartVO vo);

	int checkGoodsInCart(@Param("userId") String userId, @Param("goodsId") int goodsId);

	int updateGoodsCountInCart(@Param("userId") String userId, @Param("goodsId") int goodsId);

	void insertGoodsToWishlist(CartVO vo);

	List<CartVO> getGoodsWishlistByUserId(String userId);

	int selectGoodsWishlistExists(Map<String, Object> param);

	void deleteGoodsFromWishlist(Map<String, Object> map);

}
