<%@tag description="common page base" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .inside {
        margin: 10px;
    }

    #baseNav {
        top: 0;
        left: 0;
        height: 110px;
        width: 100%;
        position: fixed;
        border-bottom-style: solid;
    }

    #baseSidebar {
        top: 110px;
        left: 0;
        margin: 10px;
        width: 200px;
        height: 400px;
        background-color: #f1f1f1;
        position: fixed;
        overflow: auto;
    }

    #pageContent {
        top: 110px;
        left: 200px;
        margin: 10px;
        position: fixed;
    }

    #baseFooter {
        position: fixed;
        left: 0;
        bottom: 0;
        height: 50px;
        width: 100%;
        border-top-style: solid;
    }
</style>

<mytags:genericBasePage appName="aveng">

    <jsp:attribute name="baseNav">
        <div class="inside">

            <h1>
                <spring:url value="/" var="initialPage"/>
                <a href=${initialPage}>
                    <spring:message code="app.name"/>
                </a>
            </h1>

            <mytags:langSelect/>

            <security:authorize access="isAnonymous()">

            <spring:url value="/login" var="loginLink"/>
            <a href=${loginLink}>
                <spring:message code="nav.login"/>
            </a>
                &nbsp;
            <spring:url value="/register" var="registerLink"/>
            <a href=${registerLink}>
                <spring:message code="nav.register"/>
            </a>

            </security:authorize>

            <security:authorize access="hasRole('USER')">

            <spring:url value="/logout" var="logoutLink"/>
            <a href=${logoutLink}>
                <spring:message code="nav.logout"/>
            </a>

            </security:authorize>

        </div>
    </jsp:attribute>

    <jsp:attribute name="baseSidebar">
        <div class="inside">
            <!-- Some overall logic here (navigation) -->

            <h2>
                <spring:message code="headline.menu"/>
            </h2>

            <security:authorize access="hasRole('USER')">

            <spring:url value="/card/list" var="cardListLink"/>
            <a href=${cardListLink}>
                <spring:message code="menu.cards.list"/>
            </a>

            <br>

            </security:authorize>

            <security:authorize access="hasRole('ADMIN')">

                <spring:url value="/card/create" var="createCardLink"/>
                <a href=${createCardLink}>
                    <spring:message code="menu.cards.create"/>
                </a>

                <br>

            </security:authorize>

            <spring:url value="/" var="homepage"/>
            <a href=${homepage}>
                <spring:message code="menu.homepage"/>
            </a>

        </div>
    </jsp:attribute>

    <jsp:attribute name="baseFooter">
        <div class="inside">

            <!-- Some overall data here -->

            <p id="copyright">
                <spring:message code="footer.copyright"/>
            </p>

        </div>
    </jsp:attribute>

    <jsp:body>
        <div class="inside">
            <jsp:doBody/>
        </div>
    </jsp:body>

</mytags:genericBasePage>