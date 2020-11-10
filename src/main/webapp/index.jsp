<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<link href="/css/vittoriostyle.css" rel="stylesheet">

<title>Login SAMPLE</title>
</head>
<body>
	
		<form class="login" action="/user/login" method="post">
			<div id="usernamerow">
				<label for="username">Username: </label>
			
				<input type="text" id="user" name="username" placeholder="Insert username">
			</div>
			<div id="passrow">
				<label for="password">Password: </label>
			
				 <input type="password" id="pass" name="password" placeholder="Insert password">
			</div>
			<div id="bottonrow">
			<button type="submit" value="Login" name="pulsante">Login</button>
			</div>
		</form>

	
</body>
</html>