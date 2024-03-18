package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name="Login", urlPatterns={"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        AccountDAO adao = new AccountDAO();
        List<Account> list = adao.getAllAccounts();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        for(Account a : list) {
            if(a.getUsername().equals(username) && a.getPassword().equals(password)) {
                session.setAttribute("account", a);
                request.getRequestDispatcher("display").forward(request, response);
//                response.sendRedirect("display");
            return;
            }
        }
        request.setAttribute("error", "The username or password is wrong.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
