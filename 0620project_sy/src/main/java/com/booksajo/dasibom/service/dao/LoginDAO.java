package com.booksajo.dasibom.service.dao;

import java.util.Map;

import com.booksajo.dasibom.vo.UserVO;

public interface LoginDAO {
	// {user_Id, pw} map ��ü
	public UserVO loginUser(Map<String, String> param);
}
