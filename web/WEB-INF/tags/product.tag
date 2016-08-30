<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/productStyle.css">


<script type="text/javascript">
   function submitForm(id) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var respString = xhr.responseText.split('~');
                var cost = respString[0];
                var count = respString[1];
                document.getElementById("basketCount").innerHTML = count;
                document.getElementById("basketSum").innerHTML = cost;
            }
        };
        var idProduct = document.getElementById("idProduct".concat(id)).value;
        var name = document.getElementById("name".concat(id)).value;
        var cost = document.getElementById("cost".concat(id)).value;
        var producer = document.getElementById("producer".concat(id)).value;
        var category = document.getElementById("category".concat(id)).value;
        var countProd = document.getElementById("countProd".concat(id)).value;


        var url = "basket?name=" + name + "&idProduct=" + idProduct + "&cost=" + cost +
                "&producer=" + producer + "&category=" + category + "&countProd=" + countProd;
        xhr.open("GET", url , true);
        xhr.send();
    }
</script>


<c:forEach var="product" items="${productList}">
    <br>
    <c:if test="${requestScope.image == null}">
        <img src="http://www.westliguria.net/wp-content/uploads/2013/01/image-not-found.jpg" class="productimg">
    </c:if>
    <c:if test="${requestScope.image != null}" >
        <img src="${requestScope.image}" class="productimg">
    </c:if>

    <br>
        Name: ${product.name}
    <br>
        Cost: ${product.cost} $
    <br>
        Manufacturer: ${product.producer}
    <br>
        Category: ${product.category}
    <br>

    <form action="" id="prodForm">
        <input type="hidden" name="name" value="${product.name}" id="name${product.idProduct}">
        <input type="hidden" name="cost" value="${product.cost}" id="cost${product.idProduct}">
        <input type="hidden" name="producer" value="${product.producer}" id="producer${product.idProduct}">
        <input type="hidden" name="category" value="${product.category}" id="category${product.idProduct}">
        <input type="hidden" name="idProduct" value="${product.idProduct}" id="idProduct${product.idProduct}">

        <input type="number" id="countProd${product.idProduct}" class="inputcost" min="1">
        <input type="button" value="Submit" onclick="submitForm(${product.idProduct})">
    </form>
</c:forEach>