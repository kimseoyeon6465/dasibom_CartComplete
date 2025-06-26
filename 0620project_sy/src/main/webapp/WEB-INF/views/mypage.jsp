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

	<!-- 사이드 네비바 -->
	<jsp:include page="/common/mypageSideNav.jsp" />

	<h3>${sessionScope.user_id}님 환영합니다!</h3>
	<h2>마이페이지</h2>

	<!-- 푸터 -->
	<jsp:include page="/common/footer.jsp" />
</body>
</html>