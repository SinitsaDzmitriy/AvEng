<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<!-- JSTL tag lib connection -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<html>

<!-- ToDo: Set table borders' properties in CSS file -->
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        margin-top: 10px;
        margin-bottom: 10px;
    }
</style>

<mytags:overallPageBase>
    <jsp:body>

        <h1><spring:message code="card.list.headline"/></h1>

        <table>
            <tr>
                <td>ID</td>
                <td><spring:message code="card.attribute.content"/></td>
                <td><spring:message code="card.attribute.type"/></td>
                <td><spring:message code="card.attribute.definition"/></td>
            </tr>
            <c:forEach items="${shortCardDtoList}" var="cardDto">
                <tr>
                    <td>
                        <a href="<c:url value="/card/read/${cardDto.id}" />">${cardDto.id}</a>
                    </td>
                    <td>${cardDto.content}</td>
                    <td>${cardDto.type}</td>
                    <td>${cardDto.definition}</td>
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
                </tr>
            </c:forEach>
        </table>

        <button type="button" onclick="location.href='create'">
            <spring:message code="button.create"/>
        </button>

    </jsp:body>
</mytags:overallPageBase>