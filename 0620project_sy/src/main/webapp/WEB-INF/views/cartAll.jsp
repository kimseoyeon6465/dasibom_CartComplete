<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 목록</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="/dasibom/CSS/cartAll.css">
</head>
<jsp:include page="/common/header.jsp" />
<body class="cart-page">

	<h1>나의 장바구니</h1>
	<hr>
	<c:if test="${empty sessionScope.userId}">
		<script>
			alert("로그인이 필요합니다.");
			location.href = "loginPage.jsp";
		</script>
	</c:if>

	<!-- ✅ container -->
	<div class="cart-container">

		<!-- 왼쪽: 상품 영역 -->
		<div class="cart-items">
			<form id="cartForm" action="deleteCart.do" method="post">
				<input type="hidden" name="userId" value="${sessionScope.userId}" />

				<div style="text-align: left;">
					<label><input type="checkbox" id="selectAll" /> 전체선택</label>
				</div>

				<!-- 책 항목 -->
				<c:forEach var="imsi" items="${alist}">
					<div class="cart-item-card" data-type="book"
						data-isbn="${imsi.isbn}">
						<input type="checkbox" class="individual_cart_checkbox"
							value="${imsi.isbn}" data-image="${imsi.imagePath}"
							data-title="${imsi.title}" data-count="${imsi.count}"
							data-price="${imsi.price}" /> <img src="${imsi.imagePath}"
							alt="책 이미지" class="book-image" />
						<div class="book-info">
							<p>
								<strong>상품명:</strong> ${imsi.title}
							</p>
							<p>
								<strong>가격:</strong> <span class="original-price"><fmt:formatNumber
										value="${imsi.price}" type="number" /></span> → <span
									class="discounted-price"><fmt:formatNumber
										value="${imsi.price * 0.9}" type="number" /></span> 원
							</p>
							<p>
								<strong>수량:</strong>
								<button type="button" class="decrease-btn">-</button>
								<span class="book-count">${imsi.count}</span>
								<button type="button" class="increase-btn">+</button>
							</p>
						</div>
						<input type="hidden" class="individual_bookPrice_input"
							value="${imsi.price * 0.9}"> <input type="hidden"
							class="individual_bookCount_input" value="${imsi.count}">
						<input type="hidden" class="individual_totalPrice_input"
							value="${(imsi.price * 0.9) * imsi.count}">
					</div>
				</c:forEach>

				<!-- 굿즈 항목 -->
				<c:forEach var="ug" items="${userGoodsList}">
					<div class="cart-item-card" data-type="goods"
						data-goodsid="${ug.goodsId}">
						<input type="checkbox" class="individual_cart_checkbox"
							value="${ug.goodsId}" data-image="${ug.imagePath}"
							data-title="${ug.goodsName}" data-count="${ug.count}"
							data-price="${ug.price}" /> <img
							src="${ug.imagePath}"
							alt="굿즈 이미지" class="book-image" />

						<div class="book-info">
							<p>
								<strong>상품명:</strong> ${ug.goodsName}
							</p>
							<p>
								<strong>가격:</strong> <span class="original-price"><fmt:formatNumber
										value="${ug.price}" type="number" /></span> → <span
									class="discounted-price"><fmt:formatNumber
										value="${ug.price * 0.9}" type="number" /></span> 원
							</p>
							<p>
								<strong>수량:</strong>
								<button type="button" class="decrease-btn">-</button>
								<span class="book-count">${ug.count}</span>
								<button type="button" class="increase-btn">+</button>
							</p>
						</div>
						<input type="hidden" class="individual_bookPrice_input"
							value="${ug.price * 0.9}"> <input type="hidden"
							class="individual_bookCount_input" value="${ug.count}"> <input
							type="hidden" class="individual_totalPrice_input"
							value="${(ug.price * 0.9) * ug.count}">
					</div>
				</c:forEach>
			</form>
		</div>

		<!-- ✅ 오른쪽 요약 박스 -->
		<div class="summary-box">
			<p class="price-line">
				<span class="label">상품 정가 금액</span> <span
					class="value totalOriginPrice_span">0원</span>
			</p>
			<p class="price-line">
				<span class="label">상품 할인 금액</span> <span class="value discount">-
					<span class="totalDiscountPrice_span">0원</span>
				</span>
			</p>
			<hr class="divider" />
			<p class="price-line">
				<span class="label">할인 적용 소계</span> <span
					class="value totalPrice_span">0원</span>
			</p>
			<p class="price-line">
				<span class="label">배송비</span> <span class="value delivery_price">0원</span>
			</p>
			<hr class="divider" />
			<p class="price-line">
				<span class="label">보유 포인트</span> <span class="value"><fmt:formatNumber
						value="${loginUser.point}" type="number" /> P</span>
			</p>
			<p class="price-line">
				<span class="label">포인트 사용</span> <span class="value"
					id="displayUsedPoint">- 0원</span>
			</p>
			<input type="number" id="usedPointInput" placeholder="0" min="0"
				style="width: 100%; margin-top: 5px;" />
			<button type="button" onclick="applyPoint()">포인트 적용</button>
			<input type="hidden" id="usedPointHidden" name="usedPoint" value="0" />
			<hr class="divider" />
			<p class="final-line">
				<span class="label">총 결제 금액</span> <span
					class="value finalTotalPrice_span" id="finalPriceText">0원</span>
			</p>
			<button type="button" onclick="deleteSelected()">선택한 항목 삭제</button>
			<button type="button" onclick="moveToWishlist()">보관함으로 이동</button>
			<button type="button" id="orderSelectedBtn">선택상품 결제하기</button>
		</div>

	</div>

	<jsp:include page="/common/footer.jsp" />

	<script>
		// 전체 선택/해제
		$("#selectAll").on("click", function() {
			$(".individual_cart_checkbox").prop("checked", this.checked);
			setTotalInfo();
		});

		// 개별 선택 시 전체 선택 체크 여부 확인
		$(document)
				.on(
						"click",
						".individual_cart_checkbox",
						function() {
							const allChecked = $(".individual_cart_checkbox").length === $(".individual_cart_checkbox:checked").length;
							$("#selectAll").prop("checked", allChecked);
							setTotalInfo();
						});

		// 총 금액, 할인, 배송비 계산
		function setTotalInfo() {
			let totalPrice = 0;
			let totalCount = 0;
			let totalOriginPrice = 0;

			$(".individual_cart_checkbox:checked").each(
					function() {
						const row = $(this).closest(".cart-item-card");
						const count = parseInt(row.find(
								".individual_bookCount_input").val());
						const discountedPrice = parseFloat(row.find(
								".individual_bookPrice_input").val());
						const originPrice = Math.round(discountedPrice / 0.9);

						totalPrice += discountedPrice * count;
						totalOriginPrice += originPrice * count;
						totalCount += count;
					});

			const discount = totalOriginPrice - totalPrice;
			const deliveryPrice = (totalPrice >= 50000 || totalPrice === 0) ? 0
					: 3000;
			const finalPrice = totalPrice + deliveryPrice;

			$(".totalOriginPrice_span").text(
					totalOriginPrice.toLocaleString() + "원");
			$(".totalDiscountPrice_span").text(discount.toLocaleString() + "원");
			$(".totalPrice_span").text(totalPrice.toLocaleString() + "원");
			$(".delivery_price").text(deliveryPrice.toLocaleString() + "원");
			$(".finalTotalPrice_span").text(finalPrice.toLocaleString() + "원");
			$("#finalPriceText").text(finalPrice.toLocaleString() + "원");
			$(".totalCount_span").text(totalCount + "개");
		}

		// 수량 증가
		$(document).on("click", ".increase-btn", function() {
			const $card = $(this).closest(".cart-item-card");
			let count = parseInt($card.find(".book-count").text());
			count++;
			$card.find(".book-count").text(count);
			$card.find(".individual_bookCount_input").val(count);
			setTotalInfo();
		});

		// 수량 감소
		$(document).on("click", ".decrease-btn", function() {
			const $card = $(this).closest(".cart-item-card");
			let count = parseInt($card.find(".book-count").text());
			if (count > 1) {
				count--;
				$card.find(".book-count").text(count);
				$card.find(".individual_bookCount_input").val(count);
				setTotalInfo();
			}
		});

		// 포인트 적용
		function applyPoint() {
			const userPoint = parseInt("${loginUser.point}");
			const usedPoint = parseInt($("#usedPointInput").val()) || 0;
			const rawTotal = $(".totalPrice_span").text()
					.replace(/[^0-9]/g, "") * 1;
			const delivery = $(".delivery_price").text().replace(/[^0-9]/g, "") * 1;
			const total = rawTotal + delivery;

			if (usedPoint > userPoint) {
				alert("보유 포인트를 초과할 수 없습니다.");
				return;
			}
			if (usedPoint > total) {
				alert("결제 금액보다 많은 포인트를 사용할 수 없습니다.");
				return;
			}

			const finalPrice = total - usedPoint;

			$("#finalPriceText").text(finalPrice.toLocaleString() + "원");
			$("#displayUsedPoint")
					.text("- " + usedPoint.toLocaleString() + "원");
			$("#usedPointHidden").val(usedPoint);
		}

		// 선택 항목 삭제
		function deleteSelected() {
			const checkedItems = $(".individual_cart_checkbox:checked");

			if (checkedItems.length === 0) {
				alert("삭제할 항목을 선택해주세요.");
				return;
			}

			$("#cartForm").empty();
			$("#cartForm").append($("<input>", {
				type : "hidden",
				name : "userId",
				value : "${sessionScope.userId}"
			}));

			checkedItems.each(function() {
				const row = $(this).closest(".cart-item-card");
				const type = row.data("type");

				if (type === "book") {
					$("#cartForm").append($("<input>", {
						type : "hidden",
						name : "isbnList",
						value : row.data("isbn")
					}));
				} else if (type === "goods") {
					$("#cartForm").append($("<input>", {
						type : "hidden",
						name : "goodsIdList",
						value : row.data("goodsid")
					}));
				}
			});

			$("#cartForm").submit();
		}

		// 보관함 이동
		// 보관함 이동
		function moveToWishlist() {
			const checked = $(".individual_cart_checkbox:checked");
			if (checked.length === 0) {
				alert("이동할 항목을 선택해주세요.");
				return;
			}

			const userId = "${sessionScope.userId}";
			let completed = 0;
			let duplicated = [];

			checked.each(function() {
				const row = $(this).closest(".cart-item-card");
				const type = row.data("type");

				let idValue = (type === "book") ? row.data("isbn") : row
						.data("goodsid");
				let imagePath = $(this).data("image"); // ✅ 여기서 imagePath 가져오기

				$.ajax({
					url : "moveToWishlist.do",
					method : "POST",
					data : {
						userId : userId,
						type : type,
						itemId : idValue,
						imagePath : imagePath
					// ✅ 전송에 추가
					},
					success : function(res) {
						if (res === "duplicated") {
							duplicated.push(idValue);
						}
					},
					error : function() {
						alert("에러 발생. 관리자에게 문의하세요.");
					},
					complete : function() {
						completed++;
						if (completed === checked.length) {
							if (duplicated.length > 0) {
								alert("일부 항목은 이미 보관함에 존재합니다.");
							} else {
								alert("보관함으로 이동 완료!");
							}
						}
					}
				});
			});
		}

		$("#orderSelectedBtn").on("click", function() {
			const checkedItems = $(".individual_cart_checkbox:checked");
			if (checkedItems.length === 0) {
				alert("결제할 상품을 선택해주세요.");
				return;
			}

			const form = $("<form>", {
				method : "POST",
				action : "order.do"
			});

			form.append($("<input>", {
				type : "hidden",
				name : "userId",
				value : "${sessionScope.userId}"
			}));

			// 🔧 수정: usedPoint는 반복문 밖에서 한 번만 append
			form.append($("<input>", {
				type : "hidden",
				name : "usedPoint",
				value : $("#usedPointHidden").val()
			}));

			checkedItems.each(function() {
				const row = $(this).closest(".cart-item-card");
				const type = row.data("type");

				const count = row.find(".individual_bookCount_input").val();
				const price = row.find(".individual_bookPrice_input").val();

				if (type === "book") {
					form.append($("<input>", {
						type : "hidden",
						name : "isbnList",
						value : row.data("isbn")
					}));
					form.append($("<input>", { // 🔧 수정됨
						type : "hidden",
						name : "countList", // ✅ 수정
						value : count
					}));
					form.append($("<input>", { // 🔧 수정됨
						type : "hidden",
						name : "priceList", // ✅ 수정
						value : Math.floor(price)
					}));
				} else if (type === "goods") {
					form.append($("<input>", {
						type : "hidden",
						name : "goodsIdList",
						value : row.data("goodsid")
					}));
					form.append($("<input>", { // 🔧 수정됨
						type : "hidden",
						name : "goodsCountList",
						value : count
					}));
					form.append($("<input>", { // 🔧 수정됨
						type : "hidden",
						name : "goodsPriceList",
						value : Math.floor(price)
					}));
				}
			});

			$("body").append(form);
			form.submit();
		});
	</script>


</body>
</html>
