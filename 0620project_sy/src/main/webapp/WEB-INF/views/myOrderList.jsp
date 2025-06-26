<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Font  'Paperlogy' -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/fonts.css" />

<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/myOrderList-style.css">

<!-- jQuery : <body> 하단에 선언 -->
<!-- JS : <body>하단에 선언 -->

</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/common/header.jsp" />

	<div>
		<!-- 사이드네비바 -->
		<div class="mypage-side-nav">
			<jsp:include page="/common/mypageSideNav.jsp" />
		</div>

		<!-- 메인 -->
		<div class="main-container-mOL">

			<!-- 검색/필터 영역 -->
			<div class="order-search-bar">
				<!-- 도서명 검색 -->
				<div class="search-group">
					<input type="text" id="searchTitle" name="title" value="${searchTitle}" placeholder="도서명 검색" />
					<button type="button" onclick="searchByTitle()">🔍</button>
				</div>

				<!-- 구매일자 기간 검색 -->
				<div class="search-group">
					<label for="startDate">시작일:</label> 
						<input type="date" id="startDate"
								name="startDate" value="${startDate}" /> 
					<label for="endDate">종료일:</label> 
						<input type="date" id="endDate"
								name="endDate" value="${endDate}" />
					<button type="button" onclick="searchByDate()">기간 검색</button>
				</div>
			</div>
			<!-- order-search-bar 끝 -->

			<!-- 구매 내역 테이블 -->
			<div class="table-mOL-container">
				<table class="table-mOL" id="userOrderListTable">
					<thead>
						<td></td>
						<!-- 도서이미지 : image_path - (book_table) -->
						<td>상품명</td>
						<!-- 도서명 : title - (book_table) -->
						<td>가격</td>
						<!-- 가격 : sum_price - (user_order_table) -->
						<td>수량</td>
						<!-- 수량 : count - (order_detail_table) -->
						<td>구매일자</td>
						<!-- 구매일자 : order_date - (user_order_table) -->
						<td>상태</td>
						<!-- 상태 : status - (order_status)(: "결제완료" "결제취소" "배송중" "배송완료" "환불완료") -->
						<td></td>
						<!-- [주문취소] [환불] 버튼 -->
					</thead>

					<tbody>
						<c:forEach var="order" items="${orderList}">
							<tr>
								<!-- 도서 이미지 -->
								<td><img src="${order.imagePath}" alt="도서 이미지" width="60"
									height="80" /></td>

								<!-- 도서명 -->
								<td>${order.title}</td>

								<!-- (총) 가격 -->
								<td><fmt:formatNumber value="${order.sumPrice}"
										type="number" pattern="#,###" /> 원</td>

								<!-- 수량 -->
								<td>${order.count}</td>

								<!-- 구매일자 -->
								<td><fmt:formatDate value="${order.orderDate}"
										pattern="yyyy-MM-dd" /></td>

								<!-- 주문 상태 -->
								<td>${order.orderStatus}</td>

								<!-- 취소 / 환불 버튼 -->
								<td><c:choose>
										<c:when test="${order.orderStatus == '결제완료'}">
											<button onclick="goCancelOrder('${order.orderId}')">주문취소</button>
										</c:when>
										<c:when test="${order.orderStatus == '배송완료'}">
											<button onclick="goRefundOrder('${order.orderId}')">환불</button>
										</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- table-mOL 끝 -->
				
				<!-- 페이징 네비게이션 -->
				<div class="pagination">
					<c:if test="${totalPages >= 1}">
						<c:forEach var="i" begin="1" end="${totalPages}">
							<c:choose>
								<c:when test="${i == currentPage}">
									<span class="current-page">${i}</span>
								</c:when>
								<c:otherwise>
									<a href="myOrderList.do?page=${i}">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
				</div>
				<!-- pagination 끝 -->
			</div>
			<!-- table-mOL-container 끝 -->
			<!-- 디버깅 -->
			<p>디버깅정보 : <br> 현재 페이지: ${currentPage} / 총 페이지: ${totalPages}</p>
		</div>
		<!-- main-container-mOL 끝 -->

	</div>


	<!-- 푸터 -->
	<jsp:include page="/common/footer.jsp" />

	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/jquery-3.7.1.js"></script>

	<script>
	    const contextPath = "${pageContext.request.contextPath}";
	</script>
	<!-- JS -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/JS/myOrderList-script.js"></script>



</body>
</html>