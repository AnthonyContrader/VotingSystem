<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.SchedaVotazioneDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="vittoriostyle.css" rel="stylesheet">
<link href="fileProvvisorio.css" rel="stylesheet">
<title>Update card</title>
<style type="text/css">
#formContainer{
    display: block;
    height: 100%;
    width: 80%;
    margin: 0 auto;
    position: relative;
}

#formUpdate{
    margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.dati input{
    width: 100%;
    height: 15%;
    display: block;
}

.dati{
    display: block;
    width: 80%;
    height: 80%;
    margin: 0 auto;
}

.bottone{
    display: block;
    width: 40%;
    height: 20%;
    margin: 0 auto;
    position: relative;
}

.bottone input{
    display: block;
    height: 50%;
    width: 100%;
    margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

</style>
</head>
<body>
<div id="bodyContainer">

<%@include file="css/header.jsp"%>

<div class="navbar">
<div id="logout">
  <a href="LogoutServlet"><span>Logout</span></a>
</div>
</div>

<div id="CorpoCentrale">
    <div class="menu">
        <div class="link"><a href="homeadmin.jsp"><span>Home</span></a></div>
        <div class="link"><a class="active"  href="UserServlet?mode=userlist"><span>Users</span></a></div>
        <div class="link"><a href="SchedaVotazioneServlet?mode=schedelist"><span>Schede</span></a></div>
        <div class="link"><a href="UtenteVotanteServlet?mode=votolist"><span>Lista voti</span></a></div>
</div>
<div class="main">


<%SchedaVotazioneDTO s = (SchedaVotazioneDTO) request.getAttribute("dto");%>

<div id="formContainer">
	<form id="formUpdate" action="SchedaVotazioneServlet?mode=update" method="post">
<div class="dati">
		<input type="text" id ="SchedaTitolo" name ="titolo" value ="<%=s.getTitolo()%>" required>
		<input type="text" id ="Domanda" name ="domanda" value ="<%=s.getDomanda() %>" required>

		<input type="text" id="Risposta1" name ="risposta1" value ="<%=s.getRisposta1()%>" required>
		<input type="text" id="Risposta2" name ="risposta2" value ="<%=s.getRisposta2()%>" required>
		<input type="text" id="Risposta3" name ="risposta3" value ="<%=s.getRisposta3()%>" required>
		<input type="text" name ="id_scheda" value = "<%=s.getId() %>" style = "display:none" readonly>
</div>
<div class="bottone">
      <input type = "submit" value = "invia">
</div>
	</form>
</div>
</div>
</div>

<%@ include file="../css/footer.jsp" %>

</div>
</body>
</html>
