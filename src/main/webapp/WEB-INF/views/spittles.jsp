<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Connect the spring tag lib -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page session="false" %>
<html>

<head>
    <title>Spittr</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="resources/style.css" />">
</head>

<body>

<!-- Creating a URL with a URI template variable -->
<!-- The URI template variable is indicated in 'value' attribute and marked by braces '{variableName}' -->
<!-- The braces and attribute name are replaced by the URL encoded value of a parameter. -->
<!-- It is defined with the spring:param tag in the body of the url tag. -->

<!-- 'var' attribute: [optional] The name of the variable to export the URL value to.-->
<!-- The default is the current application context path. -->

<!-- 'value' attribute: The URL to build -->

<!-- 'scope' attribute: [optional] The scope for the var. -->
<!-- Supported scopes: 'application', 'session', 'request', 'page'-->
<!-- Defaults to page scope. -->
<!-- It has no effect unless the var attribute is also defined. -->

<!-- Escape attributes: Big question -->

<spring:url var="homePageUrl" value="/{homeVarName}" scope="page" >

    <!-- Body of the URL tag -->
    <!-- 'name' attribute: URI template variable name -->
    <!-- 'value' attribute: URI template variable value -->

    <spring:param name="homeVarName" value="homepage" />

</spring:url>

<h1>List of spittles:</h1>

<c:forEach items="${spittleList}" var="spittle" >
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}" />
        </div>
        <div>
            <span class="spittleTime"><c:out value="${spittle.time}" /></span>
            <span class="spittleLocation">
(<c:out value="${spittle.latitude}" />,
<c:out value="${spittle.longitude}" />)</span>
        </div>
        <a href="<spring:url value="/spittle?id=${spittle.id}" />">Spittle page</a>
    </li>
</c:forEach>

<a href="<c:url value="/homepage" />">Home JSTL URL</a> <br>
<a href="${homePageUrl}">Home Spring URL (variable)</a> <br>
<a href="<spring:url value="/homepage" />">Home Spring URL (embedded)</a>

</body>
</html>