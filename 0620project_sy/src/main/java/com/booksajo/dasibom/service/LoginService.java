package com.booksajo.dasibom.service;

import com.booksajo.dasibom.vo.UserVO;

public interface LoginService {
	// �α��� ���μ���
	public UserVO login(String user_Id, String rawPw);
}
