<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<div class="container">
		<form action="ControllerServlet" method="post" class="form-signin">
			<h2 class="form-signin-heading">Login to Library System</h2>
			<input type="text" class="form-control input-lg" name="login" placeholder="Your login" autofocus/> 
			<input type="password" class="form-control input-lg" name="password" placeholder="Password" required/>
			<button type="submit" class="btn btn-lg btn-primary btn-block" value="LOGIN" name="command">
			Login</button>
			<br/>
			<a href="index.jsp">Back to main page</a>
		</form>
	</div>
</body>
</html>