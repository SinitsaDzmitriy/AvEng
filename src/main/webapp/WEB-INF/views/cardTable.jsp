<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<html>

<spring:message code="title.cards.display.table" var="pageTitle"/>
<mytags:overallBasePage pageTitle="${pageTitle}">

    <div class="table-responsive">

        <table class="table m-0">

            <caption>
                <spring:message code="cards.display.table.caption"/>
            </caption>

            <thead>

            <tr class="table-primary">

                <th scope="col">#</th>

                <th scope="col"><spring:message code="card.attribute.label.lang"/></th>
                <th scope="col"><spring:message code="card.attribute.label.content"/></th>
                <th scope="col"><spring:message code="card.attribute.label.type"/></th>
                <th scope="col"><spring:message code="card.attribute.label.definition"/></th>

                <security:authorize access="hasRole('ADMIN')">
                    <th scope="col" class="text-center" colspan="2"><spring:message code="cards.display.table.admin-actions"/></th>
                </security:authorize>

            </tr>

            </thead>

            <tbody>

            <jstl:forEach items="${cardTableItems}" var="cardDto">
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

                                <button class="btn btn-info shadow-none mx-1"
                                        type="button"
                                        onclick="location.href = location.origin + '/cards/update/${cardDto.id}'">
                                    <spring:message code="common.update"/>
                                </button>

                                <button class="btn btn-danger shadow-none mx-1"
                                        type="button"
                                        onclick="deleteCard('${cardDto.id}')">
                                    <spring:message code="common.delete"/>
                                </button>

                            </div>

                        </td>

                    </security:authorize>

                </tr>
            </jstl:forEach>

            </tbody>
        </table>

    </div>

    <button id="prevCardTablePartBtn" type="button" class="btn btn-secondary shadow-none">
        <spring:message code="common.previous"/>
    </button>

    <button id="nextCardTablePartBtn" type="button" class="btn btn-secondary shadow-none">
        <spring:message code="common.next"/>
    </button>

</mytags:overallBasePage>

<script>

    $(document).ready(function () {

        if(${isListFirst}){
            $("#prevCardTablePartBtn").attr("disabled", true);
        }

        if(${isListLast}){
            $("#nextCardTablePartBtn").attr("disabled", true);
        }

    });

    function deleteCard(cardId) {
        $.ajax({
            type: "DELETE",
            url: location.origin + "/api/cards/delete/" + cardId,
        })
            .done(function () {
                console.log("Successful deletion.");
                location.reload();
            })
            .fail(function (response) {
                console.log("Deletion failure.");
            })
    }

    $("#nextCardTablePartBtn").click(function () {

        var searchParams = new URLSearchParams(location.search);
        var currentPageNumber = searchParams.get('pageNum');

        if(currentPageNumber == null) {
            currentPageNumber = 1;
        }

        searchParams.set('pageNum', parseInt(currentPageNumber) + 1);

        location.search = searchParams.toString();

    });

    $("#prevCardTablePartBtn").click(function () {

        var searchParams = new URLSearchParams(location.search);
        searchParams.set('pageNum', parseInt(searchParams.get('pageNum')) - 1);

        location.search = searchParams.toString();

    });

</script>