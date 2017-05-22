<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= UTF-8">
<title>DISE Ranking</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="clientjs/alerttraduccion.js"></script>

</head>
<body>
	<c:if test="${not empty user}">
		<header> Usuario:&nbsp;<c:out value="${user}" />&nbsp;|&nbsp;<a
			href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
		</header>
	</c:if>
	<c:if test="${empty user}">
		<header> <a	href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
		</header>
	</c:if>

	<h1 class="titulo">Diccionario Social Emoji</h1>

	<nav id="nav">
	<ul>
		<li class="tab"><a href="/isst_dise">Traductor</a></li>
		<li class="tab"><a href="/votar_traduccion">Votar Traduccion</a></li>
		<li class="tab"><a href="/nuevoemoji">Nuevo Emoji</a></li>
		<li class="tab"><a href="/ranking">Ranking</a></li>

	</ul>
	</nav>

	<div class="votaciones">
		<c:if test="${not empty user}">
			<p>
				Su puntuacion es
				<c:out value="${puntuacion.puntuacion}" />
			</p>
		</c:if>
		<table>
			<thead>
				<td>Usuario</td>
				<td>Puntuacion</td>
			</thead>
			<c:forEach items="${puntuaciones}" var="p">
				<tr>
					<td><c:out value="${p.usuario}" /></td>
					<td style="text-align: center;"><c:out value="${p.puntuacion}" /></td>
				</tr>
			</c:forEach>
		</table>

	</div>


</body>
</html>