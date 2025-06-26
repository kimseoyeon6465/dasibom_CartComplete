package com.booksajo.dasibom.service;

import com.booksajo.dasibom.vo.UserVO;

public interface LoginService {
	// 로그인 프로세스
	public UserVO login(String user_Id, String rawPw);
}
