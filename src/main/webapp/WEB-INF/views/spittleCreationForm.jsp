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
    div.error {
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

<sf:form method="POST" commandName="spittleDTO">
    <!-- element attribute: Defines a HTML tag in which errors are rendered -->
    <sf:errors path="*" element="div" cssClass="errors" />
    <sf:label path="message" cssErrorClass="error">Message</sf:label>:<br>
    <sf:input path="message" cssErrorClass="error" /><br>
    <sf:label path="time" cssErrorClass="error">Time</sf:label>:<br>
    <sf:input path="time" cssErrorClass="error" /><br>
    <sf:label path="longitude" cssErrorClass="error">Longitude</sf:label>:<br>
    <sf:input path="longitude" cssErrorClass="error" /><br>
    <sf:label path="latitude" cssErrorClass="error">Latitude</sf:label>:<br>
    <sf:input path="latitude" cssErrorClass="error"/><br>
    <input type="submit" value="Create" />
</sf:form>

<spring_form:form method="POST" commandName="spittle">
    <!-- element attribute: Defines a HTML tag in which errors are rendered -->
    <sf:errors path="*" element="div" cssClass="errors" />
    <spring:message path="message" cssErrorClass="error" code="spittle.message" />:<br>
    <spring_form:input path="message" cssErrorClass="error"/><br>
    <spring:message path="time" cssErrorClass="error" code="spittle.time" />:<br>
    <spring_form:input path="time" cssErrorClass="error"/><br>
    <spring:message path="longitude" cssErrorClass="error" code="spittle.longitude" />:<br>
    <spring_form:input path="longitude" cssErrorClass="error"/><br>
    <spring:message path="latitude" cssErrorClass="error" code="spittle.latitude" />:<br>
    <spring_form:input path="latitude" cssErrorClass="error"/><br>
    <input type="submit" value=<spring:message code="button.create" /> />
</spring_form:form>

</body>

</html>