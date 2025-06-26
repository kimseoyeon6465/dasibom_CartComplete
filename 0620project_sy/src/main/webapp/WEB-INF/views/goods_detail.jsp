<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GoodsMall</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/goods_detail.css">
</head>
<body>

	<jsp:include page="/common/header.jsp" />
	
	<!-- 로그인 여부 변수 -->
	<script>
	    const isLoggedIn = "${sessionScope.user_seq == null ? 'false' : 'true'}";
	</script>
	
	<!-- 로그인 모달 (공통) -->
		<div id="loginModal" class="login-modal-overlay" style="display: none;">
		    <form action="${pageContext.request.contextPath}/login.do" method="get">
		        <input type="hidden" name="redirect" value="goods_detail.do?goods_id=${goods.goods_Id}">
		        <div class="login-modal">
		            <h2>로그인 후 이용 가능합니다</h2>
		            <p>로그인 페이지로 이동하시겠습니까?</p>
		            <div class="modal-buttons">
		                <button type="button" class="cancel-btn" onclick="closeLoginModal()">취소</button>
		                <button type="submit" class="confirm-btn">확인</button>
		            </div>
		        </div>
		    </form>
		</div>

	<script>
	    function closeLoginModal() {
	        document.getElementById("loginModal").style.display = "none";
	    }
	
	    function goToLogin() {
	        const afterLogin = location.pathname + location.search;
	        window.location.href = "${pageContext.request.contextPath}/login.do?redirect=" + encodeURIComponent(afterLogin);
	    }
	</script>

	<!-- 로그인 사용자 ID 변수 선언 -->
	<c:set var="sessionUserId" value="${user.user_Id}" />
	
<div class="wrapper">
    <div class="wrap">
	
       <!-- 굿즈 상세 -->
       <div class="content_top">
       
		<!-- 사진 좌우로 넘기기  -->
			<div class="goods-slider">
			    <div class="main-image-wrapper">
			        <div class="main-image">
			            <img id="mainImage" src="${pageContext.request.contextPath}${photoList[0].img_path}" alt="${goods.goods_name}">
			        </div>
			        <button class="slider-btn prev-btn" onclick="changeImage(-1)">&#10094;</button>
			        <button class="slider-btn next-btn" onclick="changeImage(1)">&#10095;</button>
			    </div>
			    <div class="thumbnail-list">
			        <c:forEach var="photo" items="${photoList}" varStatus="status">
			            <img class="thumbnail" src="${pageContext.request.contextPath}${photo.img_path}" 
			                 alt="굿즈 이미지" onclick="showImage(${status.index})">
			        </c:forEach>
			    </div>
			</div>

            <div class="ct_right_area">
                <h1>${goods.goods_name}</h1>

                <p>정가 : <fmt:formatNumber value="${goods.price}" pattern="#,### 원" /></p>

                <p>
                    판매가 :
                    <fmt:formatNumber value="${goods.price * 0.9}" pattern="#,### 원" />
                    <span class="discount-highlight">[10% 할인]</span>
                </p>

                <p>
                    적립/혜택 : 
                    <fmt:formatNumber value="${goods.price * 0.05}" pattern="#,### 원" />
                    (구매 시 5% 적립됩니다)
                </p>

                <p>배송료 3000원 (5만원 이상 구매 시 무료배송)</p>

                <!-- 주문수량 -->
                <div class="button_quantity">
                    주문수량 
                    <input id="quantityInput" type="number" value="1" min="1" style="width:50px; text-align:center;">
                    <div class="quantity_controls" style="display:inline-block;">
                        <button type="button" onclick="changeQuantity(1)">▲</button>
                        <button type="button" onclick="changeQuantity(-1)">▼</button>
                    </div>
                </div>

                <!-- 버튼 영역 -->
                <div class="button_set" style="margin-top:20px;">
                    <!-- 장바구니 -->
                    <form id="cartForm" action="wishlist.do" method="post" onsubmit="return handleCartSubmit();" style="display:inline-block; margin-right:10px;">
                        <input type="hidden" name="goods_Id" value="${goods.goods_Id}">
                        <input type="hidden" name="user_Id" value="${user.user_Id}">
                        <input type="hidden" name="list_type" value="cart">
                        <input type="hidden" name="count" id="cartQuantity" value="1">
                        <button type="submit" class="btn_cart">장바구니</button>
                    </form>

                    <!-- 바로구매 -->
                    <form id="paymentForm" action="order.do" method="post" onsubmit="return handlePaymentSubmit();" style="display:inline-block; margin-right:10px;">
                        <input type="hidden" name="goods_Id" value="${goods.goods_Id}">
                        <input type="hidden" name="user_Id" value="${user.user_Id}">
                        <input type="hidden" name="count" id="paymentQuantity" value="1">
                        <button type="submit" class="btn_buy">바로구매</button>
                    </form>

                    <!-- 찜 -->
                    <form id="wishForm" action="wishlist.do" method="post" onsubmit="return handleWishSubmit();" style="display:inline-block;">
                        <input type="hidden" name="goods_Id" value="${goods.goods_Id}">
                        <input type="hidden" name="user_Id" value="${user.user_Id}">
                        <input type="hidden" name="count" value="1">
                        <input type="hidden" name="list_type" value="wishlist">
                        <button type="submit" class="btn_wish">♥ 찜</button>
                    </form>
                </div>
            </div>
        </div> <!-- content_top -->
    </div> <!-- wrap -->
