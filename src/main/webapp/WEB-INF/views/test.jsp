<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<html>

<head>
    <meta charset="utf-8">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        a.bold {
            font-weight: bold;
        }
    </style>


    <title>Demo</title>
</head>

<body>

<div class="container">

    <ul class="list-group">

        <li class="input-group mb-3">

            <div class="form-control" contenteditable="true">
                Some text
            </div>

            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
            </div>

        </li>


        <li class="list-group-item">Morbi leo risus</li>
        <li class="list-group-item">Porta ac consectetur ac</li>
        <li class="list-group-item">Vestibulum at eros</li>
    </ul>

</div>

<div contenteditable="true">
    This text can be edited by the user.
</div>

<a href="http://jquery.com/">jQuery</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<%--<spring:url value="/resources/js/test.js" var="testScript"/>--%>
<%--<script src="${testScript}"></script>--%>

<script>


    links = $("a");

    $(document).ready(function () {

        links.click(function (event) {

            event.preventDefault();

            if ($(this).hasClass("bold")) {
                links.removeClass("bold");

            } else {
                links.addClass("bold");
            }

            $(this).hide("slow");

        });

    });

    // Your code goes here.

</script>

<!-- Optional JavaScript -->
<!-- then Popper.js, then Bootstrap JS -->

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>

<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>

</html>