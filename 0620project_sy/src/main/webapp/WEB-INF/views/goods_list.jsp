<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>굿즈 리스트</title>
    
    <style>
    	/* 오늘의 쇼핑 추천 */
    .goods-list-title {
    font-size: 32px;   /* 더 큼직하게 */
    font-weight: 800;  /* 더 두껍게 */
    color: #333;
    padding-bottom: 12px;
    margin: 40px 0 30px 0;
    border-bottom: 3px solid #f5b5c5; /* 분홍 계열 밑줄 느낌 */
    display: inline-block;
    position: relative;
     text-align: center;     /* 가운데 정렬 */
}

/* 가운데 정렬 (display: block 대신 inline-block 사용) */
.goods-list-title {
    font-size: 32px;
    font-weight: 800;
    color: #333;
    padding-bottom: 12px;
    margin: 40px 0 30px 0;
    border-bottom: 3px solid #f5b5c5;
    text-align: center;
}

/* 상품 그리드 → flex 사용 (유연하게 배치됨) */
.goods-grid {
    display: flex;
    flex-wrap: wrap;
    justify-content: center; /* 가운데 정렬 */
    gap: 16px;  /* 상품간 간격 적당히 */
    padding: 0 30px 40px 30px;
}

/* 개별 상품 박스 */
.goods-item {
    border: 1px solid #eee;
    padding: 10px;
    border-radius: 8px;
    text-align: center;
    transition: all 0.2s ease;
    cursor: pointer;
    width: 200px;   /* 고정 너비 → 일정하게 정렬됨 */
}

.goods-item:hover {
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
    transform: translateY(-5px);
}

.goods-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 5px;
}

.goods-name {
    font-size: 16px;
    font-weight: bold;
    margin: 10px 0 5px 0;
    color: #333;
}

.goods-price {
    font-size: 15px;
    color: #000;
}

.goods-discount {
    color: red;
    font-weight: bold;
    margin-right: 5px;
}
    </style>
</head>
<body>

    <jsp:include page="/common/header.jsp" />

    <div class="goods-list-title">오늘의 쇼핑 추천</div>

    <div class="goods-grid">
        <c:forEach var="goods" items="${goodsList}">
            <div class="goods-item" onclick="location.href='goods_detail.do?goods_Id=${goods.goods_Id}'">
                <img src="${pageContext.request.contextPath}${goods.image_path}" alt="굿즈 이미지" class="goods-image">     
                <div class="goods-name">${goods.goods_name}</div>
	                <div class="goods-price">
				    <!-- 할인율 10% 고정 -->
				    <span class="goods-discount">10%</span>
				    
				    <!-- 할인된 가격 표시: price * 0.9 -->
				    <fmt:formatNumber value="${goods.price * 0.9}" pattern="#,### 원" />
				</div>

            </div>
        </c:forEach>
    </div>

    <%@ include file="/common/footer.jsp" %>

</body>
</html>
