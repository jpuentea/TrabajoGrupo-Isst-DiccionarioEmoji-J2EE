<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DISE Votar Traducción</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet" />
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

	<div class="imagen">
		<img src="${emoji.imagen}" />
	</div>
	<div>
		<div>
			<p>Traducciones aceptas:</p>
			<c:forEach items="${validadas}" var="traduccion">
				<p>${traduccion.traduccion}</p>
			</c:forEach>
			<p>Traducciones pendientes:</p>
			<c:forEach items="${noValidadas}" var="traduccion" varStatus="n">
				<form action="/admin_traduccion" method="post" acceptcharset="utf-8">
					<p>${traduccion.traduccion}:
						<input type="submit" value="Aceptar"  name="submit" class="submit">
						<input type="submit" value="Rechazar" name="submit" class="submit"></p>
					<input type="hidden" value="${emoji.imagen}" name="imagen">
					<input type="hidden" value="${traduccion.traduccion}" name="traduccion"> 
				</form>
			</c:forEach>

		</div>
	</div>


</body>
</html>