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

<sf:form method="POST" commandName="spittle">
    Message:<br>
    <sf:input path="message"/><br>
    Time:<br>
    <sf:input path="time"/><br>
    Longitude:<br>
    <sf:input path="longitude"/><br>
    Latitude:<br>
    <sf:input path="latitude"/><br>
    <input type="submit" value="Create" />
</sf:form>

</body>

</html>