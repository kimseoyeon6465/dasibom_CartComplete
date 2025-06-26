<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<html>
<head>
	<title>SignUp</title>
	<!-- FadeIn 효과 스타일 css -->
	<!-- 
	효과를 적용할 컴포넌트 --- class="animate-on-load" 
	효과 딜레이 주기 --- style="animation-delay: 0.2s;" -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/CSS/effect-fadein.css">
	<!-- Font  'Paperlogy' -->
	<link rel="stylesheet" type="text/css"
	    href="${pageContext.request.contextPath}/CSS/fonts.css" />
	<!-- 회원가입 폼 style css -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/signup-form-style.css">
	
	<!-- 회원가입 폼 script js -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/signupForm-script.js"></script>
	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/jquery-3.7.1.js"></script>

</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/common/header.jsp" />

	<div class="signup-section animate-on-load">
	<!-- 일반회원가입 폼 -->	
        <form 
         id="signupForm" 
         action="insertUser.do" 
         method="post"
     	>
		 	<!-- 정보기입란 -->
           	<div class="signup-input">
           	
           		<h2>일반 회원 가입</h2>
           		<hr>
           	
	            <div class="input-line-signup">
	                <label for="irum">이름</label>
	                <input
	                    type="text"
	                    id="irum"
	                    name="irum"
	                    placeholder="이름"
	                    required
	                    autoFocus
	                />
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="user_Id">아이디</label>
	                <input
	                    type="text"
	                    id="user_Id"
	                    name="user_Id"
	                    placeholder="아이디"
	                    required
	                    oninput="resetUserIdDuplication()"
	                />
	                <button 
	                	type="button" 
	                	class="common-button"
	                	onClick="checkUserIdDuplication()"
	                >중복확인</button>
	                <!-- 아이디 중복확인 데이터 -->
	                <input 
	                	type="hidden" 
	                	name="userIdDuplication"
	                	value="userIdUnchecked"
	                /> 
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="name">닉네임</label>
	                <input
	                    type="text"
	                    id="name"
	                    name="name"
	                    placeholder="닉네임"
	                    required
	                    oninput="resetNameDuplication()"
	                />
	                <button 
	                	type="button" 
	                	class="common-button"
	                	onClick="checkNameDuplication()"
	                >중복확인</button>
	                <!-- 닉네임 중복확인 데이터 -->
	                <input 
	                	type="hidden" 
	                	name="nameDuplication"
	                	value="nameUnchecked"
	                /> 
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="email">이메일</label>
	                <input
	                    type="email"
	                    id="email"
	                    name="email"
	                    placeholder="이메일"
	                    required
	                />
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="tel">전화번호</label>
	                <input 
		                type="text" 
		                id="tel1" 
		                name="tel1"
		                placeholder="010"  
		                value="${fn:substring(searchVO.tel,0,3)}" 
		                />&nbsp;-
    				<input 
	    				type="text" 
	    				id="tel2" 
	    				name="tel2"  
	    				placeholder="1234"
	    				value="${fn:substring(searchVO.tel,4,8)}" 
    				/>&nbsp;-
    				<input 
	    				type="text" 
	    				id="tel3" 
	    				name="tel3"
	    				placeholder="5678"  
	    				value="${fn:substring(searchVO.tel,9,13)}" 
    				/>
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="address">주소</label>
	                <input
	                    type="text"
	                    id="address"
	                    name="address"
	                    placeholder="주소"
	                    required
	                />
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="pw">비밀번호</label>
	                <input
	                    type="password"
	                    id="pw"
	                    name="pw"
	                    placeholder="영문,숫자,특문10자이상"
	                    required
	                  	oninput="comparePassword()"
	                />
	              
	                
	            </div>
	            
	            <div class="input-line-signup">
	                <label for="pwRe">비밀번호 재확인</label>
	                <input
	                    type="password"
	                    id="pwRe"
	                    name="pwRe"
	                    placeholder="비밀번호 재확인"
	                    required
	                    oninput="comparePassword()"
	                />
	                <!-- 안내메세지 -->
	                <span 
	                	id="pwMatchMsg" 
	                	style="font-size: 12px;"></span>
	                <!-- 비밀번호 확인 데이터 -->
	                <input 
	                	type="hidden" 
	                	name="isPwEqual"
	                	value="pwUnequaled"
	                /> 
	            </div>
	            
            </div> <!-- signup-input 끝 -->
            
            
            <!-- '이용약관동의' 체크박스 -->
<%--             <%@ include file="signupTerms.jsp" %> --%>
			<!--
		      	'이용약관동의' 체크박스
		    -->
			<div class="signup-agreements-check">
				<h2>이용 약관 동의</h2>
				<hr>
		
				<!-- 체크리스트 전체 동의 체크박스 -->
				<div class="chk-all">
					<label class="checkbox"> <input type="checkbox"
						id="selectAll" onchange="allChecked(this.checked)"> 
						<span>전체 동의 합니다.</span>
					</label>
				</div>
		
				<!-- 체크리스트 개별 체크박스 -->
				<div class="chk-list">
		
					<ul class="list">
		
						<li class="item"><label class="checkbox"> <input
								type="checkbox" id="term-0" class="required-check"
								onchange="selectChecked(this.checked, 0)"> <span
								class="txt">서비스 이용 약관 동의 [필수]</span>
						</label></li>
		
						<li class="item"><label class="checkbox"> <input
								type="checkbox" id="term-1" class="required-check"
								onchange="selectChecked(this.checked, 1)"> <span
								class="txt">개인정보 수집 및 이용 동의 [필수]</span>
						</label></li>
		
						<li class="item"><label class="checkbox"> <input
								type="checkbox" id="term-2" class="required-check"
								onchange="selectChecked(this.checked, 2)"> <span
								class="txt">광고성 마케팅 정보 수신 동의 [선택]</span>
						</label></li>
		
		
					</ul>
				</div>
				<!-- chk-list 끝 -->
			</div>
			<!-- signup-agreements-check 끝 -->
            
			<button type="submit" class="submit-button" id="submitBtnSignup">가입하기</button>
           	
		</form>
      
      </div><!-- signup-section 끝 -->	     
      
      <!-- 푸터 -->
      <jsp:include page="/common/footer.jsp" /> 
</body>
</html>


				         
