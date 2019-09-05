<%@ page pageEncoding="UTF-8" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

<div class="container-fluid d-flex h-100 flex-column">

    <header class="row border-bottom border-secondary">
        <div class="col-1 d-md-none d-flex justify-content-center align-items-center px-0">

            <div class="dropdown">

                <div class="media" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <spring:url value="/resources/images/menu.svg" var="menuIconPath"/>
                    <img src="${menuIconPath}" width="30" height="30" class="d-inline-block align-top" alt="">
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

                <button class="btn btn-outline-secondary dropdown-toggle"
                        type="button"
                        id="navbarLangDropdown"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Language
                </button>

                <div class="dropdown-menu mt-3" aria-labelledby="navbarDropdown">
                    <div class="dropdown-item" onclick="location.href='?lang=ru'">Русский</div>
                    <div class="dropdown-divider"></div>
                    <div class="dropdown-item" onclick="location.href='?lang=en'">English</div>
                </div>

            </div>

        </div>


        <div class="col px-0">

            <div class="d-none d-md-flex w-100">
                <div class="input-group mx-1 my-2">
                    <input class="form-control" type="search" placeholder="Search" aria-label="Search">

                    <div class="input-group-append">



                        <div class="btn-group btn-group-toggle input-group-append" data-toggle="buttons">

                            <select class="form-control">
                                <option disabled selected value>from</option>
                                <option>en</option>
                                <option>ru</option>
                                <option>de</option>
                            </select>

                            <div>
                                <button class="btn btn-outline-secondary rounded-right" type="button">Search</button>
                            </div>

                        </div>

                    </div>
                </div>

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
                                    <button class="btn btn-outline-secondary" type="button">Search</button>
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
                            <a href="${logoutLink}" class="btn btn-secondary text-nowrap">
                                <spring:message code="nav.logout"/>
                            </a>
                        </div>

                    </security:authorize>

                </div>

            </div>

    </header>

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

</body>

</html>