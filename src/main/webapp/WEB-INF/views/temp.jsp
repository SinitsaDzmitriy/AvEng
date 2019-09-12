<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    .w-10 {
        width: 10% !important;
    }

    .w-80 {
        width: 80% !important;
    }
    .carousel-control {
        width: 5rem;
        height: 5rem;
    }
</style>

<mytags:overallBasePage>

    <div class="container-fluid bg-secondary">
        <div class="row">

            <div class="col-1">

            </div>

            <div class="col-10">

            </div>

            <div class="col-1">

            </div>

        </div>
    </div>

    <div class="bg-secondary">
    <div id="carouselCardCreationForm" class="carousel slide" data-interval="false">

        <div class="carousel-inner w-80 m-auto">

            <form id="cardCreationForm" class="m-0">

                <div class="container-fluid">

                    <div id="rowForValidationTips" class="row bg-light p-2 d-flex align-items-center justify-content-center" style="height: 8rem;">
                    </div>

                    <div class="row">

                        <div id="no1" class="carousel-item bg-light d-flex align-items-center justify-content-center active">

                            <div id="formPartNo1" style="width: 235px;">

                                <div class="form-group border border-secondary rounded px-4 pt-2 pb-4 m-0">

                                    <!-- Make supported Card lang sent from back-end (jQuery, AJAX) -->

                                    <div class="form-group">
                                        <label for="cardLangSelect">Card language: </label>
                                        <select class="form-control shadow-none" id="cardLangSelect">
                                            <option value="en" selected>English</option>
                                            <option value="ru">Russian</option>
                                            <option value="de">German</option>
                                        </select>
                                    </div>

                                    <label for="enabledContentInput">
                                        <spring:message code="card.attribute.content"/>
                                    </label>

                                    <br>

                                    <input id="enabledContentInput"
                                           type="text"
                                           class="form-control shadow-none"
                                           placeholder="Enter word or phrase"/>

                                </div>

                            </div>

                        </div>

                        <div id="no2" class="carousel-item">

                            <div class="h-100 w-100 bg-light d-flex align-items-center justify-content-center">
                                <div id="formPartNo2" style="width: 235px;">

                                    <label for="disabledContentInput">
                                        <spring:message code="card.attribute.content"/>
                                    </label><br>

                                    <input id="disabledContentInput"
                                           class="form-control"
                                           type="text"
                                           readonly/><br>

                                    <label for="textareaForSamples">
                                        <spring:message code="card.attribute.samples"/>
                                    </label><br>

                                    <div id="samples">
                                    </div>

                                    <textarea id="textareaForSamples" class="form-control"
                                              aria-label="With textarea"></textarea><br>

                                    <button id="addSampleBtn" type="button" class="btn btn-primary">Add</button>

                                    <button type="button" class="btn btn-primary">Create card</button>

                                </div>
                            </div>
                        </div>


                    </div>

                    <div class="row bg-light" style="height: 8rem;">
                    </div>

                </div>

            </form>

        </div>


        <a class="carousel-control disabled carousel-control-prev w-10" href="#carouselCardCreationForm" role="button"
           data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>

        <a class="carousel-control disabled carousel-control-next w-10" href="#carouselCardCreationForm" role="button"
           data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>


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

    </div>

</mytags:overallBasePage>

