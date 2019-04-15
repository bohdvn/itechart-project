<%--
  Created by IntelliJ IDEA.
  User: gunne
  Date: 06.04.2019
  Time: 18:01
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
<form action="/updatePhoto" enctype='multipart/form-data' method="post">
    <input type="file" name="photo"/>
    <input type="submit" value="Save" />
    <c:if test="${contact != null}">
        <input type="hidden" name="id" value="<c:out value='${contact.id}' />" />
    </c:if>
    <c:if test="${image!=null}">
        <input type="hidden" name="image" value="<c:out value='${contact.base64Image}' />" />
    </c:if>
    <a href="/edit?id=<c:out value='${contact.id}' />">Cancel</a>
</form>
</body>
</html>

