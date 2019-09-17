<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<html>

<!-- ToDo: Set table borders' properties in CSS file -->

<mytags:overallBasePage pageHeadline="List of Cards">

    <div class="table-responsive">

        <table class="table m-0">

            <caption>
                <spring:message code="headline.card.read.all"/>
            </caption>

            <thead>

            <tr class="table-primary">

                <th scope="col">#</th>

                <th scope="col"><spring:message code="card.attribute.label.lang"/></th>
                <th scope="col"><spring:message code="card.attribute.label.content"/></th>
                <th scope="col"><spring:message code="card.attribute.label.type"/></th>
                <th scope="col"><spring:message code="card.attribute.label.definition"/></th>

                <security:authorize access="hasRole('ADMIN')">
                    <th scope="col" class="text-center" colspan="2"><spring:message code="headline.actions"/></th>
                </security:authorize>

            </tr>

            </thead>

            <tbody>

            <jstl:forEach items="${shortCardDtoList}" var="cardDto">
                <tr>
                    <th scope="row">
                        <a href="/cards/display/${cardDto.id}">${cardDto.id}</a>
                    </th>
                    <td>${cardDto.lang}</td>
                    <td>${cardDto.content}</td>
                    <td>${cardDto.type}</td>
                    <td>${cardDto.definition}</td>

                    <security:authorize access="hasRole('ADMIN')">

                        <td>
                            <div class="d-flex justify-content-center align-items-center">

                                <button class="btn btn-warning border-secondary mx-1"
                                        type="button"
                                        onclick="location.pathname = '/cards/update/${cardDto.id}'">
                                    <spring:message code="button.update"/>
                                </button>

                                <button class="btn btn-danger border-secondary mx-1"
                                        type="button"
                                        onclick="location.pathname = '/cards/delete/${cardDto.id}'">
                                    <spring:message code="button.delete"/>
                                </button>

                            </div>

                        </td>

                    </security:authorize>

                </tr>
            </jstl:forEach>

            </tbody>
        </table>

    </div>

</mytags:overallBasePage>