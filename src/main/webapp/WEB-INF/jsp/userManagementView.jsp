<%@ include file="./header.jsp" %>

<c:if test="${param.addSuccess != null}">
    <div class="alert alert-success mt-3" role="alert">User create success.</div>
</c:if>

<c:if test="${param.editSuccess != null}">
    <div class="alert alert-success mt-3" role="alert">User update success.</div>
</c:if>

<h2>View a User</h2>
<div class="row row-gap-3">
        <div class="col-12 col-md-6">
            <label path="username" class="form-label fw-bold">Username</label><br/>
            ${ requestScope.userAcct.username }
        </div>
        <div class="col-12 col-md-6">
            <label path="password" class="form-label fw-bold">Password</label><br/>
            ${fn:substringAfter(requestScope.userAcct.password, '{noop}')}
        </div>
        <div class="col-12 col-md-6">
            <label path="fullName" class="form-label fw-bold">Full Name</label><br/>
            ${ requestScope.userAcct.fullName }
        </div>
        <div class="col-12 col-md-6">
            <label path="emailAddr" class="form-label fw-bold">Email Address</label><br/>
            ${ requestScope.userAcct.emailAddr }
        </div>
        <div class="col-12 col-md-6">
            <label path="deliveryAddr" class="form-label fw-bold">Delivery Address</label><br/>
            ${ requestScope.userAcct.deliveryAddr }
        </div>

        <div class="col-12">
            <label class="form-label fw-bold">Role</label><br/>
            <ul>
                <c:forEach items="${requestScope.userAcct.roles}" var="roleItem">
                    <li>
                        <c:choose>
                            <c:when test="${roleItem.role == 'ROLE_USER'}">User</c:when>
                            <c:when test="${roleItem.role == 'ROLE_ADMIN'}">Admin</c:when>
                            <c:otherwise>${roleItem.role}</c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-12 text-center">
            <a href="<c:url value="/admin/user/edit/${ requestScope.userAcct.username }"/>" class="btn btn-primary">Edit</a> <a href="<c:url value="/admin/user/list"/>" class="btn btn-primary">Back</a>
        </div>
</div>

<%@ include file="./footer.jsp" %>