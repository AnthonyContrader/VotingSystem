<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.SchedaVotazioneDTO"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Utente</title>

<link href=${pageContext.request.contextPath}/css/vittoriostyle.css rel="stylesheet">
<link href=${pageContext.request.contextPath}/css/styleHomeUser.css rel="stylesheet">

<script type="text/javascript">
	
	document.addEventListener("DOMContentLoaded", function(){
		
		var btn1 = document.getElementById("btn1");
		var btn2 = document.getElementById("btn2");
		
		btn1.addEventListener("click",function (){
			var el1 = document.getElementById("gen");
			var el2 = document.getElementsByClassName("listaSchede")[0];
			var btn1 = document.getElementById("btn1");
			var btn2 = document.getElementById("btn2");
			el1.style.display = "block";
			el2.style.display = "none";
			btn1.style.background = "#00ccad";
			btn2.style.background = "#00a990";
		});
		
		btn2.addEventListener("click",function (){
			var el1 = document.getElementById("gen");
			var el2 = document.getElementsByClassName("listaSchede")[0];
			var btn1 = document.getElementById("btn1");
			var btn2 = document.getElementById("btn2");
			el1.style.display = "none";
			el2.style.display = "block";
			btn1.style.background ="#00a990";
			btn2.style.background ="#00ccad";
		});		
	}, false);

</script>
</head>
<body>
<div id="bodyContainer">

  <%@include file="css/header.jsp"%>

  <div class="navbar">
  		<div id="logout">
				<a href="LogoutServlet"><span>LOGOUT</span></a>
			</div>
	</div>

	<div id="CorpoCentrale">
		<div class="menu">
			<div id="btn1"><span>HOME</span></div>
  			<div id="btn2"><span>VOTA</span></div>

		</div>

	<div class="main">

		<div id="gen">
      <div id="tab">
			     <div class="primaColonna"><span>ID</span></div><div class="secondaColonna"><span>${user.getId()}</span></div>
			     <div class="primaColonna"><span>USERNAME</span></div><div class="secondaColonna"><span>${user.getUsername()}</span></div>
			     <div class="primaColonna"><span>TIPO UTENTE</span></div><div class="secondaColonna"><span>${user.getUsertype()}</span></div>
      </div>
		</div>

		<div class="listaSchede">
		<%
			List<SchedaVotazioneDTO> list = (List<SchedaVotazioneDTO>) request.getAttribute("list");
		%>
		<table>
			<caption>
				<h2>LISTA SCHEDE</h2>
			</caption>
			<thead>
				<tr>
					<th>TITOLO</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<%
				for (SchedaVotazioneDTO s : list) {
			%>
				<tr>
					<td>
						<%=s.getTitolo()%>
					</td>
					<td>
						<a href="UtenteVotanteServlet?mode=control&id_scheda=<%=s.getId()%>">
							VOTA
						</a>
					</td>
				</tr>
			<%
				}
			%>
				</tbody>
			</table>
	</div>

	</div>
</div>
<%@ include file="css/footer.jsp" %>
</div>
</body>
</html>