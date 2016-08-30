<%@ tag pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="overflow-x:auto;">
    <table id="tableID">
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Category</th>
            <th>Producer</th>
            <th>Count</th>
            <th>Cost</th>
            <th>Sum</th>
            <th>Change cost</th>
            <th>X</th>
        </tr>
        <c:if test="${sessionScope.basket == null}">
            ${emptyBasket}
            <br>
            <br>
        </c:if>
        <c:if test="${sessionScope.basket != null}">
            <c:forEach var="prod" items="${productBasket}" varStatus="myIndex">
                <tr>
                    <td>${myIndex.index + 1}</td>
                    <td>${prod.name}</td>
                    <td>${prod.category}</td>
                    <td>${prod.producer}</td>
                    <td>${prod.count}</td>
                    <td>${prod.cost}</td>
                    <td>${prod.sum}</td>
                    <td>
                        <form action="changeCount" method="post">
                            <input type="hidden" name="name" value="${prod.name}">
                            <input type="hidden" name="cost" value="${prod.cost}">
                            <input type="hidden" name="producer" value="${prod.producer}">
                            <input type="hidden" name="category" value="${prod.category}">
                            <input type="hidden" name="idProduct" value="${prod.idProduct}">
                            <input type="hidden" name="sum" value="${prod.sum}">
                            <input type="hidden" name="count" value="${prod.count}">

                            <input type="number" name="changedCount" value="${prod.count}" min="0" style="width: 50px; height: 30px; padding: 0; font-weight: bold;">
                            <input type="submit" value="Apply" style="width: 50px; height: 30px; padding: 0; font-weight: bold;">

                        </form>
                    </td>
                    <td>
                        <form action="clearCart" method="post">
                        <input type="hidden" name="action" value="removeRow">
                        <input type="hidden" name="idProduct" value="${prod.idProduct}">
                        <input type="submit" value="X" style="width: 50px; height: 30px; padding: 0; font-weight: bold;">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>

<br>
<c:if test="${sessionScope.totalCount == 0}">
Total price: 0 $
<br>
Total count: 0
</c:if>
<c:if test="${sessionScope.totalCount > 0}">
    Total price: ${sessionScope.totalCost}
    <br>
    Total count: ${sessionScope.totalCount}
  <br>   <a href="order" class="button">Order</a>
    <form action="clearCart" method="post">
        <input type="hidden" name="action" value="clear">
       <input type="submit" value="Clear" style="width: 50px; height: 30px; padding: 0; font-weight: bold;">
    </form>

</c:if>



