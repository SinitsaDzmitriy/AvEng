<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

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
<h1>Edit Spittle Form</h1>

<sf:form method="POST" commandName="spittle">
    Message:<br>
    <sf:input path="message"/><br>
    Time:<br>
    <sf:input path="time"/><br>
    Longitude:<br>
    <sf:input path="longitude"/><br>
    Latitude:<br>
    <sf:input path="latitude"/><br>
    <input type="submit" value="Update" />
</sf:form>

</body>

</html>