<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="vittoriostyle.css" rel="stylesheet">
<link href="fileProvvisorio.css" rel="stylesheet">
<title>Statistica scheda id<%=Integer.parseInt(request.getParameter("id_scheda"))%></title>
<style type="text/css">
#tableContainer{
    display: block;
    width: 80%;
    height: 100%;
    margin: 0 auto;
    position: relative;
}

#tableContainer table{
    margin: 0;
    position: absolute;
  	top: 50%;
    left: 50%;
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}

.main{
    width: 80%;
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
	<%double[] risultati = (double[]) request.getAttribute("risultati");%>
        <div class="table">
			<div id="tableContainer">
			<table>
    		<caption><h2>Tabella statistiche</h2></caption>
	 		<tr>
        		<th><%=request.getAttribute("risposta1").toString()%>Risposta1</th>
				<th><%=request.getAttribute("risposta2").toString()%>Risposta2</th>
				<th><%=request.getAttribute("risposta3").toString()%>Risposta3</th>
			</tr>
			<tr>
				<td><%=risultati[0]%>risultati[0]</td>
				<td><%=risultati[1]%>risultati[1]</td>
				<td><%=risultati[2]%>risultati[2]</td>
			</tr>
			</table>
			</div>
		</div>
	</div>
</div>

<%@ include file="../css/footer.jsp" %>

</div>
</body>
</html>
