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
<h1><spring:message code="headline.form.create" /></h1>

<spring_form:form method="POST" commandName="spittleDTO">
    <!-- element attribute: Defines a HTML tag in which errors are rendered -->
    <spring_form:errors path="*" element="div" cssClass="errors" />
    <spring_form:label path="message" cssErrorClass="error"><spring:message code="spittle.message" /></spring_form:label>:<br>
    <spring_form:input path="message" cssErrorClass="error"/><br>
    <spring_form:label path="time" cssErrorClass="error"><spring:message code="spittle.time" /></spring_form:label>:<br>
    <spring_form:input path="time" cssErrorClass="error"/><br>
    <spring_form:label path="longitude" cssErrorClass="error"><spring:message code="spittle.longitude" /></spring_form:label>:<br>
    <spring_form:input path="longitude" cssErrorClass="error"/><br>
    <spring_form:label path="latitude" cssErrorClass="error"><spring:message code="spittle.latitude" /></spring_form:label>:<br>
    <spring_form:input path="latitude" cssErrorClass="error"/><br>
    <input type="submit" value=<spring:message code="button.create" /> />
</spring_form:form>

</body>

</html>