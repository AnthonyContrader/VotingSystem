<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Admin</title>
<link href="../css/vittoriostyle.css" rel="stylesheet">
<link href="../css/styleHomeAdmin.css" rel="stylesheet">
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
        <div class="link"><a href="homeadmin.jsp"><span>HOME</span></a></div>
        <div class="link"><a href="UserServlet?mode=userlist"><span>USERS<span/></a></div>
        <div class="link"><a href="SchedaVotazioneServlet?mode=schedelist"><span>SCHEDE</span></a></div>
        <div class="link"><a href="UtenteVotanteServlet?mode=votolist"><span>LISTA VOTI</span></a></div>
      </div>

      <div class="main">
        <h1>Welcome ${user.getUsername()}</h1>

      </div>

    </div>

    <%@ include file="css/footer.jsp" %>
  </div>
</body>
</html>
