<%--
  Created by IntelliJ IDEA.
  User: gunne
  Date: 08.04.2019
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
        <%@include file='styles/email.css' %>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Send an e-mail</title>
    <script type="text/javascript">
        <%@include file="js/email.js"%>
    </script>
</head>
<body>
<form action="EmailSendingServlet" method="post">
    <table border="0" width="35%" align="center">
        <caption><h2>Send New E-mail</h2></caption>
        <tr>
            <td width="50%">Recipient address </td>
            <td><input type="text" name="recipient" size="50"
                       value="<c:out value='${emails}'/>" readonly
            />
                <input type="hidden" name="name" size="50"
                       value="<c:out value='${names}'/>"
                /></td>
        </tr>
        <tr>
            <td>Subject </td>
            <td><input type="text" id="subject" name="subject" size="50"/></td>
        </tr>
        <tr>
            <td>Template </td>
            <td>
                <select name="template" onchange="val()" id="select_id">
                    <option value="None">None</option>
                    <option value="Hello">Hello</option>
                    <option value="Meeting">Meeting</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Content </td>
            <td><textarea rows="10" cols="39" id="content" name="content"></textarea> </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" class="btn" value="Send"/></td>
        </tr>
    </table>

</form>
</body>
</html>
