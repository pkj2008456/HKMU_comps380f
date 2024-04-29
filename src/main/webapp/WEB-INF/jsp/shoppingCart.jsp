<%@ include file="./header.jsp" %>

<c:if test="${param.cartEdit != null}">
    <div class="alert alert-success mt-3" role="alert">Book quantity changed.</div>
</c:if>
<c:if test="${param.cartDeleted != null}">
    <div class="alert alert-success mt-3" role="alert">Book deleted.</div>
</c:if>

<div class="card mb-5">
    <h2 class="card-header">Shopping Cart</h2>
    <div class="card-body">
        <c:choose>
            <c:when test="${empty requestScope.cartItemList}">
        <div class="alert alert-info mt-3 fs-4 text-center" role="alert">Shopping Cart is empty.</div>
            </c:when>
            <c:otherwise>
                <div class="list-group">
                    <c:forEach items="${requestScope.cartItemList}" var="item" varStatus="status">
                        <div class="list-group-item" aria-current="true">
        <%--                        <div class="d-flex w-100 justify-content-between">--%>
                                <h5 class="mb-1">${item.name}</h5>
        <%--                        </div>--%>
                            <div class="mb-1">
                                <form action="<c:url value="/shopping/cart/edit/book/${item.id}"/>" method="post" class="needs-validation" novalidate>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <table>
                                        <tbody>
                                        <tr><th>Author : </th><td>${item.author}</td></tr>
                                        <tr><th>Price : </th><td>${item.price}</td></tr>
                                        <tr><th style="vertical-align: top">Quantity : </th><td>
                                            <input type="number" name="quantity" min="1" step="1" class="form-control" value="${item.quantity}" required>
                                            <div class="invalid-feedback">Please enter a Quantity.</div>
                                            <div class="mt-2 mb-3"><button type="submit" class="btn btn-primary">Edit Quantity</button></div>
                                        </td></tr>
                                        <tr><th width="130" style="vertical-align: top">Description : </th><td>${fn:replace(item.description, nl, '<br/>')}</td></tr>
                                        </tbody>
                                    </table>
                                    <div class="mt-3">
                                        <a href="<c:url value="/shopping/cart/delete/book/${item.id}" />" class="btn btn-danger ms-2">Remove</a>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </c:forEach>
                </div>
                <h3 class="mt-5">Total : ${totalPrice}</h3>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="text-center">
    <a href="<c:url value="/shopping/checkout" />" class="btn btn-primary">Checkout</a>
</div>

<%@ include file="./footer.jsp" %>