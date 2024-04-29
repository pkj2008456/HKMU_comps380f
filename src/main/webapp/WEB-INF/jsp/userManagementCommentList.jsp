<%@ include file="./header.jsp" %>

<c:if test="${param.commentCreated != null}">
    <div class="alert alert-success mt-3" role="alert">Comment Created.</div>
</c:if>

<h2>User : [${requestScope.userAcct.fullName}] Comment History</h2>

<div class="mt-5">
<c:choose>
    <c:when test="${empty requestScope.bookInfos}"><div class="alert alert-info mb-5 fs-3 text-center" role="alert">Comment History not found.</div></c:when>
    <c:otherwise>
        <c:forEach items="${requestScope.bookInfos}" var="info" varStatus="status">

            <div class="card mb-5">
                <h5 class="card-header"><c:out value="${info.name}" /></h5>
                <div class="card-body">
                    <div class="list-group">
                    <c:forEach items="${info.comments}" var="item" varStatus="status">

                        <div class="list-group-item" aria-current="true">
                            <p>${fn:replace(item.content, nl, '<br/>')}</p>
                            <small>createTime : <fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${item.createTime}" /></small>
                            <security:authorize access="hasRole('ADMIN')"><hr/><a href="<c:url value="/admin/user/comment/${requestScope.userAcct.username}/book/delete/${item.id}" />" class="btn btn-danger">Delete</a></security:authorize>
                        </div>

                    </c:forEach>
                    </div>
                </div>
            </div>

        </c:forEach>
    </c:otherwise>
</c:choose>
</div>

<div class="col-12 text-center mt-5">
    <a href="<c:url value="/admin/user/list"/>" class="btn btn-primary">Back</a>
</div>

<%@ include file="./footer.jsp" %>