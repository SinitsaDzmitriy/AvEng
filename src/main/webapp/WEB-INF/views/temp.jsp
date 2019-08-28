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
</style>

<mytags:overallBasePage>

    <div class="bg-secondary">
        <div id="carouselExampleControls" class="carousel slide" data-interval="false">

            <div class="carousel-inner w-80 m-auto">

                <spring_form:form method="POST" modelAttribute="cardDto">

                    <!-- element attribute: Defines a HTML tag in which errors are rendered -->
                    <spring_form:errors path="*" element="div" cssClass="errors"/>

                    <div class="carousel-item active">

                        <div class="h-100 w-100 bg-light d-flex align-items-center justify-content-center">
                            <div>

                                <div class="form-group">

                                    <spring_form:label for="enabledContentInput"
                                                       path="content"
                                                       cssErrorClass="error">
                                        <spring:message code="card.attribute.content"/>
                                    </spring_form:label><br>

                                    <spring_form:input id="enabledContentInput"
                                                       type="text"
                                                       class="form-control"
                                                       path="content"
                                                       placeholder="Enter word or phrase"
                                                       cssErrorClass="error"/><br>

                                </div>

                                    <%--                                <div class="form-group">--%>
                                    <%--                                    <label for="exampleInputEmail1">Email address</label>--%>
                                    <%--                                    <input type="email" class="form-control" id="exampleInputEmail1"--%>
                                    <%--                                           aria-describedby="emailHelp" placeholder="Enter email">--%>
                                    <%--                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with--%>
                                    <%--                                        anyone--%>
                                    <%--                                        else.--%>
                                    <%--                                    </small>--%>
                                    <%--                                </div>--%>

                            </div>
                        </div>

                    </div>

                    <div class="carousel-item">

                        <div class="h-100 w-100 bg-light d-flex align-items-center justify-content-center">
                            <div>

                                <spring_form:label for="enabledContentInput"
                                                   path="content"
                                                   cssErrorClass="error">
                                    <spring:message code="card.attribute.content"/>
                                </spring_form:label><br>

                                <input id="enabledContentInput"
                                                   type="text"
                                                   class="form-control"
                                                   placeholder="Enter word or phrase"
                                                   readonly/><br>

                                <spring:message code="card.attribute.type"/><br>
                                <spring_form:select path="type" cssErrorClass="errors">
                                    <spring_form:option value="" selected="true"></spring_form:option>
                                    <spring_form:options/>
                                </spring_form:select><br>

                                <spring_form:label path="pron.transcription" cssErrorClass="error">
                                    <spring:message code="card.attribute.transcription"/>
                                </spring_form:label><br>
                                <spring_form:input path="pron.transcription" cssErrorClass="error"/><br>

                                <spring_form:label path="definition" cssErrorClass="error">
                                    <spring:message code="card.attribute.definition"/>
                                </spring_form:label><br>
                                <spring_form:input path="definition" cssErrorClass="error"/><br>

                                <spring:message code="card.attribute.samples"/><br>
                                <spring_form:input path="samples[0].content"/><br>

                                <input type="submit" value=<spring:message code="button.user.register"/>>

                                    <%--                                <div class="form-group">--%>
                                    <%--                                    <label for="exampleInputPassword1">Password</label>--%>
                                    <%--                                    <input type="password" class="form-control" id="exampleInputPassword1"--%>
                                    <%--                                           placeholder="Password">--%>
                                    <%--                                </div>--%>
                                    <%--                                <button type="submit" class="btn btn-primary">Login</button>--%>

                            </div>
                        </div>
                    </div>

                </spring_form:form>

            </div>

            <a class="carousel-control-prev w-10" href="#carouselExampleControls" role="button"
               data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>

            <a class="carousel-control-next w-10" href="#carouselExampleControls" role="button"
               data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>


        </div>

</mytags:overallBasePage>