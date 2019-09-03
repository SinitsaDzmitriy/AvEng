<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring_security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<html>

<head>
    <meta charset="utf-8">

    <style>
        a.bold {
            font-weight: bold;
        }
    </style>

    <title>Demo</title>
</head>

<body>

<spring_security:authentication property="principal.id" var="currentUserId"/>

<a href="http://jquery.com/">jQuery</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<%--<spring:url value="/resources/js/test.js" var="testScript"/>--%>
<%--<script src="${testScript}"></script>--%>

<script>

    links = $("a");

    $(document).ready(function () {

        links.click(function(event) {

            event.preventDefault();

            if($(this).hasClass("bold")) {
                links.removeClass("bold");

            } else {
                links.addClass("bold");
            }

            $(this).hide("slow");

        });

    });

    // Your code goes here.

</script>

</body>

</html>