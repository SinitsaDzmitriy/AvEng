<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page import="edu.sam.aveng.base.model.domain.enumeration.Status" %>

<mytags:overallBasePage>

    <div class="container-fluid w-75">

        <jstl:forEach items="${userCardTableItems}" var="userCardItem">

            <div class="row border border-secondary p-3 my-2">

                <div class="col-2">

                        <span class="text-secondary">
                                ${userCardItem.lang}
                        </span>

                    <br>

                    <jstl:choose>

                        <jstl:when test="${userCardItem.status == Status.NEW}">
                            <span class="badge badge-warning">
                                NEW
                            </span>
                        </jstl:when>

                        <jstl:when test="${userCardItem.status == Status.DUBIOUS}">
                            <span class="badge badge-info">
                                DUBIOUS
                            </span>
                        </jstl:when>

                        <jstl:when test="${userCardItem.status == Status.FAVOURITE}">
                            <span class="badge badge-success">
                                FAVOURITE
                            </span>
                        </jstl:when>

                    </jstl:choose>


                </div>

                <div class="col-8">

                    <div class="row">

                        <b class="text-primary">
                            <spring:url value="/user_cards/display/${userCardItem.userCardId}" var="userCardAnchor"/>
                            <a href="${userCardAnchor}">
                                    ${userCardItem.content}
                            </a>
                        </b>
                        &nbsp;
                        <i>${userCardItem.type}</i>

                    </div>

                    <div class="row">
                        <p>
                                ${userCardItem.definition}
                        </p>
                    </div>

                </div>

                <div class="col-2 d-flex justify-content-end">

                    <spring:url value="/resources/images/playPronIcon.svg" var="playPronIconPath"/>
                    <img src="${playPronIconPath}" width="28" height="28" alt="playPronIcon">

                    <h5 class="d-inline-block">|</h5>

                        <%--                    <spring:url value="/resources/images/deleteIcon.svg" var="deleteIconPath"/>--%>
                        <%--                    <img src="${deleteIconPath}" width="28" height="28" alt="deleteIcon" onclick="">--%>

                    <spring:url value="/resources/images/deleteIcon.svg" var="deleteIconPath"/>
                    <input type="image" src="${deleteIconPath}" alt="deleteIcon" width="28" height="28"
                           onclick="deleteUserCard(${userCardItem.userCardId})">

                </div>

            </div>

        </jstl:forEach>

    </div>

    <script>

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


    </script>

</mytags:overallBasePage>