</div> <!-- wrapper -->

		<!-- 상세 설명 이미지 -->
		<div class="content-section">
		    <div class="goods-detail">
			    <h1>상품정보</h1>		
			     <!-- 펼치기 영역 감싸기 -->
       			 <div class="goods-info-content" id="goodsDetail">
					<c:forEach var="photo" items="${photoList}">
				        <img class="goods-detail-img"
				             src="${pageContext.request.contextPath}${photo.img_path}" 
				             alt="${goods.goods_name} 상세이미지">
				    </c:forEach>			
				</div>
		
			 <!-- 펼치기 버튼 -->
	        <div class="goods-info-toggle" onclick="toggleGoodsDetail()">
	            <span id="toggleDetailBtn">상품 정보 펼치기 ▼</span>
	        </div>
	</div>
		
		    <!-- 구분 라인 -->
		    <hr class="section-divider">
		
		    <!-- 교환 환불 정책 -->
		    <%@ include file="/common/exchange_info.jsp" %>
		    <%@ include file="/common/footer.jsp" %>
		</div>

	<!-- JS: 주문수량 변경, 로그인 체크 -->
	<script>
	    function changeQuantity(amount) {
	        const input = document.getElementById("quantityInput");
	        let newValue = parseInt(input.value) + amount;
	        if (newValue < 1) newValue = 1;
	        input.value = newValue;
	        document.getElementById("paymentQuantity").value = newValue;
	        document.getElementById("cartQuantity").value = newValue;
	    }
	
	    document.addEventListener('DOMContentLoaded', function() {
	        const input = document.getElementById("quantityInput");
	        if (input) {
	            input.addEventListener("input", function() {
	                let val = parseInt(this.value);
	                if (isNaN(val) || val < 1) val = 1;
	                this.value = val;
	                document.getElementById("paymentQuantity").value = val;
	                document.getElementById("cartQuantity").value = val;
	            });
	        }
	    });
	
	    function handleCartSubmit() {
	        if (isLoggedIn == 'false') {
	            document.getElementById("loginModal").style.display = "flex";
	            document.querySelector('#loginModal input[name="redirect"]').value = "goods_detail.do?goods_id=${goods.goods_Id}";
	            return false;
	        }
	        return true;
	    }
	
	    function handlePaymentSubmit() {
	        if (isLoggedIn == 'false') {
	            document.getElementById("loginModal").style.display = "flex";
	            document.querySelector('#loginModal input[name="redirect"]').value = "goods_detail.do?goods_id=${goods.goods_Id}";
	            return false;
	        }
	        return true;
	    }
	
	    function handleWishSubmit() {
	        if (isLoggedIn == 'false') {
	            document.getElementById("loginModal").style.display = "flex";
	            document.querySelector('#loginModal input[name="redirect"]').value = "goods_detail.do?goods_id=${goods.goods_Id}";
	            return false;
	        }
	        return true;
	    }
	    
	    /* 썸네일용  */
	    function changeMainImage(src) {
	        document.getElementById("mainImage").src = src;
	    }
		
		/* 사진 좌우로 넘기기 */
	    let currentIndex = 0;
	    const imageList = [
	        <c:forEach var="photo" items="${photoList}" varStatus="status">
	            "${pageContext.request.contextPath}${photo.img_path}"<c:if test="${!status.last}">,</c:if>
	        </c:forEach>
	    ];

	    function changeImage(direction) {
	        currentIndex += direction;
	        if (currentIndex < 0) currentIndex = imageList.length - 1;
	        if (currentIndex >= imageList.length) currentIndex = 0;
	        document.getElementById("mainImage").src = imageList[currentIndex];
	    }

	    function showImage(index) {
	        currentIndex = index;
	        document.getElementById("mainImage").src = imageList[currentIndex];
	    }
	    
	    
	    function toggleGoodsDetail() {
	        const detail = document.getElementById('goodsDetail');
	        const btn = document.getElementById('toggleDetailBtn');

	        detail.classList.toggle('expanded');

	        if (detail.classList.contains('expanded')) {
	            btn.textContent = '상품 정보 접기 ▲';
	        } else {
	            btn.textContent = '상품 정보 펼치기 ▼';
	        }
	    }


	    
	</script>

</body>
</html>
