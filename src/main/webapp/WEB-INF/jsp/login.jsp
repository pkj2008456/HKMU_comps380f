<%@ include file="./header.jsp" %>

<div style="width: 100%; max-width: 600px;margin: 0 auto">
    <div class="card">
        <h2 class="card-header">Login</h2>
        <div class="card-body">
            <form action="login" method="POST" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label>Username</label>
                    <input type="text" class="form-control" name="username" value="" required>
                    <div class="invalid-feedback">Please enter a Username.</div>
                </div>
                <div class="mb-3">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password" value="" required>
                    <div class="invalid-feedback">Please enter a Password.</div>
                </div>
                <div class="mb-3">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="remember-me" name="remember-me">
                        <label class="form-check-label" for="remember-me">
                            Remember me
                        </label>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="mt-3">
                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </div>
            </form>
        </div>
    </div>
    <c:if test="${param.error != null}">
        <div class="alert alert-danger mt-3" role="alert">Login failed.</div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-primary mt-3" role="alert">You have logged out.</div>
    </c:if>
</div>

<%@ include file="./footer.jsp" %>