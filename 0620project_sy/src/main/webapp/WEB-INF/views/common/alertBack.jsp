<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 오류</title>
<link rel="icon" href="./resources/images/favicon3.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./resources/style/header.css" />
<link rel="stylesheet" type="text/css" href="./resources/style/write-post.css" />
<link rel="stylesheet" type="text/css" href="./resources/style/footer.css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.js"></script>
</head>
<body>
	<h1 style="color: red;">${message}</h1>
	<hr />

	<form id="myForm" method="post">
		<div class="button-row">
			<input type="button" value="모두보기" onclick="submitTo('goPost.do')" />

			<c:choose>
				<c:when test="${empty post.post_id}">
					<input type="button" id="writebtn" value="작성하기" onclick="submitTo('insertPost.do')" />
				</c:when>
				<c:otherwise>
					<input type="hidden" name="post_id" value="${post.post_id}" />
					<input type="button" id="updatebtn" value="수정하기" onclick="submitTo('updatePost.do')" />
					<input type="hidden" name="post_date" value="${post.post_date}" />
				</c:otherwise>
			</c:choose>
		</div>

		<table>
			<tr>
				<td><select name="category">
						<option value="">말머리 선택</option>
						<option value="자유" ${post.category == '자유' ? 'selected' : ''}>자유</option>
						<option value="중고거래" ${post.category == '중고거래' ? 'selected' : ''}>중고거래</option>
						<option value="추천" ${post.category == '추천' ? 'selected' : ''}>추천</option>
						<option value="리뷰" ${post.category == '리뷰' ? 'selected' : ''}>리뷰</option>
						<option value="질문" ${post.category == '질문' ? 'selected' : ''}>질문</option>
						<option value="중고나눔" ${post.category == '중고나눔' ? 'selected' : ''}>중고나눔</option>
						<option value="모집" ${post.category == '모집' ? 'selected' : ''}>모집</option>
						<option value="굿즈" ${post.category == '굿즈' ? 'selected' : ''}>굿즈</option>
						<option value="공지" ${post.category == '공지' ? 'selected' : ''}>공지</option>
				</select></td>
			</tr>

			<tr>
				<td><input type="text" name="title" placeholder="제목" value="${post.title}" /></td>
			</tr>

			<tr>
				<td><input type="text" name="user_id" placeholder="작성자 이름" value="${post.user_id}" /></td>
			</tr>

			<tr>
				<td><textarea id="summernote" name="content">${post.post_contents}</textarea> <script>
					$('#summernote').summernote({
						height : 300,
						toolbar : [ [ 'insert', [ 'picture' ] ] ]
					});
					function submitTo(actionUrl) {
						const contentHtml = $('#summernote').summernote('code');

						// 이미지 src 추출
						const parser = new DOMParser();
						const doc = parser.parseFromString(contentHtml,
								'text/html');
						const firstImg = doc.querySelector('img');

						if (firstImg) {
							document.getElementById('image_path').value = firstImg
									.getAttribute('src');
						} else {
							document.getElementById('image_path').value = '';
						}

						// 텍스트만 추출
						const contentText = doc.body.textContent.trim();
						document.getElementById('content').value = contentText;

						// 폼 제출
						const form = document.getElementById('myForm');
						form.action = actionUrl;
						form.submit();
					}
				</script><input type="hidden" id="image_path" name="image_path" value="${posts.image_path}" /></td>
			</tr>
		</table>
	</form>

	<script>
		function submitTo(actionUrl) {
			const form = document.getElementById('myForm');
			form.action = actionUrl;
			form.submit();
		}
	</script>
</body>
</html>