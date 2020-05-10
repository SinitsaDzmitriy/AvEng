<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring_form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page import="edu.sam.aveng.base.model.enumeration.Status" %>
<%@ page import="edu.sam.aveng.base.model.enumeration.StatementType" %>

<style>
    .btn-secondary {
        background-color: rgb(206, 206, 206) !important;
        border-radius: 10px !important;
    }

    #content {
        font-family: Calibri, sans-serif;
    }

    .custom-border {
        border: solid rgb(206, 206, 206);
        border-radius: 15px;
    }

    .panel {
        padding: 0.2em;
    }

    .repeated-panel {
        margin-bottom: 0.2em;
    }

    .filter-panel {
        float: right;
        width: 85%;
    }

    .badge {
        font-size: 1em !important;
    }

    .query-panel {
        display: flex;
        width: 100%;
    }

    .query-panel .query-input {
        border-radius: 15px;
        padding-left: 0.5em;
        padding-right: 0.5em;
        flex: 1;
        width: 100%;
    }

    .query-panel .query-input::placeholder {
        font-style: italic;
        font-size: 1.25em;
    }

    .test {
        display: block;
        height: 40px;
        width: 40px;
        border-radius: 50%;
        background-color: rgb(206, 206, 206);
        margin: 0.1em
    }
</style>

