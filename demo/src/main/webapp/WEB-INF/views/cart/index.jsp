<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Page</title>
</head>
<body>

	<h3>Cart Page</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Option</th>
			<th>Code</th>
			<th>Name</th>
			<th>Photo</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Sub Total</th>
		</tr>
		<c:set var="total" value="0"></c:set>
		<c:forEach var="item" items="${ cart }">
			<c:set var="total"
				value="${ total + item.product.price * item.quanity }"></c:set>
			<tr>
				<td align="center"><a
					href="${ pageContext.request.contextPath }/cart/remove/${ item.id }"
					onclick="return confirm('Are you sure?')">Remove</a></td>
				<td>${ item.product.code }</td>
				<td>${ item.product.name }</td>
				<td><img src="${ pageContext.request.contextPath }/resources/http://learningprogramming.net/wp-content/uploads/java/spring_mvc/${ item.product.photo }"
					width="50"></td>
				<td>${ item.product.price }</td>
				<td>${ item.quanity }</td>
				<td>${ item.product.price * item.quanity }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="right">Sum</td>
			<td>${ total }</td>
		</tr>
	</table>
	<br>
	<a href="${pageContext.request.contextPath}/product">Continue Shopping</a>

<c:if test="${not empty cart}">
 <a href="${pageContext.request.contextPath}/checkOutOrders"    
	  onclick="return confirm('Are you sure?')">Check Out</a>
</c:if>
	


</body>
</html>