<%@page contentType="text/html; encoding=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<mytags:overallPageBase>

    <jsp:body>

        <h2>
            <spring:message code="card.read.headline" arguments="${cardDto.id}"/>
        </h2>

        <div>
            <spring:message code="card.attribute.content"/>:
            <jstl:out value="${cardDto.content}"/> <br>

            <spring:message code="card.attribute.type"/>:
            <jstl:out value="${cardDto.type}"/><br>

            <spring:message code="card.attribute.transcription"/>:
            [<jstl:out value="${cardDto.pron.transcription}"/>]<br>

            <spring:message code="card.attribute.definition"/>:
            <jstl:out value="${cardDto.definition}"/><br>

            <spring:message code="card.attribute.samples"/>:<br>
            <jstl:forEach items="${cardDto.samples}" var="sample">
                ${sample.content}<br>
            </jstl:forEach>
        </div>

        <button type="button"
                onclick="window.location.href='/card/${cardDto.id}/add/sample'">
            <spring:message code="button.add.sample"/>
        </button>

        <br>

        <button type="button"
                onclick="window.location.href='/card/list'">
            <spring:message code="button.return"/>
        </button>

    </jsp:body>

</mytags:overallPageBase>