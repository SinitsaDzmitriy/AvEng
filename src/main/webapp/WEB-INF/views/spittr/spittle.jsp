<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title><spring:message code="app.name" /></title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="../resources/style.css" />">
</head>

<body>

<div>
    <spring:message code="spittle.message" />: <c:out value="${spittle.message}"/><br>
    <spring:message code="spittle.time" />: <c:out value="${spittle.time}"/><br>
    <spring:message code="spittle.longitude" />: <c:out value="${spittle.longitude}"/><br>
    <spring:message code="spittle.latitude" />: <c:out value="${spittle.latitude}"/><br>
</div>

</body>
</html>