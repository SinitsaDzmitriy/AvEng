<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring_security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<mytags:overallBasePage pageHeadline="${cardDto.content} Card">

    <div class="d-flex justify-content-center align-items-center">
        <div class="card" style="width: 23rem;">

            <h5 class="card-header">
                <spring:message code="headline.card.read" arguments="${cardDto.id}"/>
            </h5>

            <div class="bg-primary d-flex justify-content-center">
                <spring:url value="/resources/images/samLogo.png" var="samLogo"/>
                <!-- ToDo: auto size img -->
                <img src="${samLogo}" class="m-2" width="114" height="67" alt="Card Image (default)">
            </div>

            <div class="card-body">

                <h5 class="card-title d-inline-block" id="cardContent">${cardDto.content}</h5>
                <sup id="cardLang">${cardDto.lang.name}</sup>

                <br>

                <i id="cardType">${cardDto.type}</i>

                <h5 class="d-inline-block"> | </h5>


                <spring:url value="/resources/images/playPronIcon.svg" var="playPronIcon"/>

                <button id="playPronBtn"
                        class="use-existent-sample-btn btn btn-link shadow-none p-0"
                        type="button">

                    <img src="${playPronIcon}" width="28" height="28" alt="playPronIcon">
                </button>

                <span id="cardTranscription" class="text-orangered">[${cardDto.pron.transcription}]</span>

                <br>

                <b id="cardDefinition">${cardDto.definition}</b>

                <br>

                <h6 class="card-subtitle my-2">
                    <spring:message code="card.attribute.label.samples"/>:
                </h6>

                <ul class="list-group" id="cardSamples">

                    <jstl:forEach items="${cardDto.samples}" var="sample">
                        <li class="list-group-item">${sample.content}</li>
                    </jstl:forEach>

                </ul>

                <spring_security:authorize access="isFullyAuthenticated()">

                    <br>

                    <button type="button"
                            class="btn btn-primary btn-block border-secondary"
                            data-toggle="modal"
                            data-target="#userCardCreationModal">
                        <spring:message code="button.card.add-to-dictionary"/>
                    </button>

                </spring_security:authorize>

            </div>

        </div>

        <spring_security:authorize access="isFullyAuthenticated()">

            <div class="modal fade"
                 id="userCardCreationModal"
                 tabindex="-1"
                 role="dialog"
                 aria-labelledby="userCardCreationFormLabel"
                 aria-hidden="true">

                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="userCardCreationFormLabel">
                                <spring:message code="user-card.adding-form.headline"/>
                            </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="modal-body">

                            <form id="userCardCreationForm" autocomplete="off">

                                <div class="form-group">

                                    <label for="userCardStatusSelect">
                                        <spring:message code="user-card.adding-form.attribute.status.caption"/>
                                    </label>
                                    <div id="userCardStatusSelect">
                                        <mytags:statusSelect/>
                                    </div>

                                </div>

                                <div class="form-group">

                                    <label for="additionalSample">
                                        <spring:message
                                                code="user-card.adding-form.attribute.additional-sample.caption"/>
                                    </label>

                                    <spring:message var="additionalSamplePlaceholder"
                                                    code="common.placeholder.input"/>
                                    <input id="additionalSample"
                                           class="form-control"
                                           name="userSample"
                                           type="text"
                                           aria-describedby="emailHelp"
                                           placeholder="${additionalSamplePlaceholder}">

                                    <small id="emailHelp" class="form-text text-muted">
                                        <spring:message
                                                code="user-card.adding-form.attribute.additional-sample.recommendation.intro"/>
                                        <ul>
                                            <li><spring:message
                                                    code="user-card.adding-form.attribute.additional-sample.recommendation.source"/></li>
                                            <li><spring:message
                                                    code="user-card.adding-form.attribute.additional-sample.recommendation.new"/></li>
                                        </ul>
                                    </small>

                                </div>

                            </form>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="btn-create-user-card">Add</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="modal fade" id="successfulCardAddingModal" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h5 class="modal-title">Result</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div class="modal-body">
                            Card was added successfully! You will find it when checks your dictionary next time.
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
                        </div>

                    </div>
                </div>
            </div>


            <div class="modal fade" id="failedCardAddingModal" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h5 class="modal-title">Result</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div id="failModalBody" class="modal-body">
                            Error is occurred. Card wasn't added.
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
                        </div>

                    </div>
                </div>
            </div>

        </div>

        </spring_security:authorize>



</mytags:overallBasePage>

<%-- Script available for all Users --%>

<script>

    $(document).ready(function () {

        var msg = new SpeechSynthesisUtterance();
        msg.text = "${cardDto.content}";
        msg.lang = "${cardDto.lang.code}";

        $("#playPronBtn").click(function () {
            speechSynthesis.speak(msg);
        });

    });

</script>

<%-- Script available only for authenticated Users --%>

<spring_security:authorize access="isFullyAuthenticated()">

<spring_security:authentication property="principal.id" var="currentUserId"/>

<script>

    function objectifyForm(formArray) {

        var returnArray = {};
        for (var i = 0; i < formArray.length; i++) {
            returnArray[formArray[i]['name']] = formArray[i]['value'];
        }
        return returnArray;
    }

        $("#btn-create-user-card").click(function (event) {

            event.preventDefault();
            var formData = objectifyForm($("#userCardCreationForm").serializeArray());

            console.log(formData);

            $.ajax({
                type: "POST",
                url: location.origin + "/api/user_cards/assign?userId=${currentUserId}&cardId=${cardDto.id}",
                // data: { test: "test "}
                data: JSON.stringify(formData),
                // ToDo: understand what this line change?
                contentType: "application/json"
                // dataType: "json"
                // application/
            })

                .done(function () {
                    $('#userCardCreationModal').modal('hide');
                    $('#successfulCardAddingModal').modal('show');
                })

                .fail(function (response) {

                    var failModalBodyId = "#failModalBody";

                    $(failModalBodyId).append("<br>HTTP status: " + response.status
                        + " (" + response.statusText + ")");

                    $('#userCardCreationModal').modal('hide');
                    $('#failedCardAddingModal').modal('show');

                })

        });

</script>

</spring_security:authorize>