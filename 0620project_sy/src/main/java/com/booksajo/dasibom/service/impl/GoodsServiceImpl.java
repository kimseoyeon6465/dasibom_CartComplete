package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.*;
import org.xml.sax.InputSource;


import com.booksajo.dasibom.service.GoodsService;
import com.booksajo.dasibom.service.dao.GoodsDAO;
import com.booksajo.dasibom.vo.GoodsVO;
import com.booksajo.dasibom.vo.PhotosVO;


@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;
	
	@Override
	@Transactional
	public ArrayList<GoodsVO> getAllGoods() {
		// TODO Auto-generated method stub
		return goodsDAO.getAllGoods();
	}	
	
	
	@Override
	@Transactional
	public GoodsVO getOnegoods(GoodsVO goodsVO) {
	     return goodsDAO.getOnegoods(goodsVO); 
	    }
	
	
	@Override
	public List<PhotosVO> getGoodsPhotoList(int goodsId) {
	    return goodsDAO.getGoodsPhotoList(goodsId);
	}

	
}