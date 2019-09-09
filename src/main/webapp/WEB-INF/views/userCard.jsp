<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="edu.sam.aveng.base.model.domain.enumeration.Status" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    .text-orangered {
        color: orangered;
    }
</style>

<mytags:overallBasePage pageHeadline="${userCardDto.card.content} UserCard">

    <div class="d-flex justify-content-center align-items-center">
        <div class="card" style="width: 30rem;">

            <div class="bg-primary d-flex justify-content-center">
                <spring:url value="/resources/images/samLogo.png" var="samLogo"/>
                <!-- ToDo: auto size img -->
                <img src="${samLogo}" class="m-2" width="114" height="67" alt="Card Image (default)">
            </div>

            <div class="card-body">

                <h5 class="card-title d-inline-block" id="cardContent">${userCardDto.card.content}</h5>
                <sup id="cardLang">${userCardDto.card.lang.name}</sup>

                <br>

                <i id="cardType">${userCardDto.card.type}</i>

                <h5 class="d-inline-block"> | </h5>

                <spring:url value="/resources/images/playPronIcon.svg" var="playPronIcon"/>
                <img src="${playPronIcon}" width="28" height="28" alt="playPronIcon">

                <span id="cardTranscription" class="text-orangered">[${userCardDto.card.pron.transcription}]</span>

                <br>

                <b id="cardDefinition">${userCardDto.card.definition}</b>

                <br>

                <h6 class="card-subtitle my-2">
                    <spring:message code="card.attribute.samples"/>:
                </h6>

                <ul class="list-group" id="cardSamples">

                    <jstl:forEach items="${userCardDto.card.samples}" var="sample">
                        <li class="list-group-item">${sample.content}</li>
                    </jstl:forEach>

                </ul>

                <h6 class="card-subtitle my-2">
                    <spring:message code="user-card.adding-form.attribute.additional-sample.caption"/>:
                </h6>

                <input id="userSample" name="updatedSample" class="list-group-item w-100 rounded" autocomplete="off"/>

                <br>

                <div id="cardStatus" class="btn-group d-flex">

                    <jstl:choose>

                        <jstl:when test="${userCardDto.status == Status.NEW}">

                            <button class="btn btn-secondary btn-sm" type="button" disabled>
                                new
                            </button>

                            <button class="btn btn-secondary btn-sm make-status-dubious" type="button">
                                dubious
                            </button>

                            <button class="btn btn-secondary btn-sm make-status-known" type="button">
                                known
                            </button>

                        </jstl:when>


                        <jstl:when test="${userCardDto.status == Status.DUBIOUS}">

                            <button class="btn btn-secondary btn-sm make-status-new" type="button">
                                new
                            </button>

                            <button class="btn btn-secondary btn-sm" type="button" disabled>
                                dubious
                            </button>

                            <button class="btn btn-secondary btn-sm make-status-known" type="button">
                                known
                            </button>

                        </jstl:when>


                        <jstl:when test="${userCardDto.status == Status.KNOWN}">

                            <button class="btn btn-secondary btn-sm make-status-new" type="button">
                                new
                            </button>

                            <button class="btn btn-secondary btn-sm make-status-dubious" type="button">
                                dubious
                            </button>

                            <button class="btn btn-secondary btn-sm" type="button" disabled>
                                known
                            </button>

                        </jstl:when>

                    </jstl:choose>

                </div>

                <br>

                <button id="deleteUserCard" class="btn btn-warning btn-block" type="button">Delete</button>

            </div>
        </div>
    </div>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous">
    </script>

    <script>

        $(document).ready(function () {

            var userSampleId = "#userSample";

            $(userSampleId).val("${userCardDto.userSample}");

            $(".make-status-new").click(function (event) {

                event.preventDefault();

                $.ajax({
                    type: "PATCH",
                    url: location.origin + "/api/user_cards/update/${userCardDto.id}/change-status?status=NEW",
                    contentType: "application/json"
                })
                    .done(function () {
                        location.reload();
                    })

                    .fail(function (xhr, status, errorThrown) {
                        alert("Error has been occurred!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    })
            });

            $(".make-status-dubious").click(function (event) {

                event.preventDefault();

                $.ajax({
                    type: "PATCH",
                    url: location.origin + "/api/user_cards/update/${userCardDto.id}/change-status?status=DUBIOUS",
                    contentType: "application/json"
                })
                    .done(function () {
                        location.reload();
                    })

                    .fail(function (xhr, status, errorThrown) {
                        alert("Error has been occurred!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    })
            });

            $(".make-status-known").click(function (event) {

                event.preventDefault();

                $.ajax({
                    type: "PATCH",
                    url: location.origin + "/api/user_cards/update/${userCardDto.id}/change-status?status=KNOWN",
                    contentType: "application/json"
                })
                    .done(function () {
                        location.reload();
                    })

                    .fail(function (xhr, status, errorThrown) {
                        alert("Error has been occurred!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    })
            });

            $("#deleteUserCard").click(function (event) {

                event.preventDefault();

                $.ajax({
                    type: "DELETE",
                    url: location.origin + "/api/user_cards/delete/${userCardDto.id}",
                    contentType: "application/json"
                })
                    .done(function () {
                        location.reload();
                    })

                    .fail(function (xhr, status, errorThrown) {
                        alert("Error has been occurred!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    })
            });

            $(userSampleId).change(function () {

                $.ajax({
                    type: "PATCH",
                    url: location.origin + "/api/user_cards/update/${userCardDto.id}/change-user-sample",
                    contentType: "application/json",
                    data: $(userSampleId).val()
                })
                    .fail(function (xhr, status, errorThrown) {
                        alert("Error has been occurred!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    })
            });

        });

    </script>

</mytags:overallBasePage>