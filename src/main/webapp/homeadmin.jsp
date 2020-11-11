<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Admin</title>
<link href="/css/vittoriostyle.css" rel="stylesheet">
<link href="/css/styleHomeAdmin.css" rel="stylesheet">
</head>
<body>
  <div id="bodyContainer">

    <%@include file="css/header.jsp"%>

    <div class="navbar">
      <div id="logout">
				<a href="/user/logout"><span>LOGOUT</span></a>
			</div>
    </div>

    <div id="CorpoCentrale">

      <div class="menu">
        <div class="link"><a href="/user/home"><span>HOME</span></a></div>
        <div class="link"><a href="/user/getall"><span>USERS</span></a></div>
        <div class="link"><a href="/schedavotazione/getall"><span>SCHEDE</span></a></div>
        <div class="link"><a href="/utentevotante/getall"><span>LISTA VOTI</span></a></div>
      </div>

      <div class="main">
        <h1>Welcome ${user.getUsername()}</h1>

      </div>

    </div>

    <%@ include file="css/footer.jsp" %>
  </div>
</body>
</html>
