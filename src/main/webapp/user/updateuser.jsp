<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/css/vittoriostyle.css"  rel="stylesheet">
<title>Edit User</title>
<style type="text/css">
	#updateutente {
		display: block;
		width: 80%;
		height: 100%;
		margin: 0 auto;
		position: relative;
	}
	#updateutente form {
		margin: 0;
  		position: absolute;
		top: 50%;
  		left: 50%;
  		-ms-transform: translate(-50%, -50%);
  		transform: translate(-50%, -50%);
	}
	
	.rowForm {
		margin-bottom: 2%;
	}
	
	.col-2{
		position: relative;
	}
	
	.col-2 input {
		height: 60%;
    	margin: 0;
    	position: absolute;
		top: 50%;
  		transform: translatey(-50%);
	}
	
	.col-2 select {
		width: 50%;
        margin: 0;
    	top: 50%;
    	transform: translatey(-50%);
    	position: absolute;
	}
	
	#btn{
		position: relative;
		witdh: 100%;
		height: 20%;
	}
	
	#btn button[type=submit]{
	
		padding: 0;
		width: 50%;
		height: 70%;
		position: absolute;
		margin: 0;
		
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

		<div id="updateutente">
		<form id="dataInsert" action="/user/update?id=<%=u.getId()%>" method="post">
  		<div class="tableForm">
  		<div class="rowForm">
    		<div class="col-1">
      			<span for="user">Username</span>
    		</div>
    		<div class="col-2">
      			<input type="text" id="user" name="username" value=<%=u.getUsername()%>>
    		</div>
  		</div>
  		<div class="rowForm">
    		<div class="col-1">
     			<span for="pass">Password</span>
    		</div>
    		<div class="col-2">
      <input type="text" id="pass" name="password" value=<%=u.getPassword()%>> 
    </div>
  </div>
  <div class="rowForm">
    <div class="col-1">
      <span for="type">Usertype</span>
    </div>
   		 <div class="col-2">
 			<select id="type" name="usertype">
  				<option value="ADMIN" <%if(u.getUsertype().equals("ADMIN")) {%>selected<%}%>>ADMIN</option>
  				<option value="USER" <%if(u.getUsertype().equals("USER")) {%>selected<%}%>>USER</option>
			</select>
    	</div>
  </div>
  
  <div id="btn">
      <button type="submit" >Edit</button>
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