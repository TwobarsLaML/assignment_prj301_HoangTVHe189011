package controller;

import dal.CartDAO;
import dal.ItemDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Cart;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name = "DisplayCart", urlPatterns = {"/cart"})
public class DisplayCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        try {
            CartDAO cdao = new CartDAO();
            ItemDAO idao = new ItemDAO();
            List<Cart> list = cdao.getCartsFromID(account.getUsername());
            for(Cart c:list) {
                c.setItem(idao.getItemById(c.getItemId()));
            }
            request.setAttribute("cList", list);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
