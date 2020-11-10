<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"
	import="it.contrader.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href=${pageContext.request.contextPath}/css/vittoriostyle.css rel="stylesheet">
<link href=${pageContext.request.contextPath}/css/styleUserManager.css rel="stylesheet">

<title>User Manager</title>
</head>
<div id="bodyContainer">
    <%@include file="../css/header.jsp"%>

    <div class="navbar">
      <div id="logout"><a href="LogoutServlet" id="logout"><span>LOGOUT</span></a></div>
    </div>
    <div id="CorpoCentrale">

    <div class="menu">
      <div class="link"><a href="homeadmin.jsp"><span>HOME</span></a></div>
      <div class="link"><a href="UserServlet?mode=userlist"><span>USERS</span></a></div>
      <div class="link"><a href="SchedaVotazioneServlet?mode=schedelist"><span>SCHEDE</span></a></div>
       <div class="link"><a href="UtenteVotanteServlet?mode=votolist"><span>LISTA VOTI</span></a></div>
    </div>
    <div class="main">

      <div id="mainContainer">
        <div id="tableContainerUtenti">
        <h2>LISTA UTENTI</h2>
        
	       <table>
           <thead>
		           <tr>
			              <th>Username</th>
			              <th>Password</th>
			              <th>Usertype</th>
			              <th></th>
			              <th></th>
		          </tr>
            </thead>
            <tbody>
            <%
		      List<UserDTO> list = (List<UserDTO>) request.getAttribute("list");
	  
			           for (UserDTO u : list) {
		          %>
		            <tr>
			               <td><a href=UserServlet?mode=read&id=<%=u.getId()%>>
					                  <%=u.getUsername()%>
			                  </a>
                    </td>
			              <td><%=u.getPassword()%></td>
			              <td><%=u.getUsertype()%></td>
			              <td><a href=UserServlet?mode=read&update=true&id=<%=u.getId()%>>MODIFICA</a>
			              </td>
			              <td><a href=UserServlet?mode=delete&id=<%=u.getId()%>>ELIMINA</a>
			              </td>
              </tr>
              <%
			           }
		          %>
            </tbody>
	         </table>
         </div>
         <div id="formInsertUtente">
           <form id="dataInsert" action="UserServlet?mode=insert" method="post">
             <div class="tableForm">
             <div class="rowForm">
               <div class="col-1">
                 <span>Username</span>
               </div>
              <div class="col-2">
                <input type="text" id="user" name="username" placeholder="inserisci username">
              </div>
            </div>
            <div class="rowForm">
              <div class="col-1">
                <span>Password</span>
              </div>
              <div class="col-2">
                <input type="text" id="pass" name="password" placeholder="inserisci password">
              </div>
            </div>
            <div class="rowForm">
              <div class="col-1">
                <span>Usertype</span>
              </div>
 		          <div class="col-2">
 			            <select id="type" name="usertype">
  				              <option value="ADMIN">ADMIN</option>
			                  <option value="USER">USER</option>
			           </select>
    	       </div>
           </div>

           <button type="submit" >Insert</button>
         </div>
         </form>
       </div>
       </div>
     </div>
   </div>
   <%@ include file="../css/footer.jsp" %>
 </div>
</body>
</html>
