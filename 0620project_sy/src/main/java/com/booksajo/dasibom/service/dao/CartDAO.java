package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.GoodsVO;

public interface CartDAO {

	ArrayList<CartVO> getAllCart();

	// �쐟 濡쒓렇�씤�븳 �궗�슜�옄�쓽 �옣諛붽뎄�땲 議고쉶
	ArrayList<CartVO> getCartByUserId(String userId);

	void updateCart(CartVO cartVO);

	void deleteCart(CartVO cartVO);

	CartVO getCart(CartVO cartVO);

	void deleteCart(Map<String, Object> paramMap);

	void insertCart(CartVO cartVO) throws Exception;

	int existsInCart(CartVO cartVO);

	int modifyCount(CartVO cartVO);

	void removeCartItemsAfterOrder(Map<String, Object> paramMap);

	CartVO getCartBookByUserIdAndIsbn(Map<String, Object> paramMap);

	List<CartVO> selectCartBookList(String userId);

	List<GoodsVO> selectUserGoodsCartList(String userId);

	List<GoodsVO> getUserGoodsCartByUserId(String userId);

	void deleteCartGoodsItems(Map<String, Object> paramMap);

	CartVO getCartGoodsByUserIdAndGoodsId(Map<String, Object> paramMap);

	void insertGoodsToCart(CartVO vo);

	void deleteGoods(Map<String, Object> paramMap);

}
