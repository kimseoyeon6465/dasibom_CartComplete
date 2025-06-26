<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<title>${query}관련도서 검색 결과</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/booklist.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/paging.css">
</head>
<body>

	<jsp:include page="/common/header.jsp" />
	<h2>"${query}" 검색 결과</h2>

	<c:if test="${not empty books}">
		<c:forEach var="book" items="${books}">
			<div class="search-books-container">
				<div class="search-books-content">
	
					<img class="search-books-bookimg" src="${book.imgurl}">

					<form action="insertBook.do" method="post">
						<%-- 책 하나 DB에 인서트--%>

						<input type="hidden" name="isbn" value="${book.isbn}" /> <input
							type="hidden" name="title" value="${book.title}" /> <input
							type="hidden" name="author" value="${book.author}" /> <input
							type="hidden" name="publisher" value="${book.publisher}" /> <input
							type="hidden" name="price" value="${book.price}" /> <input
							type="hidden" name="discount_price"
							value="${book.discount_price}" /> <input type="hidden"
							name="summary" value="${book.summary}" /> <input type="hidden"
							name="imgurl" value="${book.imgurl}" /> <input type="hidden"
							name="sales" value="${book.price / 10}" /> <input type="hidden"
							name="pub_date" value="${book.pub_date}" /> <input type="hidden"
							name="query" value="${query}" /> <input type="hidden"
							name="startnum" value="${currentPage}" /> <input type="submit"
							value="추가">

						<%-- <p>
					${book.isbn}, ${book.title}, ${book.author}, ${book.publisher}, ${book.price}, 
					${book.price * (90/100)}, ${book.summary}, ${book.imgurl}, ${book.price / 10}, ${book.pub_date}
					</p> --%>


						<div class="search-books-booktext">
							<strong>${book.title}</strong><br /> ${book.author} 저자(글) |
							${book.publisher}<br/>
							
							10%
							${book.discount_price}원
							출간일 : <fmt:formatDate value="${book.pub_date}" pattern="MM / dd" /><br/>
							정가 ${book.price}원<br />
							장르 : ${book.genre}
							 9.8 (160)<br /> 
						</div>


					</form>
				</div>

				<div class="search-books-buttons">
					<form action="cart.do" method="post">
						<input type="hidden" name="title" value="${book.title}" />
						<button type="submit">장바구니</button>
					</form>
					<form action="purchase.do" method="post">
						<input type="hidden" name="title" value="${book.title}" />
						<button type="submit">바로구매</button>
					</form>
					<form action="wishlist.do" method="post">
						<input type="hidden" name="title" value="${book.title}" />
						<button type="submit">찜하기</button>
					</form>
				</div>
			</div>
			<div class="search-books-divider"></div>
		</c:forEach>

	</c:if>


	<c:if test="${empty books}">
		<p>검색 결과가 없습니다.</p>
	</c:if>

	<c:if test="${total > 1}">
		<div class="paging-container">
			<c:if test="${currentPage > 1}">
				<a class = "paging-prevnext"href="?query=${fn:escapeXml(query)}&currentPage=${currentPage - 1}">이전</a>
			</c:if>

			<c:forEach begin="${startPage}" end="${endPage}" var="p">
				<c:choose>
					<c:when test="${p == currentPage}">
						<span class="paging-current">${p}</span>
					</c:when>
					<c:otherwise>
						<a class= "paging-button"href="?query=${fn:escapeXml(query)}&currentPage=${p}">${p}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage < total}">
				<a class= "paging-prevnext"href="?query=${fn:escapeXml(query)}&currentPage=${currentPage + 1}">다음</a>
			</c:if>
		</div>
	</c:if>


	<jsp:include page="/common/footer.jsp" />
</body>
</html>