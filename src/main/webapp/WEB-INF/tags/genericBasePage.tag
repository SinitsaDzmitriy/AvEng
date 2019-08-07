<%@tag description="generic page base" pageEncoding="UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="baseNav" fragment="true" %>
<%@attribute name="baseSidebar" fragment="true" %>
<%@attribute name="baseFooter" fragment="true" %>


<%@attribute name="appName" required="true"%>

<html>

<head>
    <title>${appName}</title>
</head>

<body>

<nav id="baseNav">
    <jsp:invoke fragment="baseNav"/>
</nav>

<aside id="baseSidebar">
    <jsp:invoke fragment="baseSidebar"/>
</aside>

<footer id="baseFooter">
    <jsp:invoke fragment="baseFooter"/>
</footer>

<div id="pageContent">
    <jsp:doBody/>
</div>

</body>

</html>