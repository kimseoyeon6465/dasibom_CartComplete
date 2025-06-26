<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 페이지</title>
<link rel="stylesheet" href="/dasibom/CSS/Payment.css">
</head>
<jsp:include page="/common/header.jsp" />

<body class="payment-page">

	<div class="payment-container">
		<div class="payment-left">

			<!-- 주문자 정보 (카드형) -->
			<div class="box order-user-info">
				<h3>주문자 정보</h3>
				<div class="info-row">
					<strong>이름:</strong> ${loginUser.irum}
				</div>
				<div class="info-row">
					<strong>전화번호:</strong> ${loginUser.tel}
				</div>

				<div class="info-row">
					<strong>보유 포인트:</strong>
					<fmt:formatNumber value="${loginUser.point}" type="number" />
					P
				</div>
			</div>

			<!-- 배송지 정보 -->
			<div class="box shipping-info">
				<h3>배송지 정보</h3>
				<!-- 받는 사람 -->
				<label for="receiverInput"><strong>받는 사람:</strong></label><br>
				<input type="text" id="receiverInput" name="receiverInput"
					value="${loginUser.irum}" /><br>
				<!-- 배송지 주소 -->

				<br> <label for="addressInput"><strong>배송지 주소:</strong></label><br>
				<input type="text" id="addressInput" name="addressInput"
					value="${loginUser.address}">
			</div>

			<!-- 배송 요청사항 -->
			<div class="box shipping-request">
				<h3>배송 요청사항</h3>
				<textarea id="requestInput" placeholder="예: 문 앞에 놓아주세요." rows="3"
					cols="40"></textarea>
			</div>

			<!-- 결제 수단 -->
			<div class="box payment-method">
				<h3>결제수단</h3>
				<label><input type="radio" name="payMethod" value="kakao"
					checked> 카카오페이</label><br /> <label><input type="radio"
					name="payMethod" value="naver"> 네이버페이</label><br /> <label><input
					type="radio" name="payMethod" value="card"> 신용카드</label><br /> <label><input
					type="radio" name="payMethod" value="bank" disabled> 무통장입금
					(준비중)</label><br />
			</div>
		</div>
		<input type="hidden" id="userPoint" value="${loginUser.point}" /> <input
			type="hidden" id="usedPointHidden" value="${usedPoint}" /> <input
			type="hidden" id="finalPriceHidden"
			value="${sumPrice + delivery - usedPoint}" /> <input type="hidden"
			id="userId" value="${loginUser.user_Id}" /> <input type="hidden"
			id="tel" value="${loginUser.tel}" />

		<div class="payment-right">
			<!-- 요약 박스 -->
			<div class="summary-box">
				<p class="price-line">
					<span class="label">상품 정가 금액</span> <span class="value"><fmt:formatNumber
							value="${sumPriceOrigin}" type="number" />원</span>
				</p>
				<p class="price-line">
					<span class="label">상품 할인 금액</span> <span class="value discount">-
						<fmt:formatNumber value="${sumPriceOrigin - sumPrice}"
							type="number" />원
					</span>
				</p>
				<hr class="divider" />
				<p class="price-line">
					<span class="label">할인 적용 소계</span> <span class="value"><fmt:formatNumber
							value="${sumPrice}" type="number" />원</span>
				</p>
				<p class="price-line">
					<span class="label">배송비</span> <span class="value"><fmt:formatNumber
							value="${delivery}" type="number" />원</span>
				</p>
				<hr>
				<p class="price-line">
					<span class="label">포인트 사용</span> <span class="value"
						id="displayUsedPoint">- <fmt:formatNumber
							value="${usedPoint}" type="number" />원
					</span>
				</p>
				<hr>
				<p class="final-line">
					<span class="label">총 결제 금액</span> <span class="value total"
						id="finalPriceText"> <fmt:formatNumber
							value="${sumPrice + delivery - usedPoint}" type="number" />원
					</span>
				</p>
				<p style="font-size: 13px; color: #666;">
					적립 예정 포인트 <span id="expectedPoint"> <fmt:formatNumber
							value="${(sumPrice + delivery - usedPoint) * 0.05 - ((sumPrice + delivery - usedPoint) * 0.05) % 1}"
							type="number" />P
					</span>
				</p>
				<button type="button" onclick="goToKakaoPay()">결제하기</button>
			</div>
		</div>
	</div>

	<hr>

	<!-- 📚 책 항목 -->
	<c:forEach var="item" items="${orderList}">
		<c:if test="${not empty item.title}">
			<div class="cart-item-card" data-type="book" data-isbn="${item.isbn}">
				<img src="${item.imagePath}" alt="책 이미지" class="book-image" />
				<div class="book-info">
					<p>
						<strong>상품명:</strong> ${item.title}
					</p>
					<p>
						<strong>가격:</strong> <span class="original-price"> <fmt:formatNumber
								value="${item.price / 0.9}" type="number" />
						</span> → <span class="discounted-price"> <fmt:formatNumber
								value="${item.price}" type="number" />
						</span> 원
					</p>
					<p>
						<strong>수량:</strong> ${item.count}개
					</p>
					<input type="hidden" class="item-isbn" value="${item.isbn}" /> <input
						type="hidden" class="item-image" value="${item.imagePath}" /> <input
						type="hidden" class="item-title" value="${item.title}" /> <input
						type="hidden" class="item-count" value="${item.count}" /> <input
						type="hidden" class="item-price" value="${item.price}" /> <input
						type="hidden" class="item-original-price"
						value="${item.price / 0.9}" />
				</div>
			</div>
		</c:if>
	</c:forEach>

	<!-- 🎁 굿즈 항목 -->
	<c:forEach var="item" items="${orderList}">
		<c:if test="${not empty item.goodsName}">
			<div class="cart-item-card" data-type="goods"
				data-goodsid="${item.goodsId}">
				<img src="${pageContext.request.contextPath}${item.imagePath}"
					alt="굿즈 이미지" class="book-image" />
				<div class="book-info">
					<p>
						<strong>상품명:</strong> ${item.goodsName}
					</p>
					<p>
						<strong>가격:</strong> <span class="original-price"> <fmt:formatNumber
								value="${item.price / 0.9}" type="number" />
						</span> → <span class="discounted-price"> <fmt:formatNumber
								value="${item.price}" type="number" />
						</span> 원
					</p>
					<p>
						<strong>수량:</strong> ${item.count}개
					</p>
					<input type="hidden" class="item-goods-id" value="${item.goodsId}" /><input
						type="hidden" class="item-image" value="${item.imagePath}" /> <input
						type="hidden" class="item-goods-name" value="${item.goodsName}" />
					<input type="hidden" class="item-count" value="${item.count}" /> <input
						type="hidden" class="item-price" value="${item.price}" /> <input
						type="hidden" class="item-original-price"
						value="${item.price / 0.9}" />

				</div>
			</div>
		</c:if>
	</c:forEach>
	<input type="hidden" id="userPoint" value="${loginUser.point}" />
	<input type="hidden" id="usedPointHidden" value="${usedPoint}" />
	<input type="hidden" id="finalPriceHidden"
		value="${sumPrice + delivery - usedPoint}" />
	<input type="hidden" id="userId" value="${loginUser.user_Id}" />
	<input type="hidden" id="tel" value="${loginUser.tel}" />


	<script>
