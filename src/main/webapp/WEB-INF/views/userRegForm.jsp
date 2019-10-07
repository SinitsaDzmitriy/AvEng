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
        background-color: #eee !important;
    }
</style>

<head>

    <title><spring:message code="title.users.reg.form"/></title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

</head>

<body>

<div class="container">

    <spring_form:form id="userRegistrationForm"
                      method="POST"
                      modelAttribute="userCredentials"
                      autocomplete="off"
                      cssClass="m-auto"
                      cssStyle="max-width: 330px;">

        <h2><spring:message code="users.reg.form.headline"/></h2>

        <div class="mb-3">
            <spring:message code="user.attribute.placeholder.email" var="emailPlaceholder"/>
            <spring_form:input id="email"
                               type="email"
                               path="email"
                               placeholder="${emailPlaceholder}"
                               cssClass="form-control"
                               cssErrorClass="form-control is-invalid"
                               cssStyle="padding: 10px; min-height: 42px;"
                               required="required"/>

            <div class="invalid-feedback">
                <spring_form:errors path="email"/>
            </div>

        </div>

        <div class="mb-3">
            <spring:message code="user.attribute.placeholder.password.primary" var="primaryPassPlaceholder"/>
            <spring:message code="validation.front.password.pattern" var="passPatternMismatchValidationHint"/>
            <spring_form:input id="password"
                               type="password"
                               path="password"
                               pattern="(?=.*\d)(?=.*[a-z]).{8,}"
                               title="${passPatternMismatchValidationHint}"
                               placeholder="${primaryPassPlaceholder}"
                               cssClass="form-control"
                               cssErrorClass="form-control is-invalid"
                               cssStyle="padding: 10px; min-height: 42px;"
                               required="required"/>

            <div class="invalid-feedback">
                <spring_form:errors path="password"/>
            </div>

        </div>

        <div class="mb-3">
            <spring:message code="user.attribute.placeholder.password.confirmatory" var="confirmatoryPassPlaceholder"/>
            <spring:message code="validation.front.password.mismatch" var="retypedPassMismatchValidationHint"/>
            <input id="retypedPassword"
                   type="password"
                   class="form-control"
                   style="padding: 10px; min-height: 42px;"
                   placeholder="${confirmatoryPassPlaceholder}"
                   required="required"/>
        </div>

        <spring:message code="users.reg.form.button" var="registerBtnCaption"/>
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="${registerBtnCaption}"/>

    </spring_form:form>

</div>

<script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous">
</script>

<script>
    $("#userRegistrationForm").submit(function(event) {

        var emailInput = document.getElementById("email");
        var passwordInput = document.getElementById("password");
        var retypedPasswordInput = document.getElementById("retypedPassword");

        if (emailInput.checkValidity() && passwordInput.checkValidity()) {

            if (passwordInput.value === retypedPasswordInput.value) {
                // input is valid -- reset the error message
                retypedPasswordInput.setCustomValidity('');
            } else {
                retypedPasswordInput.setCustomValidity("${retypedPassMismatchValidationHint}");
                retypedPasswordInput.reportValidity();
                event.preventDefault()
            }

        }
    });

   $("#retypedPassword").change(function () {
        document.getElementById("retypedPassword").setCustomValidity('');
        retypedPasswordInput.reportValidity();
    });

</script>

</body>