<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/loginmodal.css">
<script src="${pageContext.request.contextPath}/JS/loginmodal.js"></script>

<!-- 로그인 모달 -->
<div id="loginModal" class="login-modal-overlay">
	<div class="login-modal">
		<h2>로그인 후 이용가능합니다</h2>
		<p>로그인 페이지로 이동하시겠습니까?</p>
		<form action = "login.do" method="get"> 
			<div class="modal-buttons">
				<button type="button" class="cancel-btn" onclick="closeLoginModal()">취소</button>
				<button type="submit" class="confirm-btn" onclick="goToLogin()">확인</button>
			</div>
		</form>
	</div>
</div>
