<%@ include file="./header.jsp" %>

<c:if test="${param.deleteSelf != null}">
    <div class="alert alert-danger mt-3" role="alert">Can't delete yourself.</div>
</c:if>

<h2>List Users</h2>

<table class="table mt-5">
    <thead>
    <tr>
        <th scope="col">Username</th>
        <th scope="col">Full Name</th>
        <th scope="col">Email Address</th>
        <th scope="col">Delivery Address</th>
        <th scope="col">Roles</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    <c:forEach items="${userAccts}" var="user">
        <tr>
            <th scope="row"><a href="<c:url value="/admin/user/view/${user.username}"/>">${user.username}</a></th>
            <td>${user.fullName}</td>
            <td>${user.emailAddr}</td>
            <td>${user.deliveryAddr}</td>
            <td>
                <ul>
                    <c:forEach items="${user.roles}" var="roleItem">
                        <li>
                            <c:choose>
                                <c:when test="${roleItem.role == 'ROLE_USER'}">User</c:when>
                                <c:when test="${roleItem.role == 'ROLE_ADMIN'}">Admin</c:when>
                                <c:otherwise>${roleItem.role}</c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </td>
            <td width="100">
                <a href="<c:url value="/admin/user/comment/${user.username}"/>" class="btn btn-info w-100">Comments</a>
                <br/>
                <a href="<c:url value="/admin/user/order/${user.username}"/>" class="btn btn-info w-100 mt-1">Order</a>
            </td>
            <td width="100">
                <a href="<c:url value="/admin/user/edit/${user.username}"/>" class="btn btn-primary w-100">Edit</a>
                <br/>
                <a href="<c:url value="/admin/user/delete/${user.username}"/>" class="btn btn-danger w-100 mt-1">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="text-center mt-5"><a href="<c:url value="/admin/user/add"/>" class="btn btn-primary">Add</a></div>

<%@ include file="./footer.jsp" %>