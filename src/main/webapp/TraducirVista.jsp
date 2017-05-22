<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DISE - Diccionario Social Emoji - Traducir</title>
<link href="css/estilos.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="clientjs/tabs.js"></script>
<script type="text/javascript" src="clientjs/ponerEmojis.js"></script>

</head>


<body>
<header>
	<c:if test="${not empty user}">
		 Usuario:&nbsp;<c:out value="${user}" />&nbsp;|&nbsp;<a
			href="<c:url value="${url}"/>"><c:out value="${urlLinktext}" /></a>
		
	</c:if>
	<c:if test="${empty user}">
		 <a href="<c:url value="${url}"/>"><c:out
				value="${urlLinktext}" /></a>
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
</header>
	<!-- Seleccion de pestaña de traduccion -->
	<div class="tab">
		<button class="tablinks" onclick="openTraductor(event, 'Esp_Emoji')">Español
			-> Emoji</button>
		<button class="tablinks" onclick="openTraductor(event, 'Emoji_Esp')">Emoji
			-> Español</button>
	</div>

	<!-- Pestaña de traduccion de español a emoji -->
	<div id="Esp_Emoji" class="tabcontent" style="display: block">
		<div>
			<textarea id="textareaATraducirEspEmo" form="textoATraducirEspEmo"
				name="escrito" class="caja-texto"
				placeholder="Escribe aquí el texto en castellano"></textarea>
			<div id="traduccionAEmoji" class="caja">
				<c:out value="${textoFinal}" escapeXml="false" />
			</div>
			<!--<script type="text/javascript">document.getElementById("traduccionAEmoji").innerHTML=textoFinal</script>-->
		</div>
		<div class="button">
		<p style="clear:left"></p>
		<form id="textoATraducirEspEmo" action="/traducirEspEmo" method="post"
			acceptcharset="utf-8">
			<p><button type="submit" class="borrar">Traducir</button>
		</form>
		
		<button class="borrar" onClick="borrar1()">Reiniciar
			traducción</button></p>
		<!--	<button class="borrar" onClick="borrar2()">Reiniciar traducción2</button>-->
		</div>
	</div>

	<!-- Pestaña de traduccion de emoji a español -->
	<div id="Emoji_Esp" class="tabcontent" style="display: none">
		<div>
			<div style="float: left; width:49%">
				<div id="seleccionEmoji" class="cajita">
					<c:forEach items="${emojis}" var="emoji">
						<img onclick='insertarEmoji(this)' src="${emoji.imagen}"
							alt="${emoji.traducciones[0].traduccion}" width="43px"
							height="43px" />
					</c:forEach>
				</div>
				<div id="campo" class="cajita">
					<!-- Aquí se insertan los emojis con ponerEmojis.js -->
				</div>
			</div>
			<textarea id="traduccion" class="caja-texto" readonly
				placeholder="Aquí aparecerá la traducción"></textarea>
		</div>
		<div class="button">
			<!--<button class="borrar" onClick="borrar1()">Reiniciar31 traducción</button>-->
			<button class="borrar" onClick="borrar2()">Reiniciar
				traducción</button>
		</div>
	</div>

</body>
</html>