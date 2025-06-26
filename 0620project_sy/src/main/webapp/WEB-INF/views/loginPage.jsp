<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
	<!-- FadeIn 효과 스타일 css -->
	<!-- 
	효과를 적용할 컴포넌트 --- class="animate-on-load" 
	효과 딜레이 주기 --- style="animation-delay: 0.2s;" -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginpage.css">
	<!-- Font  'Paperlogy' -->
<%-- 	<link rel="stylesheet" type="text/css"
	    href="${pageContext.request.contextPath}/CSS/fonts.css" /> --%>

	<!-- jQuery -->
	<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/jquery-3.7.1.js"></script>
 --%>
 
 
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/common/header.jsp" />

<div class="login-wrapper">
	
	   <!-- 로고 -->
    <img src="${pageContext.request.contextPath}/IMG/trimmed_logo.png" class="logo" alt="로고" width=200px>
	    <form id="login-form" action="login.do" method="post">
	    
		    <input type="hidden" name="redirect" value="${param.redirect}" />
    
	        <h2 class="logo-title">다시, 봄</h2>
	        
	      <!-- (추가) 로그인 후 돌아올 경로 전달 (bookview.do) 
	       로그인 후 다시 돌아갈 페이지 주소(redirect)를 서버에 전달하기 위한 용도입니다.
예) bookview.do?isbn=1234 → 로그인 페이지로 이동 → 로그인 성공 시 다시 bookview.do?isbn=1234로 리디렉션-->
    
		 <div class="input-wrapper">	
<!-- 	        <div class="linput-line-login"> -->
	                <!-- <label for="userId">아이디</label> -->
	                <input type="text"
	                       id="userId"
	                       name="user_Id"
	                       placeholder="아이디를 입력해주세요"
	                       required />
	            </div>
				 <div class="input-wrapper">
<!-- 	            <div class="input-line-login"> -->
	        <!--         <label for="pw">비밀번호</label> -->
	                <input type="password"
	                       id="pw"
	                       name="pw"
	                       placeholder="비밀번호를 입력해주세요"
	                       required />
	            </div>	
	        <button type="submit" class="login-button" id="submitBtnLogin">로그인</button>
		</form>
		
	        <div class="links">
	            <a href="#">아이디찾기</a> |
	            <a href="#">비밀번호찾기</a> |
	            <a href="signup.do">회원가입</a>
	        </div>
		</div>
	
	<!-- 푸터 -->
	<jsp:include page="/common/footer.jsp" />

</body>
</html>