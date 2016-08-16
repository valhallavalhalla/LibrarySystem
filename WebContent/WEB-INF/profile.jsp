<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Profile</title>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	</head>
	<body>
		<h3>Welcome to your profile</h3>
		<br/>
		<form action="ControllerServlet" method="post">
		<button type="submit" value="LOGOUT" name="command" class="btn btn-default">Logout</button>
		</form>
		<br/>
			<form action="ControllerServlet" method="post">	
			<button type="submit" value="CATALOG" name="command" class="btn btn-default">
			Show Book Catalog</button>
		</form>
		<br/>
		<h2>Your book orders</h2>
		<br/>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${requestScope.userOrders}">
					<tr>
						<td>${order.id}</td>
						<td>${order.book.author}</td>
						<td>${order.book.title}</td>
						<c:if test="${not order.isProcessed}">
						<td>Waiting to confirm</td>
						</c:if>
						<c:if test="${order.isProcessed}">
						<td>Confirmed</td>
						<td>
							<form action="ControllerServlet" method="post">
								<input name="idOrder" value="${order.id}" type="hidden"/>
								<input name="idBook" value="${order.book.id}" type="hidden"/>		
								<button type="submit" value="RETURN_BOOK" name="command" class="btn btn-default">
								Return the book</button>
							</form>
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>