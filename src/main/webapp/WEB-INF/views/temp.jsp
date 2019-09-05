<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<mytags:overallBasePage>

    <div class="container-fluid">

        <div class="input-group">

            <input class="form-control rounded-left border-secondary shadow-none" type="search" placeholder="Search"
                   aria-label="Search">

            <div class="input-group-append">

                <select class="form-control border-left-0 border-right-0 border-secondary shadow-none rounded-0">
                    <option disabled selected value>from</option>
                    <option>en</option>
                    <option>ru</option>
                    <option>de</option>
                </select>

                <div class="d-flex align-items-center border border-secondary">
                    <spring:url value="/resources/images/swapLangsIcon.svg" var="swapLangsIconPath"/>
                    <img class="mx-1" src="${swapLangsIconPath}" width="30" height="30" alt="swapLangsIconPath">
                </div>

                <select class="form-control border-left-0 border-right-0 border-secondary shadow-none rounded-0">
                    <option disabled selected value>to</option>
                    <option>en</option>
                    <option>ru</option>
                    <option>de</option>
                </select>

                <button class="btn btn-outline-secondary shadow-none rounded-right" type="button">
                    Search
                </button>

            </div>

        </div>

    </div>


</mytags:overallBasePage>