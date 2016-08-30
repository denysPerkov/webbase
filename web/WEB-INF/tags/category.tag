<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<select name="category"  class="select" multiple size="3">
<c:forEach var="product" items="${categoryes}">
    <option value="${product}">${product}</option>
    <br>
</c:forEach>
</select>