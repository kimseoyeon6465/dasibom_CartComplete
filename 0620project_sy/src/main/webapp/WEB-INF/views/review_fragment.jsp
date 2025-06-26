<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 삭제용 -->
<script>
    const ctx = "${pageContext.request.contextPath}";
    console.log("ctx:", ctx);  // 확인용
</script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bookview.css">

	<!-- 리뷰 작성 폼 -->
	<div class="review-write-box">
	    <h3>리뷰 작성</h3>
	    <form id="reviewForm" action="${pageContext.request.contextPath}/review.do?action=insert" method="post" onsubmit="return handleReviewSubmit();">
	        <input type="hidden" name="isbn" value="${book.isbn}">
	        <input type="hidden" name="page" value="BookView">
	        <div class="flower-rating-input" data-rate="0">
	            <c:forEach begin="1" end="5" var="i">
	                <img src="${pageContext.request.contextPath}/IMG/noflower.png" data-index="${i}" data-state="0" alt="${i}점">
	            </c:forEach>
	        </div>
	        <input type="hidden" id="reviewScore" name="review_score" value="0">
	        
	        <div class="review-textarea-wrapper">
	            <textarea name="review_text" placeholder="리뷰를 작성해주세요. 주제와 무관한 댓글, 악플 등의 글은 삭제될 수 있습니다"></textarea>
	            <button type="submit" class="cherry-blossom-button">리뷰 등록</button>
	        </div>
	    </form>
	</div>

	<script>
	document.addEventListener('DOMContentLoaded', function() {
	    const filledFlowerPath = "${pageContext.request.contextPath}/IMG/flower.png";
	    const halfFlowerPath = "${pageContext.request.contextPath}/IMG/flower_half.png";
	    const emptyFlowerPath = "${pageContext.request.contextPath}/IMG/noflower.png";
	
	    const flowerContainer = document.querySelector('.flower-rating-input');
	    const flowerImages = flowerContainer.querySelectorAll('img');
	    const scoreInput = document.getElementById('reviewScore');
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
	});
	</script>


	<!-- 리뷰 목록 -->
<div id="review-section">
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
	            <span class="review-date-display">
	                <fmt:formatDate value="${review.review_date}" pattern="yy/MM/dd"/>
	            </span>
	        </p>
	
	        <c:if test="${not empty review.image_path}">
	            <div class="review-image">
	               <img src="${pageContext.request.contextPath}${review.image_path}" alt="리뷰 이미지" width="150"/>
	 			</div>
	        </c:if>
	
	        <div class="review-actions">
	            <form action="${pageContext.request.contextPath}/review.do?action=like" method="post">
	                <input type="hidden" name="cid" value="${review.cid}">
	                <input type="hidden" name="isbn" value="${book.isbn}">
	                <button type="submit" class="like-button">👍 좋아요 ${review.likes}</button>
	            </form>
	
	            <form action="${pageContext.request.contextPath}/review.do?action=update" method="post">
	                <input type="hidden" name="cid" value="${review.cid}">
	                <button type="button" onclick="showEditForm(${review.cid})">수정</button>
	            </form>
	            
				<form action="${pageContext.request.contextPath}/review.do?action=delete" method="post">
				    <input type="hidden" name="cid" value="${review.cid}">
				    <input type="hidden" name="isbn" value="${book.isbn}">
				    <button type="submit">삭제</button>
				</form>

	            <button type="button" onclick="toggleReplies(${review.cid})">💬 댓글</button>
	        </div>
	
	        <!-- 대댓글 영역 -->
	        <div class="reply-section" id="reply-${review.cid}" style="display: none; margin-left: 30px; margin-top: 10px;">
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
	                            <span class="reply-date-display">
	                                <fmt:formatDate value="${reply.review_date}" pattern="yy/MM/dd"/>
	                            </span>
	                        </p>
	                    </div>
	                </c:forEach>
	            </c:if>
	        </div>
	    </div>
	</c:forEach>
	</div>
	
	
<script>


// 리뷰 영역만 다시 가져오기
function reloadReviews(isbn) {
    fetch(`${ctx}/review.do?isbn=${isbn}`)
    .then(response => response.text())
    .then(html => {
        document.getElementById('review-section').innerHTML = html;
        renderFlowerRating();  // 벚꽃 다시 렌더링
    });
}


// 벚꽃 표시 다시 그리는 함수
function renderFlowerRating() {
    const filled = `${ctx}/IMG/flower.png`;
    const half = `${ctx}/IMG/flower_half.png`;
    const empty = `${ctx}/IMG/noflower.png`;

    document.querySelectorAll('.flower-display').forEach(container => {
        const rating = parseFloat(container.dataset.rating);
        container.innerHTML = '';
        for (let i = 1; i <= 5; i++) {
            const img = document.createElement('img');
            img.src = rating >= i ? filled : (rating >= i - 0.5 ? half : empty);
            container.appendChild(img);
        }
    });
}
</script>
