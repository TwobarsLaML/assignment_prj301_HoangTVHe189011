<%-- 
    Document   : order
    Created on : Mar 6, 2024, 8:39:15 PM
    Author     : LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp" %>
        <title>JSP Page</title>
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
        <h1 class="mx-5 pt-3">User [${sessionScope.account.username}]'s Orders</h1>
        <table class="table-sm table-striped mx-5">
            <tr>
                <th>No.</th>
                <th width="65%">Item Name</th>
                <th>Quantity</th>
                <th>Final Price</th>
            </tr>
            <c:set var="index" value="${1}"/>
            <c:set var="sum" value="${0}"/>
            <c:if test="${oList.size() == 0}">
                <tr>
                    <td></td>
                    <td>There aren't any orders</td>
                </tr>
            </c:if>
            <c:if test="${oList.size() != 0}">
                <c:forEach items="${oList}" var="o">
                    <tr style="text-align: center">
                        <td>${index}</td>
                        <td>${o.item.name}</td>
                        <td>${o.quantity}</td>
                        <td>
                            $<fmt:formatNumber maxFractionDigits="2" groupingUsed = "false" 
                                              value="${o.totalPrice}"/>
                        </td>
                    </tr>
                    <c:set var="sum" value="${sum + o.totalPrice}"/>
                    <c:set var="index" value="${index + 1}"/>
                </c:forEach>
            </c:if>    
        </table>
        <c:if test="${oList.size() > 0}">
            <h2 class="mx-5">
                Total Price: $<fmt:formatNumber maxFractionDigits="2" groupingUsed = "false" 
                                  value="${sum}"/>
            </h2>
        </c:if>
    </body>
    <%@include file="includes/footer.jsp" %>
</html>
