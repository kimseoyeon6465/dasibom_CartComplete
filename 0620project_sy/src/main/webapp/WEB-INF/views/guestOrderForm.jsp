<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비회원 주문</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/guestOrderForm.css">
</head>
<body>

	<jsp:include page="/common/header.jsp" />
	<div class="order-form">
		<h2>비회원 주문 정보</h2>

		<form id="guestOrderForm" onsubmit="return false;">
			<label for="receiver">받는 사람</label> <input type="text" id="receiver"
				name="receiver" required> <label for="address">주소</label> <input
				type="text" id="address" name="address" required> <label
				for="tel">전화번호</label> <input type="tel" id="tel" name="tel"
				required> <label for="email">이메일</label> <input type="email"
				id="email" name="email" required> <label for="request">요청사항</label>
			<textarea id="request" name="request" rows="3" style="resize: none;"
				placeholder="예: 부재 시 문 앞에 놓아주세요"></textarea>

			<div class="book-preview">
				<h3>📚 주문 상품 미리보기</h3>

				<c:forEach var="book" items="${bookList}">
					<div class="book-row">
						<img src="${book.imagePath}" alt="책 이미지" />
						<div class="book-info">
							<p>
								<strong>${book.title}</strong>
							</p>
							<p>ISBN: ${book.isbn}</p>
							<p>수량: ${book.count}</p>
							<p>가격: ${book.price}원</p>
						</div>

						<!-- 다음 페이지로 넘길 hidden 값 -->
						<input type="hidden" class="item-isbn" value="${book.isbn}" /> <input
							type="hidden" class="item-count" value="${book.count}" /> <input
							type="hidden" class="item-price" value="${book.price}" /> <input
							type="hidden" class="item-title" value="${book.title}" /> <input
							type="hidden" class="item-image" value="${book.imagePath}" />
					</div>
				</c:forEach>
			</div>

			<button type="button" onclick="goToGuestPay()">주문하기</button>
		</form>

	</div>

	<script>
	function goToGuestPay() {
		const guestInfo = {
			receiver: document.getElementById("receiver").value,
			address: document.getElementById("address").value,
			request: document.getElementById("request").value,
			tel: document.getElementById("tel").value,
			email: document.getElementById("email").value
		};

		if (!guestInfo.receiver||!guestInfo.address || !guestInfo.tel || !guestInfo.email) {
			alert("받는 사람, 주소, 전화번호, 이메일은 필수 항목입니다.");
			return;
		}

		const orderList = [];
		const isbns = document.querySelectorAll(".item-isbn");
		const counts = document.querySelectorAll(".item-count");
		const prices = document.querySelectorAll(".item-price");
		const titles = document.querySelectorAll(".item-title");
		const images = document.querySelectorAll(".item-image");

		let sumPrice = 0;

		for (let i = 0; i < isbns.length; i++) {
			const count = parseInt(counts[i].value);
			const price = parseInt(prices[i].value);
			sumPrice += count * price;

			orderList.push({
				isbn: isbns[i].value,
				count: count,
				price: price,
				title: titles[i].value,
				imagePath: images[i].value
			});
		}

		// ✅ sum_price까지 포함해서 저장
		const guestOrderData = {
			...guestInfo,
			sum_Price: sumPrice
		};

		localStorage.setItem("guestOrderInfo", JSON.stringify(guestOrderData));
		localStorage.setItem("orderData", JSON.stringify(orderList));

		const contextPath = "${pageContext.request.contextPath}";
		location.href = contextPath + "/payment/kakaopayForGuest.html";
	}
</script>


	<jsp:include page="/common/footer.jsp" />
</body>
</html>
