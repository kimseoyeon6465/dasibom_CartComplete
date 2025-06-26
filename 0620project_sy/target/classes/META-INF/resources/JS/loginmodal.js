const contextPath = "${pageContext.request.contextPath}";

function loginedSubmit() {

  if (!isLoggedIn) {
    document.getElementById("loginModal").style.display = "flex";
    return false;
  }

  return true;
}

function closeLoginModal() {
	document.getElementById("loginModal").style.display = "none";
}
	
function goToLogin() {
	window.location.href = contextPath + "/login.do";
}