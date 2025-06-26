<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>비회원 결제 확인</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 30px;
}

h2 {
	margin-bottom: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
}

.summary-box {
	border: 1px solid #ccc;
	padding: 20px;
	width: 300px;
	font-size: 14px;
	line-height: 1.8;
	margin-top: 30px;
}

.summary-box p {
	margin: 5px 0;
}

.summary-box span {
	float: right;
}
</style>
</head>
<body>

	<!-- ✅ 공통 헤더 포함 -->
	<jsp:include page="/common/header.jsp" />

	<h2>🧾 비회원 결제 확인</h2>

	<div id="guestInfoArea">
		<p>
			<strong>주문자:</strong> <span id="receiver"></span>
		</p>
		<p>
			<strong>연락처:</strong> <span id="tel"></span>
		</p>
		<p>
			<strong>이메일:</strong> <span id="email"></span>
		</p>
		<p>
			<strong>주소:</strong> <span id="address"></span>
		</p>
		<p>
			<strong>요청사항:</strong> <span id="request"></span>
		</p>
	</div>

	<h3>🛒 주문 상품 목록</h3>
	<table>
		<thead>
			<tr>
				<th>상품</th>
				<th>제목</th>
				<th>수량</th>
				<th>가격</th>
				<th>소계</th>
			</tr>
		</thead>
		<tbody id="orderTableBody"></tbody>
	</table>

	<div class="summary-box" id="summaryBox"></div>

	<!-- ✅ 공통 푸터 포함 -->
	<jsp:include page="/common/footer.jsp" />

	<script>
    const guestInfo = JSON.parse(localStorage.getItem("guestOrderInfo"));
    const orderList = JSON.parse(localStorage.getItem("orderData") || "[]");

    // 주문자 정보 출력
    document.getElementById("receiver").innerText = guestInfo.receiver || "비회원";
    document.getElementById("tel").innerText = guestInfo.tel || "-";
    document.getElementById("email").innerText = guestInfo.email || "-";
    document.getElementById("address").innerText = guestInfo.address || "-";
    document.getElementById("request").innerText = guestInfo.request || "-";

    // 주문 상품 출력
    const tbody = document.getElementById("orderTableBody");
    let total = 0;

    orderList.forEach(item => {
      const row = document.createElement("tr");
      const subtotal = item.count * item.price;
      total += subtotal;

      row.innerHTML = `
        <td><img src="${item.imagePath}" width="100" /></td>
        <td>${item.title}</td>
        <td>${item.count}</td>
        <td>${item.price.toLocaleString()}원</td>
        <td>${subtotal.toLocaleString()}원</td>
      `;
      tbody.appendChild(row);
    });

    const delivery = total >= 50000 ? 0 : 3000;
    const finalPrice = total + delivery;

    document.getElementById("summaryBox").innerHTML = `
      <p><strong>상품 금액</strong> <span>${total.toLocaleString()}원</span></p>
      <p><strong>배송비</strong> <span>${delivery.toLocaleString()}원</span></p>
      <hr>
      <p style="font-weight: bold; font-size: 16px;">
        최종 결제 금액 <span>${finalPrice.toLocaleString()}원</span>
      </p>
    `;
  </script>

</body>
</html>
