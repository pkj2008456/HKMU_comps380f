<%@ include file="./header.jsp" %>

<c:if test="${param.contentEmpty != null}">
    <div class="alert alert-danger mt-3" role="alert">Content is empty.</div>
</c:if>

<h2>Edit a User</h2>
<form:form method="POST" modelAttribute="userAcctForm" class="needs-validation" novalidate="true">
    <div class="row row-gap-3 mt-5">
        <div class="col-12 col-md-6">
            <form:label path="username" class="form-label">Username</form:label><br/>
            ${ requestScope.userAcct.username }
            <form:input type="hidden" path="username" class="form-control" readonly="true" />
            <div class="invalid-feedback">Please enter a Username. Only [A-Za-z0-9]. minlength 4.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="password" class="form-label">Password</form:label><br/>
            <input type="password" id="password" name="password" value="" class="form-control" minlength="6" />
            <div class="invalid-feedback">Please enter a Password. minlength 6.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="fullName" class="form-label">Full Name</form:label><br/>
            <form:input type="text" path="fullName" class="form-control" required="true" />
            <div class="invalid-feedback">Please enter a Full Name.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="emailAddr" class="form-label">Email Address</form:label><br/>
            <form:input type="email" path="emailAddr" class="form-control"/>
            <div class="invalid-feedback">Please enter a Email Address.</div>
        </div>
        <div class="col-12 col-md-6">
            <form:label path="deliveryAddr" class="form-label">Delivery Address</form:label><br/>
            <form:input type="text" path="deliveryAddr" class="form-control"/>
            <div class="invalid-feedback">Please enter a Delivery Address.</div>
        </div>

        <div class="col-12">
            <label class="form-label">Role</label><br/>
            <div class="form-check form-check-inline"><label class="form-check-label"><form:checkbox path="roles" value="ROLE_USER" class="form-check-input" checked="true" disabled="true"/> User</label></div>
            <div class="form-check form-check-inline"><label class="form-check-label"><form:checkbox path="roles" value="ROLE_ADMIN" class="form-check-input"/> Admin</label></div>
        </div>
        <div class="col-12 text-center mt-5">
            <input type="submit" value="Save" class="btn btn-success"/> <a href="<c:url value="/admin/user/list"/>" class="btn btn-primary">Back</a>
        </div>
    </div>
</form:form>

<%@ include file="./footer.jsp" %>