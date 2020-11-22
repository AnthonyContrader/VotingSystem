<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/vittoriostyle.css"  rel="stylesheet">
<link href="/css/fileProvvisorio.css" rel="stylesheet">
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

<%@include file="../css/header.jsp"%>

<div class="navbar">
    <div id="logout">
        <a href="/user/logout"><span>LOGOUT</span></a>
    </div>
</div>

<div id="CorpoCentrale">
    <div class="menu">
  		<div class="link">
  			<a href="/user/home"><span>HOME</span></a>
  		</div>
  		<div class="link">
  			<a class="active"  href="/user/getall"><span>USERS</span></a>
  		</div>
  		<div class="link">
  			<a href="/schedavotazione/getall"><span>SCHEDE</span></a>
  		</div>
  		<div class="link">
  			<a href="/utentevotante/getall"><span>LISTA VOTI</span></a>
  		</div>
	</div>

    <div class="main">
	<%double[] risultati = (double[]) request.getAttribute("risultati");%>
        <div class="table">
			<div id="tableContainer">
			<table>
    		<caption><h2>Tabella statistiche</h2></caption>
	 		<tr>
        		<th><%=request.getAttribute("risposta1").toString()%></th>
				<th><%=request.getAttribute("risposta2").toString()%></th>
				<th><%=request.getAttribute("risposta3").toString()%></th>
			</tr>
			<tr>
				<td><%=risultati[0]%></td>
				<td><%=risultati[1]%></td>
				<td><%=risultati[2]%></td>
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
