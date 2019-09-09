<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag description="select element on enum values" pageEncoding="UTF-8" %>

<%@ tag import="edu.sam.aveng.base.model.domain.enumeration.Status" %>

<label>
    <select class="form-control" id="statusSelect" name="status">
        <jstl:forEach items="${Status.values()}" var="status">
            <option>${status.name()}</option>
        </jstl:forEach>
    </select>
</label>