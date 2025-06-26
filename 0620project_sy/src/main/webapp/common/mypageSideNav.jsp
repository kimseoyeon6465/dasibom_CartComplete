<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Font  'Paperlogy' -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/fonts.css" />
</head>
<body>
	<div class="mypage-side-navbar">
		<div class="title-mypage">
			<font>마이페이지</font>
		</div>
		<ul>
			<li><a href="myUpdateInfo.do">내 정보 수정</a></li>
			<li><a href="myOrderList.do">구매 내역</a></li>
			<li><a href="myWishlist.do">내 찜 목록</a></li>
			<li><a href="myReview.do">내 리뷰 보기</a></li>
			<li><a href="myJunggo.do">내 중고 거래</a></li>
			<li><a href="myDonationList.do">내 나눔 목록</a></li>
			<li><a href="myPostList.do">내 게시글 보기</a></li>
		</ul>
	</div>

	<!-- 내부 stylesheet -->
	<style>
		body {
			font-family: 'Paperlogy', sans-serif;
		}
	
		.mypage-side-navbar { 
			width: 15%; 
 			min-width: 170px;
			padding: 20px 20px; 
			background-color: #fff; 
			height: auto; 
			box-sizing: border-box;
			border-top: 1px solid #ddd;
		  	border-bottom: 1px solid #ddd;
			position: fixed;  
			top: 100px; 
			left: 0; 
			z-index: 1000; 
		} 
		
		.mypage-side-navbar .title-mypage {
			text-decoration: none;
			color: #333;
			font-size: 24px;
			font-weight: 800;
			display: inline-block;
			text-decoration: none;
			padding: 8px 0;
			margin-left: 10px;
			margin-bottom: 15px;
		}
		/* 
		.mypage-side-navbar .title-mypage a {
			text-decoration: none;
			color: #333;
			font-size: 24px;
			font-weight: 800;
			display: inline-block;
			text-decoration: none;
			padding: 8px 0;
			margin-left: 10px;
			margin-bottom: 15px;
			transition: color 0.3s ease, border-bottom 0.3s ease;
			border-bottom: 2px solid transparent; 
		}
		
		.mypage-side-navbar .title-mypage a:hover {
			color: #FF77AA;
			font-weight: bold; 
		}
		  */
		.mypage-side-navbar ul a {
			text-decoration: none;
			color: #333;
			font-size: 16px;
			font-weight: 400;
			display: inline-block;
			text-decoration: none;
			padding: 8px 0;
			margin-left: 10px;
			transition: color 0.3s ease, border-bottom 0.3s ease;
			border-bottom: 2px solid transparent; 
 		} 
		
		.mypage-side-navbar ul a:hover {
			color: #FF77AA;
			border-bottom: 2px solid #FF77AA; 
			font-weight: bold; 
		}
		
		.mypage-side-navbar ul { 
			list-style: none; 
			padding-left: 0; 
		} 
		
		.mypage-side-navbar li { 
			margin-bottom: 6px; 
		} 

	</style>

</body>
</html>