package com.booksajo.dasibom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksajo.dasibom.service.LoginService;
import com.booksajo.dasibom.vo.UserVO;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam("user_Id") String user_Id, @RequestParam("pw") String pw, HttpSession session,
			Model model) {

		UserVO user = loginService.login(user_Id, pw);

		if (user != null) {
			if (user.getIrum() != null && (Integer) user.getUser_Seq() != null) {
				System.out.println("디버깅: 이름_irum: " + user.getIrum());
				System.out.println("디버깅: 시퀀스 No. " + user.getUser_Seq());
				System.out.println("디버깅: 시퀀스 No. " + user.getEmail());

				session.setAttribute("user_seq", user.getUser_Seq());
				session.setAttribute("userId", user.getUser_Id());

				/* -----------------update-start------------------- */
				session.setAttribute("loginUser", user);
				/* -----------------update-end--------------------- */
				return "redirect:/MainPage.do";
			} else {
				return null; // 나오면 안 되는 경우
			}
		} else {
			System.out.println("ssssssssssssssss");
			model.addAttribute("loginFail", "아이디 또는 비밀번호가 틀렸습니다.");
			return "loginPage";
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 제거
		return "redirect:/MainPage.do"; // 메인 페이지로 redirect
	}
}
