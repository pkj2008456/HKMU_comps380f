<%@ include file="./header.jsp" %>

<div class="alert alert-success mb-5 fs-3 text-center" role="alert">Order has been created.</div>

<div class="card mb-5">
    <h2 class="card-header">Order Id : ${userOrder.id}</h2>
    <div class="card-body">
        <table>
            <tbody>
            <tr><th>Full Name : </th><td>${userOrder.fullName}</td></tr>
            <tr><th>Email Address : </th><td>${userOrder.emailAddr}</td></tr>
            <tr><th>Delivery Address : </th><td>${userOrder.deliveryAddr}</td></tr>
            <tr><th>Total Price : </th><td>${userOrder.totalPrice}</td></tr>
            <tr><th>DateTime : </th><td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${userOrder.createTime}" /></td></tr>
            </tbody>
        </table>
        <div class="list-group mt-3">
            <c:forEach items="${userOrder.books}" var="item" varStatus="status">
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
    </div>
</div>

<%@ include file="./footer.jsp" %>