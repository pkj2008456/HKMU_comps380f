<%@ include file="./header.jsp" %>

<c:if test="${param.cartEdit != null}">
    <div class="alert alert-success mt-3" role="alert">Book quantity changed.</div>
</c:if>
<c:if test="${param.cartDelete != null}">
    <div class="alert alert-success mt-3" role="alert">Book deleted.</div>
</c:if>

<div class="card mb-5">
    <h2 class="card-header">Checkout</h2>
    <div class="card-body">
        <div class="list-group">
            <c:forEach items="${requestScope.cartItemList}" var="item" varStatus="status">
                <div class="list-group-item" aria-current="true">
                    <h5 class="mb-1">${item.name}</h5>
                    <table>
                        <tbody>
                        <tr><th>Author : </th><td>${item.author}</td></tr>
                        <tr><th>Price : </th><td>${item.price}</td></tr>
                        <tr><th>Quantity : </th><td>${item.quantity}</td></tr>
                        <tr><th width="130" style="vertical-align: top">Description : </th><td>${fn:replace(item.description, nl, '<br/>')}</td></tr>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </div>
        <h3 class="mt-5">Total : ${totalPrice}</h3>
    </div>
</div>

<form:form method="POST" modelAttribute="checkoutForm" class="needs-validation" novalidate="true">

    <div class="row row-gap-3">
        <div class="col-12 col-md-6">
            <form:label path="fullName" class="form-label fw-bold">Full Name</form:label><br/>
            <form:input type="text" path="fullName" class="form-control" required="true"/>
            <div class="invalid-feedback">Please enter a Full Name.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="emailAddr" class="form-label fw-bold">Email Address</form:label><br/>
            <form:input type="email" path="emailAddr" class="form-control" required="true"/>
            <div class="invalid-feedback">Please enter a Email Address.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="deliveryAddr" class="form-label fw-bold">Delivery Address</form:label><br/>
            <form:input type="text" path="deliveryAddr" class="form-control" required="true"/>
            <div class="invalid-feedback">Please enter a Delivery Address.</div>
        </div>

        <div class="col-12 text-center">
            <input type="submit" value="Checkout" class="btn btn-success"/> <a href="<c:url value="/shopping/cart"/>" class="btn btn-primary">Back</a>
        </div>
    </div>

</form:form>

<%@ include file="./footer.jsp" %>