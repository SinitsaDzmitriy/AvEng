<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<html>

<style>
    .error {
        color: red;
    }
</style>

<head>
    <title>Upload a file please</title>
</head>

<body>
<h1>Please upload a file</h1>

<form method="post" enctype="multipart/form-data">
    <input type="file" accept=".mp3" name="file"/>
    <input type="submit"/>
</form>

</body>

</html>