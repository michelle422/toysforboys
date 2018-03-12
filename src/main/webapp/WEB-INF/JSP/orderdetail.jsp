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
		th {
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
		<dt>Ordered:</dt><dd>${order.orderDate}</dd>
		<dt>Required:</dt><dd>${order.requiredDate}</dd>
		<dt>Customer:</dt><dd>${order.customer.name}</dd>
		<dd>${order.customer.adres.streetAndNumber}</dd>
		<dd>${order.customer.adres.postalCode} ${order.customer.adres.city} ${order.customer.adres.state}</dd>
		<dt>Comments:</dt><dd>${order.comments}</dd>
	</dl>
	<dl>
		<dd>Details:</dd>
	</dl>
	<table>
		<thead>
			<tr>
				<th>Product</th><th>Price each</th><th>Quantity</th>
				<th>Value</th><th>Deliverable</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order.products}" var="orderdetail">		
				<tr>
					<td>${orderdetail.name}</td>
					<c:set value="${orderdetail.productQuantitiesEnPriceEach.priceEach}" var="priceEach"/>
					<td>${priceEach}</td>
<%-- 					<c:set value="${orderdetail.quantitiesEnPriceEach.quantityOrdered}" var="quantityOrdered"/> --%>
<%-- 					<td>${quantityOrdered}</td> --%>
<%-- 					<c:set value="${priceEach * quantityordered}" var="value"/> --%>
					<td></td>
<!-- 					<td></td> -->
					<c:set value="${81.35 * 30}" var="value"/>
					<td>${value}</td>
					<c:set var="totalValue" value="${value + totalValue}"/>
<%-- 					<td>${quantityOrdered <= orderdetail.products.quantityInStock ? '&check' : '&cross'}</td> --%>
					<td>${30 <= 38 ? '&#x2713;' : '&#x274c;'}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>