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
</style>

<head>
    <title>Spittr</title>
</head>

<body>
<h1>Create Spittle Form</h1>

<sf: form method="POST" commandName="spittle">
    Message:<br>
    <sf:input path="text" name="message" id="message"/><br>
    Time:<br>
    <input type="text" name="time" id="time"/><br>
    Longitude:<br>
    <input type="text" name="longitude" id="longitude"/><br>
    Latitude:<br>
    <input type="text" name="latitude" id="latitude"/><br>
    <input type="submit" value="Create" />
</sf:form>

</body>

</html>