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
        <%@include file='styles/contact.css' %>
    </style>
    <title>Contacts Application</title>
    <meta charset="utf-8">
    <script type="text/javascript">
        <%@include file="js/contact.js"%>
    </script>
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
    <form id="form" action="update" method="post" role="form">
        </c:if>
        <c:if test="${contact == null}">
        <form id="form" action="insert" method="post" role="form" enctype="multipart/form-data">
            </c:if>
            <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${contact != null}">
                        Edit Contact
                        <c:if test="${contact.base64Image != null}">
                            <br><img id="image" onclick="photoService.addPhoto()" src="data:image/jpg;base64,${contact.base64Image}" width="120" height="120"/>
                        </c:if>
                        <c:if test="${contact.base64Image == null}">
                            <br><img id="image" onclick="photoService.addPhoto()" src="https://img.icons8.com/ios/1600/user-male-circle-filled.png" width="120" height="120"/>
                        </c:if>
                    </c:if>
                    <c:if test="${contact == null}">
                        Add New Contact
                        <br><img id="image" onclick="photoService.addPhoto()" src="https://img.icons8.com/ios/1600/user-male-circle-filled.png" width="120" height="120"/>
                    </c:if>
                </h2>
            </caption>
            <c:if test="${contact != null}">
                <input type="hidden" name="id" value="<c:out value='${contact.id}' />" />
            </c:if>
                <input type="hidden" id="image64" name="image64" value="<c:out value='${contact.base64Image}' />" />


            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${contact.name}' />"
                           required
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
        </table>
        </form>
</div>


<!--Pop-up for photo -->
<div id="photoPopUp" style="display:none">
    <div class='tt'>
        <div class="container">
            <form id="avatar" class="form-horizontal" accept-charset="utf-8" role="form">
                <div style="margin: auto auto 2% auto">
                    <div class="close" onclick="photoService.cancelPhoto()">x</div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="photo">Update photo:</label>
                    <div class="col-sm-2">
                        <input  type="file" min="0" class="form-control" max="1000" id="photo"
                                name="photo" placeholder="Country code">
                    </div>
                </div>
            </form>

            <button  onclick="photoService.savePhoto()" class="btn btn-default">Save</button>
            <button onclick="photoService.cancelPhoto()" class="btn btn-default">Cancel</button>
        </div>
    </div>
</div>

<!--Pop-up for phones -->

<div id="phonePopUp" style="display:none">
    <div class='tt'>
        <div class="container">
            <form id="telephone" class="form-horizontal" accept-charset="utf-8" role="form">
                <div style="margin: auto auto 2% auto">
                    <div class="close" onclick="phoneService.cancelPhone()">x</div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="countryCode">Country code:</label>
                    <div class="col-sm-2">
                        <input  type="number" min="0" class="form-control" max="1000" id="countryCode"
                                name="countryCode" placeholder="Country code">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="operatorCode">Operator code:</label>
                    <div class="col-sm-2">
                        <input type="number" min="0" class="form-control" id="operatorCode" name="operatorCode"
                               placeholder="Operator code" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Number:</label>
                    <div class="col-sm-5">
                        <input type="number" min="0" class="form-control" id="number" name="number" placeholder="Number" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="type" class="control-label col-sm-2">Type:</label>
                    <div class="col-sm-5">
                        <select name="type" class="form-control" id="type">
                            <option>Home</option>
                            <option>Mobile</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="comment">Comment:</label>
                    <div class="col-sm-5">
                            <textarea  class="form-control" rows="5" name="comment" id="comment"
                                       maxlength="60"  placeholder="Type your comment..."></textarea>
                    </div>
                </div>
            </form>

            <button  onclick="phoneService.savePhone()" class="btn btn-default">Save</button>
            <button onclick="phoneService.cancelPhone()" class="btn btn-default">Cancel</button>
        </div>
    </div>
</div>

<!--Pop-up for attachments -->

<div id="attachPopUp" style="display:none">
    <div class='tt'>
        <div class="container">
            <form id="attach" class="form-horizontal" accept-charset="utf-8" role="form">
                <div style="margin: auto auto 2% auto">
                    <div class="close" onclick="attachService.cancelAttach()">x</div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="name">Attachment name:</label>
                    <div class="col-sm-2">
                        <input  type="text" class="form-control" id="name"
                                name="name" placeholder="Name" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="commentAtt">Comment:</label>
                    <div class="col-sm-5">
                            <textarea  class="form-control" rows="5" name="commentAtt" id="commentAtt"
                                       maxlength="60"  placeholder="Type your comment..."></textarea>
                    </div>
                </div>

            </form>

            <button  onclick="attachService.saveAttach()" class="btn btn-default">Save</button>
            <button onclick="attachService.cancelAttach()" class="btn btn-default">Cancel</button>
        </div>
    </div>
</div>



<!--Table of phones and buttons -->

<div class="panel panel-default">
<div style="float: right;">
    <button onclick="phoneService.addPhone()" class="btn btn-default">Add</button>
    <button onclick="phoneService.deletePhone()" class="btn btn-default">Delete</button>
    <button onclick="phoneService.editPhone()" class="btn btn-default">Edit</button>
</div>

    <div class="panel-heading">
        <h3 class="panel-title"><span class="glyphicon glyphicon-phone-alt"></span> Phones</h3>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>Full number</th>
            <th>Type</th>
            <th>Comment</th>
        </tr>
        </thead>
        <tbody id="phoneTable">
        <c:forEach items="${phones}" var="phone" varStatus="num">
            <tr>
                <td>
                    <input type='checkbox'  name='phones'/>
                </td>
                <td><input type='text' form='form' value="${ phone.getFullPhone()}" readonly/></td>
                <td><input type='text' form='form' name="type${ num.count - 1 }" value="${ phone.type}" readonly/></td>
                <td><input type='text' form='form' name="comment${ num.count - 1 }" value="${ phone.comment}" readonly/></td>
                <td><input type='hidden' form='form' name="countryCode${ num.count - 1}" value="${ phone.countryCode}" readonly/></td>
                <td><input type='hidden' form='form' name="operatorCode${ num.count - 1}" value="${ phone.operatorCode}" readonly/></td>
                <td><input type='hidden' form='form' name="number${ num.count - 1}" value="${ phone.number}" readonly/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<br>

<!--Table of attachments and buttons -->

<div class="panel panel-default">
    <div style="float: right;">
        <button onclick="attachService.addAttach()" class="btn btn-default">Add</button>
        <button onclick="attachService.deleteAttach()" class="btn btn-default">Delete</button>
        <button onclick="attachService.editAttach()" class="btn btn-default">Edit</button>
    </div>

    <div class="panel-heading">
        <h3 class="panel-title"><span class="glyphicon glyphicon-phone-alt"></span> Attachments</h3>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Comment</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody id="attachTable">
        <c:forEach items="${listAttachment}" var="attachment" varStatus="num">
            <tr>
                <td>
                    <input type='checkbox'  name='attachments'/>
                </td>
                <td><input type='text' form='form' name="name${ num.count - 1 }" value="${ attachment.name}" readonly/></td>
                <td><input type='text' form='form' name="commentAtt${ num.count - 1 }" value="${ attachment.comment}" readonly/></td>
                <td><input type='text' form='form' name="date${ num.count - 1}" value="${ attachment.date}" readonly/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<button form="form" type="submit" class="btn btn-default pull-left btn-lg">Save</button>
</body>
</html>
