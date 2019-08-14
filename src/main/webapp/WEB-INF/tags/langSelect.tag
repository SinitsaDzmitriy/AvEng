<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag description="select element on enum values" pageEncoding="UTF-8" %>

<%@ tag import="edu.sam.aveng.domain.enumeration.Lang" %>

<script>
    function changeLang(value) {
        <c:set var="script" value="'" />
    }
</script>

<label>
    <select onchange="location.href = this.options[this.selectedIndex].value">
        <option selected disabled> -- change language -- </option>
        <jstl:forEach items="${Lang.values()}" var="lang">
            <jstl:set var="url" value="?lang=${lang.code}" />
            <option value=${url}>${lang.name}</option>
        </jstl:forEach>
    </select>
</label>