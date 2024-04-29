<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Online Book Store</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container">
                <a class="navbar-brand" href="#">Book Shop</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/"/>">Home</a>
                        </li>
                        <security:authorize access="hasRole('USER')">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/order/list"/>">User Orders</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/comment/list"/>">User Comments</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/info"/>">User Info</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/favorite"/>">Favorite</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/shopping/cart"/>">Shopping Cart</a>
                            </li>
                        </security:authorize>
                        <security:authorize access="hasRole('ADMIN')">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/admin/user"/>">User Manage</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/admin/book"/>">Book Manage</a>
                            </li>
                        </security:authorize>
                    </ul>
                </div>
            </div>
        </nav>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div></div>
            <div class="container">
                <div style="margin-left: auto; display: flex; align-items: center">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal != null}">
                            <label class="pe-3">Hi ${pageContext.request.userPrincipal.name}</label>
                            <c:url var="logoutUrl" value="/logout"/>
                            <form action="${logoutUrl}" method="post">
                                <input type="submit" value="Log out" class="btn btn-danger" />
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/register"/>" class="btn btn-primary mx-2">Register</a>
                            <a href="<c:url value="/login"/>" class="btn btn-primary">Login</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
    </nav>
    </header>
    <div class="container pt-5 pb-5">