<spring:message code="title.user-cards.display.all" var="pageTitle"/>
<mytags:overallBasePage pageTitle="${pageTitle}">
    <div id="content" class="container-fluid">
        <div class="row">
            <div class="col-2">
                <div id="statusList" class="panel filter-panel repeated-panel custom-border">
                    <div class="form-check m-2">
                        <input id="newStatusCheckbox" class="form-check-input statusCheckbox" type="checkbox"
                               value="${Status.NEW}" onclick="refresh()">
                        <label class="form-check-label badge badge-warning" for="newStatusCheckbox">
                                ${Status.NEW}
                        </label>
                    </div>

                    <div class="form-check m-2">
                        <input id="dubiousStatusCheckbox" class="form-check-input statusCheckbox" type="checkbox"
                               value="${Status.DUBIOUS}" onclick="refresh()">
                        <label class="form-check-label badge badge-info statusCheckbox" for="dubiousStatusCheckbox">
                                ${Status.DUBIOUS}
                        </label>
                    </div>

                    <div class="form-check m-2">
                        <input id="knownStatusCheckbox" class="form-check-input statusCheckbox" type="checkbox"
                               value="${Status.KNOWN}" onclick="refresh()">
                        <label class="form-check-label badge badge-success" for="knownStatusCheckbox">
                                ${Status.KNOWN}
                        </label>
                    </div>
                </div>

                <div id="typeList" class="panel filter-panel repeated-panel custom-border">
                    <jstl:forEach items="${StatementType.values()}" var="type">
                        <div class="form-check m-2">
                            <jstl:set var="name" value="${type.toString().toLowerCase()}"/>
                            <input class="form-check-input typeCheckbox" type="checkbox" id="${name}Checkbox"
                                   value="${type}" onclick="refresh()">
                            <label class="form-check-label" for="${name}Checkbox">
                                    ${type}
                            </label>
                        </div>
                    </jstl:forEach>
                </div>
            </div>

            <div class="col-8">
                <div class="panel query-panel custom-border mb-4">
                    <input id="query" class="query-input border-0" type="text" placeholder="Search...">
                    <button class="btn btn-secondary border-0 shadow-none" onclick="refresh()">
                        <spring:url value="/resources/images/searchIcon.svg" var="searchIcon"/>
                        <img width="20px" height="20px" src="${searchIcon}"/>
                    </button>
                </div>

                <div class="container-fluid">
                    <jstl:if test="${userCardTableItems.size() == 0}">
                        <div class="row">
                            <div class="col">
                                <div class="alert alert-success" role="alert">
                                    <spring:url value="/resources/images/addIcon.svg" var="addIcon"/>
                                    <jstl:set var="img"
                                              value="<img src='${addIcon}' width='28' height='28' alt='addIcon' style='margin-left: 0.2rem; margin-right: 0.2rem; background-color: white; border-radius: 50%;'>"/>
                                    <spring:message code="user-cards.display.all.alert.empty" arguments="${img}"/>
                                </div>
                            </div>
                        </div>
                    </jstl:if>

                    <!-- Cards: start -->
                    <div id="userCardList">
                    <jstl:forEach items="${userCardTableItems}" var="userCardItem">
                        <div class="row panel repeated-panel custom-border">
                            <div class="col-2">
                                <div class="m-1">
                                    <jstl:choose>
                                        <jstl:when test="${userCardItem.status == Status.NEW}">
                                            <span class="badge badge-warning" style="margin: 0.135em">${Status.NEW}</span>
                                        </jstl:when>

                                        <jstl:when test="${userCardItem.status == Status.DUBIOUS}">
                                            <span class="badge badge-info m-1" style="margin: 0.11em">${Status.DUBIOUS}</span>
                                        </jstl:when>

                                        <jstl:when test="${userCardItem.status == Status.KNOWN}">
                                            <span class="badge badge-success m-1" style="margin: 0.10em">${Status.KNOWN}</span>
                                        </jstl:when>
                                    </jstl:choose>

                                    <span class="text-secondary mx-1">${userCardItem.lang}</span>
                                </div>
                            </div>

                            <div class="col-8">
                                <div class="row">

                                    <label class="text-primary" style="font-size: 1.5em; font-weight: bold; margin-bottom: 0;">
                                        <spring:url value="/user_cards/display/${userCardItem.userCardId}"
                                                    var="userCardAnchor"/>
                                        <a href="${userCardAnchor}">
                                                ${userCardItem.content}
                                        </a>
                                    </label>
                                    &nbsp;
                                    <i>${userCardItem.type}</i>

                                </div>

                                <div class="row">
                                    <p style="line-height: 1.2em;">${userCardItem.definition}</p>
                                </div>

                            </div>

                            <div class="col-2 d-flex justify-content-center" style="align-items: center;">
                                <spring:url value="/resources/images/playPronIcon.svg" var="playPronIconPath"/>
                                <div style="display: flex; align-items: center; cursor: pointer;" class="test">
                                    <img style="display: block; margin:auto;" src="${playPronIconPath}" width="25" height="25" alt="Play pronunciation icon"
                                         onclick="playPron('${userCardItem.content}', '${userCardItem.lang.code}')">
                                </div>

                                <spring:url value="/resources/images/deleteIcon.svg" var="deleteIconPath"/>
                                <div style="display: flex; align-items: center; cursor: pointer;" class="test">
                                    <img style="display: block; margin:auto;" src="${deleteIconPath}" width="25" height="25" alt="Delete icon"
                                         onclick="deleteUserCard(${userCardItem.userCardId})">
                                </div>
                            </div>

                        </div>
                    </jstl:forEach>
                    </div>
                    <!-- Cards: end -->

                </div>
            </div>

            <div class="col-2"></div>
        </div>


    </div>

    <script>

        function playPron(text, lang) {
            var msg = new SpeechSynthesisUtterance();
            msg.text = text;
            msg.lang = lang;
            speechSynthesis.speak(msg);
        }

        function deleteUserCard(userCardId) {

            $.ajax({
                type: "DELETE",
                url: location.origin + "/api/user_cards/delete/" + userCardId,
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
        }

        function refresh() {
            let urlSearchParams = new URLSearchParams();
            urlSearchParams.append('lang', 'ENGLISH');

            let query = document.getElementById('query').value;

            let statusNodeList = document.getElementById('statusList')
                .querySelectorAll('input.statusCheckbox[type=checkbox]:checked');
            let statusList = Array.from(statusNodeList).map(statusNode => statusNode.value);

            let typeNodeList = document.getElementById('typeList')
                .querySelectorAll('input.typeCheckbox[type=checkbox]:checked');
            let typeList = Array.from(typeNodeList).map(statusNode => statusNode.value);

            if (query) {
                urlSearchParams.append('query', query)
            }
            if (statusList && statusList.length) {
                urlSearchParams.append('statusList', statusList.join());
            }
            if (typeList && typeList.length) {
                urlSearchParams.append('typeList', typeList.join());
            }

            let url = new URL(location.origin + '/user_cards/search');
            url.search = urlSearchParams;
            console.log(url.toString());
            fetch(url.toString())
                .then((response) => {
                    console.log(response.status);
                    return response.text();
                })
                .then((data) => {
                    console.log(data);
                    document.getElementById('userCardList').innerHTML = data;
                });
        }
    </script>

</mytags:overallBasePage>