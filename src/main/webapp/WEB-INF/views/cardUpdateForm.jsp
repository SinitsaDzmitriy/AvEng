<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<style>
    input {
        margin-bottom: 2px;
    }

    div.errors {
        color: red;
        border: 2px solid red;
        background-color: #ffcccc;
    }

    label.error {
        color: red;
    }

    input.error {
        background-color: #ffcccc;
    }
</style>

<mytags:overallBasePage pageHeadline="Card Update Form">

    <div class="container-fluid h-100 d-flex flex-column align-items-center justify-content-center">

        <div class="row p-2 d-flex align-items-center justify-content-center" style="height: 7rem;">
            <div id="validationTips" class="alert alert-warning text-center p-0 m-0" role="alert">
            </div>
        </div>

        <div class="row w-100">

            <div class="col-1"></div>

            <div class="col-10 d-flex align-items-center justify-content-center">

                <div id="cardUpdateFormWrapper" class="border border-secondary rounded px-4 pt-2 pb-4 m-0" style="width: 600px;">

                    <spring_form:form id="cardUpdateForm" cssClass="m-0" method="POST" modelAttribute="cardDto" autocomplete="off">


                        <label for="disabledContentInput" class="mt-2 my-1">
                            <spring:message code="card.attribute.label.content"/>
                        </label>

                        <spring_form:input id="disabledContentInput"
                                           path="content"
                                           class="form-control shadow-none"
                                           type="text"
                                           readonly="true"/>


                        <label for="disabledLangInput" class="mt-2 my-1">
                            <spring:message code="card.attribute.label.lang"/>
                        </label>

                        <spring_form:input id="disabledLangInput"
                                           path="lang"
                                           class="form-control shadow-none"
                                           type="text"
                                           readonly="true"/>


                        <label for="type" class="mt-2 my-1">
                            <spring:message code="card.attribute.label.type"/>
                        </label>

                        <spring_form:select id="type"
                                            path="type"
                                            class="form-control shadow-none"
                                            required="true">
                            <spring_form:options/>
                        </spring_form:select>


                        <label for="transcription" class="mt-2 my-1">
                            <spring:message code="card.attribute.label.transcription"/>
                        </label>

                        <spring:message code="card.attribute.placeholder.transcription" var="transcriptionPlaceholder"/>
                        <spring_form:input id="transcription"
                                           path="pron.transcription"
                                           class="form-control shadow-none"
                                           type="text"
                                           required="true"
                                           placeholder="${transcriptionPlaceholder}"/>


                        <label for="definition" class="mt-2 my-1">
                            <spring:message code="card.attribute.label.definition"/>
                        </label>

                        <spring:message code="card.attribute.placeholder.definition" var="definitionPlaceholder"/>
                        <spring_form:textarea id="definition"
                                              path="definition"
                                              class="form-control shadow-none"
                                              type="text"
                                              required="true"
                                              placeholder="${definitionPlaceholder}"/>


                        <label for="textareaForSamples" class="mt-2 my-1">
                            <spring:message code="card.attribute.label.samples"/>
                        </label>

                        <div id="samples">

                            <jstl:forEach items="${cardDto.samples}" varStatus="status">

                                <div class="sample input-group my-1">

                                    <spring_form:input path="samples[${status.index}].content"
                                                       class="sample-input is-valid form-control shadow-none"
                                                       type="text"
                                                       aria-describedby="sampleBtnNo1"/>

                                    <div class="sample-delete-btn-wrapper input-group-append">

                                        <button id="sampleBtnNo1"
                                                class="sample-delete-btn btn btn-danger shadow-none"
                                                type="button">×</button>

                                    </div>

                                </div>

                            </jstl:forEach>

                        </div>

                        <div class="input-group mt-2">

                            <textarea id="textareaForSamples"
                                      class="form-control shadow-none"
                                      aria-label="With textarea"></textarea>

                            <div class="input-group-append">
                                <button id="addSampleBtn"
                                        class="btn btn-primary p-2 shadow-none"
                                        type="button">
                                    <spring:message code="button.sample.add"/>
                                </button>
                            </div>

                        </div>

                        <div id="existentSamples" class="card mt-2">

                            <a id="existentSamplesCollapser"
                               class="card-header text-center"
                               style="cursor: pointer;"
                               role="button"
                               data-toggle="collapse"
                               data-target="#existentSamplesBody">

                                <spring:message code="button.sample.check-existent"/>
                            </a>

                            <div id="existentSamplesBody" class="collapse" aria-labelledby="existentSamplesCollapserHeader">
                                <div class="card-body">

                                    <ul id="existentSamplesList"
                                        class="list-group list-group-flush border rounded"
                                        style="max-height: 200px; overflow-y: auto;">
                                    </ul>

                                </div>
                            </div>

                        </div>

                        <div id="actionButtons" class="btn-group d-flex mt-2">

                            <button id="updateCardBtn"
                                    class="btn btn-block btn-primary d-inline-block shadow-none"
                                    type="submit">
                                <spring:message code="button.update"/>
                            </button>

                            <button id="deleteCardBtn"
                                    class="btn btn-danger shadow-none"
                                    type="button"
                                    onclick="location.pathname = '/cards/delete/${cardDto.id}'">
                                <spring:message code="button.delete"/>
                            </button>

                        </div>

                    </spring_form:form>

                </div>

            </div>

            <div class="col-1"></div>

        </div>

    </div>

