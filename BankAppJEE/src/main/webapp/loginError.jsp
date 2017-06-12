<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Mauvaise identification</p>
	<form method="POST" action="j_security_check">
		<label>Login : </label> <input type="text" name="j_username">
		<label>Password : </label> <input type="password" name="j_password">
		<button type="submit">Log in</button>
	</form>
</body>
</html>