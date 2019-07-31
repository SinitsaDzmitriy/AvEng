<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<!-- -->
<!-- JSTL tag lib is redundant -->

<!--
Spring forms tag lib
Designed to:
1. Connect a form and an object in the model.
2. Communicate errors to the user.
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>

<style>
    input {
        margin-bottom: 2px;
    }

    div.errors {
        color: red;
        border: 2px solid red;
        background-color: #ffcccc;
    }

    label.error {
        color: red;
    }

    input.error {
        background-color: #ffcccc;
    }
</style>

<head>
    <title><spring:message code="app.name"/></title>
</head>

<body>
<h1><spring:message code="card.update.headline"/></h1>

<spring_form:form method="POST" modelAttribute="cardDto">
    <!-- element attribute: Defines a HTML tag in which errors are rendered -->
    <spring_form:errors path="*" element="div" cssClass="errors"/>
    <spring_form:label path="content" cssErrorClass="error">
        <spring:message code="card.attribute.content"/>
    </spring_form:label><br>
    <spring_form:input path="content" cssErrorClass="error"/><br>

    <spring:message code="card.attribute.type"/><br>
    <spring_form:select path="type" cssErrorClass="errors">
        <spring_form:options/>
    </spring_form:select><br>

    <spring_form:label path="pron.transcription" cssErrorClass="error">
        <spring:message code="card.attribute.transcription"/>
    </spring_form:label><br>
    <spring_form:input path="pron.transcription" cssErrorClass="error"/><br>

    <spring_form:label path="definition" cssErrorClass="error">
        <spring:message code="card.attribute.definition"/>
    </spring_form:label><br>
    <spring_form:input path="definition" cssErrorClass="error"/><br>

    <spring:message code="card.attribute.samples"/><br>

    <jstl:forEach items="${cardDto.samples}" varStatus="status">
        <spring_form:input path="samples[${status.index}].content"/><br>
    </jstl:forEach>

    <input type="submit" value=<spring:message code="button.update"/>>

</spring_form:form>

</body>

</html>