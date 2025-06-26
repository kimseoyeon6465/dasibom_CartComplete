//마이페이지 버튼 클릭 시 실행 이벤트
function toggleDropdown() {
	const dropdown = document.getElementById('mypageDropdown');
	dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}


  // 클릭 바깥을 누르면 닫히도록
window.addEventListener('click', function (e) {
	const dropdown = document.getElementById('mypageDropdown');
	const target = e.target;
	if (!target.closest('.mypage-container')) {
		dropdown.style.display = 'none';
	}
});

//장르별 버튼 눌렀을 때 실행 이벤트
function toggleGenreDropdown() {
    const dropdown = document.getElementById("genreDropdown");
    dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
}

