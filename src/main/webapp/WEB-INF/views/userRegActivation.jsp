<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>

    <title>
        <spring:message code="title.users.reg.activation" arguments="${userEmail}"/>
    </title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>

<body style="background-color: #eee;">
<div class="container">

    <div class="row mt-3 d-flex justify-content-center">
        <h3 class="text-center">
            <spring:message code="users.reg.activation.message" arguments="${userEmail}"/>
        </h3>
    </div>

    <div class="row mt-3 d-flex justify-content-center">

        <button type="button" class="btn btn-primary mx-1 shadow-none" onclick="location.href = location.origin">
            <spring:message code="common.homepage"/>
        </button>

        <button type="button" class="btn btn-primary mx-1 shadow-none" onclick="location.href = location.origin + '/login'">
            <spring:message code="common.login"/>
        </button>

    </div>

</div>
</body>

</html>