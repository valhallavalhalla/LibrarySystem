<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Catalog</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<h2>Book Catalog</h2>
	<form action="ControllerServlet" method="post" class="form-signin">
		<button type="submit" value="PROFILE" name="command" class="btn btn-default">
		Back to profile</button>
		<br/>
	</form>
		<br/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Author</th>
					<th>Title</th>
					<th>AmountAvailable</th>
					<th>Quantity to order</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${requestScope.books}">
					<tr>
						<td>${book.author}</td>
						<td>${book.title}</td>
						<td>${book.amountAvailable}</td>
						<td>
						<p> <form action="ControllerServlet" method="post">
								<input name="idBook" value="${book.id}" type="hidden"/>	
								<button type="submit" name="command" value="ORDER_BOOK" class="btn btn-default">
								Make order</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>