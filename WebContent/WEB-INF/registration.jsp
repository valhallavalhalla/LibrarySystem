<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Registration</title>
	</head>
	<body>
		<div>
			<h2>Registration</h2>
			<form action="ControllerServlet" method="post" class="form-signin-reg">
				<label >Login</label> 
				<input type="text" class="form-control input-lg" name="login" required/> 
				<br/> 
				<label>Password</label> 
				<input type="password"class="form-control input-lg" name="password" required/> 
				<br/> 
				<button type="submit" class="btn btn-primary btn-lg btn-block" name="command" value="REGISTRATION">
				Registration</button>
				<br/>
				<a href="index.jsp">Back to main page</a>
			</form>
		</div>
	</body>
	</html>