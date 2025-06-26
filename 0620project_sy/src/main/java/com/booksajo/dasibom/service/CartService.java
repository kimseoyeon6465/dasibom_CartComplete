package com.booksajo.dasibom.service;

import java.util.ArrayList;
import java.util.List;

import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.GoodsVO;

public interface CartService {

	ArrayList<CartVO> getAllCart() throws Exception;

	CartVO getCart(CartVO cartVO) throws Exception;

	void insertCart(CartVO cartVO) throws Exception;

	void updateCart(CartVO cartVO) throws Exception;

	ArrayList<CartVO> getCartByUserId(String userId) throws Exception;

	void deleteCart(String userId, List<String> isbnList);

	int modifyCount(CartVO cartVO);

	void removeCartItemsAfterOrder(String userId, List<String> isbnList);

	CartVO getCartBookByUserIdAndIsbn(String userId, String string);

	List<GoodsVO> getUserGoodsCartByUserId(String userId);

	void removeCartGoodsItemsAfterOrder(String userId, List<Integer> goodsIdList);

	CartVO getCartGoodsByUserIdAndGoodsId(String userId, int parseInt);

	void addGoodsToCart(CartVO vo);

	void deleteGoodsFromCart(String userId, List<String> goodsIdList);
}
