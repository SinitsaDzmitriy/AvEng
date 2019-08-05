<%@tag description="generic page base" pageEncoding="UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>

<head>
    <title><spring:message code="app.name" /></title>
</head>

<body>

<header>
    <jsp:invoke fragment="header"/>
</header>

<div id="body">
    <jsp:doBody/>
</div>

<footer>
    <jsp:invoke fragment="footer"/>
</footer>

</body>

</html>