<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <title><spring:message code="app.name" /></title>
</head>

<body>

<h2><spring:message code="card.read.headline" arguments="${cardDto.id}" /></h2>

<div>
    <spring:message code="card.attribute.content" />: <c:out value="${cardDto.content}"/><br>
    <spring:message code="card.attribute.type" />: <c:out value="${cardDto.type}"/><br>
    <spring:message code="card.attribute.transcription" />: [<c:out value="${cardDto.pron.transcription}"/>]<br>
    <spring:message code="card.attribute.definition" />: <c:out value="${cardDto.definition}"/><br>
    <spring:message code="card.attribute.samples" />:<br>
    <c:forEach items="${cardDto.samples}" var="sample">
        ${sample.content}<br>
    </c:forEach>

</div>

<button type="button" onclick="window.location.href='/card/${cardDto.id}/add/sample'"><spring:message code="button.add.sample"/></button>

<br>

<button type="button" onclick="window.location.href='/card/list'"><spring:message code="button.return"/></button>

</body>
</html>