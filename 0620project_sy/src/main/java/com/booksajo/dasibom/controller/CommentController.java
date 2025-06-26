package com.booksajo.dasibom.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.booksajo.dasibom.service.CommentService;
import com.booksajo.dasibom.vo.CommentVO;



@Controller
public class CommentController {

	@Resource(name = "CommentService")
	private CommentService commentService;

	@RequestMapping(value = "/writeComment.do")
	public String writeComment(@ModelAttribute("commentVO") CommentVO commentVO, Model model) {

		if (commentVO.getParent_comment_id() == 0) {
			commentService.writeComment(commentVO); // �Ϲ� ���
		} else {
			commentService.writeReplyComment(commentVO); // ����
		}

		// ��� ��� ��ȸ
		ArrayList<CommentVO> comments = commentService.getAllCommentsForPost(commentVO.getPost_id());
		System.out.println();
		model.addAttribute("comments", comments);

		// �Խñ� �� �������� �̵�
		return "redirect:/showDetailPost.do?post_id=" + commentVO.getPost_id();
	}

	@RequestMapping(value = "/deleteComment.do")
	public String deleteComment(@RequestParam("comment_id") int commentId, @RequestParam("post_id") int postId,
			RedirectAttributes redirectAttributes, Model model) {
		try {
			boolean isDeleted = commentService.deleteComment(commentId);

			if (isDeleted) {
				redirectAttributes.addFlashAttribute("message", "����� �����߽��ϴ�");
				return "redirect:/showDetailPost.do?post_id=" + postId;
			} else {
				model.addAttribute("message", "��� ������ �����߽��ϴ�");
				return "/common/alertBack";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "�ý��� ������ �߻��߽��ϴ�");
			return "/common/alertBack";
		}
	}

	@RequestMapping(value = "/updateComment.do")
	public String updateComment(@RequestParam("comment_id") int commentId, @RequestParam("post_id") int postId,
			@RequestParam("edited_content") String commentContents, RedirectAttributes redirectAttributes) {

		// �ٹٲ� ó��
		commentContents = commentContents.replace("\r\n", "\n");

		CommentVO comment = new CommentVO();
		comment.setComment_id(commentId);
		comment.setComment_contents(commentContents);

		commentService.updateComment(comment); // DB ���� ����

		redirectAttributes.addAttribute("post_id", postId);
		return "redirect:/showDetailPost.do?post_id=" + postId;
	}
}