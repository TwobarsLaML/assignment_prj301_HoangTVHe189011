<%-- 
    Document   : cart
    Created on : Feb 27, 2024, 11:28:29 PM
    Author     : LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <%@include file="includes/header.jsp" %>
        <script type ="text/javascript">
            function deDelete(id) {
                if (confirm("Are you sure?")) {
                    window.location = "delete?cart_id=" + id;
                }
            }
        </script>
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
        <h1 class="mr-5 my-1 pt-2 text-center">user [${sessionScope.account.username}]'s Cart</h1>
        <form action="addorder" method="get">
            <section class="py-1">
                <table class="table table-bordered my-5 mx-auto" style="width:70%">
                    <thead class="thead-light text-center">
                        <tr>
                            <th scope="col" style="width : 50%">Item Name</th>
                            <th>Unit Price</th>
                            <th>Quantity</th>
                            <th style="width: 15%">Price</th>
                            <th>Check</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <c:set var="sum" value="${0}"/>
                    <c:forEach items="${cList}" var="c">
                        <tr style="text-align: center">
                            <td>${c.item.name}</td>
                            <td>
                                <fmt:formatNumber maxFractionDigits="2" groupingUsed = "false" 
                                                  value="${c.item.price}"/>
                            </td>
                            <td style="text-align: center">
                                <c:set var="number" value="${c.quantity}"/>
                                <c:if test="${number != 1}">
                                    <a href="decrease?cart_id=${c.id}"
                                       class="btn btn-secondary btn-sm" role="button" >-</a>
                                </c:if>
                                <c:if test="${number == 1}">
                                    <a class="btn btn-secondary btn-sm disabled" role="button">-</a>
                                </c:if>
                                <span style="width : 20px" class="d-inline-block">${c.quantity}</span>
                                <a href="increase?cart_id=${c.id}" class="btn btn-secondary btn-sm" role="button">+</a>
                            </td>
                            <td>
                                <fmt:formatNumber maxFractionDigits="2" groupingUsed = "false" 
                                                  value="${(c.item.price*number)}"/>
                                <c:set var="sum" value="${sum + (c.item.price*number)}"/>
                            </td>
                            <td>
                                <input type="checkbox" name="chosen" class="checkbox" value="${c.id}" price="${(c.item.price*number)}" onchange="printTotal()"/>
                            </td>
                            <td>
                                <a href="delete?cart_id=${c.id}" onclick="if (!confirm('Are you sure?'))
                                            return false"
                                   class="btn btn-danger btn-sm" role="button" >X</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <h2 id="totalPrice" class="ml-5">Total price: $0</h2>
                <input type="submit" value="Order selected Item(s)" class="px-2 mx-5 btn btn-success"/>
                <h3 class="px-2 mx-4 text-danger d-inline-block">${mes}</h3>
            </section>
        </form>
    </body>

    <script>
        const allPrices = document.querySelectorAll('.checkbox');
        const totalPrice = document.querySelector('#totalPrice');
        
        
        function printTotal() {
            let total = 0;
            for (let i = 0; i < allPrices.length; i++) {
            console.log(allPrices[i].checked === true);
                
            if (allPrices[i].checked == true) {// Parse the value and add it to the total
                    const value = Number(allPrices[i].getAttribute('price'));
                    total += value;
                }
            }
            totalPrice.textContent = 'Total price: $' + Math.round(total * 100) / 100;
        }
    </script>

    <%@include file="includes/footer.jsp" %>
</html>