<script>

    var nextSampleId = 1;

    var cardCreationFormId = "#cardCreationForm";

    var formPartNo2Id = "#formPartNo2";

    var rowForValidationTipsId = "#rowForValidationTips";

    var samplesValidationAlertId = "#samplesValidationAlert";
    var contentValidationTipId = "#contentValidationTip";

    var enabledContentInputId = "#enabledContentInput";
    var cardLangSelectId = "#cardLangSelect";
    var disabledContentInputId = "#disabledContentInput";

    var addSampleBtnId = "#addSampleBtn";
    var textareaForSamplesId = "#textareaForSamples";

    var samplesDeletionAlertId = "#samplesDeletionAlert";
    var confirmSamplesDeletionBtnId = "#confirmSamplesDeletionBtn";

    var carouselControlClass = ".carousel-control";

    var sampleClass = ".sample";
    var sampleInputClass = ".sample-input";
    var sampleBtnClass = ".sample-btn";

    var disabledClass = ".disabled";
    var spoiledClass = ".spoiled";

    var sampleWithoutContentAlert = "<spring:message code="validation.sample.without-content"/>";
    var sampleDuplicateAlert = "<spring:message code="validation.sample.duplicate"/>";


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
            $(carouselControlClass + disabledClass).removeClass("disabled");
        } else {
            // No -> disable carousel-controls

            $(contentValidationTipId).remove();
            var contentValidationTipElement = $("<div id='contentValidationTip' class='alert alert-danger' role='alert'>"
                + "<spring:message code='validation.card.content.lang'/>"
                + "</div>");
            $(rowForValidationTipsId).prepend(contentValidationTipElement);

            $(enabledContentInputId).removeClass("is-valid");
            $(enabledContentInputId).addClass("is-invalid");
            $(carouselControlClass + ":not(" + disabledClass + ")").addClass("disabled");
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

                case "no1":

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

                case "no2":

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

        console.log("I'm here.");

        // Delete edge blank spaces
        var newSample = $(textareaForSamplesId).val().trim();

        if (newSample) {

            // Replace wrong spacing
            newSample = newSample.replace(/[\s\t]+/g, " ");

            $(textareaForSamplesId).val(newSample);

            if (RegExp(sampleValidationRegex, "i").test(newSample)) {

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

                if (isSampleUnique) {

                    var currentSampleNo = nextSampleId++;

                    var newSampleInput = $("<div class='sample input-group my-1'>"
                        + "<input type='text' class='sample-input form-control' aria-describedby='sampleBtnNo" + currentSampleNo + "'>"
                        + "<div class='input-group-append'>"
                        + "<button id='sampleBtnNo" + currentSampleNo + "' class='sample-btn btn btn-primary' type='button'>&times</button>"
                        + "</div>"
                        + "</div>");

                    newSampleInput.children(".sample-input").val(newSample);

                    $("#samples").append(newSampleInput);

                    $(textareaForSamplesId).val("");
                }

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
                            validationTip = sampleDuplicateAlert;
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
                validationTip = sampleWithoutContentAlert;
            }

            if (isValid) {

                if ($(this).hasClass("spoiled")) {

                    $(samplesValidationAlertId).remove();
                    $(sampleClass + ":not(" + spoiledClass + ")")
                        .children(sampleInputClass)
                        .removeAttr("disabled");
                    $(addSampleBtnId).removeAttr("disabled");
                    $(textareaForSamplesId).removeAttr("disabled");
                    $(this).removeClass("spoiled");
                    $(this).children("input").removeClass("is-invalid");
                }

            } else {

                $(samplesValidationAlertId).remove();
                var samplesValidationAlertElement = $("<div id='samplesValidationAlert' class='alert alert-danger' role='alert'>"
                    + validationTip
                    + "</div>");
                $(formPartNo2Id).prepend(samplesValidationAlertElement);

                if (!$(this).hasClass("spoiled")) {

                    $(this).addClass("spoiled");
                    $(this).children("input").addClass("is-invalid");
                    $(sampleClass)
                        .not(this)
                        .children(sampleInputClass)
                        .attr("disabled", "true");
                    $(addSampleBtnId).attr("disabled", "true");
                    $(textareaForSamplesId).attr("disabled", "true");

                }
            }

        } else {

            // Yes -> remove this element
            $(samplesValidationAlertId).remove();
            $(this).remove()
        }

    });

    $(document).on("click", sampleBtnClass, function () {

        var sampleParent = $(this).parents(sampleClass);

        if (sampleParent.hasClass("spoiled")) {

            $(sampleClass + ":not(" + spoiledClass + ")")
                .children(sampleInputClass)
                .removeAttr("disabled");
            $(addSampleBtnId).removeAttr("disabled");
            $(textareaForSamplesId).removeAttr("disabled");
            $(samplesValidationAlertId).remove();
            $(this).removeClass("spoiled");
            $(this).children("input").removeClass("is-invalid");

        }

        sampleParent.remove();
    });

    $(confirmSamplesDeletionBtnId).click(function () {
        $(sampleClass).remove();
        $(samplesDeletionAlertId).modal("hide");
        $("#carouselCardCreationForm").carousel("next");
    });

</script>

