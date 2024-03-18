<%-- 
    Document   : buynow
    Created on : Mar 10, 2024, 11:37:52 PM
    Author     : LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <title>Buy Now Page</title>
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
        <c:set var="i" value="${item}"/>
        <div class="row my-4">
            <div class="col">
                <!--Demo Image-->
                <img src="images/${i.img}" alt="Imaged image" style="width: 50%" class="rounded mx-auto d-block">
            </div>
            <div class="col my-5">
                <div class="my-5"></div>
                <form action="buynow?itemid=${i.id}" method="post">
                    <div>
                        <h2>${i.name}</h2>
                        <br>
                        <p>Price: $${i.price}</p>
                        <p id="price" style="display: none">${i.price}</p>
                        <p>Category: ${i.category}</p>
                    </div>
                    <p>Quantity: <input type="number" name="quantity" min="1" id="quantity" 
                                         oninput="printTotalPrice()" required/></p><br>
                    <p id="totalPrice">Total Price: $0</p>
                    <input type="submit" value="ORDER" class="px-2 mx-5 btn btn-success"/>
                </form>
                <h3 style="color:red">${mess}</h3>
            </div>
        </div>
        <script>
            const total = document.querySelector('#totalPrice');
            const input = document.querySelector('#quantity');
            const price = document.querySelector('#price');
            function printTotalPrice() {
                const quan = input.value;
                const totalprice = Number(quan) * Number(price.textContent);
                if (quan == 0) {
                    total.textContent = '';
                } else {
                    total.textContent = 'Total Price: $' + Math.round(totalprice * 100) / 100;
                }
            }
        </script>
    </body>
    <%@include file="includes/footer.jsp" %>
</html>
