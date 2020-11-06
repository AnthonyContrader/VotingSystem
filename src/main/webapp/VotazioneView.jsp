<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.SchedaVotazioneDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../css/vittoriostyle.css" rel="stylesheet">
<title>Read User</title>
<style type="text/css">
	

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

	<div id="menu">  
 		<div class="link"><a href = "SchedaVotazioneServlet?mode=back"><span>HOME</span></a></div>
 	</div>

<div class="main">
    <%
    if((boolean)request.getAttribute("check") == true){
    	SchedaVotazioneDTO s = (SchedaVotazioneDTO) request.getAttribute("scheda");
    %>
 		<div id ="SchedaTitolo" ><%=s.getTitolo()%></div>
 		<div id ="Domanda" ><%=s.getDomanda()%></div>
		<form action="UtenteVotanteServlet" method="post">
			<input type="text" name="id_scheda" value="<%=s.getId()%>" style="display:none" readonly>
			<input type="text" name="mode" value="insert" style="display:none" readonly>
			<div id="rowR1">
				<input type="radio" id="R1" name="risposta" value="1" >
				<label for="R1"><%=s.getRisposta1()%></label>
			</div>
			<div id="rowR1">
				<input type="radio" id="R2" name="risposta" value="2" >
				<label for="R2"><%=s.getRisposta2()%></label>
			</div>
			<div id="rowR1">
				<input type="radio" id="R3" name="risposta" value="3" >
				<label for="R3"><%=s.getRisposta3()%></label>
			
			</div>
			<div id="bottonRisp">
				<input type="submit" value="invia">
			</div> 
		</form>
       
     <%
    	} else {
     %>    
        <p>ATTENZIONE HAI GIA' VOTATO IN QUESTA SCHEDA</p>
        <p><a href = UserServlet?mode=back>INDIETRO</a></p>
        
     <%
    	}
     %>
</div>  

<%@ include file="../css/footer.jsp" %>
</div> 
</div>

</body>
</html>
    