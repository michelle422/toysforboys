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
		tfoot {
			background: blue;
		}
	}
	</style>
</head>
<body>
	<header>
		<h1>Unshipped orders</h1><span>${fouten.order}</span>
		<h2>${fouten.notShipped}</h2>
	</header>
	<c:if test="${not empty orders}">
		<form method="post" id="shippedform">
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
						<fmt:parseDate value="${order.orderDate}" pattern="yyyy-MM-dd" var="orderDate" type="date"/>
						<td><fmt:formatDate value="${orderDate}" type='date' dateStyle='short'/></td>
						<fmt:parseDate value="${order.requiredDate}" pattern="yyyy-MM-dd" var="requiredDate" type="date"/>
						<td><fmt:formatDate value="${requiredDate}" type='date' dateStyle='short'/></td>
						<td>${order.customer.name}</td>
						<td>${order.comments}</td>
						<td>
							<img src='<c:url value="/images/${order.status}.png"/>' alt='<fmt:message key="${order.status}"/>'>
							${order.status}
						</td>
						<td>
							<input type="checkbox" name="orderIdShip" value="${order.id}"> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<label><input type="submit" value="Set as shipped" id="shippedknop"></label>
		<c:if test="${vanafRij != 0}">
			<c:url value="" var="vorigePaginaURL">
				<c:param name="vanafRij" value="${vanafRij - aantalRijen}"/>
			</c:url>
			<a href="<c:out value='${vorigePaginaURL}'/>" title="vorige pagina" class="pagineren">&larr;</a>
		</c:if>
		<c:if test="${empty laatstePagina}">
			<c:url value="" var="volgendePaginaURL">
				<c:param name="vanafRij" value="${vanafRij + aantalRijen}"/>
			</c:url>
			<a href="<c:out value='${volgendePaginaURL}'/>" title="volgende pagina" class="pagineren">&rarr;</a>
		</c:if>
		</form>
	</c:if>
	<script>
		document.getElementById('shippedform').onsubmit = function() {
			document.getElementById('shippedknop').disabled = true;
		}
	</script>
</body>
</html>
