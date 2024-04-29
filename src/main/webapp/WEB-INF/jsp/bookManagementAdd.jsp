<%@ include file="./header.jsp" %>

<c:if test="${param.contentEmpty != null}">
    <div class="alert alert-danger mt-3" role="alert">Content is empty.</div>
</c:if>

<h2>Create a Book</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="bookInfoForm" class="needs-validation" novalidate="true">
    <div class="row row-gap-3 mt-5">
        <div class="col-12 col-md-6">
            <form:label path="name" class="form-label">Name</form:label><br/>
            <form:input type="text" path="name" class="form-control" required="true"/>
            <div class="invalid-feedback">Please enter a Name.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="author" class="form-label">Author</form:label><br/>
            <form:input type="text" path="author" class="form-control" required="true"/>
            <div class="invalid-feedback">Please enter a Author.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="price" class="form-label">Price</form:label><br/>
            <form:input type="number" path="price" class="form-control" min="0" step="0.1" required="true"/>
            <div class="invalid-feedback">Please enter a Price.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="availability" class="form-label">Availability</form:label><br/>
            <div class="form-check">
                <form:radiobutton path="availability" value="true" id="availability-y" class="form-check-input" required="true"/>
                <label for="availability-y" class="form-check-label">Yes</label>
            </div>
            <div class="form-check">
                <form:radiobutton path="availability" value="false" id="availability-n" class="form-check-input" required="true"/>
                <label for="availability-n" class="form-check-label">No</label>
                <div class="invalid-feedback">Please enter a Availability.</div>
            </div>
        </div>
        <div class="col-12">
            <form:label path="description" class="form-label">Description</form:label><br/>
            <form:textarea type="address" path="description" class="form-control" rows="5" cols="30"/>
        </div>
        <div class="col-12">
            <label class="form-label">Add Cover Photos</label><br/>
            <input type="file" name="coverPhotos" class="form-control" multiple="multiple" required="true" accept="image/*"/>
        </div>
        <div class="col-12 text-center mt-5">
            <input type="submit" value="Save" class="btn btn-success"/> <a href="<c:url value="/admin/book/list"/>" class="btn btn-primary">Back</a>
        </div>
    </div>
</form:form>

<%@ include file="./footer.jsp" %>