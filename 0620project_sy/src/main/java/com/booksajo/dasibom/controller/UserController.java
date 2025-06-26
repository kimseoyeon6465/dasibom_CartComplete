package com.booksajo.dasibom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booksajo.dasibom.encoder.SHA256PasswordEncoder;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.UserVO;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * ȸ�� ���� ����
	 */
	@RequestMapping(value = "insertUser.do", method = RequestMethod.POST)
	public String insertUser(@RequestParam("tel1") String tel1, @RequestParam("tel2") String tel2,
			@RequestParam("tel3") String tel3, UserVO userVO, Model model) {
		// ��ȭ��ȣ(input 3ĭ¥�� ����) -> ������ ���� �ϳ��� ���ڿ���
		userVO.setTel(tel1 + "-" + tel2 + "-" + tel3);

		// ��ȣȭ SHA-256
		SHA256PasswordEncoder encoder = new SHA256PasswordEncoder();

		// encodePassword(plainText, salt) -> ��ȣȭ�� ��ȣ�� ����
		String plainText = userVO.getPw();
		userVO.setPw(encoder.encode(plainText)); // 암호화

		boolean isSuccessed = userService.insertUser(userVO);

		if (isSuccessed) {
			model.addAttribute("signupSuccess", "ȸ������ �Ϸ�! �������� ���ư��ϴ�.");
		} else {
			model.addAttribute("signupFail", "ȸ������ ����. Err: ${errmsg}");
		}

		return "signupSuccess"; // ȸ������ ���� -> alert��� �� ������������ redirect�ϴ� jsp
	}

	/*
	 * ���̵� �ߺ� Ȯ��
	 */
	@ResponseBody
	@RequestMapping(value = "validateUserId.do", method = RequestMethod.POST)
	public String validateDuplicateByUserId(UserVO userVO) {
		try {
			userService.validateDuplicateByUserId(userVO);
			return "available";
		} catch (IllegalStateException e) {
			return e.getMessage(); // ���� �޽��� �״�� ��ȯ
		}
	}

	/*
	 * �г��� �ߺ� Ȯ��
	 */
	@ResponseBody
	@RequestMapping(value = "validateName.do", method = RequestMethod.POST)
	public String validateDuplicateByName(UserVO userVO) {
		try {
			userService.validateDuplicateByName(userVO);
			return "available";
		} catch (IllegalStateException e) {
			return e.getMessage(); // ���� �޽��� �״�� ��ȯ
		}
	}

}
