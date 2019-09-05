<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<mytags:overallBasePage>

    <div class="container-fluid">


        <div class="row">

            <div class="col-2">
            <span class="text-secondary">
                English
            </span>
            </div>

            <div class="col-8">

                <div class="row">

                    <b class="text-primary">desertification</b>
                    &nbsp;
                    <i>noun</i>

                </div>

                <div class="row">
                    <p>
                        the process by which land changes into desert, for example because there has been too
                        much
                        farming
                        activity on it or because a lot of trees have been cut down
                    </p>
                </div>

            </div>

            <div class="col-2">

                <spring:url value="/resources/images/playPronIcon.svg" var="playPronIconPath"/>
                <img src="${playPronIconPath}" width="28" height="28" alt="playPronIcon">

                <h6 class="d-inline-block">|</h6>

                <spring:url value="/resources/images/deleteIcon.svg" var="deleteIconPath"/>
                <img src="${deleteIconPath}" width="28" height="28" alt="deleteIcon">

            </div>


        </div>

        <div class="row">

            <div class="col-2">
            <span class="text-secondary">
                English
            </span>
            </div>

            <div class="col-8">

                <div class="row">

                    <b class="text-primary">desertification</b>
                    &nbsp;
                    <i>noun</i>

                </div>

                <div class="row">
                    <p>
                        the process by which land changes into desert, for example because there has been too
                        much
                        farming
                        activity on it or because a lot of trees have been cut down
                    </p>
                </div>

            </div>

            <div class="col-2">

                <spring:url value="/resources/images/playPronIcon.svg" var="playPronIconPath"/>
                <img src="${playPronIconPath}" width="28" height="28" alt="playPronIcon">

                <h6 class="d-inline-block">|</h6>

                <spring:url value="/resources/images/deleteIcon.svg" var="deleteIconPath"/>
                <img src="${deleteIconPath}" width="28" height="28" alt="deleteIcon">

            </div>

        </div>

    </div>

</mytags:overallBasePage>