<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
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
    <title><spring:message code="app.name"/></title>
</head>
<body>

<!-- spring:message -->
<!-- Renders text with the specific key from a message source. -->

<button type="button" onclick="location.href='initial?lang=ru'">рус</button>
<button type="button" onclick="location.href='initial?lang=en'">eng</button>

<br>

<h1><spring:message code="initial.welcome"/></h1>

<button type="button" onclick="location.href='login'"><spring:message code="button.authorise"/></button>
<button type="button" onclick="location.href='create'"><spring:message code="button.register"/></button>
<button type="button" onclick="location.href='card/list'"><spring:message code="button.cards"/></button>

</body>
</html>
