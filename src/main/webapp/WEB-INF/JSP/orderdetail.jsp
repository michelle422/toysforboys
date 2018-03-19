<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title="Order Nummer ${empty order ? '' : order.id}"/>
	<style>
		table {
			table-layout: auto;
			width: 85em;
		}
		tfoot, th {
			background: blue;
		}
		th, td {
			padding: 0.5em;
		}
		td {
			text-align: right;
		}
		td:last-child {
			text-align: center;
		}
	</style>
</head>
<body>
	<h1>Order ${empty order ? '' : order.id}</h1>
	<dl>
		<fmt:parseDate value="${order.orderDate}" pattern="yyyy-MM-dd" var="orderDate" type="date"/>
		<dt>Ordered:</dt><dd><fmt:formatDate value="${orderDate}" type='date' dateStyle='short'/></dd>
		<fmt:parseDate value="${order.requiredDate}" pattern="yyyy-MM-dd" var="requiredDate" type="date"/>
		<dt>Required:</dt><dd><fmt:formatDate value="${requiredDate}" type='date' dateStyle='short'/></dd>
		<dt>Customer:</dt><dd>${order.customer.name}</dd>
		<dd>${order.customer.adres.streetAndNumber}</dd>
		<dd>${order.customer.adres.postalCode} ${order.customer.adres.city} ${order.customer.adres.state}</dd>
		<dd>${order.customer.country.name}</dd>
		<dt>Comments:</dt><dd>${order.comments}</dd>
	</dl>
	<br>
	<h3>Details:</h3>
	<table>
		<thead>
			<tr>
				<th>Product</th><th>Price each</th><th>Quantity</th>
				<th>Value</th><th>Deliverable</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.orderDetails}" var="orderDetail">		
				<tr>
					<td>${orderDetail.product.name}</td>
					<td><fmt:formatNumber value="${orderDetail.priceEach}" minFractionDigits="2" maxFractionDigits="2"/></td>
					<td>${orderDetail.quantityOrdered}</td>
<%-- 					<c:set value="${orderDetail.priceEach * orderDetail.quantityOrdered}" var="value"/> --%>
					<td><fmt:formatNumber value="${orderDetail.value}" minFractionDigits="2" maxFractionDigits="2"/></td>
<%-- 					<c:set var="totalValue" value="${value + totalValue}"/> --%>
					<td>${orderDetail.quantityOrdered <= orderDetail.product.quantityInStock ? '&#x2713;' : '&#x274c;'}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td>Value: </td><td><fmt:formatNumber value="${order.value}" minFractionDigits="2" maxFractionDigits="2"/></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>