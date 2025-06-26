package com.booksajo.dasibom.service;

import org.springframework.stereotype.Service;

import com.booksajo.dasibom.vo.UserVO;


public interface UserService {
	
	UserVO getUserById(String userId);
	void updateUserPoint(String userId, int diff);
	
	boolean insertUser(UserVO userVO);
    void validateDuplicateByUserId(UserVO userVO);
	void validateDuplicateByName(UserVO userVO);
	public UserVO getUserBySeq(int user_Seq);
	
	public void updateUserField(Integer user_Seq, String field, String value);
	public void updatePassword(Integer user_Seq, String currentPw, String newPw);
}