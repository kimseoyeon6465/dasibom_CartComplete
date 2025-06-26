package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksajo.dasibom.service.CartService;
import com.booksajo.dasibom.service.dao.CartDAO;
import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.GoodsVO;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartdao;

	@Override
	@Transactional
	public ArrayList<CartVO> getAllCart() throws Exception {
		return cartdao.getAllCart();
	}

	// �쐟 濡쒓렇�씤�븳 �궗�슜�옄�쓽 �옣諛붽뎄�땲 議고쉶
	@Override
	public ArrayList<CartVO> getCartByUserId(String userId) throws Exception {
		return cartdao.getCartByUserId(userId);
	}

	@Override
	public CartVO getCart(CartVO cartVO) throws Exception {
		return cartdao.getCart(cartVO);
	}

	@Override
	public void updateCart(CartVO cartVO) throws Exception {
		cartdao.updateCart(cartVO);
	}

	@Override
	public void deleteCart(String userId, List<String> isbnList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("isbnList", isbnList);
		cartdao.deleteCart(paramMap);
	}

	@Override
	public void insertCart(CartVO cartVO) throws Exception {
		cartdao.insertCart(cartVO);
	}

	@Override
	public int modifyCount(CartVO cartVO) {
		return cartdao.modifyCount(cartVO);
	}

	@Override
	public void removeCartItemsAfterOrder(String userId, List<String> isbnList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("isbnList", isbnList);
		cartdao.removeCartItemsAfterOrder(paramMap);
	}

	// �쐟 �듅�젙 �궗�슜�옄 + ISBN�쑝濡� 梨� �젙蹂� 議고쉶
	@Override
	public CartVO getCartBookByUserIdAndIsbn(String userId, String isbn) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("isbn", isbn);
		return cartdao.getCartBookByUserIdAndIsbn(paramMap);
	}

	@Override
	public List<GoodsVO> getUserGoodsCartByUserId(String userId) {
		try {
			return cartdao.getUserGoodsCartByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void removeCartGoodsItemsAfterOrder(String userId, List<Integer> goodsIdList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("goodsIdList", goodsIdList);
		cartdao.deleteCartGoodsItems(paramMap);
	}

	@Override
	public CartVO getCartGoodsByUserIdAndGoodsId(String userId, int goodsId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("goodsId", goodsId);
		return cartdao.getCartGoodsByUserIdAndGoodsId(paramMap);
	}

	@Override
	public void addGoodsToCart(CartVO vo) {
		cartdao.insertGoodsToCart(vo);
	}

	@Override
	public void deleteGoodsFromCart(String userId, List<String> goodsIdList) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("goodsIdList", goodsIdList);
		cartdao.deleteGoods(paramMap); // 이 부분이 맞게 되어 있음 ✅
	}

}
