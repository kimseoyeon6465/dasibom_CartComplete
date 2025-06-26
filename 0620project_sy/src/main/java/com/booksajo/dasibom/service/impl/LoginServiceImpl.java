package com.booksajo.dasibom.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.encoder.SHA256PasswordEncoder;
import com.booksajo.dasibom.service.LoginService;
import com.booksajo.dasibom.service.dao.LoginDAO;
import com.booksajo.dasibom.vo.UserVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	@Autowired
	private SHA256PasswordEncoder encoder; 

	/*
	 * 로그인 - 세션기반
	 */
	@Override
	public UserVO login(String user_Id, String rawPw) {
		String encodedPw = encoder.encode(rawPw);// SHA-256 암호화 사용
		Map<String, String> param = new HashMap<String, String>();
		param.put("user_Id", user_Id);
		param.put("pw", encodedPw);

		UserVO user = loginDAO.loginUser(param);
		
		if(user == null || !user.getPw().equals(encodedPw)) {
			return null;
		}
		
		return user;
	}




	
}
