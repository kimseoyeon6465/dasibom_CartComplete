<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
<link rel="stylesheet" type="text/css" href="./CSS/design.css" />
<link rel="stylesheet" href="https://jhammann.github.io/sakura/dist/sakura.css">

<script>const contextPath = '${pageContext.request.contextPath}';</script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

<script src="./JS/script.js"></script>
<script src="./JS/swip.js"></script>

</head>
<body>
	<jsp:include page="/common/header.jsp" />
	<div id="sakura-petals">
	<!-- 이 달의 베스트셀러 -->
		<div class="best-container">
			<div class = "best-text">
				<h1>이 달의 베스트셀러</h1>
				<div class = "underline"></div>
			</div>
			<div class="best-list-wrapper">
				<c:forEach var="best" items="${bestlist}" varStatus="status" begin="0" end="9">
					<div class="best-item">
					<div class="number-badge">${status.index + 1}</div>
					<a href="bookview.do?isbn=${best.isbn}"><img class="best-img" src="${best.image_Path}" alt="책 이미지" /></a>
					<div class="best-title">${best.title}</div>
				</div>
				</c:forEach>
			</div>
		</div>
	
	<!-- 요즘 뜨는 이벤트 -->
		<div class = "event-container">
			<div class="event-text">
				<h1>요즘 뜨는 이벤트</h1>
				<div class="underline"></div>
			</div>
			<div class="event-list-fixed">
				<div class="event-box side-box">
					<img id="eventImg1" src="${pageContext.request.contextPath}/IMG/EVENT/event1.PNG" alt="이벤트1">
				</div>
				<div class="event-box main-box">
					<img id="eventImg2" src="${pageContext.request.contextPath}/IMG/EVENT/event2.PNG" alt="이벤트2">
				</div>
				<div class="event-box side-box">
					<img id="eventImg3" src="${pageContext.request.contextPath}/IMG/EVENT/event3.PNG" alt="이벤트3">
				</div>
			</div>
		</div>
	
	<!-- 전체 이벤트 미리보기 -->
		<div class="event-preview-container">
			<c:forEach var="i" begin="1" end="6">
				<div class="preview-box">
					<img src="${pageContext.request.contextPath}/IMG/EVENT/event${i}.PNG" alt="이벤트${i}">
				</div>
			</c:forEach>
		</div>
	
	<script src="https://jhammann.github.io/sakura/dist/sakura.js"></script>
	<script>
		var sakura = new Sakura('#sakura-petals', {
		    
		    fallSpeed: 1.2,
		    maxSize: 27,
		    minSize: 13,
		    blowAnimations: ['blow-soft-left', 'blow-medium-left'],
		    swayAnimations: ['sway-1', 'sway-2', 'sway-3']
		  });
	</script>
	</div>
	<jsp:include page="/common/footer.jsp" />
	
</body>
</html>