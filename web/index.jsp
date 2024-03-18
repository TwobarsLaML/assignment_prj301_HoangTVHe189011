<%-- 
    Document   : login
    Created on : Feb 19, 2024, 10:34:06 PM
    Author     : LAPTOP
--%><!-- comment -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Page</title>
        <%@include file="includes/header.jsp" %>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-light bg-light"><div class="mx-auto order-0">
                <a class="navbar-brand mx-auto" href="../eshoppingcart/">E Commerce Shopping</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse w-100 order-3">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link " href="../eshoppingcart/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../eshoppingcart/cart">Cart</a>
                    </li>
                    <% if(session.getAttribute("account") == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="login">Log in</a>
                    </li> 
                    <% } else { %> 
                    <li class="nav-item">
                        <a class="nav-link" href="order">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-primary" href="logout">Log out</a>
                    </li> 
                    <% } %>
                </ul>
            </div>
        </nav>
                <h3 class="text-danger text-center">${mes}</h3>
        <section class="py-1">
            <div class="container px-2 px-lg-2 mt-5">
                <h2 class="text-center bg-light ">All Items</h2>
                <div class="row gx-4 gx-lg-5 justify-content-center mt-5">
                    <!--Each item-->
                    <c:forEach items="${requestScope.iList}" var="i">
                    <div class="col-3 mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" 
                                 src="images/${i.img}" alt="Imaged image">
                            <!-- Product details -->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${i.name}</h5>
                                    <!-- Product price-->
                                    <div>Price: $${i.price}</div>
                                    <div>Category: ${i.category}</div>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center">
                                    <a class="btn btn-outline-dark mt-auto" 
                                        href="../eshoppingcart/addcart?username=${account.getUsername()}&item_id=${i.id}">Add to Cart</a>
                                    <a class="btn btn-outline-dark mt-auto" 
                                                            href="buynow?itemid=${i.id}">Buy now</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </section>
    </body>
    <%@include file="includes/footer.jsp" %>
</html>
