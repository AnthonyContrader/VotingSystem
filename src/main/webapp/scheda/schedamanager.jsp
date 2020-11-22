<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.SchedaVotazioneDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/vittoriostyle.css" rel="stylesheet">
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
	display:block;
	width: 100%;
	height: 100%;
	background-color: #a0a6ab;
	position: relative;
	}
	
	.container{
		width: 95%;
		height: 70%;
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	
	
	input[type=text]{
		
		width: 90%;
		height: 30%;
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	
	.col-1{
		float: left;
		width: 30%;
		height: 100%;
		font-size: 0.9em;
		color: white;
		font-weight: bold;
		position: relative;
	}
	
	.col-1 label{
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	
	.col-2 {
		float: right;
		width: 70%;
		height: 100%;
		position: relative;
	}
	
	.row {
		clear: both;
		width: 100%;
		height: 15%;
		
	}
	
	#btn{
		clear: both;
		width: 100%;
		height: 15%;
		position: relative;
	}
	
	button[type=submit]{
	
		width: 80%;
		heigth: 75%;
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);		
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
  		<a href="/user/logout"><span>LOGOUT</span></a>
  	</div>
  </div>
  
  <div id="CorpoCentrale">
  <div class="menu">
      <div class="link"><a href="/user/home"><span>HOME</span></a></div>
      <div class="link"><a href=/user/getall><span>USERS</span></a></div>
      <div class="link"><a href=/schedavotazione/getall><span>SCHEDE</span></a></div>
       <div class="link"><a href=/utentevotante/getall><span>LISTA VOTI</span></a></div>
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
			<td><a href=/schedavotazione/read?id_scheda=<%=s.getId()%>>LEGGI</a></td>
			<td><a href=/schedavotazione/preupdate?id_scheda=<%=s.getId()%>>MODIFICA</a></td>
			<td><a href=/schedavotazione/delete?id_scheda=<%=s.getId()%>>ELIMINA</a></td>			
			<td><a href=/utentevotante/statistica?id_scheda=<%=s.getId()%>>STAT</a></td>
		</tr>
		<%
			}
		%>
	</table>
</div>

<div id="formContainer">
<form id="formInsert" action="/schedavotazione/insert" method="post">
	<div class="container">
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
  <div id="btn">
  	<button type="submit" >Insert</button>
  </div>
  </div>
  
      
</form>
</div>
</div>
</div>
<%@ include file="../css/footer.jsp" %>
</div>
</body>
</html>