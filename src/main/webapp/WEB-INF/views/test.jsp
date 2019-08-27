<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<style>
    .w-10 {
        width: 10% !important;
    }
    .w-80 {
        width: 80% !important;
    }
</style>

<mytags:overallBasePage>

    <%--    <div class="h-100 w-100 d-flex align-items-center justify-content-center">--%>
    <%--        <div class="h-75 w-75 bg-light">--%>

    <%--    <div id="tempCarouselId" class="carousel slide w-75 h-75" data-ride="carousel">--%>

    <%--        <div class="carousel-inner">--%>

    <%--            <div class="carousel-item active">--%>
    <%--                <spring:url value="/resources/images/temp.jpg" var="tempImgPath"/>--%>
    <%--                <img src="${tempImgPath}" class="d-block w-100" alt="...">--%>
    <%--            </div>--%>

    <%--            <div class="carousel-item">--%>
    <%--                <spring:url value="/resources/images/temp.jpg" var="tempImgPath"/>--%>
    <%--                <img src="${tempImgPath}" class="d-block w-100" alt="...">--%>
    <%--            </div>--%>

    <%--        </div>--%>

    <%--        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">--%>
    <%--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
    <%--            <span class="sr-only">Previous</span>--%>
    <%--        </a>--%>

    <%--        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">--%>
    <%--            <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
    <%--            <span class="sr-only">Next</span>--%>
    <%--        </a>--%>

    <%--    </div>--%>

    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">

        <div class="carousel-inner w-80 m-auto">

            <div class="carousel-item active h-100 w-100">
                <spring:url value="/resources/images/pinkBg.jpg" var="pinkBgPath"/>
                <img src="${pinkBgPath}" class="d-block w-100" alt="...">
            </div>

            <div class="carousel-item h-100 w-100">
                <spring:url value="/resources/images/blueBg.jpg" var="blueBgPath"/>
                <img src="${blueBgPath}" class="d-block w-100" alt="...">
            </div>

        </div>

        <a class="carousel-control-prev w-10" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>

        <a class="carousel-control-next w-10" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>

    </div>

    <%--    <div id="carouselExampleControls d-flex justify-content-center" class="carousel slide" data-ride="carousel">--%>

    <%--        <div class="carousel-inner w-50 m-auto">--%>

    <%--            <div class="carousel-item active h-100 w-100 bg-light d-flex justify-content-center" >--%>

    <%--                <div class="form-group w-75">--%>
    <%--                    <label for="exampleInputEmail1">Email address</label>--%>
    <%--                    <input type="email" class="form-control" id="exampleInputEmail1"--%>
    <%--                           aria-describedby="emailHelp"--%>
    <%--                           placeholder="Enter email">--%>
    <%--                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone--%>
    <%--                        else.--%>
    <%--                    </small>--%>
    <%--                </div>--%>

    <%--            </div>--%>

    <%--            <div class="carousel-item h-100 w-100 bg-light">--%>

    <%--                <div class="form-group">--%>
    <%--                    <label for="exampleInputPassword1">Password</label>--%>
    <%--                    <input type="password" class="form-control" id="exampleInputPassword1"--%>
    <%--                           placeholder="Password">--%>
    <%--                </div>--%>
    <%--                <div class="form-group form-check">--%>
    <%--                    <input type="checkbox" class="form-check-input" id="exampleCheck1">--%>
    <%--                    <label class="form-check-label" for="exampleCheck1">Check me out</label>--%>
    <%--                </div>--%>
    <%--                <button type="submit" class="btn btn-primary">Submit</button>--%>

    <%--            </div>--%>

    <%--        </div>--%>
    <%--        <a class="carousel-control-prev w-25" href="#carouselExampleControls" role="button" data-slide="prev">--%>
    <%--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
    <%--            <span class="sr-only">Previous</span>--%>
    <%--        </a>--%>
    <%--        <a class="carousel-control-next w-25" href="#carouselExampleControls" role="button" data-slide="next">--%>
    <%--            <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
    <%--            <span class="sr-only">Next</span>--%>
    <%--        </a>--%>

    <%--    </div>--%>

    <%--    <div class="h-100 w-100 bg-light d-flex align-items-stretch">--%>


    <%--        <form id="carouselExampleControls w-100 m-0" class="carousel slide" data-ride="carousel">--%>

    <%--            <div class="carousel-inner">--%>

    <%--                <div class="carousel-item active">--%>

    <%--                    <div class="form-group">--%>
    <%--                        <label for="exampleInputEmail1">Email address</label>--%>
    <%--                        <input type="email" class="form-control" id="exampleInputEmail1"--%>
    <%--                               aria-describedby="emailHelp"--%>
    <%--                               placeholder="Enter email">--%>
    <%--                        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone--%>
    <%--                            else.--%>
    <%--                        </small>--%>
    <%--                    </div>--%>

    <%--                </div>--%>

    <%--                <div class="carousel-item">--%>

    <%--                    <div class="form-group">--%>
    <%--                        <label for="exampleInputPassword1">Password</label>--%>
    <%--                        <input type="password" class="form-control" id="exampleInputPassword1"--%>
    <%--                               placeholder="Password">--%>
    <%--                    </div>--%>
    <%--                    <div class="form-group form-check">--%>
    <%--                        <input type="checkbox" class="form-check-input" id="exampleCheck1">--%>
    <%--                        <label class="form-check-label" for="exampleCheck1">Check me out</label>--%>
    <%--                    </div>--%>
    <%--                    <button type="submit" class="btn btn-primary">Submit</button>--%>

    <%--                </div>--%>

    <%--            </div>--%>
    <%--            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">--%>
    <%--                <span class="carousel-control-prev-icon" aria-hidden="true"></span>--%>
    <%--                <span class="sr-only">Previous</span>--%>
    <%--            </a>--%>
    <%--            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">--%>
    <%--                <span class="carousel-control-next-icon" aria-hidden="true"></span>--%>
    <%--                <span class="sr-only">Next</span>--%>
    <%--            </a>--%>

    <%--        </form>--%>

    <%--    </div>--%>
    <%--        </div>--%>
    <%--    </div>--%>

</mytags:overallBasePage>