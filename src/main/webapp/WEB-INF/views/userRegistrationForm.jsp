<%@ page contentType="text/html; UTF-8" session="false" pageEncoding="UTF-8" %>

<!-- -->
<!-- JSTL tag lib is redundant -->

<!--
Spring forms tag lib
Designed to:
1. Connect a form and an object in the model.
2. Communicate errors to the user.
-->

<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;

    }
</style>

<head>

    <title><spring:message code="title.user.registration"/></title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>

<body style="background-color: #eee;">

<div class="container">

    <spring_form:form method="POST"
                      modelAttribute="userRegCredentials"
                      autocomplete="off"
                      cssClass="m-auto"
                      cssStyle="max-width: 330px;">

        <h2><spring:message code="headline.user.registration"/></h2>

        <spring_form:errors path="*" element="div" cssClass="errors"/>

        <div class="mb-3">
            <spring:message code="user.attribute.placeholder.email" var="emailPlaceholder"/>
            <spring_form:input path="email"
                               placeholder="${emailPlaceholder}"
                               cssClass="form-control"
                               cssErrorClass="error"
                               cssStyle="padding: 10px; min-height: 42px;"/>
        </div>

        <div class="mb-3">
            <spring:message code="user.attribute.placeholder.password.primary" var="primaryPassPlaceholder"/>
            <spring_form:input path="password"
                               placeholder="${primaryPassPlaceholder}"
                               cssClass="form-control"
                               cssErrorClass="error"
                               cssStyle="padding: 10px; min-height: 42px;"/>
        </div>

        <div class="mb-3">
            <spring:message code="user.attribute.placeholder.password.confirmatory" var="confirmatoryPassPlaceholder"/>
            <spring_form:input path="retypedPassword"
                               placeholder="${confirmatoryPassPlaceholder}"
                               cssClass="form-control"
                               cssErrorClass="error"
                               cssStyle="padding: 10px; min-height: 42px;"/>
        </div>

        <spring:message code="button.user.register" var="registerBtnCaption"/>
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="${registerBtnCaption}"/>

    </spring_form:form>

</div>

</body>

<%--</html>--%>

<%--<html lang="en">--%>

<%--<body>--%>
<%--<div class="container">--%>
<%--    <form class="form-signin" method="post" action="/login">--%>
<%--        <h2 class="form-signin-heading">Please sign in</h2>--%>
<%--        <p>--%>
<%--            <label for="username" class="sr-only">Username</label>--%>
<%--            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required--%>
<%--                   autofocus>--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="password" class="sr-only">Password</label>--%>
<%--            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>--%>
<%--        </p>--%>
<%--        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>