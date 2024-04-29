<%@ include file="./header.jsp" %>

<h1 class="text-center color-red">Error</h1>

<div class="text-center mt-5 fs-4">
    <c:choose>
        <c:when test="${!empty message}">
            <div class="alert alert-danger mb-5" role="alert">${message}</div>
        </c:when>
    </c:choose>
    <div class="card">
        <div class="card-header">Status Code: ${status}</div>
        <div class="card-body">Exception: ${exception}</div>
    </div>
</div>


<%@ include file="./footer.jsp" %>