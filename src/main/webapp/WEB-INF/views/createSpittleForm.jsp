<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<form method="POST">
    Message:<br>
    <input type="text" name="message" id="message"/><br>
    Time:<br>
    <input type="text" name="time" id="time"/><br>
    Longitude:<br>
    <input type="text" name="longitude" id="longitude"/><br>
    Latitude:<br>
    <input type="text" name="latitude" id="latitude"/><br>
    <input type="submit" value="Create" />
</form>

</body>

</html>