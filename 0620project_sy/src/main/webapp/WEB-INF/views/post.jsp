<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="icon" href="./resources/images/favicon3.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./resources/style/post-board.css" />
<script src="${pageContext.request.contextPath}/JS/loginmodal.js"></script>
</head>
<body>
	<jsp:include page="/common/header.jsp" />
	<script>
	  const isLoggedIn = ${sessionScope.user_seq == null ? 'false' : 'true'};
	</script>
	<jsp:include page="/common/loginmodal.jsp" />
	
	

	<c:if test="${not empty alertMessage}">
		<script>
			alert("${alertMessage}");
		</script>
	</c:if>

	<div class="content">
		<h1>게시판</h1>
		<hr />

		<div class="top-bar" style="display: flex; justify-content: space-between; align-items: center;">
			<!-- 왼쪽: 글쓰기 버튼 -->
			<form action="goWritePost.do" method="post" onsubmit="return loginedSubmit();">
				<input type="submit" value="글쓰기" class="write-btn" />
			</form>

			<!-- 오른쪽: 검색창 + 검색 버튼 -->
			<form action="searchPost.do" method="get" class="search-form" style="display: flex; gap: 5px; align-items: center;">
				<input type="text" name="keyword" id="searchInput" placeholder="검색어를 입력하세요" value="${param.keyword}" /> <input type="submit" value="검색" id="searchBtn" />
			</form>
		</div>

		<table>
			<thead>
				<tr>
					<th style="width: 60px;">번호</th>
					<th>제목</th>
					<th style="width: 100px;">작성자</th>
					<th style="width: 160px;">작성일</th>
				</tr>
			</thead>
			<tbody>
            <!-- 공지글 + 일반 게시글 출력 -->
            <c:forEach var="posts" items="${combinedPosts}">
               <!-- category가 '공지'인 경우에만 'notice-row' 클래스 추가 -->
               <tr class="${posts.category == '공지' ? 'notice-row' : ''}">
                  <td>${posts.post_id}</td>
                  <td style="border-left: none;">
                     <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div style="text-align: left;">
                           <a href="showDetailPost.do?post_id=${posts.post_id}">[${posts.category}] ${posts.title}</a>
                           <c:if test="${posts.image_path == '1'}">
                              <img src="./resources/images/picture.png" alt="이미지 있음" style="width: 16px; height: 16px; margin-left: 4px;" />
                           </c:if>
                           <c:if test="${posts.image_path == '2'}">
                              <img src="./resources/images/video.png" alt="동영상 있음" style="width: 16px; height: 16px; margin-left: 4px;" />
                           </c:if>
                        </div>
                        <c:if test="${posts.commentCount > 0}">
                           <div style="text-align: right; color: gray;">(${posts.commentCount})</div>
                        </c:if>
                     </div>
                  </td>
                  <td>${posts.user_id}</td>
                  <td><fmt:formatDate value="${posts.post_date}" pattern="yy-MM-dd" /></td>
               </tr>
            </c:forEach>
         </tbody>
		</table>

		<!-- 페이징 -->
		<div class="button-row">
			<!-- 이전 버튼 -->
			<form action="goPost.do" method="get" style="display: inline;">
				<input type="hidden" name="spage" value="${currentPage - 1 < 1 ? 1 : currentPage - 1}" /> <input type="submit" value="이전" />
			</form>

			<!-- 페이지 번호 버튼 -->
			<c:forEach begin="1" end="${totalPages}" var="i">
				<form action="goPost.do" method="get" style="margin: 0; display: inline;">
					<input type="hidden" name="spage" value="${i}" /> <input type="submit" value="${i}" style="background-color: ${i == currentPage ? '#d63384' : '#ffe0f0'};
					   color: ${i == currentPage ? 'white' : '#d63384'};
					   font-weight: ${i == currentPage ? 'bold' : 'normal'};" />
				</form>
			</c:forEach>

			<!-- 다음 버튼 -->
			<form action="goPost.do" method="get" style="display: inline;">
				<input type="hidden" name="spage" value="${currentPage + 1 > totalPages ? totalPages : currentPage + 1}" /> <input type="submit" value="다음" />
			</form>
		</div>
	</div>

	<jsp:include page="/common/footer.jsp" />
</body>
</html>