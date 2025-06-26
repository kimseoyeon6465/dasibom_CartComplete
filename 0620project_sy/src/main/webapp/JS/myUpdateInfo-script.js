$(document).ready(function () {
	let currentEditing = null;
	let nicknameCheckPassed = false;
  

  function showField(field) {
    hideField(currentEditing);
    currentEditing = field;

	if (field === "tel") {
		$("#txt-tel").hide();
		$("#input-tel").show();
	} else if(field === "name"){
		$("#txt-name").hide();
	    $("#input-name").show();
	    $("#name").val($("#txt-name").text().trim());
	    $("#check-name").show();
	    $(".btn-save[data-field='name']").prop("disabled", true);
	} else {
	    $("#txt-" + field).hide();
	    $("#" + field).css("display", "inline-block");
	}
	
	$(".btn-save[data-field='" + field + "']").css("display", "inline-block");
	$(".btn-view[data-field='" + field + "']").hide();
  }

  function hideField(field) {
	  if (!field) return;
	
	  if (field === "tel") {
	    $("#input-tel").hide();
	    $("#txt-tel").show();
	  } else if(field === "name"){
	  	$("#input-name").hide();
	    $("#txt-name").show();
	    nicknameCheckPassed = false;
	    $("#check-name").show();
	    $(".btn-save[data-field='name']").prop("disabled", true);
	  } else {
	    $("#" + field).hide();
	    $("#txt-" + field).show();
	  }
	
	  $(".btn-save[data-field='" + field + "']").hide();
	  $(".btn-view[data-field='" + field + "']").show();
	  currentEditing = null;
}

// 중복확인 버튼 이벤트
$("#check-name").on("click", function () {
  const nickname = $("#name").val().trim();

  if (!nickname) {
    alert("닉네임을 입력해주세요.");
    return;
  }

  $.ajax({
    url: "checkNameDuplicate.do",
    method: "POST",
    data: { name: nickname },
    success: function (data) {
      if (data.available) {
        alert("사용 가능한 닉네임입니다.");
        nicknameCheckPassed = true;
        $(".btn-save[data-field='name']").prop("disabled", false);
      } else {
        alert("이미 존재하는 닉네임입니다.");
        nicknameCheckPassed = false;
        $(".btn-save[data-field='name']").prop("disabled", true);
      }
    },
    error: function () {
      alert("서버 오류가 발생했습니다.");
    }
  });
});

// 입력 변경 시 중복확인 초기화
$("#name").on("input", function () {
  nicknameCheckPassed = false;
  $(".btn-save[data-field='name']").prop("disabled", true);
});

// 확인 버튼
function saveField(field) {
  if (field === "tel") {
    const tel1 = $("#tel1").val().trim();
    const tel2 = $("#tel2").val().trim();
    const tel3 = $("#tel3").val().trim();

    if (!tel1 || !tel2 || !tel3) {
      alert("전화번호를 모두 입력해주세요.");
      return;
    }

    const telFull = `${tel1}-${tel2}-${tel3}`;

    $.ajax({
      url: "updateUserField.do",
      method: "POST",
      data: {
        field: "tel",
        value: telFull
      },
      success: function (data) {
        if (data.success) {
          $("#txt-tel").text(telFull);
          hideField("tel");
        } else {
          alert("전화번호 저장 실패: " + data.message);
        }
      },
      error: function () {
        alert("서버 오류가 발생했습니다.");
      }
    });

  } else if(field === "name" && !nicknameCheckPassed){
	  	alert("중복확인을 먼저 해주세요.");
	    return;
  } else {
  
    const newValue = $("#" + field).val().trim();

    $.ajax({
      url: "updateUserField.do",
      method: "POST",
      data: {
        field: field,
        value: newValue
      },
      success: function (data) {
        if (data.success) {
          $("#txt-" + field).text(newValue);
          hideField(field);
        } else {
          alert("업데이트 실패: " + data.message);
        }
      },
      error: function () {
        alert("서버 오류가 발생했습니다.");
      }
    });
  }
}

	// 변경하기 버튼
	$(".btn-view").on("click", function (e) {
	  e.stopPropagation();
	  const field = $(this).data("field");
	  showField(field);
	});
	
	// 확인 버튼
	$(".btn-save").on("click", function (e) {
	  e.stopPropagation();
	  const field = $(this).data("field");
	  saveField(field);
	});


  // 외부 클릭 감지
  $(document).on("click", function (e) {
  if (currentEditing) {
    const $input = $("#" + currentEditing);
    const $saveBtn = $(".btn-save[data-field='" + currentEditing + "']");
    const $checkBtn = $("#check-name"); // 추가: 중복확인 버튼 예외 처리


    // 필드가 'tel'일 경우 input 세 개를 하나의 래퍼로 묶어서 예외 처리
    if (currentEditing === "tel") {
      $input = $("#input-tel");
    } else if (currentEditing === "name") {
      $input = $("#input-name");
    } else {
      $input = $("#" + currentEditing);
    }

    if (
      !$input.is(e.target) &&
      !$saveBtn.is(e.target) &&
      !$checkBtn.is(e.target) &&
      !$input.has(e.target).length &&
      !$saveBtn.has(e.target).length &&
      !$checkBtn.has(e.target).length
    ) {
      hideField(currentEditing);
    }
  }
});

  
  // 비밀번호 변경 폼 열기
  $("#view-pw").on("click", function () {
    $(this).hide(); // "비밀번호 변경하기" 버튼 숨기기
    $("#pw-input-group").show(); // 입력 필드 보이기
  });

  // 입력 검증 로직
  $("#pw-current, #pw-new, #pw-confirm").on("input", function () {
    const current = $("#pw-current").val().trim();
    const pw1 = $("#pw-new").val().trim();
    const pw2 = $("#pw-confirm").val().trim();

    // 조건: 새 비번 2개 일치 + 현재 비번은 비워있지 않음
    const valid = current !== "" && pw1.length >= 4 && pw1 === pw2;

    $("#save-pw").prop("disabled", !valid);
  });

  // 저장 버튼 클릭
  $("#save-pw").on("click", function () {
    const current = $("#pw-current").val();
    const pw1 = $("#pw-new").val();

    $.ajax({
      url: 'updatePassword.do',
      method: 'POST',
      data: {
        currentPassword: current,
        newPassword: pw1
      },
      success: function (data) {
        if (data.success) {
          alert("비밀번호가 변경되었습니다.");
          $("#pw-input-group").hide();
          $("#view-pw").show();
          $("#pw-current, #pw-new, #pw-confirm").val("");
          $("#save-pw").prop("disabled", true);
        } else {
          alert("현재 비밀번호가 일치하지 않습니다.");
        }
      },
      error: function () {
        alert("서버 오류가 발생했습니다.");
      }
    });
  });
});
