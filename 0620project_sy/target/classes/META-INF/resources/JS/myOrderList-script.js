/**
 * 
 */
 
// 도서명으로 검색하기 
function searchByTitle() {
    const title = document.getElementById('searchTitle').value.trim();
    if (!title) {
        alert("검색어를 입력하세요.");
        return;
    }

    // 검색어를 URL 파라미터로 전달
    location.href = contextPath + "/myOrderList.do?title=" + encodeURIComponent(title);

}


// 구매일자 기간(시작일~종료일)으로 검색하기
function searchByDate() {
    const startDate = document.getElementById('startDate').value.trim();
    const endDate = document.getElementById('endDate').value.trim();

	// 시작일, 종료일 유효성 검사
    if (!startDate || !endDate) {
        alert("시작일과 종료일을 모두 입력하세요.");
        return;
    }
    
    if (startDate > endDate) {
        alert("시작일은 종료일보다 앞서야 합니다.");
        return;
    }

    console.log("기간 검색:", startDate, "~", endDate);
    
    location.href = contextPath + "/myOrderList.do?" 
    	+ "startDate=" + encodeURIComponent(startDate)
    	+ "&endDate=" + encodeURIComponent(endDate);
}

// 주문취소 페이지 cancelOrder.jsp 로 구매내역vo 함께 가져가기
function goCancelOrder(orderId) {
	location.href = 'cancelOrder.do?orderId=' + orderId;
}

// 환불 페이지 refundOrder.jsp 로 구매내역vo 함께 가져가기
function goRefundOrder(orderId) {
	location.href = 'refundOrder.do?orderId=' + orderId;
}