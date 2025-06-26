<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/common/header.jsp" />
	
	주문번호: ${orderId} <br>
	주문 도서명: ${orderedBookTitle} <br>
	주문일: ${orderDate} <br>
	상태: ${orderStatus} <br>
	
	<!-- 푸터 -->
	<jsp:include page="/common/footer.jsp" />

	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/jquery-3.7.1.js"></script>
	
</body>
</html>