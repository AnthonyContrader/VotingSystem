<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
    import="it.contrader.dto.UtenteVotanteDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Voting List</title>
<style type="text/css">
	#tableContainer {
		display: block;
		width: 80%;
		height: 100%;
		margin: 0 auto;
		position: relative;
	}
	#tableContainer table {
		margin: 0;
  		position: absolute;
		top: 50%;
  		left: 50%;
  		-ms-transform: translate(-50%, -50%);
  		transform: translate(-50%, -50%);
	}
</style>
</head>
<body>
<div id= "bodyContainer">
<%@ include file="../css/header.jsp" %>
<div class="navbar">
  <div id="logout">
  		<a href="LogoutServlet"><span>LOGOUT</span></a>
  	</div>
  </div>
<div id ="CorpoCentrale">
<div class="menu">
  		<div class="link">
  			<a href="homeadmin.jsp"><span>Home</span></a>
  		</div>
  		<div class="link">
  			<a class="active"  href="UserServlet?mode=userlist"><span>Users</span></a>
  		</div>
  		<div class="link">
  			<a href="SchedaVotazioneServlet?mode=schedelist"><span>Gestione schede</span></a>
  		</div>
  		<div class="link">
  			<a href="UtenteVotanteServlet?mode=votolist"><span>Lista voti</span></a>
  		</div>
	</div>
<div class="main">
	<%
		List<UtenteVotanteDTO> list = (List<UtenteVotanteDTO>) request.getAttribute("list");
	%>
<div id="tableContainer">
	<table>
	<caption>
	<h3>
	UTENTI CHE HANNO VOTATO
	</h3>
	</caption>
		<thead>
			<tr>
				<th>ID UTENTE</th>
				<th>ID SCHEDA</th>
				<th>VOTO</th>
			</tr>
		</thead>
			<tbody>
			<%
				for (UtenteVotanteDTO u : list) {
			%>
			<tr>
				<td><%=u.getidutente()%></td>
				<td><%=u.getidscheda()%></td>
				<td><%=u.getvoto()%></td>		
			</tr>
			<%	}
			%>
			</tbody>
	</table>
</div>
<!-- aggiungere funzione ricerca -->
</div>
</div>
<%@ include file="../css/footer.jsp" %>
</div>
</body>
</html>