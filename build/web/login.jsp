<%-- 
    Document   : login
    Created on : Feb 19, 2024, 10:34:06 PM
    Author     : LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Cart Login</title>
        <%@include file="includes/header.jsp" %>
    </head>
    <body>
        <div class="bg-light text-center w-75 mx-auto">
            <a class="navbar-brand mx-auto text-center" href="../eshoppingcart/">Return to the page</a>
        </div>
        <h1 class="text-center mt-5">Welcome to the Page!</h1>
        
        <% if(request.getAttribute("error") == null) { %>
        <h3 class="text-center text-danger"> Please login to access to the page </h3>
        <% } else { %>
        <h3 class="text-center text-danger"> ${error} </h3>
        <% } %>
        <div class ="container-fluid">
            <div class ="card w-50 mx-auto my-5">
                <div class ="card-header text-center">User Login</div>
                <div class ="card-body">
                    <form action ="login" method ="post">
                        <div class ="form-group">
                            <label>Username</label>
                            <input type ="text" class ="form-control" 
                                   name ="username" placeholder ="Enter Your Username" required/>
                        </div>
                        <div class ="form-group">
                            <label>Password</label>
                            <input type ="password" class ="form-control" 
                                   name ="password" placeholder ="********" required/>
                        </div>
                        
                        <div class="text-center">
                            <button type =" submit" class ="btn btn-primary">Login</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
    </body>
    <%@include file="includes/footer.jsp" %>
</html>
