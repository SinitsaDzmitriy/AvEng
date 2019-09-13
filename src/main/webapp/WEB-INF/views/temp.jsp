<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ page import="edu.sam.aveng.base.model.domain.enumeration.StatementType" %>

<mytags:overallBasePage pageHeadline="Temp">

    <div class="container-fluid d-flex h-100 flex-column">


        <div id="rowForValidationTips"
             class="row p-2 d-flex align-items-center justify-content-center"
             style="height: 7rem;">
        </div>

        <div class="row flex-grow-1">

            <div class="col-1 d-flex align-items-center justify-content-center">

                <a id="carouselControlLeft"
                   class="carousel-control disabled btn btn-secondary shadow-none rounded d-flex align-items-center justify-content-center"
                   style="width: 50px; height: 160px;">

                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                </a>

            </div>


            <div class="col-10">

                <div id="carouselCardCreationForm" class="carousel slide h-100" data-interval="false">

                    <div class="carousel-inner h-100">

                        <form id="cardCreationForm" class="h-100 m-0">

                            <div class="container-fluid d-flex h-100 flex-column">

                                <div class="row flex-grow-1">

                                    <div id="slideNo1"
                                         class="carousel-item h-100 active">

                                        <div class="container-fluid h-100 bg-white d-flex align-items-center justify-content-center" style="min-height: 550px;">

                                            <div id="formPartNo1"
                                                 class="border border-secondary rounded px-4 pt-2 pb-4 m-0"
                                                 style="width: 300px;">

                                                <!-- Make supported Card lang sent from back-end (jQuery, AJAX) -->

                                                <div class="form-group">
                                                    <label for="cardLangSelect">
                                                        <spring:message code="card.attribute.label.lang"/>:
                                                    </label>
                                                    <select class="form-control shadow-none" id="cardLangSelect">
                                                        <option class="shadow-none" value="en" selected>English</option>
                                                        <option class="shadow-none" value="ru">Russian</option>
                                                        <option class="shadow-none" value="de">German</option>
                                                    </select>
                                                </div>

                                                <label for="enabledContentInput">
                                                    <spring:message code="card.attribute.label.content"/>
                                                </label>

                                                <br>

                                                <spring:message code="card.attribute.placeholder.content"
                                                                var="contentPlaceholder"/>
                                                <input id="enabledContentInput"
                                                       type="text"
                                                       class="form-control shadow-none"
                                                       placeholder="${contentPlaceholder}"/>

                                            </div>

                                        </div>

                                    </div>

                                    <div id="slideNo2"
                                         class="carousel-item h-100">

                                        <div class="container-fluid h-100 bg-white d-flex align-items-center justify-content-center" style="min-height: 550px;">

                                                <div id="formPartNo2"
                                                     class="border border-secondary rounded px-4 pt-2 pb-4 m-0"
                                                     style="width: 300px;">

                                                    <label for="disabledContentInput">
                                                        <spring:message code="card.attribute.label.content"/>
                                                    </label><br>

                                                    <input id="disabledContentInput"
                                                           class="form-control shadow-none"
                                                           type="text"
                                                           readonly/>

                                                    <label for="type">
                                                        <spring:message code="card.attribute.label.type"/>
                                                    </label><br>

                                                    <select id="type" class="form-control shadow-none" required>
                                                        <jstl:forEach items="${StatementType.values()}" var="type">
                                                            <option class="shadow-none">${type.name()}</option>
                                                        </jstl:forEach>
                                                    </select>


                                                    <label for="transcription">
                                                        <spring:message code="card.attribute.label.transcription"/>
                                                    </label><br>
                                                    <spring:message code="card.attribute.placeholder.transcription"
                                                                    var="transcriptionPlaceholder"/>
                                                    <input id="transcription"
                                                           class="form-control shadow-none"
                                                           type="text"
                                                           placeholder="${transcriptionPlaceholder}"
                                                           required/>

                                                    <label for="definition">
                                                        <spring:message code="card.attribute.label.definition"/>
                                                    </label><br>
                                                    <spring:message code="card.attribute.placeholder.definition"
                                                                    var="definitionPlaceholder"/>
                                                    <textarea id="definition"
                                                              class="form-control shadow-none"
                                                              placeholder="${definitionPlaceholder}"
                                                              required></textarea>

                                                    <label for="textareaForSamples">
                                                        <spring:message code="card.attribute.label.samples"/>
                                                    </label><br>

                                                    <div id="samples">
                                                    </div>

                                                    <textarea id="textareaForSamples" class="form-control shadow-none"
                                                              aria-label="With textarea"></textarea>

                                                    <button id="addSampleBtn" type="button"
                                                            class="btn btn-secondary shadow-none my-1">
                                                        Add Sample
                                                    </button>
                                                    <br>

                                                    <input id="createCardBtn"
                                                           type="submit"
                                                           value="Create card"
                                                           class="btn btn-block btn-warning border-secondary shadow-none my-1"/>

                                                </div>

                                        </div>
                                    </div>


                                </div>

                            </div>

                        </form>

                    </div>

                </div>

            </div>


            <div class="col-1 bg-white d-flex align-items-center justify-content-center">

                <a id="carouselControlRight"
                   class="carousel-control disabled btn btn-secondary shadow-none rounded d-flex align-items-center justify-content-center"
                   style="width: 50px; height: 160px;">

                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                </a>

            </div>


        </div>

    </div>

    <!-- Modal -->
    <div class="modal fade" id="samplesDeletionAlert" tabindex="-1" role="dialog" aria-hidden="true">

    <div class="modal-dialog" role="document">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Warning!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                This action delete all samples on the current slide. Confirm to continue.
            </div>

            <div class="modal-footer">
                <button id="confirmSamplesDeletionBtn" type="button" class="btn btn-secondary">Confirm</button>
                <button id="cancelSamplesDeletionBtn" type="button" class="btn btn-primary" data-dismiss="modal">
                    Cancel
                </button>
            </div>

        </div>
    </div>


