<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.SchedaVotazioneDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/vittoriostyle.css"  rel="stylesheet">
<title>Lista Voti</title>
<style type="text/css">

	
	#SchedaTitolo{
		width: 100%;
		height: 10%;
		text-align: center;
		font-size: large;
		font-style: normal;
	}
	
	#Domanda{
		width: 100%;
		height: 10%;
		text-align: center;
		font-size: medium;
		font-style: italic;
	}
	
	#responseContainer {
		display: block;
		width: 100%;
		height: 80%;
		position: relative;
	}
	
	#repsonseInsert {
		background-color: #a0a6ab;
		width: 50%;
		height: 60%;
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	
	#rowR1, #rowR2, #rowR3, #bottonRisp {
		display: block;
		width: 100%;
		height: 25%;
		position: relative;	
	}
	
	input[type=radio] {
    	display: none;
	}
	#rowR1 label, #rowR2 label, #rowR3 label, #bottonRisp label {
    	cursor: pointer;
    	display: block;
    	width: 100%;
    	height: 100%;
    
	}
	
	#rowR1, #rowR1 label{
		border-radius: 5px 5px 0px 0px; 
	}
	
	input[type=radio]:hover + label{
		background: #c8d0d8;
	}
	
	input[type=radio]:checked + label {
    	background: #c8d0d8;
	}
	
	input[type=submit]{
		width: 50%;
		height: 50%;
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
	}
	
	label span {
		font-size: x-large;
		color: white;
		font-weight: bold;
		margin: 0;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -50%);
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
<div id ="CorpoCentrale">

	<div class="menu">  
 		<div class="link"><a href = "homeuser.jsp"><span>HOME</span></a></div>
 	</div>

<div class="main">
    <%
    if((boolean)request.getAttribute("check") == true){
    	SchedaVotazioneDTO s = (SchedaVotazioneDTO) request.getAttribute("scheda");
    %>
 		<div id ="SchedaTitolo" ><%=s.getTitolo()%></div>
 		<div id ="Domanda" ><%=s.getDomanda()%></div>
 		<div id="responseContainer">
		<form id ="repsonseInsert" action="/utentevotante/insert">
			<input type="text" name="id_scheda" value="<%=s.getId()%>" style="display:none" readonly>
			<input type="text" name="mode" value="insert" style="display:none" readonly>
			<div id="rowR1">
				<input type="radio" id="R1" name="risposta" value="1" >
				<label for="R1"><span><%=s.getRisposta1()%></span></label>
			</div>
			<div id="rowR2">
				<input type="radio" id="R2" name="risposta" value="2" >
				<label for="R2"><span><%=s.getRisposta2()%></span></label>
			</div>
			<div id="rowR3">
				<input type="radio" id="R3" name="risposta" value="3" >
				<label for="R3"><span><%=s.getRisposta3()%></span></label>
			
			</div>
			<div id="bottonRisp">
				<input type="submit" value="invia">
			</div> 
		</form>
		</div>
       
     <%
    	} else {
     %>    
        <p>ATTENZIONE HAI GIA' VOTATO IN QUESTA SCHEDA</p>
        <p>Clicca <a href = "homeuser.jsp">QUI</a> per tornare indietro</p>
        
     <%
    	}
     %>
</div>  

<%@ include file="../css/footer.jsp" %>
</div> 
</div>

</body>
</html>
    