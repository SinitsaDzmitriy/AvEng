<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<html>

<head>
    <title>Upload a file please</title>
</head>

<body>
<h1>Please upload a file</h1>

<form method="post" enctype="multipart/form-data">
    <input type="text" name="name"/>
    <input type="file" accept=".mp3" name="file"/>
    <input type="submit"/>
</form>

</body>

</html>