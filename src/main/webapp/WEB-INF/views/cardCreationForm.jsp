<%@ page contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

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

<mytags:overallBasePage>

    <h1><spring:message code="headline.card.create"/></h1>

    <spring_form:form method="POST" modelAttribute="cardDto">
        <!-- element attribute: Defines a HTML tag in which errors are rendered -->
        <spring_form:errors path="*" element="div" cssClass="errors"/>

        <spring:message code="card.attribute.lang"/><br>
        <spring_form:select path="lang" cssErrorClass="errors">
            <spring_form:option value="" selected="true"></spring_form:option>
            <spring_form:options/>
        </spring_form:select><br>

        <spring_form:label path="content" cssErrorClass="error">
            <spring:message code="card.attribute.content"/>
        </spring_form:label><br>
        <spring_form:input path="content" cssErrorClass="error"/><br>

        <spring:message code="card.attribute.type"/><br>
        <spring_form:select path="type" cssErrorClass="errors">
            <spring_form:option value="" selected="true"></spring_form:option>
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
        <spring_form:input path="samples[0].content"/><br>

        <input type="submit" value=<spring:message code="button.user.register"/>>

    </spring_form:form>

</mytags:overallBasePage>