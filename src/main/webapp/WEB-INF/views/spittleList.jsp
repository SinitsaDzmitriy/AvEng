<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<!-- JSTL tag lib connection -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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

<head>
    <title><spring:message code="app.name" /></title>
</head>

<body>

<h1><spring:message code="headline.spittle_list" /></h1>

<table>
    <tr>
        <td>ID</td>
        <td><spring:message code="spittle.message" /></td>
        <td><spring:message code="spittle.time" /></td>
        <td><spring:message code="spittle.longitude" /></td>
        <td><spring:message code="spittle.latitude" /></td>
    </tr>
    <c:forEach items="${spittleList}" var="spittle">
        <tr>
            <td>
                <a href="<c:url value="/spittles/${spittle.id}" />">${spittle.id}</a>
            </td>
            <td>${spittle.message}</td>
            <td>${spittle.time}</td>
            <td>${spittle.longitude}</td>
            <td>${spittle.latitude}</td>
            <td>
                <button type="button" onclick="location.href='spittles/update/${spittle.id}'">
                    <spring:message code="button.update" />
                </button>
            </td>
            <td>
                <button type="button" onclick="location.href='spittles/delete/${spittle.id}'">
                    <spring:message code="button.delete" />
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<button type="button" onclick="location.href='spittles/create'">
    <spring:message code="button.create" />
</button>

</body>

</html>
