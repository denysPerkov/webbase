<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>

        <c:if test="${requestScope.currentPage != 1}">
            <td><a href="home?page=${requestScope.currentPage - 1}">Previous</a></td>
        </c:if>

        <c:forEach begin="1" end="${sessionScope.countPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="home?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${requestScope.currentPage lt sessionScope.countPages}">
            <td><a href="home?page=${currentPage + 1}">Next</a></td>
        </c:if>
    </tr>
</table>


