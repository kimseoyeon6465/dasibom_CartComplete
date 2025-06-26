<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불 신청</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/Refund.css">
<script>
function validateRefundForm() {
    const checkboxes = document.querySelectorAll('input[name="selectedItems"]:checked');
    if (checkboxes.length === 0) {
        alert("환불할 상품을 선택하세요.");
        return false;
    }

    return true;
}
</script>

</head>
<body>

	<jsp:include page="/common/header.jsp" />

	<h2 class="center">📦 환불 신청</h2>

	<form action="processRefund.do" method="post" onsubmit="return validateRefundForm();">
		<input type="hidden" name="orderId" value="${order.orderId}" />

		<table>
			<tr>
				<th>선택</th>
				<th>상품 이미지</th>
				<th>제목</th>
				<th>주문 수량</th>
				<th>환불 수량</th>
			</tr>

			<c:forEach var="item" items="${orderDetailList}">
				<tr>
					<td>
						<input type="checkbox" name="selectedItems" value="${item.isbn}" />
					</td>
					<td><img src="${item.imagePath}" alt="${item.title}" /></td>
					<td>${item.title}</td>
					<td>${item.count}개 (환불 ${item.refundCount}개)</td>
					<td>
						<select name="refundCount_${item.isbn}">
							<c:forEach begin="1" end="${item.count - item.refundCount}" var="i">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:forEach>
		</table>

		<div class="center" style="margin-top: 20px;">
			<button type="submit">환불 요청</button>
		</div>
	</form>

	<jsp:include page="/common/footer.jsp" />

</body>
</html>
