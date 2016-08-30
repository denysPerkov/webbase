<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.firstName == null}">
    <form action="login" method="post">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" pattern="[A-Za-z0-9_]{5,15}" required class="inputtext">

        <label for="pass">Password</label>
        <input type="password" id="pass" name="password" pattern="[A-Za-z0-9_]{5,15}" required>

        <input type="submit" value="Submit"><br>
        <a href="register">Registration</a>
    </form>
</c:if>

<c:if test="${sessionScope.firstName != null}">
    <img src="avatar" class="ava">
    <br>
    ${sessionScope.firstName}, you're logged in
    <form action="logout" method="post">
        <div class="field">
            <input type="submit" value="Logout" class="button">
        </div>
    </form>

</c:if>
