<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 항목 추가</title>
</head>
<body>
	<h1>장바구니에 항목 추가</h1>
	<form action="insertCart.do" method="post">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userId" required></td>
			</tr>
			<tr>
				<td>ISBN</td>
				<td><input type="text" name="isbn" required></td>
			</tr>
			<tr>
				<td>이미지 경로</td>
				<td><input type="text" name="imagePath"></td>
			</tr>
			<tr>
				<td>수량</td>
				<td><input type="number" name="count" min="1" value="1"
					required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="장바구니에 추가"></td>
			</tr>
		</table>
	</form>

	<br>
	<a href="<c:url value='/cartAll.do'/>">[장바구니 목록 보기]</a>
</body>
</html>
