package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;
import java.util.List;

import com.booksajo.dasibom.vo.BookVO;
import com.booksajo.dasibom.vo.GoodsVO;
import com.booksajo.dasibom.vo.PhotosVO;
import com.booksajo.dasibom.vo.ReviewVO;

public interface GoodsDAO {
	
	GoodsVO getOnegoods(GoodsVO goodsVO);

	ArrayList<GoodsVO> getAllGoods();
	
	List<PhotosVO> getGoodsPhotoList(int goodsId);

}
