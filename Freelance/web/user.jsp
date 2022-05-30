<%-- 
    Document   : user
    Created on : Feb 10, 2022, 4:40:21 PM
    Author     : Utech
--%>

<%@page import="user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <h1>Hello!!</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                loginUser = new UserDTO();
            }
        %>

        User ID : <%= loginUser.getUserID()%> <br>
        Full Name : <%= loginUser.getFullName()%>  <br>
        Role : <%= loginUser.getRoleID()%> <br>
        Password : <%= loginUser.getPassword()%> <br>
    </body>
</html>
