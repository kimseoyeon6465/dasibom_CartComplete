<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>중고 도서 판매/나눔</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/junggo_list.css">
</head>
<body>

<jsp:include page="/common/header.jsp" />

 <h2>중고 도서 판매/나눔</h2>

<form action="junggo_write.do" method="get" class="write-form">
    <input type="submit" value="글쓰기">
</form> 

 <div class="list">
    <c:forEach var="post" items="${usedBookList}">
        <a class="container" href="junggo_view.do?post_Id=${post.post_Id}">
            <div class="bookimage">
                <c:choose>
                    <c:when test="${not empty post.image_Path}">
                        <c:choose>
                            <c:when test="${post.image_Path.startsWith('http')}">
                                <!-- 절대 URL이면 그대로 출력 -->
                                <img src="${post.image_Path}" alt="책 이미지">
                            </c:when>
                            <c:when test="${post.image_Path.startsWith('/')}">
                                <!-- '/' 로 시작하는 상대 경로면 contextPath 붙이기 -->
                                <img src="${pageContext.request.contextPath}${post.image_Path}" alt="책 이미지">
                            </c:when>
                            <c:otherwise>
                                <!-- 그냥 파일명 등일 경우, /resources/uploads/ 붙이기 (필요하면 조정) -->
                                <img src="${pageContext.request.contextPath}/resources/uploads/${post.image_Path}" alt="책 이미지">
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/resources/img/default-book.png" alt="기본 이미지">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="info">
                <div class="title">[${post.sale_Status}] ${post.title}</div>
                <div class="price">₩${post.price}</div>
                <fmt:setLocale value="ko_KR" />
                <div class="status"><fmt:formatDate value="${post.post_Date}" pattern="MM/dd a hh:mm" /></div>
                <div class="writer">${post.user_Id}</div>
            </div>
        </a>
    </c:forEach>
</div>

<!-- 페이지네이션 + 검색 폼 컨테이너 -->
<div class="pagination-search-wrapper">
    
    <!-- 페이지네이션 -->
    <form method="get" action="junggoList.do" class="pagination">
        <button name="page" value="${prevPage}" <c:if test="${!hasPrev}">disabled</c:if> >&lt;-</button>
        <c:forEach var="p" begin="1" end="${totalPages}">
            <button name="page" value="${p}" <c:if test="${currentPage == p}">disabled</c:if> >${p}</button>
        </c:forEach>
        <button name="page" value="${nextPage}" <c:if test="${!hasNext}">disabled</c:if> >&gt;-</button>
    </form>

    <!-- 검색 폼 -->
    <form method="get" action="junggoList.do" class="search-box">
        <label for="date">기간</label>
        <select name="date" id="date">
            <option value="전체기간">전체기간</option>
            <option value="1일">1일</option>
            <option value="1주">1주</option>
            <option value="1개월">1개월</option>
            <option value="6개월">6개월</option>
            <option value="1년">1년</option>
        </select>

        <label for="classification">검색 분류</label>
        <select name="classification" id="classification">
            <option value="글+댓글">글+댓글</option>
            <option value="제목">제목</option>
            <option value="작성자">작성자</option>
        </select>

        <label for="keyword">검색어</label>
        <input type="text" name="keyword" id="keyword">
        <input type="submit" value="검색">
    </form>

</div>

<jsp:include page="/common/footer.jsp" />
</body>
</html>
