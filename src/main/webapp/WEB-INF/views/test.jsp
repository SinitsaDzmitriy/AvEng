<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>


<mytags:overallBasePage>

    <form>

        <div class="form-row">

            <div class="col-md-4 mb-3">

                <label for="validationServer01">
                    First name
                </label>
                <input id="validationServer01"
                       class="form-control"
                       type="text"
                       placeholder="First name"
                       invalid
                       required>

                <!-- ToDo: Check "required" attribute for "input" tag -->

                <div class="valid-feedback">
                    Looks good!
                </div>

            </div>

            <div class="col-md-4 mb-3">

                <label for="validationServer02">
                    Last name
                </label>
                <input id="validationServer02"
                       class="form-control is-valid"
                       type="text"
                       placeholder="Last name"
                       value="Otto" required>

                <div class="valid-feedback">
                    Looks good!
                </div>

            </div>

            <div class="col-md-4 mb-3">

                <label for="validationServerUsername">
                    Username
                </label>

                <div class="input-group">

                    <div class="input-group-prepend">
                        <span id="inputGroupPrepend3" class="input-group-text">@</span>
                    </div>
                    <input id="validationServerUsername"
                           class="form-control is-invalid"
                           type="text"
                           placeholder="Username"
                           aria-describedby="inputGroupPrepend3" required>

                    <div class="invalid-feedback">
                        Please choose a username.
                    </div>

                </div>

            </div>

        </div>

        <div class="form-row">

            <div class="col-md-6 mb-3">

                <label for="validationServer03">
                    City
                </label>
                <input id="validationServer03"
                       class="form-control is-invalid"
                       type="text"
                       placeholder="City" required>

                <div class="invalid-feedback">
                    Please provide a valid city.
                </div>

            </div>

            <div class="col-md-3 mb-3">

                <label for="validationServer04">
                    State
                </label>
                <input id="validationServer04"
                       class="form-control is-invalid"
                       type="text"
                       placeholder="State" required>

                <div class="invalid-feedback">
                    Please provide a valid state.
                </div>

            </div>

            <div class="col-md-3 mb-3">

                <label for="validationServer05">
                    Zip
                </label>
                <input id="validationServer05"
                       class="form-control is-invalid"
                       type="text"
                       placeholder="Zip" required>

                <div class="invalid-feedback">
                    Please provide a valid zip.
                </div>

            </div>

        </div>

        <div class="form-group">

            <div class="form-check">

                <input id="invalidCheck3"
                       class="form-check-input is-invalid"
                       type="checkbox"
                       value="" required>

                <label class="form-check-label" for="invalidCheck3">
                    Agree to terms and conditions
                </label>

                <div class="invalid-feedback">
                    You must agree before submitting.
                </div>

            </div>

        </div>

        <button class="btn btn-primary" type="submit">Submit form</button>

    </form>

    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

</mytags:overallBasePage>