// 전화번호 3분할은 서버 JSTL에서 처리된 값으로 남겨둡니다.

// 상태 추적용 배열
let checkItems = [];

// 약관 목록 정의
const terms = [
    { id: 0, title: '서비스 이용 약관 동의 [필수]', required: true },
    { id: 1, title: '개인정보 수집 및 이용 동의 [필수]', required: true },
    { id: 2, title: '광고성 마케팅 정보 수신 동의 [선택]', required: false }
];

// 페이지 로드 시 실행
window.onload = function () {
    updateSubmitButtonState();
};

// 유효성 검사 Pattern (추가예정)

// 아이디 중복 확인
function checkUserIdDuplication() {
    const user_Id = document.getElementById("user_Id").value.trim(); // trim()이 있어야 공백값이 null로 인식됨
    const xhr = new XMLHttpRequest();
    const params = "user_Id=" + encodeURIComponent(user_Id);

	// 공백 검사
	if (user_Id === "") {
        alert("아이디를 입력해주세요.");
        return; 
    }

    xhr.open("POST", "validateUserId.do", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                const result = xhr.responseText.trim();
                if (result === "available") {
                    alert("사용 가능한 아이디입니다.");
                    document.getElementsByName("userIdDuplication")[0].value = "userIdChecked";
                } else {
                    alert(result);
                    document.getElementsByName("userIdDuplication")[0].value = "userIdUnchecked";
                }
                updateSubmitButtonState();
            } else {
                alert("아이디 중복확인 요청 실패: " + xhr.status);
            }
        }
    };

    xhr.send(params);
}

// 닉네임 중복 확인
function checkNameDuplication() {
    const name = document.getElementById("name").value.trim();
    const xhr = new XMLHttpRequest();
    const params = "name=" + encodeURIComponent(name);

	// 공백 검사
	if (name === "") {
        alert("닉네임을 입력해주세요.");
        return; 
    }

    xhr.open("POST", "validateName.do", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                const result = xhr.responseText.trim();
                if (result === "available") {
                    alert("사용 가능한 닉네임입니다.");
                    document.getElementsByName("nameDuplication")[0].value = "nameChecked";
                } else {
                    alert(result);
                    document.getElementsByName("nameDuplication")[0].value = "nameUnchecked";
                }
                updateSubmitButtonState();
            } else {
                alert("닉네임 중복확인 요청 실패: " + xhr.status);
            }
        }
    };

    xhr.send(params);
}

// 중복확인 상태 초기화
function resetUserIdDuplication() {
    document.getElementsByName("userIdDuplication")[0].value = "userIdUnchecked";
    updateSubmitButtonState();
}

function resetNameDuplication() {
    document.getElementsByName("nameDuplication")[0].value = "nameUnchecked";
    updateSubmitButtonState();
}

// 비밀번호 일치 검사
function comparePassword() {
    const pw = document.getElementById("pw").value;
    const pwRe = document.getElementById("pwRe").value;
    const isPwEqual = document.getElementsByName("isPwEqual")[0];
    const msgSpan = document.getElementById("pwMatchMsg");

    if (pw === pwRe && pw.length > 0) {
        isPwEqual.value = "pwEqualed";
        msgSpan.innerText = "비밀번호가 일치합니다.";
        msgSpan.style.color = "green";
    } else {
        isPwEqual.value = "pwUnequaled";
        msgSpan.innerText = "비밀번호가 일치하지 않습니다.";
        msgSpan.style.color = "red";
    }
    updateSubmitButtonState();
}

// 약관 체크박스 선택/해제
function selectChecked(checked, id) {
    if (checked) {
        if (!checkItems.includes(id)) checkItems.push(id);
    } else {
        checkItems = checkItems.filter(item => item !== id);
    }

    // 전체 체크박스 상태 반영
    document.getElementById("selectAll").checked = checkItems.length === terms.length;
    updateSubmitButtonState();
}

// 전체 동의 체크박스
function allChecked(checked) {
    checkItems = checked ? terms.map(t => t.id) : [];
    terms.forEach(term => {
        document.getElementById("term-" + term.id).checked = checked;
    });
    updateSubmitButtonState();
}

// 가입 버튼 활성화 조건 통합 확인
function updateSubmitButtonState() {
    const userIdStatus = document.getElementsByName("userIdDuplication")[0].value;
    const nameStatus = document.getElementsByName("nameDuplication")[0].value;
    const pwEqualedStatus = document.getElementsByName("isPwEqual")[0].value;

    // 필수 약관 체크 여부
    const allRequiredAgreed = terms
        .filter(term => term.required)
        .every(term => checkItems.includes(term.id));

    const submitBtnSignup = document.getElementById("submitBtnSignup");

    if (
        userIdStatus === "userIdChecked" &&
        nameStatus === "nameChecked" &&
        pwEqualedStatus === "pwEqualed" &&
        allRequiredAgreed
    ) {
        submitBtnSignup.disabled = false;
    } else {
        submitBtnSignup.disabled = true;
    }
}
