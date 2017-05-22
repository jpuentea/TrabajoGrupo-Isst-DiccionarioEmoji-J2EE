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
<script type="text/javascript" src="clientjs/tabs.js"></script>
<script type="text/javascript" src="clientjs/ponerEmojis.js"></script>
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

	<c:if test="${not empty user}">
	<p>Seleccione el emoji:</p>


	<div class="emojis">
		<div class="cajita1">
		<form action="/votacion" method="post" acceptcharset="utf-8">
			<c:forEach items="${emojis}" var="emoji">
				<button type="submit" class="escrito" name="escrito" value="${emoji.imagen}">
					<img src="${emoji.imagen}" width="50px" height="50px">
				</button>
			</c:forEach>
		</form>
	</div>
	</div>


	<p>
	<div id="campo">
		<!-- Aquí se insertan los emojis con ponerEmojis.js -->
	</div>
	</p>

	</c:if>

	<c:if test="${empty user}">
		<p>
			Para poder votar una traduccion necesita estar registrado, autentíquese <a
				href="<c:url value="${url}"/>" class="btn btn-success">aquí</a>
		</p>
	</c:if>


</body>
</html>