<%@ include file="./header.jsp" %>

<c:if test="${param.addSuccess != null}">
    <div class="alert alert-success mt-3" role="alert">Book create success.</div>
</c:if>

<c:if test="${param.editSuccess != null}">
    <div class="alert alert-success mt-3" role="alert">Book update success.</div>
</c:if>

<h2>View a Book</h2>

    <div class="row row-gap-3">
        <div class="col-12 col-md-6">
            <label path="name" class="form-label fw-bold">Name</label><br/>
            ${requestScope.bookInfo.name}
        </div>
        <div class="col-12 col-md-6">
            <label path="author" class="form-label fw-bold">Author</label><br/>
            ${requestScope.bookInfo.author}
        </div>
        <div class="col-12 col-md-6">
            <label path="price" class="form-label fw-bold">Price</label><br/>
            ${requestScope.bookInfo.price}
        </div>
        <div class="col-12 col-md-6">
            <label path="availability" class="form-label fw-bold">Availability</label><br/>
            ${requestScope.bookInfo.availability}
        </div>
        <div class="col-12">
            <label path="description" class="form-label fw-bold">Description</label><br/>
            <c:set var="nl" value="<%= \"\n\" %>" />${fn:replace(requestScope.bookInfo.description, nl, '<br/>')}
        </div>
        <div class="col-12">
            <label class="form-label fw-bold">Cover Photos</label><br/>
            <div class="row">
            <c:if test="${!empty requestScope.bookInfo.coverPhotos}">
                <c:forEach items="${requestScope.bookInfo.coverPhotos}" var="attachment" varStatus="status">
                    <div class="col-12 col-md-6 col-xl-4">
                        <div class="card" style="width: 100%;">
                            <img src="<c:url value="/attachment/book/${requestScope.bookInfo.id}/coverPhoto/${attachment.id}" />" class="card-img-top">
                            <div class="card-body">
                                [<a href="<c:url value="/admin/book/delete/${requestScope.bookInfo.id}/coverPhoto/${attachment.id}" />">Delete</a>]
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            </div>
        </div>
        <div class="col-12 text-center">
            <a href="<c:url value="/admin/book/edit/${ requestScope.bookInfo.id }"/>" class="btn btn-primary">Edit</a> <a href="<c:url value="/admin/book/list"/>" class="btn btn-primary">Back</a>
        </div>
    </div>


<%@ include file="./footer.jsp" %>