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
    <style>
        <%@include file='styles/list.css' %>
    </style>
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
    <form action="listActions" method="GET">
        <input type="submit" name="DeleteSelected" value="Delete Selected">
        <input type="submit" name="EmailSelected" value="Email Selected">
    <table border="1" cellpadding="5">
        <caption><h2>List of Contacts</h2></caption>
        <tr>
            <th></th>
            <th>Full name</th>
            <th>Date of birth</th>
            <th>Address</th>
            <th>Work place</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="contact" items="${listContact}">
            <tr>
                <td><input type="checkbox" name="checked" value="<c:out value="${contact.id}" />"/></td>
                <td><c:out value="${contact.name}" /> <c:out value="${contact.patronymic}" /> <c:out value="${contact.surname}" /></td>
                <td><c:out value="${contact.dateOfBirth}" /></td>
                <td><c:out value="${contact.country}" /> <c:out value="${contact.city}" /> <c:out value="${contact.address}" /></td>
                <td><c:out value="${contact.workPlace}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${contact.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${contact.id}' />">Delete</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/email?id=<c:out value='${contact.id}' />">Email</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <%--For displaying Previous link except for the 1st page --%>


    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
<c:if test="${currentPage != null}">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:if test="${currentPage != 1}">
                <td><a href="?page=${currentPage - 1}">Previous</a></td>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="?page=${currentPage + 1}">Next</a></td>
            </c:if>
        </tr>
    </table>
</c:if>

    <%--For displaying Next link --%>

</div>
</body>
</html>
