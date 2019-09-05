<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring_security" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<mytags:overallBasePage>

    <!-- Vars -->

    <spring_security:authentication property="principal.id" var="currentUserId"/>

    <h2><spring:message code="headline.card.read" arguments="${cardDto.id}"/></h2>

    <div>
        <spring:message code="card.attribute.content"/>: <jstl:out value="${cardDto.content}"/><br>
        <spring:message code="card.attribute.type"/>: <jstl:out value="${cardDto.type}"/><br>
        <spring:message code="card.attribute.transcription"/>: [<jstl:out value="${cardDto.pron.transcription}"/>]<br>
        <spring:message code="card.attribute.definition"/>: <jstl:out value="${cardDto.definition}"/><br>
        <spring:message code="card.attribute.samples"/>:<br>
        <jstl:forEach items="${cardDto.samples}" var="sample">
            ${sample.content}<br>
        </jstl:forEach>

        <button type="button"
                class="btn btn-secondary"
                data-toggle="modal"
                data-target="#userCardCreationPopup">

            Add To Favourites
        </button>

        <div class="modal fade"
             id="userCardCreationPopup"
             tabindex="-1"
             role="dialog"
             aria-labelledby="userCardCreationFormLabel"
             aria-hidden="true">

            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="userCardCreationFormLabel">Adding the Card to your favourites</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">

                        <form id="userCardCreationForm">

                            <div class="form-group">

                                <label for="additionalSample">Additional Sample</label>
                                <input id="additionalSample"
                                       class="form-control"
                                       name="userSample"
                                       type="text"
                                       aria-describedby="emailHelp"
                                       placeholder="Enter your own Sample ...">

                                <small id="emailHelp" class="form-text text-muted">
                                    We recommend you to
                                    <ul>
                                        <li>add statement where word ot phrase was found</li>
                                        <li>read our Samples and come up with one more on the spot</li>
                                    </ul>
                                </small>

                            </div>

                            <div class="form-group">

                                <label for="userCardStatusSelect">Status</label>
                                <div id="userCardStatusSelect">
                                    <mytags:statusSelect/>
                                </div>

                            </div>

                        </form>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="btn-create-user-card">Add</button>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous">
    </script>

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

                /*
                        Code to run if the request succeeds (is done). The response is
                    passed to the function.
                */

                    .done(function () {
                        $('#userCardCreationPopup').modal('hide');
                        alert("Card was added!");
                    })

                    /*
                            Code to run if the request fails; the raw request and status
                        codes are passed to the function
                    */

                    .fail(function (xhr, status, errorThrown) {
                        $('#userCardCreationPopup').modal('hide');
                        alert("Error!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    })

                /*
                    Code to run regardless of success or failure;

                    .always(function(xhr, status) {
                       alert("The request is complete!");
                    });

                */
            });
        });

    </script>

</mytags:overallBasePage>