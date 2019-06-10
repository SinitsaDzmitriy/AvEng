<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--
    Directives contain instructions to a container
    on how to translate JSP to servlet code.
    They are denoted with %@directive_name% in <>
    Attributes:
    1. contentType: Defines the type of the doc, followed by its character encoding.
    2. [?] session: Defines whether create a session (boolean).
    3. pageEncoding: Defines the character encoding for JSP page.
-->
<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/style.css" />">
</head>
<body>
<h1>Welcome to Spittr</h1>

<a href="<c:url value="/spittles" />">Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register</a>

</body>
</html>
