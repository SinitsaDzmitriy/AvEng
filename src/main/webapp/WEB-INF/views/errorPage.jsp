<%@ page contentType="text/html; chatset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

<html>

<head>

    <title>
        <spring:message code="title.http-error" arguments="${httpErrorCode}"/>
    </title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Yanone+Kaffeesatz&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab|Yanone+Kaffeesatz&display=swap" rel="stylesheet">

    <!-- Custom stylesheet -->
    <spring:url value="/resources/styles/errorPageStyle.css" var="errorPageStyleUrl" />
    <link rel="stylesheet" href="${errorPageStyleUrl}" />

    <!-- Bootstrap stylesheet -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" crossorigin="anonymous">

</head>

<body>

<div id="error-wrapper">
    <div class="error-content">

        <div class="error-status-code">
            <div></div>
            <h1>${httpErrorCode}</h1>
        </div>

        <h2>
            <spring:message code="http-error.message.headline.${httpErrorCode}"/>
        </h2>

        <p>
            <spring:message code="http-error.message.content.${httpErrorCode}"/>
        </p>

        <spring:url value="/" var="homePagePath"/>
        <a class="btn btn-secondary border rounded-pill shadow-none" href="${homePagePath}">
            <spring:message code="common.caption.homepage"/>
        </a>

    </div>
</div>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</body>

</html>