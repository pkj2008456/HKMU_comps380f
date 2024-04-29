<%@ include file="./header.jsp" %>

<h2>List Books</h2>

<table class="table mt-5">
    <thead>
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Author</th>
        <th scope="col">Price</th>
        <th scope="col">Availability</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    <c:forEach items="${bookInfos}" var="bookInfo">
        <tr>
            <th scope="row"><a href="<c:url value="/admin/book/view/${bookInfo.id}"/>">${bookInfo.name}</a></th>
            <td>${bookInfo.author}</td>
            <td>${bookInfo.price}</td>
            <td>${bookInfo.availability}</td>
            <td width="100">
                <a href="<c:url value="/admin/book/edit/${bookInfo.id}"/>" class="btn btn-primary w-100">Edit</a>
                <br/>
                <a href="<c:url value="/admin/book/delete/${bookInfo.id}"/>" class="btn btn-danger w-100 mt-1">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="text-center mt-5"><a href="<c:url value="/admin/book/add"/>" class="btn btn-primary">Add</a></div>

<%@ include file="./footer.jsp" %>