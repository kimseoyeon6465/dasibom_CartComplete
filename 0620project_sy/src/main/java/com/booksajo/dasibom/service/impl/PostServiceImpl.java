package com.booksajo.dasibom.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booksajo.dasibom.service.PostService;
import com.booksajo.dasibom.service.dao.PostsDAO;
import com.booksajo.dasibom.vo.PostsVO;


@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostsDAO postDAO;

	@Override
	public int getPostCount() {
		return postDAO.getPostCount();
	}

	@Override
	public ArrayList<PostsVO> getNoticePosts() {
		return postDAO.getNoticePosts();
	}

	@Override
	public ArrayList<PostsVO> getAllContentsWithoutNotice(int startRow, int endRow) {
		return postDAO.getAllContentsWithoutNotice(startRow, endRow);
	}

	@Override
	public PostsVO getContent(Integer post_id) {
		return postDAO.getContent(post_id);
	}

	@Override
	public boolean insertPost(String category, String title, String content, String user_id, String image_path) {
		return postDAO.insertPost(category, title, content, user_id, image_path);
	}

	@Override
	public boolean updatePost(int post_id, String category, String title, String content, String user_id,
			String image_path) {
		return postDAO.updatePost(post_id, category, title, content, user_id, image_path);
	}

	@Override
	public boolean deletePost(int post_id) {
		return postDAO.deletePost(post_id);
	}

	@Override
	public ArrayList<PostsVO> getAllComments() {
		return postDAO.getAllComments();
	}

	@Override
	public int getCommentCountForPost(Integer post_id) {
		return postDAO.getCommentCountForPost(post_id);
	}

	@Override
	public ArrayList<PostsVO> searchPostsByKeyword(String keyword) {
		return postDAO.findPostsByKeyword("%" + keyword + "%");
	}

	@Override
	public int getSearchPostCount(String keyword) {
		return postDAO.getSearchPostCount(keyword);
	}

	@Override
	public ArrayList<PostsVO> searchPostsByKeywordPaged(String keyword, int startIndex, int pageSize) {
		int endIndex = startIndex + pageSize;
		return postDAO.searchPostsByKeywordPaged(keyword, startIndex, endIndex);
	}

	@Override
	public ArrayList<PostsVO> getAllContents(int startRow, int endRow) {
		return postDAO.getAllContents(startRow, endRow);
	}

	@Override
	public int getPostCountWithoutNotice() {
		return postDAO.getPostCountWithoutNotice();
	}

}
