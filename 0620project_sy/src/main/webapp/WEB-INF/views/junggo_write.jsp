<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>중고 도서 글쓰기</title>

    <!-- 분리한 CSS 파일 링크 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/junggo_write.css">
    
    <!-- 분리한 JS 파일 링크 -->
    <script src="${pageContext.request.contextPath}/resources/js/junggo_write.js" defer></script>
</head>
<body>

<jsp:include page="/common/header.jsp" />

<h2>중고 도서 글쓰기</h2>

<form action="${pageContext.request.contextPath}/junggo_write.do" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="제목을 입력해 주세요" class="input-text" required>
    <input type="number" name="price" placeholder="가격을 입력해 주세요" class="input-number" required min="0">
    <input type="text" name="isbn" placeholder="ISBN 입력 (선택)" class="input-text">
    <select name="saleStatus" required>
        <option value="">판매 상태 선택</option>
        <option value="판매중">판매중</option>
        <option value="판매완료">판매완료</option>
    </select>
    <select name="saleType" required>
        <option value="">판매 유형 선택</option>
        <option value="used">중고</option>
        <option value="free">나눔</option>
    </select>
    
    <input type="hidden" name="userId" value="${sessionScope.loginUserId}">
    <textarea name="content" placeholder="내용을 입력하세요" class="input-text" rows="5" required></textarea>

    <label for="imageUpload" class="btn-upload">사진등록</label>
    <input type="file" id="imageUpload" name="images" accept="image/*" multiple>
    
    <div id="previewContainer" class="preview-container"></div>
    
    <input type="submit" value="등록" class="btn-submit">
    <input type="reset" value="취소" class="btn-reset" onclick="document.getElementById('previewContainer').innerHTML='';">
</form>

<jsp:include page="/common/footer.jsp" />
</body>
</html>
