<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<link href="/css/vittoriostyle.css" rel="stylesheet">

<title>Login SAMPLE</title>
<style type="text/css">
body{
	width: 100%;
	height: 100%;
}

#bodycontainer{
	display: block;
	height: 100%;
	margin: 0 auto;
	position: relative;
}

#formContainer {
	width: 35%;
	height: 40%;
	margin: 0;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.login{
	display: block;
	height: 100%;
	width: 80%;
	margin: 0 auto;
	padding: 0;

}
#usernamerow label, #passrow label{
	position: relative;
}

#usernamerow label span,
#passrow label span {
	position: absolute;
	margin: 0;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}


</style>
</head>
<body>
<div id="bodycontainer">
	<div id="formContainer">
		<form class="login" action="/user/login" method="post">
			<div id="usernamerow">
				<label for="username"><span>Username:</span> </label>
			
				<input type="text" id="user" name="username" placeholder="Insert username">
			</div>
			<div id="passrow">
				<label for="password"><span>Password:</span> </label>
			
				 <input type="password" id="pass" name="password" placeholder="Insert password">
			</div>
			<div id="bottonrow">
			<button type="submit" value="Login" name="pulsante">Login</button>
			</div>
		</form>
		</div>
</div>
	
</body>
</html>