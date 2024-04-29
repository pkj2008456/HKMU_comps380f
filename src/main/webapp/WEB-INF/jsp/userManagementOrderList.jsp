<%@ include file="./header.jsp" %>

<h2>User : [${requestScope.userAcct.fullName}] Order History</h2>

<div class="mt-5">
    <c:choose>
        <c:when test="${empty requestScope.userOrders}"><div class="alert alert-info mb-5 fs-3 text-center" role="alert">Order History not found.</div></c:when>
        <c:otherwise>

            <div class="list-group">
                <c:forEach items="${requestScope.userOrders}" var="order" varStatus="status">
                    <div class="list-group-item" aria-current="true">
                        <h3>Order Id : ${order.id}</h3>
                        <hr/>
                        <table>
                            <tbody>
                            <tr><th>Full Name : </th><td>${order.fullName}</td></tr>
                            <tr><th>Email Address : </th><td>${order.emailAddr}</td></tr>
                            <tr><th>Delivery Address : </th><td>${order.deliveryAddr}</td></tr>
                            <tr><th>Total Price : </th><td>${order.totalPrice}</td></tr>
                            <tr><th>DateTime : </th><td><fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${order.createTime}" /></td></tr>
                            </tbody>
                        </table>
                        <a href="<c:url value="/admin/user/order/${requestScope.userAcct.username}/view/${order.id}"/>" class="btn btn-info mt-3 w-100">View</a>
                    </div>
                </c:forEach>
            </div>

        </c:otherwise>
    </c:choose>
</div>

<div class="col-12 text-center mt-5">
    <a href="<c:url value="/admin/user/list"/>" class="btn btn-primary">Back</a>
</div>

<%@ include file="./footer.jsp" %>