package com.booksajo.dasibom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.UserVO;

@Controller
public class MyUpdateInfoController {
	@Autowired
	private UserService userService;

	// 留덉씠�럹�씠吏� - �궡 �젙蹂� �닔�젙 �럹�씠吏�濡� �씠�룞
	// �씠�룞�븯硫댁꽌 濡쒓렇�씤�븳 �궗�슜�옄�쓽 UserVO 媛��졇媛�
	@RequestMapping(value="/myUpdateInfo.do")
	public String myUpdateInfo(HttpSession session, HttpServletRequest request) {
	    
		Integer userSeq = (Integer) session.getAttribute("user_seq");

	    if (userSeq == null) {
	        return "redirect:/login.do"; // �꽭�뀡 �뾾�쓣 寃쎌슦 濡쒓렇�씤
	    }

	    UserVO user = userService.getUserBySeq(userSeq); // user_seq濡� �궗�슜�옄 �젙蹂� 議고쉶
	    request.setAttribute("user", user); // JSP濡� �쟾�떖
	    // JSP �뿉�꽌 �궗�슜�옄�쓽 '�씠由�'�� ${user.irum} 媛숈� �떇�쑝濡� �젙蹂� �젒洹쇳븷 �닔 �엳�떎.

	    return "myUpdateInfo"; // 酉� �씠由�
	}
	
	/*
	 * �땳�꽕�엫 以묐났 �솗�씤2 - myUpdateInfo.jsp�뿉�꽌 �꽆�뼱�샂.媛숈� �꽌鍮꾩뒪 �뀭�궗�슜
	 * 以묐났�솗�씤 寃곌낵 <"available", true/false>�쑝濡� Map 諛섑솚
	*/
	@RequestMapping(value = "checkNameDuplicate.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkNameDuplicate(@RequestParam("name") String name) {
		System.out.println("�땳�꽕�엫 以묐났�솗�씤 �슂泥� �뱾�뼱�샂: " + name);
		System.out.println(">> �쟾�떖�맂 name 媛�: " + name);

		Map<String, Object> result = new HashMap<String, Object>();

		try {
			UserVO userVO = new UserVO();
			userVO.setName(name);
			userService.validateDuplicateByName(userVO);
			result.put("available", true);
		} catch (IllegalStateException e) {
			System.out.println(">> 以묐났�맂 �땳�꽕�엫: " + e.getMessage());
			result.put("available", false);
		} catch (Exception e) {
			System.out.println(">> 湲고� �삁�쇅: " + e.getMessage()); // 狩� �삁�긽移� 紐삵븳 �삤瑜� �솗�씤�슜
			result.put("available", false);
		}

		return result;
	}

	/*
	 * �궗�슜�옄 �젙蹂� (pw�씠�쇅) �뾽�뜲�씠�듃
	 */
	@RequestMapping(value = "updateUserField.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUserField(@RequestParam("field") String field, @RequestParam("value") String value,
			HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer userSeq = (Integer) session.getAttribute("user_seq");

		// seq == null : 鍮꾨줈洹몄씤�긽�깭
		// �� �꽭�뀡�씠 �빆�긽 null�씪 �닔 �뾾湲� �븣臾몄뿉 session == null�� �쟻�젅�븯吏� �븡�떎.
		if (userSeq == null) {
			result.put("success", false);
			result.put("message", "濡쒓렇�씤�씠 �븘�슂�빀�땲�떎.");
			return result;
		}

		try {
			userService.updateUserField(userSeq, field, value);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "�뾽�뜲�씠�듃 �떎�뙣: " + e.getMessage());
		}

		return result;
	}

	/*
	 * �궗�슜�옄 �젙蹂�(pw) �뾽�뜲�씠�듃
	 */
	@RequestMapping(value = "updatePassword.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePassword(@RequestParam("currentPassword") String currentPw,
			@RequestParam("newPassword") String newPw, HttpSession session) {

		Map<String, Object> result = new HashMap<String, Object>();
		Integer userSeq = (Integer) session.getAttribute("user_seq");

		try {
			userService.updatePassword(userSeq, currentPw, newPw);
			result.put("success", true);
		} catch (IllegalArgumentException e) {
			result.put("success", false);
			result.put("message", e.getMessage());
		}

		return result;
	}
	
	

}
