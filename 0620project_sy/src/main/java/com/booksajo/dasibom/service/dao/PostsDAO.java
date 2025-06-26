package com.booksajo.dasibom.service.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.booksajo.dasibom.vo.PostsVO;


public interface PostsDAO {

	int getPostCount();

	ArrayList<PostsVO> getNoticePosts();

	ArrayList<PostsVO> getAllContentsWithoutNotice(@Param("startRow") int startRow, @Param("endRow") int endRow);

	ArrayList<PostsVO> getAllContents(@Param("startRow") int startRow, @Param("endRow") int endRow);

	ArrayList<PostsVO> getAllComments();

	PostsVO getContent(Integer pid);

	boolean insertPost(@Param("category") String category, @Param("title") String title,
			@Param("content") String content, @Param("user_id") String user_id, @Param("image_path") String image_path);

	boolean updatePost(@Param("post_id") int post_id, @Param("category") String category, @Param("title") String title,
			@Param("content") String content, @Param("user_id") String user_id, @Param("image_path") String image_path);

	boolean deletePost(int pid);

	int getCommentCountForPost(int pid);

	ArrayList<PostsVO> findPostsByKeyword(String string);

	int getSearchPostCount(@Param("keyword") String keyword);

	ArrayList<PostsVO> searchPostsByKeywordPaged(@Param("keyword") String keyword, @Param("startIndex") int startIndex,
			@Param("endIndex") int endIndex);

	int getPostCountWithoutNotice();

}
