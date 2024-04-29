<%@ include file="./header.jsp" %>

<c:if test="${param.contentEmpty != null}">
    <div class="alert alert-danger mt-3" role="alert">Content is empty.</div>
</c:if>

<c:if test="${param.editSuccess != null}">
    <div class="alert alert-success mt-3" role="alert">User Info update success.</div>
</c:if>

<h2>User Info</h2>
<form:form method="POST" modelAttribute="userInfoForm" class="needs-validation" novalidate="true">
    <div class="row row-gap-3 mt-5">
        <div class="col-12 col-md-6">
            <label class="form-label">Username</label><br/>
                ${ requestScope.userInfo.username }
            <div class="invalid-feedback">Please enter a Username.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="password" class="form-label">Password</form:label><br/>
            <input type="password" id="password" name="password" value="" class="form-control" />
            <div class="invalid-feedback">Please enter a Password.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="fullName" class="form-label">Full Name</form:label><br/>
            <form:input type="text" path="fullName" class="form-control" required="true" />
            <div class="invalid-feedback">Please enter a Full Name.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="emailAddr" class="form-label">Email Address</form:label><br/>
            <form:input type="email" path="emailAddr" class="form-control"/>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="deliveryAddr" class="form-label">Delivery Address</form:label><br/>
            <form:input type="text" path="deliveryAddr" class="form-control"/>
        </div>

        <div class="col-12 text-center mt-5">
            <input type="submit" value="Save" class="btn btn-success"/>
        </div>
    </div>
</form:form>

<%@ include file="./footer.jsp" %>