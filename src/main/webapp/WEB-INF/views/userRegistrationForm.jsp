<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<!--  -->
<!-- JSTL tag lib is redundant -->

<!--
Spring forms tag lib
Designed to:
1. Connect a form and an object in the model.
2. Communicate errors to the user.
-->

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>

<style>
    input {
        margin-bottom: 2px;
    }
    div.errors {
        color: red;
        border: 2px solid red;
        background-color: #ffcccc;
    }
    label.error {
        color: red;
    }
    input.error {
        background-color: #ffcccc;
    }
</style>

<head>
    <title><spring:message code="app.name" /></title>
</head>

<body>
<h1><spring:message code="headline.user.register" /></h1>

<spring_form:form method="POST" modelAttribute="userRegCredentials" autocomplete="off">
    <!-- element attribute: Defines a HTML tag in which errors are rendered -->
    <spring_form:errors path="*" element="div" cssClass="errors" />
    <spring_form:label path="email" cssErrorClass="error"><spring:message code="user.enter.email" /></spring_form:label><br>
    <spring_form:input path="email" cssErrorClass="error"/><br>
    <spring_form:label path="password" cssErrorClass="error"><spring:message code="user.enter.password.primary" /></spring_form:label><br>
    <spring_form:password path="password" cssErrorClass="error"/><br>
    <spring_form:label path="retypedPassword" cssErrorClass="error"><spring:message code="user.enter.password.confirmatory" /></spring_form:label><br>
    <spring_form:password path="retypedPassword" cssErrorClass="error"/><br>
    <input type="submit" value=<spring:message code="button.user.register" /> />
</spring_form:form>

</body>

</html>