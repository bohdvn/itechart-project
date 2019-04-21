<%--
  Created by IntelliJ IDEA.
  User: gunne
  Date: 27.03.2019
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Contact Management</h1>
    <h2>
        <a href="/new">Add New Contact</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Contacts</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/search">Search</a>

    </h2>
</div>
    <h1>Exception: </h1>
    <h2><%=exception.getMessage() %></h2>
</body>
</html>
