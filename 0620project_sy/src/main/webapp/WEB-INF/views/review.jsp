<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bookview.css">
</head>
<body>

	<div class="review-write-box">
		<h3>리뷰 작성</h3>
		<form id="reviewForm"
			action="${pageContext.request.contextPath}/review.do?action=insert"
			method="post" onsubmit="return handleReviewSubmit();">
			<input type="hidden" name="isbn" value="${book.isbn}"> <input
				type="hidden" name="page" value="BookView">

			<div class="flower-rating-input" data-rate="0">
				<c:forEach begin="1" end="5" var="i">
					<img src="${pageContext.request.contextPath}/IMG/noflower.png"
						data-index="${i}" data-state="0" alt="${i}점">
				</c:forEach>
			</div>
			<input type="hidden" id="reviewScore" name="review_score" value="0">
			<div class="review-textarea-wrapper">
				<textarea name="review_text"
					placeholder="리뷰를 작성해주세요. 주제와 무관한 댓글, 악플 등의 글은 삭제될 수 있습니다"></textarea>
				<button type="submit" class="cherry-blossom-button">리뷰 등록</button>
			</div>
		</form>
	</div>

	<!-- 리뷰 목록 -->
	<c:forEach var="review" items="${reviewList}">
		<div class="review-box">
			<div class="flower-display" data-rating="${review.review_score}"></div>
			<p class="review-text-display">${review.review_text}</p>
			<p class="review-meta">
				작성자 :
				<c:choose>
					<c:when test="${user.user_Id == review.user_id}">
                    ${review.user_id}(작성자)
                </c:when>
					<c:otherwise>
                    ${fn:substring(review.user_id,0,3)}***
                </c:otherwise>
				</c:choose>
				<span class="review-date-display"> <fmt:formatDate
						value="${review.review_date}" pattern="yy/MM/dd" />
				</span>
			</p>

			<c:if test="${not empty review.image_path}">
				<div class="review-image">
					<img src="${pageContext.request.contextPath}${review.image_path}"
						alt="리뷰 이미지" width="150" />
				</div>
			</c:if>

			<div class="review-actions">
				<form
					action="${pageContext.request.contextPath}/review.do?action=like"
					method="post">

					<input type="hidden" name="cid" value="${review.cid}"> <input
						type="hidden" name="isbn" value="${book.isbn}">
					<!-- 좋아요 버튼 반드시 isbn 값도 함께 넘기기   -->
					<button type="submit" class="like-button">👍 좋아요
						${review.likes}</button>
				</form>

				<form
					action="${pageContext.request.contextPath}/review.do?action=update"
					method="post">

					<input type="hidden" name="cid" value="${review.cid}">
					<input type="hidden" name="isbn" value="${book.isbn}">
					<input type="hidden" name="user_seq" value="${sessionScope.user.user_seq}">
					<button type="button" onclick="showEditForm(${review.cid})">수정</button>

				</form>

			<form action="${pageContext.request.contextPath}/review.do?action=delete" method="post">
			    <input type="hidden" name="cid" value="${review.cid}">
			    <input type="hidden" name="isbn" value="${book.isbn}">
			    <button type="submit">삭제</button>
			</form>

				<!-- ▶ 댓글 열기 버튼 -->
				<button type="button" onclick="toggleReplies(${review.cid})">💬
					댓글</button>
			</div>

			<!-- ▶ 댓글 리스트 출력 -->
			<div class="reply-section" id="reply-${review.cid}"
				style="display: none; margin-left: 30px; margin-top: 10px;">
				<c:if test="${not empty replyMap[review.cid]}">
					<c:forEach var="reply" items="${replyMap[review.cid]}">
						<div class="reply-box">
							<p class="reply-text-display">${reply.review_text}</p>
							<p class="reply-meta">
								작성자 :
								<c:choose>
									<c:when test="${user.user_id == reply.user_id}">
                                    ${reply.user_id}(작성자)
                                </c:when>
									<c:otherwise>
                                    ${fn:substring(reply.user_id,0,3)}***
                                </c:otherwise>
								</c:choose>
								<span class="reply-date-display"> <fmt:formatDate
										value="${reply.review_date}" pattern="yy/MM/dd" />
								</span>
							</p>
						</div>
					</c:forEach>
				</c:if>
			</div>


			<!-- ▶ 댓글 입력창 (기본은 숨김) -->
			<div class="reply-input-section" id="reply-input-${review.cid}"
				style="display: none; margin-left: 30px; margin-top: 10px;">
				<form action="/reply/add" method="post">
					<input type="hidden" name="cid" value="${review.cid}">
					<textarea name="review_text" placeholder="1000자 이내로 입력해주세요."
						maxlength="1000" style="width: 100%; height: 80px;"></textarea>
					<div style="text-align: right; margin-top: 5px;">
						<button type="button" onclick="hideReplyInput(${review.cid})"
							style="background-color: #888; color: white; border: none; padding: 5px 10px;">취소</button>
						<button type="submit"
							style="background-color: #9988ee; color: white; border: none; padding: 5px 10px;">등록</button>
					</div>
				</form>
			</div>
			<hr>
		</div>
	</c:forEach>


	<!-- 벚꽃 점수 입력 스크립트 
	벚꽃 이미지 경로를 JS 변수로 저장-->


	<script>
    const filledFlowerPath = "${pageContext.request.contextPath}/IMG/flower.png";
	const halfFlowerPath = "${pageContext.request.contextPath}/IMG/flower_half.png";
	const emptyFlowerPath = "${pageContext.request.contextPath}/IMG/noflower.png";

	// [수정] 벚꽃 점수 입력 UI 스크립트
	const flowerContainer = document.querySelector('.flower-rating-input');
	const flowerImages = flowerContainer.querySelectorAll('img');
	const scoreInput = document.getElementById('reviewScore');

	// 점수 상태 배열: 0(빈), 0.5(반), 1(가득)
	let currentRating = 0;
	
	flowerImages.forEach((img, i) => {
	    img.addEventListener('click', (e) => {
	        const index = i;
	        const rect = img.getBoundingClientRect();
	        const clickX = e.clientX - rect.left;
	        const isHalf = clickX < rect.width / 2;
	
	        currentRating = isHalf ? index + 0.5 : index + 1;
	        scoreInput.value = currentRating;
	        flowerContainer.dataset.rate = currentRating;
	
	        updateFlowerImages(currentRating);
	    });
	});
	
	function updateFlowerImages(rating) {
	    flowerImages.forEach((img, i) => {
	        const fullIndex = i + 1;
	        if (rating >= fullIndex) {
	            img.src = filledFlowerPath;
	        } else if (rating >= fullIndex - 0.5) {
	            img.src = halfFlowerPath;
	        } else {
	            img.src = emptyFlowerPath;
	        }
	    });
	}
	
    // 마우스가 점수 영역을 떠났을 때
		flowerContainer.addEventListener('mouseout', () => {
		    const currentRating = flowerContainer.dataset.rate;
		    			// 클릭으로 이미지로 변환
		    flowerImages.forEach((img, i) => {
		        if (flowerStates[i] === 1) {
		            img.src = filledFlowerPath;
		        } else if (flowerStates[i] === 0.5) {
		            img.src = halfFlowerPath;
		        } else {
		            img.src = emptyFlowerPath;
		        }
		    });
		});



    // 벚꽃 점수 렌더링 (리뷰 목록 용)
	const ctx = "${pageContext.request.contextPath}";
	const filled = ctx + "/IMG/flower.png";
	const half = ctx + "/IMG/flower_half.png";
	const empty = ctx + "/IMG/noflower.png";

    document.querySelectorAll('.flower-display').forEach(container => {
        const rating = parseFloat(container.dataset.rating);
        container.innerHTML = ''; // 중복 생성을 막기 위해 초기화
        for (let i = 1; i <= 5; i++) {
            const img = document.createElement('img');
            img.src = rating >= i ? filled : (rating >= i - 0.5 ? half : empty);
            container.appendChild(img);
        }
    });
   </script>



	<!-- 리뷰 로그인 모달 -->
	<div id="loginModal" class="login-modal-overlay">
		<div class="login-modal">
			<h2>로그인 후 이용가능합니다</h2>
			<p>로그인 페이지로 이동하시겠습니까?</p>
			<div class="modal-buttons">
				<button class="cancel-btn" onclick="closeLoginModal()">취소</button>
				<button class="confirm-btn" onclick="goToLogin()">확인</button>
			</div>
		</div>
	</div>

	<script>
		  function closeLoginModal() {
		    document.getElementById("loginModal").style.display = "none";
		  }
		
		  function goToLogin() {
		    window.location.href = "/login.do";
		  }
		</script>


	<script>
	  // 로그인 여부를 정확히 판단하는 안전한 방법
	  const isLoggedIn = "${sessionScope.user_seq == null ? 'false' : 'true'}";
	</script>


	<script>
	
	<!--수정 삭제-->
	function showEditForm(cid) {
		  if (!requireLoginOnly()) return;

		  // 이미 수정폼이 열려 있으면 닫기
		  const existing = document.getElementById('edit-form-' + cid);
		  if (existing) {
		    existing.style.display = 'none';
		    return;
		  }

		  // 폼을 해당 위치에 삽입 (기존 리뷰 내용을 불러와도 좋음)
		  const reviewBox = document.querySelector([data-review-id='${cid}']);
		  const formHtml = 
		    <form action="/review/update" method="post">
		      <input type="hidden" name="cid" value="${cid}">
		      <textarea name="review_text" style="width:100%; height:80px;">기존 내용</textarea>
		      <button type="submit">저장</button>
		    </form>
		  ;
		  reviewBox.insertAdjacentHTML('beforeend', <div id="edit-form-${cid}">${formHtml}</div>);
		}
	
	
	function handleDelete(cid) {
		  if (!requireLoginOnly()) return false;
		  return confirm("정말 삭제하시겠습니까?");
		}

	
	<!-- 좋아요버튼 회원 비회원 처리 -->
	const allowGuestLikeMultiple = true; // ← 테스트할 때만 true

	function handleLikeSubmit(cid) {
		const isLoggedIn = ${sessionScope.user_seq == null ? "false" : "true"};

	  if (!isLoggedIn && !allowGuestLikeMultiple) {
	    if (localStorage.getItem('liked_' + cid)) {
	      alert("이미 좋아요를 누르셨습니다.");
	      return false;
	    } else {
	      localStorage.setItem('liked_' + cid, 'true');
	    }
	  }

	  return true;
	}
	</script>

	<script>
	// 댓글 버튼 클릭시 토글, 입력창 표시 추가
	function toggleReplies(cid) {
		  const section = document.getElementById('reply-' + cid);
		  const inputBox = document.getElementById('reply-input-' + cid);
		  
		  if (section) {
		    section.style.display = (section.style.display === 'none') ? 'block' : 'none';
		  }
		  if (inputBox) {
		    inputBox.style.display = (section.style.display === 'none') ? 'none' : 'block';
		  }
		}
		
		function hideReplyInput(cid) {
		  const inputBox = document.getElementById('reply-input-' + cid);
		  if (inputBox) inputBox.style.display = 'none';
		}

		// 리뷰 등록 폼 전송전 로그인 여부 확인
     // (리뷰 등록용 로그인) 로그인 상태 확인 및 폼 제출 처리 함수
		function handleReviewSubmit() {
			const isLoggedIn = "${sessionScope.user_seq == null ? 'false' : 'true'}";
		
		    if (isLoggedIn == 'false') {
		        //로그인 하지 않은 상태
		        document.getElementById("loginModal").style.display = "flex";
		        return false;
		    }
		    
		    const score = document.getElementById('reviewScore').value;
		    if (score === '0') {
		        alert('점수를 선택해주세요.');
		        return false;
		    }
		
		    const reviewText = document.querySelector('#reviewForm textarea[name="review_text"]').value;
		    if (reviewText.trim() === '') {
		        alert('리뷰 내용을 입력해주세요.');
		        return false;
		    }
		
		    return true;
		}
		    
		 /* 일반 버튼 클릭용 (수정, 삭제 등)  */   
			function requireLoginThenRedirect(redirectUrl) {
				const isLoggedIn = "${sessionScope.user_seq == null ? 'false' : 'true'}";

					if (isLoggedIn == 'false') {
						sessionStorage.setItem('postLoginRedirect', redirectUrl);
						document.getElementById("loginModal").style.display = "flex";
						return false;
					}

		    	  // 로그인된 경우 리디렉트 바로 수행
		    	  window.location.href = redirectUrl;
		    	  return false;
		    	}


		//모달 관련 함수는 공통
		function closeLoginModal() {
		    document.getElementById("loginModal").style.display = "none";
		}
		
		function goToLogin() {
		    const afterLogin = location.pathname + location.search;
		    window.location.href = "${pageContext.request.contextPath}/login.do?redirect=" + encodeURIComponent(afterLogin);
		}
		//로그인 페이지에 redirect 처리 해주면 됨
		//로그인 성공시 되돌아오기
	</script>

	<script>
	  // 대댓글 토글 스크립트
	  function toggleReply(cid) {
	    const target = document.getElementById('reply-' + cid);
	    if (target) target.style.display = (target.style.display === 'none') ? 'block' : 'none';
	  }
	
	  document.querySelectorAll('.flower-display').forEach(container => {
	    const rating = parseFloat(container.dataset.rating);
	    container.innerHTML = '';
	    for (let i = 1; i <= 5; i++) {
	      const img = document.createElement('img');
	      img.src = rating >= i ? filled : (rating >= i - 0.5 ? half : empty);
	      container.appendChild(img);
	    }
	  });
    
	  
	</script>
	<div>${sessionScope.user_seq}</div>
</body>
</html> 