package com.booksajo.dasibom.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booksajo.dasibom.service.CommentService;
import com.booksajo.dasibom.service.PostService;
import com.booksajo.dasibom.service.UserService;
import com.booksajo.dasibom.vo.CommentVO;
import com.booksajo.dasibom.vo.PostsVO;
import com.booksajo.dasibom.vo.UserVO;


@Controller
public class PostController {

	@Resource(name = "postService")
	private PostService postService;

	@Resource(name = "CommentService")
	private CommentService commentService;

	@Resource(name = "UserService")
	private UserService userService;

	@RequestMapping("/goPost.do")
	public String goPost(@RequestParam(value = "spage", required = false, defaultValue = "1") int spage, Model model) {
		int postsPerPage = 10;

		// 전체 공지글 수
		ArrayList<PostsVO> allNoticePosts = postService.getNoticePosts();
		int totalNoticeCount = allNoticePosts.size();

		// 전체 일반 게시글 수
		int totalNormalCount = postService.getPostCountWithoutNotice();

		// 전체 게시글 수 = 공지 + 일반
		int totalPosts = totalNoticeCount + totalNormalCount;

		// 전체 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalPosts / postsPerPage);

		// 페이지 유효성 검사
		if (spage < 1) {
			spage = 1;
		} else if (spage > totalPages && totalPages != 0) {
			spage = totalPages;
		}

		// 현재 페이지에 보여줄 공지글 시작/끝 인덱스
		int noticeStartIndex = (spage - 1) * postsPerPage;
		int noticeEndIndex = Math.min(noticeStartIndex + postsPerPage, totalNoticeCount);

		ArrayList<PostsVO> noticePosts = new ArrayList<PostsVO>();
		if (noticeStartIndex < totalNoticeCount) {
			noticePosts = new ArrayList<PostsVO>(allNoticePosts.subList(noticeStartIndex, noticeEndIndex));

			for (PostsVO post : noticePosts) {
				int commentCount = postService.getCommentCountForPost(post.getPost_id());
				post.setCommentCount(commentCount);
				post.setHasImageInContent(post.getPost_contents() != null && post.getPost_contents().contains("<img"));
			}
		}

		// 공지글로 이미 채워진 수
		int noticeCountOnPage = noticePosts.size();
		int remainingForNormal = postsPerPage - noticeCountOnPage;

		ArrayList<PostsVO> postLists = new ArrayList<PostsVO>();
		if (remainingForNormal > 0) {
			// 일반 게시글의 시작 위치는 공지글을 제외한 전체 게시글 중 몇 개가 앞에 나왔는지를 기준으로 계산
			int normalStartRow = Math.max(0, (spage - 1) * postsPerPage - totalNoticeCount);
			postLists = postService.getAllContentsWithoutNotice(normalStartRow + 1,
					normalStartRow + remainingForNormal);

			for (PostsVO post : postLists) {
				int commentCount = postService.getCommentCountForPost(post.getPost_id());
				post.setCommentCount(commentCount);
				post.setHasImageInContent(post.getPost_contents() != null && post.getPost_contents().contains("<img"));
			}
		}

		// 공지글 + 일반 게시글을 합침
		ArrayList<PostsVO> combinedPosts = new ArrayList<PostsVO>();
		combinedPosts.addAll(noticePosts);
		combinedPosts.addAll(postLists);

		model.addAttribute("combinedPosts", combinedPosts);
		model.addAttribute("currentPage", spage);
		model.addAttribute("totalPages", totalPages);

		return "post";
	}

	@RequestMapping(value = "/showDetailPost.do")
	public String showDetailPost(@RequestParam("post_id") Integer postId, Model model, HttpSession session)
			throws Exception {
		PostsVO post = postService.getContent(postId);
		model.addAttribute("post", post);

		Integer userSeq = (Integer) session.getAttribute("user_seq");

		// ✅ userSeq가 null이면 user 정보를 모델에 넣지 않음
		if (userSeq != null) {
			UserVO user = userService.getUserBySeq(userSeq);
			model.addAttribute("user", user);
		}

		int commentCount = postService.getCommentCountForPost(postId);
		model.addAttribute("commentCount", commentCount);

		ArrayList<CommentVO> comments = commentService.getAllCommentsForPost(postId);
		model.addAttribute("comments", comments);

		return "showDetailPost";
	}

	@RequestMapping(value = "/goWritePost.do")
	public String goWritePost(@RequestParam(value = "post_id", required = false) Integer postId, Model model,
			HttpSession session) {

		Integer userSeq = (Integer) session.getAttribute("user_seq");
		UserVO user = userService.getUserBySeq(userSeq);
		model.addAttribute("user", user);

		if (postId != null) {
			PostsVO post = postService.getContent(postId);
			model.addAttribute("post", post);
		}
		return "goWritePost";
	}

