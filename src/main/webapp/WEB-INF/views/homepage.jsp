<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<spring:message code="app.name" var="pageTitle"/>
<mytags:overallBasePage pageTitle="${pageTitle}">
    <h1><spring:message code="initial.message"/></h1>
</mytags:overallBasePage>