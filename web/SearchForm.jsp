<%--
  Created by IntelliJ IDEA.
  User: gunne
  Date: 27.03.2019
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        <%@include file='styles/search.css' %>
    </style>
    <title>Contacts Application</title>
    <meta charset="utf-8">
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
    <h1>Search</h1>
</div>
<div align="center">
        <form action="find">
            <table id="mainForm" border="1" cellpadding="5">
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Patronymic: </th>
                    <td>
                        <input type="text" name="patronymic" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Surname: </th>
                    <td>
                        <input type="text" name="surname" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date after: </th>
                    <td>

                        <input type="date" name="dateAfter"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date before: </th>
                    <td>

                        <input type="date" name="dateBefore"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Gender: </th>
                    <td>
                        <input type="text" name="gender" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Nationality: </th>
                    <td>
                        <input type="text" name="nationality" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Marital status: </th>
                    <td>
                        <input type="text" name="maritalStatus" size="45"
                        />
                    </td>
                </tr>
                    <th>Country: </th>
                    <td>
                        <input type="text" name="country" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>City: </th>
                    <td>
                        <input type="text" name="city" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Address: </th>
                    <td>
                        <input type="text" name="address" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Zip Code: </th>
                    <td>
                        <input type="text" name="zipCode" size="45"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input class="btn" type="submit" value="Find" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
