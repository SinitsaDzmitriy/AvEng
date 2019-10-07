<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>

<head>
    <title>
        <spring:message code="title.like-vs-fts"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<div class="fluid-container">
    <div class="row">

        <div class="col"></div>

        <div class="col-10">

            <div class="row mt-1">
                <div class="col-12">
                    <button type="button" id="homepageBtn"
                            class="btn btn-block btn-primary shadow-none"
                            onclick="location.href = location.origin">
                        <spring:message code="demo.like-vs-fts.button.homepage"/>
                    </button>
                </div>
            </div>


            <div class="row mt-1">
                <div class="col-12">
                    <div class="input-group">
                        <spring:message code="demo.like-vs-fts.input.search" var="searchPlaceholder"/>
                        <input id="searchInput" class="form-control shadow-none" type="text"
                               placeholder="${searchPlaceholder}">
                        <div class="input-group-append">
                            <button type="button" id="performSearchBtn"
                                    class="btn btn-primary shadow-none">
                                <spring:message code="demo.like-vs-fts.button.search"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-1">

                <div class="col-6">
                    <div class="fluid-container">

                        <div class="row mt-1">
                            <div id="likeStatisticsWrapper" class="col d-flex " style="height: 50px">

                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">

                                    <table id="likeTable" class="table table-bordered">

                                        <caption>
                                            <spring:message code="demo.like-vs-fts.table.caption.like"/>
                                        </caption>

                                        <thead class="d-block w-100 wr-2">

                                        <tr class="table-primary d-flex">
                                            <th scope="col" style="width: 120px;">#</th>
                                            <th scope="col" class="w-100">
                                                <spring:message code="sample.attribute.label.content"/>
                                            </th>
                                        </tr>

                                        </thead>

                                        <tbody id="likeTableBody" class="d-block w-100 wr-2" style="max-height: 650px; overflow-x: hidden; overflow-y: auto;">

                                        <tr class="d-flex">
                                            <th scope="row" style="width: 120px;">0</th>
                                            <td class="w-100">Sample in the LIKE search table.</td>
                                        </tr>

                                        </tbody>

                                    </table>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="col-6">
                    <div class="fluid-container">

                        <div class="row mt-1">
                            <div id="fullTextStatisticsWrapper" class="col d-flex" style="min-height: 50px">

                            </div>
                        </div>

                        <div class="row mt-1">
                            <div class="col">

                                <table id="fullTextTable" class="table table-bordered">

                                    <caption>
                                        <spring:message code="demo.like-vs-fts.table.caption.fts"/>
                                    </caption>

                                    <thead class="d-block w-100 wr-2">

                                    <tr class="table-primary d-flex">
                                        <th scope="col" style="width: 120px;">#</th>
                                        <th scope="col" class="w-100">
                                            <spring:message code="sample.attribute.label.content"/>
                                        </th>
                                    </tr>

                                    </thead>

                                    <tbody id="fullTextTableBody" class="d-block w-100 wr-2" style="max-height: 650px; overflow-x: hidden; overflow-y: auto;">

                                    <tr class="d-flex">
                                        <th scope="row" style="width: 120px;">0</th>
                                        <td class="w-100">Sample in the Full-Text Search table.</td>
                                    </tr>

                                    </tbody>

                                </table>

                            </div>
                        </div>

                    </div>
                </div>

            </div>

        </div>

        <div class="col"></div>

    </div>
</div>

</body>

</html>

<!-- jQuery, Popper.js, Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<spring:message code="demo.like-vs-fts.statistics.results" var="results"/>
<spring:message code="demo.like-vs-fts.statistics.seconds" var="seconds"/>

<script>

    $(document).ready(function () {

        var searchInputId = "#searchInput";
        var performSearchBtnId = "#performSearchBtn";

        var likeStatisticsWrapperId = "#likeStatisticsWrapper";
        var likeTableBodyId = "#likeTableBody";

        var fullTextStatisticsWrapperId = "#fullTextStatisticsWrapper";
        var fullTextTableBodyId = "#fullTextTableBody";

        $(searchInputId).keypress(function (event) {
            if (event.which === 13) {
                $(performSearchBtnId).click();
            }
        });

        $(performSearchBtnId).click(function () {

            var searchInputVal = $(searchInputId).val();

            if (searchInputVal) {

                // LIKE search request

                $.ajax({
                    type: "GET",
                    url: location.origin + "/api/demo/test-samples/search/like?input=" + searchInputVal,
                    dataType: "json"
                })
                    .done(function(response) {

                        console.log(response);

                        var testSamples = response.testSamples;

                        $(likeStatisticsWrapperId).empty();
                        $(likeTableBodyId).empty();

                        var likeStatistics = $("<div class='alert alert-primary w-100 m-0'>"
                            + "<p class='m-0'>${results}: " + testSamples.length + " (${seconds}: " + response.time + ")</p>"
                            + "</div>");

                        $(likeStatisticsWrapperId).append(likeStatistics);

                        var tableEntry;

                        for (var i = 0; i < testSamples.length; i++) {

                            tableEntry = $("<tr class='d-flex'>"
                                + "<th scope='row' style='width: 120px;'>" + testSamples[i].id + "</th>"
                                + "<td class='w-100'>" + testSamples[i].content + "</td>"
                                + "</tr>");

                            $(likeTableBodyId).append(tableEntry);

                        }

                        console.log("LIKE: OK!")

                    })
                    .fail(function () {
                        console.log("LIKE: Error is occurred!")
                    });

                // Full text search requesr

                $.ajax({
                    type: "GET",
                    url: location.origin + "/api/demo/test-samples/search/full-text?input=" + searchInputVal,
                    dataType: "json"
                })
                    .done(function(response) {

                        console.log(response);

                        var testSamples = response.testSamples;

                        $(fullTextStatisticsWrapperId).empty();
                        $(fullTextTableBodyId).empty();

                        var fullTextStatistics = $("<div class='alert alert-primary w-100 m-0'>"
                            + "<p class='m-0'>${results}: " + testSamples.length + " (${seconds}: " + response.time + ")</p>"
                            + "</div>");

                        $(fullTextStatisticsWrapperId).append(fullTextStatistics);

                        var tableEntry;

                        for (var i = 0; i < testSamples.length; i++) {

                            tableEntry = $("<tr class='d-flex'>"
                                + "<th scope='row' style='width: 120px;'>" + testSamples[i].id + "</th>"
                                + "<td class='w-100'>" + testSamples[i].content + "</td>"
                                + "</tr>");

                            $(fullTextTableBodyId).append(tableEntry);

                        }

                        console.log("Full text: OK!")

                    })
                    .fail(function () {
                        console.log("Full text: Error is occurred!")
                    });

            }

        });

    });

</script>