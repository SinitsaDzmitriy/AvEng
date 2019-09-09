<%@ tag description="generic page base" pageEncoding="UTF-8" %>

<%@attribute name="pageHeadline" required="true" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ tag import="org.springframework.context.i18n.LocaleContextHolder" %>

<jsp:useBean id="date" class="java.util.Date"/>

<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/css/bootstrap-select.min.css">

    <style>
        .lang-select {
            width: 7em;
        }
    </style>

    <title>${pageHeadline}</title>
</head>

<body>

<div class="container-fluid d-flex h-100 flex-column">
    <!-- I want this container to stretch to the height of the parent -->
    <header class="row border-bottom border-secondary">
        <div class="col-1 d-md-none d-flex justify-content-center align-items-center px-0">

            <div class="dropdown">

                <div class="media" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <spring:url value="/resources/images/menu.svg" var="menuIconPath"/>
                    <img src="${menuIconPath}" width="30" height="30" class="d-inline-block align-top" alt="">
                </div>

                <div class="dropdown-menu mt-4" aria-labelledby="dropdownMenuButton">

                    <h5 class="dropdown-header">
                        <spring:message code="sidebar.header.public"/>
                    </h5>

                    <spring:url value="/" var="homePagePath"/>
                    <a class="dropdown-item" href="${homePagePath}">
                        <spring:message code="sidebar.anchor.homepage"/>
                    </a>

                    <security:authorize access="isFullyAuthenticated()">

                        <spring:url value="/user_cards/display" var="personalDictionaryPath"/>
                        <a class="dropdown-item" href="${personalDictionaryPath}">
                            <spring:message code="sidebar.anchor.user-cards"/>
                        </a>

                    </security:authorize>

                    <security:authorize access="hasRole('ADMIN')">

                        <div class="dropdown-divider"></div>

                        <h5 class="dropdown-header">
                            <spring:message code="sidebar.header.admin"/>
                        </h5>

                        <spring:url value="/cards/list" var="cardListPagePath"/>
                        <a class="dropdown-item" href="${cardListPagePath}">
                            <spring:message code="sidebar.anchor.cards"/>
                        </a>

                        <spring:url value="/cards/create" var="cardCreatePagePath"/>
                        <a class="dropdown-item" href="${cardCreatePagePath}">
                            <spring:message code="sidebar.anchor.cards.create"/>
                        </a>

                    </security:authorize>

                </div>

            </div>

        </div>

        <div class="col-1 col-md-2 text-center px-0">

            <spring:url value="/initial" var="initialPagePath"/>
            <a class="navbar-brand nav-link h-100 w-100 mx-0 my-1 py-2" href="${initialPagePath}">
                <div class="media d-flex justify-content-center">
                    <spring:url value="/resources/images/logo.svg" var="logoPath"/>
                    <img src="${logoPath}" width="32" height="30" class="d-inline-block align-top" alt="">
                    <div class="d-none d-md-flex">
                        <div class="media-body w-auto text-secondary">AvEng</div>
                    </div>
                </div>
            </a>

        </div>

        <div class="col-auto pl-4 pr-0">

            <div class="dropdown mx-1 my-2">

