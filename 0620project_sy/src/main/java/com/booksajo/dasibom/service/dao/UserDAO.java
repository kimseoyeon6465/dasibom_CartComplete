package com.booksajo.dasibom.service.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.UserVO;

public interface UserDAO {
	
	public void insertUser(UserVO userVO);

	public Object findUserById(String user_Id); // UserVO 로

	public Object findUserByName(String name);

	public UserVO loginUser(Map<String, String> param); // => LoginDAO 로 

	public UserVO findUserBySeq(int user_Seq); // => LoginDAO 로 

	public void updateUserField(@Param("user_Seq") int user_Seq, @Param("field") String field, @Param("value") String value);

	public void updatePassword(@Param("user_Seq") int user_Seq, @Param("newPassword") String password);
	
	public String getAddressByUserId(String userId);

	UserVO selectUserById(String userId);

	void updateUserPoint(@Param("userId") String userId, @Param("diff") int diff);

}
