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
    <title>Contacts Application</title>
    <meta charset="utf-8">
</head>
<body>
<center>
    <h1>Contacts Management</h1>
    <h2>
        <a href="/new">Add New Contact</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Contacts</a>

    </h2>
</center>
<div align="center">
    <c:if test="${contact != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${contact == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${contact != null}">
                            Edit Contact
                        </c:if>
                        <c:if test="${contact == null}">
                            Add New Contact
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${contact != null}">
                    <input type="hidden" name="id" value="<c:out value='${contact.id}' />" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${contact.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Patronymic: </th>
                    <td>
                        <input type="text" name="patronymic" size="45"
                               value="<c:out value='${contact.patronymic}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Surname: </th>
                    <td>
                        <input type="text" name="surname" size="45"
                               value="<c:out value='${contact.surname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date of birth: </th>
                    <td>

                        <input type="date" name="dateOfBirth"
                               value="<c:out value='${contact.dateOfBirth}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Gender: </th>
                    <td>
                        <input type="text" name="gender" size="45"
                               value="<c:out value='${contact.gender}' />"
                        />
                    </td>
                </tr>
                <tr>
                <th>Nationality: </th>
                    <td>
                        <input type="text" name="nationality" size="45"
                           value="<c:out value='${contact.nationality}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Marital status: </th>
                    <td>
                        <input type="text" name="maritalStatus" size="45"
                               value="<c:out value='${contact.maritalStatus}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Web Site: </th>
                    <td>
                        <input type="text" name="webSite" size="45"
                               value="<c:out value='${contact.webSite}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value='${contact.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Work Place: </th>
                    <td>
                        <input type="text" name="workPlace" size="45"
                               value="<c:out value='${contact.workPlace}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Country: </th>
                    <td>
                        <input type="text" name="country" size="45"
                               value="<c:out value='${contact.country}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>City: </th>
                    <td>
                        <input type="text" name="city" size="45"
                               value="<c:out value='${contact.city}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Address: </th>
                    <td>
                        <input type="text" name="address" size="45"
                               value="<c:out value='${contact.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Zip Code: </th>
                    <td>
                        <input type="text" name="zipCode" size="45"
                               value="<c:out value='${contact.zipCode}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