function goToKakaoPay() {
    applyPoint(); // 포인트 적용

    const orderList = [];
    const isbns = document.querySelectorAll(".item-isbn");
    const goodsIds = document.querySelectorAll(".item-goods-id");
    const titles = document.querySelectorAll(".item-title");
    const goodsNames = document.querySelectorAll(".item-goods-name");
    const counts = document.querySelectorAll(".item-count");
    const prices = document.querySelectorAll(".item-price");
    const originalPrices = document.querySelectorAll(".item-original-price");
    const images = document.querySelectorAll(".item-image");
    console.log("counts", counts.length);
    console.log("prices", prices.length);
    console.log("originalPrices", originalPrices.length);
    console.log("isbns", isbns.length);
    console.log("goodsIds", goodsIds.length);
    console.log("images", images.length);

    for (let i = 0; i < counts.length; i++) {
        orderList.push({
            type: titles[i] ? "book" : "goods", // ✅ 책/굿즈 구분
            isbn: isbns[i]?.value || null,
            goodsId: goodsIds[i]?.value || null,
            title: titles[i]?.value || null,
            goodsName: goodsNames[i]?.value || null,
            count: parseInt(counts[i].value),
            price: parseInt(prices[i].value),
            originalPrice: parseInt(originalPrices[i].value),
            imagePath: images[i].value
        });
    }

    const orderData = {
        user_Id: document.getElementById("userId").value,
        receiver: document.getElementById("receiverInput").value,
        tel: document.getElementById("tel").value,
        address: document.getElementById("addressInput").value,
        request: document.getElementById("requestInput").value,
        sumPrice: parseInt(document.getElementById("finalPriceHidden").value),
        usedPoint: parseInt(document.getElementById("usedPointHidden").value),
        orderList: orderList
    };

    localStorage.setItem("orderData", JSON.stringify(orderData));
    const contextPath = "${pageContext.request.contextPath}";
    window.open(contextPath + "/payment/kakaopay.html", "KakaoPayWindow", "width=500,height=700,resizable=no,scrollbars=yes");
}

function applyPoint() {
    const totalPrice = parseInt("${sumPrice}") + parseInt("${delivery}");
    const userPoint = parseInt(document.getElementById("userPoint").value || "0");
    const usedPoint = parseInt(document.getElementById("usedPointHidden").value || "0");

    if (usedPoint > userPoint) {
        alert("보유 포인트를 초과할 수 없습니다.");
        return;
    }

    if (usedPoint > totalPrice) {
        alert("결제 금액보다 많은 포인트를 사용할 수 없습니다.");
        return;
    }

    const finalPrice = totalPrice - usedPoint;
    const expectedPoint = Math.floor(finalPrice * 0.05);

    document.getElementById("finalPriceText").innerText = finalPrice.toLocaleString() + "원";
    document.getElementById("expectedPoint").innerText = expectedPoint.toLocaleString() + "P";
    document.getElementById("displayUsedPoint").innerText = "- " + usedPoint.toLocaleString() + "원";
    document.getElementById("usedPointHidden").value = usedPoint;
    document.getElementById("finalPriceHidden").value = finalPrice;
}
</script>

	<jsp:include page="/common/footer.jsp" />
</body>
</html>
