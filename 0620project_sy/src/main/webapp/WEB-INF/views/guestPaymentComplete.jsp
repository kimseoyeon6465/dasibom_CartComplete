<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비회원 주문 완료</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/guestOrderComplete.css">
</head>
<body>

	<jsp:include page="/common/header.jsp" />

	<h2>🧾 비회원 주문 완료</h2>
	<p>주문이 정상적으로 처리되었습니다. 감사합니다.</p>
	<br>

	<!-- 🔹 주문 정보 -->
	<h3>📌 주문 정보</h3>
	<p>
		<strong>주문 번호:</strong> ${guestOrder.orderId}
	</p>
	<p>
		<strong>받는 사람:</strong> ${guestOrder.receiver}
	</p>
	<p>
		<strong>주소:</strong> ${guestOrder.address}
	</p>
	<p>
		<strong>이메일:</strong> ${guestOrder.email}
	</p>
	<hr>

	<!-- 🔹 주문 상세 -->
	<h3>📚 주문 상세</h3>
	<table border="1" cellpadding="10" cellspacing="0">
		<thead>
			<tr>
				<th>책 사진</th>
				<th>도서명</th>
				<th>수량</th>
				<th>가격</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${orderList}">
				<tr>
					<td><img src="${item.imagePath}" width="80" /></td>
					<td>${item.title}</td>
					<td>${item.count}</td>
					<td><fmt:formatNumber value="${item.price}" type="number" />
						원</td>
					<td>${item.orderStatus}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>

	<!-- 🔹 총 합계 -->
	<p>
		<strong>총 합계:</strong>
		<fmt:formatNumber value="${guestOrder.sumPrice}" type="number" />
		원
	</p>

	<jsp:include page="/common/footer.jsp" />
</body>
</html>
