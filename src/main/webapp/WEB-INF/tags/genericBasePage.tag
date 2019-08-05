<%@tag description="generic page base" pageEncoding="UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="baseHeader" fragment="true" %>
<%@attribute name="baseNav" fragment="true" %>
<%@attribute name="baseSidebar" fragment="true" %>
<%@attribute name="baseFooter" fragment="true" %>

<%@attribute name="appName" required="true"%>

<html>

<head>
    <title>${appName}</title>
</head>

<body>

<header>
    <jsp:invoke fragment="baseHeader"/>
</header>

<nav id="baseNav">
    <jsp:invoke fragment="baseNav"/>
</nav>

<aside id="sidebar">
    <jsp:invoke fragment="baseSidebar"/>
</aside>

<main id="pageContent">
    <jsp:doBody/>
</main>

<footer>
    <jsp:invoke fragment="baseFooter"/>
</footer>

</body>

</html>