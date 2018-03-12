<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Unshipped orders' />
	<style>
		th {
			background: blue;
		}
		td:first-child {
			text-align: right;
		}
		th, td {
			padding: 0.5em;
		}
		table {
			table-layout: auto;
			width: 85em;
		}
	}
	</style>
</head>
<body>
	<header>
		<h1>Unshipped orders</h1>
		<h2>${fouten.notShipped}</h2>
	</header>
	<c:if test="${not empty orders}">
		<table>
			<thead>
				<tr>
					<th>ID</th><th>Ordered</th><th>Required</th><th>Customer</th>
					<th>Comments</th><th>Status</th><th>Ship</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td>
							<c:url value="/orderdetail.htm" var="orderdetailURL">
								<c:param name="id" value="${order.id}"/>
							</c:url>
							<a href="${orderdetailURL}">${order.id}</a>
						</td>
						<fmt:parseDate value="${order.orderDate}" pattern="yyyy-mm-dd" var="orderDate" type="date"/>
						<td><fmt:formatDate value="${orderDate}" type='date' dateStyle='short'/></td>
						<fmt:parseDate value="${order.requiredDate}" pattern="yyyy-mm-dd" var="requiredDate" type="date"/>
						<td><fmt:formatDate value="${requiredDate}" type='date' dateStyle='short'/></td>
						<td>${order.customer.name}</td>
						<td>${order.comments}</td>
						<td>
							<img src='<c:url value="/images/${order.status}.png"/>' alt='<fmt:message key="${order.status}"/>'>
							${order.status}
						</td>
						<td>
							<form method="post">
								<input type="checkbox" name="ship" value="${order.id}"> 
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>
