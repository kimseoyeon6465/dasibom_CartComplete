<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="./resources/images/favicon3.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./resources/style/postDetail.css">
<script src="${pageContext.request.contextPath}/JS/loginmodal.js"></script>
<title>${post.title}</title>
</head>
<body class="post-detail-body">
	<jsp:include page="/common/header.jsp" />
	<script>
	  const isLoggedIn = ${sessionScope.user_seq == null ? 'false' : 'true'};
	</script>
	<jsp:include page="/common/loginmodal.jsp" />

	<!-- 상단 메뉴 버튼용 form -->
	<form id="myForm" method="post">
		<table class = "post-detail-table">
			<tr>
				<td colspan="4" class="btn-row">
					<input type="submit" value="모두보기" onclick="submitTo('goPost.do')" class="post-detail-button" />

					<c:if test="${sessionScope.user_id != null && sessionScope.user_id == post.user_id}">
						<input type="submit" value="수정하기" onclick="submitTo('goWritePost.do?post_id=${post.post_id}')" class="post-detail-button" />
						<input type="submit" value="삭제하기" onclick="submitTo('deletePost.do?post_id=${post.post_id}')" class="post-detail-button" />
					</c:if>
				</td>
			</tr>

			<tr>
				<td colspan="4" class="post-detail-card">
					<h3 style="margin: 4px 0 0 0;">[${post.category}]</h3>
					<h2 style="margin: 4px 0 0 0; color: #d63384;">${post.title}</h2>
					<div class="post-detail-comment-meta">
						<b>${post.user_id}</b> |
						<fmt:formatDate value="${post.post_date}" pattern="yy-MM-dd" />
					</div>
					<div style="margin-top: 10px;">
						<c:out value="${post.postContentsWithBr}" escapeXml="false" />
					</div>
				</td>
			</tr>
		</table>
	</form>

	<!-- 댓글 출력 테이블 -->
	<table class = "post-detail-table">
		<!-- 최상위 댓글 입력폼 -->
		<tr>
			<td colspan="4" class="post-detail-comment-card">
				<form id="commentForm" action="writeComment.do" method="get" onsubmit="return loginedSubmit();">
					<input type="hidden" name="user_id" value="${user.user_Id}" />
					<input type="hidden" name="post_id" value="${post.post_id}" />
					<textarea name="comment_contents" rows="4" placeholder="댓글을 작성해 주세요" class="post-detail-textarea" style="resize: none;"></textarea>
					<input type="hidden" name="parent_comment_id" value="0" />
					<input type="submit" value="댓글쓰기" class="post-detail-button-submit" />
				</form>
			</td>
		</tr>

		<!-- 댓글 및 대댓글 출력 -->
		<c:forEach var="comment" items="${comments}">
			<tr>
				<td colspan="4" class="${comment.parent_comment_id == 0 ? 'post-detail-comment-card' : 'post-detail-reply-card'}">
					<div class="${comment.parent_comment_id != 0 ? 'post-detail-reply-form' : ''}">
						<div>
							<b>${comment.user_id}</b> |
							<fmt:formatDate value="${comment.comment_date}" pattern="yy-MM-dd" />
						</div>

						<!-- 일반 출력 영역 -->
						<div id="commentText-${comment.comment_id}" style="margin-top: 5px;">
							<c:out value="${comment.comment_contentsWithBr}" escapeXml="false" />
						</div>

						<!-- 수정 폼 (기본 숨김) -->
						<div id="editForm-${comment.comment_id}" style="display: none; margin-top: 5px;">
							<form action="updateComment.do" method="get">
								<input type="hidden" name="comment_id" value="${comment.comment_id}" />
								<input type="hidden" name="post_id" value="${post.post_id}" />
								<textarea name="edited_content" rows="3" style="width: 100%;">${comment.comment_contents}</textarea>
								<div style="margin-top: 5px;">
									<button type="submit" class="post-detail-button">수정완료</button>
									<button type="button" class="post-detail-button" onclick="cancelEdit(${comment.comment_id})">취소</button>
								</div>
							</form>
						</div>

						<!-- 버튼 영역 -->
						<div class="post-detail-comment-buttons">
							<c:if test="${comment.parent_comment_id == 0}">
								<button type="button" class="post-detail-button" onclick="showReplyForm(${comment.comment_id})">답글쓰기</button>
							</c:if>

							<c:if test="${sessionScope.user_id != null && sessionScope.user_id == comment.user_id}">
								<!-- 수정 버튼 -->
								<button type="button" class="post-detail-button" onclick="editComment(${comment.comment_id})">댓글수정</button>

								<!-- 삭제 폼 -->
								<form action="deleteComment.do" method="get" style="display: inline;">
									<input type="hidden" name="comment_id" value="${comment.comment_id}" />
									<input type="hidden" name="post_id" value="${post.post_id}" />
									<button type="submit" class="post-detail-button">댓글삭제</button>
								</form>
							</c:if>
						</div>
					</div>
				</td>
			</tr>

			<!-- 대댓글 입력 폼 -->
			<c:if test="${comment.parent_comment_id == 0}">
				<tr id="replyForm-${comment.comment_id}" style="display: none;">
					<td colspan="4" class="post-detail-reply-card">
						<form action="writeComment.do" method="get" onsubmit="return loginedSubmit();">
							<div class="post-detail-reply-form">
								<input type="hidden" name="user_id" value="${user.user_Id}" />
								<textarea name="comment_contents" rows="3" placeholder="답글을 입력하세요" class="post-detail-textarea"></textarea>
								<input type="hidden" name="post_id" value="${post.post_id}" />
								<input type="hidden" name="parent_comment_id" value="${comment.comment_id}" />
								<input type="submit" value="답글쓰기" class="post-detail-button-submit" />
							</div>
						</form>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

	<script>
		function submitTo(actionUrl) {
			const form = document.getElementById('myForm');
			form.action = actionUrl;
		}

		function showReplyForm(commentId) {
			const form = document.getElementById("replyForm-" + commentId);
			form.style.display = (form.style.display === "none") ? "table-row" : "none";
		}
		
		function editComment(commentId) {

		    const commentTextEl = document.getElementById("commentText-"+commentId);
		    const editFormEl = document.getElementById("editForm-"+commentId);
		    
		    commentTextEl.style.display = 'none';
		    editFormEl.style.display = 'block';
		}
		
		function cancelEdit(commentId) {
			document.getElementById("editForm-"+commentId).style.display = 'none';
			document.getElementById("commentText-"+commentId).style.display = 'block';
		}
	</script>

	<jsp:include page="/common/footer.jsp" />
</body>
</html>