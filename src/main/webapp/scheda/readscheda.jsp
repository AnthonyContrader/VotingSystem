<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.SchedaVotazioneDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read Scheda</title>
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
<div id="bodyContainer">
<%@ include file="../css/header.jsp" %>
<div class="navbar">
	<div id="logout">
  		<a href="LogoutServlet"><span>Logout</span></a>
  	</div>
</div>
<div id="CorpoCentrale">
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

  <%SchedaVotazioneDTO s = (SchedaVotazioneDTO) request.getAttribute("dto");%>  
  <div id="tableContainer"> 
<table>
	<tr> 
		<th>id_scheda</th>
		<th>titolo</th>
		<th>domanda</th>
		<th>risposta1</th>
		<th>risposta2</th>
		<th>risposta3</th>
	</tr>
	<tr>
		<td><%=s.getId()%></td>
		<td> <%=s.getTitolo()%></td>
		<td> <%=s.getDomanda()%></td>
		<td><%=s.getRisposta1()%></td>
		<td> <%=s.getRisposta2()%></td>
		<td> <%=s.getRisposta3()%></td>
	</tr>	
</table>
</div>
</div>
</div> 
<%@ include file="../css/footer.jsp" %>
</div>
</body>
</html>