</mytags:overallBasePage>

<spring:url value="/resources/images/liftUpIcon.svg" var="liftUpIconPath"/>

<script>

    /*
    ====================================================================================================================
        Block with variables declaration.
    ====================================================================================================================
        Main page variables.
    */

    // Builds on content value
    var sampleValidationRegex;
    var nextSampleId = ${cardDto.samples.size()};

    // Page constants

    var content = $("#disabledContentInput").val();
    var lang = $("#disabledLangInput").val();

    // Caution messages

    var loadExistentSamplesEmptyCaution = "<spring:message code='sample.load-existent.caution.empty'/>";
    var loadExistentSamplesFailureCaution = "<spring:message code='sample.load-existent.caution.failure'/>";

    // Variables with IDs' and classes' names for jQuery:

    var cardUpdateFormId = "#cardUpdateForm";
    var updateCardBtnId = "#updateCardBtn";

    // Parts of element for adding new Sample

    var textareaForNewSamplesId = "#textareaForSamples";
    var addSampleBtnId = "#addSampleBtn";

    // Elements that are parts of element with Samples

    var samplesId = "#samples";
    var sampleClass = ".sample";
    var sampleInputClass = ".sample-input";
    var sampleDeleteBtnWrapperClass = ".sample-delete-btn-wrapper";
    var deleteSampleBtnClass = ".sample-delete-btn";

    // Class for valid elements (Sample inputs in our case)
    var isValidClass = ".is-valid";

    // Elements that are parts of element with existent Samples

    var existentSamplesListId = "#existentSamplesList";
    var mediaBodyClass = ".media-body";
    var existentSampleClass = ".existent-sample";
    var useExistentSampleBtnClass = ".use-existent-sample-btn";

    var validationTipsId = "#validationTips";
    var newSampleValidationTipId = "#newSampleValidationTip";
    var correctedSampleValidationTipId = "#correctedSampleValidationTip";

    /*
    ====================================================================================================================
        Block with functions.
    ====================================================================================================================
        "Enter" key press handling
    */

    // Prevent form submission when press "Enter" on form input element
    $(cardUpdateFormId).keypress(function (event) {
        if (event.which === 13) {
            event.preventDefault();
        }
    });

    $(document).on("keypress", sampleInputClass, function (event) {
        if (event.which === 13) {
            $(this).blur();
        }
    });

    $(textareaForNewSamplesId).keypress(function (event) {
        if (event.which === 13) {
            $(addSampleBtnId).click();
        }
    });


    /*
    ====================================================================================================================
        Block with utility functions.
    */

    function isSampleUnique(sample) {

        var isSampleUnique = true;
        var locale = "${cardDto.lang.code}";

        console.log("Locale used for Samples comparison: " + locale);

        $(sampleInputClass + isValidClass).each(function () {

            var currentSample = $(this).val();

            if (sample.localeCompare(currentSample, locale, {sensitivity: 'base'}) === 0) {
                isSampleUnique = false;
                console.log("Caution: Attempt to add duplicate Sample.");
                console.log("Duplicate content: " + sample);
                return false;
            }

        });

        return isSampleUnique;

    }

    function addSampleElement(sample) {

        var currentSampleNo = nextSampleId++;

        var sampleElement = $(

            "<div class='sample input-group my-1'>" +

            "<input id='samples" + currentSampleNo + ".content' " +
            "name='samples[" + currentSampleNo +"].content' " +
            "class='sample-input is-valid form-control shadow-none' " +
            "type='text' " +
            "aria-describedby='sampleBtnNo" + currentSampleNo + "'>" +

            "<div class='sample-delete-btn-wrapper input-group-append'>" +
            "<button id='sampleBtnNo" + currentSampleNo + "' " +
            "class='sample-delete-btn btn btn-danger shadow-none' " +
            "type='button'>&times</button>" +
            "</div>" +

            "</div>"

        );

        sampleElement.children(sampleInputClass).val(sample);

        $(samplesId).append(sampleElement);

    }

    function setPartialFormLock() {

        // Disable delete buttons for valid Samples
        $(sampleInputClass + isValidClass)
            .siblings(sampleDeleteBtnWrapperClass)
            .children(deleteSampleBtnClass)
            .attr("disabled", "true");
        // Disable inputs for valid Samples
        $(sampleInputClass + isValidClass).attr("disabled", "true");
        // Disable textarea for new Samples
        $(textareaForNewSamplesId).attr("disabled", "true");
        // Disable button associated with this textarea
        $(addSampleBtnId).attr("disabled", "true");
        // Disable add buttons associated with existent Samples
        $(useExistentSampleBtnClass).attr("disabled", "true");
        // Disable Card creation from submit button
        $(updateCardBtnId).attr("disabled", "true");

    }

    function releasePartialFormLock() {

        // Enable inputs with valid Samples
        $(sampleInputClass + isValidClass).removeAttr("disabled");
        // Enable delete buttons for valid Samples
        $(sampleInputClass + isValidClass)
            .siblings(sampleDeleteBtnWrapperClass)
            .children(deleteSampleBtnClass)
            .removeAttr("disabled");
        // Enable textarea for new Samples
        $(textareaForNewSamplesId).removeAttr("disabled");
        // Enable button associated with this textarea
        $(addSampleBtnId).removeAttr("disabled");
        // Enable add buttons associated with existent Samples
        $(useExistentSampleBtnClass).removeAttr("disabled");
        // Enable Card creation from submit button
        $(updateCardBtnId).removeAttr("disabled");

    }

    /*
    ====================================================================================================================
        Block with static event handlers.
    */

    $(addSampleBtnId).click(function () {

        // Remove new Sample validation tip (if exists)
        $(newSampleValidationTipId).remove();

        // Delete blank spaces at the edges
        var newSample = $(textareaForNewSamplesId).val().trim();

        // Check if new Sample is empty.
        if (newSample) {

            // New Sample isn't empty -> Continue validation

            var isNewSampleValid = true;
            var newSampleValidationTip;

            // Correct wrong spacing
            newSample = newSample.replace(/\s{2,}|\t+/g, " ");

            // 1. Perform validation

            // Check if Sample satisfies regex
            if (RegExp(sampleValidationRegex, "i").test(newSample)) {

                // Sample satisfies regex -> Continue validation

                // Check if new Sample is unique
                if (!isSampleUnique(newSample)) {

                    // New Sample is a duplicate -> Mark new Sample as invalid, add appropriate validation tip

                    isNewSampleValid = false;
                    newSampleValidationTip = "<spring:message code='validation.sample.new.duplicate'/>";

                }

            } else {

                // New Sample doesn't satisfy regex -> Mark new Sample as invalid, add appropriate validation tip

                isNewSampleValid = false;
                newSampleValidationTip = "<spring:message code='validation.sample.new.without-content'/>";

            }

            // 2. Change page on validation results

            // Check if new Sample is valid
            if(isNewSampleValid) {

                // New Sample is valid -> Add it to other Samples
                addSampleElement(newSample);

                $(textareaForNewSamplesId).val("");

            } else {

                // New Sample is invalid -> Add validation tip, replace textarea (for new Samples) value with corrected one

                var sampleAddingValidationTipElement = $(
                    "<p id='newSampleValidationTip' class='px-3 my-2'>" + newSampleValidationTip + "</p>"
                );

                $(validationTipsId).append(sampleAddingValidationTipElement);

                $(textareaForNewSamplesId).val(newSample);

            }

        }

    });

    /*
    ====================================================================================================================

        Block with dynamic event handlers.

    */

    $(document).on("click", useExistentSampleBtnClass, function () {

        var sample = $(this).siblings(mediaBodyClass).children(existentSampleClass).text();

        if(isSampleUnique(sample)) {
            addSampleElement(sample);
        }

    });



    $(document).on("change", sampleInputClass, function () {

        $(this).removeClass("is-valid");

        var correctedSample = $(this).val().trim();

        // Check if corrected Sample is empty.
        if (correctedSample) {

            // Corrected Sample isn't empty -> Continue validation

            var isCorrectedSampleValid = true;
            var correctedSampleValidationTip;

            // Correct wrong spacing
            correctedSample = correctedSample.replace(/\s{2,}|\t+/g, " ");

            // Does updated Sample match the validation regex?
            if (RegExp(sampleValidationRegex, "i").test(correctedSample)) {

                // Sample satisfies regex -> Continue validation

                // If corrected Sample isn't unique
                if (!isSampleUnique(correctedSample)) {

                    // Corrected Sample is a duplicate -> Mark it as invalid, add appropriate validation tip

                    isCorrectedSampleValid = false;
                    correctedSampleValidationTip = "<spring:message code='validation.sample.corrected.duplicate'/>";

                }

            } else {

                //  Corrected Sample doesn't satisfy regex -> Mark it as invalid, add appropriate validation tip

                isCorrectedSampleValid = false;
                correctedSampleValidationTip = "<spring:message code='validation.sample.corrected.duplicate'/>";

            }

            // Check if corrected Sample is valid
            if (isCorrectedSampleValid) {

                // Corrected Sample is valid

                // If Sample was invalid
                if ($(this).hasClass("is-invalid")) {

                    // Remove class-marker of invalid Sample
                    $(this).removeClass("is-invalid");

                    // Remove validation tip for corrected Sample
                    $(correctedSampleValidationTipId).remove();

                    // Release partial form lock
                    releasePartialFormLock();

                }

                // Mark corrected Sample as valid
                $(this).addClass("is-valid");

            } else {

                // Corrected Sample is invalid

                // Check if Sample was invalid before correction
                if($(this).hasClass("is-invalid")) {

                    // Sample was invalid before correction

                    // Remove previous validation tip for corrected Sample
                    $(correctedSampleValidationTipId).remove();

                } else {

                    // Sample was valid before correction

                    // Mark corrected Sample as invalid
                    $(this).addClass("is-invalid");

                    // Partially block Card creation form
                    setPartialFormLock()

                }

                // Add validation tip element for corrected Sample

                var correctedSampleValidationTipElement = $(
                    "<p id='correctedSampleValidationTip' class='px-3 my-2'>" + correctedSampleValidationTip + "</p>"
                );

                $(validationTipsId).prepend(correctedSampleValidationTipElement);

            }

        } else {

            // Corrected Sample is empty

            // Remember weather it was invalid
            var wasInvalid = $(this).hasClass("is-invalid");

            // Delete empty Sample input
            $(this).parent(sampleClass).remove();


            // If Sample was invalid, release partial form lock
            if (wasInvalid) {

                // Delete validation tip for corrected Sample
                $(correctedSampleValidationTipId).remove();

                // Release partial form lock
                releasePartialFormLock();

            }

        }

    });



    $(document).on("click", deleteSampleBtnClass, function () {

        $(sampleInputClass).blur();

        var isAssociatedSampleInvalid = $(this)
            .parent(sampleDeleteBtnWrapperClass)
            .siblings(sampleInputClass)
            .hasClass("is-invalid");

        // Remove Sample element
        $(this).parents(sampleClass).remove();

        // Check if value of associated Sample input is invalid
        if(isAssociatedSampleInvalid) {

            // Remove validation tip for corrected Sample
            $(correctedSampleValidationTipId).remove();

            // Release partial form lock
            releasePartialFormLock();

        }

    });


    /*
    ====================================================================================================================

        Code to execute when page is ready.

    */

    $(document).ready(function () {

        /*
        ================================================================================================================
            1. Calculate regex for Sample validation based on 'content' field value.
        */

        var contentParts = content.split(/\s/);
        console.log("Content parts: " + contentParts);

        sampleValidationRegex = "(.+ |^)" + contentParts[0];
        for (i = 1; i < contentParts.length; i++) {
            sampleValidationRegex += ".* " + contentParts[i];
        }

        console.log("Validation regex for Samples: " + sampleValidationRegex);

        /*
        ================================================================================================================
            2. Send asynchronous request to REST api to get existent Samples for Card 'content'.
        */

        $.ajax({
            type: "GET",
            url: location.origin + "/api/samples/search?input=" + content + "&lang=" + lang,
            dataType: "json"
        })
            .done(function(samples) {

                var existentSample;
                var liftUpIconPath = "${liftUpIconPath}";

                for (var i = 0; i < samples.length; i++) {

                    existentSample = $(

                        "<li class='list-group-item p-2'>" +
                        "<div class='media'>" +

                        "<div class='media-body border-right px-2 mx-1 d-flex align-items-center' style='min-height: 43px;'>" +
                        "<p class='existent-sample m-0'>" +  samples[i].content + "</p>" +
                        "</div>" +

                        "<button class='use-existent-sample-btn btn btn-link shadow-none p-0' type='button'>" +
                        "<img src='" + liftUpIconPath + "' class='align-self-center m-2' width='25' height='25' alt='lift up icon'>" +
                        "</button>" +

                        "</div>" +
                        "</li>"

                    );

                    $(existentSamplesListId).append(existentSample);

                }

                console.log("Status of the request to get existent Samples: OK (" + samples.length + " Samples found).");

            })

            .fail(function (response) {

                var caution;

                // ToDo: Internalization support

                if (!response.ok) {

                    console.log("Status of the request to get existent Samples: OK (0 Samples found).");

                    caution = $(
                        "<li class='list-group-item list-group-item-warning p-2'>" +
                        "<p class='text-center m-0'>" +
                        loadExistentSamplesEmptyCaution +
                        "</p>" +
                        "</li>"
                    );

                } else {

                    console.log("Status of the request to get existent Samples: failure.");

                    caution = $(
                        "<li class='list-group-item list-group-item-danger p-2'>" +
                        "<p class='text-center m-0'>" +
                        loadExistentSamplesFailureCaution +
                        "</p>" +
                        "</li>"
                    );

                }

                $(existentSamplesListId).append(caution);

            });

    });

</script>