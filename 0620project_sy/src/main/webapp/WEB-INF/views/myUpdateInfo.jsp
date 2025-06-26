<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 내 정보 수정</title>



<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/myUpdateInfo-style.css">

<!-- jQuery <body>하단에 선언 -->
<!-- JS <body>하단에 선언 -->


</head>
<body>


	<!-- 헤더 -->
	<header>
		<jsp:include page="/common/header.jsp" />
	</header>

	<!-- 레이아웃 (사이드바 + 메인컨테이너) -->
	<div class="layout-wrapper">

		<!-- 사이드네비바 -->
		<div class="mypage-side-nav">
			<jsp:include page="/common/mypageSideNav.jsp" />
		</div>

		<!-- 메인 컨테이너 -->
		<div class="main-container">
			<div class="upper-form" style="display: flex;">
				<table id="userInfoUpdateTable1" border="1">
					<tbody>
						<tr>
							<!-- 이름 -->
							<td>이름</td>
							<td colspan="2">
								<span id="txt-irum">${user.irum}</span> 
								<input type="text" id="irum" name="irum" style="display: none;" />
							
								<button class="btn-view" data-field="irum">변경하기</button>
								<button class="btn-save" data-field="irum" style="display: none;">확인</button>
							</td>

						</tr>
						<tr>
							<!-- 아이디 -->
							<td>아이디</td>
							<td colspan="2"><span id="txt-userId">${user.user_Id}</span>
								<input type="text" id="userId" name="userId" style="display: none;" /></td>
						</tr>
						<tr>
							<tr>
								<td>닉네임</td>
								<td colspan="2">
								  <span id="txt-name">${user.name}</span>
								
									<span id="input-name" style="display: none;">
									  <input type="text" id="name" name="name" placeholder="닉네임 입력" />
									  <button type="button" id="check-name">중복확인</button>
									</span>
								
									<button class="btn-view" data-field="name">변경하기</button>
									<button class="btn-save" data-field="name" disabled style="display: none;">확인</button>
								</td>
							</tr>

						</tr>
						<tr>
							<!-- 이메일 -->
							<td>이메일</td>
							<td colspan="2">
								<span id="txt-email">${user.email}</span> <input
								type="text" id="email" name="email" style="display: none;" />
							
								<button class="btn-view" data-field="email">변경하기</button>
								<button class="btn-save" data-field="email" style="display: none;">확인</button>
							</td>
						</tr>
					</tbody>
				</table>

				<div class="img-container">
					<img src="#" width="100px" height="100px" alt="userFrofile.img" />
					<br>
					<button type="button">업로드</button>
				</div>
			</div>
			<!-- upper-form 끝 -->

			<div class="lower-form">
				<table id="userInfoUpdateTable2" border="1">
					<tbody>
						<tr>
							<td>전화번호</td>
							<td colspan="2">
							  	<!-- 표시용 텍스트 -->
								<span id="txt-tel">${user.tel}</span>
							
								<!-- 전화번호 input 그룹 -->
								<span id="input-tel" style="display: none; margin-left: 10px;">
								<input type="text" id="tel1" size="3" maxlength="3" style="width: 50px;" />
								-
								<input type="text" id="tel2" size="4" maxlength="4" style="width: 60px;" />
								-
								<input type="text" id="tel3" size="4" maxlength="4" style="width: 60px;" />
								</span>
							
								<!-- 버튼 -->
								<button class="btn-view" data-field="tel">변경하기</button>
								<button class="btn-save" data-field="tel" style="display: none;">확인</button>
							  </td>
						</tr>


						<tr>
							<!-- 주소 -->
							<td>주소</td>
							<td><span id="txt-address">${user.address}</span> <input
								type="text" id="address" name="address" style="display: none;" /></td>
							<td>
								<button class="btn-view" data-field="address">변경하기</button>
								<button class="btn-save" data-field="address" style="display: none;">확인</button>
							</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td colspan="2">
							  <!-- 기본 상태 버튼 -->
								<button id="view-pw">비밀번호 변경하기</button>
							
								<!-- 숨겨진 입력 필드들 -->
								<div id="pw-input-group" style="display: none; margin-top: 10px;">
								<input type="password" id="pw-current" placeholder="기존 비밀번호" style="display: block; margin-bottom: 6px;" />
								<input type="password" id="pw-new" placeholder="새 비밀번호" style="display: block; margin-bottom: 6px;" />
								<input type="password" id="pw-confirm" placeholder="새 비밀번호 확인" style="display: block; margin-bottom: 6px;" />
								<button id="save-pw" disabled>확인</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- lower-form 끝 -->
		</div>
		<!-- main-container 끝 -->
	</div>
	<!-- layout-wrapper 끝 -->

	<!-- 푸터 -->
	<footer>
		<jsp:include page="/common/footer.jsp" />
	</footer>

	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/jquery-3.7.1.js"></script>

	<!-- JS -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/myUpdateInfo-script.js"></script>



</body>
</html>