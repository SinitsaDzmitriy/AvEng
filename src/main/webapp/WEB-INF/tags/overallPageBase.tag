<%@tag description="common page base" pageEncoding="UTF-8" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<style>
    .info {
        margin: 15px;
    }

    header {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        background-color: #DCDCDC;
    }

    body {
        margin-top: 110px;
    }

    footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: #DCDCDC;
    }
</style>

<mytags:genericPageBase>

    <jsp:attribute name="header">

        <div class="info">

            <h2><spring:message code="headline.header"/></h2>

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

    <jsp:attribute name="footer">

        <div class="info">

            <!-- Some overall data here -->

            <p id="copyright">
                <spring:message code="footer.copyright"/>Copyright 1927, Future Bits When There Be Bits Inc.
            </p>

        </div>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>

</mytags:genericPageBase>