<%--                <button class="btn btn-outline-secondary dropdown-toggle"--%>
<%--                        type="button"--%>
<%--                        id="navbarLangDropdown"--%>
<%--                        data-toggle="dropdown"--%>
<%--                        aria-haspopup="true"--%>
<%--                        aria-expanded="false">--%>
<%--                    Language--%>
<%--                </button>--%>

                <select title="Language" id="siteLang" class="selectpicker">
                    <option>Русский</option>
                    <option>English</option>
                </select>

            </div>

        </div>

        <div class="col px-0">

            <form id="searchForm" class="d-none d-md-flex w-100 m-0" method="get" action="/cards/search" autocomplete="off">

                <div class="input-group mx-1 my-2">

                    <spring:message code="nav.search.placeholder" var="searchPlaceholder"/>
                    <input id="searchInput"
                           value=""
                           name="userInput"
                           class="form-control rounded-left border-secondary shadow-none"
                           type="search"
                           placeholder="${searchPlaceholder}"
                           aria-label="Search">

                    <div class="input-group-append">

                        <select id="sourceLangSelect"
                                name="usedLang"
                                class="lang-select form-control border-left-0 border-right-0 border-secondary shadow-none rounded-0">

                        </select>

                        <div class="d-flex align-items-center border border-secondary">
                            <spring:url value="/resources/images/swapLangsIcon.svg" var="swapLangsIconPath"/>
                            <img class="mx-1" src="${swapLangsIconPath}" width="30" height="30"
                                 alt="swapLangsIconPath">
                        </div>

                        <select id="destinationLangSelect"
                                name="desiredLang"
                                class="lang-select form-control border-left-0 border-right-0 border-secondary shadow-none rounded-0">

                        </select>

                        <button id="searchButton" class="btn btn-warning border-secondary shadow-none rounded-right"
                                type="submit">
                            <spring:message code="nav.search.button"/>
                        </button>

                    </div>

                </div>

            </form>

        </div>

        <div class="col-auto d-flex align-items-center position-static px-0">

            <div class="dropdown d-md-none position-static my-1 mx-1">

                <div class="media"
                     data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <spring:url value="/resources/images/search.svg" var="searchIconPath"/>
                    <img src="${searchIconPath}" width="30" height="30" class="d-inline-block align-top" alt="">
                </div>

                <div class="dropdown-menu w-75 mt-4 p-0" aria-labelledby="dLabel">
                    <div class="drop-item">
                        <div class="input-group">
                            <input class="form-control" type="search" placeholder="Search"
                                   aria-label="Search">
                            <div class="input-group-append">
                                <button class="btn btn-warning" type="button">Search</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-auto position-static pl-0 pr-4">

            <div class="nav justify-content-end mx-1 my-2">
                <div class="nav-item">

                </div>

                <security:authorize access="isAnonymous()">

                    <div class="nav-item">
                        <spring:url value="/login" var="loginLink"/>
                        <a href="${loginLink}" class="btn btn-light text-nowrap mx-1">
                            <spring:message code="nav.login"/>
                        </a>
                    </div>

                    <div class="nav-item">
                        <spring:url value="/register" var="registerLink"/>
                        <a href="${registerLink}" class="btn btn-primary text-nowrap mx-1">
                            <spring:message code="nav.register"/>
                        </a>
                    </div>

                </security:authorize>

                <security:authorize access="isFullyAuthenticated()">

                    <div class="nav-item">
                        <spring:url value="/logout" var="logoutLink"/>
                        <a href="${logoutLink}" class="btn btn-light text-primary text-nowrap">
                            <spring:message code="nav.logout"/>
                        </a>
                    </div>

                </security:authorize>

            </div>

        </div>

    </header>

    <div class="row flex-grow-1">

        <div class="col-2 d-none d-md-block border-right border-secondary h-100 px-0">

            <nav id="sidebar" class="nav flex-column mx-5 my-3">

                <div id="publicPanel">

                    <div class="nav-item d-flex align-items-center py-2">
                        <small class="text-secondary">
                            <spring:message code="sidebar.header.public"/>
                        </small>
                    </div>

                    <spring:url value="/" var="homePagePath"/>
                    <a class="nav-link px-4 py-1 text-secondary" href="${homePagePath}">
                        <spring:message code="sidebar.anchor.homepage"/>
                    </a>

                    <spring:url value="/display/list" var="displayCardsPagePath"/>
                    <a class="nav-link px-4 py-1 text-secondary" href="${displayCardsPagePath}">
                        <spring:message code="sidebar.anchor.cards"/>
                    </a>

                    <security:authorize access="isFullyAuthenticated()">

                        <spring:url value="/user_cards/display" var="personalDictionaryPath"/>
                        <a class="nav-link px-4 py-1 text-secondary" href="${personalDictionaryPath}">
                            <spring:message code="sidebar.anchor.user-cards"/>
                        </a>

                    </security:authorize>

                </div>

                <security:authorize access="hasRole('ADMIN')">

                    <div class="nav-item d-flex align-items-center py-2">
                        <small class="text-secondary">
                            <spring:message code="sidebar.header.admin"/>
                        </small>
                    </div>

                    <ul class="pl-4 text-secondary">

                        <li>
                            <b>
                                <spring:message code="sidebar.sub-header.users"/>
                            </b>
                        </li>

                        <a class="nav-link text-secondary px-0 py-1" href="...">
                            <span>list</span>
                        </a>


                        <li>
                            <b>
                                <spring:message code="sidebar.sub-header.cards"/>
                            </b>
                        </li>

                        <spring:url value="/cards/create" var="cardCreatePagePath"/>
                        <a class="nav-link text-secondary px-0 py-1" href="${cardCreatePagePath}">
                            <spring:message code="sidebar.anchor.cards.create"/>
                        </a>

                    </ul>

                </security:authorize>

            </nav>

        </div>

        <div class="col-12 col-md-10 p-0">
            <main class="p-3 h-100">
                <jsp:doBody/>
            </main>
        </div>

    </div>

    <footer class="row border-top border-secondary">
        <div class="column w-100 my-2 d-flex justify-content-center align-items-center">
            <p class="text-secondary m-0">
                <spring:message code="footer.copyright"/><fmt:formatDate value="${date}" pattern="yyyy"/>
            </p>
        </div>
    </footer>

