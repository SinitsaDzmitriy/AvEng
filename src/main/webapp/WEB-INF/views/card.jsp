<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring_security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<mytags:overallBasePage pageHeadline="${cardDto.content} Card">

    <h2><spring:message code="headline.card.read" arguments="${cardDto.id}"/></h2>

<div>
    <spring:message code="card.attribute.label.content" />: <jstl:out value="${cardDto.content}"/><br>
    <spring:message code="card.attribute.label.type" />: <jstl:out value="${cardDto.type}"/><br>
    <spring:message code="card.attribute.label.transcription" />: [<jstl:out value="${cardDto.pron.transcription}"/>]<br>
    <spring:message code="card.attribute.label.definition" />: <jstl:out value="${cardDto.definition}"/><br>
    <spring:message code="card.attribute.label.samples" />:<br>
    <jstl:forEach items="${cardDto.samples}" var="sample">
        ${sample.content}<br>
    </jstl:forEach>

    <spring_security:authorize access="isFullyAuthenticated()">

        <button type="button"
                class="btn btn-secondary"
                data-toggle="modal"
                data-target="#userCardCreationModal">

            Add To My Dictionary
        </button>

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
                                    <spring:message code="user-card.adding-form.attribute.additional-sample.caption"/>
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

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous">
    </script>


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

            $(document).ready(function () {

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
            });

        </script>

    </spring_security:authorize>

</mytags:overallBasePage>