<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<img src="generate" class="captcha">
<input type="hidden" value="${requestScope.generateID}" name="hiddenField">
<input type="hidden" value="${requestScope.startTime}" name="hiddenTime">

