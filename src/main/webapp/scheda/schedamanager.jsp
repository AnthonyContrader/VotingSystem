<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.SchedaVotazioneDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href=${pageContext.request.contextPath}/css/vittoriostyle.css rel="stylesheet">
<title>Gestione schede</title>
<style type="text/css">
	#tableContainer {
		width: 70%;
		height: 100%;
		display: block;
		float: left;
		position: relative;
	}
	#formContainer {
	width: 30%;
	height: 100%;
	display: block;
	float: right;
	position: relative;
	}
	.footer {
	clear: both;
	}
	#formInsert, #tableContainer table{
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%); 
	}
	#formInsert {
	width: 100%;
	}
	h2 {
	margin: 0;
	width: 100%;
	text-align: center;
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
  
  <div id="CorpoCentrale">
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
		List<SchedaVotazioneDTO> list = (List<SchedaVotazioneDTO>) request.getAttribute("list");
	%>
	
<div id="tableContainer">
	
		<h2>
			GESTIONE SCHEDE
		</h2>

	<table>
		<tr>
			<th>Titolo</th>
			<th></th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<%
			for (SchedaVotazioneDTO s : list) {
		%>
		<tr>
			<td><%=s.getTitolo()%></td>
			<td><a href=SchedaVotazioneServlet?mode=read&id_scheda=<%=s.getId()%>>LEGGI</a></td>
			<td><a href=SchedaVotazioneServlet?mode=read&update=true&id_scheda=<%=s.getId()%>>MODIFICA</a></td>
			<td><a href=SchedaVotazioneServlet?mode=delete&id_scheda=<%=s.getId()%>>ELIMINA</a></td>			
			<td><a href=UtenteVotanteServlet?mode=stat&id_scheda=<%=s.getId()%>>STAT</a></td>
		</tr>
		<%
			}
		%>
	</table>
</div>

<div id="formContainer">
<form id="formInsert" action="SchedaVotazioneServlet?mode=insert" method="post">
  <div class="row">
    <div class="col-1">
      <label for="titolo">Titolo</label>
    </div>
    <div class="col-2">
      <input type="text" id="titolo" name="titolo" placeholder="inserisci titolo">
    </div>
  </div>
  <div class="row">
    <div class="col-1">
     <label for="domanda">Domanda</label>
    </div>
    <div class="col-2">
      <input type="text" id="domanda" name="domanda" placeholder="inserisci domanda"> 
    </div>
  </div>
  <div class="row">
    <div class="col-1">
      <label for="risposta1">Risposta1</label>
    </div>
   		 <div class="col-2">
 			<input type="text" id="risposta1" name="risposta1" placeholder="inserisci risposta1"> 
    	</div>
  </div>
  <div class="row">
    <div class="col-1">
      <label for="risposta2">Risposta2</label>
    </div>
   		 <div class="col-2">
 			<input type="text" id="risposta2" name="risposta2" placeholder="inserisci risposta2"> 
    	</div>
  </div>
  <div class="row">
    <div class="col-1">
      <label for="risposta3">Risposta3</label>
    </div>
   		 <div class="col-2">
 			<input type="text" id="risposta3" name="risposta3" placeholder="inserisci risposta3"> 
    	</div>
  </div>
      <button type="submit" >Insert</button>
</form>
</div>
</div>
</div>
<br>
<%@ include file="../css/footer.jsp" %>
</div>
</body>
</html>