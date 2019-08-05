<%@tag description="common page base" pageEncoding="UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<style>
    .info {
        margin: 15px;
    }

    header {
        height: 110px;
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        border-bottom-style: solid;
    }

    .sidebar {
        margin: 0;
        padding: 0;
        width: 200px;
        background-color: #f1f1f1;
        position: fixed;
        height: 100%;
        overflow: auto;
    }

    body {
        margin-top: 110px;
    }

    footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        border-top-style: solid;
    }
</style>

<spring:message code="app.name" var="appName"/>

<jsp:useBean id="langs" >

</jsp:useBean>

<mytags:genericBasePage appName="${appName}">

    <jsp:attribute name="baseHeader">

        <div class="inside">

            <h2>
                <spring:url value="/" var="initialPage"/>
                <a href=${initialPage}>
                    <spring:message code="app.name"/>
                </a>
            </h2>

            <label>
                <select>
                    <option>
                        <value>
                            <spring:message code="app.name"/>
                        </value>
                        <spring:url value="?lang=en" var="initialPage"/>
                        <a href=${initialPage}>

                        </a>
                    </option>
                    <option>
                        <spring:url value="?lang=ru" var="initialPage"/>
                        <a href=${initialPage}>
                            <spring:message code="app.name"/>
                        </a>
                    </option>
            </label>

            <form:select path="userType">
        <form:option value="" label="Chose Type"/>
        <form:options items="${types}" itemLabel="code"/>
</form:select>

            <h2>
                <spring:message code="menu.headline"/>
            </h2>

            <button type="button"
                    onclick="window.location.href='/'">
                <spring:message code="app.name"/>
            </button>

            <button type="button"
                    onclick="window.location.href='/card/list'">
                <spring:message code="button.cards"/>
            </button>

            <button type="button"
                    onclick="window.location.href='/card/create'">
                <spring:message code="button.create"/>
            </button>

            <button type="button"
                    onclick="window.location.href='/logout'">
                <spring:message code="button.logout"/>
            </button>

        </div>

	    <!-- Some overall logic here (navigation) -->
    </jsp:attribute>

    <jsp:attribute name="sidebar">

        <spring:url value="/card/list" var="cardListLink"/>
        <a href=${cardListLink}>
            <spring:message code="button.cards"/>
        </a>

        <spring:url value="/card/create" var="createCardLink"/>
        <a href=${createCardLink}>
            <spring:message code="button.create"/>
        </a>

    </jsp:attribute>

    <jsp:attribute name="footer">

        <div class="info">

            <!-- Some overall data here -->

            <p id="copyright">
                <spring:message code="footer.copyright"/>
            </p>

        </div>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>

</mytags:genericBasePage>