function login(){
	var password = $("#loginPassword").val();
	localStorage.setItem("adminAuth", password);
	window.location.replace("/northgateuniform/pages/welcome-admin.html");
}