<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
    import="it.contrader.dto.UtenteVotanteDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href=${pageContext.request.contextPath}/css/vittoriostyle.css rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Voting List</title>

<script type="text/javascript">
$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $(".search").filter(function() {
	      $(this).closest("tr").toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>

<style type="text/css">
	#tableContainer {
		display: block;
		width: 80%;
		height: 90%;
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
	
	#research {
		display: block;
		width: 100%;
		height: 10%;
		position: relative;
	}
	
	
	#research input {
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
		height: 50%;
		width: 30%;
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
  			<a href="homeadmin.jsp"><span>HOME</span></a>
  		</div>
  		<div class="link">
  			<a class="active"  href="UserServlet?mode=userlist"><span>USERS</span></a>
  		</div>
  		<div class="link">
  			<a href="SchedaVotazioneServlet?mode=schedelist"><span>SCHEDE</span></a>
  		</div>
  		<div class="link">
  			<a href="UtenteVotanteServlet?mode=votolist"><span>LISTA VOTI</span></a>
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
				<td class="search"><%=u.getId_utente()%></td>
				<td><%=u.getId_scheda()%></td>
				<td><%=u.getVoto()%></td>		
			</tr>
			<%	}
			%>
			</tbody>
	</table>
	</div>
	
	<div id="research">
		<input id="myInput" type="text" placeholder="Cerca id utente">
	</div>
</div>
<!-- aggiungere funzione ricerca -->
</div>
<%@ include file="../css/footer.jsp" %>
</div>
</body>
</html>