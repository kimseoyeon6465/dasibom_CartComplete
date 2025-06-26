<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>중고도서 상세 보기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/junggo_view.css">
    <script src="${pageContext.request.contextPath}/resources/js/junggo_view.js" defer></script>
</head>
<body>

<h2>${book.title}</h2>
<p>게시글 작성 날짜: ${book.post_Date}</p>

<div class="bookimage">
    <img class= "slide" src="${book.image_Path}" />
</div>

<button id="prevBtn">이전</button>
<button id="nextBtn">다음</button>

<p>판매상태: ${book.sale_Status}</p>
<p>도서 제목: ${book.title}</p>
<p>가격: ${book.price}원</p>
<p>상품 상태: 깨끗함</p>
<p>결제 방법: 카카오페이</p>
<p>판매자 아이디: ${book.user_Id}</p>

<form action="${pageContext.request.contextPath}/chat.do" method="get">
    <input type="hidden" name="sellerId" value="${book.user_Id}" />
    <button type="submit">구매 문의 채팅</button>
</form>

<hr>

<h3>글 내용</h3>
<p>${book.content}</p>

<p>구매 문의는 채팅을 통해 이루어집니다. 상단의 구매 문의 채팅을 눌러주세요.</p>

<form action="${pageContext.request.contextPath}/junggo_write.do" method="get" style="display:inline;">
    <button type="submit">글쓰기</button>
</form>

<form action="${pageContext.request.contextPath}/junggo_list.do" method="get" style="display:inline;">
    <button type="submit">목록</button>
</form>

</body>
</html>
