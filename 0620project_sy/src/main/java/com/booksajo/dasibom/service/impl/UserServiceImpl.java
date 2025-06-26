package com.booksajo.dasibom.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.encoder.SHA256PasswordEncoder;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.service.dao.UserDAO;
import com.booksajo.dasibom.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	SHA256PasswordEncoder encoder = new SHA256PasswordEncoder();

	/*
	 * 사용자 회원가입
	 */
	@Override
	public boolean insertUser(UserVO userVO) {
		/* 실제 boolean값(성공/실패 여부) 여기서부터 - controller까지 반환 */
		try {
			userDAO.insertUser(userVO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 아이디 중복 확인
	 */
	@Override
	public void validateDuplicateByUserId(UserVO userVO) {
		if (userDAO.findUserById(userVO.getUser_Id()) != null) {
			throw new IllegalStateException("이미 존재하는 아이디 입니다.");
		}

	}

	/*
	 * 닉네임 중복 확인
	 */
	@Override
	public void validateDuplicateByName(UserVO userVO) {
		if (userDAO.findUserByName(userVO.getName()) != null) {
			throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
		}
	}

	/*
	 * 로그인 - 세션기반
	 */
//	@Override
//	public UserVO login(String user_Id, String rawPw) {
//		String encodedPw = encoder.encodePassword(rawPw, null); // SHA-256 암호화 메서드 사용
//		Map<String, String> param = new HashMap<String, String>();
//		param.put("user_Id", user_Id);
//		param.put("pw", encodedPw);
//
//		return userDAO.loginUser(param);
//	}
//
	@Override
	public UserVO getUserBySeq(int user_Seq) {
		return userDAO.findUserBySeq(user_Seq);
	}

	@Override
	public void updateUserField(Integer user_Seq, String field, String value) {
		List<String> allowedFields = Arrays.asList("irum", "email", "address", "name", "tel");

		if (!allowedFields.contains(field)) {
			throw new IllegalArgumentException("허용되지 않은 필드입니다.");
		}

		userDAO.updateUserField(user_Seq, field, value);
	}

	@Override
	public void updatePassword(Integer user_Seq, String currentPw, String newPw) {
		UserVO user = userDAO.findUserBySeq(user_Seq);

		// 1. 현재 비밀번호 비교
		if (!encoder.matches(currentPw, user.getPw())) {
			throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
		}

		// 2. 새 비밀번호 암호화 후 저장
		String encodedNewPw = encoder.encode(newPw);
		userDAO.updatePassword(user_Seq, encodedNewPw);

	}

	@Override
	public UserVO getUserById(String userId) {
		return userDAO.selectUserById(userId);
	}

	@Override
	public void updateUserPoint(String userId, int diff) {
		System.out.println("🛠 UserServiceImpl → updateUserPoint 호출됨: " + userId + ", diff=" + diff);
		userDAO.updateUserPoint(userId, diff);
	}

}
