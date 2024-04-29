<%@ include file="./header.jsp" %>

<c:if test="${param.commentCreated != null}">
    <div class="alert alert-success mt-3" role="alert">Comment Created.</div>
</c:if>
<c:if test="${param.cartInserted != null}">
    <div class="alert alert-success mt-3" role="alert">Add to cart success.</div>
</c:if>
<c:if test="${param.commentEmpty != null}">
    <div class="alert alert-danger mt-3" role="alert">Comment is empty.</div>
</c:if>
<c:if test="${param.favoriteAdd != null}">
    <div class="alert alert-success mt-3" role="alert">Book has been added to Favorite.</div>
</c:if>

<h1>${bookInfo.name}</h1>

<div class="row">
    <div class="col-12 col-md-6">
        <div class="mb-5">
            <div class="img-thumbnail">
                <div id="carouselExample" class="carousel slide">
                    <div class="carousel-inner">
                        <c:forEach items="${requestScope.bookInfo.coverPhotos}" var="attachment" varStatus="status">
                            <div class="carousel-item <c:if test="${status.first}">active</c:if>"><img class="d-block w-100" src="<c:url value="/attachment/book/${requestScope.bookInfo.id}/coverPhoto/${attachment.id}" />"></div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="col-12 col-md-6">
        <div class="mb-3">
            <h5>Author</h5>
            <div class="card" style="width: 100%;">
                <div class="card-body">
                    <p class="card-text">${bookInfo.author}</p>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <h5>Price</h5>
            <div class="card" style="width: 100%;">
                <div class="card-body">
                    <p class="card-text">${bookInfo.price}</p>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <h5>Availability</h5>
            <div class="card" style="width: 100%;">
                <div class="card-body">
                    <p class="card-text">${bookInfo.availability}</p>
                </div>
            </div>
        </div>
        <security:authorize access="hasRole('USER')">
        <div class="mb-3">
            <c:choose>
                <c:when test="${isInsideFavoriteList == false}">
                <a href="<c:url value="/user/favorite/add/${bookInfo.id}" />" class="btn btn-primary w-100">Add to favorite</a>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">Has been add to favorite</a>
                </c:otherwise>
            </c:choose>
        </div>
        </security:authorize>

        <c:if test="${bookInfo.availability}">
        <security:authorize access="hasRole('USER')">
        <div class="mt-5">
            <form action="<c:url value="/shopping/cart/add/book/${bookInfo.id}"/>" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="mb-3">
                    <h5 class="form-label">Quantity</h5>
                    <input type="number" name="quantity" min="1" step="1" class="form-control" value="1" required>
                    <div class="invalid-feedback">Please enter a Quantity.</div>
                </div>
                <div class="mt-3">
                    <button type="submit" class="btn btn-primary mb-3">Add to cart</button>
                </div>
            </form>
        </div>
        </security:authorize>
        </c:if>
    </div>
</div>

<div class="mt-5 mb-5"><h5>Description</h5><p><c:set var="nl" value="<%= \"\n\" %>" />${fn:replace(bookInfo.description, nl, '<br/>')}</p></div>

<div class="row">
    <h5>Comments</h5>
    <c:forEach items="${requestScope.orderedComments}" var="item" varStatus="status">
        <div class="col-12 mb-3">
            <div class="card" style="width: 100%;">
                <div class="card-header">User : <c:out value="${item.user.fullName}" /></div>
                <div class="card-body">
                    <p class="card-text">${fn:replace(item.content, nl, '<br/>')}</p>
                    <small>
                        createTime : <fmt:formatDate pattern="YYYY-MM-dd HH:mm:ss" value="${item.createTime}" />
<%--                        <br/>lastUpdateTime : ${item.lastUpdateTime}--%>
                    </small>
                    <security:authorize access="hasRole('ADMIN')"><hr/><a href="<c:url value="/book/${bookInfo.id}/delete/comment/${item.id}" />" class="btn btn-danger">Delete</a></security:authorize>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


<security:authorize access="hasRole('USER')">
<div class="row">
    <div class="col-12">
        <form action="<c:url value="/book/${bookInfo.id}/add/comment"/>" method="post" class="needs-validation" novalidate>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="mb-3">
                <label class="form-label">Add Comment</label>
                <textarea name="comment" class="form-control" placeholder="Leave a comment here" style="min-height: 100px" required></textarea>
                <div class="invalid-feedback">Please enter a Comment.</div>
            </div>
            <div class="mt-3">
                <button type="submit" class="btn btn-primary mb-3">Submit</button>
            </div>
        </form>
    </div>
</div>
</security:authorize>

<%@ include file="./footer.jsp" %>