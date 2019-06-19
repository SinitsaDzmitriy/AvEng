<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<!--  -->
<!-- JSTL tag lib is redundant -->

<!--
Spring forms tag lib
Designed to:
1. Connect a form and an object in the model.
2. Communicate errors to the user.
-->

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>


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
    <title>Spittr</title>
</head>

<body>
<h1>Create Spittle Form</h1>


<sf:input path="firstName" cssErrorClass="error" /><br/>

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

</body>

</html>