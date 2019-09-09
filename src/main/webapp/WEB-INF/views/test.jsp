<%@ page pageEncoding="UTF-8" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>

<jsp:useBean id="date" class="java.util.Date"/>

<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title><spring:message code="app.name"/></title>
</head>

<body>


    <div class="container-fluid">

        <button id="searchButton" class="btn btn-primary">search</button>

        <select id="sourceLangSelect"
                name="usedLang"
                class="form-control my-2">
        </select>

        <select id="destinationLangSelect"
                name="desiredLang"
                class="form-control my-2">
        </select>

    </div>



<script
        src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous">
</script>


<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>

<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script>

    $(document).ready(function() {

        var isSourceLangSelectChanged = false;
        var isDestinationLangSelectChanged = false;

        var supportedCardLangs =
            [
                "English",
                "Russian",
                "German"
            ];

        if(localStorage.getItem("lastUsedSourceLang") == null) {
            localStorage.setItem("lastUsedSourceLang",
                "${LocaleContextHolder.getLocale().getDisplayLanguage()}");
        }

        if(localStorage.getItem("lastUsedDestinationLang") == null) {
            localStorage.setItem("lastUsedDestinationLang", "English");
        }

        // ToDo: create js func to avoid duplication

        supportedCardLangs.forEach(function (lang) {
            var htmlLangOption = "<option " + "id='langSourceOption" + lang + "'>"
                + lang + "</option>";
            $("#sourceLangSelect").append(htmlLangOption);
        });

        supportedCardLangs.forEach(function (lang) {
            var htmlLangOption = "<option " + "id='langDestinationOption" + lang + "'>"
                + lang + "</option>";
            $("#destinationLangSelect").append(htmlLangOption);
        });

        $("#langSourceOption" + localStorage.getItem("lastUsedSourceLang")).attr("selected", true);
        $("#langDestinationOption" + localStorage.getItem("lastUsedDestinationLang")).attr("selected", true);

        $("#sourceLangSelect").change(function() {
            isSourceLangSelectChanged = true;
        });

        $("#destinationLangSelect").change(function() {
            isDestinationLangSelectChanged = true;
        });

        $("#searchButton").click(function () {

            if (isSourceLangSelectChanged) {
                localStorage.setItem("lastUsedSourceLang",
                    $("#sourceLangSelect").children("option:selected").val());
            }

            if (isDestinationLangSelectChanged) {
                localStorage.setItem("lastUsedDestinationLang",
                    $("#destinationLangSelect").children("option:selected").val());
            }

        })

    });

</script>

</body>

</html>



<!--

// var idOfLangDestinationOptionToActivate = "#langSourceOption" + localStorage.getItem("lastUsedSourceLang");
// $(idOfLangSourceOptionToActivate).attr("selected", true);

// alert(localStorage.getItem("lastUsedSourceLang"));
// alert(localStorage.getItem("lastUsedDestinationLang"));



-->

