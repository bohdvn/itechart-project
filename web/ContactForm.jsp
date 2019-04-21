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
    <c:if test="${contact != null}">
    <form id="form" action="update" method="post" role="form">
        </c:if>
        <c:if test="${contact == null}">
        <form id="form" action="insert" method="post" role="form" enctype="multipart/form-data">
            </c:if>
            <table id="mainForm" border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${contact != null}">
                        Edit Contact
                        <c:if test="${contact.base64Image != null}">
                            <br><img id="image" onclick="photoService.addPhoto()" src="${contact.base64Image}" width="120" height="120"/>
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
                    <%--<input type="text" name="gender" size="45"--%>
                           <%--value="<c:out value='${contact.gender}' />"--%>
                    <%--/>--%>
                        <select id="gender" name="gender">
                            <option value="<c:out value='${contact.gender}' />" selected="selected">${contact.gender}</option>
                                <option value="M">M</option>
                                <option value="F">F</option>
                        </select>
                </td>
            </tr>
            <tr>
                <th>Nationality: </th>
                <td>
                    <!--                     <input type="text" name="nationality" size="45"
                           value="<c:out value='${contact.nationality}' />"
                    /> -->
                    <select name="nationality">
                        <option value="<c:out value='${contact.nationality}' />" selected="selected">${contact.nationality}</option>
                        <option value="United States">United States</option>
                        <option value="United Kingdom">United Kingdom</option>
                        <option value="Afghanistan">Afghanistan</option>
                        <option value="Albania">Albania</option>
                        <option value="Algeria">Algeria</option>
                        <option value="American Samoa">American Samoa</option>
                        <option value="Andorra">Andorra</option>
                        <option value="Angola">Angola</option>
                        <option value="Anguilla">Anguilla</option>
                        <option value="Antarctica">Antarctica</option>
                        <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                        <option value="Argentina">Argentina</option>
                        <option value="Armenia">Armenia</option>
                        <option value="Aruba">Aruba</option>
                        <option value="Australia">Australia</option>
                        <option value="Austria">Austria</option>
                        <option value="Azerbaijan">Azerbaijan</option>
                        <option value="Bahamas">Bahamas</option>
                        <option value="Bahrain">Bahrain</option>
                        <option value="Bangladesh">Bangladesh</option>
                        <option value="Barbados">Barbados</option>
                        <option value="Belarus">Belarus</option>
                        <option value="Belgium">Belgium</option>
                        <option value="Belize">Belize</option>
                        <option value="Benin">Benin</option>
                        <option value="Bermuda">Bermuda</option>
                        <option value="Bhutan">Bhutan</option>
                        <option value="Bolivia">Bolivia</option>
                        <option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
                        <option value="Botswana">Botswana</option>
                        <option value="Bouvet Island">Bouvet Island</option>
                        <option value="Brazil">Brazil</option>
                        <option value="British Indian Ocean Territory">British Indian Ocean Territory</option>
                        <option value="Brunei Darussalam">Brunei Darussalam</option>
                        <option value="Bulgaria">Bulgaria</option>
                        <option value="Burkina Faso">Burkina Faso</option>
                        <option value="Burundi">Burundi</option>
                        <option value="Cambodia">Cambodia</option>
                        <option value="Cameroon">Cameroon</option>
                        <option value="Canada">Canada</option>
                        <option value="Cape Verde">Cape Verde</option>
                        <option value="Cayman Islands">Cayman Islands</option>
                        <option value="Central African Republic">Central African Republic</option>
                        <option value="Chad">Chad</option>
                        <option value="Chile">Chile</option>
                        <option value="China">China</option>
                        <option value="Christmas Island">Christmas Island</option>
                        <option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option>
                        <option value="Colombia">Colombia</option>
                        <option value="Comoros">Comoros</option>
                        <option value="Congo">Congo</option>
                        <option value="Congo, The Democratic Republic of The">Congo, The Democratic Republic of The</option>
                        <option value="Cook Islands">Cook Islands</option>
                        <option value="Costa Rica">Costa Rica</option>
                        <option value="Cote D'ivoire">Cote D'ivoire</option>
                        <option value="Croatia">Croatia</option>
                        <option value="Cuba">Cuba</option>
                        <option value="Cyprus">Cyprus</option>
                        <option value="Czech Republic">Czech Republic</option>
                        <option value="Denmark">Denmark</option>
                        <option value="Djibouti">Djibouti</option>
                        <option value="Dominica">Dominica</option>
                        <option value="Dominican Republic">Dominican Republic</option>
                        <option value="Ecuador">Ecuador</option>
                        <option value="Egypt">Egypt</option>
                        <option value="El Salvador">El Salvador</option>
                        <option value="Equatorial Guinea">Equatorial Guinea</option>
                        <option value="Eritrea">Eritrea</option>
                        <option value="Estonia">Estonia</option>
                        <option value="Ethiopia">Ethiopia</option>
                        <option value="Falkland Islands (Malvinas)">Falkland Islands (Malvinas)</option>
                        <option value="Faroe Islands">Faroe Islands</option>
                        <option value="Fiji">Fiji</option>
                        <option value="Finland">Finland</option>
                        <option value="France">France</option>
                        <option value="French Guiana">French Guiana</option>
                        <option value="French Polynesia">French Polynesia</option>
                        <option value="French Southern Territories">French Southern Territories</option>
                        <option value="Gabon">Gabon</option>
                        <option value="Gambia">Gambia</option>
                        <option value="Georgia">Georgia</option>
                        <option value="Germany">Germany</option>
                        <option value="Ghana">Ghana</option>
                        <option value="Gibraltar">Gibraltar</option>
                        <option value="Greece">Greece</option>
                        <option value="Greenland">Greenland</option>
                        <option value="Grenada">Grenada</option>
                        <option value="Guadeloupe">Guadeloupe</option>
                        <option value="Guam">Guam</option>
                        <option value="Guatemala">Guatemala</option>
                        <option value="Guinea">Guinea</option>
                        <option value="Guinea-bissau">Guinea-bissau</option>
                        <option value="Guyana">Guyana</option>
                        <option value="Haiti">Haiti</option>
                        <option value="Heard Island and Mcdonald Islands">Heard Island and Mcdonald Islands</option>
                        <option value="Holy See (Vatican City State)">Holy See (Vatican City State)</option>
                        <option value="Honduras">Honduras</option>
                        <option value="Hong Kong">Hong Kong</option>
                        <option value="Hungary">Hungary</option>
                        <option value="Iceland">Iceland</option>
                        <option value="India">India</option>
                        <option value="Indonesia">Indonesia</option>
                        <option value="Iran, Islamic Republic of">Iran, Islamic Republic of</option>
                        <option value="Iraq">Iraq</option>
                        <option value="Ireland">Ireland</option>
                        <option value="Israel">Israel</option>
                        <option value="Italy">Italy</option>
                        <option value="Jamaica">Jamaica</option>
                        <option value="Japan">Japan</option>
                        <option value="Jordan">Jordan</option>
                        <option value="Kazakhstan">Kazakhstan</option>
                        <option value="Kenya">Kenya</option>
                        <option value="Kiribati">Kiribati</option>
                        <option value="Korea, Democratic People's Republic of">Korea, Democratic People's Republic of</option>
                        <option value="Korea, Republic of">Korea, Republic of</option>
                        <option value="Kuwait">Kuwait</option>
                        <option value="Kyrgyzstan">Kyrgyzstan</option>
                        <option value="Lao People's Democratic Republic">Lao People's Democratic Republic</option>
                        <option value="Latvia">Latvia</option>
                        <option value="Lebanon">Lebanon</option>
                        <option value="Lesotho">Lesotho</option>
                        <option value="Liberia">Liberia</option>
                        <option value="Libyan Arab Jamahiriya">Libyan Arab Jamahiriya</option>
                        <option value="Liechtenstein">Liechtenstein</option>
                        <option value="Lithuania">Lithuania</option>
                        <option value="Luxembourg">Luxembourg</option>
                        <option value="Macao">Macao</option>
                        <option value="Macedonia, The Former Yugoslav Republic of">Macedonia, The Former Yugoslav Republic of</option>
                        <option value="Madagascar">Madagascar</option>
                        <option value="Malawi">Malawi</option>
                        <option value="Malaysia">Malaysia</option>
                        <option value="Maldives">Maldives</option>
                        <option value="Mali">Mali</option>
                        <option value="Malta">Malta</option>
                        <option value="Marshall Islands">Marshall Islands</option>
                        <option value="Martinique">Martinique</option>
                        <option value="Mauritania">Mauritania</option>
                        <option value="Mauritius">Mauritius</option>
                        <option value="Mayotte">Mayotte</option>
                        <option value="Mexico">Mexico</option>
                        <option value="Micronesia, Federated States of">Micronesia, Federated States of</option>
                        <option value="Moldova, Republic of">Moldova, Republic of</option>
                        <option value="Monaco">Monaco</option>
                        <option value="Mongolia">Mongolia</option>
                        <option value="Montserrat">Montserrat</option>
                        <option value="Morocco">Morocco</option>
                        <option value="Mozambique">Mozambique</option>
                        <option value="Myanmar">Myanmar</option>
                        <option value="Namibia">Namibia</option>
                        <option value="Nauru">Nauru</option>
                        <option value="Nepal">Nepal</option>
                        <option value="Netherlands">Netherlands</option>
                        <option value="Netherlands Antilles">Netherlands Antilles</option>
                        <option value="New Caledonia">New Caledonia</option>
                        <option value="New Zealand">New Zealand</option>
                        <option value="Nicaragua">Nicaragua</option>
                        <option value="Niger">Niger</option>
                        <option value="Nigeria">Nigeria</option>
                        <option value="Niue">Niue</option>
                        <option value="Norfolk Island">Norfolk Island</option>
                        <option value="Northern Mariana Islands">Northern Mariana Islands</option>
                        <option value="Norway">Norway</option>
                        <option value="Oman">Oman</option>
                        <option value="Pakistan">Pakistan</option>
                        <option value="Palau">Palau</option>
                        <option value="Palestinian Territory, Occupied">Palestinian Territory, Occupied</option>
                        <option value="Panama">Panama</option>
                        <option value="Papua New Guinea">Papua New Guinea</option>
                        <option value="Paraguay">Paraguay</option>
                        <option value="Peru">Peru</option>
                        <option value="Philippines">Philippines</option>
                        <option value="Pitcairn">Pitcairn</option>
                        <option value="Poland">Poland</option>
                        <option value="Portugal">Portugal</option>
                        <option value="Puerto Rico">Puerto Rico</option>
                        <option value="Qatar">Qatar</option>
                        <option value="Reunion">Reunion</option>
                        <option value="Romania">Romania</option>
                        <option value="Russian Federation">Russian Federation</option>
                        <option value="Rwanda">Rwanda</option>
                        <option value="Saint Helena">Saint Helena</option>
                        <option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
                        <option value="Saint Lucia">Saint Lucia</option>
                        <option value="Saint Pierre and Miquelon">Saint Pierre and Miquelon</option>
                        <option value="Saint Vincent and The Grenadines">Saint Vincent and The Grenadines</option>
                        <option value="Samoa">Samoa</option>
                        <option value="San Marino">San Marino</option>
                        <option value="Sao Tome and Principe">Sao Tome and Principe</option>
                        <option value="Saudi Arabia">Saudi Arabia</option>
                        <option value="Senegal">Senegal</option>
                        <option value="Serbia and Montenegro">Serbia and Montenegro</option>
                        <option value="Seychelles">Seychelles</option>
                        <option value="Sierra Leone">Sierra Leone</option>
                        <option value="Singapore">Singapore</option>
                        <option value="Slovakia">Slovakia</option>
                        <option value="Slovenia">Slovenia</option>
                        <option value="Solomon Islands">Solomon Islands</option>
                        <option value="Somalia">Somalia</option>
                        <option value="South Africa">South Africa</option>
                        <option value="South Georgia and The South Sandwich Islands">South Georgia and The South Sandwich Islands</option>
                        <option value="Spain">Spain</option>
                        <option value="Sri Lanka">Sri Lanka</option>
                        <option value="Sudan">Sudan</option>
                        <option value="Suriname">Suriname</option>
                        <option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen</option>
                        <option value="Swaziland">Swaziland</option>
                        <option value="Sweden">Sweden</option>
                        <option value="Switzerland">Switzerland</option>
                        <option value="Syrian Arab Republic">Syrian Arab Republic</option>
                        <option value="Taiwan, Province of China">Taiwan, Province of China</option>
                        <option value="Tajikistan">Tajikistan</option>
                        <option value="Tanzania, United Republic of">Tanzania, United Republic of</option>
                        <option value="Thailand">Thailand</option>
                        <option value="Timor-leste">Timor-leste</option>
                        <option value="Togo">Togo</option>
                        <option value="Tokelau">Tokelau</option>
                        <option value="Tonga">Tonga</option>
                        <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                        <option value="Tunisia">Tunisia</option>
                        <option value="Turkey">Turkey</option>
                        <option value="Turkmenistan">Turkmenistan</option>
                        <option value="Turks and Caicos Islands">Turks and Caicos Islands</option>
                        <option value="Tuvalu">Tuvalu</option>
                        <option value="Uganda">Uganda</option>
                        <option value="Ukraine">Ukraine</option>
                        <option value="United Arab Emirates">United Arab Emirates</option>
                        <option value="United Kingdom">United Kingdom</option>
                        <option value="United States">United States</option>
                        <option value="United States Minor Outlying Islands">United States Minor Outlying Islands</option>
                        <option value="Uruguay">Uruguay</option>
                        <option value="Uzbekistan">Uzbekistan</option>
                        <option value="Vanuatu">Vanuatu</option>
                        <option value="Venezuela">Venezuela</option>
                        <option value="Viet Nam">Viet Nam</option>
                        <option value="Virgin Islands, British">Virgin Islands, British</option>
                        <option value="Virgin Islands, U.S.">Virgin Islands, U.S.</option>
                        <option value="Wallis and Futuna">Wallis and Futuna</option>
                        <option value="Western Sahara">Western Sahara</option>
                        <option value="Yemen">Yemen</option>
                        <option value="Zambia">Zambia</option>
                        <option value="Zimbabwe">Zimbabwe</option>
                    </select>
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
                    <input type="email" name="email" size="45"
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
                        <input  type="file" min="0" class="form-control" id="photo"
                                name="photo" accept="image/jpeg,image/png,image/gif">
                    </div>
                </div>
            </form>

            <button  onclick="photoService.savePhoto()" class="btn">Save</button>
            <button onclick="photoService.cancelPhoto()" class="btn">Cancel</button>
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

            <button  onclick="phoneService.savePhone()" class="btn">Save</button>
            <button onclick="phoneService.cancelPhone()" class="btn">Cancel</button>
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
                    <label class="control-label col-sm-2" for="photo">File:</label>
                    <div class="col-sm-2">
                        <input  type="file" min="0" class="form-control" id="file"
                                name="file" onchange="encodeFile()">
                        <input type="hidden" id="file64"/>
                    </div>
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

            <button  onclick="attachService.saveAttach()" class="btn">Save</button>
            <button onclick="attachService.cancelAttach()" class="btn">Cancel</button>
        </div>
    </div>
</div>



<!--Table of phones and buttons -->

<div class="panel-default">
<div style="float: right;">
    <button onclick="phoneService.addPhone()" class="btn">Add</button>
    <button onclick="phoneService.deletePhone()" class="btn">Delete</button>
    <button onclick="phoneService.editPhone()" class="btn">Edit</button>
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

<div class="panel-default">
    <div style="float: right;">
        <button onclick="attachService.addAttach()" class="btn">Add</button>
        <button onclick="attachService.deleteAttach()" class="btn">Delete</button>
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
                <td><a id="file64a" href="${ attachment.base64File}" target="_blank"><input type='text' form='form' name="name${ num.count - 1 }" value="${ attachment.name}" readonly/></a></td>
                <td><input type='text' form='form' name="commentAtt${ num.count - 1 }" value="${ attachment.comment}" readonly/></td>
                <td><input type='text' form='form' name="date${ num.count - 1}" value="${ attachment.date}" readonly/></td>
                <td><input type='hidden' form='form' name="base64File${ num.count - 1}" value="${ attachment.base64File}" readonly/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<button form="form" type="submit" class="btn" id="saveBtn">Save</button>
</body>
</html>
