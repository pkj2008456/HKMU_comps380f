<%@ include file="./header.jsp" %>

<h2>Book List</h2>

<div class="mt-5">
    <c:choose>
        <c:when test="${empty bookInfos}"><div class="alert alert-info mb-5 fs-3 text-center" role="alert">Books not found.</div></c:when>
        <c:otherwise>
            <div class="row">
                <c:forEach items="${bookInfos}" var="item" varStatus="loop">
                    <div class="col-6 col-md-4 col-xl-3 pb-3">
                        <div class="card" style="width: 100%;">
                            <div id="carouselExample-${loop.index}" class="card-img-top carousel slide">
                                <div class="carousel-inner">
                                    <c:forEach items="${item.coverPhotos}" var="attachment" varStatus="status">
                                        <div class="carousel-item <c:if test="${status.first}">active</c:if>"><img class="d-block w-100" src="<c:url value="/attachment/book/${item.id}/coverPhoto/${attachment.id}" />"></div>
                                    </c:forEach>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample-${loop.index}" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExample-${loop.index}" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${item.name}</h5>
                                <div class="card-text">
                                    <label class="fw-bold">Author</label>
                                    <div>${item.author}</div>
                                    <label class="fw-bold">Price</label>
                                    <div>${item.price}</div>
                                    <label class="fw-bold">Availability</label>
                                    <div>${item.availability}</div>
                                </div>
                                <div>
                                    <hr/>
                                    <a href="<c:url value="/book/${item.id}" />" class="btn btn-primary w-100">Detail</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="./footer.jsp" %>