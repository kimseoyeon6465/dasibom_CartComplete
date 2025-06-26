<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%
// 사이트 이름 세션에서 불러오기
String siteName = (String) session.getAttribute("siteName");
if (siteName == null || siteName.trim().isEmpty()) {
	siteName = "다시봄";
}
//회사 위치 좌표 설정 (예시: 강남역 근처)
String latitude = "37.571019";
String longitude = "126.992532";
String zoom = "3";
// 로그인 사용자 이름 불러오기
String userName = (String) session.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회사소개 - <%=siteName%></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="<%=siteName%> 회사소개">
<link rel="icon" href="./resources/images/favicon2.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./resources/style/about.css" />
</head>
<body>
	<jsp:include page="/common/header.jsp" />
	<div class="about-container">
		<div class="about-logo">
			<img src="${pageContext.request.contextPath}/IMG/Logo.png" alt="<%=siteName%> 로고">
		</div>

		<%
		if (userName != null) {
		%>
		<div class="about-welcome"><%=userName%>님, 반갑습니다!
		</div>
		<%
		}
		%>

		<h1 class="about-title">회사소개</h1>

		<p class="about-paragraph">
			<strong><%=siteName%>에 오신 것을 환영합니다.</strong>
		</p>

		<p class="about-paragraph">
			<%=siteName%>은(는) 단순히 책을 판매하는 온라인 서점이 아니라, 독자와 책을 잇는 지적이고 따뜻한 연결 고리를 만들어가고자 노력하는 지식 문화 플랫폼입니다.
		</p>

		<p class="about-paragraph">
			우리는 믿습니다. 한 권의 책이 삶을 바꾸고, 문장이 세상을 이끄는 힘이 된다는 것을.
			<%=siteName%>은 그런 변화의 시작이 되고자 지난 수년간 꾸준히 성장을 이어왔습니다.
		</p>

		<p class="about-paragraph">베스트셀러는 물론, 출판 시장에서 주목받지 못한 숨겨진 명작, 고전, 전문 서적, 독립출판물에 이르기까지, 다양한 분야의 도서를 누구보다 빠르고 정확하게 제공해드립니다. 또한 독자 한 사람 한 사람의 취향과 필요를 고려하여 맞춤형 추천 서비스와 큐레이션도 함께 운영하고 있습니다.</p>

		<p class="about-paragraph">
			고객이 언제 어디서든 책을 만나볼 수 있도록, <strong>24시간 주문</strong>, <strong>빠른 배송 시스템</strong>, <strong>친절한 고객 지원</strong> 체계를 갖추었으며, 독자분들의 피드백은 항상 겸허히 받아들이고 서비스에 반영하고 있습니다.
		</p>

		<p class="about-paragraph">
			<strong>우리가 추구하는 가치는 단순한 유통이 아니라, 독서 문화의 저변 확대</strong>입니다. 이를 위해 독서 캠페인, 지역 사회와의 협업, 저자와의 만남 등 다양한 문화 프로그램도 함께 진행하고 있습니다.
		</p>

		<p class="about-paragraph">
			<%=siteName%>은 앞으로도 변함없이, 책을 사랑하는 모든 이들과 함께 지식과 감성의 가치를 나누는 길을 걸어가겠습니다.
		</p>

		<p class="about-paragraph" style="margin-top: 30px; font-weight: bold; color: #2c3e50;">
			책이 있는 일상, 그 중심에
			<%=siteName%>이 있습니다.
		</p>

		<div class="about-timeline">
			<h2 class="about-timeline-title">회사 연혁</h2>
			<ul class="about-timeline-list">
				<li class="about-timeline-item"><span>2021</span> - 온라인 도서 판매 플랫폼 구축</li>
				<li class="about-timeline-item"><span>2022</span> - 100만 권 도서 데이터베이스 확보</li>
				<li class="about-timeline-item"><span>2023</span> - 전국 당일 배송 서비스 개시</li>
				<li class="about-timeline-item"><span>2024</span> - 모바일 앱 런칭 및 회원 10만명 돌파</li>
			</ul>
		</div>

		<div class="map-about-container">
			<h2 class="about-timeline-title">오시는 길</h2>
			<div id="map" style="width: 100%; height: 400px;"></div>
			<!-- 지도 영역 -->

			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c717a221ce217e228cbfa8927b53e01f&autoload=false"></script>
			<script type="text/javascript">
				kakao.maps.load(function() {
					var container = document.getElementById('map');
					var options = {
						center : new kakao.maps.LatLng(
			<%=latitude%>
				,
			<%=longitude%>
				),
						level :
			<%=zoom%>
				};
					var map = new kakao.maps.Map(container, options);
					// 마커 추가 (선택사항)
					var marker = new kakao.maps.Marker({
						position : new kakao.maps.LatLng(
			<%=latitude%>
				,
			<%=longitude%>
				)
					});
					marker.setMap(map);
				});
			</script>
		</div>
	</div>
	<jsp:include page="/common/footer.jsp" />
</body>
</html>