<%--
  Created by IntelliJ IDEA.
  User: danil
  Date: 27.10.2016
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="/WEB-INF/resources/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  </head>
  <body>
  <spring:form method="post" modelAttribute="fromForm" action="outputForm">
    <spring:hidden path="id"/>
    First Name:<spring:input path="firstname"/> <br><br><br>
    Last Name:<spring:input path="lastname"/>   <br><br><br>
    Telephone:<spring:input path="telephone"/>  <br><br><br>
    Email:<spring:input path="email"/>          <br><br><br>
    <spring:button value="add">Say Hello!</spring:button>
  </spring:form>
  <div align="center">
    <h1>Contact List</h1>
    <table border="1" class="table">
      <th>Number</th>
      <th>FirstName</th>
      <th>LastName</th>
      <th>Telephone</th>
      <th>Email</th>
      <th>Action</th>
      <c:if test="${not empty contactsList}">
        <c:forEach var="contact" items="${contactsList}" varStatus = "status">
          <tr>
            <td>${status.index + 1}</td>
            <td>${contact.firstname}</td>
            <td>${contact.lastname}</td>
            <td>${contact.telephone}</td>
            <td>${contact.email}</td>
            <td>
                 <a href="/edit?id=${contact.id}">Edit</a>
                  &nbsp;&nbsp;&nbsp;
                 <a href="/delete?id=${contact.id}">Delete</a>
            </td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
  </div>
  </body>
</html>
