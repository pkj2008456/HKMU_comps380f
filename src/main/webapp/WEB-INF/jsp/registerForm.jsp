<%@ include file="./header.jsp" %>

<c:if test="${param.userExist != null}">
    <div class="alert alert-danger mt-3" role="alert">Username is exist.</div>
</c:if>

<h2>Register User</h2>

<div class="mt-5">
    <form:form method="POST" modelAttribute="registerForm" class="needs-validation" novalidate="true">
        <div class="row row-gap-3">
            <div class="col-12 col-md-6">
                <form:label path="username" class="form-label">Username</form:label><br/>
                <form:input type="text" path="username" class="form-control" required="true"/>
                <div class="invalid-feedback">Please enter a Username.</div>
            </div>
            <div class="col-12 col-md-6">
                <form:label path="password" class="form-label">Password</form:label><br/>
                <form:input type="password" path="password" class="form-control" required="true"/>
                <div class="invalid-feedback">Please enter a Password.</div>
            </div>
            <div class="col-12 col-md-6">
                <form:label path="fullName" class="form-label">Full Name</form:label><br/>
                <form:input type="text" path="fullName" class="form-control" required="true"/>
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
                <input type="submit" value="Create" class="btn btn-success"/>
            </div>
        </div>
    </form:form>
</div>

<%@ include file="./footer.jsp" %>