</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/bootstrap-select.min.js"></script>

<script>

    $(document).ready(function () {

        var isSourceLangSelectChanged = false;
        var isDestinationLangSelectChanged = false;

        var supportedCardLangs =
            [
                "English",
                "Russian",
                "German"
            ];

        if (localStorage.getItem("lastUsedSourceLang") == null) {
            localStorage.setItem("lastUsedSourceLang",
                "${LocaleContextHolder.getLocale().getDisplayLanguage()}");
        }

        if (localStorage.getItem("lastUsedDestinationLang") == null) {
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

        $("#sourceLangSelect").change(function () {
            isSourceLangSelectChanged = true;
        });

        $("#destinationLangSelect").change(function () {
            isDestinationLangSelectChanged = true;
        });

        // $("#searchButton").click(function (event) {
        //
        //     console.log($("#searchInput").val);
        //     console.log("hello");
        //
        //     if($("#searchInput").val) {
        //
        //         if (isSourceLangSelectChanged) {
        //             localStorage.setItem("lastUsedSourceLang",
        //                 $("#sourceLangSelect").children("option:selected").val());
        //         }
        //
        //         if (isDestinationLangSelectChanged) {
        //             localStorage.setItem("lastUsedDestinationLang",
        //                 $("#destinationLangSelect").children("option:selected").val());
        //         }
        //
        //     } else {
        //         event.preventDefault();
        //     }
        //
        // });

        $("#searchForm").submit(function(event) {

            if($("#searchInput").val().trim()) {

                if (isSourceLangSelectChanged) {
                    localStorage.setItem("lastUsedSourceLang",
                        $("#sourceLangSelect").children("option:selected").val());
                }

                if (isDestinationLangSelectChanged) {
                    localStorage.setItem("lastUsedDestinationLang",
                        $("#destinationLangSelect").children("option:selected").val());
                }

            } else {
                event.preventDefault();
            }

        });

        $("#siteLang").change(function () {

            var searchParams = new URLSearchParams(location.search);
            var langParamVal;

            switch($(this).val()) {
                case "Русский":
                    langParamVal = "ru";
                    break;
                default:
                    langParamVal = "en";
            }

            searchParams.set("lang", langParamVal);
            location.search = searchParams.toString();
        });

    });

</script>

</body>

</html>