	@RequestMapping(value = "/insertPost.do")
	public String insertPost(@RequestParam("category") String category, @RequestParam("title") String title,
			@RequestParam("user_id") String userId, @RequestParam("content") String content,
			@RequestParam("image_path") String imagePath, RedirectAttributes redirectAttributes, Model model) {
		
		
		// ✅ post 객체 먼저 구성 (모든 값 세팅)
		PostsVO post = new PostsVO();
		post.setCategory(category);
		post.setTitle(title);
		post.setUser_id(userId);
		post.setPost_contents(content);
		post.setImage_path(imagePath);

		// ✅ model에 post 담기 (유효성 실패 시에도 값을 복원하기 위해)
		model.addAttribute("post", post);

		// ✅ 유효성 검증
		if (category == null || category.trim().isEmpty()) {
			model.addAttribute("message", "말머리를 선택해주세요");
			return "/common/alertBack";
		}
		if (title == null || title.trim().isEmpty()) {
			model.addAttribute("message", "제목을 입력해주세요");
			return "/common/alertBack";
		}
		if (userId == null || userId.trim().isEmpty()) {
			model.addAttribute("message", "작성자 성명을 입력해주세요");
			return "/common/alertBack";
		}
		if (content == null || content.trim().isEmpty()) {
			model.addAttribute("message", "내용을 입력해주세요");
			return "/common/alertBack";
		}

		// ✅ DB에 저장
		boolean isSuccess = postService.insertPost(category, title, content, userId, imagePath);

		if (isSuccess) {
			redirectAttributes.addFlashAttribute("message", "작성을 완료했습니다");
			return "redirect:/goPost.do";
		} else {
			model.addAttribute("message", "작성이 실패했습니다");
			return "/common/alertBack";
		}
	}

	@RequestMapping(value = "/updatePost.do")
	public String updatePost(@RequestParam("post_id") int postId, @RequestParam("category") String category,
			@RequestParam("title") String title, @RequestParam("user_id") String userId,
			@RequestParam("content") String content, @RequestParam("image_path") String imagePath,
			RedirectAttributes redirectAttributes, Model model) {

		// ✅ post 객체 생성 및 모든 값 세팅
		PostsVO post = new PostsVO();
		post.setPost_id(postId);
		post.setCategory(category);
		post.setTitle(title);
		post.setUser_id(userId);
		post.setPost_contents(content);
		post.setImage_path(imagePath);

		// ✅ model에 post 담기 (유효성 검사 실패 시 입력값 복원용)
		model.addAttribute("post", post);

		// ✅ 유효성 검사
		if (category == null || category.trim().isEmpty()) {
			model.addAttribute("message", "말머리를 선택해주세요");
			return "/common/alertBack";
		}
		if (title == null || title.trim().isEmpty()) {
			model.addAttribute("message", "제목을 입력해주세요");
			return "/common/alertBack";
		}
		if (userId == null || userId.trim().isEmpty()) {
			model.addAttribute("message", "작성자 성명을 입력해주세요");
			return "/common/alertBack";
		}
		if (content == null || content.trim().isEmpty()) {
			model.addAttribute("message", "내용을 입력해주세요");
			return "/common/alertBack";
		}

		// ✅ DB 업데이트
		boolean isSuccess = postService.updatePost(postId, category, title, content, userId, imagePath);

		if (isSuccess) {
			redirectAttributes.addFlashAttribute("message", "수정을 완료했습니다");
			return "redirect:/showDetailPost.do?post_id=" + postId;
		} else {
			model.addAttribute("message", "수정에 실패했습니다");
			return "/common/alertBack";
		}
	}

	@RequestMapping(value = "/deletePost.do")
	public String deletePost(@RequestParam("post_id") int postId, RedirectAttributes redirectAttributes, Model model) {
		try {
			boolean isDeleted = postService.deletePost(postId);

			if (isDeleted) {
				redirectAttributes.addFlashAttribute("message", "게시글을 삭제했습니다");
				return "redirect:/goPost.do";
			} else {
				model.addAttribute("message", "게시글 삭제에 실패했습니다");
				return "/common/alertBack";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "시스템 오류가 발생했습니다");
			return "/common/alertBack";
		}
	}

	@RequestMapping(value = "/uploadImage.do")
	@ResponseBody
	public String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (!file.isEmpty()) {
			try {
				String uploadDir = request.getSession().getServletContext().getRealPath("/IMG/");
				File dir = new File(uploadDir);
				if (!dir.exists())
					dir.mkdirs();

				String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				File targetFile = new File(uploadDir, fileName);
				file.transferTo(targetFile);

				// ✅ context path 포함한 이미지 경로 반환
				String contextPath = request.getContextPath();
				return contextPath + "/IMG/" + fileName;

			} catch (Exception e) {
				e.printStackTrace();
				return "error: " + e.getMessage();
			}
		}
		return "error: file is empty";
	}

	@RequestMapping(value = "/searchPost.do", method = RequestMethod.GET)
	public String searchPost(@RequestParam("keyword") String keyword,
			@RequestParam(value = "spage", required = false, defaultValue = "1") int spage, Model model) {

		// 검색어가 없으면 goPost로 리다이렉트
		if (keyword == null || keyword.trim().isEmpty()) {
			return "redirect:/goPost.do?spage=" + spage;
		}

		int pageSize = 10;
		int startIndex = (spage - 1) * pageSize;

		int searchCount = postService.getSearchPostCount("%" + keyword + "%");

		ArrayList<PostsVO> searchResults = postService.searchPostsByKeywordPaged(keyword, startIndex, pageSize);

		int totalPages = (int) Math.ceil((double) searchCount / pageSize);

		model.addAttribute("postLists", searchResults);
		model.addAttribute("currentPage", spage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("keyword", keyword);

		return "post";
	}
}