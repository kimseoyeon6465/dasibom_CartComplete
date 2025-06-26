package com.booksajo.dasibom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booksajo.dasibom.service.GoodsService;
import com.booksajo.dasibom.vo.GoodsVO;
import com.booksajo.dasibom.vo.PhotosVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GoodsController {

	@Resource(name = "goodsService")
	private GoodsService goodsService;

	// 援우쫰 紐⑸줉 �럹�씠吏�
	@RequestMapping("/goods_list.do")
	public String goodsList(Model model) {

		ArrayList<GoodsVO> goodsList = goodsService.getAllGoods();
		model.addAttribute("goodsList", goodsList);

		return "goods_list"; // �넂 goods_list.jsp 濡� �씠�룞
	}

	// 援우쫰 �긽�꽭 �럹�씠吏�
	@RequestMapping("/goods_detail.do")
	public String goodsDetail(GoodsVO goodsVO, Model model) {
		// 1. 援우쫰 �젙蹂�
		GoodsVO onegoods = goodsService.getOnegoods(goodsVO);
		if (onegoods == null) {
			return "redirect:/MainPage.do";
		}
		model.addAttribute("goods", onegoods);

		// 2. 援우쫰 �씠誘몄� 紐⑸줉 議고쉶 (photos_table �궗�슜)
		List<PhotosVO> photoList = goodsService.getGoodsPhotoList(onegoods.getGoods_Id());
		model.addAttribute("photoList", photoList);

		return "goods_detail"; // �넂 goods_detail.jsp 濡� �씠�룞
	}
}
