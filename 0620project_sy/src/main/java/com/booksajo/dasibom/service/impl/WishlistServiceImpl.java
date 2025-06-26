package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booksajo.dasibom.service.WishlistService;
import com.booksajo.dasibom.service.dao.WishlistDAO;
import com.booksajo.dasibom.vo.CartVO;
import com.booksajo.dasibom.vo.WishlistVO;

@Service("wishlistService")
@Repository
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistDAO wishdao;

	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional
	public ArrayList<WishlistVO> getAllWishlist() throws Exception {
		return wishdao.getAllWishlist();
	}

	// �쐟 濡쒓렇�씤�븳 �궗�슜�옄�쓽 �옣諛붽뎄�땲 議고쉶
	@Override
	public ArrayList<WishlistVO> getWishlistByUserId(String userId) throws Exception {
		return wishdao.getWishlistByUserId(userId);
	}

	@Override
	public WishlistVO getWishlist(WishlistVO wishVO) throws Exception {
		return wishdao.getWishlist(wishVO);
	}

	@Override
	public void deleteWishlist(String userId, List<String> isbnList) {
		wishdao.deleteWishlist(userId, isbnList);
	}

	@Override
	public void insertWishlist(WishlistVO wishVO) throws Exception {
		wishdao.insertWishlist(wishVO);
	}

	@Override
	public void updateWishlist(WishlistVO wishVO) throws Exception {
		wishdao.updateWishlist(wishVO);
	}

	@Override
	public void moveToCart(WishlistVO wishVO) {
		int exists = wishdao.existsInCart(wishVO); // 移댄듃�뿉 �엳�뒗吏� �솗�씤
		if (exists > 0) {
			wishdao.updateCartCount(wishVO); // �닔�웾 利앷�
		} else {
			wishdao.insertToCart(wishVO); // �깉濡� �궫�엯
		}
	}

	// �쐟 �옣諛붽뎄�땲 �넂 蹂닿��븿 �씠�룞
	@Override
	public void moveToWishlist(WishlistVO wishVO) {
		try {
			WishlistVO existing = wishdao.getWishlist(wishVO);
			if (existing == null || !"wishlist".equals(existing.getListType())) {
				wishdao.insertWishlist(wishVO);
			}
		} catch (Exception e) {
			e.printStackTrace(); // �븘�슂 �떆 濡쒓퉭 �삉�뒗 �삁�쇅 �쟾�뙆
		}
	}

	@Override
	public List<WishlistVO> getWishlistByPage(Map<String, Object> param) {
		return sqlSession.selectList("com.booksajo.dasibom.service.dao.WishlistDAO.getWishlistByPage", param);
	}

	@Override
	public int getWishlistCount(String userId) {
		return sqlSession.selectOne("com.booksajo.dasibom.service.dao.WishlistDAO.getWishlistCount", userId);
	}

	public WishlistVO getWishlistBookByUserIdAndIsbn(String userId, String isbn, String listType) {
		return wishdao.getWishlistBookByUserIdAndIsbn(userId, isbn, listType);
	}

	@Override
	public boolean checkGoodsWishlistExists(String userId, String goodsId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("goodsId", goodsId);

		return wishdao.selectGoodsWishlistExists(param) > 0;
	}

	@Override
	public void insertGoodsToWishlistAsCart(CartVO vo) {
		wishdao.insertGoodsToWishlistAsCart(vo);
	}

	@Override
	public boolean checkGoodsExistsInCart(String userId, int goodsId) {
		return wishdao.checkGoodsInCart(userId, goodsId) > 0;
	}

	@Override
	public void incrementGoodsCountInCart(String userId, int goodsId) {
		wishdao.updateGoodsCountInCart(userId, goodsId);
	}

	@Override
	public void insertGoodsToWishlist(CartVO vo) {
		wishdao.insertGoodsToWishlist(vo);
	}

	@Override
	public List<CartVO> getGoodsWishlistByUserId(String userId) {
		return wishdao.getGoodsWishlistByUserId(userId);
	}

	@Override
	public void deleteGoodsFromWishlist(String userId, List<Integer> goodsIdList) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("goodsIdList", goodsIdList);
		wishdao.deleteGoodsFromWishlist(map);
	}

}