</mytags:overallBasePage>

<script
        src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous">
</script>


<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>

<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script>

    var nextSampleId = 1;

    var carouselCardCreationFormId = "#carouselCardCreationForm";
    var cardCreationFormId = "#cardCreationForm";

    var formPartNo2Id = "#formPartNo2";

    var rowForValidationTipsId = "#rowForValidationTips";

    var sampleUpdateValidationTipId = "#samplesValidationTip";
    var sampleAddingValidationTipId = "#sampleAddingValidationTip";
    var contentValidationTipId = "#contentValidationTip";

    var enabledContentInputId = "#enabledContentInput";
    var cardLangSelectId = "#cardLangSelect";
    var disabledContentInputId = "#disabledContentInput";

    var textareaForSamplesId = "#textareaForSamples";
    var addSampleBtnId = "#addSampleBtn";
    var createCardBtnId = "#createCardBtn";

    var samplesDeletionAlertId = "#samplesDeletionAlert";
    var confirmSamplesDeletionBtnId = "#confirmSamplesDeletionBtn";

    var carouselControlClass = ".carousel-control";

    var sampleClass = ".sample";
    var sampleInputClass = ".sample-input";
    var sampleDeleteBtnClass = ".sample-delete-btn";

    var disabledClass = ".disabled";
    var spoiledClass = ".spoiled";

    var sampleWithoutContentTip = "<spring:message code="validation.sample.without-content"/>";
    var sampleDuplicateTip = "<spring:message code="validation.sample.duplicate"/>";

    var slideNo1Id = "slideNo1";
    var slideNo2Id = "slideNo2";

    var carouselControlLeftId = "#carouselControlLeft";
    var carouselControlRightId = "#carouselControlRight";

    // Regex for (default) English language
    var contentValidationRegex = "^[\\s\\-/.:0-9a-z]+$";
    // Builds on content value
    var sampleValidationRegex;


    // Prevent form submission when press "Enter" on form input element
    $(cardCreationFormId).keypress(function (event) {
        if (event.which === 13) {
            event.preventDefault();
        }
    });


    function validateContent() {

        var isValid = true;
        var correctedContent = $(enabledContentInputId).val().trim();

        if (correctedContent) {

            correctedContent = correctedContent.replace(/[\s\t]+/g, " ");
            $(enabledContentInputId).val(correctedContent);


            // Does corrected content input match current validation regex?
            if (RegExp(contentValidationRegex, "i").test($(enabledContentInputId).val())) {

                // Yes -> do nothing
                console.log("Content is valid");

            } else {

                // No -> mark content input as invalid
                console.log("Content is invalid (doesn't match regex)");
                console.log("content = " + $(enabledContentInputId).val());
                console.log("contentValidationRegex = " + contentValidationRegex);
                isValid = false;

            }
        } else {

            console.log("Content is invalid (empty)");
            isValid = false;

        }

        // Is corrected content input is valid?
        if (isValid) {
            // Yes -> enable carousel-controls
            $(contentValidationTipId).remove();
            $(enabledContentInputId).removeClass("is-invalid");
            $(enabledContentInputId).addClass("is-valid");
            $(carouselControlRightId).removeClass("disabled");

        } else {
            // No -> disable carousel-controls

            $(contentValidationTipId).remove();
            var contentValidationTipElement = $("<div id='contentValidationTip' class='alert alert-danger m-0' role='alert'>"
                + "<spring:message code='validation.card.content.lang'/>"
                + "</div>");
            $(rowForValidationTipsId).prepend(contentValidationTipElement);

            $(enabledContentInputId).removeClass("is-valid");
            $(enabledContentInputId).addClass("is-invalid");
            $(carouselControlRightId).addClass("disabled");
        }

    }


    $(cardLangSelectId).change(function () {

        // Reform regex for validating content input based on new selected language
        contentValidationRegex = "^[\\s\\-/.:0-9a-z";

        switch ($(cardLangSelectId).val()) {
            case "ru":
                contentValidationRegex += "а-яё";
                break;
            case "de":
                contentValidationRegex += "äöüß";
        }

        contentValidationRegex += "]+$";

        console.log("New contentValidationRegex = " + contentValidationRegex);

        if ($(enabledContentInputId).val()) {
            validateContent();
        }

    });


    $(enabledContentInputId).change(validateContent);


    $(enabledContentInputId).keypress(function (event) {
        if (event.which === 13) {
            validateContent();
        }
    });


    $(carouselControlClass).click(function (event) {
        if ($(this).hasClass("disabled")) {
            event.stopPropagation();
        } else {

            switch ($(".carousel-item.active").attr("id")) {

                case "slideNo1":

                    // Set value of disabled content input on the 2nd slide
                    $(disabledContentInputId).val($(enabledContentInputId).val());

                    // Form validation regex for Samples
                    var contentParts = $(disabledContentInputId).val().split(/\s/);
                    console.log(contentParts.length);

                    sampleValidationRegex = "(.+ |^)" + contentParts[0];
                    for (i = 1; i < contentParts.length; i++) {
                        sampleValidationRegex += ".* " + contentParts[i];
                    }

                    console.log("sampleValidationRegex = " + sampleValidationRegex);
                    break;

                case "slideNo2":

                    $(samplesDeletionAlertId).modal("show");
                    event.stopPropagation();
                    break;

                default:
                    console.log("Undefined carousel-item id (click jQuery method).")
            }

        }
    });


    /*
        Add Sample if valid:
        1. Not empty
        2. Contains content
        3. Not duplicate
    */

    $(addSampleBtnId).click(function () {

        $(sampleAddingValidationTipId).remove();

        var sampleAddingValidationTipElement;

        // Delete edge blank spaces
        var newSample = $(textareaForSamplesId).val().trim();

        if (newSample) {

            // Replace wrong spacing
            newSample = newSample.replace(/[\s\t]+/g, " ");

            $(textareaForSamplesId).val(newSample);

            // Does new Sample match the validation regex?
            if (RegExp(sampleValidationRegex, "i").test(newSample)) {
                // Yes -> continue validation

                var isSampleUnique = true;

                if ($(sampleClass).length > 0) {

                    var locale = $(cardLangSelectId).val();
                    console.log("Current locale: " + locale);

                    $(sampleClass).each(function () {

                        var currentSample = $(this).children(sampleInputClass).val();

                        if (newSample.localeCompare(currentSample, locale, {sensitivity: 'base'}) === 0) {
                            isSampleUnique = false;
                            console.log("New Sample is a duplicate");
                            console.log("Current Sample: " + currentSample);
                            console.log("New Sample: " + newSample);
                            return false;
                        }

                    });

                }

                // Is new Sample unique?
                if (isSampleUnique) {

                    var currentSampleNo = nextSampleId++;

                    var newSampleInput = $("<div class='sample input-group my-1'>"
                        + "<input type='text' class='sample-input form-control shadow-none' aria-describedby='sampleBtnNo" + currentSampleNo + "'>"
                        + "<div class='input-group-append'>"
                        + "<button id='sampleBtnNo" + currentSampleNo + "' class='sample-delete-btn btn btn-danger shadow-none' type='button'>&times</button>"
                        + "</div>"
                        + "</div>");

                    newSampleInput.children(".sample-input").val(newSample);

                    $("#samples").append(newSampleInput);

                    $(textareaForSamplesId).val("");
                } else {

                    sampleAddingValidationTipElement = $("<div id='sampleAddingValidationTip' class='alert alert-danger' role='alert'>"
                        + sampleDuplicateTip
                        + "</div>");
                    $(rowForValidationTipsId).append(sampleAddingValidationTipElement)

                }

            } else {
                // No -> display validation tip for User

                sampleAddingValidationTipElement = $("<div id='sampleAddingValidationTip' class='alert alert-danger' role='alert'>"
                    + sampleWithoutContentTip
                    + "</div>");
                $(rowForValidationTipsId).append(sampleAddingValidationTipElement);

            }

        }
    });


    /*
        Add Sample if valid:
        1. Not empty
        2. Contains content
        3. Not duplicate
    */

    $(document).on("change", sampleClass, function () {

        var updatedSampleInput = $(this).children("input.sample-input");

        var updatedSample = updatedSampleInput.val().trim();

        // Is this input empty?
        if (updatedSample) {

            // No -> continue validation

            // Replace wrong spacing
            updatedSample = updatedSample.replace(/[\s\t]+/g, " ");
            updatedSampleInput.val(updatedSample);

            var isValid = true;
            var validationTip;

            // Does updated Sample match the validation regex?
            if (RegExp(sampleValidationRegex, "i").test(updatedSample)) {

                // Yes -> continue validation

                if ($(sampleClass).length > 0) {

                    var locale = $(cardLangSelectId).val();
                    console.log("Current locale: " + locale);

                    $(sampleInputClass).not(updatedSampleInput).each(function () {

                        var currentSample = $(this).val();

                        if (updatedSample.localeCompare(currentSample, locale, {sensitivity: 'base'}) === 0) {
                            isValid = false;
                            validationTip = sampleDuplicateTip;
                            console.log("Updated Sample is a duplicate");
                            console.log("Current Sample: " + currentSample);
                            console.log("Updated Sample: " + updatedSample);
                            return false;
                        }

                    });

                }

            } else {

                // No -> mark this Sample as invalid
                isValid = false;
                validationTip = sampleWithoutContentTip;
            }

            if (isValid) {

                if ($(this).hasClass("spoiled")) {

                    $(sampleUpdateValidationTipId).remove();
                    $(sampleClass + ":not(" + spoiledClass + ")")
                        .children(sampleInputClass)
                        .removeAttr("disabled");
                    $(textareaForSamplesId).removeAttr("disabled");
                    $(addSampleBtnId).removeAttr("disabled");
                    $(createCardBtnId).removeAttr("disabled");
                    $(this).removeClass("spoiled");
                    $(this).children("input").removeClass("is-invalid");
                }

            } else {

                $(sampleUpdateValidationTipId).remove();
                var samplesValidationTipElement = $("<div id='samplesValidationTip' class='alert alert-danger' role='alert'>"
                    + validationTip
                    + "</div>");
                $(rowForValidationTipsId).prepend(samplesValidationTipElement);

                if (!$(this).hasClass("spoiled")) {

                    $(this).addClass("spoiled");
                    $(this).children("input").addClass("is-invalid");
                    $(sampleClass)
                        .not(this)
                        .children(sampleInputClass)
                        .attr("disabled", "true");
                    $(createCardBtnId).attr("disabled", "true");
                    $(addSampleBtnId).attr("disabled", "true");
                    $(textareaForSamplesId).attr("disabled", "true");

                }
            }

        } else {

            // Yes -> remove this element

            var wasSpoiled = $(this).hasClass("spoiled");
            $(this).remove();

            if (wasSpoiled) {
                $(sampleUpdateValidationTipId).remove();
                $(sampleClass).children(sampleInputClass).removeAttr("disabled");
                $(textareaForSamplesId).removeAttr("disabled");
                $(addSampleBtnId).removeAttr("disabled");
                $(createCardBtnId).removeAttr("disabled");
            }

        }

    });

    $(document).on("click", sampleDeleteBtnClass, function () {

        var sampleElement = $(this).parents(sampleClass);
        var wasSampleElementSpoiled = sampleElement.hasClass("spoiled");
        sampleElement.remove();

        if (wasSampleElementSpoiled) {
            $(sampleUpdateValidationTipId).remove();
            $(sampleClass).children(sampleInputClass).removeAttr("disabled");
            $(textareaForSamplesId).removeAttr("disabled");
            $(addSampleBtnId).removeAttr("disabled");
            $(createCardBtnId).removeAttr("disabled");
        }

    });

    // hardcode

    $(carouselControlLeftId).click(function () {

        if ($(".carousel-item.active").attr("id") === "slideNo2") {
            $(samplesDeletionAlertId).modal("show");
        }

    });

    $(confirmSamplesDeletionBtnId).click(function () {

        $(samplesDeletionAlertId).modal("hide");
        $(carouselCardCreationFormId).carousel("prev");

        $(carouselControlRightId).removeClass("disabled");
        $(carouselControlLeftId).addClass("disabled");

        $(sampleUpdateValidationTipId).remove();
        $(textareaForSamplesId).removeAttr("disabled");
        $(addSampleBtnId).removeAttr("disabled");
        $(createCardBtnId).removeAttr("disabled");

        $(sampleClass).remove();
        $(textareaForSamplesId).val("");

    });

    $(carouselControlRightId).click(function () {

        if ($(".carousel-item.active").attr("id") === "slideNo1") {
            $(carouselCardCreationFormId).carousel("next");
            $(carouselControlLeftId).removeClass("disabled");
            $(this).addClass("disabled");
        }

    });

    function sendSynchCardCreationRequest() {
        alert("stub");
    }

    $(cardCreationFormId).submit(function (event) {

        event.preventDefault();

        alert("I'm here");
        console.log("I'm here");

        var samplesJson = "";
        var samples = [];

        if ($(sampleInputClass).length > 0) {

            $(sampleInputClass).each(function () {
                console.log($(this).val());
                samples.push($(this).val());
            });

            console.log(samples);

            samplesJson = '"samples" : [{"content" : "' + samples[0] + '"}';

            if (samples.length > 1) {

                for (var i = 1; i < samples.length; i++) {
                    samplesJson += ',{"' + samples[i] + '"}';
                }

            }

            samplesJson += ']';

        }

        console.log(samplesJson);

        var card = '{'
            + '"content" : "' + $(enabledContentInputId).val() + '",'
            + '"type" : "' + $("#type").val() + '",'
            + '"pron" : {'
            + '"transcription" : "' + $("#transcription").val() + '"'
            + '},'
            + '"definition" : "' + $("#definition").val() + '",'
            + samplesJson
            + '}';

        $.ajax({
            url: location.origin + "/api/cards/create",
            data: card,
            type: "POST",
            contentType: "application/json"
        })

            .done(function () {
                alert("OK!");
            })

            .fail(function () {
                alert("Error!");
            });


    });

</script>
