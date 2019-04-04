<%--
  Created by IntelliJ IDEA.
  User: gunne
  Date: 27.03.2019
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Contacts Application</title>
    <meta charset="utf-8">
</head>
<body>
<center>
    <h1>Contact Management</h1>
    <h2>
        <a href="/new">Add New Contact</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Contacts</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/search">Search</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Contacts</h2></caption>
        <tr>
            <th>Full name</th>
            <th>Date of birth</th>
            <th>Address</th>
            <th>Work place</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="contact" items="${listContact}">
            <tr>
                <td><c:out value="${contact.name}" /> <c:out value="${contact.patronymic}" /> <c:out value="${contact.surname}" /></td>
                <td><c:out value="${contact.dateOfBirth}" /></td>
                <td><c:out value="${contact.country}" /> <c:out value="${contact.city}" /> <c:out value="${contact.address}" /></td>
                <td><c:out value="${contact.workPlace}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${contact.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${contact.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
