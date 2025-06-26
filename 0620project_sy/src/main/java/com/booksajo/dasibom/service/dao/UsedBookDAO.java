package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.UsedBookVO;


public interface UsedBookDAO {
    List<UsedBookVO> selectAll();
    UsedBookVO selectId(int postId);
    int insert(UsedBookVO vo);
    int update(UsedBookVO vo);
    int delete(int postId);
	int selectUsedBookCount();
	ArrayList<UsedBookVO> selectUsedBooksByPage(@Param("startRow") int startRow,  @Param("endRow")int endRow);
}