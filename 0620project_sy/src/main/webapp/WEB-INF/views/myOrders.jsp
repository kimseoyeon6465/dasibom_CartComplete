<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>나의 주문 내역</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/myOrders.css">
</head>
<body>
	<jsp:include page="/common/header.jsp" />

	<c:if test="${not empty msg}">
		<script>
			alert("${msg}");
		</script>
	</c:if>

	<div class="container">
		<h2>📦 나의 주문 내역</h2>
		<!-- -------------------------update-start------------------------------ -->
		<c:if test="${empty sessionScope.userId}">
			<script>
				alert("로그인이 필요합니다.");
				location.href = "loginPage.jsp";
			</script>
		</c:if>
		<!-- -------------------------update-end------------------------------ -->

		<c:forEach var="order" items="${orderList}">
			<h3>🧾 주문번호: ${order.orderId}</h3>
			<p>
				주문일자:
				<fmt:formatDate value="${order.orderDate}"
					pattern="yyyy-MM-dd HH:mm" />
			</p>
			<p>
				수령인: ${order.receiver}, 주소: ${order.address}, 총합계:
				<fmt:formatNumber value="${order.sumPrice}" type="number" />
				원
			</p>

			<table>
				<thead>
					<tr>
						<th>상품 이미지</th>
						<th>제목</th>
						<th>가격</th>
						<th>수량</th>
						<th>상태</th>
						<th>처리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${order.detailList}">
						<c:set var="remainCount" value="${item.count - item.refundCount}" />

						<!-- 환불된 수량 표시 -->
						<c:if test="${item.refundCount > 0}">
							<tr>
								<td><img src="${item.imagePath}" width="100" /></td>
								<td>${item.title}</td>
								<td><fmt:formatNumber value="${item.price}"
										pattern="#,###원" /></td>
								<td>${item.refundCount}</td>
								<td>환불완료</td>
								<td></td>
							</tr>
						</c:if>

						<!-- 남은 수량 표시 -->
						<c:if test="${remainCount > 0}">
							<tr>
								<td><img src="${item.imagePath}" width="100" /></td>
								<td>${item.title}</td>
								<td><fmt:formatNumber value="${item.price}"
										pattern="#,###원" /></td>
								<td>${remainCount}</td>
								<td>${item.orderStatus}</td>
								<td><c:choose>
										<c:when test="${item.orderStatus == '배송완료'}">
											<form action="Refund.do" method="post"
												style="margin-bottom: 5px;">
												<input type="hidden" name="orderId" value="${item.orderId}" />
												<input type="hidden" name="isbn" value="${item.isbn}" />
												<button type="submit" class="button-pink">반품신청</button>
											</form>

										</c:when>

										<c:when test="${item.orderStatus == '결제완료'}">
											<form action="cancelPayment.do" method="post"
												onsubmit="return confirm('결제 취소하시겠습니까?')">
												<input type="hidden" name="orderId" value="${item.orderId}" />
												<input type="hidden" name="isbn" value="${item.isbn}" />
												<button type="submit" class="button-pink">결제취소</button>
											</form>
										</c:when>
										<c:when test="${item.orderStatus == '결제취소'}">
											<button class="button-pink disabled">취소완료</button>

										</c:when>
										<c:otherwise>
											<button class="button-pink disabled">반품불가</button>
											<br />
											<button class="button-pink disabled">교환불가</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>

			<hr style="width: 90%;">
		</c:forEach>
	</div>

	<jsp:include page="/common/footer.jsp" />
</body>
</html>
