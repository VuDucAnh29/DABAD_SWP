<%-- 
    Document   : login
    Created on : Feb 10, 2022, 4:10:30 PM
    Author     : Utech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Input your information : </h1>
        <form action="MainController" method="POST">
            Email <input type="text" name="email" required=""/></br>
            Password<input type="password" name="password" required=""/></br>
            <input type="submit" name="action" value="Login" />
            <input type="reset" value="reset"/>
        </form>
        <%
            String error = (String) request.getAttribute("ERROR");
            if(error == null){
                error = "";
            }
        %>
        <%= error %>
        <a href="create.jsp"> create a new user</a>   
        <a href="shopping.jsp"> Shopping </a>  
    </body>
</html>
