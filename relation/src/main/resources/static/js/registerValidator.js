function checkForm() {
	if (checkUserName() && checkPassword() && checkPhone() && ConfirmPassword()) {
		return true;
	} else {
		return false;
	}
}
//validate user name  
function checkUserName() {
	var username = document.getElementById('username');
	var errname = document.getElementById('nameErr');
	var pattern = /^\w{6,20}$/; //用户名格式正则表达式：用户名要在6-20位 
	if (username.value.length == 0) {
		errname.innerHTML = "用户名不能为空"
		errname.className = "error"
		return false;
	}
	if (!pattern.test(username.value)) {
		errname.innerHTML = "用户名要在6-20位"
		errname.className = "error"
		return false;
	} else {
		errname.innerHTML = "OK"
		errname.className = "success";
		return true;
	}
}
//validate password
function checkPassword() {
	var userpasswd = document.getElementById('password');
	var errPasswd = document.getElementById('passwordErr');
	var pattern = /^\w{6,20}$/; //密码要在6-20位 
	if (!pattern.test(userpasswd.value)) {
		errPasswd.innerHTML = "密码不合规范"
		errPasswd.className = "error"
		return false;
	} else {
		errPasswd.innerHTML = "OK"
		errPasswd.className = "success";
		return true;
	}
}
//verify password
function ConfirmPassword() {
	var userpasswd = document.getElementById('password');
	var userConPassword = document.getElementById('userConfirmPasword');
	var errConPasswd = document.getElementById('conPasswordErr');
	if ((userpasswd.value) != (userConPassword.value) || userConPassword.value.length == 0) {
		errConPasswd.innerHTML = "上下密码不一致"
		errConPasswd.className = "error"
		return false;
	} else {
		errConPasswd.innerHTML = "OK"
		errConPasswd.className = "success";
		return true;
	}
}
//validate phone number
function checkPhone() {
	var userphone = document.getElementById('phoneNumber');
	var phonrErr = document.getElementById('phoneErr');
	var pattern = /^1\d*/; //验证手机号正则表达式 
	if (!pattern.test(userphone.value)) {
		phonrErr.innerHTML = "手机号码不合规范"
		phonrErr.className = "error"
		return false;
	} else {
		phonrErr.innerHTML = "OK"
		phonrErr.className = "success";
		return true;
	}
}