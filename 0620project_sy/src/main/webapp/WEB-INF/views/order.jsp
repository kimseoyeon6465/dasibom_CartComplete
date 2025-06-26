<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 확인 및 결제</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/order.css">
</head>
<body>

	<jsp:include page="/common/header.jsp" />

	<h1>주문 확인</h1>

	<form id="orderForm" onsubmit="return false;">
		<input type="hidden" id="userId" name="userId"
			value="${loginUser.userId}" />

		<table>
			<thead>
				<tr>
					<th>상품</th>
					<th>수량</th>
					<th>가격</th>
					<th>합계</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${orderList}">
					<tr data-isbn="${item.isbn}" data-count="${item.count}"
						data-price="${item.price}">
						<td>${item.title}</td>
						<td>${item.count}</td>
						<td><span class="original-price"> <fmt:formatNumber
									value="${item.price / 0.9}" type="number" />
						</span><br> <span class="discounted-price"> <fmt:formatNumber
									value="${item.price}" type="number" />
						</span> 원</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 총 상품금액 출력 -->
		<p>
			총 상품금액: <span id="totalPrice">${sumPrice}</span>원
		</p>

		<!-- 포인트 입력 및 사용 -->
		<p>
			보유 포인트: <span id="userPoint">${loginUser.point}</span>P
		</p>
		<p>
			사용할 포인트: <input type="number" id="usedPointInput" placeholder="0"
				min="0" />
			<button type="button" onclick="applyPoint()">사용</button>
		</p>

		<!-- 최종 결제 금액 -->
		<p>
			최종 결제 금액: <span id="finalPrice">${sumPrice}</span>원
		</p>

		<!-- hidden 필드 -->
		<input type="hidden" id="usedPointHidden" name="usedPoint" value="0" />
		<input type="hidden" id="finalPriceHidden" name="finalPrice"
			value="${sumPrice}" />

		<h2>배송 정보</h2>
		<label>받는 사람: <input type="text" id="receiver" name="receiver"
			required /></label><br> <label>전화번호: <input type="text"
			id="tel" name="tel" required /></label><br> <label>주소: <input
			type="text" id="address" name="address" required /></label><br>

		<h2>결제 수단</h2>
		<p>
			<label><input type="radio" name="payment_method"
				value="kakaopay" checked /> 카카오페이</label>
		</p>
		<p>
			<label><input type="radio" name="payment_method" value="card"
				disabled /> 카드결제 (준비 중)</label>
		</p>
		<p>
			<label><input type="radio" name="payment_method" value="bank"
				disabled /> 무통장 입금 (준비 중)</label>
		</p>

		<button type="button" class="btn-order" onclick="submitOrder()">결제하기</button>
	</form>

	<jsp:include page="/common/footer.jsp" />

	<script>
	function applyPoint() {
		const totalPrice = parseInt(document.getElementById("totalPrice").innerText.replace(/,/g, ""));
		const userPoint = parseInt(document.getElementById("userPoint").innerText.replace(/,/g, ""));
		const usedPoint = parseInt(document.getElementById("usedPointInput").value) || 0;

		if (usedPoint > userPoint) {
			alert("보유 포인트를 초과할 수 없습니다.");
			return;
		}
		if (usedPoint > totalPrice) {
			alert("상품 금액을 초과하는 포인트는 사용할 수 없습니다.");
			return;
		}

		const finalPrice = totalPrice - usedPoint;
		document.getElementById("finalPrice").innerText = finalPrice.toLocaleString();
		document.getElementById("usedPointHidden").value = usedPoint;
		document.getElementById("finalPriceHidden").value = finalPrice;
	}

	function submitOrder() {
		const userId = document.getElementById("userId").value;
		const receiver = document.getElementById("receiver").value;
		const tel = document.getElementById("tel").value;
		const address = document.getElementById("address").value;
		const usedPoint = parseInt(document.getElementById("usedPointHidden").value);
		const finalPrice = parseInt(document.getElementById("finalPriceHidden").value);

		const orderList = [];
		document.querySelectorAll("tbody tr").forEach(row => {
			orderList.push({
				isbn: row.dataset.isbn,
				count: parseInt(row.dataset.count),
				price: parseInt(row.dataset.price)
			});
		});

		const payload = {
			userId,
			receiver,
			tel,
			address,
			request: "",
			usedPoint,
			sumPrice: finalPrice,
			orderList
		};

		fetch("orderInsert.do", {
			method: "POST",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(payload)
		})
		.then(res => res.json())
		.then(data => {
			if (data.result === 'success') {
				window.location.href = "paymentComplete.do";
			} else {
				alert("주문 처리 중 오류가 발생했습니다.");
			}
		})
		.catch(err => {
			console.error("전송 실패", err);
			alert("서버 오류가 발생했습니다.");
		});
	}
	</script>
</body>
</html>
