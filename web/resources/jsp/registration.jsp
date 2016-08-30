<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>W3.CSS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-black.css    ">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/registration.css">

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
<div class="w3-main" style="margin-left:250px">

    <div class="w3-row w3-padding-hor-64">
    </div>
    <div class="parent">
        <div class="block">
            <form action="register" method="post" enctype="multipart/form-data">

                <label for="firstName">First name</label>
                <input type="text" name="firstName" value="${user.firstName}" id="firstName" pattern="[A-Za-z0-9_]{2,25}" required
                       title="Use latin and numbers, (5-15)"/>

                <label for="lastName">Last name</label>
                <input type="text" name="lastName" value="${user.lastName}" id="lastName" pattern="[A-Za-z0-9_]{2,25}" required
                       title="Use latin and numbers, (5-15)"/>

                <label for="login">Login</label>
                <input type="text" name="login" id="login" pattern="[A-Za-z0-9_]{5,15}" required
                       title="Use latin and numbers, (5-15)"/>

                <label for="password">Password</label>
                <input type="password" name="password" id="password" pattern="[A-Za-z0-9_]{5,15}" required
                       title="Use latin and numbers, (5-15)"/>

                <label for="email">Email</label>
                <input type="email" name="email" value="${user.email}" id="email" required
                       title="email@mail.com"/>

                <br><br>
                <input type="checkbox" class="checkbox" id="checkbox" />
                <label for="checkbox">Receive information about new products</label>

                <br><br>
                <input type="checkbox" class="checkbox" id="checkbox3" />
                <label for="checkbox3">get information about discounts</label>

                <br> <br>
                <input type="checkbox" class="checkbox" id="checkbox2" />
                <label for="checkbox2">Receive information about the event</label>
                <br><br>
                <mt:captchaTag></mt:captchaTag>
                <br>

                <label>Input captcha</label>
                <input type="text" name="capt" required>

                <br>
                <label>Upload avatar</label>
                <div class="file_upload">+<input type="file" name="fileName"></div>

                <input type="hidden" name="hiddenTime" value="${requestScope.startTime}">
                <input type="submit" value="Submit" />

            </form>

        </div>
    </div>

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
