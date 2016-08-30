<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>W3.CSS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-black.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/logformStyle.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/productStyle.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/pagin.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/basket.css">
     <link rel="stylesheet" type="text/css" href="/resources/css/lang.css">

    <style>
        html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
        .w3-sidenav a,.w3-sidenav h4{padding:12px;}
        .w3-navbar a{padding-top:12px !important;padding-bottom:12px !important;}
    </style>
</head>

<body>

<!-- Navbar -->
<ul class="w3-navbar w3-theme w3-top w3-left-align w3-large" style="z-index:4;">
    <li class="w3-opennav w3-right w3-hide-large">
        <a class="w3-hover-white w3-large w3-theme-l1" href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a>
    </li>
    <li><a href="#" class="w3-theme-l1">Logo</a></li>
    <li class="w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
    <li class="w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
    <li class="w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
    <li class="w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
    <li class="w3-hide-medium w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
    <li class="w3-hide-medium w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
    <li class="w3-hide-medium w3-hide-small"><a href="#" class="w3-hover-white">#</a></li>
</ul>



<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"></div>

<!-- Main content: shift it to the right by 250 pixels when the sidenav is visible -->
<div class="w3-main" style="margin-left:1px">

    <div class="login_form">
        <mt:loginForm></mt:loginForm>
        <div class="bask" style="
           padding-top: 5%;
           text-align: center;">
            <table>
                <tr>
                    <th>Count</th>
                    <th>Sum cost</th>
                </tr>
                <tr>
                    <td><div id="basketCount"></div></td>
                    <td><div id="basketSum"></div></td>
                </tr>
            </table>
            <a href="cart">To cart</a>

        <mt:switchLang></mt:switchLang>
        </div>
    </div>

    <div class="product">
        <mt:product></mt:product>
    </div>

    <div class="filterform">
        <form action="home" method="get">
            <br>
            <label><fmt:message key="home.name"/></label> <br>
            <input type="text" name="name" id="Name" class="inputname">
            <br>
            <br>
            <label><fmt:message key="home.price"/>:</label> <br>
            <fmt:message key="home.from"/> <input type="number" name="fromPrice" class="inputcost" min="1">
            <fmt:message key="home.to"/> <input type="number" name="toPrice" class="inputcost" min="1">
            <br>
            <br>
            <label><fmt:message key="home.category"/>: </label>
            <br>
            <mt:category></mt:category>
            <br>
            <br>
            <label><fmt:message key="home.producer"/>: </label>
            <br>
            <mt:manufacturer></mt:manufacturer>
            <br>
            <br>
            <label><fmt:message key="home.sort"/>: </label> <br>
            <input id="nameField" type="radio" name="sortField" value="name" checked>
            <label for="nameField" class="label"><fmt:message key="home.name"/></label>
            <input id="costField" type="radio" name="sortField" value="cost">
            <label for="costField" class="label"><fmt:message key="home.cost"/></label>
            <br>
            <input id="ASC" type="radio" name="sortType" value="ASC" checked>
            <label for="ASC" class="label">Ascend</label>
            <input id="DESC" type="radio" name="sortType" value="DESC">
            <label for="DESC" class="label">Descend</label>
            <br>
            <label><fmt:message key="home.count"/>: </label> <br>
            <input type="number" name="countProduct" min="1">

            <input type="hidden" name="currentPage" value="${requestScope.currentpage}">
            <input type="submit" value="Filter">
        </form>
    </div

    <%------------------------------------------------------------------------%>


    <%------------------------------------------------------------------------%>
    <div class="pagin">
        <br>
        <mt:pagin></mt:pagin>
    </div>
    <%------------------------------------------------------------------------%>

</div>



<script>
    // Script to open and close the sidenav
    function w3_open() {
        document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
        document.getElementsByClassName("w3-overlay")[0].style.display = "block";
    }

    function w3_close() {
        document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
        document.getElementsByClassName("w3-overlay")[0].style.display = "none";
    }
</script>

</body>
</html>