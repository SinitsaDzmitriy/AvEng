<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<html>

<!-- ToDo: Set table borders' properties in CSS file -->
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .boldTableHeader {
        font-weight: bold;
    }
</style>

<mytags:overallBasePage>

    <h1><spring:message code="headline.card.read.all"/></h1>

    <table>
        <tr class="boldTableHeader">
            <td>ID</td>
            <td><spring:message code="card.attribute.content"/></td>
            <td><spring:message code="card.attribute.type"/></td>
            <td><spring:message code="card.attribute.definition"/></td>
            <td colspan="2"><spring:message code="headline.actions"/></td>
        </tr>
        <jstl:forEach items="${shortCardDtoList}" var="cardDto">
            <tr>
                <td>
                    <a href="<jstl:url value="/card/read/${cardDto.id}" />">${cardDto.id}</a>
                </td>
                <td>${cardDto.content}</td>
                <td>${cardDto.type}</td>
                <td>${cardDto.definition}</td>

                <security:authorize access="hasRole('ADMIN')">

                    <td>

                        <button type="button" onclick="location.href='update/${cardDto.id}'">
                            <spring:message code="button.update"/>
                        </button>

                    </td>

                    <td>
                        <button type="button" onclick="location.href='delete/${cardDto.id}'">
                            <spring:message code="button.delete"/>
                        </button>

                    </td>

                </security:authorize>

            </tr>
        </jstl:forEach>
    </table>

</mytags:overallBasePage>