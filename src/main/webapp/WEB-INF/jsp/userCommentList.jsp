<%@ include file="./header.jsp" %>

<h2>Comment History</h2>

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
            <%--                <div class="d-flex w-100 justify-content-between">--%>
            <%--                    <h5 class="mb-1">User : <c:out value="${item.user.fullName}" /></h5>--%>
            <%--                    <small>createTime : ${item.createTime}</small>--%>
            <%--                </div>--%>
                            <p>${fn:replace(item.content, nl, '<br/>')}</p>
                            <small>createTime : <fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${item.createTime}" /></small>
                        </div>

                <%--                    <security:authorize access="hasRole('ADMIN')"><br/><a href="<c:url value="/book/${bookInfo.id}/delete/comment/${item.id}" />" class="btn btn-danger">Delete</a></security:authorize>--%>
                    </c:forEach>
                    </div>
                </div>
            </div>

        </c:forEach>
    </c:otherwise>
</c:choose>
</div>

<%@ include file="./footer.jsp" %>