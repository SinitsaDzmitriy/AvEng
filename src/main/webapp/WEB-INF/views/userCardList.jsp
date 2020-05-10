<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="edu.sam.aveng.base.model.enumeration.Status" %>

<jstl:forEach items="${userCardList}" var="userCardItem">
    <div class="row panel repeated-panel custom-border">
        <div class="col-2">
            <div class="m-1">
                <jstl:choose>
                    <jstl:when test="${userCardItem.status == Status.NEW}">
                        <span class="badge badge-warning" style="margin: 0.1425em">${Status.NEW}</span>
                    </jstl:when>

                    <jstl:when test="${userCardItem.status == Status.DUBIOUS}">
                        <span class="badge badge-info m-1" style="margin: 0.1425em">${Status.DUBIOUS}</span>
                    </jstl:when>

                    <jstl:when test="${userCardItem.status == Status.KNOWN}">
                        <span class="badge badge-success m-1" style="margin: 0.1425em">${Status.KNOWN}</span>
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