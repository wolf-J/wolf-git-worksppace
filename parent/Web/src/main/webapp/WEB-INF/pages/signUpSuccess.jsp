<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="UserMessage">
		<h4>Sign Up Success!</h4>
		<p>
		<h5>details：</h5>
		UserName：${classMember.name}<br /> PassWord：${classMember.password}<br /> PhoneNumber：${classMember.phoneNumber}<br />
		UserID：${classMember.id}<br /> creatTime：${classMember.creatTime}<br />
		</p>
	</div>
</body>
</html>