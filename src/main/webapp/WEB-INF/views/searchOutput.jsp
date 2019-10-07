<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<style>
    .text-orangered {
        color: orangered;
    }
</style>

<spring:message code="title.cards.search" var="pageTitlePart"/>
<mytags:overallBasePage pageTitle="${pageTitlePart}: ${searchInput}">

    <div class="container-fluid w-75">

        <jstl:if test="${searchOutput.size() == 0}">

            <div class="row">
                <div class="col">
                    <div class="alert alert-warning m-0" role="alert">

                        <p class="m-0 text-body">
                            <spring:message code="cards.search.alert.no-results.main" arguments="<b><i>${searchInput}</i></b>"/>
                        </p>
                        <p class="m-0 text-body font-weight-bold">
                            <spring:message code="cards.search.alert.no-results.tip.lang"/>
                        </p>
                        <p class="m-0 text-body font-weight-bold">
                            <spring:message code="cards.search.alert.no-results.tip.typos"/>
                        </p>
                        <p class="m-0 text-body">
                            <spring:message code="cards.search.alert.no-results.conclusion"/>
                        </p>

                    </div>
                </div>
            </div>


        </jstl:if>

        <jstl:forEach items="${searchOutput}" var="searchItem">

            <div class="row border border-secondary p-3 my-2">

                <div class="col">

                    <spring:url value="/cards/display/${searchItem.get('id')}" var="cardAnchor"/>
                    <a href="${cardAnchor}">
                        <h4>${searchItem.get("content")}</h4>
                    </a>

                    <span>•</span>
                    <i>${searchItem.get("type")}</i>
                    <span class="text-orangered">[${searchItem.get("transcription")}]</span>

                    <spring:url value="/resources/images/playPronIcon.svg" var="playPronIconPath"/>
                    <img src="${playPronIconPath}" style="cursor: pointer;"
                         width="28" height="28" alt="Play pronunciation icon"
                         onclick="playPron('${searchItem.get("content")}', '${searchInputLang.code}')">

                    <br>

                    <b>${searchItem.get("definition")}</b>

                    <br>

                    <jstl:if test="${searchItem.coupledCards.size() > 0}">

                        <h6><spring:message code="cards.search.label.translations"/>:</h6>

                        <ul class="m-0">
                            <jstl:forEach items="${searchItem.coupledCards}" var="card">

                                <li>
                                    <spring:url value="/cards/display/${card.get('id')}" var="coupledCardAnchor"/>
                                    <a href="${coupledCardAnchor}">
                                        ${card.get("content")}
                                    </a>
                                </li>

                            </jstl:forEach>
                        </ul>

                    </jstl:if>

                </div>

            </div>

        </jstl:forEach>

    </div>

    <script>

        function playPron(text, lang) {
            var msg = new SpeechSynthesisUtterance();
            msg.text = text;
            msg.lang = lang;
            speechSynthesis.speak(msg);
        }

    </script>

</mytags:overallBasePage>