<%--
  Created by IntelliJ IDEA.
  User: gunne
  Date: 07.04.2019
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Result</title>
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
<div style="text-align: center;">
    <h3><%=request.getAttribute("Message")%></h3>
</div>
</body>
</html>