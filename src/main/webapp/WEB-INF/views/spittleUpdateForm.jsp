<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<style>
    input {
        margin-bottom: 2px;
    }
</style>

<head>
    <title><spring:message code="app.name" /></title>
</head>

<body>
<h1><spring:message code="headline.form.update" /></h1>

<spring_form:form method="POST" commandName="spittle">
    <spring:message code="spittle.message" />:<br>
    <spring_form:input path="message"/><br>
    <spring:message code="spittle.time" />:<br>
    <spring_form:input path="time"/><br>
    <spring:message code="spittle.longitude" />:<br>
    <spring_form:input path="longitude"/><br>
    <spring:message code="spittle.latitude" />:<br>
    <spring_form:input path="latitude"/><br>
    <input type="submit" value=<spring:message code="button.update" /> />
</spring_form:form>

</body>

</html>