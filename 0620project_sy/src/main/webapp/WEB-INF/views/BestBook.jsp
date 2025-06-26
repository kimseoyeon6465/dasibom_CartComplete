<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./CSS/design.css">
<script src="./JS/script.js"></script>
</head>
<body>

	<jsp:include page="/common/header.jsp" />
	
	<div class="best-container">
		<div class = "best-text">
			<h1>이 달의 베스트셀러</h1>
			<div class = "underline"></div>
		</div>
		<div class="best-list-wrapper">
			<c:forEach var="best" items="${books}" varStatus="status" begin="0" end="49">
				<div class="best-item">
				<div class="number-badge">${status.index + 1}</div>
				<a href="bookview.do?isbn=${best.isbn}"><img class="best-img" src="${best.image_Path}" alt="책 이미지" /></a>
				<div class="best-title">${best.title}</div>
			</div>
			</c:forEach>
		</div>
	</div>
	
	<c:if test="${empty books}">
	    <div>책 데이터가 없습니다</div>
	</c:if>
	
	<%@ include file="/common/footer.jsp" %>
</body>
</html>