<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CSS/header.css">
<script src="${pageContext.request.contextPath}/JS/script.js"></script>

</head>
<body>
	<!-- 
		로그인 세션 정보 표기 테스트용 headerTmp.jsp
 	-->
	<div class="header-logoline">

		<!-- 로고 누르면 메인페이지로 이동 -->
		<a href="MainPage.do"> <img class="header-logo-img"
			src="${pageContext.request.contextPath}/IMG/Logo.png"></img>
		</a>

		<!-- 도서 검색 -->
		<form action="books.do" method="get">
			<div class="header-search-container">
				<input class="header-search-Input" type="text" name="query" placeholder="검색어를 입력하세요"> 
				<input type="hidden" name="currentPage" value=1>
				<button type="submit" class="header-search-btn">🔍</button>
			</div>
		</form>
		<!-- 장바구니 버튼 -->
		<a href="cartAll.do"> <img class="header-cartbtn-img"
			src="${pageContext.request.contextPath}/IMG/cart.jpeg" alt="장바구니" />
		</a>

		<!-- 마이페이지 -->
		<div class="mypage-container">
			<img class="header-mypagebtn-img"
				src="${pageContext.request.contextPath}/IMG/mypage.png" alt="마이페이지"
				onclick="toggleDropdown()" />

			<!-- 
			
			## 이곳 수정 ##
			. 로그인 세션이 존재하면 사용자 아이디 정보 표시 및 마이페이지 버튼, 로그아웃 버튼 보이기
			. 로그인 세션이 없으면 로그인 버튼, 회원가입 버튼 보이기
			. 마이페이지 버튼 url : mypage.do -> myUpdateInfo.do 수정
			-->
			<div class="header-mypagebtn-dropdown" id="mypageDropdown">
				<c:choose>
					<c:when test="${not empty sessionScope.userId}">
						<%-- 여기 수정됨! --%>
						<div style="padding: 5px 10px;">${sessionScope.userId}님
							환영합니다!</div>
						<button
							onclick="location.href='/dasibom/myUpdateInfo.do'">마이페이지</button>
						<button
							onclick="location.href='/dasibom/logout.do'">로그아웃</button>
					</c:when>

					<c:otherwise>
						<button
							onclick="location.href='/dasibom/login.do'">로그인</button>
						<button
							onclick="location.href='/dasibom/signup.do'">회원가입</button>
					</c:otherwise>
				</c:choose>

			</div>
			<!-- mypage-dropdown 끝 -->
		</div>
		<!-- mypage-container 끝 -->
	</div>
	<!-- headerlogoline 끝 -->

	<div class="header-pathline-container">
		<div class="header-dropdown-container">
			<button class="header-pathbtn" onclick="toggleGenreDropdown()">장르별</button>
			<div class="header-genre-dropdown" id="genreDropdown">
				<button onclick="location.href='genre.do?category=소설'">소설</button>
				<button onclick="location.href='genre.do?category=시/에세이'">시/에세이</button>
				<button onclick="location.href='genre.do?category=인문'">인문</button>
				<button onclick="location.href='genre.do?category=요리/건강'">요리/건강</button>
				<button onclick="location.href='genre.do?category=취미'">취미</button>
				<button onclick="location.href='genre.do?category=여행'">여행</button>
				<button onclick="location.href='genre.do?category=컴퓨터/IT'">컴퓨터/IT</button>
				<button onclick="location.href='genre.do?category=만화'">만화</button>
			
			</div>
		</div>
		<button class="header-pathbtn"
			onclick="location.href='junggo_list.do'">중고거래</button>
		<button class="header-pathbtn" onclick = "location.href='goPost.do'">커뮤니티</button>
		<button class="header-pathbtn" onclick = "location.href='bestbook.do'">베스트</button>
		<button class="header-pathbtn" onclick = "location.href='newbook.do'">새로
			나온 책</button>
		 <button class="header-pathbtn" onclick="location.href='event_list.do'">이벤트</button>
      <button class="header-pathbtn" onclick="location.href='goods_list.do'">굿즈샵</button>
		<button class="header-pathbtn" onclick = "location.href='cartAll.do'">장바구니</button>
		<button class="header-pathbtn" onclick = "location.href='wishlist.do'">찜</button>
					<button class="header-pathbtn" onclick = "location.href='guestOrderForm.do'">비회원 주문하기</button>
			
	
	</div>
</body>
</html>