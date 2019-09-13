<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

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

<mytags:overallBasePage pageHeadline="Card Update Form">

    <h1><spring:message code="headline.card.update"/></h1>

    <spring_form:form method="POST" modelAttribute="cardDto" autocomplete="off">
        <!-- element attribute: Defines a HTML tag in which errors are rendered -->
        <spring_form:errors path="*" element="div" cssClass="errors"/>

        <spring:message code="card.attribute.lang"/><br>
        <spring_form:select path="lang" cssErrorClass="errors">
            <spring_form:options/>
        </spring_form:select><br>

        <spring_form:label path="content" cssErrorClass="error">
            <spring:message code="card.attribute.label.content"/>
        </spring_form:label><br>
        <spring_form:input path="content" cssErrorClass="error"/><br>

        <spring:message code="card.attribute.label.type"/><br>
        <spring_form:select path="type" cssErrorClass="errors">
            <spring_form:options/>
        </spring_form:select><br>

        <spring_form:label path="pron.transcription" cssErrorClass="error">
            <spring:message code="card.attribute.label.transcription"/>
        </spring_form:label><br>
        <spring_form:input path="pron.transcription" cssErrorClass="error"/><br>

        <spring_form:label path="definition" cssErrorClass="error">
            <spring:message code="card.attribute.label.definition"/>
        </spring_form:label><br>
        <spring_form:input path="definition" cssErrorClass="error"/><br>

        <spring:message code="card.attribute.label.samples"/><br>

        <jstl:forEach items="${cardDto.samples}" varStatus="status">
            <spring_form:input path="samples[${status.index}].content"/><br>
        </jstl:forEach>

        <input type="submit" value=<spring:message code="button.update"/>>

    </spring_form:form>

</mytags:overallBasePage>