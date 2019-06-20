<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--
    Directives contain instructions to a container
    on how to translate JSP to servlet code.
    They are denoted with %@directive_name% in <>
    Attributes:
    1. contentType: Defines the type of the doc, followed by its character encoding.
    2. [?] session: Defines whether create a session (boolean).
    3. pageEncoding: Defines the character encoding for JSP page.
-->
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title><spring:message code="app.name" /></title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/style.css" />">
</head>
<body>
<!-- spring:message -->
<!-- Renders text with the specific key from a message source. -->
<h1><spring:message code="home.welcome" /></h1>

<button type="button" onclick="location.href='homepage?lang=ru'">рус</button>
<button type="button" onclick="location.href='homepage?lang=en'">eng</button>
<br>

<a href="<c:url value="/spittles" />"><spring:message code="home.recent_spittles" /></a> |
<a href="<c:url value="/spitter/register" />"><spring:message code="home.registration" /></a>

</body>
</html>
