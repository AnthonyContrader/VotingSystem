<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/vittoriostyle.css"  rel="stylesheet">
<title>Read User</title>
<style type="text/css">
	#tableContainer {
		display: block;
		width: 80%;
		height: 100%;
		margin: 0 auto;
		position: relative;
	}
	#tableContainer table {
		margin: 0;
  		position: absolute;
		top: 50%;
  		left: 50%;
  		-ms-transform: translate(-50%, -50%);
  		transform: translate(-50%, -50%);
	}
</style>
</head>
<body>
<div id="bodyContainer">
<%@ include file="../css/header.jsp" %>
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
	<%UserDTO u = (UserDTO) request.getAttribute("dto");%>
	<div id="tableContainer">
	<table>
		<tr> 
			<th>Username</th>
			<th>Password</th>
			<th>Usertype</th>
		</tr>
		<tr>
			<td><%=u.getUsername()%></td>
			<td> <%=u.getPassword()%></td>
			<td> <%=u.getUsertype()%></td>
		</tr>	
	</table>
	</div>
	</div>
</div>
<%@ include file="../css/footer.jsp" %>
</div>
</body>
</html>